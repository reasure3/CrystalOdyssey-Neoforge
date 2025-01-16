package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseBlockStateProvider
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.LanternBlock
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ModBaseBlockStateProvider(output, exFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.GLOWSTONE_GEM_BLOCK)
        simpleBlockWithItem(ModBlocks.RUBY_BLOCK)
        simpleBlockWithItem(ModBlocks.SAPPHIRE_BLOCK)
        getVariantBuilder(ModBlocks.GLOWSTONE_GEM_LANTERN)
            .partialState().with(LanternBlock.HANGING, false).setModel("glowstone_gem_lantern")
            .partialState().with(LanternBlock.HANGING, true).setModel("glowstone_gem_lantern_hanging")
        simpleBlockStateWithItem(ModBlocks.MANA_INJECTOR)
        simpleBlockStateWithItem(ModBlocks.LIGHT_ORB_BLOCK)
    }
}
