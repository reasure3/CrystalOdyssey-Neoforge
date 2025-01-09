package com.reasure.crystal_odyssey.block

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.custom.GlowstoneLanternBlock
import com.reasure.crystal_odyssey.block.custom.ManaInjectorBlock
import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(CrystalOdyssey.ID)

    val GLOWSTONE_GEM_BLOCK: Block by registerBlockWithItem("glowstone_gem_block") {
        Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).lightLevel { 15 })
    }

    val GLOWSTONE_GEM_LANTERN: Block by BLOCKS.register("glowstone_gem_lantern") { ->
        GlowstoneLanternBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.GOLD)
                .lightLevel { state -> (state.getValue(ModBlockStates.LANTERN_LEVEL) + 1) * 5 })
    }

    val MANA_INJECTOR: Block by registerBlockWithItem("mana_injector") { ->
        ManaInjectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON).requiresCorrectToolForDrops())
    }

    private fun <T : Block> registerBlockWithItem(name: String, block: () -> T): DeferredBlock<T> {
        val registry: DeferredBlock<T> = BLOCKS.register(name, block)
        ModItems.ITEMS.registerSimpleBlockItem(name, registry)
        return registry
    }
}