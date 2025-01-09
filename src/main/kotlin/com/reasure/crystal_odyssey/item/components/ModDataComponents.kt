package com.reasure.crystal_odyssey.item.components

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.ExtraCodecs
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModDataComponents {
    val DATA_COMPONENTS: DeferredRegister.DataComponents =
        DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, CrystalOdyssey.ID)

    val LANTERN_LEVEL: DataComponentType<Int> by DATA_COMPONENTS.registerComponentType("lantern_level") { builder ->
        builder.persistent(ExtraCodecs.intRange(0, 2)).networkSynchronized(ByteBufCodecs.VAR_INT)
    }
}