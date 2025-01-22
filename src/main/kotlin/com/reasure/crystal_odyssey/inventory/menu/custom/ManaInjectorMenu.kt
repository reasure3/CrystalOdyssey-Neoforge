package com.reasure.crystal_odyssey.inventory.menu.custom

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.inventory.handler.ContainerMenuItemStackHandler
import com.reasure.crystal_odyssey.inventory.handler.OutputItemStackHandler
import com.reasure.crystal_odyssey.inventory.menu.BaseRecipeContainerMenu
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.inventory.slot.OutputSlotItemHandler
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.RecipeManager
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.SlotItemHandler
import kotlin.math.max

class ManaInjectorMenu(containerId: Int, playerInventory: Inventory, pos: BlockPos) :
    BaseRecipeContainerMenu(ModMenuTypes.MANA_INJECTOR_MENU, containerId, playerInventory, pos) {
    var requireLevel: Int = 0
        private set
    var hasRecipe: Boolean = false
        private set

    private val inputHandler: ItemStackHandler = ContainerMenuItemStackHandler(this, 1)
    private val outputHandler: ItemStackHandler = OutputItemStackHandler()

    private val quickCheck: RecipeManager.CachedCheck<SingleRecipeInput, ManaInjectingRecipe> =
        RecipeManager.createCheck(ModRecipeTypes.MANA_INJECTING_RECIPE_TYPE)

    constructor(containerId: Int, playerInventory: Inventory, extraData: FriendlyByteBuf) : this(
        containerId,
        playerInventory,
        extraData.readBlockPos()
    )

    init {
        addSlot(SlotItemHandler(inputHandler, 0, 45, 34))
        addSlot(OutputSlotItemHandler(this, outputHandler, 0, 111, 34))
        addPlayerInventory(playerInventory, 8, 84)
        addPlayerHotbar(playerInventory, 8, 142)
    }

    override fun onCraft(player: Player, stack: ItemStack) {
        val recipe = currentRecipe() ?: return
        inputHandler.extractItem(0, 1, false)
        if (!player.isCreative && player is ServerPlayer) {
            val levelPerOne = recipe.value.requireLevel
            val craftedCount = stack.count / recipe.value.result.count
            player.setExperienceLevels(max(player.experienceLevel - levelPerOne * craftedCount, 0))
        }
    }

    private fun currentRecipeInput() = SingleRecipeInput(slots[INPUT_SLOT_ID].item)

    private fun currentRecipe(): RecipeHolder<ManaInjectingRecipe>? {
        return quickCheck.getRecipeFor(currentRecipeInput(), level).orElse(null)
    }

    override fun setRecipe() {
        val recipe = currentRecipe()
        if (recipe == null) {
            clearResult()
            return
        }
        hasRecipe = true
        setRequireLevel(recipe)
        if (isSatisfyRequireLevel()) {
            setResult(recipe)
        } else {
            setResultToEmpty()
        }
    }

    override fun setRecipeClient() {
        val recipe = currentRecipe()
        if (recipe == null) {
            hasRecipe = false
            requireLevel = 0
        } else {
            hasRecipe = true
            setRequireLevel(recipe)
        }
    }

    private fun setResult(recipe: RecipeHolder<ManaInjectingRecipe>) {
        outputHandler.setStackInSlot(0, recipe.value.assemble(currentRecipeInput(), level.registryAccess()))
    }

    private fun setResultToEmpty() {
        outputHandler.setStackInSlot(0, ItemStack.EMPTY)
    }

    private fun setRequireLevel(recipe: RecipeHolder<ManaInjectingRecipe>) {
        requireLevel = recipe.value.requireLevel
    }

    private fun clearResult() {
        outputHandler.setStackInSlot(0, ItemStack.EMPTY)
        requireLevel = 0
        hasRecipe = false
    }

    private fun clearContainer(player: Player) {
        if (!player.isAlive || (player is ServerPlayer && player.hasDisconnected())) {
            player.drop(inputHandler.getStackInSlot(0).copy(), false)
        } else {
            val inventory: Inventory = player.inventory
            if (player is ServerPlayer) {
                inventory.placeItemBackInInventory(inputHandler.getStackInSlot(0))
            }
        }
    }

    fun isSatisfyRequireLevel(): Boolean {
        if (player.isCreative) return true
        return player.experienceLevel >= requireLevel
    }

    override fun removed(player: Player) {
        super.removed(player)
        clearContainer(player)
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        val sourceSlot: Slot = slots[index]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY
        val sourceStack: ItemStack = sourceSlot.item
        val copyOfSourceStack: ItemStack = sourceStack.copy()

        when (index) {
            OUTPUT_SLOT_ID -> {
                if (!moveItemStackTo(sourceStack, INVENTORY_FIRST_SLOT_ID, HOTBAR_LAST_SLOT_ID + 1, true)) {
                    return ItemStack.EMPTY
                }
            }

            INPUT_SLOT_ID -> {
                if (!moveItemStackTo(sourceStack, INVENTORY_FIRST_SLOT_ID, HOTBAR_LAST_SLOT_ID + 1, false)) {
                    return ItemStack.EMPTY
                }
            }

            // source slot is inventory or hotbar
            else -> {
                if (!moveItemStackTo(sourceStack, INPUT_SLOT_ID, INPUT_SLOT_ID + 1, false)) {
                    val targetSlotRange = if (index < HOTBAR_FIRST_SLOT_ID) { // sourceSlot is inventory
                        HOTBAR_FIRST_SLOT_ID..HOTBAR_LAST_SLOT_ID // to hotbar
                    } else {
                        INVENTORY_FIRST_SLOT_ID..<HOTBAR_FIRST_SLOT_ID // to inventory
                    }
                    if (!moveItemStackTo(sourceStack, targetSlotRange.first, targetSlotRange.last + 1, false)) {
                        return ItemStack.EMPTY
                    }
                }
            }
        }

        if (sourceStack.isEmpty) {
            sourceSlot.setByPlayer(ItemStack.EMPTY)
        }

        if (sourceStack.count == copyOfSourceStack.count) {
            return ItemStack.EMPTY
        }

        if (index == OUTPUT_SLOT_ID) {
            sourceSlot.onTake(player, copyOfSourceStack.copy())
            player.drop(sourceStack, false)
        }

        return copyOfSourceStack
    }

    override fun getBlock(): Block = ModBlocks.MANA_INJECTOR

    override fun canTakeItemForPickAll(stack: ItemStack, slot: Slot): Boolean {
        return slot.index != OUTPUT_SLOT_ID && super.canTakeItemForPickAll(stack, slot)
    }

    companion object {
        const val INPUT_SLOT_ID = 0
        const val OUTPUT_SLOT_ID = 1
        const val INVENTORY_FIRST_SLOT_ID = 2
        const val HOTBAR_FIRST_SLOT_ID = 29
        const val HOTBAR_LAST_SLOT_ID = 37
    }
}