package org.teamvoided.voided_delight.data

import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.voided_delight.VoidedDelight.id

object VDPaintingVariants {
    val SKILLETON = create("skilleton")
    fun create(id: String): RegistryKey<PaintingVariant> = RegistryKey.of(RegistryKeys.PAINTING_VARIANT, id(id))
}