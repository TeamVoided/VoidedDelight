package org.teamvoided.voided_delight.util

import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.item.Item
import vectorwing.farmersdelight.common.block.FeastBlock

val CUTOUT_BLOCKS = mutableSetOf<Block>()

val SWORD_MINEABLE = mutableSetOf<Block>()
val PICKAXE_MINEABLE = mutableSetOf<Block>()
val AXE_MINEABLE = mutableSetOf<Block>()
val SHOVEL_MINEABLE = mutableSetOf<Block>()
val HOE_MINEABLE = mutableSetOf<Block>()

val NEEDS_STONE = mutableSetOf<Block>()
val NEEDS_IRON = mutableSetOf<Block>()
val NEEDS_DIAMOND = mutableSetOf<Block>()

val SILK_TOUCH_OR_NOTHING = mutableSetOf<Block>()

fun Block.cutout(): Block {
    CUTOUT_BLOCKS.add(this)
    return this
}

fun Block.sword(): Block {
    SWORD_MINEABLE.add(this)
    return this
}

fun Block.pickaxe(): Block {
    PICKAXE_MINEABLE.add(this)
    return this
}

fun Block.axe(): Block {
    AXE_MINEABLE.add(this)
    return this
}

fun Block.shovel(): Block {
    SHOVEL_MINEABLE.add(this)
    return this
}

fun Block.hoe(): Block {
    HOE_MINEABLE.add(this)
    return this
}

fun Block.needsStone(): Block {
    NEEDS_STONE.add(this)
    return this
}

fun Block.needsIron(): Block {
    NEEDS_IRON.add(this)
    return this
}

fun Block.needsDiamond(): Block {
    NEEDS_DIAMOND.add(this)
    return this
}

fun Block.silkTouchOrNothing(): Block {
    SILK_TOUCH_OR_NOTHING.add(this)
    return this
}

fun stairsOf(block: Block): Block = StairsBlock(block.defaultState, copy(block))
fun slabOf(block: Block): Block = SlabBlock(copy(block))
fun wallOf(block: Block): Block = WallBlock(copy(block).solid())
fun stuffedPumpkinOf(settings: AbstractBlock.Settings, item: Item) =
    FeastBlock(settings, { item }, false)