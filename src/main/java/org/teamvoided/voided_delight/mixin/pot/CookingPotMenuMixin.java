package org.teamvoided.voided_delight.mixin.pot;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.refabricated.inventory.RecipeWrapper;

@Mixin(CookingPotMenu.class)
public abstract class CookingPotMenuMixin extends AbstractRecipeScreenHandler<RecipeWrapper, CookingPotRecipe> {
    public CookingPotMenuMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @Shadow
    @Final
    private ScreenHandlerContext canInteractWithCallable;

    @Shadow(remap = false)
    @Final
    public CookingPotBlockEntity blockEntity;


    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    public boolean modifyCanUse(boolean original, PlayerEntity player) {
        return canUse(canInteractWithCallable, player, blockEntity.getCachedState().getBlock()) || original;
    }
}
