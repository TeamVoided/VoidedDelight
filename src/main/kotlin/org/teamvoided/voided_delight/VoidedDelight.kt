package org.teamvoided.voided_delight

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.init.VDTabs
import org.teamvoided.voided_delight.init.registerComposting
import vectorwing.farmersdelight.FarmersDelight

@Suppress("unused")
object VoidedDelight {
    const val MODID = "voided_delight"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(VoidedDelight::class.simpleName)

    fun init() {
        log.info("The Void wants to eat too... ;)")
        VDItems.init()
        VDBlocks.init()
        VDTabs.init()

        registerComposting()
    }

    fun id(path: String) = Identifier.of(MODID, path)
    fun id(namespace: String, path: String) = Identifier.of(namespace, path)
    fun fd(path: String) = Identifier.of(FarmersDelight.MODID, path)
}
