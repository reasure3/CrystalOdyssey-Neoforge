package com.reasure.crystal_odyssey.datagen.server.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.advancements.Criterion
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import java.util.concurrent.CompletableFuture

abstract class ModBaseRecipeProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    RecipeProvider(output, registries) {
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

    fun smelting(
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

    fun blasting(
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

    fun ShapedRecipeBuilder.unlockedBy(ingredient: ItemLike): ShapedRecipeBuilder =
        unlockedBy(getHasName(ingredient), has(ingredient))
}