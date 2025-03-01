package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.network.chat.Component
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

/**
 * This software includes modifications based on the original source code from the project "[Create](https://github.com/Creators-of-Create/Create)" by simibubi.
 * The original project is licensed under the MIT License (MIT).
 *
 * Copyright (c) 2019 simibubi
 *
 * Modifications made:
 * - I referred to the [GoggleItem](https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/content/equipment/goggles/GogglesItem.java)
 * code to utilize [lanternGetter] for checking Curios slots only when Curios is loaded.
 * - The code has been adapted to conform to Kotlin syntax.
 * - Change Predicate -> Function
 *
 * The original license terms are retained and apply to this code. See the LICENSE file in the project root for license details.
 */

class GlowstoneGemLanternItem(block: Block, properties: Properties) : BlockItem(block, properties) {
    private val lanternGetter: MutableList<(Player) -> ItemStack> = mutableListOf(
        { player -> player.mainHandItem },
        { player -> player.offhandItem },
        { player -> player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD) },
    )

    fun addLanternGetter(getter: (Player) -> ItemStack) {
        lanternGetter.add(getter)
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        if (level.isClientSide) return
        if (entity !is Player) return
        val lantern = getLantern(entity)
        if (!lantern.isEmpty) {
            entity.addEffect(
                MobEffectInstance(ModMobEffects.LANTERN_VISION, 10, getLanternLevel(lantern), true, false)
            )
        }
    }

    private fun getLantern(player: Player): ItemStack {
        return lanternGetter.map { it(player) }
            .filter { it.`is`(ModItems.GLOWSTONE_GEM_LANTERN) }
            .maxByOrNull { getLanternLevel(it) } ?: ItemStack.EMPTY
    }

    private fun getLanternLevel(lantern: ItemStack): Int {
        return lantern.getOrDefault(ModDataComponents.LANTERN_LEVEL, 0)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        tooltipComponents.add(TranslateHelper.Tooltip.LAMP_LEVEL.translateComponent(getLanternLevel(stack)))
    }
}