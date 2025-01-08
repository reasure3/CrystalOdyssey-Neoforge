package com.reasure.crystal_odyssey.inventory.handler

import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.ItemStackHandler

open class OutputItemStackHandler : ItemStackHandler() {
    override fun isItemValid(slot: Int, stack: ItemStack): Boolean = false
}