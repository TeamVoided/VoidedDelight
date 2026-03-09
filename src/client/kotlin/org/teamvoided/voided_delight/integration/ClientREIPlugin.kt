package org.teamvoided.voided_delight.integration

import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.plugin.common.BuiltinPlugin
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers
import vectorwing.farmersdelight.common.registry.ModItems as FDItems


@Suppress("unused")
class ClientREIPlugin : REIClientPlugin {
    override fun registerScreens(registry: ScreenRegistry?) {
//        registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), CookingPotScreen.class, REICategoryIdentifiers.COOKING);
    }

    override fun registerCategories(registry: CategoryRegistry) {
        registry.addWorkstations(REICategoryIdentifiers.COOKING, EntryStacks.of(VDItems.NETHERITE_COOKING_POT))
          listOf(FDItems.STOVE.get(), FDItems.SKILLET.get(), VDItems.NETHERITE_SKILLET)
            .forEach { registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(it)) }
    }

    override fun registerTransferHandlers(registry: TransferHandlerRegistry?) {
//        registry.register(SimpleTransferHandler.create(CookingPotMenu.class, REICategoryIdentifiers.COOKING, new SimpleTransferHandler.IntRange(0, 6)));
    }
}
