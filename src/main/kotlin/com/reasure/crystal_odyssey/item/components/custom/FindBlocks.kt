package com.reasure.crystal_odyssey.item.components.custom

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.util.CustomColor
import com.reasure.crystal_odyssey.util.ModTags
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

        fun makeDefaultList(): List<FindBlocks> {
            return listOf(
                of(
                    ModTags.Blocks.EL_DORADO_NETHERITE,
                    "item.${CrystalOdyssey.ID}.el_dorado.netherite",
                    0xFF271C1D
                ),
                of(
                    ModTags.Blocks.EL_DORADO_DIAMOND,
                    "item.${CrystalOdyssey.ID}.el_dorado.diamond",
                    0x7F11727A
                ),
                of(
                    ModTags.Blocks.EL_DORADO_EMERALD,
                    "item.${CrystalOdyssey.ID}.el_dorado.emerald",
                    0x7F005300
                ),
                of(
                    ModTags.Blocks.EL_DORADO_GOLD,
                    "item.${CrystalOdyssey.ID}.el_dorado.gold",
                    0x7FB26411
                ),
                of(
                    ModTags.Blocks.EL_DORADO_IRON,
                    "item.${CrystalOdyssey.ID}.el_dorado.iron",
                    0x7FD8D8D8
                ),
                of(
                    ModTags.Blocks.EL_DORADO_COPPER,
                    "item.${CrystalOdyssey.ID}.el_dorado.copper",
                    0x7F9C4529
                ),
                of(
                    ModTags.Blocks.EL_DORADO_COAL,
                    "item.${CrystalOdyssey.ID}.el_dorado.coal",
                    0xFF1C1C1E
                ),
                of(
                    ModTags.Blocks.EL_DORADO_LAPIS_LAZULI,
                    "item.${CrystalOdyssey.ID}.el_dorado.lapis_lazuli",
                    0x7F1A3D8F
                ),
                of(
                    ModTags.Blocks.EL_DORADO_REDSTONE,
                    "item.${CrystalOdyssey.ID}.el_dorado.redstone",
                    0x7F5C0700
                ),
                of(
                    ModTags.Blocks.EL_DORADO_QUARTZ,
                    "item.${CrystalOdyssey.ID}.el_dorado.quartz",
                    0x7FB3A091
                ),
                of(
                    listOf(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.SAPPHIRE_BLOCK),
                    "item.${CrystalOdyssey.ID}.el_dorado.sapphire",
                    0x7F5793E1
                ),
                of(
                    listOf(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.RUBY_BLOCK),
                    "item.${CrystalOdyssey.ID}.el_dorado.ruby",
                    0x7FBB4052
                )
            )
        }
    }
}