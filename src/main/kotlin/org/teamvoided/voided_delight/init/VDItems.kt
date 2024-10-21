package org.teamvoided.voided_delight.init

import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents


@Suppress("unused", "MemberVisibilityCanBePrivate")
object VDItems {
    val ITEMS = mutableListOf<Item>()
    val EVIL_ITEMS = mutableSetOf<Item>()

    val CANDY_BERRY = register("candy_berry", Item(Item.Settings().food(VDFoodComponents.CANDY_BERRY)))
    val MARSHMARROW = register("marshmarrow", Item(Item.Settings().food(VDFoodComponents.MARSHMAROW)))
    val LOLLIPOP = register("lollipop", Item(Item.Settings().food(VDFoodComponents.LOLLIPOP)))
    val CANDY_CARROT = register("candy_carrot", Item(Item.Settings().food(VDFoodComponents.CANDY_CARROT)))
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
}
