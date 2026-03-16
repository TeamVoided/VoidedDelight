package org.teamvoided.voided_delight.data.gen.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementHolder
import net.minecraft.advancement.AdvancementType
import net.minecraft.advancement.criterion.InventoryChangedCriterionTrigger
import net.minecraft.advancement.criterion.OnKilledCriterionTrigger.Conditions.createKilled
import net.minecraft.item.ItemConvertible
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.registry.HolderLookup
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.teamvoided.voided_delight.VoidedDelight.MODID
import org.teamvoided.voided_delight.VoidedDelight.fd
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.init.VDEntityTypes
import org.teamvoided.voided_delight.init.VDItems
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementGenerator(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricAdvancementProvider(o, r) {
    override fun generateAdvancement(lookup: HolderLookup.Provider, gen: Consumer<AdvancementHolder>) {
        val portableCooking = AdvancementHolder(fd("main/use_skillet"), null)
        val skilletIssue = getAdvancement(
            portableCooking,
            VDItems.SKILLETON_SKULL,
            SKILLET_ISSUE,
            AdvancementType.TASK,
            showToast = true,
            announceToChat = true,
            hidden = false
        )
            .putCriteria("skilleton", createKilled(EntityPredicate.Builder.create().type(VDEntityTypes.SKILLETON)))
            .build(gen, id("main/kill_skilleton"))


        getAdvancement(
            skilletIssue,
            VDItems.NETHERITE_SKILLETON_SKULL,
            COOKED_TOO_LONG,
            AdvancementType.CHALLENGE,
            showToast = true, announceToChat = true, hidden = true
        )
            .putCriteria(
                "netherite_skull",
                InventoryChangedCriterionTrigger.Conditions.create(VDItems.NETHERITE_SKILLETON_SKULL)
            )
            .build(gen, id("main/get_netherite_skull"))
    }

    companion object {
        const val SKILLET_ISSUE = "kill_skilleton"
        const val COOKED_TOO_LONG = "cooked_too_long"

        fun getAdvancement(
            parent: AdvancementHolder, display: ItemConvertible, name: String,
            frame: AdvancementType, showToast: Boolean, announceToChat: Boolean, hidden: Boolean,
        ): Advancement.Builder = Advancement.Builder.create().parent(parent).display(
            display,
            getTranslation("advancement.$name"),
            getTranslation("advancement.$name.desc"),
            null, frame,
            showToast, announceToChat, hidden
        )

        fun getTranslation(key: String, vararg args: Any): MutableText = Text.translatable(tKey(key), *args)
        fun tKey(key: String) = "${MODID}.$key"

    }
}

fun Advancement.Builder.build(consumer: Consumer<AdvancementHolder>, id: Identifier): AdvancementHolder =
    build(consumer, id.toString())

