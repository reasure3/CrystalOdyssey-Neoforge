package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.client.model.generators.BlockStateProvider
import net.neoforged.neoforge.client.model.generators.ConfiguredModel
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder
import net.neoforged.neoforge.common.data.ExistingFileHelper

abstract class ModBaseBlockStateProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    BlockStateProvider(output, CrystalOdyssey.ID, exFileHelper) {

    fun simpleBlockWithItem(block: Block) = simpleBlockWithItem(block, cubeAll(block))

    fun simpleBlockStateWithItem(block: Block) =
        simpleBlockWithItem(block, ModelFile.UncheckedModelFile(blockTexture(block)))

    fun horizontalBlockWithExistingModel(block: Block) {
        val model: ModelFile = ModelFile.UncheckedModelFile(blockTexture(block))
        horizontalBlock(block, model)
        simpleBlockItem(block, model)
    }

    fun VariantBlockStateBuilder.PartialBlockstate.setModel(path: String): VariantBlockStateBuilder.PartialBlockstate =
        addModels(ConfiguredModel(ModelFile.UncheckedModelFile(CrystalOdyssey.modLoc("block/$path"))))
}