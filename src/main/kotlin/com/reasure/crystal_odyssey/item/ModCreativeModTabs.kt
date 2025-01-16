package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModCreativeModTabs {
    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CrystalOdyssey.ID)

    @Suppress("UNUSED")
    val CRYSTAL_ODYSSEY_TAB: CreativeModeTab by CREATIVE_MODE_TABS.register("${CrystalOdyssey.ID}_tab") { ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.${CrystalOdyssey.ID}"))
            .icon { ItemStack(ModItems.GLOWSTONE_GEM) }
            .displayItems { _, output ->
                with(output) {
                    accept(ModItems.GLOWSTONE_GEM)
                    accept(ModItems.ENCHANTED_GLOWSTONE_GEM)
                    accept(ModBlocks.GLOWSTONE_GEM_BLOCK)
                    accept(ModItems.RUBY)
                    accept(ModItems.ENCHANTED_RUBY)
                    accept(ModBlocks.RUBY_BLOCK)
                    accept(ModItems.SAPPHIRE)
                    accept(ModItems.ENCHANTED_SAPPHIRE)
                    accept(ModBlocks.SAPPHIRE_BLOCK)
                    accept(ModBlocks.MANA_INJECTOR)
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 0) })
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 1) })
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 2) })
                    accept(ModItems.LIGHT_STAFF)
                }
            }
            .build()
    }
}