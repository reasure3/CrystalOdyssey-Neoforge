package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.datagen.util.worldgen.ore.ModOreBiomeModifiers
import com.reasure.crystal_odyssey.datagen.util.worldgen.ore.ModOreConfigs
import com.reasure.crystal_odyssey.datagen.util.worldgen.ore.ModOrePlacements
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.concurrent.CompletableFuture

class ModDatapackEntriesProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    DatapackBuiltinEntriesProvider(output, registries, BUILDER, setOf(CrystalOdyssey.ID)) {

    companion object {
        val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModOreConfigs::bootstrap)
            .add(Registries.PLACED_FEATURE, ModOrePlacements::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModOreBiomeModifiers::bootstrap)
    }
}