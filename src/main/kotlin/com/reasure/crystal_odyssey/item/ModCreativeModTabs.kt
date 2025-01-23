package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.SimpleFluidContent
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
                    accept(ModBlocks.RUBY_ORE)
                    accept(ModBlocks.DEEPSLATE_RUBY_ORE)
                    accept(ModItems.SAPPHIRE)
                    accept(ModItems.ENCHANTED_SAPPHIRE)
                    accept(ModBlocks.SAPPHIRE_BLOCK)
                    accept(ModBlocks.SAPPHIRE_ORE)
                    accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
                    accept(ModBlocks.MANA_INJECTOR)
                    accept(ModBlocks.MANA_ANVIL)
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 0) })
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 1) })
                    accept(ItemStack(ModItems.GLOWSTONE_GEM_LANTERN).apply { set(ModDataComponents.LANTERN_LEVEL, 2) })
                    accept(ModItems.LIGHT_STAFF)
                    accept(ModItems.SAPPHIRE_BUCKET)
                    accept(ItemStack(ModItems.SAPPHIRE_BUCKET).apply {
                        set(
                            ModDataComponents.FLUID,
                            SimpleFluidContent.copyOf(FluidStack(Fluids.WATER, 30 * FluidType.BUCKET_VOLUME))
                        )
                    })
                    accept(ModItems.INFINITE_SAPPHIRE_BUCKET)
                    accept(ModItems.RUBY_BUCKET)
                    accept(ItemStack(ModItems.RUBY_BUCKET).apply {
                        set(
                            ModDataComponents.FLUID,
                            SimpleFluidContent.copyOf(FluidStack(Fluids.LAVA, 30 * FluidType.BUCKET_VOLUME))
                        )
                    })
                    accept(ModItems.INFINITE_RUBY_BUCKET)
                }
            }
            .build()
    }
}