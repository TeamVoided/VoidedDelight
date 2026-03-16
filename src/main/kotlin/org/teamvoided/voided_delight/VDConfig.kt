package org.teamvoided.voided_delight

import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.voided_delight.VoidedDelight.MODID
import org.teamvoided.voided_delight.VoidedDelight.id

class VDConfig : Config(id(MODID)) {
    @JvmField
    var skilletonCanSpawn = true
    var canEnchantLollipop = true
}