package com.seleneandmana.compatoplenty.core.data.server.tags;

import com.ninni.twigs.TwigsTags;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.CompatBlocks;
import com.seleneandmana.compatoplenty.core.registry.CompatItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class CompatItemTagsProvider extends ItemTagsProvider {
    public CompatItemTagsProvider(PackOutput output, CompletableFuture<Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, registries, blockTags, CompatOPlenty.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            tag(ItemTags.PLANKS).add(asItem(CompatBlocks.VERTICAL_PLANKS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_CHESTS).add(asItem(CompatBlocks.CHESTS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_TRAPPED_CHESTS).add(asItem(CompatBlocks.TRAPPED_CHESTS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_LADDERS).add(asItem(CompatBlocks.LADDERS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_BEEHIVES).add(asItem(CompatBlocks.BEEHIVES.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_BOOKSHELVES).add(asItem(CompatBlocks.BOOKSHELVES.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_CHISELED_BOOKSHELVES); // TODO
            tag(BlueprintItemTags.WOODEN_BOARDS).add(asItem(CompatBlocks.BOARDS.get(woodMaterial)));
            tag(BlueprintItemTags.LEAF_PILES).add(asItem(CompatBlocks.LEAF_PILES.get(woodMaterial)));
            tag(ModTags.WOODEN_CABINETS).add(asItem(CompatBlocks.CABINETS.get(woodMaterial)));
            tag(TwigsTags.TABLES_ITEM).add(asItem(CompatBlocks.TABLES.get(woodMaterial)));
        }
        tag(ItemTags.SLABS).add(
                asItem(CompatBlocks.BLACK_SANDSTONE_BRICK_SLAB),
                asItem(CompatBlocks.ORANGE_SANDSTONE_BRICK_SLAB),
                asItem(CompatBlocks.WHITE_SANDSTONE_BRICK_SLAB),
                asItem(CompatBlocks.GALANOS_SLAB),
                asItem(CompatBlocks.POLISHED_ROSE_QUARTZ_SLAB),
                asItem(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB)
        );
        tag(ItemTags.STAIRS).add(
                asItem(CompatBlocks.BLACK_SANDSTONE_BRICK_STAIRS),
                asItem(CompatBlocks.ORANGE_SANDSTONE_BRICK_STAIRS),
                asItem(CompatBlocks.WHITE_SANDSTONE_BRICK_STAIRS),
                asItem(CompatBlocks.GALANOS_STAIRS),
                asItem(CompatBlocks.POLISHED_ROSE_QUARTZ_STAIRS),
                asItem(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS)
        );
        tag(ItemTags.WALLS).add(
                asItem(CompatBlocks.BLACK_SANDSTONE_BRICK_WALL),
                asItem(CompatBlocks.ORANGE_SANDSTONE_BRICK_WALL),
                asItem(CompatBlocks.WHITE_SANDSTONE_BRICK_WALL),
                asItem(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL)
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                asItem(CompatBlocks.VERTICAL_SLABS.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.VERTICAL_PLANKS.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.BOARDS.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.BOOKSHELVES.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.LADDERS.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.BEEHIVES.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.CHESTS.get(WoodMaterial.HELLBARK)),
                asItem(CompatBlocks.TRAPPED_CHESTS.get(WoodMaterial.HELLBARK)),
                CompatItems.LARGE_HELLBARK_BOAT.get()
        );
        tag(BlueprintItemTags.FURNACE_BOATS).add(CompatItems.HELLBARK_FURNACE_BOAT.get());
        tag(BlueprintItemTags.LARGE_BOATS).add(CompatItems.LARGE_HELLBARK_BOAT.get());
    }

    public static Item asItem(RegistryObject<? extends Block> block) {
        return block.get().asItem();
    }
}
