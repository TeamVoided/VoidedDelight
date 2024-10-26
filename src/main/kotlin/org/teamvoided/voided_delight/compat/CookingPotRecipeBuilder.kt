package org.teamvoided.voided_delight.compat

import net.minecraft.advancement.AdvancementCriterion
import net.minecraft.advancement.AdvancementRequirements
import net.minecraft.advancement.AdvancementRewards
import net.minecraft.advancement.criterion.InventoryChangedCriterionTrigger
import net.minecraft.advancement.criterion.RecipeUnlockedCriterionTrigger
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeJsonFactory
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe


class CookingPotRecipeBuilder(
    private val resultStack: ItemStack,
    private val cookingTime: Int,
    private val experience: Float,
    container: ItemConvertible = Items.AIR
) : RecipeJsonFactory {
    private var tab: CookingPotRecipeBookTab = CookingPotRecipeBookTab.MEALS
    private val ingredients: DefaultedList<Ingredient> = DefaultedList.of()
    private val result: Item = resultStack.item
    private val container: ItemStack = ItemStack(container)
    private val criteria: MutableMap<String, AdvancementCriterion<*>> = LinkedHashMap()

    constructor(
        result: ItemConvertible,
        count: Int, cookingTime: Int, experience: Float,
        container: ItemConvertible = Items.AIR
    ) : this(ItemStack(result, count), cookingTime, experience, container)

    fun addIngredient(tagIn: TagKey<Item>): CookingPotRecipeBuilder {
        return addIngredient(Ingredient.ofTag(tagIn))
    }

    @JvmOverloads
    fun addIngredient(itemIn: ItemConvertible, quantity: Int = 1): CookingPotRecipeBuilder {
        for (i in 0 until quantity) {
            addIngredient(Ingredient.ofItems(itemIn))
        }
        return this
    }

    @JvmOverloads
    fun addIngredient(ingredientIn: Ingredient, quantity: Int = 1): CookingPotRecipeBuilder {
        for (i in 0 until quantity) {
            ingredients.add(ingredientIn)
        }
        return this
    }

    override fun group(group: String?): RecipeJsonFactory {
        return this
    }

    fun setRecipeBookTab(tab: CookingPotRecipeBookTab): CookingPotRecipeBuilder {
        this.tab = tab
        return this
    }

    override fun getResult(): Item {
        return this.result
    }

    override fun criterion(name: String, criterion: AdvancementCriterion<*>): CookingPotRecipeBuilder {
        criteria[name] = criterion
        return this
    }

    fun unlockedByItems(criterionName: String, vararg items: ItemConvertible): CookingPotRecipeBuilder {
        return criterion(criterionName, InventoryChangedCriterionTrigger.Conditions.create(*items))
    }

    fun unlockedByAnyIngredient(vararg items: ItemConvertible): CookingPotRecipeBuilder {
        criteria["has_any_ingredient"] =
            InventoryChangedCriterionTrigger.Conditions.create(ItemPredicate.Builder.create().items(*items).build())
        return this
    }

    override fun offerTo(exporter: RecipeExporter) {
        val id = Registries.ITEM.getId(result)
        offerTo(exporter, id)
    }

    override fun offerTo(exporter: RecipeExporter, save: String) {
        val id = Registries.ITEM.getId(result)
        check((Identifier.parse(save)) != id) { "Cooking Recipe $save should remove its 'save' argument" }
        offerTo(exporter, Identifier.parse(save))
    }

    override fun offerTo(exporter: RecipeExporter, id: Identifier) {
        val recipeId = id.withPrefix("cooking/")
        val advancementBuilder = exporter.accept()
            .putCriteria("has_the_recipe", RecipeUnlockedCriterionTrigger.create(recipeId))
            .rewards(AdvancementRewards.Builder.recipe(recipeId))
            .merger(AdvancementRequirements.RequirementMerger.ANY)
        criteria.forEach(advancementBuilder::putCriteria)
        val recipe = CookingPotRecipe(
            "", this.tab, this.ingredients, this.resultStack, this.container, this.experience, this.cookingTime
        )
        exporter.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/cooking/")))
    }

    companion object {
        fun cookingPotRecipe(
            mainResult: ItemConvertible,
            count: Int,
            cookingTime: Int,
            experience: Float,
            container: ItemConvertible = Items.AIR
        ): CookingPotRecipeBuilder {
            return CookingPotRecipeBuilder(mainResult, count, cookingTime, experience, container)
        }
    }
}