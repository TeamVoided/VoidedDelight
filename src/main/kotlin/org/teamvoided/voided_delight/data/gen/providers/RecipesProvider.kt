package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.criterion
import org.teamvoided.dusk_autumn.util.offerReversibleCompactingRecipes4
import org.teamvoided.dusk_autumn.util.smeltDefault
import org.teamvoided.voided_delight.block.VDFamilies.recipesBlockFamilies
import org.teamvoided.voided_delight.compat.CookingPotRecipeBuilder
import org.teamvoided.voided_delight.data.tags.VDItemTags
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.common.registry.ModItems
import vectorwing.farmersdelight.common.tag.CommonTags
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }

        e.smeltDefault(VDBlocks.CRYSTAL_CANDY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
        e.offerReversibleCompactingRecipes4(
            RecipeCategory.MISC, VDItems.CRYSTAL_CANDY_SHARD,
            RecipeCategory.BUILDING_BLOCKS, VDBlocks.CRYSTAL_CANDY_BLOCK
        )
        pumpkins(e)

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


    fun pumpkins(e: RecipeExporter) {
        e.make1to1(DnDItems.LANTERN_PUMPKIN_SEEDS, VDItems.LANTERN_PUMPKIN_SLICE)
        e.make1to1(DnDItems.MOSSKIN_PUMPKIN_SEEDS, VDItems.MOSSKIN_PUMPKIN_SLICE)
        e.make1to1(DnDItems.GLOOM_PUMPKIN_SEEDS, VDItems.GLOOM_PUMPKIN_SLICE)
        e.make1to1(DnDItems.PALE_PUMPKIN_SEEDS, VDItems.PALE_PUMPKIN_SLICE)

        e.make2x2(DnDFloraBlocks.LANTERN_PUMPKIN, VDItems.LANTERN_PUMPKIN_SLICE)
        e.make2x2(DnDFloraBlocks.MOSSKIN_PUMPKIN, VDItems.MOSSKIN_PUMPKIN_SLICE)
        e.make2x2(DnDFloraBlocks.GLOOM_PUMPKIN, VDItems.GLOOM_PUMPKIN_SLICE)
        e.make2x2(DnDFloraBlocks.PALE_PUMPKIN, VDItems.PALE_PUMPKIN_SLICE)

        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, ModItems.FRUIT_SALAD.get())
            .ingredient(Items.APPLE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(Items.MELON_SLICE)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(ConventionalItemTags.BERRY_FOODS)
            .ingredient(VDItemTags.PUMPKIN_SLICES)
            .criterion(ConventionalItemTags.BERRY_FOODS)
            .criterion(VDItemTags.PUMPKIN_SLICES)
            .offerTo(e)

        e.makeSoup(VDItems.LANTERN_PUMPKIN_SOUP, VDItems.LANTERN_PUMPKIN_SLICE)
        e.makeSoup(VDItems.MOSSKIN_PUMPKIN_SOUP, VDItems.MOSSKIN_PUMPKIN_SLICE)
        e.makeSoup(VDItems.GLOOM_PUMPKIN_SOUP, VDItems.GLOOM_PUMPKIN_SLICE)
        e.makeSoup(VDItems.PALE_PUMPKIN_SOUP, VDItems.PALE_PUMPKIN_SLICE)
    }
}

fun RecipeExporter.makeSoup(output: ItemConvertible, input: ItemConvertible) {
    CookingPotRecipeBuilder.cookingPotRecipe(output, 1, 200, 1.0f, Items.BOWL)
        .addIngredient(input)
        .addIngredient(CommonTags.FOODS_CABBAGE)
        .addIngredient(CommonTags.FOODS_RAW_BACON)
        .addIngredient(CommonTags.FOODS_MILK)
        .unlockedByAnyIngredient(input)
        .offerTo(this)
}

fun RecipeExporter.makePotBowl(output: ItemConvertible, vararg input: ItemConvertible) =
    this.potRecipe(output, 1, 200, 1.0f, Items.BOWL, *input)

fun RecipeExporter.potRecipe(
    output: ItemConvertible, count: Int, cookingTime: Int, experience: Float, container: ItemConvertible,
    vararg input: ItemConvertible
) {
    val recipe = CookingPotRecipeBuilder.cookingPotRecipe(output, count, cookingTime, experience, container)
    input.forEach { recipe.addIngredient(it) }
    recipe.unlockedByAnyIngredient(*input)
        .offerTo(this)
}

fun RecipeExporter.make1to1(output: ItemConvertible, input: ItemConvertible) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, output)
        .ingredient(input)
        .criterion(input)
        .offerTo(this)
}

fun RecipeExporter.make2x2(output: ItemConvertible, input: ItemConvertible, count: Int = 1) {
    ShapedRecipeJsonFactory.create(RecipeCategory.MISC, output, count)
        .pattern("##")
        .pattern("##")
        .ingredient('#', Ingredient.ofItems(input))
        .criterion(DnDItems.FARMERS_HAT).offerTo(this)
}
