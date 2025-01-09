package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.server.base.ModBaseBlockLootSubProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.concurrent.CompletableFuture

class ModBlockLootProvider(registries: HolderLookup.Provider) : ModBaseBlockLootSubProvider(registries) {
    override fun generate() {
        dropSelf(ModBlocks.GLOWSTONE_GEM_BLOCK)
        addLantern(ModBlocks.GLOWSTONE_GEM_LANTERN)
        dropSelf(ModBlocks.MANA_INJECTOR)
    }

    override fun getKnownBlocks(): MutableIterable<Block> =
        ModBlocks.BLOCKS.entries.map { it.value() }.toMutableList()

    companion object {
        fun of(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) = LootTableProvider(
            output, setOf(),
            listOf(LootTableProvider.SubProviderEntry(::ModBlockLootProvider, LootContextParamSets.BLOCK)),
            registries
        )
    }
}