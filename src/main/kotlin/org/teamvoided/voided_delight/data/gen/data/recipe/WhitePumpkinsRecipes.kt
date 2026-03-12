package org.teamvoided.voided_delight.data.gen.data.recipe

import einstein.white_pumpkins.ModInit as WPInit
import net.minecraft.data.server.recipe.RecipeExporter
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems


fun whitePumpkins(e: RecipeExporter) {
    // region Pumpkin
    e.make1to1(WPInit.WHITE_PUMPKIN_SEEDS.get(), VDItems.WHITE_PUMPKIN_SLICE)
    e.make2x2(WPInit.WHITE_PUMPKIN.get(), VDItems.WHITE_PUMPKIN_SLICE)
    e.makePumpkinSoup(VDItems.WHITE_PUMPKIN_SOUP, VDItems.WHITE_PUMPKIN_SLICE)
    e.stuffThePumpkin(VDBlocks.STUFFED_WHITE_PUMPKIN, VDItems.WHITE_PUMPKIN_SLICE, WPInit.WHITE_PUMPKIN.get())
    e.make2x2(WPInit.WHITE_PUMPKIN_PIE.get(), VDItems.WHITE_PUMPKIN_PIE_SLICE, 1, "_from_slices")
    // endregion
}

