package org.teamvoided.voided_delight

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.model.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.VoidedDelight.mc
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDEntityTypes
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.init.VDSkulls
import org.teamvoided.voided_delight.render.entity.SkilletonEntityRenderer
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer
import vectorwing.farmersdelight.common.registry.ModDataComponents.SKILLET_INGREDIENT
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE as BlockLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry.register as registerRenderer
import org.teamvoided.headless.HeadlessRegistry.register as registerHeadModel
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper.EMPTY as EMPTY_ITEM

object VoidedDelightClient {

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::VDClientConfig, RegisterType.CLIENT)

    val SKILLETON_LAYER = EntityModelLayer(id("skilleton_head"), "main")

    @Suppress("unused")
    fun init() {
        BlockLayerMap.putBlocks(
            RenderLayer.getCutout(),
            VDBlocks.NETHERITE_COOKING_POT, VDBlocks.NETHERITE_SKILLET, FDBlocks.SKILLET.get()
        )

        registerRenderer(VDEntityTypes.SKILLETON, ::SkilletonEntityRenderer)
        registerModelLayer(SKILLETON_LAYER, SkullBlockEntityModel::getSkullTexturedModelData)
        registerHeadModel(VDSkulls.SKILLETON, SkilletonEntityRenderer.TEXTURE) {
            SkullBlockEntityModel(it.getModelPart(SKILLETON_LAYER))
        }
        registerHeadModel(VDSkulls.NETHERITE_SKILLETON, SkilletonEntityRenderer.NETHERITE) {
            SkullBlockEntityModel(it.getModelPart(SKILLETON_LAYER))
        }


        BuiltinItemRendererRegistry.INSTANCE.register(VDItems.NETHERITE_SKILLET, SkilletItemRenderer())
        ModelPredicateProviderRegistry.register(VDItems.NETHERITE_SKILLET, mc("cooking")) { stack, _, _, _ ->
            if (stack.getOrDefault(SKILLET_INGREDIENT.get(), EMPTY_ITEM).stack.isEmpty) 0f else 1f
        }
    }
}
