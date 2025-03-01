package com.reasure.crystal_odyssey.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.client.curios.CuriosClientInitializer
import com.reasure.crystal_odyssey.client.item.properties.ModItemProperties
import com.reasure.crystal_odyssey.client.particle.LightOrbParticle
import com.reasure.crystal_odyssey.client.screen.ManaAnvilScreen
import com.reasure.crystal_odyssey.client.screen.ManaInjectorScreen
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.particle.ModParticleTypes
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(CrystalOdyssey.ID, dist = [Dist.CLIENT])
class CrystalOdysseyClient(container: ModContainer) {
    companion object {
        val LOGGER: Logger = LogManager.getLogger("${CrystalOdyssey.ID}/client")
    }

    init {
        LOGGER.info("Starting initialization of ${CrystalOdyssey.ID}.client")
    }

    @EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
    object ClientModSetupEvents {
        @SubscribeEvent
        private fun onClientSetup(event: FMLClientSetupEvent) {
            LOGGER.info("Starting clientSetup")
            ModItemProperties.register()
            if (ModList.get().isLoaded("curios")) {
                CuriosClientInitializer.registerRenderer()
            }
        }

        @SubscribeEvent
        private fun registerScreen(event: RegisterMenuScreensEvent) {
            event.register(ModMenuTypes.MANA_INJECTOR_MENU, ::ManaInjectorScreen)
            event.register(ModMenuTypes.MANA_ANVIL_MENU, ::ManaAnvilScreen)
        }

        @SubscribeEvent
        private fun registerParticleProvider(event: RegisterParticleProvidersEvent) {
            event.registerSpriteSet(ModParticleTypes.LIGHT_ORB, LightOrbParticle::Provider)
        }
    }
}