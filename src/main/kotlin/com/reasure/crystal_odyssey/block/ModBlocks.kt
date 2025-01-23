package com.reasure.crystal_odyssey.block

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.custom.GlowstoneLanternBlock
import com.reasure.crystal_odyssey.block.custom.LightOrbBlock
import com.reasure.crystal_odyssey.block.custom.ManaAnvilBlock
import com.reasure.crystal_odyssey.block.custom.ManaInjectorBlock
import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.ModItems
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DropExperienceBlock
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

    val RUBY_ORE: Block by registerBlockWithItem("ruby_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 5),
            BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE)
        )
    }

    val DEEPSLATE_RUBY_ORE: Block by registerBlockWithItem("deepslate_ruby_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 5),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE)
        )
    }

    val SAPPHIRE_ORE: Block by registerBlockWithItem("sapphire_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 8),
            BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE)
        )
    }

    val DEEPSLATE_SAPPHIRE_ORE: Block by registerBlockWithItem("deepslate_sapphire_ore") {
        DropExperienceBlock(
            UniformInt.of(2, 8),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE)
        )
    }

    val GLOWSTONE_GEM_LANTERN: GlowstoneLanternBlock by BLOCKS.register("glowstone_gem_lantern") { ->
        GlowstoneLanternBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).mapColor(MapColor.GOLD)
                .lightLevel { state -> (state.getValue(ModBlockStates.LANTERN_LEVEL) + 1) * 5 })
    }

    val MANA_INJECTOR: ManaInjectorBlock by registerBlockWithItem("mana_injector") {
        ManaInjectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON).requiresCorrectToolForDrops())
    }

    val MANA_ANVIL: ManaAnvilBlock by registerBlockWithItem("mana_anvil") {
        ManaAnvilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL))
    }

    val LIGHT_ORB_BLOCK: LightOrbBlock by BLOCKS.registerBlock("light_orb_block") { properties ->
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