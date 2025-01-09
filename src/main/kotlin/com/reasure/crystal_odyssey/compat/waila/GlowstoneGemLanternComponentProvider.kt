package com.reasure.crystal_odyssey.compat.waila

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.state.ModBlockStates
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import snownee.jade.api.BlockAccessor
import snownee.jade.api.IBlockComponentProvider
import snownee.jade.api.IServerDataProvider
import snownee.jade.api.ITooltip
import snownee.jade.api.config.IPluginConfig

object GlowstoneGemLanternComponentProvider : IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    private val UID: ResourceLocation =
        ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "glowstone_gem_lantern")

    override fun appendTooltip(tooltip: ITooltip, accessor: BlockAccessor, config: IPluginConfig) {
        if (accessor.serverData.contains("lantern_level")) {
            tooltip.add(
                Component.translatable(
                    "item.${CrystalOdyssey.ID}.tooltip.lamp_level",
                    accessor.serverData.getInt("lantern_level") + 1
                )
            )
        }
    }

    override fun appendServerData(data: CompoundTag, accessor: BlockAccessor) {
        data.putInt("lantern_level", accessor.blockState.getOptionalValue(ModBlockStates.LANTERN_LEVEL).orElse(0))
    }

    override fun getUid(): ResourceLocation = UID
}
