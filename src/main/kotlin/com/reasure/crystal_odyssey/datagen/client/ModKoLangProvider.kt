package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import com.reasure.crystal_odyssey.util.TranslateHelper
import net.minecraft.data.PackOutput

class ModKoLangProvider(output: PackOutput) : ModBaseLangProvider(output, "ko_kr") {
    override fun addCreativeModeTabTranslations() {
        addCreativeModeTab("크리스탈 오디세이")
    }

    override fun addItemTranslations() {
        add(ModItems.GLOWSTONE_GEM, "발광 보석")
        add(ModItems.SAPPHIRE, "사파이어")
        add(ModItems.RUBY, "루비")
        add(ModItems.ENCHANTED_DIAMOND, "마법이 부여된 다이아몬드")
        add(ModItems.ENCHANTED_EMERALD, "마법이 부여된 에메랄드")
        add(ModItems.ENCHANTED_AMETHYST_SHARD, "마법이 부여된 자수정 조각")
        add(ModItems.ENCHANTED_GLOWSTONE_GEM, "마법이 부여된 발광 보석")
        add(ModItems.ENCHANTED_SAPPHIRE, "마법이 부여된 사파이어")
        add(ModItems.ENCHANTED_RUBY, "마법이 부여된 루비")
        add(ModItems.GLOWSTONE_GEM_CORE, "발광 보석 핵")
        add(ModItems.LIGHT_STAFF, "빛의 지팡이")
        add(ModItems.EL_DORADO_STAFF, "엘도라도 지팡이")
        add(ModItems.EL_DORADO_STAFF_ACTIVE, "엘도라도 지팡이")
        add(ModItems.SAPPHIRE_BUCKET, "사파이어 양동이")
        add(ModItems.INFINITE_SAPPHIRE_BUCKET, "무한의 사파이어 양동이")
        add(ModItems.RUBY_BUCKET, "루비 양동이")
        add(ModItems.INFINITE_RUBY_BUCKET, "무한의 루비 양동이")
    }

    override fun addItemSubtypeTranslations() {
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "coal", "엘도라도 지팡이 (석탄)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "iron", "엘도라도 지팡이 (철)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "copper", "엘도라도 지팡이 (구리)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "gold", "엘도라도 지팡이 (금)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "redstone", "엘도라도 지팡이 (레드스톤)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "emerald", "엘도라도 지팡이 (에메랄드)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "lapis", "엘도라도 지팡이 (청금석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "diamond", "엘도라도 지팡이 (다이아몬드)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "quartz", "엘도라도 지팡이 (석영)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "netherite", "엘도라도 지팡이 (네더라이트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "amethyst", "엘도라도 지팡이 (자수정)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "sapphire", "엘도라도 지팡이 (사파이어)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "ruby", "엘도라도 지팡이 (루비)")
    }

    override fun addBlockTranslations() {
        add(ModBlocks.SAPPHIRE_ORE, "사파이어 광석")
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, "심층암 사파이어 광석")
        add(ModBlocks.RUBY_ORE, "루비 광석")
        add(ModBlocks.DEEPSLATE_RUBY_ORE, "심층암 루비 광석")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "발광 보석 블록")
        add(ModBlocks.SAPPHIRE_BLOCK, "사파이어 블록")
        add(ModBlocks.RUBY_BLOCK, "루비 블록")
        add(ModBlocks.MANA_INJECTOR, "마나 주입기")
        add(ModBlocks.MANA_ANVIL, "마나 모루")
        add(ModBlocks.GLOWSTONE_GEM_LANTERN, "발광 보석 랜턴")
        add(ModBlocks.LIGHT_ORB_BLOCK, "빛 오브")
    }

    override fun addEffectTranslations() {
        addEffect(ModMobEffects.LANTERN_VISION, "랜턴 불빛")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "랜턴 불빛의 화살")
        addPotion(ModMobEffects.LANTERN_VISION, "랜턴 불및의 물약")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "투척용 랜턴 불빛의 물약")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "잔류형 랜턴 불빛의 물약")
    }

    override fun addTooltipTranslations() {
        add(TranslateHelper.Tooltip.LAMP_LEVEL, "§e§l불빛 강도:§r %1\$s")
        add(TranslateHelper.Tooltip.Crouch.PICKUP_FLUID, "%1\$s을 담으려면 웅크리기")
        add(TranslateHelper.Tooltip.Shift.MB_CAPACITY, "§eSHIFT§r를 눌러 mB 단위로 보기")
        add(TranslateHelper.Tooltip.Shift.BLOCK_LIST, "§eSHIFT§r를 눌러 블록 목록 보기")
    }

    override fun addGuiTranslations() {
        add(TranslateHelper.Container.MANA_INJECTOR, "마나 주입기")
        add(TranslateHelper.Container.MANA_ANVIL, "마나 모루")

        add(TranslateHelper.Jei.CATEGORY_MANA_INJECTING, "마나 주입")
        add(TranslateHelper.Jei.CATEGORY_MANA_ANVIL, "마나 모루")
    }

    override fun addConfigTranslations() {
        addConfig("title", "크리스탈 오디세이 설정")
        addConfig("section.crystal.odyssey.server.toml", "서버 설정 수정하기")
        addConfig("section.crystal.odyssey.server.toml.title", "서버 설정")

        addConfig("ElDoradoStaff", "엘도라도 지팡이")
        addConfig("ElDoradoStaff.button", "수정")
        addConfig("ElDoradoStaff.tooltip", "엘도라도 지팡이 스탯 수정")
        addConfig("ElDoradoStaff.Range", "탐지 범위")
        addConfigDesc(
            "ElDoradoStaff.Range.tooltip",
            "엘도라도 지팡이 광물 탐지 범위입니다.",
            "만약 이 값이 10이라면, 플레이어 주위로 10칸, 즉 21 x 21 x 21 범위의 광물을 탐지합니다."
        )
        addConfig("ElDoradoStaff.RetainTick", "지속 시간 (틱 단위)")
        addConfigDesc(
            "ElDoradoStaff.RetainTick.tooltip",
            "광물이 감지됐을 때, 블록 테두리가 유지되는 시간입니다.",
            "만약, 지속 시간이 끝나기 전에 광물을 다시 탐지하면 이전에 탐지된 블록의 테두리는 사라집니다."
        )

        addJadeConfig("glowstone_gem_lantern", "발광 보석 랜턴 강도")
    }

    override fun addTagTranslations() {
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "빛의 지팡이 수리 재료들")
        add(ModTags.Items.EL_DORADO_STAFF_REPAIRABLE, "엘도라도 지팡이 수리 재료들")
    }
}