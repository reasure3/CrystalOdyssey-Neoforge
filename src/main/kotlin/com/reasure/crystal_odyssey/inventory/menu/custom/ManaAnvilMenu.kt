package com.reasure.crystal_odyssey.inventory.menu.custom

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.client.screen.ManaAnvilScreen
import com.reasure.crystal_odyssey.inventory.handler.InputItemStackHandler
import com.reasure.crystal_odyssey.inventory.handler.OutputItemStackHandler
import com.reasure.crystal_odyssey.inventory.menu.BaseRecipeContainerMenu
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.inventory.slot.InputSlotItemHandler
import com.reasure.crystal_odyssey.inventory.slot.OutputSlotItemHandler
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.ManaAnvilRecipe
import com.reasure.crystal_odyssey.recipe.custom.input.TupleRecipeInput
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.items.ItemStackHandler

class ManaAnvilMenu(containerId: Int, playerInventory: Inventory, pos: BlockPos) :
    BaseRecipeContainerMenu(ModMenuTypes.MANA_ANVIL_MENU, containerId, playerInventory, pos) {
    private val _recipeList: MutableList<RecipeHolder<ManaAnvilRecipe>> = mutableListOf()
    val recipeList: List<RecipeHolder<ManaAnvilRecipe>>
        get() = _recipeList

    private val inputHandler: ItemStackHandler = InputItemStackHandler(this, 2)
    private val outputHandler: ItemStackHandler = OutputItemStackHandler()
    private val data: ContainerData = SimpleContainerData(DATA_SLOT_COUNT)

    private var lastInputGem: ItemStack = ItemStack.EMPTY
    private var lastInputMaterial: ItemStack = ItemStack.EMPTY

    var currentRecipeIndex: Int
        get() = data[CUR_RECIPE_INDEX_DATA_SLOT_ID]
        private set(value) {
            data[CUR_RECIPE_INDEX_DATA_SLOT_ID] = value
        }

    var onUpdateRecipeClient: () -> Unit = {}

    constructor(containerId: Int, playerInventory: Inventory, extraData: FriendlyByteBuf) : this(
        containerId,
        playerInventory,
        extraData.readBlockPos()
    )

    init {
        addSlot(InputSlotItemHandler(inputHandler, 0, 19, 17))
        addSlot(InputSlotItemHandler(inputHandler, 1, 19, 52))
        addSlot(OutputSlotItemHandler(this, outputHandler, 0, 138, 35))
        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)
        addDataSlots(data)
    }

    override fun getBlock(): Block = ModBlocks.MANA_ANVIL

    override fun onCraft(player: Player, stack: ItemStack) {
        inputHandler.extractItem(0, 1, false)
        inputHandler.extractItem(1, 1, false)
    }

    override fun setRecipe() {
        if (!isSameInputWithLast()) {
            resetRecipe()
            currentRecipeIndex = -1
            outputHandler.setStackInSlot(0, ItemStack.EMPTY)
        }
        setRecipeResult(currentRecipeIndex)
        lastInputGem = slots[INPUT_GEM_SLOT_ID].item.copy()
        lastInputMaterial = slots[INPUT_MATERIAL_SLOT_ID].item.copy()
    }

    override fun setRecipeClient() {
        if (!isSameInputWithLast()) {
            resetRecipe()
            onUpdateRecipeClient()
        }
        lastInputGem = slots[INPUT_GEM_SLOT_ID].item.copy()
        lastInputMaterial = slots[INPUT_MATERIAL_SLOT_ID].item.copy()
    }

    private fun resetRecipe() {
        _recipeList.clear()
        if (isNotEmptyInputs()) {
            _recipeList.addAll(currentRecipes())
        }
    }

    private fun isNotEmptyInputs(): Boolean {
        return slots[INPUT_GEM_SLOT_ID].hasItem() && slots[INPUT_MATERIAL_SLOT_ID].hasItem()
    }

    private fun isSameInputWithLast(): Boolean {
        return ItemStack.isSameItemSameComponents(slots[INPUT_GEM_SLOT_ID].item, lastInputGem)
                && ItemStack.isSameItemSameComponents(slots[INPUT_MATERIAL_SLOT_ID].item, lastInputMaterial)
    }

    private fun currentRecipeInput() =
        TupleRecipeInput(slots[INPUT_GEM_SLOT_ID].item, slots[INPUT_MATERIAL_SLOT_ID].item)

    private fun currentRecipes(): List<RecipeHolder<ManaAnvilRecipe>> {
        return level.recipeManager.getRecipesFor(ModRecipeTypes.MANA_ANVIL_RECIPE_TYPE, currentRecipeInput(), level)
            .sortedByDescending { it.value.getPriority(level.registryAccess()) }
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        val sourceSlot: Slot = slots[index]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY
        val sourceStack: ItemStack = sourceSlot.item
        val copyOfSourceStack: ItemStack = sourceStack.copy()

        when (index) {
            INPUT_GEM_SLOT_ID, INPUT_MATERIAL_SLOT_ID -> {
                if (!moveItemStackTo(sourceStack, INVENTORY_FIRST_SLOT_ID, HOTBAR_LAST_SLOT_ID + 1, false)) {
                    return ItemStack.EMPTY
                }
            }

            OUTPUT_SLOT_ID -> {
                if (!moveItemStackTo(sourceStack, INVENTORY_FIRST_SLOT_ID, HOTBAR_LAST_SLOT_ID + 1, true)) {
                    return ItemStack.EMPTY
                }
            }

            // source slot is inventory or hotbar
            else -> {
                val recipes = level.recipeManager.getAllRecipesFor(ModRecipeTypes.MANA_ANVIL_RECIPE_TYPE)
                if (recipes.any { it.value.getGem().test(copyOfSourceStack) }) {
                    if (!moveItemStackTo(sourceStack, INPUT_GEM_SLOT_ID, INPUT_GEM_SLOT_ID + 1, false)
                        && !moveInInventory(index, sourceStack)
                    ) return ItemStack.EMPTY
                } else if (recipes.any {
                        it.value.getMaterial().test(copyOfSourceStack)
                    }) {
                    if (!moveItemStackTo(sourceStack, INPUT_MATERIAL_SLOT_ID, INPUT_MATERIAL_SLOT_ID + 1, false)
                        && !moveInInventory(index, sourceStack)
                    ) return ItemStack.EMPTY
                } else if (!moveInInventory(index, sourceStack)) {
                    return ItemStack.EMPTY
                }
            }
        }

        return finalizeQuickMoveStack(sourceStack, sourceSlot, copyOfSourceStack, index, OUTPUT_SLOT_ID, player)
    }

    private fun moveInInventory(index: Int, sourceStack: ItemStack): Boolean {
        val targetSlotRange = if (index < HOTBAR_FIRST_SLOT_ID) { // sourceSlot is inventory
            HOTBAR_FIRST_SLOT_ID..HOTBAR_LAST_SLOT_ID // to hotbar
        } else {
            INVENTORY_FIRST_SLOT_ID..<HOTBAR_FIRST_SLOT_ID // to inventory
        }
        return moveItemStackTo(sourceStack, targetSlotRange.first, targetSlotRange.last + 1, false)
    }

    private fun clearContainer(player: Player) {
        if (!player.isAlive || (player is ServerPlayer && player.hasDisconnected())) {
            player.drop(inputHandler.getStackInSlot(0).copy(), false)
            player.drop(inputHandler.getStackInSlot(1).copy(), false)
        } else {
            val inventory: Inventory = player.inventory
            if (player is ServerPlayer) {
                inventory.placeItemBackInInventory(inputHandler.getStackInSlot(0))
                inventory.placeItemBackInInventory(inputHandler.getStackInSlot(1))
            }
        }
    }

    override fun clickMenuButton(player: Player, id: Int): Boolean {
        if (id >= ManaAnvilScreen.RECIPE_BTN_START_ID) {
            setRecipeResult(id - ManaAnvilScreen.RECIPE_BTN_START_ID)
        }
        return true
    }

    private fun setRecipeResult(recipeId: Int) {
        if (recipeId in recipeList.indices) {
            currentRecipeIndex = recipeId
            val output: ItemStack = recipeList[recipeId].value.assemble(currentRecipeInput(), level.registryAccess())
            outputHandler.setStackInSlot(0, output)
        }
    }

    fun getInputGem(): ItemStack {
        return slots[INPUT_GEM_SLOT_ID].item
    }

    fun getInputMaterial(): ItemStack {
        return slots[INPUT_MATERIAL_SLOT_ID].item
    }

    fun previewResult(index: Int): ItemStack {
        return recipeList[index].value.getResultItem(level.registryAccess())
    }

    override fun removed(player: Player) {
        super.removed(player)
        clearContainer(player)
    }

    override fun canTakeItemForPickAll(stack: ItemStack, slot: Slot): Boolean {
        return slot.index != OUTPUT_SLOT_ID && super.canTakeItemForPickAll(stack, slot)
    }

    companion object {
        const val DATA_SLOT_COUNT = 2
        const val CUR_RECIPE_INDEX_DATA_SLOT_ID = 0
        const val INPUT_GEM_SLOT_ID = 0
        const val INPUT_MATERIAL_SLOT_ID = 1
        const val OUTPUT_SLOT_ID = 2
        const val INVENTORY_FIRST_SLOT_ID = 3
        const val HOTBAR_FIRST_SLOT_ID = 30
        const val HOTBAR_LAST_SLOT_ID = 38
    }
}