package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import com.mojang.math.Axis
import com.reasure.crystal_odyssey.client.util.BlockFinder
import net.minecraft.client.Camera
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.util.FastColor
import org.lwjgl.opengl.GL11

object FoundBlockBorderRenderer {
    fun render(camera: Camera) {
        val poseStack = PoseStack()

        poseStack.mulPose(Axis.XP.rotationDegrees(camera.xRot))
        poseStack.mulPose(Axis.YP.rotationDegrees(camera.yRot - 180f))
        poseStack.translate(-camera.position.x, -camera.position.y, -camera.position.z)

        val builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)

        val alpha = FastColor.ARGB32.alpha(BlockFinder.borderColor)
        val red = FastColor.ARGB32.red(BlockFinder.borderColor)
        val blue = FastColor.ARGB32.blue(BlockFinder.borderColor)
        val green = FastColor.ARGB32.green(BlockFinder.borderColor)

        synchronized(BlockFinder.foundBlocks) {
            BlockFinder.foundBlocks.forEach { pos ->
                RenderHelper.renderBoxBorder(pos, poseStack, builder, red, green, blue, alpha)
            }
        }

        RenderSystem.setShader(GameRenderer::getPositionColorShader)
        RenderSystem.enableBlend()
        RenderSystem.depthFunc(GL11.GL_ALWAYS)

        builder.build()?.let { BufferUploader.drawWithShader(it) }

        RenderSystem.depthFunc(GL11.GL_LEQUAL)
        RenderSystem.disableBlend()
    }
}