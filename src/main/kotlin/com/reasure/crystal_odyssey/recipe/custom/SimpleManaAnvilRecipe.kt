package com.reasure.crystal_odyssey.recipe.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.custom.input.TupleRecipeInput
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer

// The higher the priority number, the higher the priority.
data class SimpleManaAnvilRecipe(
    val ingredientGem: Ingredient,
    val ingredientMaterial: Ingredient,
    val result: ItemStack,
    val priority: Int = 1,
    val maintainData: Boolean = false
) : ManaAnvilRecipe {
    override fun getPriority(registries: HolderLookup.Provider): Int = priority

    override fun getGem(): Ingredient = ingredientGem

    override fun getMaterial(): Ingredient = ingredientMaterial

    override fun assemble(input: TupleRecipeInput, registries: HolderLookup.Provider): ItemStack {
        val output: ItemStack = result.copy()
        if (maintainData) {
            val inputMaterial = input.getItem(1)
            if (!inputMaterial.isComponentsPatchEmpty) {
                output.applyComponents(inputMaterial.componentsPatch)
            }
        }
        return output
    }

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack = result

    override fun getSerializer(): RecipeSerializer<SimpleManaAnvilRecipe> =
        ModRecipeSerializers.SIMPLE_MANA_ANVIL_SERIALIZER

    object Serializer : RecipeSerializer<SimpleManaAnvilRecipe> {
        private val CODEC: MapCodec<SimpleManaAnvilRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_gem").forGetter(SimpleManaAnvilRecipe::ingredientGem),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient_material")
                    .forGetter(SimpleManaAnvilRecipe::ingredientMaterial),
                ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("result").forGetter(SimpleManaAnvilRecipe::result),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("priority").forGetter(SimpleManaAnvilRecipe::priority),
                Codec.BOOL.optionalFieldOf("maintain_data", false).forGetter(SimpleManaAnvilRecipe::maintainData)
            ).apply(instance, ::SimpleManaAnvilRecipe)
        }

        private val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SimpleManaAnvilRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, SimpleManaAnvilRecipe::ingredientGem,
            Ingredient.CONTENTS_STREAM_CODEC, SimpleManaAnvilRecipe::ingredientMaterial,
            ItemStack.STREAM_CODEC, SimpleManaAnvilRecipe::result,
            ByteBufCodecs.VAR_INT, SimpleManaAnvilRecipe::priority,
            ByteBufCodecs.BOOL, SimpleManaAnvilRecipe::maintainData,
            ::SimpleManaAnvilRecipe
        )

        override fun codec(): MapCodec<SimpleManaAnvilRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, SimpleManaAnvilRecipe> = STREAM_CODEC
    }
}
