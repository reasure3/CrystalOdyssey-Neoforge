package com.reasure.crystal_odyssey.datagen.util.el_dorado_target

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.datagen.util.DatagenTags
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.item.components.custom.OverlayItems
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.ItemTags
import net.neoforged.neoforge.common.Tags

object DefaultElDoradoTargets {
    val COAL_KEY: ResourceKey<ElDoradoTarget> = createKey("coal")
    val IRON_KEY: ResourceKey<ElDoradoTarget> = createKey("iron")
    val COPPER_KEY: ResourceKey<ElDoradoTarget> = createKey("copper")
    val GOLD_KEY: ResourceKey<ElDoradoTarget> = createKey("gold")
    val REDSTONE_KEY: ResourceKey<ElDoradoTarget> = createKey("redstone")
    val EMERALD_KEY: ResourceKey<ElDoradoTarget> = createKey("emerald")
    val LAPIS_KEY: ResourceKey<ElDoradoTarget> = createKey("lapis")
    val DIAMOND_KEY: ResourceKey<ElDoradoTarget> = createKey("diamond")
    val QUARTZ_KEY: ResourceKey<ElDoradoTarget> = createKey("quartz")
    val NETHERITE_KEY: ResourceKey<ElDoradoTarget> = createKey("netherite")
    val AMETHYST_KEY: ResourceKey<ElDoradoTarget> = createKey("amethyst")
    val RUBY_KEY: ResourceKey<ElDoradoTarget> = createKey("ruby")
    val SAPPHIRE_KEY: ResourceKey<ElDoradoTarget> = createKey("sapphire")

    private val TARGETS: Map<ResourceKey<ElDoradoTarget>, ElDoradoTarget> = mapOf(
        COAL_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.coal"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_COAL, 0x7D2E2E2E),
            OverlayItems.of(ItemTags.COALS)
        ),

        IRON_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.iron"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_IRON, 0x32D8D8D8),
            OverlayItems.of(Tags.Items.INGOTS_IRON)
        ),

        COPPER_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.copper"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_COPPER, 0x32E77C56),
            OverlayItems.of(Tags.Items.INGOTS_COPPER)
        ),

        GOLD_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.gold"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_GOLD, 0x32FDF55F),
            OverlayItems.of(Tags.Items.INGOTS_GOLD)
        ),

        REDSTONE_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.redstone"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_REDSTONE, 0x32AA0F01),
            OverlayItems.of(Tags.Items.DUSTS_REDSTONE)
        ),

        EMERALD_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.emerald"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_EMERALD, 0x3217DD62),
            OverlayItems.of(Tags.Items.GEMS_EMERALD)
        ),

        LAPIS_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.lapis"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_LAPIS_LAZULI, 0x321C53A8),
            OverlayItems.of(Tags.Items.GEMS_LAPIS)
        ),

        DIAMOND_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.diamond"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_DIAMOND, 0x3220C5B5),
            OverlayItems.of(Tags.Items.GEMS_DIAMOND)
        ),

        QUARTZ_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.quartz"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_QUARTZ, 0x32F7F5F2),
            OverlayItems.of(Tags.Items.GEMS_QUARTZ)
        ),

        NETHERITE_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.netherite"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_NETHERITE, 0x60654740),
            OverlayItems.of(Tags.Items.INGOTS_NETHERITE)
        ),

        AMETHYST_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.amethyst"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_AMETHYST, 0x328D6ACC),
            OverlayItems.of(Tags.Items.GEMS_AMETHYST)
        ),

        RUBY_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.ruby"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_RUBY, 0x32BB4052),
            OverlayItems.of(DatagenTags.Items.GEMS_RUBY)
        ),

        SAPPHIRE_KEY to ElDoradoTarget(
            Component.translatable("item.${CrystalOdyssey.ID}.el_dorado_staff_active.sapphire"),
            FindBlocks.of(DatagenTags.Blocks.EL_DORADO_SAPPHIRE, 0x325793E1),
            OverlayItems.of(DatagenTags.Items.GEMS_SAPPHIRE)
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