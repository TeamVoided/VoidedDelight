package org.teamvoided.voided_delight.mixin.skillet;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;

import static org.teamvoided.voided_delight.util.MixinHelpersKt.getSkilletCookingTimeModifier;

@Mixin(SkilletItem.class)
public class SkilletItemMixin {

    @ModifyExpressionValue(method = "getUseTicks", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/SkilletBlock;getSkilletCookingTime(II)I"))
    int addCookingTimeModifier(int original) {
        return (int) (original * getSkilletCookingTimeModifier((Item) (Object) this));
    }

    @Inject(method = "isPlayerNearHeatSource", at = @At("HEAD"), cancellable = true)
    private static void isInHotDim(PlayerEntity player, WorldView level, CallbackInfoReturnable<Boolean> cir) {
        if (level.getDimension().ultraWarm()) {
            cir.setReturnValue(true);
        }
    }
}
