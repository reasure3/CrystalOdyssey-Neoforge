package com.reasure.crystal_odyssey.item.fluid

import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.capability.IFluidHandler

class InfiniteOneFluidHandlerItemStack(container: ItemStack, acceptFluid: Fluid) :
    OneFluidHandlerItemStack(container, acceptFluid, Int.MAX_VALUE) {

    override fun fill(resource: FluidStack, doFill: IFluidHandler.FluidAction): Int {
        if (container.count != 1 || resource.isEmpty || !canFillFluidType(resource)) {
            return 0
        }

        return if (FluidStack.isSameFluid(getFluidInTank(0), resource)) resource.amount else 0
    }

    override fun drain(maxDrain: Int, action: IFluidHandler.FluidAction): FluidStack {
        if (container.count != 1 || maxDrain <= 0) {
            return FluidStack.EMPTY
        }

        val contained = fluid
        if (!canDrainFluidType(contained)) {
            return FluidStack.EMPTY
        }

        return contained.copyWithAmount(maxDrain)
    }

    override fun getFluid(): FluidStack {
        return FluidStack(acceptFluid, Int.MAX_VALUE)
    }
}