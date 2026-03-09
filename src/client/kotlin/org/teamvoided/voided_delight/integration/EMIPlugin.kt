package org.teamvoided.voided_delight.integration

import dev.emi.emi.api.EmiEntrypoint
import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories.CAMPFIRE_COOKING
import dev.emi.emi.api.stack.EmiStack
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.integration.emi.FDRecipeCategories
import vectorwing.farmersdelight.common.registry.ModItems as FDItems

@Suppress("unused")
@EmiEntrypoint
object EMIPlugin : EmiPlugin {
    override fun register(registry: EmiRegistry) {
        registry.addWorkstation(FDRecipeCategories.COOKING, EmiStack.of(VDItems.NETHERITE_COOKING_POT))
        listOf(FDItems.STOVE.get(), FDItems.SKILLET.get(), VDItems.NETHERITE_SKILLET)
            .forEach { registry.addWorkstation(CAMPFIRE_COOKING, EmiStack.of(it)) }
    }
}
