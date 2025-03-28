package com.reasure.crystal_odyssey.registry

import com.mojang.serialization.Codec
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.TagKey
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.item.Item

class RegistryHolder<T>(val registry: Registry<T>, val locs: List<ExtraCodecs.TagOrElementLocation>) {
    private var initialized = false
    private val _elements: MutableList<T> = mutableListOf()
    val elements: List<T>
        get() {
            if (!initialized) lookup()
            return _elements
        }

    private fun lookup() {
        initialized = true

        if (locs.isEmpty()) {
            return
        }

        val set = mutableSetOf<T>()
        for (loc in locs) {
            if (loc.tag) {
                registry.getTag(TagKey.create(registry.key(), loc.id))
                    .ifPresent { holder -> set.addAll(holder.map(Holder<T>::value)) }
            } else {
                registry.getOptional(loc.id).ifPresent(set::add)
            }
        }
        _elements.addAll(set)
    }

    companion object {
        val ITEM_CODEC: Codec<RegistryHolder<Item>> = codec { BuiltInRegistries.ITEM }

        fun <T> codec(registry: () -> Registry<T>): Codec<RegistryHolder<T>> {
            return ExtraCodecs.TAG_OR_ELEMENT_ID.listOf()
                .xmap({ RegistryHolder(registry(), it) }, RegistryHolder<T>::locs)
        }
    }
}
