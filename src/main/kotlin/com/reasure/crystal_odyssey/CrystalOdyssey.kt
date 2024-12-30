package com.reasure.crystal_odyssey

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.ModCreativeModTabs
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(CrystalOdyssey.ID)
object CrystalOdyssey {
    const val ID = "crystal_odyssey"

    private val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Starting initialization of $ID")

        ModItems.ITEMS.register(MOD_BUS)
        ModBlocks.BLOCKS.register(MOD_BUS)
        ModCreativeModTabs.CREATIVE_MODE_TABS.register(MOD_BUS)
    }

    fun modLoc(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(ID, path)
}