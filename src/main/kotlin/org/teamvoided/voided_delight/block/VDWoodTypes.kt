package org.teamvoided.voided_delight.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.block.BlockSetType
import net.minecraft.block.WoodType
import org.teamvoided.voided_delight.VoidedDelight.id


object VDWoodTypes {
    fun init() = Unit
//    val BONEWOOD_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder().register(id("bonewood"))
//    val BONEWOOD_WOOD_TYPE = registerWoodType("bonewood", WoodType.SPRUCE, BONEWOOD_BLOCK_SET_TYPE)

    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}