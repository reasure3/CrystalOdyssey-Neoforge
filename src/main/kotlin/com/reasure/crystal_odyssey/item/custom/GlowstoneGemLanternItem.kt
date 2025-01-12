package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import java.util.function.Predicate

/**
 * This software includes modifications based on the original source code from the project "[Create](https://github.com/Creators-of-Create/Create)" by simibubi.
 * The original project is licensed under the MIT License (MIT).
 *
 * Copyright (c) 2019 simibubi
 *
 * Modifications made:
 * - I referred to the [GoggleItem](https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/content/equipment/goggles/GogglesItem.java)
 * code to utilize [isHoldingPredicates] for checking Curios slots only when Curios is loaded.
 * - The code has been adapted to conform to Kotlin syntax.
 *
 * The original license terms are retained and apply to this code. See the LICENSE file in the project root for license details.
 */

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
            entity.addEffect(MobEffectInstance(ModMobEffects.LANTERN_VISION, 10, getLanternLevel(stack), true, false))
        }
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        tooltipComponents.add(Component.translatable(LAMP_LEVEL_KEY, getLanternLevel(stack) + 1))
    }

    private fun isHoldingLantern(player: Player): Boolean {
        return isHoldingPredicates.any { predicate -> predicate.test(player) }
    }

    private fun getLanternLevel(lantern: ItemStack): Int {
        return lantern.getOrDefault(ModDataComponents.LANTERN_LEVEL, 0)
    }

    fun addIsHoldingPredicate(predicate: (Player) -> Boolean) {
        isHoldingPredicates.add(predicate)
    }

    companion object {
        const val LAMP_LEVEL_KEY = "item.${CrystalOdyssey.ID}.tooltip.lamp_level"
    }
}