package com.reasure.crystal_odyssey.datagen.util.worldgen.ore

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest

object ModOreConfigs {
    val ORE_SAPPHIRE = createKey("ore_sapphire")
    val ORE_RUBY = createKey("ore_ruby")
    val ORE_RUBY_BURIED = createKey("ore_ruby_buried")

    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables: RuleTest = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables: RuleTest = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)

        val sapphireOreConfig = listOf(
            OreConfiguration.target(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.defaultBlockState()),
            OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.defaultBlockState())
        )

        val rubyOreConfig = listOf(
            OreConfiguration.target(stoneReplaceables, ModBlocks.RUBY_ORE.defaultBlockState()),
            OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.defaultBlockState())
        )

        FeatureUtils.register(context, ORE_SAPPHIRE, Feature.ORE, OreConfiguration(sapphireOreConfig, 8, 0.0f))

        FeatureUtils.register(context, ORE_RUBY, Feature.ORE, OreConfiguration(rubyOreConfig, 5, 0.5f))
        FeatureUtils.register(context, ORE_RUBY_BURIED, Feature.ORE, OreConfiguration(rubyOreConfig, 6, 1.0f))
    }

    private fun createKey(name: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CrystalOdyssey.modLoc(name))
    }
}