package com.reasure.crystal_odyssey.compat.waila

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.nbt.CompoundTag
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
        if (accessor.serverData.contains(LANTERN_LEVEL_KEY)) {
            tooltip.add(
                TranslateHelper.Tooltip.LAMP_LEVEL.translateComponent(
                    accessor.serverData.getInt(LANTERN_LEVEL_KEY)
                )
            )
        }
    }

    override fun appendServerData(data: CompoundTag, accessor: BlockAccessor) {
        data.putInt(LANTERN_LEVEL_KEY, accessor.blockState.getOptionalValue(ModBlockStates.LANTERN_LEVEL).orElse(0))
    }

    override fun getUid(): ResourceLocation = UID

    private const val LANTERN_LEVEL_KEY = "lantern_level"
}
