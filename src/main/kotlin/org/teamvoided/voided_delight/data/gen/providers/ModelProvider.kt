package org.teamvoided.voided_delight.data.gen.providers


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import org.teamvoided.voided_delight.block.VDFamilies
import org.teamvoided.voided_delight.init.VDItems

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        VDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
    }

    private val single = listOf(
        VDItems.LANTERN_PUMPKIN_SLICE,
        VDItems.MOSSKIN_PUMPKIN_SLICE,
        VDItems.PALE_PUMPKIN_SLICE,
        VDItems.GLOOM_PUMPKIN_SLICE,

        VDItems.STUFFED_LANTERN_PUMPKIN,
        VDItems.STUFFED_MOSSKIN_PUMPKIN,
        VDItems.STUFFED_PALE_PUMPKIN,
        VDItems.STUFFED_GLOOM_PUMPKIN,

        VDItems.CANDY_BERRY,
        VDItems.MARSHMARROW,
        VDItems.CANDY_CORN,
        VDItems.CLOUDY_CANDY,
        VDItems.SYRUP_APPLE,
        VDItems.CRYSTAL_CANDY_SHARD,
    )

    override fun generateItemModels(gen: ItemModelGenerator) {
        single.forEach { gen.register(it, Models.SINGLE_LAYER_ITEM) }
        gen.register(VDItems.LOLLIPOP, Models.HANDHELD_MACE)
    }
}
