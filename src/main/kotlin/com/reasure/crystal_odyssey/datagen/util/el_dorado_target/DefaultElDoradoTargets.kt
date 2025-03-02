package com.reasure.crystal_odyssey.datagen.util.el_dorado_target

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.block.ModBlocks
import com.reasure.crystal_odyssey.item.ModItems
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import com.reasure.crystal_odyssey.registry.ElDoradoTarget
import com.reasure.crystal_odyssey.registry.ModRegistries.EL_DORADO_TARGET_REGISTRY_KEY
import com.reasure.crystal_odyssey.util.ModTags
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.Tags

object DefaultElDoradoTargets {
    private val SAPPHIRE: ResourceKey<ElDoradoTarget> = createKey("sapphire")
    private val RUBY: ResourceKey<ElDoradoTarget> = createKey("ruby")
    private val QUARTZ: ResourceKey<ElDoradoTarget> = createKey("quartz")
    private val REDSTONE: ResourceKey<ElDoradoTarget> = createKey("redstone")
    private val LAPIS_LAZULI: ResourceKey<ElDoradoTarget> = createKey("lapis_lazuli")
    private val COAL: ResourceKey<ElDoradoTarget> = createKey("coal")
    private val COPPER: ResourceKey<ElDoradoTarget> = createKey("copper")
    private val IRON: ResourceKey<ElDoradoTarget> = createKey("iron")
    private val GOLD: ResourceKey<ElDoradoTarget> = createKey("gold")
    private val EMERALD: ResourceKey<ElDoradoTarget> = createKey("emerald")
    private val DIAMOND: ResourceKey<ElDoradoTarget> = createKey("diamond")
    private val NETHERITE: ResourceKey<ElDoradoTarget> = createKey("netherite")

    fun bootstrap(context: BootstrapContext<ElDoradoTarget>) {
        register(
            context, SAPPHIRE, ElDoradoTarget(
                Ingredient.of(ModItems.SAPPHIRE),
                FindBlocks.of(
                    listOf(ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModBlocks.SAPPHIRE_BLOCK),
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.sapphire",
                    0x7D5793E1
                ),
                0
            )
        )
        register(
            context, RUBY, ElDoradoTarget(
                Ingredient.of(ModItems.RUBY),
                FindBlocks.of(
                    listOf(ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.RUBY_BLOCK),
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.ruby",
                    0x7DBB4052
                ),
                1
            )
        )
        register(
            context, QUARTZ, ElDoradoTarget(
                Ingredient.of(Tags.Items.GEMS_QUARTZ),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_QUARTZ,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.quartz",
                    0x7DB3A091
                ),
                2
            )
        )
        register(
            context, REDSTONE, ElDoradoTarget(
                Ingredient.of(Tags.Items.DUSTS_REDSTONE),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_REDSTONE,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.redstone",
                    0x7D5C0700
                ),
                3
            )
        )
        register(
            context, LAPIS_LAZULI, ElDoradoTarget(
                Ingredient.of(Tags.Items.GEMS_LAPIS),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_LAPIS_LAZULI,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.lapis_lazuli",
                    0x7D1A3D8F
                ),
                4
            )
        )
        register(
            context, COAL, ElDoradoTarget(
                Ingredient.of(ItemTags.COALS),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_COAL,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.coal",
                    0xBE1C1C1E
                ),
                5
            )
        )
        register(
            context, COPPER, ElDoradoTarget(
                Ingredient.of(Tags.Items.INGOTS_COPPER),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_COPPER,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.copper",
                    0x7D9C4529
                ),
                6
            )
        )
        register(
            context, IRON, ElDoradoTarget(
                Ingredient.of(Tags.Items.INGOTS_IRON),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_IRON,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.iron",
                    0x7DD8D8D8
                ),
                7
            )
        )
        register(
            context, GOLD, ElDoradoTarget(
                Ingredient.of(Tags.Items.INGOTS_GOLD),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_GOLD,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.gold",
                    0x7DB26411
                ),
                8
            )
        )
        register(
            context, EMERALD, ElDoradoTarget(
                Ingredient.of(Tags.Items.GEMS_EMERALD),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_EMERALD,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.emerald",
                    0x7D005300
                ),
                9
            )
        )
        register(
            context, DIAMOND, ElDoradoTarget(
                Ingredient.of(Tags.Items.GEMS_DIAMOND),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_DIAMOND,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.diamond",
                    0x7D11727A
                ),
                10
            )
        )
        register(
            context, NETHERITE, ElDoradoTarget(
                Ingredient.of(Tags.Items.INGOTS_NETHERITE),
                FindBlocks.of(
                    ModTags.Blocks.EL_DORADO_NETHERITE,
                    "item.${CrystalOdyssey.ID}.el_dorado_staff_active.netherite",
                    0xBE271C1D
                ),
                11
            )
        )
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