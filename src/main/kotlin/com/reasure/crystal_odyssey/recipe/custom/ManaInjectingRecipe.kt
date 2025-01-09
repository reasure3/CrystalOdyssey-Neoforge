package com.reasure.crystal_odyssey.recipe.custom

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.Level


data class ManaInjectingRecipe(val ingredient: Ingredient, val result: ItemStack, val requireLevel: Int) :
    Recipe<SingleRecipeInput> {
    override fun matches(input: SingleRecipeInput, level: Level): Boolean {
        if (level.isClientSide) return false
        return ingredient.test(input.getItem(0))
    }

    override fun assemble(input: SingleRecipeInput, registries: HolderLookup.Provider): ItemStack {
        return result.copy()
    }

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = true

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getSerializer(): RecipeSerializer<ManaInjectingRecipe> = ModRecipeSerializers.MANA_INJECTING_SERIALIZER

    override fun getType(): RecipeType<ManaInjectingRecipe> = ModRecipeTypes.MANA_INJECTING_RECIPE_TYPE

    object Serializer : RecipeSerializer<ManaInjectingRecipe> {
        private val CODEC: MapCodec<ManaInjectingRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ManaInjectingRecipe::ingredient),
                ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("result").forGetter(ManaInjectingRecipe::result),
                ExtraCodecs.intRange(0, 25565).fieldOf("require_level").forGetter(ManaInjectingRecipe::requireLevel)
            ).apply(instance, ::ManaInjectingRecipe)
        }

        private val STEAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ManaInjectingRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ManaInjectingRecipe::ingredient,
            ItemStack.STREAM_CODEC, ManaInjectingRecipe::result,
            ByteBufCodecs.VAR_INT, ManaInjectingRecipe::requireLevel,
            ::ManaInjectingRecipe
        )

        override fun codec(): MapCodec<ManaInjectingRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, ManaInjectingRecipe> = STEAM_CODEC
    }
}
