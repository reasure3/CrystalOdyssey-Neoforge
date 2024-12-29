package com.reasure.crystal_odyssey.datagen

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.datagen.client.ModBlockStateProvider
import com.reasure.crystal_odyssey.datagen.client.ModEnLangProvider
import com.reasure.crystal_odyssey.datagen.client.ModItemModelProvider
import com.reasure.crystal_odyssey.datagen.client.ModKoLangProvider
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@Suppress("UNUSED")
@EventBusSubscriber(modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.MOD)
object ModDataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        with(event.generator) {
            addProvider(event.includeClient(), ModItemModelProvider(packOutput, event.existingFileHelper))
            addProvider(event.includeClient(), ModBlockStateProvider(packOutput, event.existingFileHelper))
            addProvider(event.includeClient(), ModEnLangProvider(packOutput))
            addProvider(event.includeClient(), ModKoLangProvider(packOutput))

            addProvider(
                event.includeServer(),
                ModBlockTagsProvider(packOutput, event.lookupProvider, event.existingFileHelper)
            )
            addProvider(event.includeServer(), ModBlockLootProvider.of(packOutput, event.lookupProvider))
            addProvider(event.includeServer(), ModRecipeProvider(packOutput, event.lookupProvider))
        }
    }
}