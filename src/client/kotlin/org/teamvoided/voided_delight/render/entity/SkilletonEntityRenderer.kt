package org.teamvoided.voided_delight.render.entity

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.SkeletonEntityRenderer
import net.minecraft.util.Identifier
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.entity.mob.SkilletonEntity

class SkilletonEntityRenderer(context: EntityRendererFactory.Context) :
    SkeletonEntityRenderer<SkilletonEntity>(context) {
    override fun getTexture(skilleton: SkilletonEntity?): Identifier =
        if (skilleton != null && skilleton.isNetherite()) NETHERITE else TEXTURE

    companion object {
        val TEXTURE = id("textures/entity/skeleton/skilleton.png")
        val NETHERITE = id("textures/entity/skeleton/netherite_skilleton.png")
    }
}

