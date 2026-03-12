package org.teamvoided.voided_delight

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.RenderLayer
import org.teamvoided.voided_delight.VoidedDelight.mc
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer
import vectorwing.farmersdelight.common.registry.ModBlocks
import vectorwing.farmersdelight.common.registry.ModDataComponents
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper.EMPTY as EMPTY_ITEM

object VoidedDelightClient {
    @Suppress("unused")
    fun init() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(),
            VDBlocks.NETHERITE_COOKING_POT, VDBlocks.NETHERITE_SKILLET, ModBlocks.SKILLET.get()
        )

        BuiltinItemRendererRegistry.INSTANCE.register(VDItems.NETHERITE_SKILLET, SkilletItemRenderer())
        ModelPredicateProviderRegistry.register(VDItems.NETHERITE_SKILLET, mc("cooking")) { stack, _, _, _ ->
            if (stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), EMPTY_ITEM).stack.isEmpty) 0f else 1f
        }
    }
}
