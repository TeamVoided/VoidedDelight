package org.teamvoided.voided_delight.init

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.util.TriState
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.item.ItemConvertible
import org.teamvoided.voided_delight.VoidedDelight.config
import org.teamvoided.voided_delight.item.CustomSkillet

fun modifyItemComponents() {
    DefaultItemComponentEvents.MODIFY.register { context ->
        context.modify({ it is CustomSkillet }) { builder, item ->
            if (item is CustomSkillet) builder.put(DataComponentTypes.MAX_DAMAGE, item.tier.durability)
        }
    }
}

fun injectSpawns() {
    BiomeModifications.addSpawn(
        BiomeSelectors.spawnsOneOf(EntityType.SKELETON, EntityType.STRAY, EntityType.BOGGED),
        SpawnGroup.MONSTER, VDEntityTypes.SKILLETON, 25, 4, 4
    )
}

fun modifyEnchanting() {
    EnchantmentEvents.ALLOW_ENCHANTING.register { _, stack, _ ->
        if (stack.isOf(VDItems.LOLLIPOP))
            if (config.canEnchantLollipop) TriState.TRUE
            else TriState.FALSE
        else TriState.DEFAULT
    }
}


fun registerComposting() {

    // region Dusks and Dungeons
    compost(VDItems.LANTERN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.MOSSKIN_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.GLOOM_PUMPKIN_SLICE, 0.5f)
    compost(VDItems.PALE_PUMPKIN_SLICE, 0.5f)

    compost(VDBlocks.STUFFED_LANTERN_PUMPKIN)
    compost(VDBlocks.STUFFED_MOSSKIN_PUMPKIN)
    compost(VDBlocks.STUFFED_GLOOM_PUMPKIN)
    compost(VDBlocks.STUFFED_PALE_PUMPKIN)

    compost(VDBlocks.LANTERN_PUMPKIN_PIE)
    compost(VDBlocks.MOSSKIN_PUMPKIN_PIE)
    compost(VDBlocks.GLOOM_PUMPKIN_PIE)
    compost(VDBlocks.PALE_PUMPKIN_PIE)

    compost(VDItems.LANTERN_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.MOSSKIN_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.GLOOM_PUMPKIN_SLICE, 0.85f)
    compost(VDItems.PALE_PUMPKIN_SLICE, 0.85f)
    // endregion

    // region White Pumpkins
    compost(VDItems.WHITE_PUMPKIN_SLICE, 0.5f)
    compost(VDBlocks.STUFFED_WHITE_PUMPKIN)
    compost(VDItems.WHITE_PUMPKIN_SLICE, 0.85f)
    // endregion

}

fun compost(item: ItemConvertible, chance: Float = 1.0f) = CompostingChanceRegistry.INSTANCE.add(item, chance)

