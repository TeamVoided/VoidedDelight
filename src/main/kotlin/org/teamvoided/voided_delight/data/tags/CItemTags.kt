package org.teamvoided.voided_delight.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.id

object CItemTags {
    val STORAGE_GOLDEN_BEETROOT = create("storage_blocks/golden_beetroot")

    fun create(name: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id("c",name))
}