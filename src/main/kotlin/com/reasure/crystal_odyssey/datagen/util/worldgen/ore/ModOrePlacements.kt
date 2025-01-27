package com.reasure.crystal_odyssey.datagen.util.worldgen.ore

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*

object ModOrePlacements {
    val ORE_SAPPHIRE = createKey("ore_sapphire")
    val ORE_RUBY_EXTRA = createKey("ore_ruby_extra")
    val ORE_RUBY = createKey("ore_ruby")

    fun bootstrap(context: BootstrapContext<PlacedFeature>) {
        val oreConfigs: HolderGetter<ConfiguredFeature<*, *>> = context.lookup(Registries.CONFIGURED_FEATURE)

        PlacementUtils.register(
            context, ORE_SAPPHIRE, oreConfigs.getOrThrow(ModOreConfigs.ORE_SAPPHIRE),
            commonOrePlacement(
                10,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(92))
            )
        )

        PlacementUtils.register(
            context, ORE_RUBY_EXTRA, oreConfigs.getOrThrow(ModOreConfigs.ORE_RUBY),
            commonOrePlacement(
                3,
                HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(0))
            )
        )

        PlacementUtils.register(
            context, ORE_RUBY, oreConfigs.getOrThrow(ModOreConfigs.ORE_RUBY_BURIED),
            commonOrePlacement(
                10,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(-48))
            )
        )
    }

    private fun createKey(name: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, CrystalOdyssey.modLoc(name))
    }

    private fun orePlacement(
        countPlacement: PlacementModifier,
        heightRange: PlacementModifier
    ): List<PlacementModifier> {
        return listOf(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome())
    }

    private fun commonOrePlacement(count: Int, heightRange: PlacementModifier): List<PlacementModifier> {
        return orePlacement(CountPlacement.of(count), heightRange)
    }
}