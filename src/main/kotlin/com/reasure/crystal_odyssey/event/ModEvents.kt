package com.reasure.crystal_odyssey.event

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.custom.GlowstoneGemLanternItem
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.tick.PlayerTickEvent

@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.GAME)
object ModEvents {
    @SubscribeEvent
    fun onTick(event: PlayerTickEvent.Pre) {
        GlowstoneGemLanternItem.onTick(event.entity.level(), event.entity)
    }
}