package com.reasure.crystal_odyssey.datagen.util.el_dorado_target

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey

object DefaultElDoradoTargets {
    val SAPPHIRE_KEY: ResourceKey<ElDoradoTarget> = createKey("sapphire")
    val RUBY_KEY: ResourceKey<ElDoradoTarget> = createKey("ruby")
    val QUARTZ_KEY: ResourceKey<ElDoradoTarget> = createKey("quartz")
    val REDSTONE_KEY: ResourceKey<ElDoradoTarget> = createKey("redstone")
    val LAPIS_LAZULI_KEY: ResourceKey<ElDoradoTarget> = createKey("lapis_lazuli")
    val COAL_KEY: ResourceKey<ElDoradoTarget> = createKey("coal")
    val COPPER_KEY: ResourceKey<ElDoradoTarget> = createKey("copper")
    val IRON_KEY: ResourceKey<ElDoradoTarget> = createKey("iron")
    val GOLD_KEY: ResourceKey<ElDoradoTarget> = createKey("gold")
    val EMERALD_KEY: ResourceKey<ElDoradoTarget> = createKey("emerald")
    val DIAMOND_KEY: ResourceKey<ElDoradoTarget> = createKey("diamond")
    val NETHERITE_KEY: ResourceKey<ElDoradoTarget> = createKey("netherite")

    private val TARGETS: Map<ResourceKey<ElDoradoTarget>, ElDoradoTarget> = mapOf(
        SAPPHIRE_KEY to ElDoradoTarget(
            FindBlocks.of(
                listOf(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.SAPPHIRE_BLOCK),
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.sapphire",
                0x7D5793E1
            ), 0
        ),

        RUBY_KEY to ElDoradoTarget(
            FindBlocks.of(
                listOf(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.RUBY_BLOCK),
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.ruby",
                0x7DBB4052
            ), 1
        ),

        QUARTZ_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_QUARTZ,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.quartz",
                0x7DB3A091
            ), 2
        ),

        REDSTONE_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_REDSTONE,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.redstone",
                0x7D5C0700
            ), 3
        ),

        LAPIS_LAZULI_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_LAPIS_LAZULI,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.lapis_lazuli",
                0x7D1A3D8F
            ), 4
        ),

        COAL_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_COAL,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.coal",
                0xBE1C1C1E
            ), 5
        ),

        COPPER_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_COPPER,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.copper",
                0x7D9C4529
            ), 6
        ),

        IRON_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_IRON,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.iron",
                0x7DD8D8D8
            ), 7
        ),

        GOLD_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_GOLD,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.gold",
                0x7DB26411
            ), 8
        ),

        EMERALD_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_EMERALD,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.emerald",
                0x7D005300
            ), 9
        ),

        DIAMOND_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_DIAMOND,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.diamond",
                0x7D11727A
            ),
            10
        ),

        NETHERITE_KEY to ElDoradoTarget(
            FindBlocks.of(
                ModTags.Blocks.EL_DORADO_NETHERITE,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.netherite",
                0xBE271C1D
            ), 11
        ),
    )

    fun bootstrap(context: BootstrapContext<ElDoradoTarget>) {
        TARGETS.forEach { (key, target) -> register(context, key, target) }
    }

    private fun createKey(path: String): ResourceKey<ElDoradoTarget> {
        return ResourceKey.create(EL_DORADO_TARGET_REGISTRY_KEY, CrystalOdyssey.modLoc(path))
    }

    private fun register(
        context: BootstrapContext<ElDoradoTarget>,
        key: ResourceKey<ElDoradoTarget>,
        data: ElDoradoTarget
    ) {
        context.register(key, data)
    }
}