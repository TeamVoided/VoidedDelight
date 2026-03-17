package org.teamvoided.voided_delight.data.gen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.voided_delight.FDItems
import org.teamvoided.voided_delight.FDTags
import org.teamvoided.voided_delight.VoidedDelight.id
import org.teamvoided.voided_delight.data.tags.block.CBlockTags
import org.teamvoided.voided_delight.data.tags.block.FDBlockTags
import org.teamvoided.voided_delight.data.tags.item.CItemTags
import org.teamvoided.voided_delight.data.tags.item.FDItemTags
import org.teamvoided.voided_delight.data.tags.item.VDItemTags
import org.teamvoided.voided_delight.init.VDBlocks
import org.teamvoided.voided_delight.init.VDItems
import org.teamvoided.voided_delight.util.add
import java.util.concurrent.CompletableFuture

class ItemTagProvider(
    output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider>, blockTag: BlockTagProvider,
) : FabricTagProvider.ItemTagProvider(output, completableFuture, blockTag) {
    override fun configure(wrapperLookup: HolderLookup.Provider) {
        candy()
        pumpkins()
        getOrCreateTagBuilder(FDTags.MEALS).addTag(VDItemTags.MEALS)
        getOrCreateTagBuilder(ConventionalItemTags.FOODS).addTag(VDItemTags.PIE_SLICES)
        copy(FDBlockTags.COOKING_POTS, FDItemTags.COOKING_POTS)
        copy(FDBlockTags.SKILLETS, FDItemTags.SKILLETS)
        copy(CBlockTags.STORAGE_GOLDEN_BEETROOT, CItemTags.STORAGE_GOLDEN_BEETROOT)
        copy(CBlockTags.STORAGE_GOLDEN_CARROT, CItemTags.STORAGE_GOLDEN_CARROT)
        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
            .forceAddTag(CItemTags.STORAGE_GOLDEN_BEETROOT)
            .forceAddTag(CItemTags.STORAGE_GOLDEN_CARROT)


        getOrCreateTagBuilder(ConventionalItemTags.SOUP_FOODS).add(
            VDItems.LANTERN_PUMPKIN_SOUP,
            VDItems.MOSSKIN_PUMPKIN_SOUP,
            VDItems.GLOOM_PUMPKIN_SOUP,
            VDItems.PALE_PUMPKIN_SOUP,
            VDItems.WHITE_PUMPKIN_SOUP,
            VDItems.GOLDEN_BEETROOT_SOUP
        )

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).addOptionalTag(FDItemTags.SKILLETS)
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).addOptionalTag(FDItemTags.SKILLETS)
        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE).addOptionalTag(FDItemTags.SKILLETS)
        getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).addOptionalTag(FDItemTags.SKILLETS)
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).addOptionalTag(FDItemTags.SKILLETS)

        getOrCreateTagBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS).add(VDItems.NETHERITE_SKILLETON_SKULL)
        getOrCreateTagBuilder(ItemTags.SKULLS).add(
            VDItems.SKILLETON_SKULL,
//            VDItems.NETHERITE_SKILLETON_SKULL
        )
        getOrCreateTagBuilder(ItemTags.NOTEBLOCK_TOP_INSTRUMENTS).add(
            VDItems.SKILLETON_SKULL,
//            VDItems.NETHERITE_SKILLETON_SKULL
        )
    }

    fun candy() {
        getOrCreateTagBuilder(ConventionalItemTags.CANDY_FOODS)
            .add(
                VDItems.CANDY_BERRY,
                VDItems.CANDY_CORN,
                VDItems.CANDY_CLOUD,
                VDItems.MARSHMARROW,
                VDItems.LOLLIPOP,
                VDItems.SYRUP_APPLE,
                VDItems.CRYSTAL_CANDY_SHARD,
            )
            .add(
                VDBlocks.CRYSTAL_CANDY_BLOCK,
                VDBlocks.CRYSTAL_CANDY_STAIRS,
                VDBlocks.CRYSTAL_CANDY_SLAB,
                VDBlocks.CRYSTAL_CANDY_WALL,
            )

        getOrCreateTagBuilder(ItemTags.MACE_ENCHANTABLE).add(VDItems.LOLLIPOP)
    }

    fun pumpkins() {
        getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
            .add(
                VDBlocks.STUFFED_LANTERN_PUMPKIN,
                VDBlocks.STUFFED_MOSSKIN_PUMPKIN,
                VDBlocks.STUFFED_GLOOM_PUMPKIN,
                VDBlocks.STUFFED_PALE_PUMPKIN,
                VDBlocks.STUFFED_WHITE_PUMPKIN,
            )
            .add(
                VDBlocks.LANTERN_PUMPKIN_PIE,
                VDBlocks.MOSSKIN_PUMPKIN_PIE,
                VDBlocks.GLOOM_PUMPKIN_PIE,
                VDBlocks.PALE_PUMPKIN_PIE,
            )

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, id("c", "foods/pie")))
            .add(
                VDBlocks.LANTERN_PUMPKIN_PIE,
                VDBlocks.MOSSKIN_PUMPKIN_PIE,
                VDBlocks.GLOOM_PUMPKIN_PIE,
                VDBlocks.PALE_PUMPKIN_PIE,
            )
            .addOptional(id("white_pumpkins", "white_pumpkin_pie"))
        getOrCreateTagBuilder(VDItemTags.PUMPKIN_SLICES)
            .add(
                FDItems.PUMPKIN_SLICE.get(),
                VDItems.LANTERN_PUMPKIN_SLICE,
                VDItems.MOSSKIN_PUMPKIN_SLICE,
                VDItems.GLOOM_PUMPKIN_SLICE,
                VDItems.PALE_PUMPKIN_SLICE,
                VDItems.WHITE_PUMPKIN_SLICE,
            )
        getOrCreateTagBuilder(VDItemTags.PIE_SLICES)
            .add(
                VDItems.LANTERN_PUMPKIN_PIE_SLICE,
                VDItems.MOSSKIN_PUMPKIN_PIE_SLICE,
                VDItems.GLOOM_PUMPKIN_PIE_SLICE,
                VDItems.PALE_PUMPKIN_PIE_SLICE,
                VDItems.WHITE_PUMPKIN_PIE_SLICE,
            )
            .add(
                FDItems.APPLE_PIE_SLICE.get(),
                FDItems.CHOCOLATE_PIE_SLICE.get(),
            )
        getOrCreateTagBuilder(VDItemTags.MEALS).add(
            // Soup
            VDItems.LANTERN_PUMPKIN_SOUP,
            VDItems.MOSSKIN_PUMPKIN_SOUP,
            VDItems.GLOOM_PUMPKIN_SOUP,
            VDItems.PALE_PUMPKIN_SOUP,
            VDItems.WHITE_PUMPKIN_SOUP,
            // Stuffed Pumpkin
            VDItems.STUFFED_LANTERN_PUMPKIN,
            VDItems.STUFFED_MOSSKIN_PUMPKIN,
            VDItems.STUFFED_GLOOM_PUMPKIN,
            VDItems.STUFFED_PALE_PUMPKIN,
            VDItems.STUFFED_WHITE_PUMPKIN,
        )
    }
}