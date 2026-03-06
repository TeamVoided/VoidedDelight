package org.teamvoided.voided_delight.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.minecraft.block.Block
import net.minecraft.block.DoorBlock
import net.minecraft.block.SlabBlock
import net.minecraft.data.server.loot_table.BlockLootTableGenerator
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.VDCompat
import org.teamvoided.voided_delight.init.VDBlocks
import vectorwing.farmersdelight.common.block.FeastBlock
import vectorwing.farmersdelight.common.block.PieBlock
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    val manualList: List<Block> = listOf()

    val dndGen = conditional(VDCompat.DND)
    val whitePumpkinsGen = conditional(VDCompat.DND)

    override fun generate() {
        for (block in VDBlocks.BLOCKS.filterNot(manualList::contains)) {
            processBlock(block)
        }
    }

    fun conditional(vararg id: String): BlockLootTableGenerator = withConditions(ResourceConditions.anyModsLoaded(*id))
}

fun BlockLootTableGenerator.feastDrops(it: Block): LootTable.Builder {
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

fun BlockLootTableGenerator.processBlock(block: Block) {
    when (block) {
        is SlabBlock -> add(block, ::slabDrops)
        is DoorBlock -> add(block, ::doorDrops)
        is FeastBlock -> add(block, ::feastDrops)
        is PieBlock -> add(block) { BlockLootTableGenerator.dropsNothing() }
        else -> addDrop(block)
    }
}
