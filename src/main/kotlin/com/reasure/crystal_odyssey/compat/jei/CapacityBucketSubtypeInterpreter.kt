package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.world.item.ItemStack

class CapacityBucketSubtypeInterpreter(private val capacity: Int) : ISubtypeInterpreter<ItemStack> {
    override fun getSubtypeData(ingredient: ItemStack, context: UidContext): Any {
        val amount = ingredient[ModDataComponents.FLUID]?.amount ?: 0
        return if (amount >= capacity) "full" else "not_full"
    }

    @Deprecated("Deprecated in Java")
    override fun getLegacyStringSubtypeInfo(ingredient: ItemStack, context: UidContext): String {
        val amount = ingredient[ModDataComponents.FLUID]?.amount ?: 0
        return if (amount >= capacity) "full" else "not_full"
    }
}