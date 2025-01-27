package com.reasure.crystal_odyssey.inventory.slot

import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.SlotItemHandler
import kotlin.math.min

open class InputSlotItemHandler(itemHandler: IItemHandler, index: Int, xPosition: Int, yPosition: Int) :
    SlotItemHandler(itemHandler, index, xPosition, yPosition) {
    /**
     * fix [Neoforge Issue#1896](https://github.com/neoforged/NeoForge/issues/1896)
     */
    override fun getMaxStackSize(stack: ItemStack): Int {
        return min(itemHandler.getSlotLimit(index), stack.maxStackSize)
    }
}