package org.teamvoided.voided_delight.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.id

object CBlockTags {
    val STORAGE_GOLDEN_BEETROOT = create("storage_blocks/golden_beetroot")

    fun create(name: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id("c",name))
}