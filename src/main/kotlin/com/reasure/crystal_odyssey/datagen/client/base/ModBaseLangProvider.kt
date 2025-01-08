package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.data.PackOutput
import net.minecraft.world.effect.MobEffect
import net.neoforged.neoforge.common.data.LanguageProvider
import java.util.function.Supplier

abstract class ModBaseLangProvider(output: PackOutput, locale: String) :
    LanguageProvider(output, CrystalOdyssey.ID, locale) {

    fun addCreativeModeTab(name: String) = add("itemGroup.${CrystalOdyssey.ID}", name)

    fun <T : MobEffect> addTippedArrow(key: Supplier<T>, name: String) =
        add("item.minecraft.tipped_arrow.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.potion.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addSplashPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.splash_potion.effect.${key.get().descriptionId}", name)

    fun <T : MobEffect> addLingeringPotion(key: Supplier<T>, name: String) =
        add("item.minecraft.lingering_potion.effect.${key.get().descriptionId}", name)

    fun addContainer(key: String, name: String) = add("container.${CrystalOdyssey.ID}.$key", name)
}