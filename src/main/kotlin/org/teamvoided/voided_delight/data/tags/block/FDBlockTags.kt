package org.teamvoided.voided_delight.data.tags.block

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.fd

object FDBlockTags {
    val COOKING_POTS = create("cooking_pots")
    val SKILLETS = create("skillets")

    val CABINETS = create("cabinets")
    val WOODEN_CABINETS = create("cabinets/wooden")

    fun create(name: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, fd(name))
}