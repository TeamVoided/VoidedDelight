package org.teamvoided.voided_delight.block

import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.voided_delight.init.VDBlocks


object VDFamilies {
    private val CRYSTAL_CANDY_FAMILY: BlockFamily =
        BlockFamilies.register(VDBlocks.CRYSTAL_CANDY_BLOCK)
            .stairs(VDBlocks.CRYSTAL_CANDY_STAIRS)
            .slab(VDBlocks.CRYSTAL_CANDY_SLAB)
            .wall(VDBlocks.CRYSTAL_CANDY_WALL)
            .build()

    val modelsBlockFamilies = listOf(
        CRYSTAL_CANDY_FAMILY
    )
    val recipesBlockFamilies = modelsBlockFamilies

    fun init() {}
}