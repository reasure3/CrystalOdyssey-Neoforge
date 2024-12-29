package com.reasure.crystal_odyssey.datagen

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

        tag(Tags.Blocks.STORAGE_BLOCKS)
            .add(ModBlocks.GLOWSTONE_GEM_BLOCK)
    }
}