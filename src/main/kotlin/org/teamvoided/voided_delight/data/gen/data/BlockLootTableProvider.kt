package org.teamvoided.voided_delight.data.gen.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions.anyModsLoaded
import net.minecraft.block.Block
import net.minecraft.block.DoorBlock
import net.minecraft.block.SlabBlock
import net.minecraft.component.DataComponentTypes
import net.minecraft.data.server.loot_table.BlockLootTableGenerator
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.BlockStatePropertyLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.CopyComponentsLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.StatePredicate
import net.minecraft.registry.HolderLookup
import org.teamvoided.voided_delight.VDCompat
import org.teamvoided.voided_delight.init.VDBlocks
import vectorwing.farmersdelight.common.block.FeastBlock
import vectorwing.farmersdelight.common.block.PieBlock
import vectorwing.farmersdelight.common.loot.function.CopySkilletFunction
import vectorwing.farmersdelight.common.registry.ModDataComponents
import java.util.concurrent.CompletableFuture

class BlockLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricBlockLootTableProvider(o, r) {
    val manualList: List<Block> = listOf(
        VDBlocks.NETHERITE_COOKING_POT,
        VDBlocks.NETHERITE_SKILLET
    )

    @Suppress("unused")
    val dndGen = conditional(VDCompat.DND)

    @Suppress("unused")
    val whitePumpkinsGen = conditional(VDCompat.DND)

    override fun generate() {
        for (block in VDBlocks.BLOCKS.filterNot(manualList::contains)) {
            processBlock(block)
        }

        add(VDBlocks.NETHERITE_COOKING_POT) {
            LootTable.builder().pool(
                applySurvivesExplosionCondition(
                    it, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                        ItemEntry.builder(it).apply(
                            CopyComponentsLootFunction
                                .method_57637(CopyComponentsLootFunction.C_zcqyfuyv.BLOCK_ENTITY)
                                .method_58730(DataComponentTypes.CUSTOM_NAME)
                                .method_58730(ModDataComponents.MEAL.get())
                                .method_58730(ModDataComponents.CONTAINER.get())
                        )
                    )
                )
            )
        }
        add(VDBlocks.NETHERITE_SKILLET) {
            LootTable.builder().pool(
                applySurvivesExplosionCondition(
                    it, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
                        ItemEntry.builder(it).apply(CopySkilletFunction.builder())
                    )
                )
            )
        }
    }

    fun conditional(vararg id: String): BlockLootTableGenerator = withConditions(anyModsLoaded(*id))
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
