package org.teamvoided.voided_delight.mixin;

import com.mojang.blaze3d.vertex.VertexConsumers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.client.renderer.SkilletRenderer;
import vectorwing.farmersdelight.common.block.SkilletBlock;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

@Mixin(SkilletRenderer.class)
public class SkilletRendererMixin {
    @Inject(method = "render(Lvectorwing/farmersdelight/common/block/entity/SkilletBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 0))
    void renderGlintOnBlock(SkilletBlockEntity skilletEntity, float partialTicks, MatrixStack poseStack, VertexConsumerProvider buffer, int light, int overlay, CallbackInfo ci) {
        if (skilletEntity.getSkilletAsItem().hasGlint() /*TODO add config for this*/) {
            poseStack.push();
            poseStack.translate(-0.0005f, -0.0001f, -0.0005f);
            poseStack.scale(1.001f, 1.002f, 1.001f);
            VertexConsumerProvider layer = renderLayer -> VertexConsumers.union(
                    buffer.getBuffer(renderLayer),
                    buffer.getBuffer(RenderLayer.getGlintTranslucent())
            );
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(skilletEntity.getCachedState().with(SkilletBlock.SUPPORT, false), poseStack, layer, light, overlay);
            poseStack.pop();
        }
    }
}
