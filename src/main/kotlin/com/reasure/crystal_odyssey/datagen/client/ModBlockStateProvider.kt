package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseBlockStateProvider
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ModBaseBlockStateProvider(output, exFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.GLOWSTONE_GEM_BLOCK)
    }
}
