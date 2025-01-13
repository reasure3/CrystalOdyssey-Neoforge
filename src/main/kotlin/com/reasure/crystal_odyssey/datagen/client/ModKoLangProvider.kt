package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.data.PackOutput

class ModKoLangProvider(output: PackOutput) : ModBaseLangProvider(output, "ko_kr") {
    override fun addTranslations() {
        addCreativeModeTab("크리스탈 오디세이")

        add(ModItems.GLOWSTONE_GEM, "발광 보석")
        add(ModItems.ENCHANTED_GLOWSTONE_GEM, "마법이 부여된 발광 보석")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "발광 보석 블록")
        add(ModBlocks.GLOWSTONE_GEM_LANTERN, "발광 보석 랜턴")
        add(ModItems.LIGHT_STAFF, "빛의 지팡이")
        add(ModBlocks.MANA_INJECTOR, "마나 주입기")
        add(ModBlocks.LIGHT_ORB_BLOCK, "빛 오브")
        addEffect(ModMobEffects.LANTERN_VISION, "랜턴 불빛")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "랜턴 불빛의 화살")
        addPotion(ModMobEffects.LANTERN_VISION, "랜턴 불및의 물약")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "투척용 랜턴 불빛의 물약")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "잔류형 랜턴 불빛의 물약")
        addTooltip("lamp_level", "§e§l불빛 강도:§r %1\$s")
        addContainer("mana_injector", "마나 주입기")
        addJeiCategory("mana_injecting", "마나 주입")
        addJadeConfig("glowstone_gem_lantern", "발광 보석 랜턴 강도")
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "빛의 지팡이 수리 재료들")
    }
}