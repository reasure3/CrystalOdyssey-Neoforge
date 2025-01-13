package com.reasure.crystal_odyssey.util

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

class ModTags {
    object Items {
        val LIGHT_STAFF_REPAIRABLE = modTag("light_staff_repairable")

        val SLOT_BELT = curiosTag("belt")

        private fun modTag(name: String): TagKey<Item> = ItemTags.create(CrystalOdyssey.modLoc(name))

        private fun curiosTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name))
    }
}