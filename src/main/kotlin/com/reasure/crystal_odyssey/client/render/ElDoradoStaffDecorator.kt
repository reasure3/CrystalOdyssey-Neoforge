package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.vertex.PoseStack
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.client.IItemDecorator

object ElDoradoStaffDecorator : IItemDecorator {
    private val startTimeMills = System.currentTimeMillis()

    override fun render(guiGraphics: GuiGraphics, font: Font, stack: ItemStack, xOffset: Int, yOffset: Int): Boolean {
        val overlayItems = stack[ModDataComponents.OVERLAY_ITEMS] ?: return false
        val currentItem = getCurrentOverlayItem(overlayItems.items, overlayItems.rotationTimeMills)
        if (currentItem.isEmpty) return false
        renderOverlayItem(guiGraphics, currentItem, xOffset, yOffset)
        return false
    }

    private fun getCurrentOverlayItem(overlayItems: Ingredient, rotationTimeMills: Int): ItemStack {
        if (overlayItems.hasNoItems()) return ItemStack.EMPTY
        val items = overlayItems.getItems()
        if (items.size == 1) return items[0]
        val elapsedTimeSec = ((System.currentTimeMillis() - startTimeMills) / rotationTimeMills).toInt()
        val index = elapsedTimeSec % items.size
        return items[index]
    }

    private fun renderOverlayItem(guiGraphics: GuiGraphics, overlayItem: ItemStack, xOffset: Int, yOffset: Int) {
        val poseStack: PoseStack = guiGraphics.pose()
        poseStack.pushPose()
        poseStack.scale(0.4f, 0.4f, 0.4f)
        guiGraphics.renderFakeItem(overlayItem, (xOffset * 2.5).toInt(), (yOffset * 2.5).toInt())
        poseStack.popPose()
    }
}