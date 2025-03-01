package com.reasure.crystal_odyssey.block.custom

import com.reasure.crystal_odyssey.inventory.menu.custom.ManaAnvilMenu
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class ManaAnvilBlock(properties: Properties) : Block(properties) {
    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        return defaultBlockState().setValue(FACING, context.horizontalDirection.clockWise)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return if (state.getValue(FACING).axis == Direction.Axis.X) X_AXIS_AABB else Z_AXIS_AABB
    }

    override fun rotate(state: BlockState, rot: Rotation): BlockState {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun useWithoutItem(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hitResult: BlockHitResult
    ): InteractionResult {
        if (level.isClientSide) return InteractionResult.SUCCESS
        player.openMenu(state.getMenuProvider(level, pos)!!, pos)
        return InteractionResult.CONSUME
    }

    override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider {
        return SimpleMenuProvider({ containerId, playerInventory, _ ->
            ManaAnvilMenu(containerId, playerInventory, pos)
        }, TranslateHelper.Container.MANA_ANVIL.translateComponent())
    }

    companion object {
        private val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING
        private val BASE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 4.0, 14.0)
        private val X_LEG1: VoxelShape = box(3.0, 4.0, 4.0, 13.0, 5.0, 12.0)
        private val X_LEG2: VoxelShape = box(4.0, 5.0, 6.0, 12.0, 10.0, 10.0)
        private val X_TOP: VoxelShape = box(0.0, 10.0, 3.0, 16.0, 16.0, 13.0)
        private val Z_LEG1: VoxelShape = box(4.0, 4.0, 3.0, 12.0, 5.0, 13.0)
        private val Z_LEG2: VoxelShape = box(6.0, 5.0, 4.0, 10.0, 10.0, 12.0)
        private val Z_TOP: VoxelShape = box(3.0, 10.0, 0.0, 13.0, 16.0, 16.0)
        private val X_AXIS_AABB: VoxelShape = Shapes.or(BASE, X_LEG1, X_LEG2, X_TOP)
        private val Z_AXIS_AABB: VoxelShape = Shapes.or(BASE, Z_LEG1, Z_LEG2, Z_TOP)
    }
}