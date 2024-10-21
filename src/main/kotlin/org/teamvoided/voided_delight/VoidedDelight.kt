package org.teamvoided.voided_delight

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.init.VDTabs

@Suppress("unused")
object VoidedDelight {
    const val MODID = "voided_delight"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(VoidedDelight::class.simpleName)

    fun init() {
        log.info("Hello from Common")
        VDItems.init()
        VDBlocks.init()
        VDItems.SECRET_ITEMS.addAll(VDBlocks.SECRET_BLOCKS.map { it.asItem() })
        VDTabs.init()
    }

    fun id(path: String) = Identifier.of(MODID, path)
}
