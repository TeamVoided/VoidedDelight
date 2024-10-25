package org.teamvoided.voided_delight.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.id

object VDItemTags {
    val PUMPKIN_SLICES = create("pumpkin_slices")

    fun create(name: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(name))
}