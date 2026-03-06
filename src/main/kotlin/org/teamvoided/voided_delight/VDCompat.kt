package org.teamvoided.voided_delight

import net.fabricmc.loader.api.FabricLoader

object VDCompat {
    const val DND = "dusks_and_dungeons"
    const val WHITE_PUMPKINS = "white_pumpkins"

    val HAS_DND = mod(DND)
    val HAS_WHITE_PUMPKINS = mod(WHITE_PUMPKINS)

    fun mod(id: String) = FabricLoader.getInstance().isModLoaded(id)
}