package org.teamvoided.voided_delight.init


import net.minecraft.block.AbstractBlock
import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.FoodComponents
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.headless.block.HeadlessSkull
import org.teamvoided.headless.block.HeadlessWallSkull
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.item.VDFoodComponents
import org.teamvoided.voided_delight.util.*
import vectorwing.farmersdelight.common.block.CookingPotBlock
import vectorwing.farmersdelight.common.block.SkilletBlock
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes
import vectorwing.farmersdelight.common.registry.ModBlocks as FDBlocks


object VDBlocks {
    val BLOCKS get() = getModEntries(Registries.BLOCK)

    val netheriteProps: AbstractBlock.Settings = AbstractBlock.Settings.create()
        .mapColor(MapColor.BLACK)
        .strength(1.0F, 1200.0F)
        .sounds(BlockSoundGroup.NETHERITE)

    val NETHERITE_COOKING_POT =
        registerNoItem("netherite_cooking_pot", CookingPotBlock(netheriteProps)).cutout().pickaxe()
    val NETHERITE_SKILLET = registerNoItem("netherite_skillet", SkilletBlock(netheriteProps)).pickaxe()

    var SKULL_PROS: AbstractBlock.Settings = AbstractBlock.Settings.create()
        .instrument(NoteBlockInstrument.CUSTOM_HEAD)
        .strength(0.75F, 6.0F)
        .sounds(BlockSoundGroup.LANTERN)
        .pistonBehavior(PistonBehavior.DESTROY)

    val SKILLETON_SKULL =
        registerNoItem("skilleton_skull", HeadlessSkull(VDSkulls.SKILLETON, SKULL_PROS))
    val SKILLETON_WALL_SKULL = registerNoItem(
        "skilleton_wall_skull",
        HeadlessWallSkull(VDSkulls.SKILLETON, copy(SKILLETON_SKULL).dropsLike(SKILLETON_SKULL))
    )

    var N_SKULL_PROS: AbstractBlock.Settings = AbstractBlock.Settings.create()
        .instrument(NoteBlockInstrument.CUSTOM_HEAD)
        .strength(0.75F, 1200.0F)
        .sounds(BlockSoundGroup.NETHERITE)
        .pistonBehavior(PistonBehavior.DESTROY)
    val NETHERITE_SKILLETON_SKULL =
        registerNoItem("netherite_skilleton_skull", HeadlessSkull(VDSkulls.NETHERITE_SKILLETON, N_SKULL_PROS))
    val NETHERITE_SKILLETON_WALL_SKULL = registerNoItem(
        "netherite_skilleton_wall_skull", HeadlessWallSkull(
            VDSkulls.NETHERITE_SKILLETON, copy(NETHERITE_SKILLETON_SKULL).dropsLike(NETHERITE_SKILLETON_SKULL)
        )
    )

    val GOLDEN_CARROT_CRATE = register("golden_carrot_crate", Block(copy(FDBlocks.CARROT_CRATE.get()))).axe()


    // region Dusks and Dungeons
    val STUFFED_LANTERN_PUMPKIN = registerMaxStack(
        "stuffed_lantern_pumpkin_block", stuffedPumpkinOf(copy(Blocks.PUMPKIN)) { VDItems.STUFFED_LANTERN_PUMPKIN }
    ).axe()
    val STUFFED_MOSSKIN_PUMPKIN = registerMaxStack(
        "stuffed_mosskin_pumpkin_block", stuffedPumpkinOf(copy(Blocks.PUMPKIN)) { VDItems.STUFFED_MOSSKIN_PUMPKIN }
    ).axe()
    val STUFFED_GLOOM_PUMPKIN = registerMaxStack(
        "stuffed_gloom_pumpkin_block", stuffedPumpkinOf(copy(Blocks.PUMPKIN)) { VDItems.STUFFED_GLOOM_PUMPKIN }
    ).axe()
    val STUFFED_PALE_PUMPKIN = registerMaxStack(
        "stuffed_pale_pumpkin_block", stuffedPumpkinOf(copy(Blocks.PUMPKIN)) { VDItems.STUFFED_PALE_PUMPKIN }
    ).axe()

    val LANTERN_PUMPKIN_PIE = registerEdible(
        "lantern_pumpkin_pie",
        FoodComponents.PUMPKIN_PIE,
        pieOf(copy(Blocks.CAKE)) { VDItems.LANTERN_PUMPKIN_PIE_SLICE }
    ).knife()
    val MOSSKIN_PUMPKIN_PIE = registerEdible(
        "mosskin_pumpkin_pie",
        FoodComponents.PUMPKIN_PIE,
        pieOf(copy(Blocks.CAKE)) { VDItems.MOSSKIN_PUMPKIN_PIE_SLICE }
    ).knife()
    val GLOOM_PUMPKIN_PIE = registerEdible(
        "gloom_pumpkin_pie", FoodComponents.PUMPKIN_PIE, pieOf(copy(Blocks.CAKE)) { VDItems.GLOOM_PUMPKIN_PIE_SLICE }
    ).knife()
    val PALE_PUMPKIN_PIE = registerEdible(
        "pale_pumpkin_pie", FoodComponents.PUMPKIN_PIE, pieOf(copy(Blocks.CAKE)) { VDItems.PALE_PUMPKIN_PIE_SLICE }
    ).knife()

    val GOLDEN_BEETROOT_CRATE = register(
        "golden_beetroot_crate", Block(copy(FDBlocks.BEETROOT_CRATE.get()))
    ).axe()

    val CRYSTAL_CANDY_BLOCK = registerEdible(
        "crystal_candy_block", VDFoodComponents.CRYSTAL_CANDY_8, Block(copy(Blocks.CALCITE).mapColor(MapColor.BLUE))
    ).pickaxe()
    val CRYSTAL_CANDY_STAIRS = registerEdible(
        "crystal_candy_stairs", VDFoodComponents.CRYSTAL_CANDY_6, stairsOf(CRYSTAL_CANDY_BLOCK)
    ).pickaxe()
    val CRYSTAL_CANDY_SLAB =
        registerEdible("crystal_candy_slab", VDFoodComponents.CRYSTAL_CANDY_4, slabOf(CRYSTAL_CANDY_BLOCK)).pickaxe()
    val CRYSTAL_CANDY_WALL =
        registerEdible("crystal_candy_wall", VDFoodComponents.CRYSTAL_CANDY_6, wallOf(CRYSTAL_CANDY_BLOCK)).pickaxe()
    // endregion

    // region White Pumpkins
    val STUFFED_WHITE_PUMPKIN = registerMaxStack(
        "stuffed_white_pumpkin_block", stuffedPumpkinOf(copy(Blocks.PUMPKIN)) { VDItems.STUFFED_WHITE_PUMPKIN }
    ).axe()
    // endregion

    fun init() {
        ModBlockEntityTypes.COOKING_POT.get().addSupportedBlock(NETHERITE_COOKING_POT)
        ModBlockEntityTypes.SKILLET.get().addSupportedBlock(NETHERITE_SKILLET)
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

    fun registerNoItem(id: String, block: Block): Block = Registry.register(Registries.BLOCK, id(id), block)
}
