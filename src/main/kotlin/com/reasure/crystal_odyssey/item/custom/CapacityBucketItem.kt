package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.neoforged.neoforge.fluids.FluidType
import java.text.DecimalFormat

class CapacityBucketItem(properties: Properties, private val capacity: Int, fluidDescKey: String = "") :
    CapabilityBucketItem(properties, fluidDescKey) {
    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val amount = stack.get(ModDataComponents.FLUID)?.amount ?: 0
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.literal("${amount.format()} / ${capacity.format()} mB"))
        } else {
            tooltipComponents.add(Component.literal("${amount / FluidType.BUCKET_VOLUME} / ${capacity / FluidType.BUCKET_VOLUME} B"))
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)
        tooltipComponents.add(
            TranslateHelper.Tooltip.Shift.MB_CAPACITY.translateComponent()
                .withStyle(ChatFormatting.DARK_GRAY)
        )
    }

    companion object {
        private val formatter = DecimalFormat("#,###")

        private fun Int.format(): String {
            return formatter.format(this)
        }
    }
}