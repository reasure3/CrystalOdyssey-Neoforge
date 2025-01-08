package com.reasure.crystal_odyssey.inventory.menu.custom

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.inventory.handler.OutputItemStackHandler
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.inventory.slot.FakeSlotItemHandler
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.RecipeManager
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.neoforged.neoforge.items.ItemStackHandler
import net.neoforged.neoforge.items.SlotItemHandler
import kotlin.math.max

class ManaInjectorMenu(containerId: Int, playerInventory: Inventory, private val pos: BlockPos) :
    AbstractContainerMenu(ModMenuTypes.MANA_INJECTOR_MENU, containerId) {
    private var requireLevel: Int = 0
    private var hasRecipe: Boolean = false

    private val player: Player = playerInventory.player
    private val level: Level = playerInventory.player.level()

    private val inputHandler: ItemStackHandler = object : ItemStackHandler(1) {
        override fun onContentsChanged(slot: Int) {
            onSlotChanged()
        }
    }
    private val outputHandler: ItemStackHandler = OutputItemStackHandler()
    private val data: ContainerData = createData()

    private val quickCheck: RecipeManager.CachedCheck<SingleRecipeInput, ManaInjectingRecipe> =
        RecipeManager.createCheck(ModRecipeTypes.MANA_INJECTING_RECIPE_TYPE)

    constructor(containerId: Int, playerInventory: Inventory, extraData: FriendlyByteBuf) : this(
        containerId,
        playerInventory,
        extraData.readBlockPos()
    )

    init {
        addSlot(SlotItemHandler(inputHandler, 0, 45, 34))
        addSlot(object : FakeSlotItemHandler(outputHandler, 0, 111, 34) {
            override fun onTake(player: Player, stack: ItemStack) {
                onCraft(player, stack)
                super.onTake(player, stack)
                setRecipe()
            }
        })
        addPlayerInventory(playerInventory, 8, 84)
        addPlayerHotbar(playerInventory, 8, 142)
        addDataSlots(data)
    }

    private fun onCraft(player: Player, stack: ItemStack) {
        val recipe = currentRecipe() ?: return
        inputHandler.extractItem(0, 1, false)
        if (!player.isCreative && player is ServerPlayer) {
            val levelPerOne = recipe.value.requireLevel
            val craftedCount = stack.count / recipe.value.result.count
            player.setExperienceLevels(max(player.experienceLevel - levelPerOne * craftedCount, 0))
        }
    }

    private fun currentRecipe(): RecipeHolder<ManaInjectingRecipe>? {
        return quickCheck.getRecipeFor(SingleRecipeInput(inputHandler.getStackInSlot(0)), level).orElse(null)
    }

    private fun onSlotChanged() {
        if (level.isClientSide) return
        setRecipe()
        broadcastChanges()
    }

    private fun setRecipe() {
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

    private fun setResult(recipe: RecipeHolder<ManaInjectingRecipe>) {
        outputHandler.setStackInSlot(0, recipe.value.result.copy())
    }

    private fun setResultToEmpty() {
        outputHandler.setStackInSlot(0, ItemStack.EMPTY)
    }

    private fun setRequireLevel(recipe: RecipeHolder<ManaInjectingRecipe>) {
        data.set(REQUIRE_LEVEL_DATA_SLOT, recipe.value.requireLevel)
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
            val inventory = player.inventory
            if (player is ServerPlayer) {
                inventory.placeItemBackInInventory(inputHandler.getStackInSlot(0))
            }
        }
    }

    fun isSatisfyRequireLevel(): Boolean {
        if (player.isCreative) return true
        return player.experienceLevel >= data[REQUIRE_LEVEL_DATA_SLOT]
    }

    fun getRequireLevel(): Int {
        return data[REQUIRE_LEVEL_DATA_SLOT]
    }

    fun isHaveRecipe(): Boolean {
        return data[HAS_RECIPE_DATA_SLOT] > 0
    }

    override fun removed(player: Player) {
        super.removed(player)
        clearContainer(player)
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        val sourceSlot: Slot = slots[index]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY
        val sourceStack = sourceSlot.item
        val copyOfSourceStack = sourceStack.copy()

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

    override fun stillValid(player: Player): Boolean {
        if (level.isClientSide) return true
        if (!level.getBlockState(pos).`is`(ModBlocks.MANA_INJECTOR)) return false
        return player.canInteractWithBlock(pos, 4.0)
    }

    override fun canTakeItemForPickAll(stack: ItemStack, slot: Slot): Boolean {
        return slot.index != OUTPUT_SLOT_ID && super.canTakeItemForPickAll(stack, slot)
    }

    private fun addPlayerInventory(playerInventory: Inventory, x: Int, y: Int) {
        for (i in 0..2) {
            for (j in 0..8) {
                addSlot(Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(playerInventory: Inventory, x: Int, y: Int) {
        for (i in 0..8) {
            addSlot(Slot(playerInventory, i, x + i * 18, y))
        }
    }

    private fun createData(): ContainerData {
        if (level.isClientSide) return SimpleContainerData(DATA_SLOT_COUNT)
        return object : ContainerData {
            override fun get(index: Int): Int = when (index) {
                REQUIRE_LEVEL_DATA_SLOT -> requireLevel
                HAS_RECIPE_DATA_SLOT -> if (hasRecipe) 1 else 0
                else -> 0
            }

            override fun set(index: Int, value: Int) {
                when (index) {
                    REQUIRE_LEVEL_DATA_SLOT -> requireLevel = value
                    HAS_RECIPE_DATA_SLOT -> hasRecipe = value > 0
                }
            }

            override fun getCount(): Int = DATA_SLOT_COUNT
        }
    }

    companion object {
        const val DATA_SLOT_COUNT = 2
        const val REQUIRE_LEVEL_DATA_SLOT = 0
        const val HAS_RECIPE_DATA_SLOT = 1
        const val INPUT_SLOT_ID = 0
        const val OUTPUT_SLOT_ID = 1
        const val INVENTORY_FIRST_SLOT_ID = 2
        const val HOTBAR_FIRST_SLOT_ID = 29
        const val HOTBAR_LAST_SLOT_ID = 37
    }
}