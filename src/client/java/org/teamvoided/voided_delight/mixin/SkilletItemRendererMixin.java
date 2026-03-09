package org.teamvoided.voided_delight.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;

@Mixin(SkilletItemRenderer.class)
public class SkilletItemRendererMixin {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderBlockAsEntity(Lnet/minecraft/block/BlockState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V"))
    VertexConsumerProvider warpWithGlint(VertexConsumerProvider original, @Local(argsOnly = true) ItemStack item) {
        if (item.hasGlint()) {
            return renderLayer -> VertexConsumers.union(original.getBuffer(renderLayer), original.getBuffer(RenderLayer.getGlintTranslucent()));
        }
        return original;
    }
}
