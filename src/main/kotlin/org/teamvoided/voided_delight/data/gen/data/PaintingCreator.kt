package org.teamvoided.voided_delight.data.gen.data

import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import org.teamvoided.voided_delight.data.VDPaintingVariants.SKILLETON

object PaintingCreator {
    fun bootstrap(c: BootstrapContext<PaintingVariant>) {
        c.registerPaintingVariant(SKILLETON, 4, 3)
    }

    private fun BootstrapContext<PaintingVariant>.registerPaintingVariant(
        registryKey: RegistryKey<PaintingVariant>, width: Int, height: Int,
    ) = register(registryKey, PaintingVariant(width, height, registryKey.value))
}