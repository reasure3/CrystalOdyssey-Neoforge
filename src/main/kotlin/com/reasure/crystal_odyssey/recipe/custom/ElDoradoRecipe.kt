package com.reasure.crystal_odyssey.recipe.custom

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.custom.input.TupleRecipeInput
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer

data class ElDoradoRecipe(
    val ingredientGem: Ingredient,
    val ingredientMaterial: Ingredient,
    val targetKey: ResourceKey<ElDoradoTarget>
) : ManaAnvilRecipe {
    private fun getTarget(registries: HolderLookup.Provider): ElDoradoTarget {
        val targetRegistries = registries.lookupOrThrow(ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY)
        return targetRegistries[targetKey].orElseThrow().value()
    }

    override fun getPriority(registries: HolderLookup.Provider): Int {
        return getTarget(registries).priority
    }

    override fun getIngredientFirst(): Ingredient = ingredientGem

    override fun getIngredientSecond(): Ingredient = ingredientMaterial

    override fun assemble(input: TupleRecipeInput, registries: HolderLookup.Provider): ItemStack {
        val output = getResultItem(registries)
        val emptyStaff = input.getItem(1)
        if (!emptyStaff.isComponentsPatchEmpty) {
            output.applyComponents(emptyStaff.componentsPatch)
        }
        return output
    }

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack {
        val target = getTarget(registries)
        val output = ItemStack(ModItems.EL_DORADO_STAFF_ACTIVE)
        output.set(ModDataComponents.FIND_BLOCKS, target.findBlocks)
        return output
    }

    override fun getSerializer(): RecipeSerializer<ElDoradoRecipe> = ModRecipeSerializers.EL_DORADO_SERIALIZER

    object Serializer : RecipeSerializer<ElDoradoRecipe> {
        private val CODEC: MapCodec<ElDoradoRecipe> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ElDoradoRecipe::ingredientGem),
                Ingredient.CODEC_NONEMPTY.optionalFieldOf("staff", Ingredient.of(ModItems.EL_DORADO_STAFF))
                    .forGetter(ElDoradoRecipe::ingredientMaterial),
                ResourceKey.codec(ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY).fieldOf("el_dorado_target")
                    .forGetter(ElDoradoRecipe::targetKey)
            ).apply(instance, ::ElDoradoRecipe)
        }

        private val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ElDoradoRecipe> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ElDoradoRecipe::ingredientGem,
            Ingredient.CONTENTS_STREAM_CODEC, ElDoradoRecipe::ingredientMaterial,
            ResourceKey.streamCodec(ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY), ElDoradoRecipe::targetKey,
            ::ElDoradoRecipe
        )

        override fun codec(): MapCodec<ElDoradoRecipe> = CODEC

        override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, ElDoradoRecipe> = STREAM_CODEC
    }
}
