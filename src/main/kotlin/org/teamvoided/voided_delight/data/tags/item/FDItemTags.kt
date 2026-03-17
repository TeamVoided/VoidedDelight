package org.teamvoided.voided_delight.data.tags.item

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.fd

object FDItemTags {
    @JvmField
    val COOKING_POTS = create("cooking_pots")

    @JvmField
    val SKILLETS = create("skillets")

    fun init() = Unit
    private fun create(name: String): TagKey<Item> {
        val tag = TagKey.of(RegistryKeys.ITEM, fd(name))
        VDItemTags.ITEM_TAGS.add(tag)
        return tag
    }
}