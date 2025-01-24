package com.reasure.crystal_odyssey.client.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaAnvilMenu
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.player.Inventory

class ManaAnvilScreen(menu: ManaAnvilMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<ManaAnvilMenu>(menu, playerInventory, title) {
    private val guiTexture: ResourceLocation = CrystalOdyssey.modLoc("textures/gui/container/mana_anvil.png")
    private val prevTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/prev.png")
    private val prevDisabledTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/prev_disabled.png")
    private val prevHighlightedTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/prev_highlighted.png")
    private val nextTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/next.png")
    private val nextDisabledTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/next_disabled.png")
    private val nextHighlightedTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/next_highlighted.png")
    private val recipeSlotTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/recipe_slot.png")
    private val recipeSlotSelectedTexture: ResourceLocation =
        CrystalOdyssey.modLoc("textures/gui/sprites/container/mana_anvil/recipe_slot_selected.png")

    private val inputGemBackgroundTexture: ResourceLocation =
        ResourceLocation.withDefaultNamespace("textures/item/empty_slot_amethyst_shard.png")
    private val inputMaterialBackgroundTexture: ResourceLocation =
        ResourceLocation.withDefaultNamespace("textures/item/empty_slot_ingot.png")

    private var recipeCount = 0
    private var maxPage = 0
    private var page = 0

    override fun init() {
        super.init()
        menu.onUpdateRecipeClient = this::onRecipeUpdate
    }

    private fun onRecipeUpdate() {
        recipeCount = menu.recipeList.size
        maxPage = (recipeCount + 2) / 3 - 1
        page = 0
    }

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, guiTexture)

        guiGraphics.blit(guiTexture, leftPos, topPos, 0, 0, imageWidth, imageHeight)
        renderInputSlotBackground(guiGraphics)
        if (maxPage > 0) {
            renderPrevButton(guiGraphics, mouseX, mouseY)
            renderNextButton(guiGraphics, mouseX, mouseY)
        }
        if (recipeCount > 0) {
            renderRecipes(guiGraphics)
        }
    }

    private fun renderInputSlotBackground(guiGraphics: GuiGraphics) {
        if (menu.getInputGem().isEmpty) {
            guiGraphics.blit(inputGemBackgroundTexture, leftPos + 19, topPos + 17, 0f, 0f, 16, 16, 16, 16)
        }
        if (menu.getInputMaterial().isEmpty) {
            guiGraphics.blit(inputMaterialBackgroundTexture, leftPos + 19, topPos + 52, 0f, 0f, 16, 16, 16, 16)
        }
    }

    private fun renderPrevButton(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        if (canClickPrevButton()) {
            val texture = if (isMouseInPrevButton(mouseX, mouseY)) prevHighlightedTexture else prevTexture
            guiGraphics.blit(texture, leftPos + 97, topPos + 58, 0f, 0f, 8, 13, 8, 13)
        } else {
            guiGraphics.blit(prevDisabledTexture, leftPos + 97, topPos + 58, 0f, 0f, 8, 13, 8, 13)
        }
    }

    private fun renderNextButton(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        if (canClickNextButton()) {
            val texture = if (isMouseInNextButton(mouseX, mouseY)) nextHighlightedTexture else nextTexture
            guiGraphics.blit(texture, leftPos + 109, topPos + 58, 0f, 0f, 8, 13, 8, 13)
        } else {
            guiGraphics.blit(nextDisabledTexture, leftPos + 109, topPos + 58, 0f, 0f, 8, 13, 8, 13)
        }
    }

    private fun renderRecipes(guiGraphics: GuiGraphics) {
        for (i in 0..2) {
            val recipeIndex = page * 3 + i
            if (recipeIndex >= recipeCount) break
            val item = menu.recipeList[recipeIndex].value.result
            val texture = if (recipeIndex == menu.currentRecipeIndex) recipeSlotSelectedTexture else recipeSlotTexture
            val x = leftPos + 55 + i * 22
            guiGraphics.blit(texture, x, topPos + 34, 0f, 0f, 18, 18, 18, 18)
            guiGraphics.renderFakeItem(item, x + 1, topPos + 35)
        }
    }

    override fun renderTooltip(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        if (menu.carried.isEmpty) {
            for (i in 0..2) {
                val recipeIndex = page * 3 + i
                if (recipeIndex >= recipeCount) break
                if (isMouseInRecipeButton(mouseX, mouseY, i)) {
                    val item = menu.recipeList[recipeIndex].value.result
                    guiGraphics.renderTooltip(font, item, mouseX, mouseY)
                    return
                }
            }
            super.renderTooltip(guiGraphics, mouseX, mouseY)
        }
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        val x = mouseX.toInt()
        val y = mouseY.toInt()
        if (canClickPrevButton() && isMouseInPrevButton(x, y)) {
            page--
            return buttonClicked(PREV_BTN_ID)
        } else if (canClickNextButton() && isMouseInNextButton(x, y)) {
            page++
            return buttonClicked(NEXT_BTN_ID)
        } else {
            for (i in 0..2) {
                val recipeIndex = page * 3 + i
                if (recipeIndex >= recipeCount) break
                if (isMouseInRecipeButton(x, y, i)) {
                    return buttonClicked(RECIPE_BTN_START_ID + recipeIndex)
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button)
    }

    private fun buttonClicked(buttonId: Int): Boolean {
        // Invoke ManaAnvilMenu#clickMenuButton on server side
        getMinecraft().gameMode?.handleInventoryButtonClick(menu.containerId, buttonId)
        getMinecraft().soundManager.play(
            SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0f)
        )
        return true
    }

    private fun isMouseInRecipeButton(mouseX: Int, mouseY: Int, posId: Int): Boolean {
        return (mouseY - topPos) in 34..51 && (mouseX - leftPos) in (55 + posId * 22)..(55 + posId * 22 + 18)
    }

    private fun isMouseInPrevButton(mouseX: Int, mouseY: Int): Boolean {
        return (mouseX - leftPos) in 97..104 && (mouseY - topPos) in 58..70
    }

    private fun isMouseInNextButton(mouseX: Int, mouseY: Int): Boolean {
        return (mouseX - leftPos) in 109..116 && (mouseY - topPos) in 58..70
    }

    private fun canClickPrevButton(): Boolean {
        return maxPage > 0 && page > 0
    }

    private fun canClickNextButton(): Boolean {
        return maxPage > 0 && page < maxPage
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        renderTooltip(guiGraphics, mouseX, mouseY)
    }

    companion object {
        const val PREV_BTN_ID = 0
        const val NEXT_BTN_ID = 1
        const val RECIPE_BTN_START_ID = 2
    }
}