package com.reasure.crystal_odyssey.network

import com.reasure.crystal_odyssey.CrystalOdyssey
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

object ChangeDimensionPayload : CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<ChangeDimensionPayload> = TYPE

    val TYPE: CustomPacketPayload.Type<ChangeDimensionPayload> =
        CustomPacketPayload.Type(CrystalOdyssey.modLoc("change_dimension"))

    val STREAM_CODEC: StreamCodec<ByteBuf, ChangeDimensionPayload> = StreamCodec.unit(ChangeDimensionPayload)
}