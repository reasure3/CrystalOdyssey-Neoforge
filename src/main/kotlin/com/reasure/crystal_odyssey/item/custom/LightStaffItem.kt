package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.gameevent.GameEvent

class LightStaffItem(val block: Block, properties: Properties) : Item(properties) {
    override fun getEnchantmentValue(stack: ItemStack): Int = 25

    override fun isValidRepairItem(stack: ItemStack, repairCandidate: ItemStack): Boolean {
        return repairCandidate.`is`(ModTags.Items.LIGHT_STAFF_REPAIRABLE)
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        val stack = context.itemInHand
        if (stack.damageValue == stack.maxDamage) return super.useOn(context)

        val placeContext = BlockPlaceContext(context)
        val level = placeContext.level
        val pos = placeContext.clickedPos
        val oldState = level.getBlockState(pos)

        if (!oldState.isAir && !oldState.`is`(Blocks.WATER)) return InteractionResult.FAIL
        val state = block.getStateForPlacement(placeContext) ?: return InteractionResult.FAIL

        val player = placeContext.player
        level.setBlock(pos, state, 11)
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos)

        if (player is ServerPlayer && level is ServerLevel) {
            stack.hurtAndBreak(1, level, player) { stack.count = 1 }
        }

        return InteractionResult.sidedSuccess(level.isClientSide)
    }
}