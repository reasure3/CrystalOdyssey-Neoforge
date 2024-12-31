package com.reasure.crystal_odyssey.curios

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems

object CuriosInitializer {
    fun init() {
        CrystalOdyssey.LOGGER.debug("Find Curios! Start initialization for curio.")
        ModItems.GLOWSTONE_GEM_LANTERN.addIsHoldingPredicate { player ->
            CuriosHelper.isInCurios(player, ModItems.GLOWSTONE_GEM_LANTERN)
        }
    }
}
