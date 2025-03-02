package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.core.BlockPos

object RenderHelper {
    fun renderBoxBorder(
        pos: BlockPos,
        pose: PoseStack,
        consumer: VertexConsumer,
        red: Int,
        green: Int,
        blue: Int,
        alpha: Int
    ) {
        val x = pos.x.toDouble()
        val y = pos.y.toDouble()
        val z = pos.z.toDouble()

        pose.pushPose()
        pose.translate(x, y, z)

        val matrix = pose.last().pose()

        // -Z
        consumer.addVertex(matrix, 0f, 0f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 1f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 0f, 0f).setColor(red, green, blue, alpha)

        // +Z
        consumer.addVertex(matrix, 0f, 0f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 0f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 1f, 1f).setColor(red, green, blue, alpha)

        // -Y
        consumer.addVertex(matrix, 0f, 0f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 0f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 0f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 0f, 1f).setColor(red, green, blue, alpha)

        // +Y
        consumer.addVertex(matrix, 0f, 1f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 1f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 0f).setColor(red, green, blue, alpha)

        // -X
        consumer.addVertex(matrix, 0f, 0f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 0f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 1f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 0f, 1f, 0f).setColor(red, green, blue, alpha)

        // +X
        consumer.addVertex(matrix, 1f, 0f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 0f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 1f, 1f).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, 1f, 0f, 1f).setColor(red, green, blue, alpha)

        pose.popPose()
    }
}