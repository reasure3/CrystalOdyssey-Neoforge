package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.effect.ModMobEffects
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import java.util.function.Predicate

class GlowstoneGemLanternItem(block: Block, properties: Properties) : BlockItem(block, properties) {
    private val isHoldingPredicates: MutableList<Predicate<Player>> = mutableListOf()

    init {
        addIsHoldingPredicate { player -> player.mainHandItem.`is`(this) }
        addIsHoldingPredicate { player -> player.offhandItem.`is`(this) }
        addIsHoldingPredicate { player ->
            player.getItemBySlot(EquipmentSlot.HEAD).`is`(this)
        }
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (level.isClientSide) return
        if (entity is Player && isHoldingLantern(entity)) {
            entity.addEffect(MobEffectInstance(ModMobEffects.LANTERN_VISION, 10, 0, true, false))
        }
    }

    private fun isHoldingLantern(player: Player): Boolean {
        return isHoldingPredicates.any { predicate -> predicate.test(player) }
    }

    fun addIsHoldingPredicate(predicate: (Player) -> Boolean) {
        isHoldingPredicates.add(predicate)
    }
}