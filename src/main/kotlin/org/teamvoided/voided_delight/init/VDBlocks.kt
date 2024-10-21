package org.teamvoided.voided_delight.init


import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.util.*
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object VDBlocks {
    val BLOCKS = mutableSetOf<Block>()
    val CUTOUT_BLOCKS = mutableSetOf<Block>()
    val TRANSLUCENT_BLOCKS = mutableSetOf<Block>()
    val GRASS_TINT_BLOCKS = mutableSetOf<Block>()
    val FOLIAGE_TINT_BLOCKS = mutableSetOf<Block>()
    val FLAMMABLE_PLANKS = mutableSetOf<Block>()
    val FLAMMABLE_LOGS = mutableSetOf<Block>()
    val FLAMMABLE_LEAVES = mutableSetOf<Block>()
    val SWORDABLE = mutableSetOf<Block>()
    val PICKAXABLE = mutableSetOf<Block>()
    val AXABLE = mutableSetOf<Block>()
    val SHOVELABLE = mutableSetOf<Block>()
    val HOEABLE = mutableSetOf<Block>()
    val EVIL_BLOCKS = mutableSetOf<Block>()

    val CRYSTAL_CANDY_BLOCK = registerEdible(
        "crystal_candy_block",
        VDFoodComponents.CRYSTAL_CANDY_8,
        Block(AbstractBlock.Settings.copy(Blocks.CALCITE).mapColor(MapColor.BLUE)).pickaxe()
    )
    val CRYSTAL_CANDY_STAIRS = registerEdible(
        "crystal_candy_stairs",
        VDFoodComponents.CRYSTAL_CANDY_6,
        stairsOf(CRYSTAL_CANDY_BLOCK).pickaxe()
    )
    val CRYSTAL_CANDY_SLAB = registerEdible(
        "crystal_candy_slab",
        VDFoodComponents.CRYSTAL_CANDY_4,
        slabOf(CRYSTAL_CANDY_BLOCK).pickaxe()
    )
    val CRYSTAL_CANDY_WALL = registerEdible(
        "crystal_candy_wall",
        VDFoodComponents.CRYSTAL_CANDY_6,
        wallOf(CRYSTAL_CANDY_BLOCK).pickaxe()
    )

    fun init() {
//        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_PLANKS, 5, 20)
//        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LOGS, 5, 5)
//        FlammableBlockRegistry.getInstance(FIRE).add(DnDBlockTags.FLAMMABLE_LEAVES, 30, 60)
    }

    fun register(id: String, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        VDItems.register(id, BlockItem(regBlock, Item.Settings()))
        return regBlock
    }

    fun registerEdible(id: String, foodComponent: FoodComponent, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        VDItems.register(id, BlockItem(regBlock, Item.Settings().food(foodComponent)))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}
