package com.reasure.crystal_odyssey.inventory.handler

import com.reasure.crystal_odyssey.inventory.menu.BaseRecipeContainerMenu
import net.neoforged.neoforge.items.ItemStackHandler

class ContainerMenuItemStackHandler(val menu: BaseRecipeContainerMenu, size: Int) : ItemStackHandler(size) {
    override fun onContentsChanged(slot: Int) {
        menu.onSlotChanged()
    }
}