package com.reasure.crystal_odyssey.client.item.properties

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.client.renderer.item.ItemProperties
import net.neoforged.neoforge.fluids.FluidType

object ModItemProperties {
    private val HAS_FLUID_PROPERTY = ClampedItemPropertyFunction { stack, _, _, _ ->
        if ((stack.get(ModDataComponents.FLUID)?.amount ?: 0) < FluidType.BUCKET_VOLUME) 0f else 1f
    }

    fun register() {
        val hasFluidLoc = CrystalOdyssey.modLoc("has_fluid")
        ItemProperties.register(ModItems.SAPPHIRE_BUCKET, hasFluidLoc, HAS_FLUID_PROPERTY)
        ItemProperties.register(ModItems.RUBY_BUCKET, hasFluidLoc, HAS_FLUID_PROPERTY)
    }
}