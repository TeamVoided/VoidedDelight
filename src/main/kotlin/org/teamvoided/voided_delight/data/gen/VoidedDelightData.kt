package org.teamvoided.voided_delight.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.voided_delight.VoidedDelight.log
import org.teamvoided.voided_delight.data.gen.data.BlockLootTableProvider
import org.teamvoided.voided_delight.data.gen.assets.EnglishTranslationProvider
import org.teamvoided.voided_delight.data.gen.assets.ModelProvider
import org.teamvoided.voided_delight.data.gen.data.recipe.RecipesProvider
import org.teamvoided.voided_delight.data.gen.data.tags.BlockTagProvider
import org.teamvoided.voided_delight.data.gen.data.tags.ItemTagProvider


@Suppress("unused")
object VoidedDelightData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()
        // Assets
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)

        // Data
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::RecipesProvider)
        val blockTags = pack.addProvider(::BlockTagProvider)
        pack.addProvider { o, r -> ItemTagProvider(o, r, blockTags) }
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
//        gen.add(RegistryKeys.BIOME, TemplateBiomes::boostrap)
    }
}
