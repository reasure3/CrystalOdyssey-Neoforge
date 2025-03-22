package com.reasure.crystal_odyssey.datagen.util

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class DatagenTags {
    object Items {
        val SLOT_BELT = curiosTag("belt")

        val GEMS_RUBY = forgeTag("gems/ruby")
        val GEMS_SAPPHIRE = forgeTag("gems/sapphire")

        val STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby")
        val STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire")

        val ORES_RUBY = forgeTag("ores/ruby")
        val ORES_SAPPHIRE = forgeTag("ores/sapphire")

        private fun curiosTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name))

        private fun forgeTag(name: String): TagKey<Item> =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name))
    }

    object Blocks {
        val STORAGE_BLOCKS_QUARTZ = forgeTag("storage_blocks/quartz")
        val STORAGE_BLOCKS_AMETHYST = forgeTag("storage_blocks/amethyst")
        val STORAGE_BLOCKS_RUBY = forgeTag("storage_blocks/ruby")
        val STORAGE_BLOCKS_SAPPHIRE = forgeTag("storage_blocks/sapphire")

        val ORES_RUBY = forgeTag("ores/ruby")
        val ORES_SAPPHIRE = forgeTag("ores/sapphire")

        val EL_DORADO_COAL = modTag("el_dorado/coal")
        val EL_DORADO_IRON = modTag("el_dorado/iron")
        val EL_DORADO_COPPER = modTag("el_dorado/copper")
        val EL_DORADO_GOLD = modTag("el_dorado/gold")
        val EL_DORADO_REDSTONE = modTag("el_dorado/redstone")
        val EL_DORADO_EMERALD = modTag("el_dorado/emerald")
        val EL_DORADO_LAPIS_LAZULI = modTag("el_dorado/lapis")
        val EL_DORADO_DIAMOND = modTag("el_dorado/diamond")
        val EL_DORADO_QUARTZ = modTag("el_dorado/quartz")
        val EL_DORADO_NETHERITE = modTag("el_dorado/netherite")
        val EL_DORADO_AMETHYST = modTag("el_dorado/amethyst")
        val EL_DORADO_SAPPHIRE = modTag("el_dorado/sapphire")
        val EL_DORADO_RUBY = modTag("el_dorado/ruby")

        private fun modTag(name: String): TagKey<Block> =
            BlockTags.create(CrystalOdyssey.modLoc(name))

        private fun forgeTag(name: String): TagKey<Block> =
            BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name))
    }
}