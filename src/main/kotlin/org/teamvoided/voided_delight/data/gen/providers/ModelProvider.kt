package org.teamvoided.voided_delight.data.gen.providers


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates
import net.minecraft.data.client.model.TextureKey.*
import org.teamvoided.dusk_autumn.util.INNER
import org.teamvoided.dusk_autumn.util.block
import org.teamvoided.dusk_autumn.util.model
import org.teamvoided.voided_delight.VoidedDelight.fd
import org.teamvoided.voided_delight.block.VDFamilies
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.common.block.FeastBlock
import vectorwing.farmersdelight.common.block.PieBlock

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
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
    }

    private val single = listOf(
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

val pieModelList = listOf(
    block(fd("block/pie"), PARTICLE, BOTTOM, SIDE, TOP),
    block(fd("block/pie_slice1"), "_slice1", PARTICLE, BOTTOM, SIDE, TOP, INNER),
    block(fd("block/pie_slice2"), "_slice2", PARTICLE, BOTTOM, SIDE, TOP, INNER),
    block(fd("block/pie_slice3"), "_slice3", PARTICLE, BOTTOM, SIDE, TOP, INNER),
)

fun BlockStateModelGenerator.registerPie(block: Block) {
    val texture = Texture.texture(block)
        .put(PARTICLE, block.model("_top"))
        .put(BOTTOM, fd("block/pie_bottom"))
        .put(SIDE, fd("block/pie_side"))
        .put(TOP, block.model("_top"))
        .put(INNER, block.model("_inner"))

    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(BlockStateVariantMap.create(PieBlock.BITES).register {
                BlockStateVariant.create()
                    .put(VariantSettings.MODEL, pieModelList[it].upload(block, texture, this.modelCollector))
            })
    )
}
