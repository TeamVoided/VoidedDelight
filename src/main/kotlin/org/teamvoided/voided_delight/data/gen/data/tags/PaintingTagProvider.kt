package org.teamvoided.voided_delight.data.gen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.PaintingTags
import org.teamvoided.voided_delight.data.VDPaintingVariants
import java.util.concurrent.CompletableFuture

class PaintingTagProvider(o: FabricDataOutput, f: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<PaintingVariant>(o, RegistryKeys.PAINTING_VARIANT, f) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {
        getOrCreateTagBuilder(PaintingTags.PLACEABLE).add(VDPaintingVariants.SKILLETON)
    }
}