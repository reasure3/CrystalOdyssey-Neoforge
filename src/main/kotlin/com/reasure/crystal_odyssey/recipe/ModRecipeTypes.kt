package com.reasure.crystal_odyssey.recipe

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModRecipeTypes {
    val RECIPE_TYPES: DeferredRegister<RecipeType<*>> =
        DeferredRegister.create(Registries.RECIPE_TYPE, CrystalOdyssey.ID)

    val MANA_INJECTING_RECIPE_TYPE: RecipeType<ManaInjectingRecipe> by RECIPE_TYPES.register("mana_injecting") { ->
        RecipeType.simple(CrystalOdyssey.modLoc("mana_injecting"))
    }
}