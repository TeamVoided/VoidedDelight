package org.teamvoided.voided_delight.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.data.tags.VDItemTags
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.util.add
import vectorwing.farmersdelight.common.registry.ModItems as FDItems
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class ItemTagProvider(
    output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider>,
    blockTagProvider: BlockTagProvider
) : FabricTagProvider.ItemTagProvider(output, completableFuture, blockTagProvider) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {
        candy()
        pumpkins()
    }

    fun candy() {
        getOrCreateTagBuilder(ConventionalItemTags.CANDY_FOODS)
            .add(
                VDItems.CANDY_BERRY,
                VDItems.MARSHMARROW,
                VDItems.LOLLIPOP,
                VDItems.CANDY_CORN,
                VDItems.CLOUDY_CANDY,
                VDItems.SYRUP_APPLE,
                VDItems.CRYSTAL_CANDY_SHARD,
            )
            .add(
                VDBlocks.CRYSTAL_CANDY_BLOCK,
                VDBlocks.CRYSTAL_CANDY_STAIRS,
                VDBlocks.CRYSTAL_CANDY_SLAB,
                VDBlocks.CRYSTAL_CANDY_WALL,
            )
    }

    fun pumpkins() {
        getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
            .add(
                VDBlocks.STUFFED_LANTERN_PUMPKIN,
                VDBlocks.STUFFED_MOSSKIN_PUMPKIN,
                VDBlocks.STUFFED_GLOOM_PUMPKIN,
                VDBlocks.STUFFED_PALE_PUMPKIN,
            )
            .add(
                VDBlocks.LANTERN_PUMPKIN_PIE,
                VDBlocks.MOSSKIN_PUMPKIN_PIE,
                VDBlocks.GLOOM_PUMPKIN_PIE,
                VDBlocks.PALE_PUMPKIN_PIE,
            )
        getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS)
            .add(
                VDItems.LANTERN_PUMPKIN_SOUP,
                VDItems.MOSSKIN_PUMPKIN_SOUP,
                VDItems.GLOOM_PUMPKIN_SOUP,
                VDItems.PALE_PUMPKIN_SOUP
            )
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, id("c", "foods/pie")))
            .add(
                VDBlocks.LANTERN_PUMPKIN_PIE,
                VDBlocks.MOSSKIN_PUMPKIN_PIE,
                VDBlocks.GLOOM_PUMPKIN_PIE,
                VDBlocks.PALE_PUMPKIN_PIE,
            )
        getOrCreateTagBuilder(VDItemTags.PUMPKIN_SLICES)
            .add(
                FDItems.PUMPKIN_SLICE.get(),
                VDItems.LANTERN_PUMPKIN_SLICE,
                VDItems.MOSSKIN_PUMPKIN_SLICE,
                VDItems.GLOOM_PUMPKIN_SLICE,
                VDItems.PALE_PUMPKIN_SLICE,
            )
        getOrCreateTagBuilder(VDItemTags.PIE_SLICES)
            .add(
                VDItems.LANTERN_PUMPKIN_PIE_SLICE,
                VDItems.MOSSKIN_PUMPKIN_PIE_SLICE,
                VDItems.GLOOM_PUMPKIN_PIE_SLICE,
                VDItems.PALE_PUMPKIN_PIE_SLICE
            )
            .add(
                FDItems.APPLE_PIE_SLICE.get(),
                FDItems.CHOCOLATE_PIE_SLICE.get(),
            )
    }
}