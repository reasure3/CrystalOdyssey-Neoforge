package com.reasure.crystal_odyssey.item.custom

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class FoilItem(properties: Properties) : Item(properties) {
    override fun isFoil(stack: ItemStack): Boolean = true
}