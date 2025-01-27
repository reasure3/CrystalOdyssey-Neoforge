package com.reasure.crystal_odyssey.compat.curios

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.CuriosApi
import kotlin.jvm.optionals.getOrNull

object CuriosHelper {
    fun findFirst(livingEntity: LivingEntity, item: Item): ItemStack {
        val inventory = CuriosApi.getCuriosInventory(livingEntity).getOrNull() ?: return ItemStack.EMPTY
        val result = inventory.findFirstCurio(item)?.getOrNull() ?: return ItemStack.EMPTY
        return result.stack
    }
}