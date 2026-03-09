package org.teamvoided.voided_delight.mixin.advancement;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Advancement.class)
public interface AdvancementAccessor {

    @Accessor("criteria")
    Map<String, AdvancementCriterion<?>> vd_getCriteria();

    @Mutable
    @Accessor("criteria")
    void vd_setCriteria(Map<String, AdvancementCriterion<?>> criteria);

    @Accessor("requirements")
    AdvancementRequirements vd_getRequirements();

    @Mutable
    @Accessor("requirements")
    void vd_setRequirements(AdvancementRequirements requirements);
}
