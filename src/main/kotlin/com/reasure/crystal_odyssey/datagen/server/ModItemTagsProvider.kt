package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.datagen.util.DatagenTags
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModItemTagsProvider(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagLookup<Block>>,
    exFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, registries, blockTags, CrystalOdyssey.ID, exFileHelper) {
    override fun addTags(provider: HolderLookup.Provider) {
        tag(DatagenTags.Items.SLOT_BELT)
            .add(ModItems.GLOWSTONE_GEM_LANTERN)

        tag(ModTags.Items.LIGHT_STAFF_REPAIRABLE)
            .add(ModItems.GLOWSTONE_GEM)
            .add(ModItems.ENCHANTED_GLOWSTONE_GEM)

        tag(ModTags.Items.EL_DORADO_STAFF_REPAIRABLE)
            .add(ModItems.GLOWSTONE_GEM)
            .add(ModItems.ENCHANTED_GLOWSTONE_GEM)
            .add(ModItems.GLOWSTONE_GEM_CORE)

        tag(ItemTags.DURABILITY_ENCHANTABLE)
            .add(ModItems.LIGHT_STAFF)
            .add(ModItems.EL_DORADO_STAFF_ACTIVE)

        tag(DatagenTags.Items.GEMS_SAPPHIRE)
            .add(ModItems.SAPPHIRE)

        tag(DatagenTags.Items.GEMS_RUBY)
            .add(ModItems.RUBY)

        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS)
        copy(DatagenTags.Blocks.STORAGE_BLOCKS_RUBY, DatagenTags.Items.STORAGE_BLOCKS_RUBY)
        copy(DatagenTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, DatagenTags.Items.STORAGE_BLOCKS_SAPPHIRE)
        copy(Tags.Blocks.ORES, Tags.Items.ORES)
        copy(DatagenTags.Blocks.ORES_RUBY, DatagenTags.Items.ORES_RUBY)
        copy(DatagenTags.Blocks.ORES_SAPPHIRE, DatagenTags.Items.ORES_SAPPHIRE)
    }
}