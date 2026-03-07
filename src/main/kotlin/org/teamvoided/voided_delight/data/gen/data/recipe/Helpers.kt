package org.teamvoided.voided_delight.data.gen.data.recipe

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.fabricmc.fabric.impl.recipe.ingredient.builtin.DifferenceIngredient
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeJsonFactory
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.id
import org.teamvoided.dusks_and_dungeons.util.datagen.suffix
import org.teamvoided.voided_delight.compat.CookingPotRecipeBuilder
import vectorwing.farmersdelight.common.tag.CommonTags

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
        .addIngredient(CommonTags.FOODS_RAW_PORK)
        .addIngredient(CommonTags.FOODS_MILK)
        .unlockedByAnyIngredient(input)
        .offerTo(this)
}

/*fun RecipeExporter.makePotBowl(output: ItemConvertible, vararg input: ItemConvertible) =
    potRecipe(output, 1, 200, 1.0f, Items.BOWL, *input)*/

/*fun RecipeExporter.potRecipe(
    output: ItemConvertible, count: Int, cookingTime: Int, experience: Float, container: ItemConvertible,
    vararg input: ItemConvertible,
) {
    val recipe = CookingPotRecipeBuilder.cookingPotRecipe(output, count, cookingTime, experience, container)
    input.forEach { recipe.addIngredient(it) }
    recipe.unlockedByAnyIngredient(*input)
        .offerTo(this)
}*/

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

fun RecipeJsonFactory.criterion(item: ItemConvertible): RecipeJsonFactory =
    criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))

fun RecipeJsonFactory.criterion(tag: TagKey<Item>): RecipeJsonFactory =
    criterion("has_${tag.id.path}", FabricRecipeProvider.conditionsFromTag(tag))