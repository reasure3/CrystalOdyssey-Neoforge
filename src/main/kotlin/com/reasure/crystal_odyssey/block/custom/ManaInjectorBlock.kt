package com.reasure.crystal_odyssey.block.custom

import com.reasure.crystal_odyssey.inventory.menu.custom.ManaInjectorMenu
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class ManaInjectorBlock(properties: Properties) : Block(properties) {
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
            ManaInjectorMenu(containerId, playerInventory, pos)
        }, TranslateHelper.Container.MANA_INJECTOR.translateComponent())
    }

    override fun getCollisionShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = CORE_SHAPE

    companion object {
        val CORE_SHAPE: VoxelShape = box(4.0, 4.0, 4.0, 12.0, 12.0, 12.0)
    }
}