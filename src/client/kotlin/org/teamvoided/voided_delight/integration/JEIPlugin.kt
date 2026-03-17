package org.teamvoided.voided_delight.integration


import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.constants.RecipeTypes
import mezz.jei.api.registration.IRecipeCatalystRegistration
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes

@Suppress("unused")
@JeiPlugin
object JEIPlugin : IModPlugin {
    val ID = id("jei_plugin")
    override fun getPluginUid() = ID
    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        registration.addRecipeCatalyst(VDItems.NETHERITE_COOKING_POT, FDRecipeTypes.COOKING)
        registration.addRecipeCatalyst(VDItems.NETHERITE_SKILLET, RecipeTypes.CAMPFIRE_COOKING)
    }
}

