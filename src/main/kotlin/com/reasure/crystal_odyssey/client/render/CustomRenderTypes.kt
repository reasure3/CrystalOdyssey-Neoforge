package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.client.renderer.RenderStateShard
import net.minecraft.client.renderer.RenderType

@Suppress("INACCESSIBLE_TYPE")
object CustomRenderTypes {
    private val DISABLE_DEPTH_TEST: RenderStateShard.LayeringStateShard =
        RenderStateShard.LayeringStateShard("never", RenderSystem::disableDepthTest) {}

    val QUADS_NO_DEPTH: RenderType = RenderType.create(
        "${CrystalOdyssey.ID}:quads_no_depth",
        DefaultVertexFormat.POSITION_COLOR,
        VertexFormat.Mode.QUADS,
        256,
        false,
        true,
        RenderType.CompositeState.builder()
            .setShaderState(RenderStateShard.POSITION_COLOR_SHADER)
            .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
            .setCullState(RenderStateShard.NO_CULL) // disable cull
            .setLayeringState(DISABLE_DEPTH_TEST) // disable depth test
            .setWriteMaskState(RenderStateShard.COLOR_WRITE) // disable depth mask
            .createCompositeState(false)
    )
}