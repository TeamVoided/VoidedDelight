package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.voided_delight.VDCompat.HAS_DND
import org.teamvoided.voided_delight.VDCompat.HAS_WHITE_PUMPKINS
import org.teamvoided.voided_delight.VDCompat.isDev
import org.teamvoided.voided_delight.VoidedDelight.id
import kotlin.jvm.optionals.getOrNull


object VDTabs {
    val VOIDED_DELIGHT_ITEMS = register(
        "voided_delight", FabricItemGroup.builder()
            .icon { ItemStack(VDItems.NETHERITE_SKILLET) }
            .name(Text.translatable("itemGroup.voided_delight.voided_delight"))
            .entries { _ ->
                add(
                    VDItems.NETHERITE_SKILLET,
                    VDItems.NETHERITE_COOKING_POT,
                )

                if (HAS_WHITE_PUMPKINS) add(VDItems.WHITE_PUMPKIN_SLICE)
                if (HAS_DND) add(
                    VDItems.LANTERN_PUMPKIN_SLICE,
                    VDItems.MOSSKIN_PUMPKIN_SLICE,
                    VDItems.GLOOM_PUMPKIN_SLICE,
                    VDItems.PALE_PUMPKIN_SLICE,
                )

                if (HAS_WHITE_PUMPKINS) add(VDItems.WHITE_PUMPKIN_SOUP)
                if (HAS_DND) add(
                    VDItems.LANTERN_PUMPKIN_SOUP,
                    VDItems.MOSSKIN_PUMPKIN_SOUP,
                    VDItems.GLOOM_PUMPKIN_SOUP,
                    VDItems.PALE_PUMPKIN_SOUP,
                )

                if (HAS_DND) add(
                    VDBlocks.LANTERN_PUMPKIN_PIE,
                    VDBlocks.MOSSKIN_PUMPKIN_PIE,
                    VDBlocks.GLOOM_PUMPKIN_PIE,
                    VDBlocks.PALE_PUMPKIN_PIE,
                )
                if (HAS_WHITE_PUMPKINS) add(VDItems.WHITE_PUMPKIN_PIE_SLICE)
                if (HAS_DND) add(
                    VDItems.LANTERN_PUMPKIN_PIE_SLICE,
                    VDItems.MOSSKIN_PUMPKIN_PIE_SLICE,
                    VDItems.GLOOM_PUMPKIN_PIE_SLICE,
                    VDItems.PALE_PUMPKIN_PIE_SLICE,
                )

                if (HAS_WHITE_PUMPKINS) add(VDBlocks.STUFFED_WHITE_PUMPKIN)
                if (HAS_DND) add(
                    VDBlocks.STUFFED_LANTERN_PUMPKIN,
                    VDBlocks.STUFFED_MOSSKIN_PUMPKIN,
                    VDBlocks.STUFFED_GLOOM_PUMPKIN,
                    VDBlocks.STUFFED_PALE_PUMPKIN,
                )

                if (HAS_WHITE_PUMPKINS) add(VDItems.STUFFED_WHITE_PUMPKIN)
                if (HAS_DND) add(
                    VDItems.STUFFED_LANTERN_PUMPKIN,
                    VDItems.STUFFED_MOSSKIN_PUMPKIN,
                    VDItems.STUFFED_GLOOM_PUMPKIN,
                    VDItems.STUFFED_PALE_PUMPKIN,
                )

                if (HAS_DND) add(
                    VDItems.GOLDEN_BEETROOT_SOUP,
                    VDItems.SYRUP_APPLE,
                    VDItems.LOLLIPOP,
                    VDItems.MARSHMARROW,
                    VDItems.CANDY_CLOUD,
                    VDItems.CANDY_BERRY,
                    VDItems.CANDY_CORN,
                    VDItems.CRYSTAL_CANDY_SHARD,
                    VDBlocks.CRYSTAL_CANDY_BLOCK,
                    VDBlocks.CRYSTAL_CANDY_STAIRS,
                    VDBlocks.CRYSTAL_CANDY_SLAB,
                    VDBlocks.CRYSTAL_CANDY_WALL,
                )

            }
            .build()
    )

    fun init() {
        if (isDev()) register(
            "voided_delight_debug", FabricItemGroup.builder()
                .icon { ItemStack(VDItems.ITEMS.random()) }
                .name(Text.literal("Voided Delight Debug"))
                .entries { _, entries -> entries.add(*VDItems.ITEMS.toTypedArray()) }
                .build()
        )
    }

    fun register(name: String, itemGroup: ItemGroup): ItemGroup =
        Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? = Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
}

//TODO Move to Voidlib
typealias TabBuilder = ItemGroup.ItemStackCollector.(ItemGroup.DisplayParameters) -> Unit

fun ItemGroup.Builder.entries(fn: TabBuilder): ItemGroup.Builder = entries { par, col -> fn.invoke(col, par) }
fun ItemGroup.ItemStackCollector.add(vararg item: ItemConvertible) = item.forEach(::addItem)
