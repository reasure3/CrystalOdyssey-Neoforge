package com.reasure.crystal_odyssey.particle

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModParticleTypes {
    val PARTICLE_TYPES: DeferredRegister<ParticleType<*>> =
        DeferredRegister.create(Registries.PARTICLE_TYPE, CrystalOdyssey.ID)

    val LIGHT_ORB: SimpleParticleType by PARTICLE_TYPES.register("light_orb") { ->
        SimpleParticleType(false)
    }
}