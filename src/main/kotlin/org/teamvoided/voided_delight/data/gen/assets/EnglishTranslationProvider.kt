package org.teamvoided.voided_delight.data.gen.assets

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.voided_delight.data.tags.VDItemTags
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.init.VDTabs
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        VDTabs.getKey(VDTabs.VOIDED_DELIGHT_ITEMS)?.let { gen.add(it, "Voided Delight") }
        println(VDItems.ITEMS)
        VDItems.ITEMS.forEach { gen.add(it.translationKey, process(genLang(it.id))) }
        VDItemTags.ITEM_TAGS.forEach { gen.add(it, genLang(it.id)) }
    }

    private fun genLang(id: Identifier): String =
        id.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun process(lang: String): String {
        if (lang.contains("Stuffed")) {
            if (lang.contains(" Block")) {
                return lang.removeSuffix(" Block")
            }

            return "Bowl of $lang"
        }

        return lang
    }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)
}
