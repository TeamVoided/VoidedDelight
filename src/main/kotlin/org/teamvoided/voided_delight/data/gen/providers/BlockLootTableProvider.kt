package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.*
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.init.VDBlocks
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {


    val manualList: List<Block> = listOf()
    override fun generate() {
        VDBlocks.BLOCKS.filterNot(manualList::contains).forEach {
            when (it) {
                is SlabBlock -> add(it, ::slabDrops)
                is DoorBlock -> add(it, ::doorDrops)
                else -> addDrop(it)
            }
        }
    }
}