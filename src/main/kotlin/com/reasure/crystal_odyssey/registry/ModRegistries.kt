package com.reasure.crystal_odyssey.registry

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

object ModRegistries {
    val EL_DORADO_TARGET_REGISTRY_KEY: ResourceKey<Registry<ElDoradoTarget>> =
        ResourceKey.createRegistryKey(CrystalOdyssey.modLoc("el_dorado_targets"))
}