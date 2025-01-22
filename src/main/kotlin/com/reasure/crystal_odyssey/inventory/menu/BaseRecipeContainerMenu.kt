package com.reasure.crystal_odyssey.inventory.menu

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

abstract class BaseRecipeContainerMenu(
    menuType: MenuType<*>,
    containerId: Int,
    playerInventory: Inventory,
    private val pos: BlockPos
) : AbstractContainerMenu(menuType, containerId) {
    protected val player: Player = playerInventory.player
    protected val level: Level = playerInventory.player.level()

    abstract fun getBlock(): Block

    abstract fun onCraft(player: Player, stack: ItemStack)

    abstract fun setRecipe()

    abstract fun setRecipeClient()

    fun onSlotChanged() {
        if (level.isClientSide) {
            setRecipeClient()
        } else {
            setRecipe()
        }
    }

    override fun stillValid(player: Player): Boolean {
        if (level.isClientSide) return true
        if (!level.getBlockState(pos).`is`(getBlock())) return false
        return player.canInteractWithBlock(pos, 4.0)
    }

    protected fun addPlayerInventory(playerInventory: Inventory, x: Int, y: Int) {
        for (i in 0..2) {
            for (j in 0..8) {
                addSlot(Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18))
            }
        }
    }

    protected fun addPlayerHotbar(playerInventory: Inventory, x: Int, y: Int) {
        for (i in 0..8) {
            addSlot(Slot(playerInventory, i, x + i * 18, y))
        }
    }
}