package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.data.PackOutput
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.item.Item
import net.neoforged.neoforge.common.data.LanguageProvider
import java.util.function.Supplier

abstract class ModBaseLangProvider(output: PackOutput, locale: String) :
    LanguageProvider(output, CrystalOdyssey.ID, locale) {

    fun addCreativeModeTab(name: String) = add("itemGroup.${CrystalOdyssey.ID}", name)

    fun addCreativeModeTab(key: String, name: String) = add("itemGroup.${CrystalOdyssey.ID}.$key", name)

    fun addItemSubtype(item: Item, key: String, name: String) = add("${item.descriptionId}.$key", name)

    fun <T : MobEffect> addTippedArrow(key: Supplier<T>, name: String) =
        add("item.minecraft.tipped_arrow.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.potion.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addSplashPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.splash_potion.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addLingeringPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.lingering_potion.effect.${key.get().descriptionId}", name)

    fun addConfig(key: String, name: String) =
        add("${CrystalOdyssey.ID}.configuration.$key", name)

    fun addConfigDesc(key: String, vararg desc: String) =
        add("${CrystalOdyssey.ID}.configuration.$key", desc.joinToString(" "))

    fun addJadeConfig(key: String, name: String) = add("config.jade.plugin_${CrystalOdyssey.ID}.$key", name)


    override fun addTranslations() {
        addCreativeModeTabTranslations()
        addItemTranslations()
        addItemSubtypeTranslations()
        addBlockTranslations()
        addEffectTranslations()
        addTooltipTranslations()
        addGuiTranslations()
        addConfigTranslations()
        addTagTranslations()
    }

    abstract fun addCreativeModeTabTranslations()
    abstract fun addItemTranslations()
    abstract fun addItemSubtypeTranslations()
    abstract fun addBlockTranslations()
    abstract fun addEffectTranslations()
    abstract fun addTooltipTranslations()
    abstract fun addGuiTranslations()
    abstract fun addConfigTranslations()
    abstract fun addTagTranslations()
}