package org.teamvoided.voided_delight.events

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.minecraft.item.ItemConvertible
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems


fun registerComposting() {
    compost(VDItems.LANTERN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.MOSSKIN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.GLOOM_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.PALE_PUMPKIN_SLICE, 0.5f)

    compost(VDBlocks.STUFFED_LANTERN_PUMPKIN)
    compost(VDBlocks.STUFFED_MOSSKIN_PUMPKIN)
    compost(VDBlocks.STUFFED_GLOOM_PUMPKIN)
    compost(VDBlocks.STUFFED_PALE_PUMPKIN)

    compost(VDBlocks.LANTERN_PUMPKIN_PIE)
    compost(VDBlocks.MOSSKIN_PUMPKIN_PIE)
    compost(VDBlocks.GLOOM_PUMPKIN_PIE)
    compost(VDBlocks.PALE_PUMPKIN_PIE)

    compost(VDItems.LANTERN_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.MOSSKIN_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.GLOOM_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.PALE_PUMPKIN_SLICE, 0.85f)
}

fun compost(item: ItemConvertible, chance: Float = 1.0f) = CompostingChanceRegistry.INSTANCE.add(item, chance)

