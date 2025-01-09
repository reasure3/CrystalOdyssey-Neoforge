package com.reasure.crystal_odyssey.datagen.server.base

import com.reasure.crystal_odyssey.block.state.ModBlockStates
import com.reasure.crystal_odyssey.item.components.ModDataComponents
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition

abstract class ModBaseBlockLootSubProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    fun addLantern(block: Block) = add(block, ::createLanternTable)

    fun createLanternTable(block: Block): LootTable.Builder {
        val table: LootTable.Builder = createSingleItemTable(block)
        for (i: Int in 0..2) {
            table.apply(
                SetComponentsFunction.setComponent(ModDataComponents.LANTERN_LEVEL, i).`when`(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(
                        StatePropertiesPredicate.Builder.properties().hasProperty(ModBlockStates.LANTERN_LEVEL, i)
                    )
                )
            )
        }
        return table
    }
}