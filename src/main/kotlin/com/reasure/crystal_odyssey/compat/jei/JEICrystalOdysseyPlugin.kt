package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.client.screen.ManaInjectorScreen
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaInjectorMenu
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.*
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation

@JeiPlugin
class JEICrystalOdysseyPlugin : IModPlugin {
    override fun getPluginUid(): ResourceLocation =
        ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "jei_plugin")

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        registration.addRecipeCategories(ManaInjectingRecipeCategory(registration.jeiHelpers.guiHelper))
    }

    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        registration.addRecipeCatalyst(ModBlocks.MANA_INJECTOR, ManaInjectingRecipeCategory.MANA_INJECTING_RECIPE_TYPE)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        val recipeManager = Minecraft.getInstance().level?.recipeManager ?: return

        val manaInjectingRecipes: List<ManaInjectingRecipe> =
            recipeManager.getAllRecipesFor(ModRecipeTypes.MANA_INJECTING_RECIPE_TYPE)
                .map { it.value }.toList()
        registration.addRecipes(ManaInjectingRecipeCategory.MANA_INJECTING_RECIPE_TYPE, manaInjectingRecipes)
    }

    override fun registerRecipeTransferHandlers(registration: IRecipeTransferRegistration) {
        registration.addRecipeTransferHandler(
            ManaInjectorMenu::class.java,
            ModMenuTypes.MANA_INJECTOR_MENU,
            ManaInjectingRecipeCategory.MANA_INJECTING_RECIPE_TYPE,
            0, 1,
            2, 36
        )
    }

    override fun registerGuiHandlers(registration: IGuiHandlerRegistration) {
        registration.addRecipeClickArea(
            ManaInjectorScreen::class.java,
            73, 35, 22, 15,
            ManaInjectingRecipeCategory.MANA_INJECTING_RECIPE_TYPE
        )
    }

    override fun registerItemSubtypes(registration: ISubtypeRegistration) {
        registration.registerSubtypeInterpreter(ModItems.GLOWSTONE_GEM_LANTERN, LanternSubtypeInterpreter())
    }
}