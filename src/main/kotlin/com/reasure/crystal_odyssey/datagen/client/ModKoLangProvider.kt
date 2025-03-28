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
        addCreativeModeTab("el_dorado_staff", "엘도라도 지팡이")
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
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "aquamarine", "엘도라도 지팡이 (아쿠아마린)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "opal", "엘도라도 지팡이 (오팔)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "peridot", "엘도라도 지팡이 (페리도트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "ruby", "엘도라도 지팡이 (루비)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "sapphire", "엘도라도 지팡이 (사파이어)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "topaz", "엘도라도 지팡이 (토파즈)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "aluminum", "엘도라도 지팡이 (알루미늄)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "ardite", "엘도라도 지팡이 (아다만타이트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "azure_silver", "엘도라도 지팡이 (하늘빛 은)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "bort", "엘도라도 지팡이 (보르트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "certus_quartz", "엘도라도 지팡이 (서투스 석영)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "cobalt", "엘도라도 지팡이 (코발트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "crimson_iron", "엘도라도 지팡이 (진홓빛 철)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "firestone", "엘도라도 지팡이 (파이어스톤)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "fluorite", "엘도라도 지팡이 (형석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "inferium", "엘도라도 지팡이 (인퍼륨)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "iridium", "엘도라도 지팡이 (이리듐)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "lead", "엘도라도 지팡이 (납)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "magnetite", "엘도라도 지팡이 (자철석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "meteorite", "엘도라도 지팡이 (운석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "mithril", "엘도라도 지팡이 (미스릴)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "nickel", "엘도라도 지팡이 (니켈)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "orichalcum", "엘도라도 지팡이 (오리하르콘)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "osmium", "엘도라도 지팡이 (오스뮴)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "palladium", "엘도라도 지팡이 (팔라듐)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "platinum", "엘도라도 지팡이 (백금)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "prosperity", "엘도라도 지팡이 (번영)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "salt", "엘도라도 지팡이 (소금)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "saltpeter", "엘도라도 지팡이 (초석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "silver", "엘도라도 지팡이 (은)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "soulium", "엘도라도 지팡이 (소울륨)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "sulfur", "엘도라도 지팡이 (황)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "tin", "엘도라도 지팡이 (주석)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "titanium", "엘도라도 지팡이 (티타늄)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "tungsten", "엘도라도 지팡이 (텅스텐)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "uraninite", "엘도라도 지팡이 (우라니나이트)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "uranium", "엘도라도 지팡이 (우리늄)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "zinc", "엘도라도 지팡이 (아연)")
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