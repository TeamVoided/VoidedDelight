package org.teamvoided.voided_delight.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, completableFuture) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {

    }
}