package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.LanguageProvider

abstract class ModBaseLangProvider(output: PackOutput, locale: String) :
    LanguageProvider(output, CrystalOdyssey.ID, locale) {

    fun addCreativeModeTab(name: String) = add("itemGroup.${CrystalOdyssey.ID}", name)
}