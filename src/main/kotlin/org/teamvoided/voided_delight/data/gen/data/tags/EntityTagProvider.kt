package org.teamvoided.voided_delight.data.gen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.EntityTypeTags
import org.teamvoided.voided_delight.init.VDEntityTypes
import java.util.concurrent.CompletableFuture

class EntityTagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, completableFuture) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {
        getOrCreateTagBuilder(EntityTypeTags.SKELETONS).add(VDEntityTypes.SKILLETON)
        getOrCreateTagBuilder(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE).add(VDEntityTypes.SKILLETON)
    }
}