package com.reasure.crystal_odyssey.compat.jei

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient

object ModAnvilRepairRecipeMaker {
    fun getRepairRecipes(vanillaRecipeFactory: IVanillaRecipeFactory): List<IJeiAnvilRecipe> {
//        val elDoradoStaffList = FindBlocks.makeDefaultList().map {
//            ItemStack(ModItems.EL_DORADO_STAFF_ACTIVE).apply {
//                set(ModDataComponents.FIND_BLOCKS, it)
//            }
//        }
        return listOf(
            vanillaRecipeFactory.createAnvilRecipe(
                ItemStack(ModItems.LIGHT_STAFF).apply { damageValue = maxDamage * 3 / 4 },
                listOf(ItemStack(ModItems.LIGHT_STAFF).apply { damageValue = maxDamage * 3 / 4 }),
                listOf(ItemStack(ModItems.LIGHT_STAFF).apply { damageValue = maxDamage / 2 }),
                ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "self_repair.light_staff")
            ),
            vanillaRecipeFactory.createAnvilRecipe(
                ItemStack(ModItems.LIGHT_STAFF).apply { damageValue = maxDamage },
                Ingredient.of(ModTags.Items.LIGHT_STAFF_REPAIRABLE).items.toList(),
                listOf(ItemStack(ModItems.LIGHT_STAFF).apply { damageValue = maxDamage * 3 / 4 }),
                ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "materials_repair.light_staff")
            ),
//            vanillaRecipeFactory.createAnvilRecipe(
//                elDoradoStaffList.map { it.copy().apply { damageValue = maxDamage * 3 / 4 } }.toList(),
//                elDoradoStaffList.map { it.copy().apply { damageValue = maxDamage * 3 / 4 } }.toList(),
//                elDoradoStaffList.map { it.copy().apply { damageValue = maxDamage / 2 } }.toList(),
//                ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "self_repair.el_dorado_staff")
//            ),
//            vanillaRecipeFactory.createAnvilRecipe(
//                elDoradoStaffList.map { it.copy().apply { damageValue = maxDamage } }.toList(),
//                Ingredient.of(ModTags.Items.EL_DORADO_STAFF_REPAIRABLE).items.toList(),
//                elDoradoStaffList.map { it.copy().apply { damageValue = maxDamage * 3 / 4 } }.toList(),
//                ResourceLocation.fromNamespaceAndPath(CrystalOdyssey.ID, "materials_repair.el_dorado_staff")
//            )
        )
    }
}