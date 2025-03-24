package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.PoseStack.Pose
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.level.Level
import org.joml.Matrix4f

object RenderHelper {
    fun renderBoxQuad(
        level: Level, pos: BlockPos,
        pose: PoseStack, consumer: VertexConsumer,
        red: Int, green: Int, blue: Int, alpha: Int
    ) {
        pose.pushPose()
        pose.translate(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

        val matrix = pose.last().pose()

        level.getBlockState(pos).getOcclusionShape(level, pos).forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            drawBoxQuad(
                consumer, matrix,
                minX.toFloat(), minY.toFloat(), minZ.toFloat(),
                maxX.toFloat(), maxY.toFloat(), maxZ.toFloat(),
                red, green, blue, alpha
            )
        }

        pose.popPose()
    }

    fun renderBoxLine(
        level: Level, pos: BlockPos,
        pose: PoseStack, consumer: VertexConsumer,
        red: Int, green: Int, blue: Int, alpha: Int
    ) {
        pose.pushPose()
        pose.translate(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

        val lastPose = pose.last()
        val matrix = lastPose.pose()

        level.getBlockState(pos).getOcclusionShape(level, pos).forAllEdges { minX, minY, minZ, maxX, maxY, maxZ ->
            drawBoxLine(
                lastPose, consumer, matrix,
                minX.toFloat(), minY.toFloat(), minZ.toFloat(),
                maxX.toFloat(), maxY.toFloat(), maxZ.toFloat(),
                red, green, blue, alpha
            )
        }

        pose.popPose()
    }

    @Suppress("DuplicatedCode")
    private fun drawBoxQuad(
        consumer: VertexConsumer, matrix: Matrix4f,
        minX: Float, minY: Float, minZ: Float,
        maxX: Float, maxY: Float, maxZ: Float,
        red: Int, green: Int, blue: Int, alpha: Int
    ) {
        // -Z
        consumer.addVertex(matrix, minX, minY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, maxY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, minY, minZ).setColor(red, green, blue, alpha)

        // +Z
        consumer.addVertex(matrix, minX, minY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, minY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, maxY, maxZ).setColor(red, green, blue, alpha)

        // -Y
        consumer.addVertex(matrix, minX, minY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, minY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, minY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, minY, maxZ).setColor(red, green, blue, alpha)

        // +Y
        consumer.addVertex(matrix, minX, maxY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, maxY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, minZ).setColor(red, green, blue, alpha)

        // -X
        consumer.addVertex(matrix, minX, minY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, minY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, maxY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, minX, maxY, minZ).setColor(red, green, blue, alpha)

        // +X
        consumer.addVertex(matrix, maxX, minY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, minZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha)
        consumer.addVertex(matrix, maxX, minY, maxZ).setColor(red, green, blue, alpha)
    }

    @Suppress("DuplicatedCode")
    private fun drawBoxLine(
        pose: Pose, consumer: VertexConsumer, matrix: Matrix4f,
        minX: Float, minY: Float, minZ: Float,
        maxX: Float, maxY: Float, maxZ: Float,
        red: Int, green: Int, blue: Int, alpha: Int
    ) {
        val dx = maxX - minX
        val dy = maxY - minY
        val dz = maxZ - minZ

        val len = Mth.sqrt(dx * dx + dy * dy + dz * dz)

        val nx = dx / len
        val ny = dy / len
        val nz = dz / len

        consumer.addVertex(matrix, minX, minY, minZ).setColor(red, green, blue, alpha).setNormal(pose, nx, ny, nz)
        consumer.addVertex(matrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha).setNormal(pose, nx, ny, nz)
    }
}