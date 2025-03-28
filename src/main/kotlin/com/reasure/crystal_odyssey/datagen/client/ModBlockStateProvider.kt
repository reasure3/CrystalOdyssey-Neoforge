package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseBlockStateProvider
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModBlockStateProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ModBaseBlockStateProvider(output, exFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.GLOWSTONE_GEM_BLOCK)
        simpleBlockWithItem(ModBlocks.RUBY_BLOCK)
        simpleBlockWithItem(ModBlocks.SAPPHIRE_BLOCK)
        simpleBlockWithItem(ModBlocks.RUBY_ORE)
        simpleBlockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE)
        simpleBlockWithItem(ModBlocks.SAPPHIRE_ORE)
        simpleBlockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
        attachFaceBlock(
            ModBlocks.GLOWSTONE_GEM_LANTERN,
            "glowstone_gem_lantern",
            "glowstone_gem_lantern_hanging",
            "glowstone_gem_lantern_wall"
        )
        simpleBlockStateWithItem(ModBlocks.MANA_INJECTOR)
        simpleBlockStateWithItem(ModBlocks.LIGHT_ORB_BLOCK)
        horizontalBlockWithExistingModel(ModBlocks.MANA_ANVIL)
    }
}
