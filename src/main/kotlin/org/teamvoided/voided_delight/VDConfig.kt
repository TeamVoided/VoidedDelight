package org.teamvoided.voided_delight

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.voided_delight.VoidedDelight.MODID
import org.teamvoided.voided_delight.VoidedDelight.id

class VDConfig : Config(id(MODID)) {
    @JvmField
    var skilletonCanSpawn = true
    @RequiresAction(Action.RESTART)
    var skilletonSpawnWeight = 10
    var canEnchantLollipop = true
}