package org.teamvoided.voided_delight.data.gen.assets.models


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import org.teamvoided.voided_delight.block.VDFamilies
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        gen.registerCookingPot(VDBlocks.NETHERITE_COOKING_POT, FD_TRAY)
        gen.registerSkillet(VDBlocks.NETHERITE_SKILLET, FD_TRAY)

        VDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
        gen.registerStuffedPumpkin(VDBlocks.STUFFED_LANTERN_PUMPKIN)
        gen.registerStuffedPumpkin(VDBlocks.STUFFED_MOSSKIN_PUMPKIN)
        gen.registerStuffedPumpkin(VDBlocks.STUFFED_GLOOM_PUMPKIN)
        gen.registerStuffedPumpkin(VDBlocks.STUFFED_PALE_PUMPKIN)

        gen.registerPie(VDBlocks.LANTERN_PUMPKIN_PIE)
        gen.registerPie(VDBlocks.MOSSKIN_PUMPKIN_PIE)
        gen.registerPie(VDBlocks.GLOOM_PUMPKIN_PIE)
        gen.registerPie(VDBlocks.PALE_PUMPKIN_PIE)

        gen.registerStuffedPumpkin(VDBlocks.STUFFED_WHITE_PUMPKIN)
        
        gen.registerCrate(VDBlocks.GOLDEN_BEETROOT_CRATE)
    }

    val single = listOf(
        VDItems.LANTERN_PUMPKIN_SLICE,
        VDItems.MOSSKIN_PUMPKIN_SLICE,
        VDItems.GLOOM_PUMPKIN_SLICE,
        VDItems.PALE_PUMPKIN_SLICE,

        VDItems.STUFFED_LANTERN_PUMPKIN,
        VDItems.STUFFED_MOSSKIN_PUMPKIN,
        VDItems.STUFFED_GLOOM_PUMPKIN,
        VDItems.STUFFED_PALE_PUMPKIN,

        VDBlocks.STUFFED_LANTERN_PUMPKIN.asItem(),
        VDBlocks.STUFFED_MOSSKIN_PUMPKIN.asItem(),
        VDBlocks.STUFFED_GLOOM_PUMPKIN.asItem(),
        VDBlocks.STUFFED_PALE_PUMPKIN.asItem(),

        VDItems.LANTERN_PUMPKIN_SOUP,
        VDItems.MOSSKIN_PUMPKIN_SOUP,
        VDItems.GLOOM_PUMPKIN_SOUP,
        VDItems.PALE_PUMPKIN_SOUP,

        VDItems.LANTERN_PUMPKIN_PIE_SLICE,
        VDItems.MOSSKIN_PUMPKIN_PIE_SLICE,
        VDItems.GLOOM_PUMPKIN_PIE_SLICE,
        VDItems.PALE_PUMPKIN_PIE_SLICE,

        VDBlocks.LANTERN_PUMPKIN_PIE.asItem(),
        VDBlocks.MOSSKIN_PUMPKIN_PIE.asItem(),
        VDBlocks.GLOOM_PUMPKIN_PIE.asItem(),
        VDBlocks.PALE_PUMPKIN_PIE.asItem(),

        VDItems.CANDY_BERRY,
        VDItems.CANDY_CORN,
        VDItems.CANDY_CLOUD,
        VDItems.MARSHMARROW,
        VDItems.CRYSTAL_CANDY_SHARD,

        VDItems.GOLDEN_BEETROOT_SOUP,

        VDItems.WHITE_PUMPKIN_SLICE,
        VDItems.STUFFED_WHITE_PUMPKIN,
        VDBlocks.STUFFED_WHITE_PUMPKIN.asItem(),
        VDItems.WHITE_PUMPKIN_SOUP,
        VDItems.WHITE_PUMPKIN_PIE_SLICE,
    )

    override fun generateItemModels(gen: ItemModelGenerator) {
        single.forEach { gen.register(it, Models.SINGLE_LAYER_ITEM) }
        gen.register(VDItems.LOLLIPOP, Models.HANDHELD_MACE)
        gen.register(VDItems.SYRUP_APPLE, Models.HANDHELD_MACE)
    }
}
