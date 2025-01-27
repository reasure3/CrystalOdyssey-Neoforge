package com.reasure.crystal_odyssey.inventory.handler

import com.reasure.crystal_odyssey.inventory.menu.BaseRecipeContainerMenu
import net.neoforged.neoforge.items.ItemStackHandler

open class InputItemStackHandler(val menu: BaseRecipeContainerMenu, size: Int = 1) : ItemStackHandler(size) {
    override fun onContentsChanged(slot: Int) {
        menu.onSlotChanged()
    }
}