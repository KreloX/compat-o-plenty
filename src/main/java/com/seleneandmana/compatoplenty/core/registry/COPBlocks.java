package com.seleneandmana.compatoplenty.core.registry;

import biomesoplenty.api.block.BOPBlocks;
import com.seleneandmana.compatoplenty.common.blocks.RoseQuartzSlabBlock;
import com.seleneandmana.compatoplenty.common.blocks.RoseQuartzStairBlock;
import com.seleneandmana.compatoplenty.common.blocks.RoseQuartzWallBlock;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.helper.COPBlockSubRegistryHelper;
import com.seleneandmana.compatoplenty.integrations.quark.COPQuark;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;

import static com.seleneandmana.compatoplenty.core.CompatOPlenty.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class COPBlocks {
    private COPBlocks() {
    }

    public static final COPBlockSubRegistryHelper HELPER = REGISTRY_HELPER.getBlockSubHelper();

    // Woodworks
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> BOARDS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> LEAF_PILES = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> LADDERS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> BEEHIVES = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> BOOKSHELVES = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> CHISELED_BOOKSHELVES = new EnumMap<>(WoodMaterial.class); // TODO
    public static final EnumMap<WoodMaterial, RegistryObject<BlueprintChestBlock>> CHESTS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<BlueprintTrappedChestBlock>> TRAPPED_CHESTS = new EnumMap<>(WoodMaterial.class);
    // Farmer's Delight
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> CABINETS = new EnumMap<>(WoodMaterial.class);
    // Twigs
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> TABLES = new EnumMap<>(WoodMaterial.class);
    // Quark
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> HOLLOW_LOGS = new EnumMap<>(WoodMaterial.class); // TODO
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> VERTICAL_PLANKS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> VERTICAL_SLABS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> POSTS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> STRIPPED_POSTS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> HEDGES = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Block>> LEAF_CARPETS = new EnumMap<>(WoodMaterial.class);

    static {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            String woodName = woodMaterial.getName();
            var woodProperties = woodMaterial.getProperties();
            int burnTime = woodMaterial.getBurnTime();
            BOARDS.put(woodMaterial, HELPER.createFuelBlock(woodName + "_boards", () -> new RotatedPillarBlock(woodProperties.planks()), burnTime));
            LADDERS.put(woodMaterial, HELPER.createFuelBlock(woodName + "_ladder", () -> new LadderBlock(woodProperties.ladder()), burnTime));
            BEEHIVES.put(woodMaterial, HELPER.createFuelBlock(woodName + "_beehive", () -> new BlueprintBeehiveBlock(Properties.copy(Blocks.BEEHIVE)), burnTime));
            BOOKSHELVES.put(woodMaterial, HELPER.createFuelBlock(woodName + "_bookshelf", () -> new Block(woodProperties.bookshelf()), burnTime));
            CHESTS.put(woodMaterial, HELPER.createChestBlock(woodMaterial));
            TRAPPED_CHESTS.put(woodMaterial, HELPER.createTrappedChestBlock(woodMaterial));
            LEAF_PILES.put(woodMaterial, HELPER.createBlock(woodName + "_leaf_pile", () -> new LeafPileBlock(woodProperties.leafPile())));

            CABINETS.put(woodMaterial, HELPER.createCabinetBlock(woodMaterial));

            TABLES.put(woodMaterial, HELPER.createTableBlock(woodMaterial));

            VERTICAL_PLANKS.put(woodMaterial, HELPER.createFuelBlock("vertical_" + woodName + "_planks", () -> new Block(woodProperties.planks()), burnTime));
            VERTICAL_SLABS.put(woodMaterial, HELPER.createWoodVerticalSlabBlock(woodMaterial));
            POSTS.put(woodMaterial, HELPER.createWoodPostBlock("", woodMaterial));
            STRIPPED_POSTS.put(woodMaterial, HELPER.createWoodPostBlock("stripped_", woodMaterial));
            HEDGES.put(woodMaterial, HELPER.createHedgeBlock(woodName + "_hedge", bopBlock(woodName + "_fence"), bopBlock(woodName + "_leaves"), burnTime));
            LEAF_CARPETS.put(woodMaterial, HELPER.createLeafCarpetBlock(woodMaterial));
        }
    }

    // Sandstone Blocks
    public static final Properties WHITE_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F);
    public static final RegistryObject<Block> WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("white_sandstone_vertical_slab",
            () -> BOPBlocks.WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> CUT_WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_white_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_white_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final RegistryObject<Block> WHITE_SANDSTONE_BRICKS = HELPER.createBlock("white_sandstone_bricks",
            () -> new Block(WHITE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> WHITE_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("white_sandstone_brick_stairs",
            () -> new StairBlock(WHITE_SANDSTONE_BRICKS.get().defaultBlockState(), WHITE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> WHITE_SANDSTONE_BRICK_SLAB = HELPER.createBlock("white_sandstone_brick_slab",
            () -> new SlabBlock(WHITE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> WHITE_SANDSTONE_BRICK_WALL = HELPER.createBlock("white_sandstone_brick_wall",
            () -> new WallBlock(WHITE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> WHITE_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("white_sandstone_brick_vertical_slab",
            WHITE_SANDSTONE_BRICK_SLAB, WHITE_SANDSTONE_PROPERTIES);

    public static final Properties ORANGE_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(0.8F);
    public static final RegistryObject<Block> ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("orange_sandstone_vertical_slab",
            () -> BOPBlocks.ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> CUT_ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_orange_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_orange_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final RegistryObject<Block> ORANGE_SANDSTONE_BRICKS = HELPER.createBlock("orange_sandstone_bricks",
            () -> new Block(ORANGE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> ORANGE_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("orange_sandstone_brick_stairs",
            () -> new StairBlock(ORANGE_SANDSTONE_BRICKS.get().defaultBlockState(), ORANGE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> ORANGE_SANDSTONE_BRICK_SLAB = HELPER.createBlock("orange_sandstone_brick_slab",
            () -> new SlabBlock(ORANGE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> ORANGE_SANDSTONE_BRICK_WALL = HELPER.createBlock("orange_sandstone_brick_wall",
            () -> new WallBlock(ORANGE_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("orange_sandstone_brick_vertical_slab",
            ORANGE_SANDSTONE_BRICK_SLAB, ORANGE_SANDSTONE_PROPERTIES);

    public static final Properties BLACK_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F);
    public static final RegistryObject<Block> BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("black_sandstone_vertical_slab",
            () -> BOPBlocks.BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> CUT_BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_black_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_PROPERTIES);
    public static final RegistryObject<Block> SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_black_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static final RegistryObject<Block> BLACK_SANDSTONE_BRICKS = HELPER.createBlock("black_sandstone_bricks",
            () -> new Block(BLACK_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> BLACK_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("black_sandstone_brick_stairs",
            () -> new StairBlock(BLACK_SANDSTONE_BRICKS.get().defaultBlockState(), BLACK_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> BLACK_SANDSTONE_BRICK_SLAB = HELPER.createBlock("black_sandstone_brick_slab",
            () -> new SlabBlock(BLACK_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> BLACK_SANDSTONE_BRICK_WALL = HELPER.createBlock("black_sandstone_brick_wall",
            () -> new WallBlock(BLACK_SANDSTONE_PROPERTIES));
    public static final RegistryObject<Block> BLACK_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("black_sandstone_brick_vertical_slab",
            BLACK_SANDSTONE_BRICK_SLAB, BLACK_SANDSTONE_PROPERTIES);

    // Galanos Blocks
    public static final Properties GALANOS_PROPERTIES = Properties.of().mapColor(MapColor.DIAMOND).requiresCorrectToolForDrops().strength(1.5f, 6f).lightLevel(state -> 6).sound(SoundType.STONE);
    public static final RegistryObject<Block> GALANOS_BLOCK = HELPER.createBlock("galanos_block",
            () -> new Block(GALANOS_PROPERTIES));
    public static final RegistryObject<Block> GALANOS_PILLAR = HELPER.createBlock("galanos_pillar",
            () -> new RotatedPillarBlock(GALANOS_PROPERTIES));
    public static final RegistryObject<Block> GALANOS_STAIRS = HELPER.createBlock("galanos_stairs",
            () -> new StairBlock(() -> GALANOS_BLOCK.get().defaultBlockState(), GALANOS_PROPERTIES));
    public static final RegistryObject<Block> GALANOS_SLAB = HELPER.createBlock("galanos_slab",
            () -> new SlabBlock(GALANOS_PROPERTIES));
    public static final RegistryObject<Block> GALANOS_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("galanos_vertical_slab",
            GALANOS_SLAB, GALANOS_PROPERTIES);

    // Polished Rose Quartz Blocks
    public static final Properties POLISHED_ROSE_QUARTZ_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F);
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ = HELPER.createBlock("polished_rose_quartz",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_STAIRS = HELPER.createBlock("polished_rose_quartz_stairs",
            () -> new RoseQuartzStairBlock(POLISHED_ROSE_QUARTZ.get().defaultBlockState(), POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_SLAB = HELPER.createBlock("polished_rose_quartz_slab",
            () -> new RoseQuartzSlabBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_VERTICAL_SLAB = HELPER.createBlock("polished_rose_quartz_vertical_slab",
            ModList.get().isLoaded(QUARK_ID)
                    ? COPQuark.roseQuartzVerticalSlab(POLISHED_ROSE_QUARTZ_SLAB, POLISHED_ROSE_QUARTZ_PROPERTIES)
                    : () -> new Block(POLISHED_ROSE_QUARTZ_PROPERTIES));

    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICKS = HELPER.createBlock("polished_rose_quartz_bricks",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_STAIRS = HELPER.createBlock("polished_rose_quartz_brick_stairs",
            () -> new RoseQuartzStairBlock(POLISHED_ROSE_QUARTZ_BRICKS.get().defaultBlockState(), POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_SLAB = HELPER.createBlock("polished_rose_quartz_brick_slab",
            () -> new RoseQuartzSlabBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_WALL = HELPER.createBlock("polished_rose_quartz_brick_wall",
            () -> new RoseQuartzWallBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB = HELPER.createBlock("polished_rose_quartz_brick_vertical_slab",
            ModList.get().isLoaded(QUARK_ID)
                    ? COPQuark.roseQuartzVerticalSlab(POLISHED_ROSE_QUARTZ_SLAB, POLISHED_ROSE_QUARTZ_PROPERTIES)
                    : () -> new Block(POLISHED_ROSE_QUARTZ_PROPERTIES));

    public static final RegistryObject<Block> CRACKED_POLISHED_ROSE_QUARTZ_BRICKS = HELPER.createBlock("cracked_polished_rose_quartz_bricks",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static final RegistryObject<Block> CHISELED_POLISHED_ROSE_QUARTZ = HELPER.createBlock("chiseled_polished_rose_quartz",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));

    // Misc
    public static final RegistryObject<Block> FLOWERING_OAK_LEAF_PILE = HELPER.createBlock("flowering_oak_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> FLOWERING_OAK_LEAF_CARPET = HELPER.createLeafCarpetBlock("flowering_oak_leaf_carpet",
            () -> BOPBlocks.FLOWERING_OAK_LEAVES);
    public static final RegistryObject<Block> FLOWERING_OAK_HEDGE = HELPER.createHedgeBlock("flowering_oak_hedge",
            () -> Blocks.OAK_FENCE, () -> BOPBlocks.FLOWERING_OAK_LEAVES, 300);

    public static final RegistryObject<Block> RAINBOW_BIRCH_LEAF_PILE = HELPER.createBlock("rainbow_birch_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> RAINBOW_BIRCH_LEAF_CARPET = HELPER.createLeafCarpetBlock("rainbow_birch_leaf_carpet",
            () -> BOPBlocks.RAINBOW_BIRCH_LEAVES);
    public static final RegistryObject<Block> RAINBOW_BIRCH_HEDGE = HELPER.createHedgeBlock("rainbow_birch_hedge",
            () -> Blocks.BIRCH_FENCE, () -> BOPBlocks.RAINBOW_BIRCH_LEAVES, 300);

    public static final RegistryObject<Block> ORIGIN_LEAF_PILE = HELPER.createBlock("origin_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> ORIGIN_LEAF_CARPET = HELPER.createLeafCarpetBlock("origin_leaf_carpet",
            () -> BOPBlocks.ORIGIN_LEAVES);
    public static final RegistryObject<Block> ORIGIN_HEDGE = HELPER.createHedgeBlock("origin_hedge",
            () -> Blocks.OAK_FENCE, () -> BOPBlocks.ORIGIN_LEAVES, 300);

    public static final RegistryObject<Block> RED_MAPLE_LEAF_PILE = HELPER.createBlock("red_maple_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> RED_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("red_maple_leaf_carpet",
            () -> BOPBlocks.RED_MAPLE_LEAVES);
    public static final RegistryObject<Block> RED_MAPLE_HEDGE = HELPER.createHedgeBlock("red_maple_hedge",
            () -> Blocks.OAK_FENCE, () -> BOPBlocks.RED_MAPLE_LEAVES, 300);

    public static final RegistryObject<Block> ORANGE_MAPLE_LEAF_PILE = HELPER.createBlock("orange_maple_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> ORANGE_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("orange_maple_leaf_carpet",
            () -> BOPBlocks.ORANGE_MAPLE_LEAVES);
    public static final RegistryObject<Block> ORANGE_MAPLE_HEDGE = HELPER.createHedgeBlock("orange_maple_hedge",
            () -> Blocks.DARK_OAK_FENCE, () -> BOPBlocks.ORANGE_MAPLE_LEAVES, 300);

    public static final RegistryObject<Block> YELLOW_MAPLE_LEAF_PILE = HELPER.createBlock("yellow_maple_leaf_pile",
            () -> new LeafPileBlock(WoodMaterial.WILLOW.getProperties().leafPile()));
    public static final RegistryObject<Block> YELLOW_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("yellow_maple_leaf_carpet",
            () -> BOPBlocks.YELLOW_MAPLE_LEAVES);
    public static final RegistryObject<Block> YELLOW_MAPLE_HEDGE = HELPER.createHedgeBlock("yellow_maple_hedge",
            () -> Blocks.BIRCH_FENCE, () -> BOPBlocks.YELLOW_MAPLE_LEAVES, 300);
}
