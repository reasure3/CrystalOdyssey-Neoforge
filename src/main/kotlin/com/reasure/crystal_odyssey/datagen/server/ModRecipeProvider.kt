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
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.common.Tags
import net.neoforged.neoforge.common.crafting.DataComponentIngredient
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.SimpleFluidContent
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

        smeltingAndBlasting(
            recipeOutput, ModItems.RUBY, ModBlocks.RUBY_ORE,
            group = getItemName(ModItems.RUBY),
            experience = 0.7f,
            smeltingName = getSmeltingName(ModItems.RUBY, ModBlocks.RUBY_ORE),
            blastingName = getBlastingName(ModItems.RUBY, ModBlocks.RUBY_ORE)
        )

        smeltingAndBlasting(
            recipeOutput, ModItems.RUBY, ModBlocks.DEEPSLATE_RUBY_ORE,
            group = getItemName(ModItems.RUBY),
            experience = 0.7f,
            smeltingName = getSmeltingName(ModItems.RUBY, ModBlocks.DEEPSLATE_RUBY_ORE),
            blastingName = getBlastingName(ModItems.RUBY, ModBlocks.DEEPSLATE_RUBY_ORE)
        )

        smeltingAndBlasting(
            recipeOutput, ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_ORE,
            group = getItemName(ModItems.SAPPHIRE),
            experience = 1.0f,
            smeltingName = getSmeltingName(ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_ORE),
            blastingName = getBlastingName(ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_ORE)
        )

        smeltingAndBlasting(
            recipeOutput, ModItems.SAPPHIRE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE,
            group = getItemName(ModItems.SAPPHIRE),
            experience = 1.0f,
            smeltingName = getSmeltingName(ModItems.SAPPHIRE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE),
            blastingName = getBlastingName(ModItems.SAPPHIRE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
        )

        storageBlock(
            recipeOutput, ModItems.RUBY, ModBlocks.RUBY_BLOCK,
            unpackedGroup = getItemName(ModItems.RUBY)
        )

        storageBlock(
            recipeOutput, ModItems.SAPPHIRE, ModBlocks.SAPPHIRE_BLOCK,
            unpackedGroup = getItemName(ModItems.SAPPHIRE)
        )

        ShapedRecipeBuilder.shaped(
            RecipeCategory.TOOLS,
            ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 0) }
        ).unlockedBy('X', ModItems.GLOWSTONE_GEM)
            .unlockedBy('#', Tags.Items.NUGGETS_IRON)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANA_INJECTOR)
            .unlockedBy('X', Items.DIAMOND)
            .unlockedBy('#', Blocks.TINTED_GLASS)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .save(recipeOutput)

        manaInjecting(recipeOutput, ModItems.GLOWSTONE_GEM, ModItems.ENCHANTED_GLOWSTONE_GEM, 10)
        manaInjecting(recipeOutput, ModItems.RUBY, ModItems.ENCHANTED_RUBY, 10)
        manaInjecting(recipeOutput, ModItems.SAPPHIRE, ModItems.ENCHANTED_SAPPHIRE, 10)

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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LIGHT_STAFF)
            .unlockedBy('X', ModItems.ENCHANTED_GLOWSTONE_GEM)
            .unlockedBy('#', Tags.Items.RODS)
            .pattern("  X")
            .pattern(" # ")
            .pattern("#  ")
            .save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SAPPHIRE_BUCKET)
            .unlockedBy('X', ModItems.ENCHANTED_SAPPHIRE)
            .unlockedBy('#', Tags.Items.INGOTS_IRON)
            .pattern("#X#")
            .pattern(" # ")
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.SAPPHIRE_BUCKET)
            .requiredBy(Items.BUCKET)
            .requiredBy(ModItems.ENCHANTED_SAPPHIRE)
            .save(recipeOutput, CrystalOdyssey.modLoc("sapphire_bucket_from_bucket"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemStack(ModItems.SAPPHIRE_BUCKET).apply {
            set(
                ModDataComponents.FLUID,
                SimpleFluidContent.copyOf(FluidStack(Fluids.WATER, FluidType.BUCKET_VOLUME))
            )
        }).requiredBy(Items.WATER_BUCKET)
            .requiredBy(ModItems.ENCHANTED_SAPPHIRE)
            .save(recipeOutput, CrystalOdyssey.modLoc("sapphire_bucket_from_water_bucket"))

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_BUCKET)
            .unlockedBy('X', ModItems.ENCHANTED_RUBY)
            .unlockedBy('#', Tags.Items.INGOTS_IRON)
            .pattern("#X#")
            .pattern(" # ")
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.RUBY_BUCKET)
            .requiredBy(Items.BUCKET)
            .requiredBy(ModItems.ENCHANTED_RUBY)
            .save(recipeOutput, CrystalOdyssey.modLoc("ruby_bucket_from_bucket"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ItemStack(ModItems.RUBY_BUCKET).apply {
            set(
                ModDataComponents.FLUID,
                SimpleFluidContent.copyOf(FluidStack(Fluids.LAVA, FluidType.BUCKET_VOLUME))
            )
        }).requiredBy(Items.LAVA_BUCKET)
            .requiredBy(ModItems.ENCHANTED_RUBY)
            .save(recipeOutput, CrystalOdyssey.modLoc("ruby_bucket_from_lava_bucket"))

        manaInjecting(
            recipeOutput,
            input = DataComponentIngredient.of(
                false,
                ModDataComponents.FLUID,
                SimpleFluidContent.copyOf(FluidStack(Fluids.WATER, 30 * FluidType.BUCKET_VOLUME)),
                ModItems.SAPPHIRE_BUCKET
            ),
            output = ItemStack(ModItems.INFINITE_SAPPHIRE_BUCKET),
            requireLevel = 50,
            id = CrystalOdyssey.modLoc("infinite_sapphire_bucket")
        )

        manaInjecting(
            recipeOutput,
            input = DataComponentIngredient.of(
                false,
                ModDataComponents.FLUID,
                SimpleFluidContent.copyOf(FluidStack(Fluids.LAVA, 30 * FluidType.BUCKET_VOLUME)),
                ModItems.RUBY_BUCKET
            ),
            output = ItemStack(ModItems.INFINITE_RUBY_BUCKET),
            requireLevel = 50,
            id = CrystalOdyssey.modLoc("infinite_ruby_bucket")
        )
    }
}