package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.client.model.generators.ModelFile.UncheckedModelFile
import net.neoforged.neoforge.common.data.ExistingFileHelper

abstract class ModBaseItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ItemModelProvider(output, CrystalOdyssey.ID, exFileHelper) {

    fun copyTextureItem(item: Item, original: Item): ItemModelBuilder {
        val loc = BuiltInRegistries.ITEM.getKey(item)
        val originalLoc = BuiltInRegistries.ITEM.getKey(original)
        return getBuilder(loc.toString())
            .parent(UncheckedModelFile("item/generated"))
            .texture("layer0", originalLoc.withPrefix("item/"))
    }
}