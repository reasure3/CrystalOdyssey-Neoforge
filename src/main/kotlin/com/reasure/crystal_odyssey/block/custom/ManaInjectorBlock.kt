package com.reasure.crystal_odyssey.block.custom

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaInjectorMenu
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

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
        }, Component.translatable("container.${CrystalOdyssey.ID}.mana_injector"))
    }
}