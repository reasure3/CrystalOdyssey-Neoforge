package com.reasure.crystal_odyssey

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.compat.curios.CuriosInitializer
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.inventory.menu.ModMenuTypes
import com.reasure.crystal_odyssey.item.ModCreativeModTabs
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.particle.ModParticleTypes
import com.reasure.crystal_odyssey.recipe.ModRecipeSerializers
import com.reasure.crystal_odyssey.recipe.ModRecipeTypes
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.ModContainer
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(CrystalOdyssey.ID)
class CrystalOdyssey(container: ModContainer) {
    companion object {
        const val ID = "crystal_odyssey"

        val LOGGER: Logger = LogManager.getLogger(ID)

        fun modLoc(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(ID, path)
    }

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
        ModParticleTypes.PARTICLE_TYPES.register(MOD_BUS)

        container.registerConfig(ModConfig.Type.SERVER, CrystalOdysseyServerConfig.SPEC)

        if (ModList.get().isLoaded("curios")) {
            CuriosInitializer.init()
        }
    }
}