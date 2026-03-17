package org.teamvoided.voided_delight.util.outsource

import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPointer

// TODO move to Voidlib
object EquipArmorBehavior : FallibleItemDispenserBehavior() {
    override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
        isSuccess = ArmorItem.dispenseArmor(pointer, stack)
        return stack
    }
}