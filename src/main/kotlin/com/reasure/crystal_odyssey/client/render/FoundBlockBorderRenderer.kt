package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import com.reasure.crystal_odyssey.client.util.BlockFinder
import net.minecraft.client.Camera
import net.minecraft.client.Minecraft
import net.minecraft.util.FastColor

object FoundBlockBorderRenderer {
    private val poseStack = PoseStack()

    fun render(camera: Camera) {
        val minecraft = Minecraft.getInstance()
        val level = minecraft.level ?: return

        poseStack.pushPose()
        poseStack.mulPose(Axis.XP.rotationDegrees(camera.xRot))
        poseStack.mulPose(Axis.YP.rotationDegrees(camera.yRot - 180f))

        poseStack.translate(-camera.position.x, -camera.position.y, -camera.position.z)

        val bufferSource = minecraft.renderBuffers().bufferSource()

        val alpha = FastColor.ARGB32.alpha(BlockFinder.borderColor)
        val red = FastColor.ARGB32.red(BlockFinder.borderColor)
        val blue = FastColor.ARGB32.blue(BlockFinder.borderColor)
        val green = FastColor.ARGB32.green(BlockFinder.borderColor)

        RenderSystem.disableDepthTest()
        val quadBuilder = bufferSource.getBuffer(CustomRenderTypes.QUADS_NO_DEPTH)
        synchronized(BlockFinder.foundBlocks) {
            BlockFinder.foundBlocks.forEach { pos ->
                RenderHelper.renderBoxQuad(level, pos, poseStack, quadBuilder, red, green, blue, alpha)
            }
        }
        bufferSource.endBatch(CustomRenderTypes.QUADS_NO_DEPTH)

        poseStack.popPose()
    }
}