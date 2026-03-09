package org.teamvoided.voided_delight.mixin.skillet;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

import static org.teamvoided.voided_delight.util.MixinHelpersKt.getSkilletCookingTimeModifier;

@Mixin(SkilletBlockEntity.class)
public class SkilletBlockEntityMixin {
    @Shadow
    private ItemStack skilletStack;

    @ModifyExpressionValue(method = "addItemToCook", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/SkilletBlock;getSkilletCookingTime(II)I"))
    int addCookingTimeModifier(int original) {
        return (int) (original * getSkilletCookingTimeModifier(skilletStack.getItem()));
    }
}
