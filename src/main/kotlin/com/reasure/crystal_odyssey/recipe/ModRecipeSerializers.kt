package com.reasure.crystal_odyssey.recipe

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModRecipeSerializers {
    val RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>> =
        DeferredRegister.create(Registries.RECIPE_SERIALIZER, CrystalOdyssey.ID)

    val MANA_INJECTING_SERIALIZER: RecipeSerializer<ManaInjectingRecipe> by RECIPE_SERIALIZERS.register("mana_injecting") { -> ManaInjectingRecipe.Serializer }
}