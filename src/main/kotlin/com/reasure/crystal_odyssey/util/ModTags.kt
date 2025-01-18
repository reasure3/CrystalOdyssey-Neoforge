package com.reasure.crystal_odyssey.util

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class ModTags {
    object Items {
        val LIGHT_STAFF_REPAIRABLE = modTag("light_staff_repairable")

        val SLOT_BELT = curiosTag("belt")

        val STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby")
        val STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire")

        val ORES_RUBY = forgeTag("ores/ruby")
        val ORES_SAPPHIRE = forgeTag("ores/sapphire")

        private fun modTag(name: String): TagKey<Item> = ItemTags.create(CrystalOdyssey.modLoc(name))

        private fun curiosTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name))

        private fun forgeTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name))
    }

    object Blocks {
        val STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby")
        val STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire")

        val ORES_RUBY = forgeTag("ores/ruby")
        val ORES_SAPPHIRE = forgeTag("ores/sapphire")

        private fun forgeTag(name: String): TagKey<Block> =
            BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name))
    }
}