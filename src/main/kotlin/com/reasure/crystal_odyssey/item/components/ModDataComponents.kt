package com.reasure.crystal_odyssey.item.components

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.item.components.custom.OverlayItems
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.ExtraCodecs
import net.neoforged.neoforge.fluids.SimpleFluidContent
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModDataComponents {
    val DATA_COMPONENTS: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, CrystalOdyssey.ID)

    val LANTERN_LEVEL: Supplier<DataComponentType<Int>> =
        DATA_COMPONENTS.registerComponentType("lantern_level") { builder ->
            builder.persistent(ExtraCodecs.intRange(0, 2)).networkSynchronized(ByteBufCodecs.VAR_INT)
        }

    val FIND_BLOCKS: Supplier<DataComponentType<FindBlocks>> =
        DATA_COMPONENTS.registerComponentType("find_blocks") { builder ->
            builder.persistent(FindBlocks.CODEC).networkSynchronized(FindBlocks.STEAM_CODEC)
        }

    val OVERLAY_ITEMS: Supplier<DataComponentType<OverlayItems>> =
        DATA_COMPONENTS.registerComponentType("overlay_items") { builder ->
            builder.persistent(OverlayItems.CODEC).networkSynchronized(OverlayItems.STREAM_CODEC)
        }

    val FLUID: Supplier<DataComponentType<SimpleFluidContent>> =
        DATA_COMPONENTS.registerComponentType("fluid") { builder ->
            builder.persistent(SimpleFluidContent.CODEC).networkSynchronized(SimpleFluidContent.STREAM_CODEC)
        }
}