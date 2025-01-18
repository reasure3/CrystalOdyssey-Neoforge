package com.reasure.crystal_odyssey.datagen.util.worldgen.ore

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.NeoForgeRegistries

object ModOreBiomeModifiers {
    private val ADD_ORE_RUBY = createKey("add_ore_ruby")
    private val ADD_ORE_SAPPHIRE = createKey("add_ore_sapphire")

    fun bootstrap(context: BootstrapContext<BiomeModifier>) {
        val orePlacements: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)
        val biomes: HolderGetter<Biome> = context.lookup(Registries.BIOME)

        val isOverworld: HolderSet.Named<Biome> = biomes.getOrThrow(BiomeTags.IS_OVERWORLD)

        register(
            context, ADD_ORE_RUBY, isOverworld,
            listOf(
                orePlacements.getOrThrow(ModOrePlacements.ORE_RUBY_EXTRA),
                orePlacements.getOrThrow(ModOrePlacements.ORE_RUBY)
            )
        )
        register(
            context, ADD_ORE_SAPPHIRE, isOverworld,
            listOf(orePlacements.getOrThrow(ModOrePlacements.ORE_SAPPHIRE)),
        )
    }

    private fun createKey(name: String): ResourceKey<BiomeModifier> {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CrystalOdyssey.modLoc(name))
    }

    private fun register(
        context: BootstrapContext<BiomeModifier>,
        key: ResourceKey<BiomeModifier>,
        biomes: HolderSet<Biome>,
        placements: List<Holder<PlacedFeature>>
    ) {
        context.register(
            key,
            BiomeModifiers.AddFeaturesBiomeModifier(
                biomes,
                HolderSet.direct(placements),
                GenerationStep.Decoration.UNDERGROUND_ORES
            )
        )
    }
}