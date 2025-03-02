package com.reasure.crystal_odyssey.client.network

import com.reasure.crystal_odyssey.client.util.BlockFinder
import com.reasure.crystal_odyssey.network.BlockBreakPayload
import com.reasure.crystal_odyssey.network.ChangeDimensionPayload
import net.neoforged.neoforge.network.handling.IPayloadContext

object ClientPayloadHandler {
    fun handle(data: BlockBreakPayload, context: IPayloadContext) {
        BlockFinder.removePos(data.pos)
    }

    fun handle(data: ChangeDimensionPayload, context: IPayloadContext) {
        BlockFinder.clearFind()
    }
}