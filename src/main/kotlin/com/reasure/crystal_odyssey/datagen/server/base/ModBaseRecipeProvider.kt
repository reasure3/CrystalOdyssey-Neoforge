package com.reasure.crystal_odyssey.datagen.server.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.recipe.custom.ManaAnvilRecipe
import com.reasure.crystal_odyssey.recipe.custom.ManaInjectingRecipe
import net.minecraft.advancements.Criterion
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import java.util.concurrent.CompletableFuture

abstract class ModBaseRecipeProvider(
    output: PackOutput,
    protected val registries: CompletableFuture<HolderLookup.Provider>
) : RecipeProvider(output, registries) {
    fun storageBlock(
        recipeOutput: RecipeOutput, unpacked: ItemLike, packed: ItemLike,
        unpackedCategory: RecipeCategory = RecipeCategory.MISC,
        unpackedName: String = getConversionRecipeName(unpacked, packed),
        unpackedGroup: String? = null,
        packedCategory: RecipeCategory = RecipeCategory.BUILDING_BLOCKS,
        packedName: String = getItemName(packed),
        packedGroup: String? = null,
    ) = nineBlockStorageRecipes(
        recipeOutput, unpackedCategory, unpacked, packedCategory, packed,
        "${CrystalOdyssey.ID}:$packedName", packedGroup,
        "${CrystalOdyssey.ID}:$unpackedName", unpackedGroup
    )

    private fun smelting(
        recipeOutput: RecipeOutput, result: ItemLike, ingredient: ItemLike,
        category: RecipeCategory = RecipeCategory.MISC,
        experience: Float = 0.1f,
        cookingTime: Int = 200,
        name: String = getItemName(result),
        criterion: Criterion<*> = has(ingredient),
        group: String? = null
    ) = SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
        .unlockedBy(getHasName(ingredient), criterion)
        .group(group)
        .save(recipeOutput, CrystalOdyssey.modLoc(name))

    private fun blasting(
        recipeOutput: RecipeOutput, result: ItemLike, ingredient: ItemLike,
        category: RecipeCategory = RecipeCategory.MISC,
        experience: Float = 0.1f,
        cookingTime: Int = 100,
        name: String = getItemName(result),
        criterion: Criterion<*> = has(ingredient),
        group: String? = null
    ) = SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
        .unlockedBy(getHasName(ingredient), criterion)
        .group(group)
        .save(recipeOutput, CrystalOdyssey.modLoc(name))

    fun smeltingAndBlasting(
        recipeOutput: RecipeOutput, result: ItemLike, ingredient: ItemLike,
        category: RecipeCategory = RecipeCategory.MISC,
        experience: Float = 0.1f,
        cookingTime: Int = 200,
        smeltingName: String = getSmeltingRecipeName(result),
        blastingName: String = getBlastingRecipeName(result),
        criterion: Criterion<*> = has(ingredient),
        group: String? = null
    ) {
        smelting(recipeOutput, result, ingredient, category, experience, cookingTime, smeltingName, criterion, group)
        blasting(
            recipeOutput, result, ingredient, category, experience, cookingTime / 2, blastingName, criterion, group
        )
    }

    fun ShapedRecipeBuilder.unlockedBy(symbol: Char, ingredient: ItemLike): ShapedRecipeBuilder =
        unlockedBy(getHasName(ingredient), has(ingredient)).define(symbol, ingredient)

    fun ShapedRecipeBuilder.unlockedBy(symbol: Char, tag: TagKey<Item>): ShapedRecipeBuilder =
        unlockedBy(getHasName(tag), has(tag)).define(symbol, tag)

    private fun getHasName(tag: TagKey<Item>): String {
        return "has_" + tag.location.path.replace('/', '_')
    }

    fun ShapelessRecipeBuilder.requiredBy(ingredient: ItemLike): ShapelessRecipeBuilder =
        unlockedBy(getHasName(ingredient), has(ingredient)).requires(ingredient)

    fun manaInjecting(
        recipeOutput: RecipeOutput,
        input: ItemLike,
        output: ItemLike,
        requireLevel: Int,
        id: ResourceLocation = BuiltInRegistries.ITEM.getKey(output.asItem())
    ) {
        val recipe = ManaInjectingRecipe(Ingredient.of(input), ItemStack(output), requireLevel)
        recipeOutput.accept(id.withPrefix("mana_injecting/"), recipe, null)
    }

    fun manaInjecting(
        recipeOutput: RecipeOutput,
        input: TagKey<Item>,
        output: ItemLike,
        requireLevel: Int,
        id: ResourceLocation = BuiltInRegistries.ITEM.getKey(output.asItem())
    ) {
        val recipe = ManaInjectingRecipe(Ingredient.of(input), ItemStack(output), requireLevel)
        recipeOutput.accept(id.withPrefix("mana_injecting/"), recipe, null)
    }

    fun manaInjecting(
        recipeOutput: RecipeOutput,
        input: Ingredient,
        output: ItemStack,
        requireLevel: Int,
        id: ResourceLocation
    ) {
        val recipe = ManaInjectingRecipe(input, output, requireLevel)
        recipeOutput.accept(id.withPrefix("mana_injecting/"), recipe, null)
    }

    fun manaAnvil(
        recipeOutput: RecipeOutput,
        inputGem: ItemLike,
        inputMaterial: ItemLike,
        output: ItemStack,
        priority: Int,
        maintainData: Boolean = false,
        id: ResourceLocation = BuiltInRegistries.ITEM.getKey(output.item)
    ) {
        val recipe = ManaAnvilRecipe(Ingredient.of(inputGem), Ingredient.of(inputMaterial), output, priority, maintainData)
        recipeOutput.accept(id.withPrefix("mana_anvil/"), recipe, null)
    }

    fun getSmeltingName(output: ItemLike, ingredient: ItemLike) =
        getSmeltingRecipeName(output) + "_" + getItemName(ingredient)

    fun getBlastingName(output: ItemLike, ingredient: ItemLike) =
        getBlastingRecipeName(output) + "_" + getItemName(ingredient)
}