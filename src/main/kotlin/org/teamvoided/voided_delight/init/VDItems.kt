package org.teamvoided.voided_delight.init

import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents
import vectorwing.farmersdelight.common.FoodValues
import vectorwing.farmersdelight.common.item.ConsumableItem
import vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem


@Suppress("unused", "MemberVisibilityCanBePrivate")
object VDItems {
    val ITEMS = mutableListOf<Item>()
    val SECRET_ITEMS = mutableSetOf<Item>()

    val LANTERN_PUMPKIN_SLICE = register("lantern_pumpkin_slice", Item(Item.Settings().food(FoodValues.PUMPKIN_SLICE)))
    val MOSSKIN_PUMPKIN_SLICE = register("mosskin_pumpkin_slice", Item(Item.Settings().food(FoodValues.PUMPKIN_SLICE)))
    val GLOOM_PUMPKIN_SLICE = register("gloom_pumpkin_slice", Item(Item.Settings().food(FoodValues.PUMPKIN_SLICE)))
    val PALE_PUMPKIN_SLICE = register("pale_pumpkin_slice", Item(Item.Settings().food(FoodValues.PUMPKIN_SLICE)))

    val STUFFED_LANTERN_PUMPKIN = register("stuffed_lantern_pumpkin", bowlItem(FoodValues.STUFFED_PUMPKIN))
    val STUFFED_MOSSKIN_PUMPKIN = register("stuffed_mosskin_pumpkin", bowlItem(FoodValues.STUFFED_PUMPKIN))
    val STUFFED_GLOOM_PUMPKIN = register("stuffed_gloom_pumpkin", bowlItem(FoodValues.STUFFED_PUMPKIN))
    val STUFFED_PALE_PUMPKIN = register("stuffed_pale_pumpkin", bowlItem(FoodValues.STUFFED_PUMPKIN))

    val LANTERN_PUMPKIN_SOUP = register("lantern_pumpkin_soup", bowlItem(FoodValues.PUMPKIN_SOUP))
    val MOSSKIN_PUMPKIN_SOUP = register("mosskin_pumpkin_soup", bowlItem(FoodValues.PUMPKIN_SOUP))
    val GLOOM_PUMPKIN_SOUP = register("gloom_pumpkin_soup", bowlItem(FoodValues.PUMPKIN_SOUP))
    val PALE_PUMPKIN_SOUP = register("pale_pumpkin_soup", bowlItem(FoodValues.PUMPKIN_SOUP))

    val LANTERN_PUMPKIN_PIE_SLICE = register("lantern_pumpkin_pie_slice", foodItem(FoodValues.PIE_SLICE))
    val MOSSKIN_PUMPKIN_PIE_SLICE = register("mosskin_pumpkin_pie_slice", foodItem(FoodValues.PIE_SLICE))
    val GLOOM_PUMPKIN_PIE_SLICE = register("gloom_pumpkin_pie_slice", foodItem(FoodValues.PIE_SLICE))
    val PALE_PUMPKIN_PIE_SLICE = register("pale_pumpkin_pie_slice", foodItem(FoodValues.PIE_SLICE))

    val CANDY_BERRY = register("candy_berry", Item(Item.Settings().food(VDFoodComponents.CANDY_BERRY)))
    val MARSHMARROW = register("marshmarrow", Item(Item.Settings().food(VDFoodComponents.MARSHMAROW)))
    val LOLLIPOP = register("lollipop", Item(Item.Settings().food(VDFoodComponents.LOLLIPOP)))
    val CANDY_CORN = register("candy_corn", Item(Item.Settings().food(VDFoodComponents.CANDY_CORN)))
    val CLOUDY_CANDY = register("cloudy_candy", Item(Item.Settings().food(VDFoodComponents.CLOUDY_CANDY)))
    val SYRUP_APPLE = register("syrup_apple", Item(Item.Settings().food(VDFoodComponents.SYRUP_APPLE)))
    val CRYSTAL_CANDY_SHARD =
        register("crystal_candy_shard", Item(Item.Settings().food(VDFoodComponents.CRYSTAL_CANDY_2)))

    fun init() {}

    fun register(id: String, item: Item): Item {
        val regItem = Registry.register(Registries.ITEM, id(id), item)
        ITEMS.add(regItem)
        return regItem
    }

    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))
    }

    @Suppress("FunctionName")
    fun AttributeSettings(comp: AttributeModifiersComponent): Item.Settings =
        Item.Settings().attributeModifiersComponent(comp)

    @Suppress("FunctionName")
    fun CountSettings(count: Int): Item.Settings = Item.Settings().maxCount(count)

    fun bowlItem(food: FoodComponent) = ConsumableItem(bowlFoodItem(food), true)
    fun foodItem(food: FoodComponent) = Item(Item.Settings().food(food))
}
