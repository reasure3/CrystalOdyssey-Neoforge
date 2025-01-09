package com.reasure.crystal_odyssey

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.compat.curios.CuriosInitializer
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.item.ModCreativeModTabs
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModList
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(CrystalOdyssey.ID)
@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD)
object CrystalOdyssey {
    const val ID = "crystal_odyssey"

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Starting initialization of $ID")

        ModItems.ITEMS.register(MOD_BUS)
        ModBlocks.BLOCKS.register(MOD_BUS)
        ModCreativeModTabs.CREATIVE_MODE_TABS.register(MOD_BUS)
        ModMobEffects.MOB_EFFECTS.register(MOD_BUS)
        ModMenuTypes.MENUS.register(MOD_BUS)
        ModDataComponents.DATA_COMPONENTS.register(MOD_BUS)
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(MOD_BUS)
        ModRecipeTypes.RECIPE_TYPES.register(MOD_BUS)
    }

    fun modLoc(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(ID, path)

    @SubscribeEvent
    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info("Starting commonSetup")
        if (ModList.get().isLoaded("curios")) {
            CuriosInitializer.init()
        }
    }
}