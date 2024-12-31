package com.reasure.crystal_odyssey.datagen.server

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.ExistingFileHelper
import top.theillusivec4.curios.api.CuriosDataProvider
import java.util.concurrent.CompletableFuture

class ModCuriosProvider(
    output: PackOutput,
    exFileHelper: ExistingFileHelper,
    registries: CompletableFuture<HolderLookup.Provider>
) : CuriosDataProvider(CrystalOdyssey.ID, output, exFileHelper, registries) {
    override fun generate(registries: HolderLookup.Provider, fileHelper: ExistingFileHelper) {
        createEntities("belt")
            .addSlots("belt")
            .addPlayer()
    }
}