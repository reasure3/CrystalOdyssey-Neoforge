package com.reasure.crystal_odyssey.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.client.render.FoundBlockBorderRenderer
import com.reasure.crystal_odyssey.client.util.BlockFinder
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent

@EventBusSubscriber(value = [Dist.CLIENT], modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.GAME)
object ModClientEvents {
    @SubscribeEvent
    fun onLoggedIn(event: ClientPlayerNetworkEvent.LoggingIn) {
        BlockFinder.clearFind()
    }

    @SubscribeEvent
    fun onLoggedOut(event: ClientPlayerNetworkEvent.LoggingOut) {
        BlockFinder.clearFind()
    }

    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        BlockFinder.onClientTick()
    }

    @SubscribeEvent
    fun onRenderLevel(event: RenderLevelStageEvent) {
        if (BlockFinder.renderBlock && event.stage == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            FoundBlockBorderRenderer.render(event.camera)
        }
    }
}