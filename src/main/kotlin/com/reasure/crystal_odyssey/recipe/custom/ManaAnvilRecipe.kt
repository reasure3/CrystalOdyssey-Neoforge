package com.reasure.crystal_odyssey.recipe.custom

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import com.reasure.crystal_odyssey.recipe.custom.input.TupleRecipeInput
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level

data class ManaAnvilRecipe(val ingredientGem: Ingredient, val ingredientMaterial: Ingredient, val result: ItemStack) :
    Recipe<TupleRecipeInput> {
    override fun matches(input: TupleRecipeInput, level: Level): Boolean {
        return ingredientGem.test(input.getItem(0)) && ingredientMaterial.test(input.getItem(1))
    }

    override fun assemble(input: TupleRecipeInput, registries: HolderLookup.Provider): ItemStack {
        return result.copy()
    }

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = true

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getSerializer(): RecipeSerializer<ManaAnvilRecipe> = ModRecipeSerializers.MANA_ANVIL_SERIALIZER

    override fun getType(): RecipeType<ManaAnvilRecipe> = ModRecipeTypes.MANA_ANVIL_RECIPE_TYPE

    object Serializer : RecipeSerializer<ManaAnvilRecipe> {
        private val CODEC: MapCodec<ManaAnvilRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_gem").forGetter(ManaAnvilRecipe::ingredientGem),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_material").forGetter(ManaAnvilRecipe::ingredientMaterial),
                ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("result").forGetter(ManaAnvilRecipe::result)
            ).apply(instance, ::ManaAnvilRecipe)
        }

        private val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ManaAnvilRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ManaAnvilRecipe::ingredientGem,
            Ingredient.CONTENTS_STREAM_CODEC, ManaAnvilRecipe::ingredientMaterial,
            ItemStack.STREAM_CODEC, ManaAnvilRecipe::result,
            ::ManaAnvilRecipe
        )

        override fun codec(): MapCodec<ManaAnvilRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, ManaAnvilRecipe> = STREAM_CODEC
    }
}