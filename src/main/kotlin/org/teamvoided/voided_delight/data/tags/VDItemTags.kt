package org.teamvoided.voided_delight.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.apache.logging.log4j.util.InternalApi
import org.teamvoided.voided_delight.VoidedDelight.id

object VDItemTags {
    @InternalApi
    val ITEM_TAGS = mutableListOf<TagKey<Item>>()

    val PUMPKIN_SLICES = create("pumpkin_slices")
    val PIE_SLICES = create("pie_slices")

    val MEALS = create("meals")

    init {
        CItemTags.init()
        FDItemTags.init()
    }

    private fun create(name: String): TagKey<Item> {
        val tag = TagKey.of(RegistryKeys.ITEM, id(name))
        ITEM_TAGS.add(tag)
        return tag
    }
}