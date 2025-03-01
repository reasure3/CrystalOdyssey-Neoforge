package com.reasure.crystal_odyssey.util

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent

object TranslateHelper {
    object Tooltip {
        const val LAMP_LEVEL = "item.${CrystalOdyssey.ID}.tooltip.lamp_level"

        object Crouch {
            const val PICKUP_FLUID = "item.${CrystalOdyssey.ID}.tooltip.crouch.pickup_fluid"
        }

        object Shift {
            const val MB_CAPACITY = "item.${CrystalOdyssey.ID}.tooltip.press_shift.mb_capacity"
            const val BLOCK_LIST = "item.${CrystalOdyssey.ID}.tooltip.press_shift.block_list"
        }
    }

    object Container {
        const val MANA_INJECTOR = "container.${CrystalOdyssey.ID}.mana_injector"
        const val MANA_ANVIL = "container.${CrystalOdyssey.ID}.mana_anvil"
    }

    object Jei {
        const val CATEGORY_MANA_INJECTING = "jei.category.${CrystalOdyssey.ID}.mana_injecting"
        const val CATEGORY_MANA_ANVIL = "jei.category.${CrystalOdyssey.ID}.mana_anvil"
    }

    fun String.translateComponent(vararg args: Any): MutableComponent = Component.translatable(this, *args)
}