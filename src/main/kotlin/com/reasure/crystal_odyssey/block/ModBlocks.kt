package com.reasure.crystal_odyssey.block

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.custom.GlowstoneLanternBlock
import com.reasure.crystal_odyssey.block.custom.LightOrbBlock
import com.reasure.crystal_odyssey.block.custom.ManaInjectorBlock
import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(CrystalOdyssey.ID)

    val GLOWSTONE_GEM_BLOCK: Block by registerBlockWithItem("glowstone_gem_block") {
        Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).lightLevel { 15 })
    }

    val RUBY_BLOCK: Block by registerBlockWithItem("ruby_block") {
        Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).mapColor(MapColor.COLOR_RED))
    }

    val SAPPHIRE_BLOCK: Block by registerBlockWithItem("sapphire_block") {
        Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).mapColor(MapColor.COLOR_BLUE))
    }

    val GLOWSTONE_GEM_LANTERN: Block by BLOCKS.register("glowstone_gem_lantern") { ->
        GlowstoneLanternBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.GOLD)
                .lightLevel { state -> (state.getValue(ModBlockStates.LANTERN_LEVEL) + 1) * 5 })
    }

    val MANA_INJECTOR: Block by registerBlockWithItem("mana_injector") { ->
        ManaInjectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON).requiresCorrectToolForDrops())
    }

    val LIGHT_ORB_BLOCK: Block by BLOCKS.registerBlock("light_orb_block") { properties ->
        LightOrbBlock(
            properties
                .replaceable().noCollission().noLootTable()
                .mapColor(waterloggedMapColor(MapColor.GOLD))
                .lightLevel { 15 }
        )
    }

    private fun <T : Block> registerBlockWithItem(name: String, block: () -> T): DeferredBlock<T> {
        val registry: DeferredBlock<T> = BLOCKS.register(name, block)
        ModItems.ITEMS.registerSimpleBlockItem(name, registry)
        return registry
    }

    private fun waterloggedMapColor(unWaterloggedMapColor: MapColor): (BlockState) -> MapColor =
        { state -> if (state.getValue(BlockStateProperties.WATERLOGGED)) MapColor.WATER else unWaterloggedMapColor }
}