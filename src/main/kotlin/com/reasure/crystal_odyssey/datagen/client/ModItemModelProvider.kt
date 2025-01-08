package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.datagen.client.base.ModBaseItemModelProvider
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ModBaseItemModelProvider(output, exFileHelper) {

    override fun registerModels() {
        basicItem(ModItems.GLOWSTONE_GEM)
        copyTextureItem(ModItems.ENCHANTED_GLOWSTONE_GEM, ModItems.GLOWSTONE_GEM)
    }
}