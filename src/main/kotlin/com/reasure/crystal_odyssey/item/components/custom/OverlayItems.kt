package com.reasure.crystal_odyssey.item.components.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient

data class OverlayItems(val items: Ingredient, val rotationTimeMills: Int = 1500) {
    fun isEmpty() = items.isEmpty

    companion object {
        val EMPTY = OverlayItems(Ingredient.EMPTY)

        val CODEC: Codec<OverlayItems> = RecordCodecBuilder.create { instance ->
            instance.group(
                Ingredient.CODEC.optionalFieldOf("items", Ingredient.EMPTY).forGetter(OverlayItems::items),
                ExtraCodecs.POSITIVE_INT.optionalFieldOf("rotation_time_mills", 1500)
                    .forGetter(OverlayItems::rotationTimeMills)
            ).apply(instance, ::OverlayItems)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, OverlayItems> = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, OverlayItems::items,
            ByteBufCodecs.VAR_INT, OverlayItems::rotationTimeMills,
            ::OverlayItems
        )

        fun of(itemTag: TagKey<Item>, rotationTimeMills: Int = 1500) =
            OverlayItems(Ingredient.of(itemTag), rotationTimeMills)
    }
}
