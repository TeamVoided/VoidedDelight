package org.teamvoided.voided_delight.data.gen.assets

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.voided_delight.data.VDPaintingVariants
import org.teamvoided.voided_delight.data.gen.data.AdvancementGenerator.Companion.COOKED_TOO_LONG
import org.teamvoided.voided_delight.data.gen.data.AdvancementGenerator.Companion.SKILLET_ISSUE
import org.teamvoided.voided_delight.data.tags.item.VDItemTags
import org.teamvoided.voided_delight.init.VDEntityTypes
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.init.VDTabs
import org.teamvoided.voided_delight.util.outsource.add
import org.teamvoided.voided_delight.util.outsource.addAdvancement
import java.util.concurrent.CompletableFuture

class EnLangProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        VDTabs.getKey(VDTabs.VOIDED_DELIGHT_ITEMS)?.let { gen.add(it, "Voided Delight") }
        println(VDItems.ITEMS)
        VDItems.ITEMS.forEach { gen.add(it.translationKey, process(genLang(it.id))) }
        VDEntityTypes.ENTITY_TYPES.forEach { gen.add(it.translationKey, genLang(it.id)) }
        VDItemTags.ITEM_TAGS.forEach { gen.add(it, genLang(it.id)) }
        gen.addAdvancement(SKILLET_ISSUE, "Skillet Issue", "Kill a Skilleton with a Skillet")
        gen.addAdvancement(COOKED_TOO_LONG, "This One Cooked For Too Long...", "Acquire a Netherite Skilleton Skull")
        gen.add(VDPaintingVariants.SKILLETON, "Skill Coil", "TheEnderCore")
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
    val EntityType<*>.id get() = Registries.ENTITY_TYPE.getId(this)
}


