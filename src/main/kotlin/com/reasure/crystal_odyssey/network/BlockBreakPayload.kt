package com.reasure.crystal_odyssey.network

import com.reasure.crystal_odyssey.CrystalOdyssey
import io.netty.buffer.ByteBuf
import net.minecraft.core.BlockPos
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

data class BlockBreakPayload(val pos: BlockPos) : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<BlockBreakPayload> = TYPE

    companion object {
        val TYPE: CustomPacketPayload.Type<BlockBreakPayload> =
            CustomPacketPayload.Type(CrystalOdyssey.modLoc("block_break"))

        val STEAM_CODEC: StreamCodec<ByteBuf, BlockBreakPayload> = StreamCodec.composite(
            BlockPos.STREAM_CODEC, BlockBreakPayload::pos, ::BlockBreakPayload
        )
    }
}