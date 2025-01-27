package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.data.PackOutput

class ModKoLangProvider(output: PackOutput) : ModBaseLangProvider(output, "ko_kr") {
    override fun addTranslations() {
        addCreativeModeTab("크리스탈 오디세이")

        add(ModItems.ENCHANTED_DIAMOND, "마법이 부여된 다이아몬드")
        add(ModItems.ENCHANTED_EMERALD, "마법이 부여된 에메랄드")
        add(ModItems.ENCHANTED_AMETHYST_SHARD, "마법이 부여된 자수정 조각")
        add(ModItems.GLOWSTONE_GEM, "발광 보석")
        add(ModItems.ENCHANTED_GLOWSTONE_GEM, "마법이 부여된 발광 보석")
        add(ModItems.GLOWSTONE_GEM_CORE, "발광 보석 핵")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "발광 보석 블록")
        add(ModItems.RUBY, "루비")
        add(ModItems.ENCHANTED_RUBY, "마법이 부여된 루비")
        add(ModBlocks.RUBY_BLOCK, "루비 블록")
        add(ModBlocks.RUBY_ORE, "루비 광석")
        add(ModBlocks.DEEPSLATE_RUBY_ORE, "심층암 루비 광석")
        add(ModItems.SAPPHIRE, "사파이어")
        add(ModItems.ENCHANTED_SAPPHIRE, "마법이 부여된 사파이어")
        add(ModBlocks.SAPPHIRE_BLOCK, "사파이어 블록")
        add(ModBlocks.SAPPHIRE_ORE, "사파이어 광석")
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, "심층암 사파이어 광석")
        add(ModBlocks.GLOWSTONE_GEM_LANTERN, "발광 보석 랜턴")
        add(ModItems.LIGHT_STAFF, "빛의 지팡이")
        add(ModItems.EL_DORADO_STAFF, "엘도라도 지팡이")
        add(ModItems.EL_DORADO_STAFF_ACTIVE, "엘도라도 지팡이")
        add(ModBlocks.MANA_INJECTOR, "마나 주입기")
        add(ModBlocks.MANA_ANVIL, "마나 모루")
        add(ModBlocks.LIGHT_ORB_BLOCK, "빛 오브")
        add(ModItems.SAPPHIRE_BUCKET, "사파이어 양동이")
        add(ModItems.INFINITE_SAPPHIRE_BUCKET, "무한의 사파이어 양동이")
        add(ModItems.RUBY_BUCKET, "루비 양동이")
        add(ModItems.INFINITE_RUBY_BUCKET, "무한의 루비 양동이")
        addEffect(ModMobEffects.LANTERN_VISION, "랜턴 불빛")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "랜턴 불빛의 화살")
        addPotion(ModMobEffects.LANTERN_VISION, "랜턴 불및의 물약")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "투척용 랜턴 불빛의 물약")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "잔류형 랜턴 불빛의 물약")
        addTooltip("lamp_level", "§e§l불빛 강도:§r %1\$s")
        addTooltip("crouch.pickup_fluid", "%1\$s을 담으려면 웅크리기")
        addTooltip("press_shift.mb_capacity", "§eSHIFT§r를 눌러 mB 단위로 보기")
        addTooltip("press_shift.block_list", "§eSHIFT§r를 눌러 블록 목록 보기")
        addContainer("mana_injector", "마나 주입기")
        addContainer("mana_anvil", "마나 모루")
        addJeiCategory("mana_injecting", "마나 주입")
        addJeiCategory("mana_anvil", "마나 모루")
        addJadeConfig("glowstone_gem_lantern", "발광 보석 랜턴 강도")
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "빛의 지팡이 수리 재료들")
        add("item.${CrystalOdyssey.ID}.el_dorado.coal", "석탄")
        add("item.${CrystalOdyssey.ID}.el_dorado.copper", "구리")
        add("item.${CrystalOdyssey.ID}.el_dorado.iron", "철")
        add("item.${CrystalOdyssey.ID}.el_dorado.gold", "금")
        add("item.${CrystalOdyssey.ID}.el_dorado.diamond", "다이아몬드")
        add("item.${CrystalOdyssey.ID}.el_dorado.netherite", "네더라이트")
        add("item.${CrystalOdyssey.ID}.el_dorado.lapis_lazuli", "청금석")
        add("item.${CrystalOdyssey.ID}.el_dorado.redstone", "레드스톤")
        add("item.${CrystalOdyssey.ID}.el_dorado.quartz", "석영")
        add("item.${CrystalOdyssey.ID}.el_dorado.emerald", "에메랄드")
        add("item.${CrystalOdyssey.ID}.el_dorado_staff_active.cover_name", "엘도라도 지팡이 (%1\$s)")
    }
}