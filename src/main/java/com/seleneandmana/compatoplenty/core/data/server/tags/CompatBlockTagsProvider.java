package com.seleneandmana.compatoplenty.core.data.server.tags;

import biomesoplenty.api.block.BOPBlocks;
import com.ninni.twigs.TwigsTags;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.CompatBlocks;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class CompatBlockTagsProvider extends BlockTagsProvider {
    public CompatBlockTagsProvider(PackOutput output, CompletableFuture<Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, CompatOPlenty.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(Provider provider) {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(
                    CompatBlocks.VERTICAL_SLABS.get(woodMaterial).get(),
                    CompatBlocks.POSTS.get(woodMaterial).get(),
                    CompatBlocks.STRIPPED_POSTS.get(woodMaterial).get(),
                    CompatBlocks.HEDGES.get(woodMaterial).get(),
                    CompatBlocks.CABINETS.get(woodMaterial).get(),
                    CompatBlocks.VERTICAL_PLANKS.get(woodMaterial).get()
            );
            tag(BlockTags.PLANKS).add(CompatBlocks.VERTICAL_PLANKS.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_CHESTS).add(CompatBlocks.CHESTS.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(CompatBlocks.TRAPPED_CHESTS.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_LADDERS).add(CompatBlocks.LADDERS.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(CompatBlocks.BEEHIVES.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(CompatBlocks.BOOKSHELVES.get(woodMaterial).get());
            tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES); // TODO
            tag(BlueprintBlockTags.WOODEN_BOARDS).add(CompatBlocks.BOARDS.get(woodMaterial).get());
            tag(BlueprintBlockTags.LEAF_PILES).add(CompatBlocks.LEAF_PILES.get(woodMaterial).get());
            tag(TwigsTags.TABLES_BLOCK).add(CompatBlocks.TABLES.get(woodMaterial).get());
        }
        // Minecraft tags
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                CompatBlocks.FLOWERING_OAK_HEDGE.get(),
                CompatBlocks.RAINBOW_BIRCH_HEDGE.get(),
                CompatBlocks.ORIGIN_HEDGE.get(),
                CompatBlocks.RED_MAPLE_HEDGE.get(),
                CompatBlocks.ORANGE_MAPLE_HEDGE.get(),
                CompatBlocks.YELLOW_MAPLE_HEDGE.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                CompatBlocks.FLOWERING_OAK_LEAF_CARPET.get(),
                CompatBlocks.RAINBOW_BIRCH_LEAF_CARPET.get(),
                CompatBlocks.ORIGIN_LEAF_CARPET.get(),
                CompatBlocks.RED_MAPLE_LEAF_CARPET.get(),
                CompatBlocks.ORANGE_MAPLE_LEAF_CARPET.get(),
                CompatBlocks.YELLOW_MAPLE_LEAF_CARPET.get(),

                CompatBlocks.FLOWERING_OAK_LEAF_PILE.get(),
                CompatBlocks.RAINBOW_BIRCH_LEAF_PILE.get(),
                CompatBlocks.ORIGIN_LEAF_PILE.get(),
                CompatBlocks.RED_MAPLE_LEAF_PILE.get(),
                CompatBlocks.ORANGE_MAPLE_LEAF_PILE.get(),
                CompatBlocks.YELLOW_MAPLE_LEAF_PILE.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CompatBlocks.BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.BLACK_SANDSTONE_BRICKS.get(),
                CompatBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.BLACK_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                CompatBlocks.ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICKS.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                CompatBlocks.WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICKS.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                CompatBlocks.GALANOS_BLOCK.get(),
                CompatBlocks.GALANOS_PILLAR.get(),
                CompatBlocks.GALANOS_STAIRS.get(),
                CompatBlocks.GALANOS_SLAB.get(),
                CompatBlocks.GALANOS_VERTICAL_SLAB.get(),

                CompatBlocks.POLISHED_ROSE_QUARTZ.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(),
                CompatBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get(),
                CompatBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get()
        );
        tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
                CompatBlocks.POLISHED_ROSE_QUARTZ.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(),
                CompatBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get(),
                CompatBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get()
        );
        tag(BlockTags.SLABS).add(
                CompatBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(),
                CompatBlocks.GALANOS_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                CompatBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get(),
                CompatBlocks.GALANOS_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get()
        );
        tag(BlockTags.WALLS).add(
                CompatBlocks.BLACK_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.ORANGE_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.WHITE_SANDSTONE_BRICK_WALL.get(),
                CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get()
        );
        // Farmer's Delight Tags
        tag(ModTags.COMPOST_ACTIVATORS).add(BOPBlocks.TOADSTOOL, BOPBlocks.GLOWSHROOM);
        // Blueprint tags
        tag(BlueprintBlockTags.LEAF_PILES).add(
                CompatBlocks.FLOWERING_OAK_LEAF_PILE.get(),
                CompatBlocks.RAINBOW_BIRCH_LEAF_PILE.get(),
                CompatBlocks.ORIGIN_LEAF_PILE.get(),
                CompatBlocks.RED_MAPLE_LEAF_PILE.get(),
                CompatBlocks.ORANGE_MAPLE_LEAF_PILE.get(),
                CompatBlocks.YELLOW_MAPLE_LEAF_PILE.get()
        );
    }
}
