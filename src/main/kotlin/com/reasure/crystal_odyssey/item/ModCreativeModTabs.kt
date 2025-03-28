package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries
import com.reasure.crystal_odyssey.util.ItemStackHelper.with
import com.reasure.crystal_odyssey.util.ItemStackHelper.withBucket
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.material.Fluids
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
                    accept(ModItems.ENCHANTED_DIAMOND)
                    accept(ModItems.ENCHANTED_EMERALD)
                    accept(ModItems.ENCHANTED_AMETHYST_SHARD)
                    accept(ModItems.GLOWSTONE_GEM)
                    accept(ModItems.ENCHANTED_GLOWSTONE_GEM)
                    accept(ModItems.GLOWSTONE_GEM_CORE)
                    accept(ModBlocks.GLOWSTONE_GEM_BLOCK)
                    accept(ModItems.SAPPHIRE)
                    accept(ModItems.ENCHANTED_SAPPHIRE)
                    accept(ModBlocks.SAPPHIRE_BLOCK)
                    accept(ModBlocks.SAPPHIRE_ORE)
                    accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
                    accept(ModItems.RUBY)
                    accept(ModItems.ENCHANTED_RUBY)
                    accept(ModBlocks.RUBY_BLOCK)
                    accept(ModBlocks.RUBY_ORE)
                    accept(ModBlocks.DEEPSLATE_RUBY_ORE)
                    accept(ModBlocks.MANA_INJECTOR)
                    accept(ModBlocks.MANA_ANVIL)
                    accept(ModItems.GLOWSTONE_GEM_LANTERN.with(ModDataComponents.LANTERN_LEVEL, 0))
                    accept(ModItems.GLOWSTONE_GEM_LANTERN.with(ModDataComponents.LANTERN_LEVEL, 1))
                    accept(ModItems.GLOWSTONE_GEM_LANTERN.with(ModDataComponents.LANTERN_LEVEL, 2))
                    accept(ModItems.LIGHT_STAFF)
                    accept(ModItems.EL_DORADO_STAFF)
                    accept(ModItems.SAPPHIRE_BUCKET)
                    accept(ModItems.SAPPHIRE_BUCKET.withBucket(Fluids.WATER, 30))
                    accept(ModItems.INFINITE_SAPPHIRE_BUCKET)
                    accept(ModItems.RUBY_BUCKET)
                    accept(ModItems.RUBY_BUCKET.withBucket(Fluids.LAVA, 30))
                    accept(ModItems.INFINITE_RUBY_BUCKET)
                }
            }
            .build()
    }

    @Suppress("UNUSED")
    val EL_DORADO_STAFF_TAB: CreativeModeTab by CREATIVE_MODE_TABS.register("${CrystalOdyssey.ID}.el_dorado_staff_tab") { ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.${CrystalOdyssey.ID}.el_dorado_staff"))
            .withTabsBefore(CrystalOdyssey.modLoc("${CrystalOdyssey.ID}_tab"))
            .icon { ItemStack(ModItems.EL_DORADO_STAFF_ACTIVE) }
            .displayItems { parameters, output ->
                output.acceptAll(makeAllElDoradoStaff(parameters))
            }
            .build()
    }

    private fun makeAllElDoradoStaff(parameters: CreativeModeTab.ItemDisplayParameters): List<ItemStack> {
        return parameters.holders.lookupOrThrow(ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY)
            .filterElements(ElDoradoTarget::isNotEmpty)
            .listElements()
            .map { it.value() }
            .sorted(compareBy<ElDoradoTarget> { it.priority })
            .map {
                ModItems.EL_DORADO_STAFF_ACTIVE
                    .with({ DataComponents.ITEM_NAME }, it.staffName)
                    .with(ModDataComponents.FIND_BLOCKS, it.findBlocks)
                    .with(ModDataComponents.OVERLAY_ITEMS, it.overlayItems)
            }
            .toList()
    }
}