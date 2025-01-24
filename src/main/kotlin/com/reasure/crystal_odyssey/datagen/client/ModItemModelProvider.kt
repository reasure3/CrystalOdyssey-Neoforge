package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.datagen.client.base.ModBaseItemModelProvider
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Items
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ModBaseItemModelProvider(output, exFileHelper) {

    override fun registerModels() {
        copyTextureItem(ModItems.ENCHANTED_DIAMOND, Items.DIAMOND)
        copyTextureItem(ModItems.ENCHANTED_EMERALD, Items.EMERALD)
        copyTextureItem(ModItems.ENCHANTED_AMETHYST_SHARD, Items.AMETHYST_SHARD)
        basicItem(ModItems.GLOWSTONE_GEM)
        copyTextureItem(ModItems.ENCHANTED_GLOWSTONE_GEM, ModItems.GLOWSTONE_GEM)
        basicItem(ModItems.RUBY)
        copyTextureItem(ModItems.ENCHANTED_RUBY, ModItems.RUBY)
        basicItem(ModItems.SAPPHIRE)
        copyTextureItem(ModItems.ENCHANTED_SAPPHIRE, ModItems.SAPPHIRE)
        capacityBucketItem(ModItems.SAPPHIRE_BUCKET, Fluids.WATER)
        basicItem(ModItems.INFINITE_SAPPHIRE_BUCKET)
        capacityBucketItem(ModItems.RUBY_BUCKET, Fluids.LAVA)
        basicItem(ModItems.INFINITE_RUBY_BUCKET)
    }
}