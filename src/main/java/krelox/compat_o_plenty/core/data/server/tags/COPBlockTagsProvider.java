package krelox.compat_o_plenty.core.data.server.tags;

import biomesoplenty.api.block.BOPBlocks;
import com.ninni.twigs.TwigsTags;
import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.core.registry.COPBlocks;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.violetmoon.quark.base.Quark;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class COPBlockTagsProvider extends BlockTagsProvider {
    public COPBlockTagsProvider(PackOutput output, CompletableFuture<Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, CompatOPlenty.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(Provider provider) {
        for (var woodType : COPBlocks.WOOD_PROPERTIES.keySet()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(
                    COPBlocks.VERTICAL_SLABS.get(woodType).get(),
                    COPBlocks.POSTS.get(woodType).get(),
                    COPBlocks.STRIPPED_POSTS.get(woodType).get(),
                    COPBlocks.HEDGES.get(woodType).get(),
                    COPBlocks.CABINETS.get(woodType).get(),
                    COPBlocks.VERTICAL_PLANKS.get(woodType).get()
            );
            tag(BlockTags.PLANKS).add(COPBlocks.VERTICAL_PLANKS.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_CHESTS).add(COPBlocks.CHESTS.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(COPBlocks.TRAPPED_CHESTS.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_LADDERS).add(COPBlocks.LADDERS.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(COPBlocks.BEEHIVES.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(COPBlocks.BOOKSHELVES.get(woodType).get());
            tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES); // TODO
            tag(BlueprintBlockTags.WOODEN_BOARDS).add(COPBlocks.BOARDS.get(woodType).get());
            tag(BlueprintBlockTags.LEAF_PILES).add(COPBlocks.LEAF_PILES.get(woodType).get());
            tag(TwigsTags.TABLES_BLOCK).add(COPBlocks.TABLES.get(woodType).get());
            tag(TagUtil.blockTag(Quark.MOD_ID, "hollow_logs")).add(COPBlocks.HOLLOW_LOGS.get(woodType).get());
            tag(TagUtil.blockTag(Quark.MOD_ID, "vertical_slabs")).add(COPBlocks.VERTICAL_SLABS.get(woodType).get());
            tag(TagUtil.blockTag(Quark.MOD_ID, "hedges")).add(COPBlocks.HEDGES.get(woodType).get());
        }
        // Blueprint tags
        tag(BlueprintBlockTags.LEAF_PILES).add(
                COPBlocks.ORIGIN_LEAF_PILE.get(),
                COPBlocks.FLOWERING_OAK_LEAF_PILE.get(),
                COPBlocks.RAINBOW_BIRCH_LEAF_PILE.get(),
                COPBlocks.CYPRESS_LEAF_PILE.get(),
                COPBlocks.SNOWBLOSSOM_LEAF_PILE.get(),
                COPBlocks.RED_MAPLE_LEAF_PILE.get(),
                COPBlocks.ORANGE_MAPLE_LEAF_PILE.get(),
                COPBlocks.YELLOW_MAPLE_LEAF_PILE.get()
        );
        // Quark tags
        tag(TagUtil.blockTag(Quark.MOD_ID, "hedges")).add(
                COPBlocks.FLOWERING_OAK_HEDGE.get(),
                COPBlocks.RAINBOW_BIRCH_HEDGE.get(),
                COPBlocks.ORIGIN_HEDGE.get(),
                COPBlocks.CYPRESS_HEDGE.get(),
                COPBlocks.SNOWBLOSSOM_HEDGE.get(),
                COPBlocks.RED_MAPLE_HEDGE.get(),
                COPBlocks.ORANGE_MAPLE_HEDGE.get(),
                COPBlocks.YELLOW_MAPLE_HEDGE.get()
        );
        // Farmer's Delight Tags
        tag(ModTags.COMPOST_ACTIVATORS).add(BOPBlocks.TOADSTOOL, BOPBlocks.GLOWSHROOM);
        // Minecraft tags
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                COPBlocks.FLOWERING_OAK_HEDGE.get(),
                COPBlocks.RAINBOW_BIRCH_HEDGE.get(),
                COPBlocks.ORIGIN_HEDGE.get(),
                COPBlocks.RED_MAPLE_HEDGE.get(),
                COPBlocks.ORANGE_MAPLE_HEDGE.get(),
                COPBlocks.YELLOW_MAPLE_HEDGE.get()
        );
        tag(BlockTags.MINEABLE_WITH_HOE).add(
                COPBlocks.FLOWERING_OAK_LEAF_CARPET.get(),
                COPBlocks.RAINBOW_BIRCH_LEAF_CARPET.get(),
                COPBlocks.ORIGIN_LEAF_CARPET.get(),
                COPBlocks.RED_MAPLE_LEAF_CARPET.get(),
                COPBlocks.ORANGE_MAPLE_LEAF_CARPET.get(),
                COPBlocks.YELLOW_MAPLE_LEAF_CARPET.get(),

                COPBlocks.FLOWERING_OAK_LEAF_PILE.get(),
                COPBlocks.RAINBOW_BIRCH_LEAF_PILE.get(),
                COPBlocks.ORIGIN_LEAF_PILE.get(),
                COPBlocks.RED_MAPLE_LEAF_PILE.get(),
                COPBlocks.ORANGE_MAPLE_LEAF_PILE.get(),
                COPBlocks.YELLOW_MAPLE_LEAF_PILE.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                COPBlocks.BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.BLACK_SANDSTONE_BRICKS.get(),
                COPBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.BLACK_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                COPBlocks.ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICKS.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                COPBlocks.WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(),
                COPBlocks.WHITE_SANDSTONE_BRICKS.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(),

                COPBlocks.GALANOS_BLOCK.get(),
                COPBlocks.GALANOS_PILLAR.get(),
                COPBlocks.GALANOS_STAIRS.get(),
                COPBlocks.GALANOS_SLAB.get(),
                COPBlocks.GALANOS_VERTICAL_SLAB.get(),

                COPBlocks.POLISHED_ROSE_QUARTZ.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(),
                COPBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get(),
                COPBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get()
        );
        tag(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
                COPBlocks.POLISHED_ROSE_QUARTZ.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(),
                COPBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get(),
                COPBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get()
        );
        tag(BlockTags.SLABS).add(
                COPBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(),
                COPBlocks.GALANOS_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                COPBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get(),
                COPBlocks.GALANOS_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get()
        );
        tag(BlockTags.WALLS).add(
                COPBlocks.BLACK_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.ORANGE_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.WHITE_SANDSTONE_BRICK_WALL.get(),
                COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get()
        );
    }
}
