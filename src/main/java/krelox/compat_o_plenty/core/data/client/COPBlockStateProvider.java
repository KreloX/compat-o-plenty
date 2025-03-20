package krelox.compat_o_plenty.core.data.client;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.api.block.BOPBlocks;
import com.ninni.twigs.TwigsProperties;
import krelox.compat_o_plenty.core.CompatOPlenty;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.violetmoon.quark.content.building.block.HedgeBlock;
import org.violetmoon.quark.content.building.block.VerticalSlabBlock;
import org.violetmoon.quark.content.building.block.WoodPostBlock;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Function;
import java.util.function.Supplier;

import static krelox.compat_o_plenty.core.registry.COPBlocks.*;

public class COPBlockStateProvider extends BlueprintBlockStateProvider {
    public COPBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, CompatOPlenty.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (var woodType : WOOD_PROPERTIES.keySet()) {
            String materialName = woodType.name().replace(BOPAPI.MOD_ID + ":", "");
            Block leaves = CompatOPlenty.bopBlock(materialName + "_leaves").get();
            Block planks = CompatOPlenty.bopBlock(materialName + "_planks").get();
            Block log = CompatOPlenty.bopBlock(materialName + "_log").get();

            // Woodworks
            boardsBlock(BOARDS.get(woodType));
            leafPileBlock(leaves, LEAF_PILES.get(woodType));
            ladderBlock(LADDERS.get(woodType));
            beehiveBlock(BEEHIVES.get(woodType));
            bookshelfBlock(planks, BOOKSHELVES.get(woodType));
            chestBlocks(planks, CHESTS.get(woodType), TRAPPED_CHESTS.get(woodType));

            // Farmer's Delight
            cabinetBlock(CABINETS.get(woodType));

            // Twigs
            tableBlock(planks, TABLES.get(woodType));

            // Quark
            hollowLogBlock(log, HOLLOW_LOGS.get(woodType));
            verticalPlanksBlock(planks, VERTICAL_PLANKS.get(woodType));
            leafCarpetBlock(leaves, LEAF_CARPETS.get(woodType));
            hedgeBlock(leaves, log, HEDGES.get(woodType));
            verticalSlabBlock(planks, VERTICAL_SLABS.get(woodType));
            woodPostBlock(log, POSTS.get(woodType));
            woodPostBlock(CompatOPlenty.bopBlock("stripped_" + materialName + "_log").get(), STRIPPED_POSTS.get(woodType));
        }

        verticalSlabBlock(BOPBlocks.WHITE_SANDSTONE, WHITE_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.CUT_WHITE_SANDSTONE, CUT_WHITE_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.SMOOTH_WHITE_SANDSTONE, SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB);
        baseBlocks(WHITE_SANDSTONE_BRICKS, WHITE_SANDSTONE_BRICK_STAIRS, WHITE_SANDSTONE_BRICK_SLAB, WHITE_SANDSTONE_BRICK_WALL);
        verticalSlabBlock(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_VERTICAL_SLAB);

        verticalSlabBlock(BOPBlocks.ORANGE_SANDSTONE, ORANGE_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.CUT_ORANGE_SANDSTONE, CUT_ORANGE_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.SMOOTH_ORANGE_SANDSTONE, SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB);
        baseBlocks(ORANGE_SANDSTONE_BRICKS, ORANGE_SANDSTONE_BRICK_STAIRS, ORANGE_SANDSTONE_BRICK_SLAB, ORANGE_SANDSTONE_BRICK_WALL);
        verticalSlabBlock(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB);

        verticalSlabBlock(BOPBlocks.BLACK_SANDSTONE, BLACK_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.CUT_BLACK_SANDSTONE, CUT_BLACK_SANDSTONE_VERTICAL_SLAB);
        verticalSlabBlock(BOPBlocks.SMOOTH_BLACK_SANDSTONE, SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB);
        baseBlocks(BLACK_SANDSTONE_BRICKS, BLACK_SANDSTONE_BRICK_STAIRS, BLACK_SANDSTONE_BRICK_SLAB, BLACK_SANDSTONE_BRICK_WALL);
        verticalSlabBlock(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_VERTICAL_SLAB);

        block(GALANOS_BLOCK.get());
        logBlock((RotatedPillarBlock) GALANOS_PILLAR.get());
        blockItem(GALANOS_PILLAR);
        stairsBlock(GALANOS_BLOCK.get(), GALANOS_STAIRS.get());
        slabBlock(GALANOS_BLOCK.get(), GALANOS_SLAB.get());
        verticalSlabBlock(GALANOS_BLOCK.get(), GALANOS_VERTICAL_SLAB);

        baseBlocks(POLISHED_ROSE_QUARTZ, POLISHED_ROSE_QUARTZ_STAIRS, POLISHED_ROSE_QUARTZ_SLAB);
        verticalSlabBlock(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_VERTICAL_SLAB);

        baseBlocks(POLISHED_ROSE_QUARTZ_BRICKS, POLISHED_ROSE_QUARTZ_BRICK_STAIRS, POLISHED_ROSE_QUARTZ_BRICK_SLAB, POLISHED_ROSE_QUARTZ_BRICK_WALL);
        verticalSlabBlock(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB);

        block(CRACKED_POLISHED_ROSE_QUARTZ_BRICKS);
        block(CHISELED_POLISHED_ROSE_QUARTZ);

        leafPileBlock(BOPBlocks.ORIGIN_LEAVES, ORIGIN_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.ORIGIN_LEAVES, ORIGIN_LEAF_CARPET);
        hedgeBlock(BOPBlocks.ORIGIN_LEAVES, Blocks.OAK_LOG, ORIGIN_HEDGE);

        leafPileBlock(BOPBlocks.FLOWERING_OAK_LEAVES, FLOWERING_OAK_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.FLOWERING_OAK_LEAVES, FLOWERING_OAK_LEAF_CARPET);
        hedgeBlock(BOPBlocks.FLOWERING_OAK_LEAVES, Blocks.OAK_LOG, FLOWERING_OAK_HEDGE);

        leafPileBlock(BOPBlocks.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_LEAF_CARPET);
        hedgeBlock(BOPBlocks.RAINBOW_BIRCH_LEAVES, Blocks.BIRCH_LOG, RAINBOW_BIRCH_HEDGE);

        leafPileBlock(BOPBlocks.CYPRESS_LEAVES, CYPRESS_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.CYPRESS_LEAVES, CYPRESS_LEAF_CARPET);
        hedgeBlock(BOPBlocks.CYPRESS_LEAVES, Blocks.OAK_LOG, CYPRESS_HEDGE);

        leafPileBlock(BOPBlocks.SNOWBLOSSOM_LEAVES, SNOWBLOSSOM_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.SNOWBLOSSOM_LEAVES, SNOWBLOSSOM_LEAF_CARPET);
        hedgeBlock(BOPBlocks.SNOWBLOSSOM_LEAVES, Blocks.OAK_LOG, SNOWBLOSSOM_HEDGE);

        leafPileBlock(BOPBlocks.RED_MAPLE_LEAVES, RED_MAPLE_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.RED_MAPLE_LEAVES, RED_MAPLE_LEAF_CARPET);
        hedgeBlock(BOPBlocks.RED_MAPLE_LEAVES, Blocks.OAK_LOG, RED_MAPLE_HEDGE);

        leafPileBlock(BOPBlocks.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_LEAF_CARPET);
        hedgeBlock(BOPBlocks.ORANGE_MAPLE_LEAVES, Blocks.DARK_OAK_LOG, ORANGE_MAPLE_HEDGE);

        leafPileBlock(BOPBlocks.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_LEAF_PILE);
        leafCarpetBlock(BOPBlocks.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_LEAF_CARPET);
        hedgeBlock(BOPBlocks.YELLOW_MAPLE_LEAVES, Blocks.BIRCH_LOG, YELLOW_MAPLE_HEDGE);
    }

    public void cabinetBlock(Supplier<Block> cabinet) {
        Block cabinetBlock = cabinet.get();
        horizontalBlock(cabinetBlock, state -> {
            String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return models().orientable(name(cabinetBlock) + suffix,
                    blockTexture(cabinetBlock).withSuffix("_side"),
                    blockTexture(cabinetBlock).withSuffix("_front" + suffix),
                    blockTexture(cabinetBlock).withSuffix("_top"));
        });
        blockItem(cabinetBlock);
    }


    public void tableBlock(Block planks, Supplier<Block> table) {
        Block tableBlock = table.get();
        Function<String, ModelFile> model = suffix -> models().withExistingParent(name(tableBlock) + suffix, "twigs:block/template_table" + suffix)
                .texture("side", blockTexture(tableBlock))
                .texture("bottom", blockTexture(tableBlock).withSuffix("_bottom"))
                .texture("top", blockTexture(tableBlock).withSuffix("_top"))
                .texture("particle", blockTexture(planks));
        ModelFile tableLeg = model.apply("_leg");
        getMultipartBuilder(tableBlock)
                .part().modelFile(model.apply("_top")).addModel().end()
                .part().modelFile(tableLeg).addModel().condition(TwigsProperties.TABLE_LEG1, true).end()
                .part().modelFile(tableLeg).rotationY(90).addModel().condition(TwigsProperties.TABLE_LEG2, true).end()
                .part().modelFile(tableLeg).rotationY(180).addModel().condition(TwigsProperties.TABLE_LEG3, true).end()
                .part().modelFile(tableLeg).rotationY(270).addModel().condition(TwigsProperties.TABLE_LEG4, true).end();
        simpleBlockItem(tableBlock, model.apply("_inventory"));
    }

    public void hollowLogBlock(Block log, Supplier<Block> hollowLog) {
        if (hollowLog.get() instanceof RotatedPillarBlock hollowLogBlock) {
            var logLoc = blockTexture(log);
            Function<String, ModelFile> model = suffix -> models().withExistingParent(name(hollowLogBlock) + suffix, "quark:block/hollow_log" + suffix)
                    .texture("end", logLoc.withSuffix("_top"))
                    .texture("side", logLoc)
                    .texture("inside", logLoc.toString().replace("block/", "block/stripped_"));
            axisBlock(hollowLogBlock, model.apply(""), model.apply("_horizontal"));
            blockItem(hollowLogBlock);
        }
    }

    public void verticalPlanksBlock(Block planks, Supplier<Block> verticalPlanks) {
        simpleBlockWithItem(verticalPlanks.get(), models()
                .withExistingParent(name(verticalPlanks.get()), "quark:block/vertical_planks")
                .texture("all", blockTexture(planks)));
    }

    public void leafCarpetBlock(Block leaves, Supplier<Block> leafCarpet) {
        simpleBlockWithItem(leafCarpet.get(), models()
                .withExistingParent(name(leafCarpet.get()), "quark:block/leaf_carpet")
                .texture("all", blockTexture(leaves)));
    }

    public void hedgeBlock(Block leaves, Block log, Supplier<Block> hedge) {
        String name = name(hedge.get());
        var leavesLoc = blockTexture(leaves);
        ModelFile hedgePost = models().withExistingParent(name + "_post", "quark:block/hedge_post")
                .texture("log", blockTexture(log))
                .texture("leaf", leavesLoc);
        ModelFile hedgeExtend = models().withExistingParent(name + "_extend", "quark:block/hedge_extend")
                .texture("leaf", leavesLoc);
        ModelFile hedgeSide = models().withExistingParent(name + "_side", "quark:block/hedge_side")
                .texture("leaf", leavesLoc);
        getMultipartBuilder(hedge.get())
                .part().modelFile(hedgePost).addModel().condition(HedgeBlock.EXTEND, false).end()
                .part().modelFile(hedgeExtend).addModel().condition(HedgeBlock.EXTEND, true).end()
                .part().modelFile(hedgeSide).uvLock(true).addModel().condition(BlockStateProperties.NORTH, true).end()
                .part().modelFile(hedgeSide).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.EAST, true).end()
                .part().modelFile(hedgeSide).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true).end()
                .part().modelFile(hedgeSide).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.WEST, true).end();
        simpleBlockItem(hedge.get(), hedgePost);
    }

    public void verticalSlabBlock(Block block, Supplier<Block> verticalSlab) {
        var blockLoc = blockTexture(block);
        ModelFile verticalSlabModel = models().withExistingParent(name(verticalSlab.get()), "quark:block/vertical_slab")
                .texture("bottom", blockLoc)
                .texture("top", blockLoc)
                .texture("side", blockLoc);
        getVariantBuilder(verticalSlab.get())
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.NORTH)
                .modelForState().modelFile(verticalSlabModel).rotationY(0).uvLock(true).addModel()
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.EAST)
                .modelForState().modelFile(verticalSlabModel).rotationY(90).uvLock(true).addModel()
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.SOUTH)
                .modelForState().modelFile(verticalSlabModel).rotationY(180).uvLock(true).addModel()
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.WEST)
                .modelForState().modelFile(verticalSlabModel).rotationY(270).uvLock(true).addModel()
                .partialState().with(VerticalSlabBlock.TYPE, VerticalSlabBlock.VerticalSlabType.DOUBLE)
                .modelForState().modelFile(models().getExistingFile(blockLoc)).addModel();
        blockItem(verticalSlab.get());
    }

    public void woodPostBlock(Block log, Supplier<Block> post) {
        String materialName = name(post.get()).replace("_post", "");
        var logLoc = blockTexture(log);
        ModelFile postModel = models().withExistingParent(materialName + "_post", "quark:block/post")
                .texture("texture", logLoc);
        ModelFile chainSmall = models().getExistingFile(mcLoc("quark:block/chain_small"));
        ModelFile chainSmallTop = models().getExistingFile(mcLoc("quark:block/chain_small_top"));
        ModelFile postConnect = models().withExistingParent("block/" + materialName + "_post_connect", "quark:block/post_connect")
                .texture("texture", logLoc);
        ModelFile postConnectTop = models().withExistingParent("block/" + materialName + "_post_connect_top", "quark:block/post_connect_top")
                .texture("texture", logLoc);
        getMultipartBuilder(post.get())
                .part().modelFile(postModel).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Y).end()
                .part().modelFile(postModel).rotationX(90).rotationY(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.X).end()
                .part().modelFile(postModel).rotationX(90).addModel().condition(BlockStateProperties.AXIS, Direction.Axis.Z).end()
                .part().modelFile(chainSmall).addModel().condition(WoodPostBlock.SIDES[0], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(chainSmallTop).addModel().condition(WoodPostBlock.SIDES[1], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(chainSmallTop).rotationX(90).addModel().condition(WoodPostBlock.SIDES[2], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(chainSmall).rotationX(90).addModel().condition(WoodPostBlock.SIDES[3], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(chainSmall).rotationX(90).rotationY(90).addModel().condition(WoodPostBlock.SIDES[4], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(chainSmallTop).rotationX(90).rotationY(90).addModel().condition(WoodPostBlock.SIDES[5], WoodPostBlock.PostSideType.CHAIN).end()
                .part().modelFile(postConnect).addModel().condition(WoodPostBlock.SIDES[0], WoodPostBlock.PostSideType.OTHER_POST).end()
                .part().modelFile(postConnectTop).addModel().condition(WoodPostBlock.SIDES[1], WoodPostBlock.PostSideType.OTHER_POST).end()
                .part().modelFile(postConnectTop).rotationX(90).addModel().condition(WoodPostBlock.SIDES[2], WoodPostBlock.PostSideType.OTHER_POST).end()
                .part().modelFile(postConnect).rotationX(90).addModel().condition(WoodPostBlock.SIDES[3], WoodPostBlock.PostSideType.OTHER_POST).end()
                .part().modelFile(postConnect).rotationX(90).rotationY(90).addModel().condition(WoodPostBlock.SIDES[4], WoodPostBlock.PostSideType.OTHER_POST).end()
                .part().modelFile(postConnectTop).rotationX(90).rotationY(90).addModel().condition(WoodPostBlock.SIDES[5], WoodPostBlock.PostSideType.OTHER_POST).end();
        simpleBlockItem(post.get(), postModel);
    }
}
