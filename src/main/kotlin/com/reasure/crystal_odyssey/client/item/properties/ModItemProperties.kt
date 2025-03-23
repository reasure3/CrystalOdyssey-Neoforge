package com.reasure.crystal_odyssey.client.item.properties

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ItemStackHelper.hasBucket
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.client.renderer.item.ItemProperties

object ModItemProperties {
    private val HAS_FLUID_PROPERTY = ClampedItemPropertyFunction { stack, _, _, _ -> if (stack.hasBucket()) 1f else 0f }

    fun register() {
        val hasFluidLoc = CrystalOdyssey.modLoc("has_fluid")
        ItemProperties.register(ModItems.SAPPHIRE_BUCKET, hasFluidLoc, HAS_FLUID_PROPERTY)
        ItemProperties.register(ModItems.RUBY_BUCKET, hasFluidLoc, HAS_FLUID_PROPERTY)
    }
}