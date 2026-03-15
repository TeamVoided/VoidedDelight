package org.teamvoided.voided_delight.item

import net.minecraft.item.ToolMaterials.NETHERITE
import org.teamvoided.voidcore.mc.item.VoidToolMaterial


object VDTiers {
    // 	NETHERITE(
    // 	    BlockTags.INCORRECT_FOR_NETHERITE_TOOL, // Level
    // 	    2031, // Durability
    // 	    9.0F, // Mining Speed
    // 	    4.0F, // Attack Damage
    // 	    15, // Enchantability
    // 	    () -> Ingredient.ofItems(Items.NETHERITE_INGOT) // Repair Ingredient
    // 	);

    var NETHERITE_SKILLET = VoidToolMaterial(
        NETHERITE.durability,
        NETHERITE.miningSpeedMultiplier,
        NETHERITE.attackDamage,
        NETHERITE.incorrectForDropsBlocks,
        NETHERITE.enchantability,
        NETHERITE.repairIngredient
    )


}