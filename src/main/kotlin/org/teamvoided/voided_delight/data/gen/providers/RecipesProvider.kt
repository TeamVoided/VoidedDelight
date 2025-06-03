package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.fabricmc.fabric.impl.recipe.ingredient.builtin.DifferenceIngredient
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeJsonFactory
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.id
import org.teamvoided.dusks_and_dungeons.util.datagen.offerReversibleCompactingRecipes4
import org.teamvoided.dusks_and_dungeons.util.datagen.smeltDefault
import org.teamvoided.dusks_and_dungeons.util.datagen.suffix
import org.teamvoided.voided_delight.block.VDFamilies.recipesBlockFamilies
import org.teamvoided.voided_delight.compat.CookingPotRecipeBuilder
import org.teamvoided.voided_delight.data.tags.VDItemTags
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.common.tag.CommonTags
import java.util.concurrent.CompletableFuture
import vectorwing.farmersdelight.common.registry.ModItems as FDItems

@Suppress("MemberVisibilityCanBePrivate")
class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }
        candy(e)
        pumpkins(e)
    }

    fun candy(e: RecipeExporter) {
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
    }

    fun pumpkins(e: RecipeExporter) {
        e.make1to1(DnDItems.LANTERN_PUMPKIN_SEEDS, VDItems.LANTERN_PUMPKIN_SLICE)
        e.make1to1(DnDItems.MOSSKIN_PUMPKIN_SEEDS, VDItems.MOSSKIN_PUMPKIN_SLICE)
        e.make1to1(DnDItems.GLOOM_PUMPKIN_SEEDS, VDItems.GLOOM_PUMPKIN_SLICE)
        e.make1to1(DnDItems.PALE_PUMPKIN_SEEDS, VDItems.PALE_PUMPKIN_SLICE)
        e.make2x2(DnDBlocks.LANTERN_PUMPKIN, VDItems.LANTERN_PUMPKIN_SLICE)
        e.make2x2(DnDBlocks.MOSSKIN_PUMPKIN, VDItems.MOSSKIN_PUMPKIN_SLICE)
        e.make2x2(DnDBlocks.GLOOM_PUMPKIN, VDItems.GLOOM_PUMPKIN_SLICE)
        e.make2x2(DnDBlocks.PALE_PUMPKIN, VDItems.PALE_PUMPKIN_SLICE)

        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, FDItems.FRUIT_SALAD.get())
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
    }
}

fun RecipeExporter.candied(output: ItemConvertible, input: Ingredient, count: Int = 8) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.FOOD, output, count)
        .ingredient(input)
        .ingredient(DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient(input)
        .ingredient(DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient(Items.PAPER)
        .ingredient(DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient(input)
        .ingredient(DnDItems.CORN_SYRUP_BOTTLE)
        .ingredient(input)
        .criterion(DnDItems.CORN_SYRUP_BOTTLE)
        .offerTo(this)
}

@Suppress("UnstableApiUsage")
fun RecipeExporter.stuffThePumpkin(output: ItemConvertible, slice: ItemConvertible, container: ItemConvertible) {
    CookingPotRecipeBuilder.cookingPotRecipe(output, 1, 400, 2.0f, container)
        .addIngredient(CommonTags.CROPS_RICE)
        .addIngredient(CommonTags.CROPS_ONION)
        .addIngredient(Items.BROWN_MUSHROOM)
        .addIngredient(slice)
        .addIngredient(ConventionalItemTags.BERRY_FOODS)
        .addCIngredient(
            DifferenceIngredient(
                Ingredient.ofTag(ConventionalItemTags.VEGETABLE_FOODS), Ingredient.ofItems(Items.MELON_SLICE)
            )
        )
        .unlockedByAnyIngredient(container)
        .offerTo(this)
}

fun RecipeExporter.makePie(output: ItemConvertible, input: ItemConvertible) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, output)
        .ingredient(input)
        .ingredient(Items.SUGAR)
        .ingredient(Items.EGG)
        .criterion(input)
        .criterion(Items.SUGAR)
        .criterion(Items.EGG)
        .offerTo(this)
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
    vararg input: ItemConvertible,
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

fun RecipeExporter.make2x2(output: ItemConvertible, input: ItemConvertible, count: Int = 1, suffix: String = "") {
    ShapedRecipeJsonFactory.create(RecipeCategory.MISC, output, count)
        .pattern("##")
        .pattern("##")
        .ingredient('#', Ingredient.ofItems(input))
        .criterion(input)
        .criterion(output)
        .offerTo(this, output.id.suffix(suffix))
}

private fun RecipeJsonFactory.criterion(item: ItemConvertible): RecipeJsonFactory =
    this.criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))

private fun RecipeJsonFactory.criterion(tag: TagKey<Item>): RecipeJsonFactory =
    this.criterion("has_${tag.id.path}", FabricRecipeProvider.conditionsFromTag(tag))