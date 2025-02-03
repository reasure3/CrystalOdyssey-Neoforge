package com.reasure.crystal_odyssey.event

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.fluid.InfiniteOneFluidHandlerItemStack
import com.reasure.crystal_odyssey.item.fluid.OneFluidHandlerItemStack
import net.minecraft.world.level.material.Fluids
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import net.neoforged.neoforge.fluids.FluidType

@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD)
object ModSetupEvents {
    @SubscribeEvent
    private fun registerCapabilities(event: RegisterCapabilitiesEvent) {
        event.registerItem(
            Capabilities.FluidHandler.ITEM,
            { stack, _ -> OneFluidHandlerItemStack(stack, Fluids.WATER, 30 * FluidType.BUCKET_VOLUME) },
            ModItems.SAPPHIRE_BUCKET
        )

        event.registerItem(
            Capabilities.FluidHandler.ITEM,
            { stack, _ -> InfiniteOneFluidHandlerItemStack(stack, Fluids.WATER) },
            ModItems.INFINITE_SAPPHIRE_BUCKET
        )

        event.registerItem(
            Capabilities.FluidHandler.ITEM,
            { stack, _ -> OneFluidHandlerItemStack(stack, Fluids.LAVA, 30 * FluidType.BUCKET_VOLUME) },
            ModItems.RUBY_BUCKET
        )

        event.registerItem(
            Capabilities.FluidHandler.ITEM,
            { stack, _ -> InfiniteOneFluidHandlerItemStack(stack, Fluids.LAVA) },
            ModItems.INFINITE_RUBY_BUCKET
        )
    }
}