package com.reasure.crystal_odyssey.compat.curios

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.CuriosApi
import kotlin.jvm.optionals.getOrNull

object CuriosHelper {
    fun findFirst(livingEntity: LivingEntity, item: Item): ItemStack {
        return CuriosApi.getCuriosInventory(livingEntity).getOrNull()?.findFirstCurio(item)?.get()?.stack
            ?: ItemStack.EMPTY
    }
}