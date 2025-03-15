package com.seleneandmana.compatoplenty.core.data.server.tags;

import com.ninni.twigs.TwigsTags;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.COPBlocks;
import com.seleneandmana.compatoplenty.core.registry.COPItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.blueprint.core.util.TagUtil;
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

public class COPItemTagsProvider extends ItemTagsProvider {
    public COPItemTagsProvider(PackOutput output, CompletableFuture<Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, registries, blockTags, CompatOPlenty.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            tag(ItemTags.PLANKS).add(asItem(COPBlocks.VERTICAL_PLANKS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_CHESTS).add(asItem(COPBlocks.CHESTS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_TRAPPED_CHESTS).add(asItem(COPBlocks.TRAPPED_CHESTS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_LADDERS).add(asItem(COPBlocks.LADDERS.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_BEEHIVES).add(asItem(COPBlocks.BEEHIVES.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_BOOKSHELVES).add(asItem(COPBlocks.BOOKSHELVES.get(woodMaterial)));
            tag(BlueprintItemTags.WOODEN_CHISELED_BOOKSHELVES); // TODO
            tag(BlueprintItemTags.WOODEN_BOARDS).add(asItem(COPBlocks.BOARDS.get(woodMaterial)));
            tag(BlueprintItemTags.LEAF_PILES).add(asItem(COPBlocks.LEAF_PILES.get(woodMaterial)));
            tag(ModTags.WOODEN_CABINETS).add(asItem(COPBlocks.CABINETS.get(woodMaterial)));
            tag(TwigsTags.TABLES_ITEM).add(asItem(COPBlocks.TABLES.get(woodMaterial)));
            tag(TagUtil.itemTag("quark", "hollow_logs")); // TODO
            tag(TagUtil.itemTag("quark", "vertical_slabs")).add(asItem(COPBlocks.VERTICAL_SLABS.get(woodMaterial)));
            tag(TagUtil.itemTag("quark", "hedges")).add(asItem(COPBlocks.HEDGES.get(woodMaterial)));
        }
        tag(ItemTags.SLABS).add(
                asItem(COPBlocks.BLACK_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.GALANOS_SLAB),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_SLAB),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB)
        );
        tag(ItemTags.STAIRS).add(
                asItem(COPBlocks.BLACK_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.GALANOS_STAIRS),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS)
        );
        tag(ItemTags.WALLS).add(
                asItem(COPBlocks.BLACK_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL)
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                asItem(COPBlocks.VERTICAL_SLABS.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.VERTICAL_PLANKS.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.BOARDS.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.BOOKSHELVES.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.LADDERS.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.BEEHIVES.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.CHESTS.get(WoodMaterial.HELLBARK)),
                asItem(COPBlocks.TRAPPED_CHESTS.get(WoodMaterial.HELLBARK)),
                COPItems.LARGE_BOATS.get(WoodMaterial.HELLBARK).get()
        );
        tag(BlueprintItemTags.FURNACE_BOATS).add(COPItems.FURNACE_BOATS.get(WoodMaterial.HELLBARK).get());
        tag(BlueprintItemTags.LARGE_BOATS).add(COPItems.LARGE_BOATS.get(WoodMaterial.HELLBARK).get());
    }

    public static Item asItem(RegistryObject<? extends Block> block) {
        return block.get().asItem();
    }
}
