package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.client.util.BlockFinder
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.util.ModTags
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class ElDoradoStaffItem(properties: Properties) : Item(properties) {
    override fun getEnchantmentValue(stack: ItemStack): Int = 20

    override fun isValidRepairItem(stack: ItemStack, repairCandidate: ItemStack): Boolean {
        return stack.`is`(ModTags.Items.EL_DORADO_STAFF_REPAIRABLE)
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        player.cooldowns.addCooldown(this, 20)
        if (level.isClientSide) {
            BlockFinder.startFind(level, player.onPos, player.getItemInHand(usedHand))
        }
        if (!level.isClientSide && level is ServerLevel) {
            val staff = player.getItemInHand(usedHand).copy()
            player.getItemInHand(usedHand).hurtAndBreak(1, level, player) {
                val emptyStaff = ItemStack(ModItems.EL_DORADO_STAFF)
                if (!staff.isComponentsPatchEmpty) {
                    emptyStaff.applyComponents(staff.componentsPatch)
                    emptyStaff.remove(ModDataComponents.FIND_BLOCKS)
                    emptyStaff.remove(ModDataComponents.OVERLAY_ITEMS)
                    emptyStaff.remove(DataComponents.DAMAGE)
                }
                player.setItemInHand(usedHand, emptyStaff)
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            val findBlocks = stack[ModDataComponents.FIND_BLOCKS] ?: return
            if (findBlocks.isNotEmpty()) {
                findBlocks.blocks.forEach {
                    tooltipComponents.add(it.value().name)
                }
            }
        } else {
            tooltipComponents.add(
                TranslateHelper.Tooltip.Shift.BLOCK_LIST.translateComponent()
                    .withStyle(ChatFormatting.DARK_GRAY)
            )
        }
    }
}