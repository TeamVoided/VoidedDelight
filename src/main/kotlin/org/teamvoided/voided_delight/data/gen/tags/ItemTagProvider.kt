package org.teamvoided.voided_delight.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import java.util.concurrent.CompletableFuture

class ItemTagProvider(
    output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider>,
    blockTagProvider: BlockTagProvider
) : FabricTagProvider.ItemTagProvider(output, completableFuture, blockTagProvider) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {

    }
}