package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.dispenser.DispenserBlock
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.component.DataComponentTypes.NOTE_BLOCK_SOUND
import net.minecraft.item.*
import net.minecraft.item.Item.Settings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.math.BlockPointer
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.CustomSkillet
import org.teamvoided.voided_delight.item.VDFoodComponents
import org.teamvoided.voided_delight.item.VDTiers
import org.teamvoided.voided_delight.util.getModEntries
import vectorwing.farmersdelight.common.FoodValues.*
import vectorwing.farmersdelight.common.item.ConsumableItem
import vectorwing.farmersdelight.common.item.CookingPotItem
import vectorwing.farmersdelight.common.item.SkilletItem
import vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem
import vectorwing.farmersdelight.common.registry.ModSounds


object VDItems {
    val ITEMS get() = getModEntries(Registries.ITEM)

    val NETHERITE_COOKING_POT =
        register(
            "netherite_cooking_pot",
            CookingPotItem(VDBlocks.NETHERITE_COOKING_POT, Settings().maxCount(1).fireproof())
        )
    val NETHERITE_SKILLET = register(
        "netherite_skillet", CustomSkillet(
            VDTiers.NETHERITE_SKILLET, VDBlocks.NETHERITE_SKILLET,
            Settings().maxCount(1).fireproof()
                .attributeModifiersComponent(SkilletItem.createAttributes(VDTiers.NETHERITE_SKILLET, 5.0f, -3f))
        )
    )

    val SKILLETON_SPAWN_EGG = register(
        "skilleton_spawn_egg", SpawnEggItem(VDEntityTypes.SKILLETON, 0x3a3a3a, 0xc66851, Settings())
    )
    val SKILLETON_SKULL = register(
        "skilleton_skull", SkullItem(
            VDBlocks.SKILLETON_SKULL, VDBlocks.SKILLETON_WALL_SKULL, Settings().component(
                NOTE_BLOCK_SOUND, ModSounds.ITEM_SKILLET_ATTACK_WEAK.get().id
            )
        )
    )
    val NETHERITE_SKILLETON_SKULL = register(
        "netherite_skilleton_skull", SkullItem(
            VDBlocks.NETHERITE_SKILLETON_SKULL, VDBlocks.NETHERITE_SKILLETON_WALL_SKULL, Settings().component(
                NOTE_BLOCK_SOUND, ModSounds.ITEM_SKILLET_ATTACK_STRONG.get().id
            )
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

    val GOLDEN_BEETROOT_SOUP = register("golden_beetroot_soup", bowlItem(VDFoodComponents.GOLDEN_BEETROOT_SOUP))
    // endregion

    // region White Pumpkins
    val WHITE_PUMPKIN_SLICE = register("white_pumpkin_slice", foodItem(PUMPKIN_SLICE))
    val STUFFED_WHITE_PUMPKIN = register("stuffed_white_pumpkin", bowlItem(STUFFED_PUMPKIN))
    val WHITE_PUMPKIN_SOUP = register("white_pumpkin_soup", bowlItem(PUMPKIN_SOUP))
    val WHITE_PUMPKIN_PIE_SLICE = register("white_pumpkin_pie_slice", foodItem(PIE_SLICE))
    // endregion

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register {
            it.addAfter(Items.SKELETON_HORSE_SPAWN_EGG, SKILLETON_SPAWN_EGG)
        }

        val equipArmor = object : FallibleItemDispenserBehavior() {
            override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
                isSuccess = ArmorItem.dispenseArmor(pointer, stack)
                return stack
            }
        }
        DispenserBlock.registerBehavior(SKILLETON_SKULL, equipArmor)
        DispenserBlock.registerBehavior(NETHERITE_SKILLETON_SKULL, equipArmor)
    }

    fun register(id: String, item: Item): Item = Registry.register(Registries.ITEM, id(id), item)
    fun bowlItem(food: FoodComponent) = ConsumableItem(bowlFoodItem(food), true)
    fun foodItem(food: FoodComponent) = Item(Settings().food(food))
}
