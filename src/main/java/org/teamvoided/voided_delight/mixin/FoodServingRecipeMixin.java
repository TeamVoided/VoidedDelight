package org.teamvoided.voided_delight.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.voided_delight.data.tags.FDItemTags;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;


@Mixin(FoodServingRecipe.class)
public abstract class FoodServingRecipeMixin {

    @WrapOperation(method = "assemble", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean assemblePlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.COOKING_POTS) || original.call(instance, item);
    }

    @WrapOperation(method = "getRemainingItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean getRemainingItemsPlusTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.isIn(FDItemTags.COOKING_POTS) || original.call(instance, item);
    }
}
