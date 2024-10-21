package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks
import org.teamvoided.dusk_autumn.util.offerReversibleCompactingRecipes4
import org.teamvoided.dusk_autumn.util.smeltDefault
import org.teamvoided.voided_delight.block.VDFamilies.recipesBlockFamilies
import org.teamvoided.voided_delight.init.VDBlocks
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }


        e.smeltDefault(VDBlocks.CRYSTAL_CANDY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
        e.offerReversibleCompactingRecipes4(
            RecipeCategory.MISC, DnDItems.ROCK_CANDY_SHARD,
            RecipeCategory.BUILDING_BLOCKS, DnDStoneBlocks.ROCK_CANDY_BLOCK
        )

//        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.FARMERS_HAT)
//            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
//            .ingredient('@', Ingredient.ofItems(Items.STRING))
//            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
//            .pattern("###")
//            .pattern("@%@")
//            .pattern("# #")
//            .criterion(DnDItems.FARMERS_HAT).offerTo(e)
    }


//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}