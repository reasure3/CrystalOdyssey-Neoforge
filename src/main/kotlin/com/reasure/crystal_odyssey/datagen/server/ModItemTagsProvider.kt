package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
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
        tag(ModTags.Items.SLOT_BELT)
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

        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS)
        copy(ModTags.Blocks.STORAGE_BLOCKS_RUBY, ModTags.Items.STORAGE_BLOCKS_RUBY)
        copy(ModTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, ModTags.Items.STORAGE_BLOCKS_SAPPHIRE)
        copy(Tags.Blocks.ORES, Tags.Items.ORES)
        copy(ModTags.Blocks.ORES_RUBY, ModTags.Items.ORES_RUBY)
        copy(ModTags.Blocks.ORES_SAPPHIRE, ModTags.Items.ORES_SAPPHIRE)
    }
}