package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.voidlib.helpers.mc.addLists
import org.teamvoided.voided_delight.VoidedDelight.id
import kotlin.jvm.optionals.getOrNull


object VDTabs {
    val VOIDED_DELIGHT_ITEMS: ItemGroup = register("voided_delight",
        FabricItemGroup.builder()
            .icon { ItemStack(VDItems.LOLLIPOP) }
            .name(Text.translatable("itemGroup.voided_delight.voided_delight"))
            .entries { _, entries -> entries.addLists(VDItems.ITEMS) }
            .build()
    )

    fun init() {}

    @Suppress("SameParameterValue")
    fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}


