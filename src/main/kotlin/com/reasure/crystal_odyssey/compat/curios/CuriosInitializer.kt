package com.reasure.crystal_odyssey.compat.curios

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems

/**
 * This software includes modifications based on the original source code from the project "[Create](https://github.com/Creators-of-Create/Create)" by simibubi.
 * The original project is licensed under the MIT License (MIT).
 *
 * Copyright (c) 2019 simibubi
 *
 * Modifications made:
 * - I referred to the [Curios in Create](https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/compat/curios/Curios.java#L45)
 * code to implement the initialization logic for Curios, ensuring it runs only when Curios is loaded.
 * - The code has been adapted to conform to Kotlin syntax.
 * - The initialization has been moved to the FMLCommonSetupEvent instead of using modEventBus and forgeEventBus.
 * - The item inspection logic has been handled using my utility class.
 *
 * The original license terms are retained and apply to this code. See the LICENSE file in the project root for license details.
 */

object CuriosInitializer {
    fun init() {
        CrystalOdyssey.LOGGER.debug("Find Curios! Start initialization for curio.")
        ModItems.GLOWSTONE_GEM_LANTERN.addLanternGetter { player ->
            CuriosHelper.findFirst(player, ModItems.GLOWSTONE_GEM_LANTERN)
        }
    }
}
