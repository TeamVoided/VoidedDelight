package org.teamvoided.voided_delight.events

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.minecraft.item.ItemConvertible
import org.teamvoided.voided_delight.init.VDItems


fun registerComposting() {
    compost(VDItems.LANTERN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.MOSSKIN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.GLOOM_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.PALE_PUMPKIN_SLICE, 0.5f)
}

fun compost(item: ItemConvertible, chance: Float) = CompostingChanceRegistry.INSTANCE.add(item, chance)

