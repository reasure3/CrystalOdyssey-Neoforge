package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.gui.ingredient.IRecipeSlotsView
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.IRecipeCategory
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack


class ManaInjectingRecipeCategory(helper: IGuiHelper) : IRecipeCategory<ManaInjectingRecipe> {
    private val background: IDrawable = helper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "textures/gui/jei/mana_injecting.png"),
        0, 0, 78, 36
    ).setTextureSize(78, 36).build()

    private val icon: IDrawable = helper.createDrawableItemStack(ItemStack(ModBlocks.MANA_INJECTOR))

    override fun getRecipeType(): RecipeType<ManaInjectingRecipe> = MANA_INJECTING_RECIPE_TYPE

    override fun getTitle(): Component = Component.translatable("jei.category.${CrystalOdyssey.ID}.mana_injecting")

    override fun getIcon(): IDrawable = icon

    override fun setRecipe(builder: IRecipeLayoutBuilder, recipe: ManaInjectingRecipe, focuses: IFocusGroup) {
        builder.addInputSlot(1, 5).addIngredients(recipe.ingredient)
        builder.addOutputSlot(57, 5).addItemStack(recipe.result)
    }

    override fun getWidth(): Int = 78
    override fun getHeight(): Int = 36

    override fun draw(
        recipe: ManaInjectingRecipe,
        recipeSlotsView: IRecipeSlotsView,
        guiGraphics: GuiGraphics,
        mouseX: Double,
        mouseY: Double
    ) {
        background.draw(guiGraphics)
        val font: Font = Minecraft.getInstance().font
        val requireLevel = recipe.requireLevel.toString()
        guiGraphics.drawString(font, requireLevel, 10, 28, 0x2D2102, false)
        guiGraphics.drawString(font, requireLevel, 12, 28, 0x2D2102, false)
        guiGraphics.drawString(font, requireLevel, 11, 27, 0x2D2102, false)
        guiGraphics.drawString(font, requireLevel, 11, 29, 0x2D2102, false)
        guiGraphics.drawString(font, requireLevel, 11, 28, 0xC8FF8F, false)
    }

    companion object {
        private val UID: ResourceLocation = ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "mana_injecting")
        val MANA_INJECTING_RECIPE_TYPE: RecipeType<ManaInjectingRecipe> =
            RecipeType(UID, ManaInjectingRecipe::class.java)
    }
}