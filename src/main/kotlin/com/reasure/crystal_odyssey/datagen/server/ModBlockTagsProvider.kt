package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModBlockTagsProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    exFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, registries, CrystalOdyssey.ID, exFileHelper) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.GLOWSTONE_GEM_BLOCK)
            .add(ModBlocks.RUBY_BLOCK)
            .add(ModBlocks.SAPPHIRE_BLOCK)
            .add(ModBlocks.GLOWSTONE_GEM_LANTERN)
            .add(ModBlocks.MANA_INJECTOR)

        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.RUBY_BLOCK)
            .add(ModBlocks.SAPPHIRE_BLOCK)
            .add(ModBlocks.MANA_INJECTOR)

        tag(Tags.Blocks.STORAGE_BLOCKS)
            .add(ModBlocks.GLOWSTONE_GEM_BLOCK)
            .add(ModBlocks.RUBY_BLOCK)
            .add(ModBlocks.SAPPHIRE_BLOCK)
    }
}