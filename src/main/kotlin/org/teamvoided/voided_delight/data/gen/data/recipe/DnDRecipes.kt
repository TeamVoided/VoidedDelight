package org.teamvoided.voided_delight.data.gen.data.recipe

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.data.server.RecipesProvider.*
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.offerReversibleCompactingRecipes4
import org.teamvoided.dusks_and_dungeons.util.datagen.smeltDefault
import org.teamvoided.voided_delight.block.VDFamilies.CRYSTAL_CANDY_FAMILY
import org.teamvoided.voided_delight.compat.CookingPotRecipeBuilder
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems


fun dnd(e: RecipeExporter) {
    // region Candy
    generateFamily(e, CRYSTAL_CANDY_FAMILY, FeatureFlags.VANILLA_SET)
    e.smeltDefault(VDBlocks.CRYSTAL_CANDY_BLOCK, DnDBlocks.CORN_SYRUP_BLOCK)
    e.offerReversibleCompactingRecipes4(
        RecipeCategory.MISC, VDItems.CRYSTAL_CANDY_SHARD,
        RecipeCategory.BUILDING_BLOCKS, VDBlocks.CRYSTAL_CANDY_BLOCK
    )

    e.candied(VDItems.CANDY_BERRY, Ingredient.ofTag(ConventionalItemTags.BERRY_FOODS))
    e.candied(VDItems.CANDY_CORN, Ingredient.ofItems(DnDItems.CORN_KERNELS))
    e.candied(VDItems.CANDY_CLOUD, Ingredient.ofItems(Items.WIND_CHARGE))

    ShapedRecipeJsonFactory.create(RecipeCategory.FOOD, VDItems.MARSHMARROW, 8)
        .ingredient('#', DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient('X', Items.BONE_MEAL)
        .pattern("X#X")
        .pattern("#X#")
        .pattern("X#X")
        .criterion(DnDItems.CORN_SYRUP_BOTTLE)
        .offerTo(e)
    ShapedRecipeJsonFactory.create(RecipeCategory.FOOD, VDItems.LOLLIPOP)
        .ingredient('#', DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient('X', Items.STICK)
        .ingredient('0', DnDBlocks.CORN_SYRUP_BLOCK)
        .ingredient('%', Items.RED_DYE)
        .pattern(" #%")
        .pattern(" 0#")
        .pattern("X  ")
        .criterion(DnDItems.CORN_SYRUP_BOTTLE)
        .offerTo(e)
    ShapedRecipeJsonFactory.create(RecipeCategory.FOOD, VDItems.SYRUP_APPLE)
        .ingredient('#', DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient('X', Items.STICK)
        .ingredient('0', Items.APPLE)
        .pattern(" # ")
        .pattern("#0#")
        .pattern("X# ")
        .criterion(DnDItems.CORN_SYRUP_BOTTLE)
        .offerTo(e)
    // endregion

    // region Pumpkin
    e.make1to1(DnDItems.LANTERN_PUMPKIN_SEEDS, VDItems.LANTERN_PUMPKIN_SLICE)
    e.make1to1(DnDItems.MOSSKIN_PUMPKIN_SEEDS, VDItems.MOSSKIN_PUMPKIN_SLICE)
    e.make1to1(DnDItems.GLOOM_PUMPKIN_SEEDS, VDItems.GLOOM_PUMPKIN_SLICE)
    e.make1to1(DnDItems.PALE_PUMPKIN_SEEDS, VDItems.PALE_PUMPKIN_SLICE)

    e.make2x2(DnDBlocks.LANTERN_PUMPKIN, VDItems.LANTERN_PUMPKIN_SLICE)
    e.make2x2(DnDBlocks.MOSSKIN_PUMPKIN, VDItems.MOSSKIN_PUMPKIN_SLICE)
    e.make2x2(DnDBlocks.GLOOM_PUMPKIN, VDItems.GLOOM_PUMPKIN_SLICE)
    e.make2x2(DnDBlocks.PALE_PUMPKIN, VDItems.PALE_PUMPKIN_SLICE)

    e.makePumpkinSoup(VDItems.LANTERN_PUMPKIN_SOUP, VDItems.LANTERN_PUMPKIN_SLICE)
    e.makePumpkinSoup(VDItems.MOSSKIN_PUMPKIN_SOUP, VDItems.MOSSKIN_PUMPKIN_SLICE)
    e.makePumpkinSoup(VDItems.GLOOM_PUMPKIN_SOUP, VDItems.GLOOM_PUMPKIN_SLICE)
    e.makePumpkinSoup(VDItems.PALE_PUMPKIN_SOUP, VDItems.PALE_PUMPKIN_SLICE)

    e.stuffThePumpkin(VDBlocks.STUFFED_LANTERN_PUMPKIN, VDItems.LANTERN_PUMPKIN_SLICE, DnDBlocks.LANTERN_PUMPKIN)
    e.stuffThePumpkin(VDBlocks.STUFFED_MOSSKIN_PUMPKIN, VDItems.MOSSKIN_PUMPKIN_SLICE, DnDBlocks.MOSSKIN_PUMPKIN)
    e.stuffThePumpkin(VDBlocks.STUFFED_GLOOM_PUMPKIN, VDItems.GLOOM_PUMPKIN_SLICE, DnDBlocks.GLOOM_PUMPKIN)
    e.stuffThePumpkin(VDBlocks.STUFFED_PALE_PUMPKIN, VDItems.PALE_PUMPKIN_SLICE, DnDBlocks.PALE_PUMPKIN)

    e.makePie(VDBlocks.LANTERN_PUMPKIN_PIE, DnDBlocks.LANTERN_PUMPKIN)
    e.makePie(VDBlocks.MOSSKIN_PUMPKIN_PIE, DnDBlocks.MOSSKIN_PUMPKIN)
    e.makePie(VDBlocks.GLOOM_PUMPKIN_PIE, DnDBlocks.GLOOM_PUMPKIN)
    e.makePie(VDBlocks.PALE_PUMPKIN_PIE, DnDBlocks.PALE_PUMPKIN)

    e.make2x2(VDBlocks.LANTERN_PUMPKIN_PIE, VDItems.LANTERN_PUMPKIN_PIE_SLICE, 1, "_from_slices")
    e.make2x2(VDBlocks.MOSSKIN_PUMPKIN_PIE, VDItems.MOSSKIN_PUMPKIN_PIE_SLICE, 1, "_from_slices")
    e.make2x2(VDBlocks.GLOOM_PUMPKIN_PIE, VDItems.GLOOM_PUMPKIN_PIE_SLICE, 1, "_from_slices")
    e.make2x2(VDBlocks.PALE_PUMPKIN_PIE, VDItems.PALE_PUMPKIN_PIE_SLICE, 1, "_from_slices")
    // endregion

    // region Golden Beetroot
    ShapelessRecipeJsonFactory.create(RecipeCategory.FOOD, VDItems.GOLDEN_BEETROOT_SOUP)
        .ingredient(Items.BOWL)
        .ingredient(DnDItems.GOLDEN_BEETROOT, 6)
        .criterion("has_golden_beetroot", conditionsFromItem(DnDItems.GOLDEN_BEETROOT))
        .offerTo(e)

    CookingPotRecipeBuilder.cookingPotRecipe(VDItems.GOLDEN_BEETROOT_SOUP, 1, 200, 1.0f, Items.BOWL)
        .addIngredient(DnDItems.GOLDEN_BEETROOT)
        .addIngredient(DnDItems.GOLDEN_BEETROOT)
        .addIngredient(DnDItems.GOLDEN_BEETROOT)
        .unlockedByAnyIngredient(DnDItems.GOLDEN_BEETROOT)
        .offerTo(e)

    offerReversibleCompactingRecipesWithInputItemGroup(
        e,
        RecipeCategory.MISC,
        DnDItems.GOLDEN_BEETROOT,
        RecipeCategory.BUILDING_BLOCKS,
        VDBlocks.GOLDEN_BEETROOT_CRATE,
        "golden_beetroot_from_golden_beetroot_crate",
        "golden_beetroot"
    )
    // endregion
}

