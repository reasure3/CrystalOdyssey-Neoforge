package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import com.reasure.crystal_odyssey.util.TranslateHelper
import net.minecraft.data.PackOutput

class ModEnLangProvider(output: PackOutput) : ModBaseLangProvider(output, "en_us") {
    override fun addCreativeModeTabTranslations() {
        addCreativeModeTab("Crystal Odyssey")
    }

    override fun addItemTranslations() {
        add(ModItems.GLOWSTONE_GEM, "Glowstone Gem")
        add(ModItems.SAPPHIRE, "Sapphire")
        add(ModItems.RUBY, "Ruby")
        add(ModItems.ENCHANTED_DIAMOND, "Enchanted Diamond")
        add(ModItems.ENCHANTED_EMERALD, "Enchanted Emerald")
        add(ModItems.ENCHANTED_AMETHYST_SHARD, "Enchanted Amethyst Shard")
        add(ModItems.ENCHANTED_GLOWSTONE_GEM, "Enchanted Glowstone Gem")
        add(ModItems.ENCHANTED_SAPPHIRE, "Enchanted Sapphire")
        add(ModItems.ENCHANTED_RUBY, "Enchanted Ruby")
        add(ModItems.GLOWSTONE_GEM_CORE, "Core of Glowstone Gem")
        add(ModItems.LIGHT_STAFF, "Staff of Light")
        add(ModItems.EL_DORADO_STAFF, "El Dorado Staff")
        add(ModItems.EL_DORADO_STAFF_ACTIVE, "El Dorado Staff")
        add(ModItems.SAPPHIRE_BUCKET, "Sapphire Bucket")
        add(ModItems.INFINITE_SAPPHIRE_BUCKET, "Infinite Sapphire Bucket")
        add(ModItems.RUBY_BUCKET, "Ruby Bucket")
        add(ModItems.INFINITE_RUBY_BUCKET, "Infinite Ruby Bucket")
    }

    override fun addItemSubtypeTranslations() {
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "coal", "El Dorado Staff (Coal)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "copper", "El Dorado Staff (Copper)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "iron", "El Dorado Staff (Iron)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "gold", "El Dorado Staff (Gold)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "diamond", "El Dorado Staff (Diamond)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "netherite", "El Dorado Staff (Netherite)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "lapis_lazuli", "El Dorado Staff (Lapis Lazuli)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "redstone", "El Dorado Staff (Redstone)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "quartz", "El Dorado Staff (Quartz)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "emerald", "El Dorado Staff (Emerald)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "sapphire", "El Dorado Staff (Sapphire)")
        addItemSubtype(ModItems.EL_DORADO_STAFF_ACTIVE, "ruby", "El Dorado Staff (Ruby)")
    }

    override fun addBlockTranslations() {
        add(ModBlocks.SAPPHIRE_ORE, "Sapphire Ore")
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore")
        add(ModBlocks.RUBY_ORE, "Ruby Ore")
        add(ModBlocks.DEEPSLATE_RUBY_ORE, "Deepslate Ruby Ore")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "Block of Glowstone Gem")
        add(ModBlocks.SAPPHIRE_BLOCK, "Block of Sapphire")
        add(ModBlocks.RUBY_BLOCK, "Block of Ruby")
        add(ModBlocks.GLOWSTONE_GEM_LANTERN, "Glowstone Gem Lantern")
        add(ModBlocks.MANA_INJECTOR, "Mana Injector")
        add(ModBlocks.MANA_ANVIL, "Mana Anvil")
        add(ModBlocks.LIGHT_ORB_BLOCK, "Light Orb")
    }

    override fun addEffectTranslations() {
        addEffect(ModMobEffects.LANTERN_VISION, "Lantern Vision")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "Arrow of Lantern Vision")
        addPotion(ModMobEffects.LANTERN_VISION, "Potion of Lantern Vision")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "Splash Potion of Lantern Vision")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "Lingering Potion of Lantern Vision")
    }

    override fun addTooltipTranslations() {
        add(TranslateHelper.Tooltip.LAMP_LEVEL, "§eLight Level:§r %1\$s")
        add(TranslateHelper.Tooltip.Crouch.PICKUP_FLUID, "Crouch to pickup %1\$s")
        add(TranslateHelper.Tooltip.Shift.MB_CAPACITY, "Press §eSHIFT§r to see in mB")
        add(TranslateHelper.Tooltip.Shift.BLOCK_LIST, "Press §eSHIFT§r to see block list")
    }

    override fun addGuiTranslations() {
        add(TranslateHelper.Container.MANA_INJECTOR, "Mana Injector")
        add(TranslateHelper.Container.MANA_ANVIL, "Mana Anvil")

        add(TranslateHelper.Jei.CATEGORY_MANA_INJECTING, "Mana Injecting")
        add(TranslateHelper.Jei.CATEGORY_MANA_ANVIL, "Mana Anvil")
    }

    override fun addConfigTranslations() {
        addConfig("title", "Crystal Odyssey Configuration")
        addConfig("section.crystal.odyssey.server.toml", "Modify Server Configuration")
        addConfig("section.crystal.odyssey.server.toml.title", "Server Configuration")

        addConfig("ElDoradoStaff", "El Dorado Staff")
        addConfig("ElDoradoStaff.button", "Modify")
        addConfig("ElDoradoStaff.tooltip", "Modify El Dorado Staff Stat")
        addConfig("ElDoradoStaff.Range", "Detection Range")
        addConfigDesc(
            "ElDoradoStaff.Range.tooltip",
            "This is the mineral detection range of the El Dorado Staff.",
            "If this value is 10, it will detect an area of 10 squares around the player, or a total of 21 x 21 x 21."
        )
        addConfig("ElDoradoStaff.RetainTick", "Retain Tick")
        addConfigDesc(
            "ElDoradoStaff.RetainTick.tooltip",
            "This is the duration (in ticks) for which the mineral detection border is visible.",
            "This value determines how long the border will remain on the screen after detecting mineral.",
            "If the player detects mineral again before the duration expires, the previous border will disappear."
        )

        addJadeConfig("glowstone_gem_lantern", "Glowstone Gem Lantern Level")
    }

    override fun addTagTranslations() {
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "Repair Items of Staff of Light")
        add(ModTags.Items.EL_DORADO_STAFF_REPAIRABLE, "Repair Items of El Dorado Staff")
    }
}