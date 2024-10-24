package org.teamvoided.voided_delight.init


import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents
import org.teamvoided.voided_delight.util.*


@Suppress("LargeClass", "TooManyFunctions", "MemberVisibilityCanBePrivate", "unused")
object VDBlocks {
    val BLOCKS = mutableSetOf<Block>()

    val STUFFED_LANTERN_PUMPKIN = registerMaxStack(
        "stuffed_lantern_pumpkin_block",
        stuffedPumpkinOf(copy(DnDFloraBlocks.LANTERN_PUMPKIN), VDItems.STUFFED_LANTERN_PUMPKIN)
    )
    val STUFFED_MOSSKIN_PUMPKIN = registerMaxStack(
        "stuffed_mosskin_pumpkin_block",
        stuffedPumpkinOf(copy(DnDFloraBlocks.MOSSKIN_PUMPKIN), VDItems.STUFFED_MOSSKIN_PUMPKIN)
    )
    val STUFFED_GLOOM_PUMPKIN = registerMaxStack(
        "stuffed_gloom_pumpkin_block",
        stuffedPumpkinOf(copy(DnDFloraBlocks.GLOOM_PUMPKIN), VDItems.STUFFED_GLOOM_PUMPKIN)
    )
    val STUFFED_PALE_PUMPKIN = registerMaxStack(
        "stuffed_pale_pumpkin_block",
        stuffedPumpkinOf(copy(DnDFloraBlocks.PALE_PUMPKIN), VDItems.STUFFED_PALE_PUMPKIN)
    )

    val CRYSTAL_CANDY_BLOCK = registerEdible(
        "crystal_candy_block",
        VDFoodComponents.CRYSTAL_CANDY_8,
        Block(copy(Blocks.CALCITE).mapColor(MapColor.BLUE)).pickaxe()
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


    fun registerMaxStack(id: String, block: Block, maxStack: Int = 1): Block =
        registerCustom(id, Item.Settings().maxCount(maxStack), block)

    fun registerEdible(id: String, foodComponent: FoodComponent, block: Block): Block =
        registerCustom(id, Item.Settings().food(foodComponent), block)

    fun registerCustom(id: String, settings: Item.Settings, block: Block): Block {
        val regBlock = registerNoItem(id, block)
        VDItems.register(id, BlockItem(regBlock, settings))
        return regBlock
    }

    fun registerNoItem(id: String, block: Block): Block {
        val regBlock = Registry.register(Registries.BLOCK, id(id), block)
        BLOCKS.add(regBlock)
        return regBlock
    }
}
