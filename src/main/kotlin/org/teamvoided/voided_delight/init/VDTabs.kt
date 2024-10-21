package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.util.addLists
import org.teamvoided.voided_delight.VoidedDelight.id
import kotlin.jvm.optionals.getOrNull


object VDTabs {
    val VOIDED_DELIGHT_ITEMS: ItemGroup = register("vd_everything",
        FabricItemGroup.builder()
            .icon { ItemStack(VDItems.LOLLIPOP) }
            .name(Text.translatable("itemGroup.voided_delight.everything"))
            .entries { _, entries ->
                entries.addLists(VDItems.ITEMS.filterNot(VDItems.SECRET_ITEMS::contains))
            }
            .build()
    )

    fun init() {
//        if (isDev()) register("dnd_experimental",
//            FabricItemGroup.builder()
//                .icon { ItemStack(VDItems.GALLERY_MAPLE_DOOR) }
//                .name(Text.literal("DnD Experimental"))
//                .entries { _, entries -> entries.addLists(EVIL_ITEMS) }
//                .build()
//        )
    }

    @Suppress("SameParameterValue")
    fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}


