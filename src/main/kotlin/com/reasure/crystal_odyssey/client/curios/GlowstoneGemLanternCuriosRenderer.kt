package com.reasure.crystal_odyssey.client.curios

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.model.EntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.ICurioRenderer

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

        matrixStack.mulPose(Axis.ZP.rotationDegrees(180f))
        matrixStack.scale(0.5f, 0.5f, 0.5f)

        if (slotContext.entity.getItemBySlot(EquipmentSlot.LEGS).isEmpty) {
            matrixStack.translate(0.1f, -1.1f, 0f)
        } else {
            matrixStack.translate(0.17f, -1.1f, 0f)
        }

        val mc = Minecraft.getInstance()
        mc.itemRenderer.renderStatic(
            stack,
            ItemDisplayContext.HEAD,
            light,
            OverlayTexture.NO_OVERLAY,
            matrixStack,
            renderTypeBuffer,
            mc.level,
            0
        )

        matrixStack.popPose()
    }
}