package com.reasure.crystal_odyssey.registry

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import net.minecraft.util.ExtraCodecs

data class ElDoradoTarget(val findBlocks: FindBlocks, val priority: Int = 0) {
    fun isNotEmpty(): Boolean = findBlocks.isNotEmpty()

    companion object {
        val CODEC: Codec<ElDoradoTarget> = RecordCodecBuilder.create { instance ->
            instance.group(
                FindBlocks.CODEC.fieldOf("find_blocks").forGetter(ElDoradoTarget::findBlocks),
                ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("priority", 0).forGetter(ElDoradoTarget::priority)
            ).apply(instance, ::ElDoradoTarget)
        }
    }
}

