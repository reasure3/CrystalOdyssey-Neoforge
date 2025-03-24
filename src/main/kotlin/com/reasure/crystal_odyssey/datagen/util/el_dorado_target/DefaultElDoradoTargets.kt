package com.reasure.crystal_odyssey.datagen.util.el_dorado_target

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.datagen.util.DatagenTags
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey

object DefaultElDoradoTargets {
    val COAL_KEY: ResourceKey<ElDoradoTarget> = createKey("coal")
    val IRON_KEY: ResourceKey<ElDoradoTarget> = createKey("iron")
    val COPPER_KEY: ResourceKey<ElDoradoTarget> = createKey("copper")
    val GOLD_KEY: ResourceKey<ElDoradoTarget> = createKey("gold")
    val REDSTONE_KEY: ResourceKey<ElDoradoTarget> = createKey("redstone")
    val EMERALD_KEY: ResourceKey<ElDoradoTarget> = createKey("emerald")
    val LAPIS_LAZULI_KEY: ResourceKey<ElDoradoTarget> = createKey("lapis")
    val DIAMOND_KEY: ResourceKey<ElDoradoTarget> = createKey("diamond")
    val QUARTZ_KEY: ResourceKey<ElDoradoTarget> = createKey("quartz")
    val NETHERITE_KEY: ResourceKey<ElDoradoTarget> = createKey("netherite")
    val AMETHYST_KEY: ResourceKey<ElDoradoTarget> = createKey("amethyst")
    val RUBY_KEY: ResourceKey<ElDoradoTarget> = createKey("ruby")
    val SAPPHIRE_KEY: ResourceKey<ElDoradoTarget> = createKey("sapphire")

    private val TARGETS: Map<ResourceKey<ElDoradoTarget>, ElDoradoTarget> = mapOf(
        COAL_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_COAL,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.coal",
                0x7D2E2E2E
            )
        ),

        IRON_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_IRON,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.iron",
                0x32D8D8D8
            )
        ),

        COPPER_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_COPPER,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.copper",
                0x32E77C56
            )
        ),

        GOLD_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_GOLD,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.gold",
                0x32FDF55F
            )
        ),

        REDSTONE_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_REDSTONE,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.redstone",
                0x32AA0F01
            )
        ),

        EMERALD_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_EMERALD,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.emerald",
                0x3217DD62
            )
        ),

        LAPIS_LAZULI_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_LAPIS_LAZULI,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.lapis",
                0x321C53A8
            )
        ),

        DIAMOND_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_DIAMOND,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.diamond",
                0x3220C5B5
            )
        ),

        QUARTZ_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_QUARTZ,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.quartz",
                0x32F7F5F2
            )
        ),

        NETHERITE_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_NETHERITE,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.netherite",
                0x60654740
            )
        ),

        AMETHYST_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_AMETHYST,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.amethyst",
                0x328D6ACC
            )
        ),

        RUBY_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_RUBY,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.ruby",
                0x32BB4052
            )
        ),

        SAPPHIRE_KEY to ElDoradoTarget(
            FindBlocks.of(
                DatagenTags.Blocks.EL_DORADO_SAPPHIRE,
                "item.${CrystalOdyssey.ID}.el_dorado_staff_active.sapphire",
                0x325793E1
            )
        )
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