package org.teamvoided.voided_delight.mixin.pot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.teamvoided.voided_delight.data.tags.FDItemTags;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

import static org.teamvoided.voided_delight.util.MixinHelpersKt.getPotCookingTimeModifier;


@Mixin(CookingPotBlockEntity.class)
public abstract class CookingPotBlockEntityMixin extends BlockEntity {
    public CookingPotBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @ModifyExpressionValue(method = "processCooking", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/crafting/CookingPotRecipe;getCookTime()I"))
    int addCookingTimeModifier(int original) {
        return (int) (original * getPotCookingTimeModifier(getCachedState().getBlock()));
    }

    @ModifyArg(method = "getAsItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;)V"))
    public ItemConvertible modifyGetAsItem(ItemConvertible item) {
        return getCachedState().getBlock();
    }

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
