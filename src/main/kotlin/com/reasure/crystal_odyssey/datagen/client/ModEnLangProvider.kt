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

        add(ModItems.ENCHANTED_DIAMOND, "Enchanted Diamond")
        add(ModItems.ENCHANTED_EMERALD, "Enchanted Emerald")
        add(ModItems.ENCHANTED_AMETHYST_SHARD, "Enchanted Amethyst Shard")
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
        add(ModBlocks.MANA_ANVIL, "Mana Anvil")
        add(ModBlocks.LIGHT_ORB_BLOCK, "Light Orb")
        add(ModItems.SAPPHIRE_BUCKET, "Sapphire Bucket")
        add(ModItems.INFINITE_SAPPHIRE_BUCKET, "Infinite Sapphire Bucket")
        add(ModItems.RUBY_BUCKET, "Ruby Bucket")
        add(ModItems.INFINITE_RUBY_BUCKET, "Infinite Ruby Bucket")
        addEffect(ModMobEffects.LANTERN_VISION, "Lantern Vision")
        addTippedArrow(ModMobEffects.LANTERN_VISION, "Arrow of Lantern Vision")
        addPotion(ModMobEffects.LANTERN_VISION, "Potion of Lantern Vision")
        addSplashPotion(ModMobEffects.LANTERN_VISION, "Splash Potion of Lantern Vision")
        addLingeringPotion(ModMobEffects.LANTERN_VISION, "Lingering Potion of Lantern Vision")
        addTooltip("lamp_level", "§eLight Level:§r %1\$s")
        addTooltip("crouch.pickup_fluid", "Crouch to pickup %1\$s")
        addTooltip("press_shift.mb_capacity", "Press §eSHIFT§r to see in mB")
        addContainer("mana_injector", "Mana Injector")
        addContainer("mana_anvil", "Mana Anvil")
        addJeiCategory("mana_injecting", "Mana Injecting")
        addJeiCategory("mana_anvil", "Mana Anvil")
        addJadeConfig("glowstone_gem_lantern", "Glowstone Gem Lantern Level")
        add(ModTags.Items.LIGHT_STAFF_REPAIRABLE, "Repair Items of Staff of Light")
    }
}