package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.Direction
import net.minecraft.data.PackOutput
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
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

    fun attachFaceBlock(block: Block, floorModel: String, ceilModel: String, wallModel: String) {
        getVariantBuilder(block)
            .partialState().with(BlockStateProperties.FACING, Direction.DOWN).setModel(floorModel)
            .partialState().with(BlockStateProperties.FACING, Direction.UP).setModel(ceilModel)
            .setHorizontalModel(wallModel)
    }

    fun modelFile(path: String): ModelFile {
        return ModelFile.UncheckedModelFile(CrystalOdyssey.modLoc("block/$path"))
    }

    private fun VariantBlockStateBuilder.PartialBlockstate.setModel(path: String): VariantBlockStateBuilder =
        setModels(ConfiguredModel(modelFile(path)))

    private fun VariantBlockStateBuilder.setHorizontalModel(path: String): VariantBlockStateBuilder {
        for (direction: Direction in Direction.entries) {
            if (direction == Direction.DOWN || direction == Direction.UP) continue
            partialState().with(BlockStateProperties.FACING, direction).setModels(
                ConfiguredModel.builder()
                    .modelFile(modelFile(path))
                    .rotationY((direction.toYRot().toInt() + 180) % 360)
                    .build()[0]
            )
        }
        return this
    }
}