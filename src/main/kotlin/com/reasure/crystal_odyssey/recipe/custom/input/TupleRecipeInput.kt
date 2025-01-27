package com.reasure.crystal_odyssey.recipe.custom.input

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeInput

data class TupleRecipeInput(val itemFirst: ItemStack, val itemSecond: ItemStack) : RecipeInput {
    override fun getItem(index: Int): ItemStack {
        return when (index) {
            0 -> itemFirst
            1 -> itemSecond
            else -> throw IllegalArgumentException("No item for index $index")
        }
    }

    override fun size(): Int = 2
}