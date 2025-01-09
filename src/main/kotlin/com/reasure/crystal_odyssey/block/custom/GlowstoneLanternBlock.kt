package com.reasure.crystal_odyssey.block.custom

import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LanternBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty

class GlowstoneLanternBlock(properties: Properties) : LanternBlock(properties) {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(WATERLOGGED, false)
                .setValue(LANTERN_LEVEL, 0)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(LANTERN_LEVEL)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val state: BlockState = super.getStateForPlacement(context) ?: return null
        return state.setValue(LANTERN_LEVEL, context.itemInHand.getOrDefault(ModDataComponents.LANTERN_LEVEL, 0))
    }

    companion object {
        val LANTERN_LEVEL: IntegerProperty = ModBlockStates.LANTERN_LEVEL
    }
}