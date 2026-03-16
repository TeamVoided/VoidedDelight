package org.teamvoided.voided_delight.data.gen.data.loot.tables

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.entity.EntityType
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.KilledByPlayerLootCondition
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKey
import org.teamvoided.voided_delight.init.VDEntityTypes
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition.method_953 as commonWithLooting
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction.method_547 as moreFromLooting

class EntityLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, LootContextTypes.ENTITY) {
    val lookup: HolderLookup.Provider = r.join()

    override fun generate(gen: BiConsumer<RegistryKey<LootTable>, LootTable.Builder>) {
        val nuggetPool = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(
            ItemEntry.builder(Items.IRON_NUGGET)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3f, 9f)))
                .apply(moreFromLooting(lookup, UniformLootNumberProvider.create(0f, 1f)))
        )
        gen.create(
            VDEntityTypes.SKILLETON,
            LootTable.builder()
                .pool(nuggetPool)
                .pool(nuggetPool)
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.IRON_INGOT))
                        .conditionally(KilledByPlayerLootCondition.builder())
                        .conditionally(commonWithLooting(lookup, 0.025f, 0.01f))
                )

        )
    }

}

fun BiConsumer<RegistryKey<LootTable>, LootTable.Builder>.create(
    type: EntityType<*>, builder: LootTable.Builder,
) = accept(type.getLootTableId(), builder)


