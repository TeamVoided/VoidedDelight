package org.teamvoided.voided_delight.integration

import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.common.util.EntryStacks
import me.shedaniel.rei.plugin.common.BuiltinPlugin
import org.teamvoided.voided_delight.FDItems
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers


@Suppress("unused")
class ClientREIPlugin : REIClientPlugin {
    override fun registerCategories(registry: CategoryRegistry) {
        registry.addWorkstations(REICategoryIdentifiers.COOKING, EntryStacks.of(VDItems.NETHERITE_COOKING_POT))
        registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(FDItems.STOVE.get()))
        registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(FDItems.SKILLET.get()))
        registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(VDItems.NETHERITE_SKILLET))
    }
}
