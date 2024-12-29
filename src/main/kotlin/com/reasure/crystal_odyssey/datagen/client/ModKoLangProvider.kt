package com.reasure.crystal_odyssey.datagen.client

import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.datagen.client.base.ModBaseLangProvider
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.data.PackOutput

class ModKoLangProvider(output: PackOutput) : ModBaseLangProvider(output, "ko_kr") {
    override fun addTranslations() {
        addCreativeModeTab("크리스탈 오디세이")

        add(ModItems.GLOWSTONE_GEM, "발광석 보석")
        add(ModBlocks.GLOWSTONE_GEM_BLOCK, "발광석 보석 블록")
    }
}