package com.reasure.crystal_odyssey.registry

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.item.components.custom.OverlayItems
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.util.ExtraCodecs

data class ElDoradoTarget(
    val staffName: Component = Component.empty(),
    val findBlocks: FindBlocks = FindBlocks.EMPTY,
    val overlayItems: OverlayItems = OverlayItems.EMPTY,
    val priority: Int = 0
) {
    fun isNotEmpty(): Boolean = findBlocks.isNotEmpty()

    companion object {
        val CODEC: Codec<ElDoradoTarget> = RecordCodecBuilder.create { instance ->
            instance.group(
                ComponentSerialization.CODEC.fieldOf("staff_name").forGetter(ElDoradoTarget::staffName),
                FindBlocks.CODEC.fieldOf("find_blocks").forGetter(ElDoradoTarget::findBlocks),
                OverlayItems.CODEC.optionalFieldOf("overlay_items", OverlayItems.EMPTY)
                    .forGetter(ElDoradoTarget::overlayItems),
                ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("priority", 0).forGetter(ElDoradoTarget::priority)
            ).apply(instance, ::ElDoradoTarget)
        }
    }
}
