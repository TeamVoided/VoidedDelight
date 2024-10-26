package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.block.Block
import net.minecraft.block.DoorBlock
import net.minecraft.block.SlabBlock
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.init.VDBlocks
import vectorwing.farmersdelight.common.block.FeastBlock
import vectorwing.farmersdelight.common.block.PieBlock
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    val manualList: List<Block> = listOf()

    override fun generate() {
        VDBlocks.BLOCKS.filterNot(manualList::contains).forEach {
            when (it) {
                is SlabBlock -> add(it, ::slabDrops)
                is DoorBlock -> add(it, ::doorDrops)
                is FeastBlock -> add(it, ::feastDrops)
                is PieBlock -> add(it) { dropsNothing() }
                else -> addDrop(it)
            }
        }

    }

    private fun feastDrops(it: Block): LootTable.Builder {
        return LootTable.builder().pool(
            applySurvivesExplosionCondition(
                it, LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .conditionally(
                        BlockStatePropertyLootCondition.builder(it)
                            .properties(StatePredicate.Builder.create().exactMatch(FeastBlock.SERVINGS, 4))
                    )
                    .with(ItemEntry.builder(it))
            )
        )

    }
}