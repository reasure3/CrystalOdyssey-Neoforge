package com.reasure.crystal_odyssey.item.components.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.util.CustomColor
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import kotlin.jvm.optionals.getOrNull

data class FindBlocks(
    val blocks: HolderSet<Block>,
    val blockGroupName: Component,
    val borderColor: CustomColor
) {
    fun isNotEmpty() = blocks.size() > 0

    companion object {
        val EMPTY = FindBlocks(HolderSet.empty(), Component.empty(), CustomColor.DEFAULT)

        val CODEC: Codec<FindBlocks> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("blocks").forGetter(FindBlocks::blocks),
                ComponentSerialization.CODEC.fieldOf("block_group_name").forGetter(FindBlocks::blockGroupName),
                CustomColor.CODEC.optionalFieldOf("border_color", CustomColor.DEFAULT)
                    .forGetter(FindBlocks::borderColor)
            ).apply(instance, ::FindBlocks)
        }

        val STEAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, FindBlocks> = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.BLOCK), FindBlocks::blocks,
            ComponentSerialization.STREAM_CODEC, FindBlocks::blockGroupName,
            CustomColor.STREAM_CODEC, FindBlocks::borderColor,
            ::FindBlocks
        )

        fun of(blockTag: TagKey<Block>, blockGroupNameTransKey: String, borderColor: Long) = FindBlocks(
            BuiltInRegistries.BLOCK.getOrCreateTag(blockTag),
            Component.translatable(blockGroupNameTransKey),
            CustomColor.of(borderColor)
        )

        fun of(blocks: List<Block>, blockGroupNameTransKey: String, borderColor: Long) = FindBlocks(
            HolderSet.direct(blocks.mapNotNull { block ->
                BuiltInRegistries.BLOCK.getHolder(
                    BuiltInRegistries.BLOCK.getKey(block)
                ).getOrNull()
            }.toList()),
            Component.translatable(blockGroupNameTransKey),
            CustomColor.of(borderColor)
        )
    }
}