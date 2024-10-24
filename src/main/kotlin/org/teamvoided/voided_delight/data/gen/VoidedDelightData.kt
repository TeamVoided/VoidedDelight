package org.teamvoided.voided_delight.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.voided_delight.VoidedDelight.log
import org.teamvoided.voided_delight.data.gen.providers.BlockLootTableProvider
import org.teamvoided.voided_delight.data.gen.providers.EnglishTranslationProvider
import org.teamvoided.voided_delight.data.gen.providers.ModelProvider
import org.teamvoided.voided_delight.data.gen.providers.RecipesProvider
import org.teamvoided.voided_delight.data.gen.tags.BlockTagProvider
import org.teamvoided.voided_delight.data.gen.tags.ItemTagProvider


@Suppress("unused")
object VoidedDelightData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)
        pack.addProvider(::RecipesProvider)
        pack.addProvider(::BlockLootTableProvider)

        val blockTags = pack.addProvider(::BlockTagProvider)
        pack.addProvider { o, r -> ItemTagProvider(o, r, blockTags) }
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
//        gen.add(RegistryKeys.BIOME, TemplateBiomes::boostrap)
    }
}
