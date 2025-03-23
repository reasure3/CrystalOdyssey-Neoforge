package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.world.item.ItemStack

class LanternSubtypeInterpreter : ISubtypeInterpreter<ItemStack> {
    override fun getSubtypeData(ingredient: ItemStack, context: UidContext): Any? {
        return ingredient[ModDataComponents.LANTERN_LEVEL]
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "ingredient.get(ModDataComponents.LANTERN_LEVEL)?.toString() ?: \"\"",
            "com.reasure.crystal_odyssey.item.components.ModDataComponents"
        )
    )
    override fun getLegacyStringSubtypeInfo(ingredient: ItemStack, context: UidContext): String {
        return "${ingredient[ModDataComponents.LANTERN_LEVEL]}"
    }
}