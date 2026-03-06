package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.minecraft.item.ItemConvertible


fun registerComposting() {

    // region Dusks and Dungeons
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
    // endregion

    // region White Pumpkins
    compost(VDItems.WHITE_PUMPKIN_SLICE, 0.5f)
    compost(VDBlocks.STUFFED_WHITE_PUMPKIN)
    compost(VDItems.WHITE_PUMPKIN_SLICE, 0.85f)
    // endregion

}

fun compost(item: ItemConvertible, chance: Float = 1.0f) = CompostingChanceRegistry.INSTANCE.add(item, chance)

