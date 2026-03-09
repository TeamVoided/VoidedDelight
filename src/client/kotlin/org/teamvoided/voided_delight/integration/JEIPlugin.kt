package org.teamvoided.voided_delight.integration


import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.constants.RecipeTypes
import mezz.jei.api.registration.IRecipeCatalystRegistration
import mezz.jei.api.registration.IRecipeTransferRegistration
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes

@Suppress("unused")
@JeiPlugin
object JEIPlugin : IModPlugin {
    @JvmStatic
    val ID = id("jei_plugin")

    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        registration.addRecipeCatalyst(VDItems.NETHERITE_COOKING_POT, FDRecipeTypes.COOKING)
        registration.addRecipeCatalyst(VDItems.NETHERITE_SKILLET, RecipeTypes.CAMPFIRE_COOKING)
    }

    override fun registerRecipeTransferHandlers(registration: IRecipeTransferRegistration?) {
//        registration.addRecipeTransferHandler(CookingPotMenu.class, ModMenuTypes.COOKING_POT.get(), FDRecipeTypes.COOKING, 0, 6, 9, 36);
    }

    override fun getPluginUid() = ID
}

