package com.reasure.crystal_odyssey.compat.waila

import com.reasure.crystal_odyssey.block.custom.GlowstoneLanternBlock
import snownee.jade.api.IWailaClientRegistration
import snownee.jade.api.IWailaCommonRegistration
import snownee.jade.api.IWailaPlugin
import snownee.jade.api.WailaPlugin

@WailaPlugin
class WailaCrystalOdysseyPlugin: IWailaPlugin {
    override fun register(registration: IWailaCommonRegistration) {
        registration.registerBlockDataProvider(GlowstoneGemLanternComponentProvider, GlowstoneLanternBlock::class.java)
    }

    override fun registerClient(registration: IWailaClientRegistration) {
        registration.registerBlockComponent(GlowstoneGemLanternComponentProvider, GlowstoneLanternBlock::class.java)
    }
}