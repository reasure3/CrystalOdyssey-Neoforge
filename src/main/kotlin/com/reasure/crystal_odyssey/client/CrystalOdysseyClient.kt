package com.reasure.crystal_odyssey.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.client.curios.CuriosClientInitializer
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModList
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(CrystalOdyssey.ID, dist = [Dist.CLIENT])
@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object CrystalOdysseyClient {
    val LOGGER: Logger = LogManager.getLogger("${CrystalOdyssey.ID}/client")

    init {
        LOGGER.info("Starting initialization of ${CrystalOdyssey.ID}.client")
    }

    @SubscribeEvent
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Starting clientSetup")
        if (ModList.get().isLoaded("curios")) {
            CuriosClientInitializer.registerRenderer()
        }
    }
}