package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.util.DatagenTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
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
            .addTag(DatagenTags.Blocks.STORAGE_BLOCKS_RUBY)
            .addTag(DatagenTags.Blocks.STORAGE_BLOCKS_SAPPHIRE)

        tag(DatagenTags.Blocks.STORAGE_BLOCKS_QUARTZ)
            .add(Blocks.QUARTZ_BLOCK)

        tag(DatagenTags.Blocks.STORAGE_BLOCKS_AMETHYST)
            .add(Blocks.AMETHYST_BLOCK)

        tag(DatagenTags.Blocks.STORAGE_BLOCKS_RUBY)
            .add(ModBlocks.RUBY_BLOCK)

        tag(DatagenTags.Blocks.STORAGE_BLOCKS_SAPPHIRE)
            .add(ModBlocks.SAPPHIRE_BLOCK)

        tag(Tags.Blocks.ORES)
            .addTag(DatagenTags.Blocks.ORES_RUBY)
            .addTag(DatagenTags.Blocks.ORES_SAPPHIRE)

        tag(DatagenTags.Blocks.ORES_RUBY)
            .add(ModBlocks.RUBY_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)

        tag(DatagenTags.Blocks.ORES_SAPPHIRE)
            .add(ModBlocks.SAPPHIRE_ORE)
            .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)

        tag(Tags.Blocks.ORES_IN_GROUND_STONE)
            .add(ModBlocks.SAPPHIRE_ORE)
            .add(ModBlocks.RUBY_ORE)

        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)
            .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
            .add(ModBlocks.DEEPSLATE_RUBY_ORE)

        tag(DatagenTags.Blocks.EL_DORADO_COAL)
            .addTag(Tags.Blocks.ORES_COAL)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_COAL)

        tag(DatagenTags.Blocks.EL_DORADO_IRON)
            .addTag(Tags.Blocks.ORES_IRON)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_IRON)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_IRON)

        tag(DatagenTags.Blocks.EL_DORADO_COPPER)
            .addTag(Tags.Blocks.ORES_COPPER)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_COPPER)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_COPPER)

        tag(DatagenTags.Blocks.EL_DORADO_GOLD)
            .addTag(Tags.Blocks.ORES_GOLD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_RAW_GOLD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_GOLD)

        tag(DatagenTags.Blocks.EL_DORADO_REDSTONE)
            .addTag(Tags.Blocks.ORES_REDSTONE)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_REDSTONE)

        tag(DatagenTags.Blocks.EL_DORADO_EMERALD)
            .addTag(Tags.Blocks.ORES_EMERALD)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_EMERALD)

        tag(DatagenTags.Blocks.EL_DORADO_LAPIS_LAZULI)
            .addTag(Tags.Blocks.ORES_LAPIS)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_LAPIS)

        tag(DatagenTags.Blocks.EL_DORADO_DIAMOND)
            .addTag(Tags.Blocks.ORES_DIAMOND)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND)

        tag(DatagenTags.Blocks.EL_DORADO_QUARTZ)
            .addTag(Tags.Blocks.ORES_QUARTZ)
            .add(Blocks.QUARTZ_BLOCK)

        tag(DatagenTags.Blocks.EL_DORADO_NETHERITE)
            .addTag(Tags.Blocks.ORES_NETHERITE_SCRAP)
            .addTag(Tags.Blocks.STORAGE_BLOCKS_NETHERITE)

        tag(DatagenTags.Blocks.EL_DORADO_AMETHYST)
            .add(Blocks.BUDDING_AMETHYST)
            .add(Blocks.SMALL_AMETHYST_BUD)
            .add(Blocks.MEDIUM_AMETHYST_BUD)
            .add(Blocks.LARGE_AMETHYST_BUD)
            .add(Blocks.AMETHYST_CLUSTER)
            .add(Blocks.AMETHYST_BLOCK)

        tag(DatagenTags.Blocks.EL_DORADO_SAPPHIRE)
            .addTag(DatagenTags.Blocks.ORES_SAPPHIRE)
            .addTag(DatagenTags.Blocks.STORAGE_BLOCKS_SAPPHIRE)

        tag(DatagenTags.Blocks.EL_DORADO_RUBY)
            .addTag(DatagenTags.Blocks.ORES_RUBY)
            .addTag(DatagenTags.Blocks.STORAGE_BLOCKS_RUBY)
    }
}