package com.reasure.crystal_odyssey.datagen.client.base

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.material.Fluid
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.common.data.ExistingFileHelper

abstract class ModBaseItemModelProvider(output: PackOutput, exFileHelper: ExistingFileHelper) :
    ItemModelProvider(output, CrystalOdyssey.ID, exFileHelper) {

    fun copyTextureItem(item: Item, original: Item): ItemModelBuilder {
        val loc = itemLoc(item)
        val originalLoc = itemLoc(original)
        return getBuilder(loc.toString())
            .parent(generatedItem)
            .texture("layer0", originalLoc.withPrefix("item/"))
    }

    fun capacityBucketItem(item: Item, fluid: Fluid): ItemModelBuilder {
        val fluidName = BuiltInRegistries.FLUID.getKey(fluid).path
        val hasFluidModel = basicItem(itemLoc(item).withPrefix("${fluidName}_"))
        return basicItem(item).override()
            .model(hasFluidModel)
            .predicate(CrystalOdyssey.modLoc("has_fluid"), 1f)
            .end()
    }

    private fun itemLoc(item: Item): ResourceLocation {
        return BuiltInRegistries.ITEM.getKey(item)
    }

    private val generatedItem = ModelFile.UncheckedModelFile("item/generated")
}