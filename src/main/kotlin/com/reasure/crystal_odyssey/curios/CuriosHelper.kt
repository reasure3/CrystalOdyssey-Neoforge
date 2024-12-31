package com.reasure.crystal_odyssey.curios

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import top.theillusivec4.curios.api.CuriosApi
import kotlin.jvm.optionals.getOrNull

object CuriosHelper {

    fun isInCurios(livingEntity: LivingEntity, item: Item): Boolean {
        return CuriosApi.getCuriosInventory(livingEntity).getOrNull()?.findFirstCurio(item)?.isPresent ?: false
    }
}