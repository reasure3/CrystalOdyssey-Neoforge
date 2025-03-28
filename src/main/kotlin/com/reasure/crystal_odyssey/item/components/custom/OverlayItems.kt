package com.reasure.crystal_odyssey.item.components.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.registry.RegistryHolder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.Item

data class OverlayItems(val items: RegistryHolder<Item>, val rotationTimeMills: Int = 1500) {
    companion object {
        val EMPTY = OverlayItems(RegistryHolder(BuiltInRegistries.ITEM, emptyList()))

        val CODEC: Codec<OverlayItems> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryHolder.ITEM_CODEC.fieldOf("items").forGetter(OverlayItems::items),
                ExtraCodecs.POSITIVE_INT.optionalFieldOf("rotation_time_mills", 1500)
                    .forGetter(OverlayItems::rotationTimeMills)
            ).apply(instance, ::OverlayItems)
        }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, OverlayItems> = StreamCodec.composite(
            ByteBufCodecs.fromCodecWithRegistries(RegistryHolder.ITEM_CODEC), OverlayItems::items,
            ByteBufCodecs.VAR_INT, OverlayItems::rotationTimeMills,
            ::OverlayItems
        )

        fun of(itemTag: TagKey<Item>, rotationTimeMills: Int = 1500) =
            OverlayItems(
                RegistryHolder<Item>(
                    BuiltInRegistries.ITEM,
                    listOf(ExtraCodecs.TagOrElementLocation(itemTag.location, true))
                ), rotationTimeMills
            )
    }
}
