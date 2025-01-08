package com.reasure.crystal_odyssey.client.compat.curios

import com.reasure.crystal_odyssey.client.CrystalOdysseyClient
import com.reasure.crystal_odyssey.item.ModItems
import top.theillusivec4.curios.api.client.CuriosRendererRegistry

object CuriosClientInitializer {
    fun registerRenderer() {
        CrystalOdysseyClient.LOGGER.info("Find Curios! Starting register Curios Renderer")
        CuriosRendererRegistry.register(ModItems.GLOWSTONE_GEM_LANTERN, ::GlowstoneGemLanternCuriosRenderer)
    }
}