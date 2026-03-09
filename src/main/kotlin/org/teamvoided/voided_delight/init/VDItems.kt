package org.teamvoided.voided_delight.init

import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents
import org.teamvoided.voided_delight.util.getModEntries
import vectorwing.farmersdelight.common.FoodValues.*
import vectorwing.farmersdelight.common.item.ConsumableItem
import vectorwing.farmersdelight.common.item.CookingPotItem
import vectorwing.farmersdelight.common.item.SkilletItem
import vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem


object VDItems {
    val ITEMS get() = getModEntries(Registries.ITEM)

    val NETHERITE_COOKING_POT =
        register("netherite_cooking_pot", CookingPotItem(VDBlocks.NETHERITE_COOKING_POT, Item.Settings().maxCount(1).fireproof()))
    val NETHERITE_SKILLET = register(
        "netherite_skillet", SkilletItem(
            VDBlocks.NETHERITE_SKILLET, Item.Settings().maxCount(1).fireproof()
                .attributeModifiersComponent(SkilletItem.createAttributes(SkilletItem.SKILLET_TIER, 5.0f, -3.1f))
        )
    )

    // region Dusks and Dungeons
    val LANTERN_PUMPKIN_SLICE = register("lantern_pumpkin_slice", foodItem(PUMPKIN_SLICE))
    val MOSSKIN_PUMPKIN_SLICE = register("mosskin_pumpkin_slice", foodItem(PUMPKIN_SLICE))
    val GLOOM_PUMPKIN_SLICE = register("gloom_pumpkin_slice", foodItem(PUMPKIN_SLICE))
    val PALE_PUMPKIN_SLICE = register("pale_pumpkin_slice", foodItem(PUMPKIN_SLICE))

    val STUFFED_LANTERN_PUMPKIN = register("stuffed_lantern_pumpkin", bowlItem(STUFFED_PUMPKIN))
    val STUFFED_MOSSKIN_PUMPKIN = register("stuffed_mosskin_pumpkin", bowlItem(STUFFED_PUMPKIN))
    val STUFFED_GLOOM_PUMPKIN = register("stuffed_gloom_pumpkin", bowlItem(STUFFED_PUMPKIN))
    val STUFFED_PALE_PUMPKIN = register("stuffed_pale_pumpkin", bowlItem(STUFFED_PUMPKIN))

    val LANTERN_PUMPKIN_SOUP = register("lantern_pumpkin_soup", bowlItem(PUMPKIN_SOUP))
    val MOSSKIN_PUMPKIN_SOUP = register("mosskin_pumpkin_soup", bowlItem(PUMPKIN_SOUP))
    val GLOOM_PUMPKIN_SOUP = register("gloom_pumpkin_soup", bowlItem(PUMPKIN_SOUP))
    val PALE_PUMPKIN_SOUP = register("pale_pumpkin_soup", bowlItem(PUMPKIN_SOUP))

    val LANTERN_PUMPKIN_PIE_SLICE = register("lantern_pumpkin_pie_slice", foodItem(PIE_SLICE))
    val MOSSKIN_PUMPKIN_PIE_SLICE = register("mosskin_pumpkin_pie_slice", foodItem(PIE_SLICE))
    val GLOOM_PUMPKIN_PIE_SLICE = register("gloom_pumpkin_pie_slice", foodItem(PIE_SLICE))
    val PALE_PUMPKIN_PIE_SLICE = register("pale_pumpkin_pie_slice", foodItem(PIE_SLICE))

    val CANDY_BERRY = register("candy_berry", foodItem(VDFoodComponents.CANDY_BERRY))
    val CANDY_CORN = register("candy_corn", foodItem(VDFoodComponents.CANDY_CORN))
    val CANDY_CLOUD = register("candy_cloud", foodItem(VDFoodComponents.CANDY_CLOUD))
    val MARSHMARROW = register("marshmarrow", foodItem(VDFoodComponents.MARSHMAROW))
    val LOLLIPOP = register("lollipop", foodItem(VDFoodComponents.LOLLIPOP))
    val SYRUP_APPLE = register("syrup_apple", foodItem(VDFoodComponents.SYRUP_APPLE))
    val CRYSTAL_CANDY_SHARD = register("crystal_candy_shard", foodItem(VDFoodComponents.CRYSTAL_CANDY_2))
    // endregion

    // region White Pumpkins
    val WHITE_PUMPKIN_SLICE = register("white_pumpkin_slice", foodItem(PUMPKIN_SLICE))
    val STUFFED_WHITE_PUMPKIN = register("stuffed_white_pumpkin", bowlItem(STUFFED_PUMPKIN))
    val WHITE_PUMPKIN_SOUP = register("white_pumpkin_soup", bowlItem(PUMPKIN_SOUP))
    val WHITE_PUMPKIN_PIE_SLICE = register("white_pumpkin_pie_slice", foodItem(PIE_SLICE))
    // endregion

    fun init() {}

    fun register(id: String, item: Item): Item = Registry.register(Registries.ITEM, id(id), item)
    fun bowlItem(food: FoodComponent) = ConsumableItem(bowlFoodItem(food), true)
    fun foodItem(food: FoodComponent) = Item(Item.Settings().food(food))
}
