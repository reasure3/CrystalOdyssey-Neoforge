package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ItemModelProvider(output, CrystalOdyssey.ID, exFileHelper) {

    override fun registerModels() {
        basicItem(ModItems.GLOWSTONE_GEM)
    }
}