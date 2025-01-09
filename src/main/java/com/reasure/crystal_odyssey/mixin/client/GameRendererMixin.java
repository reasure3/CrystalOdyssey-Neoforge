package com.reasure.crystal_odyssey.mixin.client;

import com.reasure.crystal_odyssey.effect.ModMobEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "getNightVisionScale", at = @At("HEAD"), cancellable = true)
    private static void applyLanternVision(LivingEntity livingEntity, float nanoTime, CallbackInfoReturnable<Float> cir) {
        MobEffectInstance lanternVision = livingEntity.getEffect(ModMobEffects.INSTANCE.getLANTERN_VISION());
        if (lanternVision != null) {
            switch (lanternVision.getAmplifier()) {
                case 0:
                    if (!livingEntity.hasEffect(MobEffects.NIGHT_VISION)) {
                        cir.setReturnValue(0.05f);
                    }
                    break;
                case 1:
                    if (!livingEntity.hasEffect(MobEffects.NIGHT_VISION)) {
                        cir.setReturnValue(0.15f);
                    }
                    break;
                case 2:
                    cir.setReturnValue(1.0f);
                    break;
            }
        }
    }
}
