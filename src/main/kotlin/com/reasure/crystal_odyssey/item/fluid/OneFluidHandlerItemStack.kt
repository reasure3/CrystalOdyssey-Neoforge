package com.reasure.crystal_odyssey.item.fluid

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.capability.templates.FluidHandlerItemStack

open class OneFluidHandlerItemStack(container: ItemStack, protected val acceptFluid: Fluid, capacity: Int) :
    FluidHandlerItemStack(ModDataComponents.FLUID, container, capacity) {
    override fun canFillFluidType(fluid: FluidStack): Boolean {
        return fluid.`is`(acceptFluid)
    }

    override fun canDrainFluidType(fluid: FluidStack): Boolean {
        return fluid.`is`(acceptFluid)
    }

    override fun isFluidValid(tank: Int, stack: FluidStack): Boolean {
        return stack.`is`(acceptFluid)
    }
}