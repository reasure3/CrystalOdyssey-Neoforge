package com.reasure.crystal_odyssey

import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(CrystalOdyssey.ID)
object CrystalOdyssey {
    const val ID = "crystal_odyssey"

    private val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.info("Starting initialization of $ID")
    }
}