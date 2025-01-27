package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.server.base.ModBaseRecipeProvider
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraft.world.item.enchantment.Enchantments
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

        manaInjecting(recipeOutput, ItemTags.ANVIL, ModBlocks.MANA_ANVIL, 30)

        manaInjecting(recipeOutput, Tags.Items.GEMS_DIAMOND, ModItems.ENCHANTED_DIAMOND, 10)
        manaInjecting(recipeOutput, Tags.Items.GEMS_EMERALD, ModItems.ENCHANTED_EMERALD, 10)
        manaInjecting(recipeOutput, Tags.Items.GEMS_AMETHYST, ModItems.ENCHANTED_AMETHYST_SHARD, 10)
        manaInjecting(recipeOutput, ModItems.GLOWSTONE_GEM, ModItems.ENCHANTED_GLOWSTONE_GEM, 10)
        manaInjecting(recipeOutput, ModItems.RUBY, ModItems.ENCHANTED_RUBY, 10)
        manaInjecting(recipeOutput, ModItems.SAPPHIRE, ModItems.ENCHANTED_SAPPHIRE, 10)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DIAMOND)
            .requiredBy(ModItems.ENCHANTED_DIAMOND)
            .save(recipeOutput, CrystalOdyssey.modLoc("diamond_by_extract_mana"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.EMERALD)
            .requiredBy(ModItems.ENCHANTED_EMERALD)
            .save(recipeOutput, CrystalOdyssey.modLoc("emerald_by_extract_mana"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.AMETHYST_SHARD)
            .requiredBy(ModItems.ENCHANTED_AMETHYST_SHARD)
            .save(recipeOutput, CrystalOdyssey.modLoc("amethyst_shard_by_extract_mana"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GLOWSTONE_GEM)
            .requiredBy(ModItems.ENCHANTED_GLOWSTONE_GEM)
            .save(recipeOutput, CrystalOdyssey.modLoc("glowstone_gem_by_extract_mana"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE)
            .requiredBy(ModItems.ENCHANTED_SAPPHIRE)
            .save(recipeOutput, CrystalOdyssey.modLoc("sapphire_by_extract_mana"))

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY)
            .requiredBy(ModItems.ENCHANTED_RUBY)
            .save(recipeOutput, CrystalOdyssey.modLoc("ruby_by_extract_mana"))

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLOWSTONE_GEM_CORE)
            .unlockedBy('X', ModItems.ENCHANTED_DIAMOND)
            .unlockedBy('#', ModItems.ENCHANTED_GLOWSTONE_GEM)
            .pattern("XXX")
            .pattern("X#X")
            .pattern("XXX")
            .save(recipeOutput)

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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LIGHT_STAFF)
            .unlockedBy('X', ModItems.ENCHANTED_GLOWSTONE_GEM)
            .unlockedBy('#', Tags.Items.RODS_WOODEN)
            .pattern("  X")
            .pattern(" # ")
            .pattern("#  ")
            .save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EL_DORADO_STAFF)
            .unlockedBy('X', ModItems.GLOWSTONE_GEM)
            .unlockedBy('#', Tags.Items.RODS_BLAZE)
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

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.EFFICIENCY), 5)
            ),
            priority = 3,
            id = CrystalOdyssey.modLoc("enchanted_book_efficiency")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.PROTECTION), 4)
            ),
            priority = 2,
            id = CrystalOdyssey.modLoc("enchanted_book_protection")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.UNBREAKING), 3)
            ),
            priority = 2,
            id = CrystalOdyssey.modLoc("enchanted_book_unbreaking")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FEATHER_FALLING), 4)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_feather_falling")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.PROJECTILE_PROTECTION), 4)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_projectile_protection")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_DIAMOND,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.KNOCKBACK), 2)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_knockback")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_EMERALD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FORTUNE), 3)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_fortune")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_EMERALD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.SILK_TOUCH), 1)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_silk_touch")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_EMERALD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.LOOTING), 3)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_looting")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.SHARPNESS), 5)
            ),
            priority = 4,
            id = CrystalOdyssey.modLoc("enchanted_book_sharpness")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.SMITE), 5)
            ),
            priority = 3,
            id = CrystalOdyssey.modLoc("enchanted_book_smite")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.SWEEPING_EDGE), 3)
            ),
            priority = 2,
            id = CrystalOdyssey.modLoc("enchanted_book_sweeping_edge")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.IMPALING), 5)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_impaling")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.BANE_OF_ARTHROPODS), 5)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_bane_of_arthropods")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_AMETHYST_SHARD,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.THORNS), 3)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_thorns")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.AQUA_AFFINITY), 1)
            ),
            priority = 3,
            id = CrystalOdyssey.modLoc("enchanted_book_aqua_affinity")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.RESPIRATION), 3)
            ),
            priority = 3,
            id = CrystalOdyssey.modLoc("enchanted_book_respiration")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.DEPTH_STRIDER), 3)
            ),
            priority = 3,
            id = CrystalOdyssey.modLoc("enchanted_book_depth_strider")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FROST_WALKER), 2)
            ),
            priority = 2,
            id = CrystalOdyssey.modLoc("enchanted_book_frost_walker")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.CHANNELING), 1)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_channeling")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.LOYALTY), 3)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_loyalty")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.RIPTIDE), 3)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_book_riptide")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.LUCK_OF_THE_SEA), 3)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_luck_of_sea")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_SAPPHIRE,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.LURE), 3)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_book_lure")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_RUBY,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FIRE_ASPECT), 2)
            ),
            priority = 2,
            id = CrystalOdyssey.modLoc("enchanted_fire_aspect")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_RUBY,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FLAME), 1)
            ),
            priority = 1,
            id = CrystalOdyssey.modLoc("enchanted_flame")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_RUBY,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.BLAST_PROTECTION), 4)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_blast_protection")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_RUBY,
            inputMaterial = Items.BOOK,
            output = EnchantedBookItem.createForEnchantment(
                EnchantmentInstance(registries.get().holderOrThrow(Enchantments.FIRE_PROTECTION), 4)
            ),
            priority = 0,
            id = CrystalOdyssey.modLoc("enchanted_fire_protection")
        )

        manaAnvil(
            recipeOutput = recipeOutput,
            inputGem = ModItems.ENCHANTED_RUBY,
            inputMaterial = Items.BOOK,
            output = ItemStack(Items.BOOK).apply {
                set(
                    ModDataComponents.FIND_BLOCKS,
                    FindBlocks.of(Tags.Blocks.ORES, "hahaha", 0xFF00FF00)
                )
            },
            priority = 0,
            id = CrystalOdyssey.modLoc("test")
        )

        FindBlocks.makeVanillaList().zip(
            listOf(
                "netherite", "diamond", "emerald", "gold", "iron",
                "copper", "coal", "lapis_lazuli", "redstone", "quartz"
            )
        ).reversed().forEachIndexed { index, pair ->
            manaAnvil(
                recipeOutput = recipeOutput,
                inputGem = ModItems.GLOWSTONE_GEM_CORE,
                inputMaterial = ModItems.EL_DORADO_STAFF,
                output = ItemStack(ModItems.EL_DORADO_STAFF_ACTIVE).apply {
                    set(ModDataComponents.FIND_BLOCKS, pair.first)
                },
                priority = index,
                maintainData = true,
                id = CrystalOdyssey.modLoc("el_dorado_staff_${pair.second}")
            )
        }
    }
}