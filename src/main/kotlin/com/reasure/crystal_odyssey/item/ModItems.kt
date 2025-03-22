package com.reasure.crystal_odyssey.item

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.item.custom.*
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.SimpleFluidContent
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModItems {
    val ITEMS: DeferredRegister.Items = DeferredRegister.createItems(CrystalOdyssey.ID)

    val ENCHANTED_DIAMOND: Item by ITEMS.registerItem("enchanted_diamond") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val ENCHANTED_EMERALD: Item by ITEMS.registerItem("enchanted_emerald") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val ENCHANTED_AMETHYST_SHARD: Item by ITEMS.registerItem("enchanted_amethyst_shard") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val GLOWSTONE_GEM: Item by ITEMS.registerSimpleItem("glowstone_gem")

    val ENCHANTED_GLOWSTONE_GEM: Item by ITEMS.registerItem("enchanted_glowstone_gem") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val GLOWSTONE_GEM_CORE: Item by ITEMS.registerItem("glowstone_gem_core") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.UNCOMMON))
    }

    val RUBY: Item by ITEMS.registerSimpleItem("ruby")

    val ENCHANTED_RUBY: Item by ITEMS.registerItem("enchanted_ruby") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val SAPPHIRE: Item by ITEMS.registerSimpleItem("sapphire")

    val ENCHANTED_SAPPHIRE: Item by ITEMS.registerItem("enchanted_sapphire") { properties ->
        Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true))
    }

    val GLOWSTONE_GEM_LANTERN: GlowstoneGemLanternItem by ITEMS.registerItem("glowstone_gem_lantern") { properties ->
        GlowstoneGemLanternItem(
            ModBlocks.GLOWSTONE_GEM_LANTERN,
            properties.component(ModDataComponents.LANTERN_LEVEL, 0)
        )
    }

    val LIGHT_STAFF: LightStaffItem by ITEMS.registerItem("light_staff") { properties ->
        LightStaffItem(ModBlocks.LIGHT_ORB_BLOCK, properties.durability(2048))
    }

    val EL_DORADO_STAFF: Item by ITEMS.registerItem("el_dorado_staff") { properties ->
        Item(properties.stacksTo(1))
    }

    val EL_DORADO_STAFF_ACTIVE: ElDoradoStaffItem by ITEMS.registerItem("el_dorado_staff_active") { properties ->
        ElDoradoStaffItem(
            properties.durability(1024).rarity(Rarity.RARE).component(ModDataComponents.FIND_BLOCKS, FindBlocks.EMPTY)
        )
    }

    val SAPPHIRE_BUCKET: CapacityBucketItem by ITEMS.registerItem("sapphire_bucket") { properties ->
        CapacityBucketItem(properties.stacksTo(1), 30 * FluidType.BUCKET_VOLUME, Blocks.WATER.descriptionId)
    }

    val INFINITE_SAPPHIRE_BUCKET: CapabilityBucketItem by ITEMS.registerItem("infinite_sapphire_bucket") { properties ->
        CapabilityBucketItem(
            properties.rarity(Rarity.RARE).stacksTo(1)
                .component(
                    ModDataComponents.FLUID,
                    SimpleFluidContent.copyOf(FluidStack(Fluids.WATER, Int.MAX_VALUE))
                ).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true),
            Blocks.WATER.descriptionId
        )
    }

    val RUBY_BUCKET: CapacityBucketItem by ITEMS.registerItem("ruby_bucket") { properties ->
        CapacityBucketItem(properties.stacksTo(1), 30 * FluidType.BUCKET_VOLUME, Blocks.LAVA.descriptionId)
    }

    val INFINITE_RUBY_BUCKET: CapabilityBucketItem by ITEMS.registerItem("infinite_ruby_bucket") { properties ->
        CapabilityBucketItem(
            properties.rarity(Rarity.RARE).stacksTo(1)
                .component(
                    ModDataComponents.FLUID,
                    SimpleFluidContent.copyOf(FluidStack(Fluids.LAVA, Int.MAX_VALUE))
                ).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true),
            Blocks.LAVA.descriptionId
        )
    }
}