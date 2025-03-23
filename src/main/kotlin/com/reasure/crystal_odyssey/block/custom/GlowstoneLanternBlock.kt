package com.reasure.crystal_odyssey.block.custom

import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class GlowstoneLanternBlock(properties: Properties) : Block(properties),
    SimpleWaterloggedBlock {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(LANTERN_LEVEL, 0)
                .setValue(FACING, Direction.DOWN)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(LANTERN_LEVEL, FACING, WATERLOGGED)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return when (state.getValue(FACING) ?: Direction.DOWN) {
            Direction.UP -> UP_AABB
            Direction.DOWN -> DOWN_AABB
            Direction.NORTH -> NORTH_AABB
            Direction.SOUTH -> SOUTH_AABB
            Direction.WEST -> WEST_AABB
            Direction.EAST -> EAST_AABB
        }
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val lanternLevel = context.itemInHand[ModDataComponents.LANTERN_LEVEL] ?: 0
        val face = if (context.clickedFace.axis == Direction.Axis.Y) context.clickedFace.opposite
        else context.clickedFace
        val fluidState = context.level.getFluidState(context.clickedPos)

        val state = defaultBlockState()
            .setValue(LANTERN_LEVEL, lanternLevel)
            .setValue(FACING, face)
            .setValue(WATERLOGGED, fluidState.`is`(Fluids.WATER))

        if (state.canSurvive(context.level, context.clickedPos)) {
            return state
        }

        for (direction in context.nearestLookingDirections) {
            val lookDir = if (direction.axis == Direction.Axis.Y) direction.opposite else direction
            val lookState = state.setValue(FACING, lookDir)
            if (lookState.canSurvive(context.level, context.clickedPos)) {
                return lookState
            }
        }

        return null
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val direction = getConnectedDirection(state).opposite
        return canSupportCenter(level, pos.relative(direction), direction.opposite)
    }

    override fun updateShape(
        state: BlockState,
        facing: Direction,
        facingState: BlockState,
        level: LevelAccessor,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        if (getConnectedDirection(state).opposite == facing && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    @Suppress("DEPRECATION")
    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun isPathfindable(state: BlockState, pathComputationType: PathComputationType): Boolean = false

    override fun getCloneItemStack(
        state: BlockState,
        target: HitResult,
        level: LevelReader,
        pos: BlockPos,
        player: Player
    ): ItemStack {
        return super.getCloneItemStack(state, target, level, pos, player).apply {
            this[ModDataComponents.LANTERN_LEVEL] = state.getValue(LANTERN_LEVEL)
        }
    }

    // Returns which side of the block the lantern is on, based on the block it is on.
    private fun getConnectedDirection(state: BlockState): Direction {
        val facing = state.getValue(FACING)
        if (facing.axis == Direction.Axis.Y) {
            return facing.opposite
        }
        return facing
    }

    companion object {
        val LANTERN_LEVEL: IntegerProperty = ModBlockStates.LANTERN_LEVEL
        val FACING: DirectionProperty = BlockStateProperties.FACING
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        private val DOWN_AABB: VoxelShape = Shapes.or(
            box(5.0, 0.0, 5.0, 11.0, 7.0, 11.0),
            box(6.0, 7.0, 6.0, 10.0, 9.0, 10.0)
        )

        private val UP_AABB: VoxelShape = Shapes.or(
            box(5.0, 1.0, 5.0, 11.0, 8.0, 11.0),
            box(6.0, 8.0, 6.0, 10.0, 10.0, 10.0)
        )

        private val NORTH_AABB: VoxelShape = Shapes.or(
            box(5.0, 0.0, 6.0, 11.0, 7.0, 12.0),
            box(6.0, 7.0, 7.0, 10.0, 9.0, 11.0),
            box(7.0, 11.0, 7.0, 9.0, 13.0, 14.0),
            box(6.0, 9.0, 14.0, 10.0, 15.0, 16.0)
        )

        private val SOUTH_AABB: VoxelShape = Shapes.or(
            box(5.0, 0.0, 4.0, 11.0, 7.0, 10.0),
            box(6.0, 7.0, 5.0, 10.0, 9.0, 9.0),
            box(7.0, 11.0, 2.0, 9.0, 13.0, 9.0),
            box(6.0, 9.0, 0.0, 10.0, 15.0, 2.0)
        )

        private val WEST_AABB: VoxelShape = Shapes.or(
            box(6.0, 0.0, 5.0, 12.0, 7.0, 11.0),
            box(7.0, 7.0, 6.0, 11.0, 9.0, 10.0),
            box(7.0, 11.0, 7.0, 14.0, 13.0, 9.0),
            box(14.0, 9.0, 6.0, 16.0, 15.0, 10.0)
        )

        private val EAST_AABB: VoxelShape = Shapes.or(
            box(4.0, 0.0, 5.0, 10.0, 7.0, 11.0),
            box(5.0, 7.0, 6.0, 9.0, 9.0, 10.0),
            box(2.0, 11.0, 7.0, 9.0, 13.0, 9.0),
            box(0.0, 9.0, 6.0, 2.0, 15.0, 10.0)
        )
    }
}