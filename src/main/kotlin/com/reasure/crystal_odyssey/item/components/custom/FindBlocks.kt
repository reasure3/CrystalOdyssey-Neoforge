package com.reasure.crystal_odyssey.item.components.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.util.CustomColor
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

data class FindBlocks(
    val blocks: HolderSet<Block>,
    val borderColor: CustomColor
) {
    fun isNotEmpty() = blocks.size() > 0

    companion object {
        val EMPTY = FindBlocks(HolderSet.empty(), CustomColor.DEFAULT)

        val CODEC: Codec<FindBlocks> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("blocks").forGetter(FindBlocks::blocks),
                CustomColor.CODEC.optionalFieldOf("border_color", CustomColor.DEFAULT)
                    .forGetter(FindBlocks::borderColor)
            ).apply(instance, ::FindBlocks)
        }

        val STEAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, FindBlocks> = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.BLOCK), FindBlocks::blocks,
            CustomColor.STREAM_CODEC, FindBlocks::borderColor,
            ::FindBlocks
        )

        fun of(blockTag: TagKey<Block>, borderColor: Long) = FindBlocks(
            BuiltInRegistries.BLOCK.getOrCreateTag(blockTag),
            CustomColor.of(borderColor)
        )
    }
}