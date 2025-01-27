package com.reasure.crystal_odyssey.effect

import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.core.registries.Registries
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModMobEffects {
    val MOB_EFFECTS: DeferredRegister<MobEffect> = DeferredRegister.create(Registries.MOB_EFFECT, CrystalOdyssey.ID)

    val LANTERN_VISION: DeferredHolder<MobEffect, BaseMobEffect> = MOB_EFFECTS.register("lantern_vision") { ->
        BaseMobEffect(MobEffectCategory.BENEFICIAL, 0xFAE06F)
    }
}