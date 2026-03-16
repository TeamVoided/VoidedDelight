package org.teamvoided.voided_delight.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.VoidedDelight.log
import org.teamvoided.voided_delight.data.gen.assets.EnLangProvider
import org.teamvoided.voided_delight.data.gen.assets.models.ModelProvider
import org.teamvoided.voided_delight.data.gen.data.AdvancementGenerator
import org.teamvoided.voided_delight.data.gen.data.PaintingCreator
import org.teamvoided.voided_delight.data.gen.data.loot.tables.BlockLootTableProvider
import org.teamvoided.voided_delight.data.gen.data.loot.tables.EntityLootTableProvider
import org.teamvoided.voided_delight.data.gen.data.recipe.RecipesProvider
import org.teamvoided.voided_delight.data.gen.data.tags.BlockTagProvider
import org.teamvoided.voided_delight.data.gen.data.tags.EntityTagProvider
import org.teamvoided.voided_delight.data.gen.data.tags.ItemTagProvider
import org.teamvoided.voided_delight.data.gen.data.tags.PaintingTagProvider
import java.util.concurrent.CompletableFuture

@Suppress("unused")
object VoidedDelightData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()
        // Assets
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnLangProvider)

        // Data
        pack.addProvider(::AdvancementGenerator)
        pack.addProvider(::RecipesProvider)
        pack.addProvider(::DynRegProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::EntityLootTableProvider)
        val blockTags = pack.addProvider(::BlockTagProvider)
        pack.addProvider { o, r -> ItemTagProvider(o, r, blockTags) }
        pack.addProvider(::EntityTagProvider)
        pack.addProvider(::PaintingTagProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.PAINTING_VARIANT, PaintingCreator::bootstrap)
    }

    class DynRegProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, r) {
        override fun configure(provider: HolderLookup.Provider, entries: Entries) {
            entries.addAll(provider.getLookupOrThrow(RegistryKeys.PAINTING_VARIANT))
        }

        override fun getName(): String = id("registry_provider").toString()
    }
}
