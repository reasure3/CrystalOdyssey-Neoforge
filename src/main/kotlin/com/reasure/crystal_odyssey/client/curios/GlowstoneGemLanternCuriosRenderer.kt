package com.reasure.crystal_odyssey.client.curios

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.model.EntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.util.Mth
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.ICurioRenderer
import kotlin.random.Random

class GlowstoneGemLanternCuriosRenderer : ICurioRenderer {
    override fun <T : LivingEntity, M : EntityModel<T>> render(
        stack: ItemStack,
        slotContext: SlotContext,
        matrixStack: PoseStack,
        renderLayerParent: RenderLayerParent<T, M>,
        renderTypeBuffer: MultiBufferSource,
        light: Int,
        limbSwing: Float,
        limbSwingAmount: Float,
        partialTicks: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        matrixStack.pushPose()

        // Basic rotation and scaling
        matrixStack.apply {
            scale(0.4f, 0.4f, 0.4f)
            mulPose(Axis.ZP.rotation(Mth.PI))
            mulPose(Axis.YN.rotation(Mth.HALF_PI))
        }

        // Calculate position offsets
        val isCrouching = slotContext.entity.isCrouching
        val hasLegArmor = !slotContext.entity.getItemBySlot(EquipmentSlot.LEGS).isEmpty

        val xOffset = if (isCrouching) 0.7f else 0f
        val yOffset = if (isCrouching) -1.9f else -1.6f
        val zOffset = if (hasLegArmor) -0.75f else -0.7f

        matrixStack.translate(xOffset, yOffset, zOffset)

        // Handle swinging animation
        if (!Mth.equal(limbSwingAmount, 0f)) {
            // Change the rotation center
            matrixStack.translate(0f, 0.35f, 0f)

            // Calculate swing motion
            val swingAmount = Mth.abs(0.5f - limbSwingAmount)
            val randomness = (Random.nextFloat() - 0.5f) * 0.05f
            val swingAngle = Mth.sin(limbSwing * 0.5f + randomness) * swingAmount * 0.5f

            // Apply swinging rotation
            matrixStack.mulPose(Axis.ZP.rotation(swingAngle))

            // Return to the original position
            matrixStack.translate(0f, -0.35f, 0f)
        }

        // Render lantern model
        with(Minecraft.getInstance()) {
            itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                light,
                OverlayTexture.NO_OVERLAY,
                matrixStack,
                renderTypeBuffer,
                level,
                0
            )
        }

        matrixStack.popPose()
    }
}