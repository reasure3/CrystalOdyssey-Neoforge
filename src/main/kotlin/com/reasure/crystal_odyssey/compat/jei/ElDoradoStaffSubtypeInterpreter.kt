package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class ElDoradoStaffSubtypeInterpreter : ISubtypeInterpreter<ItemStack> {
    override fun getSubtypeData(ingredient: ItemStack, context: UidContext): Any? {
        val findBlocks = ingredient[ModDataComponents.FIND_BLOCKS] ?: FindBlocks.EMPTY
        return if (findBlocks.isNotEmpty()) {
            findBlocks.blockGroupName
        } else Component.empty()
    }

    @Deprecated("Deprecated in Java")
    override fun getLegacyStringSubtypeInfo(ingredient: ItemStack, context: UidContext): String {
        val findBlocks = ingredient[ModDataComponents.FIND_BLOCKS] ?: FindBlocks.EMPTY
        return if (findBlocks.isNotEmpty()) {
            findBlocks.blockGroupName.toString()
        } else ""
    }
}