package com.reasure.crystal_odyssey

import com.reasure.crystal_odyssey.network.BlockBreakPayload
import com.reasure.crystal_odyssey.network.ChangeDimensionPayload
import net.minecraft.server.level.ServerPlayer
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import net.neoforged.neoforge.event.level.BlockEvent
import net.neoforged.neoforge.network.PacketDistributor

@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.GAME)
object CrystalOdysseyEvents {
    @SubscribeEvent
    fun onBreak(event: BlockEvent.BreakEvent) {
        PacketDistributor.sendToPlayer(event.player as ServerPlayer, BlockBreakPayload(event.pos))
    }

    @SubscribeEvent
    fun onChangeDimension(event: PlayerEvent.PlayerChangedDimensionEvent) {
        PacketDistributor.sendToPlayer(event.entity as ServerPlayer, ChangeDimensionPayload)
    }
}