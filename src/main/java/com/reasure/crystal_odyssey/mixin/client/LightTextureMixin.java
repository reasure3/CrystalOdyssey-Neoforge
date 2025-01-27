package com.reasure.crystal_odyssey.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.reasure.crystal_odyssey.effect.ModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightTexture.class)
public class LightTextureMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyExpressionValue(
            method = "updateLightTexture",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/core/Holder;)Z", ordinal = 0)
    )
    private boolean hasLanternVision(boolean original) {
        if (minecraft.player == null) return original;
        if (minecraft.player.hasEffect(ModMobEffects.INSTANCE.getLANTERN_VISION())) {
            return true;
        }
        return original;
    }
}
