package org.teamvoided.voided_delight.util.outsource

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.entity.decoration.painting.PaintingVariant
import net.minecraft.registry.RegistryKey
import org.teamvoided.voided_delight.data.gen.data.AdvancementGenerator

// TODO move to Devin
fun FabricLanguageProvider.TranslationBuilder.add(
    painting: RegistryKey<PaintingVariant>, title: String, author: String,
) {
    add(painting.value.toTranslationKey("painting", "title"), title)
    add(painting.value.toTranslationKey("painting", "author"), author)
}

fun FabricLanguageProvider.TranslationBuilder.addAdvancement(name: String, title: String, description: String) {
    add(AdvancementGenerator.tKey("advancement.$name"), title)
    add(AdvancementGenerator.tKey("advancement.$name.desc"), description)
}