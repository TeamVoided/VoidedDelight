package org.teamvoided.voided_delight.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.teamvoided.voided_delight.util.MixinHelpersKt.injectVDCriteria;

@Mixin(ServerAdvancementLoader.class)
public class ServerAdvancementLoaderMixin {

    @Inject(method = "validate", at = @At("HEAD"))
    void injectCustomCriteria(Identifier id, Advancement advancement, CallbackInfo ci) {
        injectVDCriteria(id, advancement);
    }
}
