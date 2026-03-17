package org.teamvoided.voided_delight.data.tags.block

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.id

@Suppress("unused")
object VDBlockTags {
    fun create(name: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(name))
}