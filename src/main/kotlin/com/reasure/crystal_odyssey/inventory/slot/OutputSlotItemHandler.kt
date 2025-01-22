package com.reasure.crystal_odyssey.inventory.slot

import com.reasure.crystal_odyssey.inventory.menu.BaseRecipeContainerMenu
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.SlotItemHandler

open class OutputSlotItemHandler(
    private val menu: BaseRecipeContainerMenu,
    itemHandler: IItemHandler,
    index: Int,
    xPosition: Int,
    yPosition: Int
) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {

    override fun mayPlace(stack: ItemStack): Boolean = false

    override fun isFake(): Boolean = true

    override fun onTake(player: Player, stack: ItemStack) {
        menu.onCraft(player, stack)
        super.onTake(player, stack)
        menu.setRecipe()
    }
}