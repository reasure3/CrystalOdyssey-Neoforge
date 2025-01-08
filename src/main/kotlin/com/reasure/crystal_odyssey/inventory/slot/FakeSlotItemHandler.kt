package com.reasure.crystal_odyssey.inventory.slot

import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.SlotItemHandler

open class FakeSlotItemHandler(itemHandler: IItemHandler, index: Int, xPosition: Int, yPosition: Int) :
    SlotItemHandler(itemHandler, index, xPosition, yPosition) {

    override fun mayPlace(stack: ItemStack): Boolean = false

    override fun isFake(): Boolean = true
}