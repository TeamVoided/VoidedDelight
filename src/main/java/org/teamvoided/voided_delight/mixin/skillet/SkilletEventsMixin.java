package org.teamvoided.voided_delight.mixin.skillet;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voided_delight.data.tags.FDItemTags;
import vectorwing.farmersdelight.common.item.SkilletItem;

@Mixin(SkilletItem.SkilletEvents.class)
public class SkilletEventsMixin {
    @WrapOperation(method = "playSkilletAttackSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getRemainingItemsPlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.SKILLETS) || original.call(instance, item);
    }
}
