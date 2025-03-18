package com.reasure.crystal_odyssey.recipe.custom

import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.input.TupleRecipeInput
import net.minecraft.core.HolderLookup
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

interface ManaAnvilRecipe : Recipe<TupleRecipeInput> {
    fun getPriority(registries: HolderLookup.Provider): Int
    fun getIngredientFirst(): Ingredient
    fun getIngredientSecond(): Ingredient

    override fun matches(input: TupleRecipeInput, level: Level): Boolean {
        return getIngredientFirst().test(input.getItem(0)) && getIngredientSecond().test(input.getItem(1))
    }

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = true

    override fun getType(): RecipeType<ManaAnvilRecipe> = ModRecipeTypes.MANA_ANVIL_RECIPE_TYPE
}