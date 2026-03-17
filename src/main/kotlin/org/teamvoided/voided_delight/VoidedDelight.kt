package org.teamvoided.voided_delight

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.voided_delight.init.*
import vectorwing.farmersdelight.FarmersDelight

object VoidedDelight {
    const val MODID = "voided_delight"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(VoidedDelight::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::VDConfig)


    fun init() {
        log.info("The Void wants to eat too... ;)")
        VDSkulls.init()
        VDItems.init()
        VDBlocks.init()
        VDEntityTypes.init()
        VDTabs.init()
        FabEvents.init()
    }

    fun id(namespace: String, path: String): Identifier = Identifier.of(namespace, path)
    fun fd(path: String): Identifier = Identifier.of(FarmersDelight.MODID, path)
    fun mc(path: String): Identifier = Identifier.ofDefault(path)
    fun id(path: String) = id(MODID, path)
}
