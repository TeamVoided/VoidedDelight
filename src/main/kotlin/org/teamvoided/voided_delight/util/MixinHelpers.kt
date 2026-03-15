@file:Suppress("CAST_NEVER_SUCCEEDS")

package org.teamvoided.voided_delight.util

import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementCriterion
import net.minecraft.advancement.AdvancementRequirements
import net.minecraft.advancement.criterion.ConsumeItemCriterionTrigger
import net.minecraft.advancement.criterion.ItemUsedOnLocationCriterionTrigger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import org.teamvoided.voided_delight.VoidedDelight.fd
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.mixin.advancement.AdvancementAccessor

var Advancement.mutableCriteria: Map<String, AdvancementCriterion<*>>
    get() = (this as AdvancementAccessor).vd_getCriteria()
    set(value) = (this as AdvancementAccessor).vd_setCriteria(value)


var Advancement.mutableRequirements: AdvancementRequirements
    get() = (this as AdvancementAccessor).vd_getRequirements()
    set(value) = (this as AdvancementAccessor).vd_setRequirements(value)

val PLACE_COOKING_POT = fd("main/place_cooking_pot")
val USE_SKILLET = fd("main/use_skillet")
val PLACE_SKILLET = fd("main/place_skillet")

fun injectVDCriteria(id: Identifier, advancement: Advancement) {
    val patchMap = mutableMapOf<String, AdvancementCriterion<*>>()
    var updated = true

    when (id) {
        PLACE_COOKING_POT -> {
            patchMap["netherite_pot"] =
                ItemUsedOnLocationCriterionTrigger.Conditions.create(VDBlocks.NETHERITE_COOKING_POT)
        }

        USE_SKILLET -> {
            patchMap["netherite_skillet"] =
                ConsumeItemCriterionTrigger.Conditions.create(VDItems.NETHERITE_SKILLET)
        }

        PLACE_SKILLET -> {
            patchMap["netherite_skillet"] =
                ItemUsedOnLocationCriterionTrigger.Conditions.create(VDBlocks.NETHERITE_SKILLET)
        }

        else -> updated = false
    }

    if (updated) {
        advancement.mutableCriteria += patchMap
        advancement.mutableRequirements =
            AdvancementRequirements.anyOf(advancement.mutableRequirements.requirements.flatten() + patchMap.keys)
    }
}


var SKILLET_COOKING_TIME_MODIFIERS = mutableMapOf(
    VDItems.NETHERITE_SKILLET to 0.5f
)

var POT_COOKING_TIME_MODIFIERS = mutableMapOf(
    VDBlocks.NETHERITE_COOKING_POT to 0.5f
)
fun getSkilletCookingTimeModifier(item: Item): Float = SKILLET_COOKING_TIME_MODIFIERS[item] ?: 1f
fun getPotCookingTimeModifier(block: Block): Float = POT_COOKING_TIME_MODIFIERS[block] ?: 1f
