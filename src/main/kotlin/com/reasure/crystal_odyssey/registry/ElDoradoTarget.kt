package com.reasure.crystal_odyssey.registry

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.crafting.Ingredient

data class ElDoradoTarget(val ingredient: Ingredient, val findBlocks: FindBlocks, val priority: Int) {
    fun isNotEmpty(): Boolean = findBlocks.isNotEmpty()

    companion object {
        val CODEC: Codec<ElDoradoTarget> = RecordCodecBuilder.create { instance ->
            instance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ElDoradoTarget::ingredient),
                FindBlocks.CODEC.fieldOf("find_blocks").forGetter(ElDoradoTarget::findBlocks),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("priority").forGetter(ElDoradoTarget::priority)
            ).apply(instance, ::ElDoradoTarget)
        }
    }
}

