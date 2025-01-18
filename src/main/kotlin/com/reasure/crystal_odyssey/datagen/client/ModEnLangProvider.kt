package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.effect.ModMobEffects
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.data.PackOutput

class ModEnLangProvider(output: PackOutput) : ModBaseLangProvider(output, "en_us") {
    override fun addTranslations() {
        addCreativeModeTab("Crystal Odyssey")

        add(ModItems.GLOWSTONE_GEM, "Glowstone Gem")
        add(ModItems.ENCHANTED_GLOWSTONE_GEM, "Enchanted Glowstone Gem")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "Block of Glowstone Gem")
        add(ModItems.RUBY, "Ruby")
        add(ModItems.ENCHANTED_RUBY, "Enchanted Ruby")
        add(ModBlocks.RUBY_BLOCK, "Block of Ruby")
        add(ModBlocks.RUBY_ORE, "Ruby Ore")
        add(ModBlocks.DEEPSLATE_RUBY_ORE, "Deepslate Ruby Ore")
        add(ModItems.SAPPHIRE, "Sapphire")
        add(ModItems.ENCHANTED_SAPPHIRE, "Enchanted Sapphire")
        add(ModBlocks.SAPPHIRE_BLOCK, "Block of Sapphire")
        add(ModBlocks.SAPPHIRE_ORE, "Sapphire Ore")
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, "Deepslate Sapphire Ore")
        add(ModBlocks.GLOWSTONE_GEM_LANTERN, "Glowstone Gem Lantern")
        add(ModItems.LIGHT_STAFF, "Staff of Light")
        add(ModBlocks.MANA_INJECTOR, "Mana Injector")
        add(ModBlocks.LIGHT_ORB_BLOCK, "Light Orb")
        addEffect(ModMobEffects.LANTERN_VISION, "Lantern Vision")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "Arrow of Lantern Vision")
        addPotion(ModMobEffects.LANTERN_VISION, "Potion of Lantern Vision")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "Splash Potion of Lantern Vision")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "Lingering Potion of Lantern Vision")
        addTooltip("lamp_level", "§eLight Level:§r %1\$s")
        addContainer("mana_injector", "Mana Injector")
        addJeiCategory("mana_injecting", "Mana Injecting")
        addJadeConfig("glowstone_gem_lantern", "Glowstone Gem Lantern Level")
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "Repair Items of Staff of Light")
    }
}