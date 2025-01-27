package com.reasure.crystal_odyssey.client.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.SimpleParticleType

class LightOrbParticle(
    level: ClientLevel,
    x: Double,
    y: Double,
    z: Double,
    private val spriteSet: SpriteSet
) : TextureSheetParticle(level, x, y, z) {
    init {
        gravity = 0.0f
        hasPhysics = false
        quadSize = 0.1f
        lifetime = 30
        setSpriteFromAge(spriteSet)
    }

    override fun tick() {
        setSpriteFromAge(spriteSet)
        age++
        if (age >= lifetime) {
            remove()
        }
    }

    override fun getQuadSize(scaleFactor: Float): Float = quadSize

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_LIT

    class Provider(private val spriteSet: SpriteSet) : ParticleProvider<SimpleParticleType> {
        override fun createParticle(
            type: SimpleParticleType,
            level: ClientLevel,
            x: Double,
            y: Double,
            z: Double,
            xSpeed: Double,
            ySpeed: Double,
            zSpeed: Double
        ): Particle = LightOrbParticle(level, x, y, z, spriteSet)
    }
}