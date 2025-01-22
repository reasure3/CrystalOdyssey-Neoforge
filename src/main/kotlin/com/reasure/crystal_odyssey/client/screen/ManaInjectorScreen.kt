package com.reasure.crystal_odyssey.client.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaInjectorMenu
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class ManaInjectorScreen(menu: ManaInjectorMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<ManaInjectorMenu>(menu, playerInventory, title) {
    private val guiTexture: ResourceLocation = CrystalOdyssey.modLoc("textures/gui/container/mana_injector.png")
    private val expOrbTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_injector/exp_orb.png")
    private val expOrbDisableTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_injector/exp_orb_disable.png")

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, guiTexture)

        guiGraphics.blit(guiTexture, leftPos, topPos, 0, 0, imageWidth, imageHeight)
        renderRequireLevel(guiGraphics)
    }

    private fun renderRequireLevel(guiGraphics: GuiGraphics) {
        if (menu.hasRecipe) {
            val enable = menu.isSatisfyRequireLevel()
            renderExpOrb(guiGraphics, enable)
            if (menu.requireLevel > 0) {
                renderRequireLevelText(guiGraphics, menu.requireLevel.toString(), enable)
            }
        }
    }

    private fun renderRequireLevelText(guiGraphics: GuiGraphics, requireLevel: String, enable: Boolean) {
        val textColor = if (enable) 0xC8FF8F else 0x8C605D
        val textBorderColor = if (enable) 0x2D2102 else 0x47352F
        val x = leftPos + 84
        val y = topPos + 57
        guiGraphics.drawString(font, requireLevel, x + 1, y, textBorderColor, false)
        guiGraphics.drawString(font, requireLevel, x - 1, y, textBorderColor, false)
        guiGraphics.drawString(font, requireLevel, x, y + 1, textBorderColor, false)
        guiGraphics.drawString(font, requireLevel, x, y - 1, textBorderColor, false)
        guiGraphics.drawString(font, requireLevel, x, y, textColor, false)
    }

    private fun renderExpOrb(guiGraphics: GuiGraphics, enable: Boolean) {
        val expOrb = if (enable) expOrbTexture else expOrbDisableTexture
        guiGraphics.blit(expOrb, leftPos + 73, topPos + 56, 0f, 0f, 9, 9, 9, 9)
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        renderTooltip(guiGraphics, mouseX, mouseY)
    }
}