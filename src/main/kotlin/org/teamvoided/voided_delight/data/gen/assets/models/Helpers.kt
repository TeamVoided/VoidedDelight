package org.teamvoided.voided_delight.data.gen.assets.models

import net.minecraft.block.Block
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates
import net.minecraft.data.client.model.BlockStateModelGenerator.createSingletonBlockState
import net.minecraft.data.client.model.TextureKey.*
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.util.datagen.INNER
import org.teamvoided.dusks_and_dungeons.util.datagen.block
import org.teamvoided.dusks_and_dungeons.util.datagen.model
import org.teamvoided.dusks_and_dungeons.util.datagen.suffix
import org.teamvoided.voided_delight.VoidedDelight.fd
import vectorwing.farmersdelight.common.block.CookingPotBlock
import vectorwing.farmersdelight.common.block.FeastBlock
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.block.SkilletBlock


val DETAILS: TextureKey = of("details", TOP)
val PARTS: TextureKey = of("parts")
val HANDLE: TextureKey = of("handle")
val TRAY_TOP: TextureKey = of("tray_top")
val TRAY_SIDE: TextureKey = of("tray_side")

// region Stuffed Pumpkin
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
    var models = pumpkinModelList.map { it.upload(block, texture, modelCollector) }
    models = listOf(models.first()) + models
    blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(BlockStateVariantMap.create(FeastBlock.SERVINGS).register {
                BlockStateVariant.create().put(VariantSettings.MODEL, models[it])
            })
    )
}
// endregion

// region Pie
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
    val models = pieModelList.map { it.upload(block, texture, modelCollector) }

    blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(
                BlockStateVariantMap.create(PieBlock.BITES)
                    .register { BlockStateVariant.create().put(VariantSettings.MODEL, models[it]) }
            )
    )
}
// endregion

val FD_TRAY = fd("block/cooking_pot_tray")

// region Cooking Pot
val potModelList = listOf(
    block(fd("block/cooking_pot"), TOP, PARTICLE, SIDE, PARTS, BOTTOM),
    block(fd("block/cooking_pot_tray"), "_tray", TOP, PARTICLE, SIDE, PARTS, BOTTOM, TRAY_TOP, TRAY_SIDE),
    block(fd("block/cooking_pot_handle"), "_handle", TOP, PARTICLE, SIDE, PARTS, BOTTOM, HANDLE),
)

fun BlockStateModelGenerator.registerCookingPot(block: Block, trayTexture: Identifier = block.model("_tray")) {
    val texture = Texture.texture(block)
        .put(TOP, block.model("_top"))
        .put(PARTICLE, block.model("_side"))
        .put(SIDE, block.model("_side"))
        .put(PARTS, block.model("_parts"))
        .put(BOTTOM, block.model("_bottom"))
        .put(HANDLE, block.model("_handle"))
        .put(TRAY_TOP, trayTexture.suffix("_top"))
        .put(TRAY_SIDE, trayTexture.suffix("_side"))
    val models = potModelList.map { it.upload(block, texture, modelCollector) }

    blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(
                BlockStateVariantMap.create(CookingPotBlock.SUPPORT)
                    .register { BlockStateVariant.create().put(VariantSettings.MODEL, models[it.ordinal]) }
            )
    )
}
// endregion


// region Skillet
val skilletModelList = listOf(
    block(fd("block/skillet"), PARTICLE, SIDE, TOP, BOTTOM),
    block(fd("block/skillet_tray"), "_tray", PARTICLE, SIDE, TOP, BOTTOM, TRAY_TOP, TRAY_SIDE),
)

fun BlockStateModelGenerator.registerSkillet(block: Block, trayTexture: Identifier = block.model("_tray")) {
    excludeFromSimpleItemModelGeneration(block)
    val texture = Texture.texture(block)
        .put(PARTICLE, block.model("_side"))
        .put(SIDE, block.model("_side"))
        .put(TOP, block.model("_top"))
        .put(BOTTOM, block.model("_bottom"))
        .put(TRAY_TOP, trayTexture.suffix("_top"))
        .put(TRAY_SIDE, trayTexture.suffix("_side"))
    val models = skilletModelList.map { it.upload(block, texture, modelCollector) }
    blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block)
            .coordinate(createNorthDefaultHorizontalRotationStates())
            .coordinate(
                BlockStateVariantMap.create(SkilletBlock.SUPPORT).register {
                    BlockStateVariant.create().put(VariantSettings.MODEL, if (it) models[1] else models[0])
                }
            )
    )
}
// endregion

val FD_CRATE_BOTTOM = fd("block/crate_bottom")

// region Crate
fun BlockStateModelGenerator.registerCrate(block: Block, bottomTexture: Identifier = FD_CRATE_BOTTOM) {
    val texture = Texture()
        .put(BOTTOM, bottomTexture)
        .put(SIDE, block.model("_side"))
        .put(TOP, block.model("_top"))

    val model = Models.CUBE_BOTTOM_TOP.upload(block, texture, modelCollector)
    blockStateCollector.accept(createSingletonBlockState(block, model))
}
// endregion