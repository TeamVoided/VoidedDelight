package org.teamvoided.voided_delight.data.gen.data.recipe

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions.anyModsLoaded
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.VDCompat
import org.teamvoided.voided_delight.data.tags.VDItemTags
import org.teamvoided.voided_delight.init.VDItems
import java.util.concurrent.CompletableFuture
import vectorwing.farmersdelight.common.registry.ModItems as FDItems

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {

        offerNetheriteUpgradeRecipe(e, FDItems.COOKING_POT.get(), RecipeCategory.MISC, VDItems.NETHERITE_COOKING_POT)
        offerNetheriteUpgradeRecipe(e, FDItems.SKILLET.get(), RecipeCategory.MISC, VDItems.NETHERITE_SKILLET)

        dnd(conditional(e, VDCompat.DND))
        whitePumpkins(conditional(e, VDCompat.WHITE_PUMPKINS))
        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, FDItems.FRUIT_SALAD.get())
            .ingredient(Items.APPLE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(VDItemTags.PUMPKIN_SLICES)
            .criterion(ConventionalItemTags.BERRY_FOODS)
            .criterion(VDItemTags.PUMPKIN_SLICES)
            .offerTo(conditional(e, VDCompat.DND, VDCompat.WHITE_PUMPKINS))
    }

    fun conditional(e: RecipeExporter, vararg id: String): RecipeExporter = withConditions(e, anyModsLoaded(*id))

}