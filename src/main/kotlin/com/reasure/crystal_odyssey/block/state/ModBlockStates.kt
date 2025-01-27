package com.reasure.crystal_odyssey.block.state

import net.minecraft.world.level.block.state.properties.IntegerProperty

object ModBlockStates {
    val LANTERN_LEVEL: IntegerProperty = IntegerProperty.create("lantern_level", 0, 2)
}