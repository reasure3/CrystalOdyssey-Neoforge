package com.reasure.crystal_odyssey.inventory.menu

import com.reasure.crystal_odyssey.CrystalOdyssey
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaAnvilMenu
import com.reasure.crystal_odyssey.inventory.menu.custom.ManaInjectorMenu
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.network.IContainerFactory
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.neoforge.forge.getValue
import java.util.function.Supplier

object ModMenuTypes {
    val MENUS: DeferredRegister<MenuType<*>> = DeferredRegister.create(Registries.MENU, CrystalOdyssey.ID)

    val MANA_INJECTOR_MENU: MenuType<ManaInjectorMenu> by registerMenuType("mana_injector_menu", ::ManaInjectorMenu)

    val MANA_ANVIL_MENU: MenuType<ManaAnvilMenu> by registerMenuType("mana_anvil_menu", ::ManaAnvilMenu)

    private fun <T : AbstractContainerMenu?> registerMenuType(
        name: String,
        factory: IContainerFactory<T>
    ): DeferredHolder<MenuType<*>, MenuType<T>> {
        return MENUS.register(name, Supplier { IMenuTypeExtension.create(factory) })
    }
}