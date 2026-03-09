package org.teamvoided.voided_delight.mixin.pot;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voided_delight.data.tags.FDItemTags;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;



@Mixin(CookingPotBlockEntity.class)
public abstract class CookingPotBlockEntityMixin {

    @WrapOperation(method = "getMealFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getMealFromItemPlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.COOKING_POTS) || original.call(instance, item);
    }

    @WrapOperation(method = "takeServingFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean takeServingFromItemPlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.COOKING_POTS) || original.call(instance, item);
    }

    @WrapOperation(method = "getContainerFromItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getContainerFromItemPlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.COOKING_POTS) || original.call(instance, item);
    }
}
