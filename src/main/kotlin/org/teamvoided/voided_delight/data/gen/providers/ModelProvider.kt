package org.teamvoided.voided_delight.data.gen.providers


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import org.teamvoided.voided_delight.init.VDItems

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {

    }

    private val single = listOf(
        VDItems.CANDY_BERRY,
        VDItems.MARSHMARROW,
        VDItems.CANDY_CARROT,
        VDItems.CLOUDY_CANDY,
        VDItems.SYRUP_APPLE,
        VDItems.CRYSTAL_CANDY_SHARD
    )

    override fun generateItemModels(gen: ItemModelGenerator) {
        single.forEach { gen.register(it, Models.SINGLE_LAYER_ITEM) }
        gen.register(VDItems.LOLLIPOP, Models.HANDHELD_MACE)
    }
}
