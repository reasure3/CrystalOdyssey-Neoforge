package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.world.item.Item
import net.neoforged.neoforge.registries.DeferredRegister

import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(CrystalOdyssey.ID)

    val GLOWSTONE_GEM: Item by ITEMS.registerSimpleItem("glowstone_gem")
}