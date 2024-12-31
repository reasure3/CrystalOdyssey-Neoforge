package com.reasure.crystal_odyssey.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

class ModTags {
    object Items {
        val SLOT_BELT = curiosTag("belt")

        private fun curiosTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name))
    }
}