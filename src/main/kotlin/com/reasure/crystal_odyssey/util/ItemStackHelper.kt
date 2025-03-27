package com.reasure.crystal_odyssey.util

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.SimpleFluidContent
import java.util.function.Supplier

object ItemStackHelper {
    fun <T> Item.with(componentType: Supplier<DataComponentType<T>>, value: T) = ItemStack(this).apply {
        set(componentType.get(), value)
    }

    fun <T> ItemStack.with(componentType: Supplier<DataComponentType<T>>, value: T) = apply {
        set(componentType.get(), value)
    }

    fun Item.withBucket(fluid: Fluid, bucketAmount: Int = 1) = with(
        ModDataComponents.FLUID, bucketContent(fluid, bucketAmount)
    )

    fun ItemStack.hasBucket(bucketAmount: Int = 1): Boolean =
        (this[ModDataComponents.FLUID]?.amount ?: 0) >= bucketAmount * FluidType.BUCKET_VOLUME

    fun bucketContent(fluid: Fluid, bucketAmount: Int = 1): SimpleFluidContent =
        SimpleFluidContent.copyOf(FluidStack(fluid, bucketAmount * FluidType.BUCKET_VOLUME))
}