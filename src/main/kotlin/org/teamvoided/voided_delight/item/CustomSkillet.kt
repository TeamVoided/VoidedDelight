package org.teamvoided.voided_delight.item

import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import vectorwing.farmersdelight.common.item.SkilletItem

class CustomSkillet(var tier: ToolMaterial, block: Block, properties: Settings) : SkilletItem(block, properties) {
    override fun getEnchantability(): Int = tier.enchantability
    override fun canRepair(toRepair: ItemStack, repair: ItemStack): Boolean = tier.repairIngredient.test(repair)
}