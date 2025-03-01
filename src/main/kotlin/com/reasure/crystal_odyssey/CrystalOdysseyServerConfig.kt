package com.reasure.crystal_odyssey

import net.neoforged.neoforge.common.ModConfigSpec
import kotlin.reflect.KProperty

class CrystalOdysseyServerConfig(builder: ModConfigSpec.Builder) {
    val elDoradoStaffRange: Int by builder
        .translation("${CrystalOdyssey.ID}.configuration.ElDoradoStaff.Range")
        .comment("This is the mineral detection range of the El Dorado Staff.")
        .comment("If this value is 10, it will detect an area of 10 squares around the player, or a total of 21 x 21 x 21.")
        .defineInRange("ElDoradoStaff.Range", 10, 0, 30)

    val elDoradoStaffRetainTick: Int by builder
        .translation("${CrystalOdyssey.ID}.configuration.ElDoradoStaff.RetainTick")
        .comment("This is the duration (in ticks) for which the mineral detection border is visible.")
        .comment("This value determines how long the border will remain on the screen after detecting mineral.")
        .comment("If the player detects mineral again before the duration expires, the previous border will disappear.")
        .defineInRange("ElDoradoStaff.RetainTick", 200, 0, Int.MAX_VALUE)

    companion object {
        val SERVER: CrystalOdysseyServerConfig
        val SPEC: ModConfigSpec

        init {
            with(ModConfigSpec.Builder().configure(::CrystalOdysseyServerConfig)) {
                SERVER = left
                SPEC = right
            }
        }
    }

    operator fun ModConfigSpec.IntValue.getValue(any: Any?, property: KProperty<*>): Int {
        return get()
    }
}