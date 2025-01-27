package com.reasure.crystal_odyssey.client.event

import com.mojang.blaze3d.systems.RenderSystem
import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.CrystalOdysseyServerConfig
import com.reasure.crystal_odyssey.client.util.BlockFinder
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LevelRenderer
import net.minecraft.client.renderer.RenderType
import net.minecraft.util.FastColor
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent

@EventBusSubscriber(value = [Dist.CLIENT], modid = CrystalOdyssey.ID, bus = EventBusSubscriber.Bus.GAME)
object ModClientEvents {
    @SubscribeEvent
    fun onLoggedIn(event: ClientPlayerNetworkEvent.LoggingIn) {
        BlockFinder.clearFind()
    }

    @SubscribeEvent
    fun onLoggedOut(event: ClientPlayerNetworkEvent.LoggingOut) {
        BlockFinder.clearFind()
    }

    @SubscribeEvent
    fun onClientTick(event: ClientTickEvent.Post) {
        if (BlockFinder.renderBlock) {
            val minecraft = Minecraft.getInstance() ?: return
            val currentTick = minecraft.level?.gameTime ?: return
            val borderDuration = CrystalOdysseyServerConfig.SERVER.elDoradoStaffRetainTick
            if (currentTick - BlockFinder.lastFoundTick >= borderDuration) {
                BlockFinder.clearFind()
            }
        }
    }

    @SubscribeEvent
    fun onRenderLevel(event: RenderLevelStageEvent) {
        if (BlockFinder.renderBlock && event.stage == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
            val viewPosition = event.camera.position
            val minecraft = Minecraft.getInstance() ?: return
            val bufferSource = minecraft.renderBuffers().outlineBufferSource() ?: return
            val vertexBuilder = bufferSource.getBuffer(RenderType.lines())

            val alpha = FastColor.ARGB32.alpha(BlockFinder.borderColor) / 255f
            val red = FastColor.ARGB32.red(BlockFinder.borderColor) / 255f
            val blue = FastColor.ARGB32.blue(BlockFinder.borderColor) / 255f
            val green = FastColor.ARGB32.green(BlockFinder.borderColor) / 255f

            RenderSystem.enableBlend()
            RenderSystem.defaultBlendFunc()

            synchronized(BlockFinder.foundBlocks) {
                val posList = BlockFinder.foundBlocks
                posList.forEach {
                    val bound = it.move(-viewPosition.x, -viewPosition.y, -viewPosition.z)
                    LevelRenderer.renderLineBox(
                        event.poseStack, vertexBuilder,
                        bound.minX, bound.minY, bound.minZ,
                        bound.maxX, bound.maxY, bound.maxZ,
                        red, green, blue, alpha
                    )
                }
            }
        }
    }
}