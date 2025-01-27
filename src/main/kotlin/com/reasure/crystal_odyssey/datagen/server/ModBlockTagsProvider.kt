package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.util.ModTags
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
            .add(ModBlocks.RUBY_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)
            .add(ModBlocks.SAPPHIRE_ORE)
            .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
            .add(ModBlocks.GLOWSTONE_GEM_LANTERN)
            .add(ModBlocks.MANA_INJECTOR)
            .add(ModBlocks.MANA_ANVIL)

        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.RUBY_BLOCK)
            .add(ModBlocks.SAPPHIRE_BLOCK)
            .add(ModBlocks.RUBY_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)
            .add(ModBlocks.SAPPHIRE_ORE)
            .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
            .add(ModBlocks.MANA_INJECTOR)
            .add(ModBlocks.MANA_ANVIL)

        tag(Tags.Blocks.STORAGE_BLOCKS)
            .addTag(ModTags.Blocks.STORAGE_BLOCKS_RUBY)
            .addTag(ModTags.Blocks.STORAGE_BLOCKS_SAPPHIRE)

        tag(ModTags.Blocks.STORAGE_BLOCKS_RUBY)
            .add(ModBlocks.RUBY_BLOCK)

        tag(ModTags.Blocks.STORAGE_BLOCKS_SAPPHIRE)
            .add(ModBlocks.SAPPHIRE_BLOCK)

        tag(Tags.Blocks.ORES)
            .addTag(ModTags.Blocks.ORES_RUBY)
            .addTag(ModTags.Blocks.ORES_SAPPHIRE)

        tag(ModTags.Blocks.ORES_RUBY)
            .add(ModBlocks.RUBY_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)

        tag(ModTags.Blocks.ORES_SAPPHIRE)
            .add(ModBlocks.SAPPHIRE_ORE)
            .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)

        tag(ModTags.Blocks.EL_DORADO_COAL)
            .addTag(Tags.Blocks.ORES_COAL)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_COAL)

        tag(ModTags.Blocks.EL_DORADO_COPPER)
            .addTag(Tags.Blocks.ORES_COPPER)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_COPPER)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_COPPER)

        tag(ModTags.Blocks.EL_DORADO_IRON)
            .addTag(Tags.Blocks.ORES_IRON)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_IRON)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_IRON)

        tag(ModTags.Blocks.EL_DORADO_GOLD)
            .addTag(Tags.Blocks.ORES_GOLD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_GOLD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_GOLD)

        tag(ModTags.Blocks.EL_DORADO_DIAMOND)
            .addTag(Tags.Blocks.ORES_DIAMOND)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND)

        tag(ModTags.Blocks.EL_DORADO_NETHERITE)
            .addTag(Tags.Blocks.ORES_NETHERITE_SCRAP)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE)

        tag(ModTags.Blocks.EL_DORADO_LAPIS_LAZULI)
            .addTag(Tags.Blocks.ORES_LAPIS)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_LAPIS)

        tag(ModTags.Blocks.EL_DORADO_REDSTONE)
            .addTag(Tags.Blocks.ORES_REDSTONE)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_REDSTONE)

        tag(ModTags.Blocks.EL_DORADO_QUARTZ)
            .addTag(Tags.Blocks.ORES_QUARTZ)

        tag(ModTags.Blocks.EL_DORADO_EMERALD)
            .addTag(Tags.Blocks.ORES_EMERALD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_EMERALD)
    }
}