package org.teamvoided.voided_delight

import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.voided_delight.VoidedDelight.MODID
import org.teamvoided.voided_delight.VoidedDelight.id

class VDClientConfig : Config(id(MODID)) {
    @JvmField
    var skilletBlockRendersAsEnchanted = true
}