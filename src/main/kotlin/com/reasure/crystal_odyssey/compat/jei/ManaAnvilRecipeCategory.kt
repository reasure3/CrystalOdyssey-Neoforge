package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.recipe.custom.ManaAnvilRecipe
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.gui.ingredient.IRecipeSlotsView
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.IRecipeCategory
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack


class ManaAnvilRecipeCategory(helper: IGuiHelper) : IRecipeCategory<ManaAnvilRecipe> {
    private val background: IDrawable = helper.drawableBuilder(
        ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "textures/gui/jei/mana_anvil.png"),
        0, 0, 54, 53
    ).setTextureSize(54, 53).build()

    private val icon: IDrawable = helper.createDrawableItemStack(ItemStack(ModBlocks.MANA_ANVIL))

    override fun getRecipeType(): RecipeType<ManaAnvilRecipe> = MANA_ANVIL_RECIPE_TYPE

    override fun getTitle(): Component = TranslateHelper.Jei.CATEGORY_MANA_ANVIL.translateComponent()

    override fun getIcon(): IDrawable = icon

    override fun setRecipe(builder: IRecipeLayoutBuilder, recipe: ManaAnvilRecipe, focuses: IFocusGroup) {
        builder.addInputSlot(1, 1).addIngredients(recipe.ingredientGem)
        builder.addInputSlot(1, 36).addIngredients(recipe.ingredientMaterial)
        builder.addOutputSlot(33, 18).addItemStack(recipe.result)
    }

    override fun getWidth(): Int = 54
    override fun getHeight(): Int = 53

    override fun draw(
        recipe: ManaAnvilRecipe,
        recipeSlotsView: IRecipeSlotsView,
        guiGraphics: GuiGraphics,
        mouseX: Double,
        mouseY: Double
    ) {
        background.draw(guiGraphics)
    }

    companion object {
        private val UID: ResourceLocation = ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "mana_anvil")
        val MANA_ANVIL_RECIPE_TYPE: RecipeType<ManaAnvilRecipe> =
            RecipeType(UID, ManaAnvilRecipe::class.java)
    }
}