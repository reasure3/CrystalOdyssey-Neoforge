package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.custom.FoilItem
import com.reasure.crystal_odyssey.item.custom.GlowstoneGemLanternItem
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister

import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(CrystalOdyssey.ID)

    val GLOWSTONE_GEM: Item by ITEMS.registerSimpleItem("glowstone_gem")

    val ENCHANTED_GLOWSTONE_GEM: Item by ITEMS.registerItem("enchanted_glowstone_gem") { properties ->
        FoilItem(properties)
    }

    val GLOWSTONE_GEM_LANTERN: GlowstoneGemLanternItem by ITEMS.registerItem("glowstone_gem_lantern") { properties ->
        GlowstoneGemLanternItem(ModBlocks.GLOWSTONE_GEM_LANTERN, properties)
    }
}