package com.reasure.crystal_odyssey.compat.jei

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.world.item.ItemStack

class ItemNameInterpreter : ISubtypeInterpreter<ItemStack> {
    override fun getSubtypeData(ingredient: ItemStack, context: UidContext): Any? {
        return ingredient.hoverName
    }

    @Deprecated("Deprecated in Java", ReplaceWith("ingredient.hoverName.toString()"))
    override fun getLegacyStringSubtypeInfo(ingredient: ItemStack, context: UidContext): String {
        return ingredient.hoverName.toString()
    }
}