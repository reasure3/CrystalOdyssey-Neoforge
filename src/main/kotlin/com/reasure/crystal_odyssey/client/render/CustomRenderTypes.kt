package com.reasure.crystal_odyssey.client.render

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import com.reasure.crystal_odyssey.CrystalOdyssey
import net.minecraft.client.renderer.RenderStateShard
import net.minecraft.client.renderer.RenderType

@Suppress("INACCESSIBLE_TYPE")
object CustomRenderTypes {
    val QUADS_NO_DEPTH: RenderType = RenderType.create(
        "${CrystalOdyssey.ID}:quads_no_depth",
        DefaultVertexFormat.POSITION_COLOR,
        VertexFormat.Mode.QUADS,
        2048,
        false,
        true,
        RenderType.CompositeState.builder()
            .setShaderState(RenderStateShard.POSITION_COLOR_SHADER)
            .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
            .setCullState(RenderStateShard.NO_CULL) // disable cull
            .setDepthTestState(RenderStateShard.NO_DEPTH_TEST) // disable depth test
            .setWriteMaskState(RenderStateShard.COLOR_WRITE) // disable depth mask
            .createCompositeState(false)
    )
}