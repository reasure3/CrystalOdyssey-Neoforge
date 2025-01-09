package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.server.base.ModBaseRecipeProvider
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.neoforged.neoforge.common.crafting.DataComponentIngredient
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    ModBaseRecipeProvider(output, registries) {

    override fun buildRecipes(recipeOutput: RecipeOutput) {
        smeltingAndBlasting(
            recipeOutput, ModItems.GLOWSTONE_GEM, Items.GLOWSTONE,
            group = getItemName(ModItems.GLOWSTONE_GEM)
        )

        storageBlock(
            recipeOutput, ModItems.GLOWSTONE_GEM, ModBlocks.GLOWSTONE_GEM_BLOCK,
            unpackedGroup = getItemName(ModItems.GLOWSTONE_GEM)
        )

        ShapedRecipeBuilder.shaped(
            RecipeCategory.TOOLS,
            ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 0) }
        ).unlockedBy(ModItems.GLOWSTONE_GEM)
            .unlockedBy(Items.IRON_NUGGET)
            .define('X', ModItems.GLOWSTONE_GEM)
            .define('#', Items.IRON_NUGGET)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANA_INJECTOR)
            .unlockedBy(Items.DIAMOND)
            .unlockedBy(Blocks.TINTED_GLASS)
            .define('X', Items.DIAMOND)
            .define('#', Blocks.TINTED_GLASS)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .save(recipeOutput)

        manaInjecting(recipeOutput, ModItems.GLOWSTONE_GEM, ModItems.ENCHANTED_GLOWSTONE_GEM, 10)

        manaInjecting(
            recipeOutput,
            input = DataComponentIngredient.of(
                false, ModDataComponents.LANTERN_LEVEL, 0, ModItems.GLOWSTONE_GEM_LANTERN
            ),
            output = ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 1) },
            requireLevel = 7,
            id = CrystalOdyssey.modLoc("glowstone_gem_lantern_level_2")
        )
        manaInjecting(
            recipeOutput,
            input = DataComponentIngredient.of(
                false, ModDataComponents.LANTERN_LEVEL, 1, ModItems.GLOWSTONE_GEM_LANTERN
            ),
            output = ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 2) },
            requireLevel = 14,
            id = CrystalOdyssey.modLoc("glowstone_gem_lantern_level_3")
        )

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLOWSTONE_GEM)
            .requiredBy(ModItems.ENCHANTED_GLOWSTONE_GEM)
            .save(recipeOutput, CrystalOdyssey.modLoc("glowstone_gem_by_extract_mana"))
    }
}