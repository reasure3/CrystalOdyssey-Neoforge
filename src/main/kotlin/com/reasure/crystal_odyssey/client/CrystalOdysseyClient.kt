package com.reasure.crystal_odyssey.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.client.curios.GlowstoneGemLanternCuriosRenderer
import com.reasure.crystal_odyssey.item.ModItems
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModList
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import top.theillusivec4.curios.api.client.CuriosRendererRegistry

@Mod(CrystalOdyssey.ID, dist = [Dist.CLIENT])
@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object CrystalOdysseyClient {
    private val LOGGER: Logger = LogManager.getLogger("${CrystalOdyssey.ID}/client")

    init {
        LOGGER.info("Starting initialization of ${CrystalOdyssey.ID}.client")
    }

    @SubscribeEvent
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Starting clientSetup")
        if (ModList.get().isLoaded("curios")) {
            LOGGER.info("Find Curios! Starting register Curios Renderer")
            CuriosRendererRegistry.register(ModItems.GLOWSTONE_GEM_LANTERN, ::GlowstoneGemLanternCuriosRenderer)
        }
    }
}