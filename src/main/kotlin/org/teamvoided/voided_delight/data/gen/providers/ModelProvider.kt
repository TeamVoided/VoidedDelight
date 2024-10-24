package org.teamvoided.voided_delight.data.gen.providers


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates
import net.minecraft.data.client.model.TextureKey.*
import org.teamvoided.dusk_autumn.util.block
import org.teamvoided.dusk_autumn.util.model
import org.teamvoided.voided_delight.VoidedDelight.fd
import org.teamvoided.voided_delight.block.VDFamilies
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.common.block.FeastBlock

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        VDFamilies.modelsBlockFamilies.forEach {
            gen.registerCubeAllModelTexturePool(it.baseBlock).family(it)
        }
        listOf(
            VDBlocks.STUFFED_LANTERN_PUMPKIN,
            VDBlocks.STUFFED_MOSSKIN_PUMPKIN,
            VDBlocks.STUFFED_GLOOM_PUMPKIN,
            VDBlocks.STUFFED_PALE_PUMPKIN,
        ).forEach(gen::registerStuffedPumpkin)
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

        VDBlocks.STUFFED_LANTERN_PUMPKIN.asItem(),
        VDBlocks.STUFFED_MOSSKIN_PUMPKIN.asItem(),
        VDBlocks.STUFFED_PALE_PUMPKIN.asItem(),
        VDBlocks.STUFFED_GLOOM_PUMPKIN.asItem(),

        VDItems.LANTERN_PUMPKIN_SOUP,
        VDItems.MOSSKIN_PUMPKIN_SOUP,
        VDItems.PALE_PUMPKIN_SOUP,
        VDItems.GLOOM_PUMPKIN_SOUP,

        VDItems.LANTERN_PUMPKIN_PIE,
        VDItems.MOSSKIN_PUMPKIN_PIE,
        VDItems.PALE_PUMPKIN_PIE,
        VDItems.GLOOM_PUMPKIN_PIE,

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

val DETAILS: TextureKey = of("details", TOP)
val detailsTex = fd("block/stuffed_pumpkin_details")
val pumpkinModelList = listOf(
    block(fd("block/stuffed_pumpkin_block_stage3"), "_3", BOTTOM, DETAILS, SIDE, TOP, PARTICLE),
    block(fd("block/stuffed_pumpkin_block_stage2"), "_2", BOTTOM, DETAILS, SIDE, TOP, PARTICLE),
    block(fd("block/stuffed_pumpkin_block_stage1"), "_1", BOTTOM, DETAILS, SIDE, TOP, PARTICLE),
    block(fd("block/stuffed_pumpkin_block_stage0"), "_0", BOTTOM, DETAILS, SIDE, TOP, PARTICLE),
)

fun BlockStateModelGenerator.registerStuffedPumpkin(block: Block) {
    val texture = Texture.texture(block)
        .put(BOTTOM, block.model("_bottom"))
        .put(DETAILS, detailsTex)
        .put(SIDE, block.model("_side"))
        .put(TOP, block.model("_top"))
        .put(PARTICLE, block.model("_bottom"))
    var models = pumpkinModelList.map { it.upload(block, texture, this.modelCollector) }
    models = listOf(models.first()) + models
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(BlockStateVariantMap.create(FeastBlock.SERVINGS).register {
                BlockStateVariant.create().put(VariantSettings.MODEL, models[it])
            })
    )
}
