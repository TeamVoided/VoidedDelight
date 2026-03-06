package org.teamvoided.voided_delight.data.gen.providers.recipe

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.VoidedDelight
import org.teamvoided.voided_delight.data.tags.VDItemTags
import java.util.concurrent.CompletableFuture
import vectorwing.farmersdelight.common.registry.ModItems as FDItems

@Suppress("MemberVisibilityCanBePrivate")
class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        dnd(conditional(e, VoidedDelight.DND))
        whitePumpkins(conditional(e, VoidedDelight.WHITE_PUMPKINS))
        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, FDItems.FRUIT_SALAD.get())
            .ingredient(Items.APPLE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(VDItemTags.PUMPKIN_SLICES)
            .criterion(ConventionalItemTags.BERRY_FOODS)
            .criterion(VDItemTags.PUMPKIN_SLICES)
            .offerTo(conditional(e, VoidedDelight.DND, VoidedDelight.WHITE_PUMPKINS))
    }

    fun conditional(e: RecipeExporter, vararg id: String): RecipeExporter =
        withConditions(e, ResourceConditions.anyModsLoaded(*id))

}