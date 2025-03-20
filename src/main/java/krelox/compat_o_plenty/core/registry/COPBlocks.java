package krelox.compat_o_plenty.core.registry;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPWoodTypes;
import krelox.compat_o_plenty.common.blocks.RoseQuartzSlabBlock;
import krelox.compat_o_plenty.common.blocks.RoseQuartzStairBlock;
import krelox.compat_o_plenty.common.blocks.RoseQuartzWallBlock;
import krelox.compat_o_plenty.core.registry.helper.COPBlockSubRegistryHelper;
import krelox.compat_o_plenty.integrations.quark.COPQuark;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import krelox.compat_o_plenty.core.CompatOPlenty;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

import static com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;

@Mod.EventBusSubscriber(modid = CompatOPlenty.MOD_ID)
public class COPBlocks {
    private COPBlocks() {
    }

    public static final COPBlockSubRegistryHelper HELPER = CompatOPlenty.REGISTRY_HELPER.getBlockSubHelper();

    public static final HashMap<WoodType, WoodSetProperties> WOOD_PROPERTIES = Util.make(new HashMap<>(), map -> {
        map.put(BOPWoodTypes.FIR, WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build());
        //map.put(BOPWoodTypes.PINE, PropertyUtil.WoodSetProperties.builder().build()); TODO
        //map.put(BOPWoodTypes.MAPLE, PropertyUtil.WoodSetProperties.builder().build()); TODO
        map.put(BOPWoodTypes.REDWOOD, WoodSetProperties.builder(MapColor.TERRACOTTA_RED).build());
        map.put(BOPWoodTypes.MAHOGANY, WoodSetProperties.builder(MapColor.TERRACOTTA_PINK).build());
        map.put(BOPWoodTypes.JACARANDA, WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build());
        map.put(BOPWoodTypes.PALM, WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).build());
        map.put(BOPWoodTypes.WILLOW, WoodSetProperties.builder(MapColor.TERRACOTTA_LIGHT_GREEN).build());
        map.put(BOPWoodTypes.DEAD, WoodSetProperties.builder(MapColor.STONE).build());
        map.put(BOPWoodTypes.MAGIC, WoodSetProperties.builder(MapColor.COLOR_BLUE).build());
        map.put(BOPWoodTypes.UMBRAN, WoodSetProperties.builder(MapColor.TERRACOTTA_BLUE).build());
        map.put(BOPWoodTypes.HELLBARK, WoodSetProperties.builder(MapColor.TERRACOTTA_GRAY).fireproof().build());
        //map.put(BOPWoodTypes.EMPYREAL, PropertyUtil.WoodSetProperties.builder().build()); TODO
    });

    // Woodworks
    public static final HashMap<WoodType, RegistryObject<Block>> BOARDS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> LEAF_PILES = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> LADDERS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> BEEHIVES = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> BOOKSHELVES = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> CHISELED_BOOKSHELVES = new HashMap<>(); // TODO
    public static final HashMap<WoodType, RegistryObject<BlueprintChestBlock>> CHESTS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<BlueprintTrappedChestBlock>> TRAPPED_CHESTS = new HashMap<>();
    // Farmer's Delight
    public static final HashMap<WoodType, RegistryObject<Block>> CABINETS = new HashMap<>();
    // Twigs
    public static final HashMap<WoodType, RegistryObject<Block>> TABLES = new HashMap<>();
    // Quark
    public static final HashMap<WoodType, RegistryObject<Block>> HOLLOW_LOGS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> VERTICAL_PLANKS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> VERTICAL_SLABS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> POSTS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> STRIPPED_POSTS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> HEDGES = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Block>> LEAF_CARPETS = new HashMap<>();

    static {
        for (var entry : WOOD_PROPERTIES.entrySet()) {
            WoodType woodType = entry.getKey();
            WoodSetProperties properties = entry.getValue();
            String materialName = woodType.name().replace(BOPAPI.MOD_ID + ":", "");
            int burnTime = properties.planks().ignitedByLava ? 300 : -1;
            BOARDS.put(woodType, HELPER.createFuelBlock(materialName + "_boards", () -> new RotatedPillarBlock(properties.planks()), burnTime));
            LADDERS.put(woodType, HELPER.createFuelBlock(materialName + "_ladder", () -> new LadderBlock(properties.ladder()), burnTime));
            BEEHIVES.put(woodType, HELPER.createFuelBlock(materialName + "_beehive", () -> new BlueprintBeehiveBlock(properties.beehive()), burnTime));
            BOOKSHELVES.put(woodType, HELPER.createFuelBlock(materialName + "_bookshelf", () -> new Block(properties.bookshelf()), burnTime));
            CHESTS.put(woodType, HELPER.createChestBlock(materialName, properties.chest()));
            TRAPPED_CHESTS.put(woodType, HELPER.createTrappedChestBlockNamed(materialName, properties.chest()));
            LEAF_PILES.put(woodType, HELPER.createBlock(materialName + "_leaf_pile", () -> new LeafPileBlock(properties.leafPile())));

            CABINETS.put(woodType, HELPER.createCabinetBlock(materialName, burnTime));
            TABLES.put(woodType, HELPER.createTableBlock(materialName, properties.planks()));

            HOLLOW_LOGS.put(woodType, HELPER.createHollowLogBlock(materialName, CompatOPlenty.bopBlock(materialName + "_log"), properties.log()));
            VERTICAL_PLANKS.put(woodType, HELPER.createFuelBlock("vertical_" + materialName + "_planks", () -> new Block(properties.planks()), burnTime));
            VERTICAL_SLABS.put(woodType, HELPER.createWoodVerticalSlabBlock(materialName, properties.planks()));
            Block fence = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(woodType.name() + "_fence"));
            POSTS.put(woodType, HELPER.createWoodPostBlock("", materialName, fence, properties.sound(), burnTime));
            STRIPPED_POSTS.put(woodType, HELPER.createWoodPostBlock("stripped_", materialName, fence, properties.sound(), burnTime));
            HEDGES.put(woodType, HELPER.createHedgeBlock(materialName + "_hedge", CompatOPlenty.bopBlock(materialName + "_fence"), CompatOPlenty.bopBlock(materialName + "_leaves"), burnTime));
            LEAF_CARPETS.put(woodType, HELPER.createLeafCarpetBlock(materialName));
        }
    }

    // Sandstone Blocks
    public static final Properties WHITE_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F);
    public static RegistryObject<Block> WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("white_sandstone_vertical_slab",
            () -> BOPBlocks.WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> CUT_WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_white_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_white_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static RegistryObject<Block> WHITE_SANDSTONE_BRICKS = HELPER.createBlock("white_sandstone_bricks",
            () -> new Block(WHITE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> WHITE_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("white_sandstone_brick_stairs",
            () -> new StairBlock(WHITE_SANDSTONE_BRICKS.get().defaultBlockState(), WHITE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> WHITE_SANDSTONE_BRICK_SLAB = HELPER.createBlock("white_sandstone_brick_slab",
            () -> new SlabBlock(WHITE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> WHITE_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("white_sandstone_brick_vertical_slab",
            WHITE_SANDSTONE_BRICK_SLAB, WHITE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> WHITE_SANDSTONE_BRICK_WALL = HELPER.createBlock("white_sandstone_brick_wall",
            () -> new WallBlock(WHITE_SANDSTONE_PROPERTIES));

    public static final Properties ORANGE_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(0.8F);
    public static RegistryObject<Block> ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("orange_sandstone_vertical_slab",
            () -> BOPBlocks.ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> CUT_ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_orange_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_orange_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static RegistryObject<Block> ORANGE_SANDSTONE_BRICKS = HELPER.createBlock("orange_sandstone_bricks",
            () -> new Block(ORANGE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> ORANGE_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("orange_sandstone_brick_stairs",
            () -> new StairBlock(ORANGE_SANDSTONE_BRICKS.get().defaultBlockState(), ORANGE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> ORANGE_SANDSTONE_BRICK_SLAB = HELPER.createBlock("orange_sandstone_brick_slab",
            () -> new SlabBlock(ORANGE_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("orange_sandstone_brick_vertical_slab",
            ORANGE_SANDSTONE_BRICK_SLAB, ORANGE_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> ORANGE_SANDSTONE_BRICK_WALL = HELPER.createBlock("orange_sandstone_brick_wall",
            () -> new WallBlock(ORANGE_SANDSTONE_PROPERTIES));

    public static final Properties BLACK_SANDSTONE_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F);
    public static RegistryObject<Block> BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("black_sandstone_vertical_slab",
            () -> BOPBlocks.BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> CUT_BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("cut_black_sandstone_vertical_slab",
            () -> BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("smooth_black_sandstone_vertical_slab",
            () -> BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
    public static RegistryObject<Block> BLACK_SANDSTONE_BRICKS = HELPER.createBlock("black_sandstone_bricks",
            () -> new Block(BLACK_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> BLACK_SANDSTONE_BRICK_STAIRS = HELPER.createBlock("black_sandstone_brick_stairs",
            () -> new StairBlock(BLACK_SANDSTONE_BRICKS.get().defaultBlockState(), BLACK_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> BLACK_SANDSTONE_BRICK_SLAB = HELPER.createBlock("black_sandstone_brick_slab",
            () -> new SlabBlock(BLACK_SANDSTONE_PROPERTIES));
    public static RegistryObject<Block> BLACK_SANDSTONE_BRICK_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("black_sandstone_brick_vertical_slab",
            BLACK_SANDSTONE_BRICK_SLAB, BLACK_SANDSTONE_PROPERTIES);
    public static RegistryObject<Block> BLACK_SANDSTONE_BRICK_WALL = HELPER.createBlock("black_sandstone_brick_wall",
            () -> new WallBlock(BLACK_SANDSTONE_PROPERTIES));

    // Galanos Blocks
    public static final Properties GALANOS_PROPERTIES = Properties.of().mapColor(MapColor.DIAMOND).requiresCorrectToolForDrops().strength(1.5f, 6f).lightLevel(state -> 6).sound(SoundType.STONE);
    public static RegistryObject<Block> GALANOS_BLOCK = HELPER.createBlock("galanos_block",
            () -> new Block(GALANOS_PROPERTIES));
    public static RegistryObject<Block> GALANOS_PILLAR = HELPER.createBlock("galanos_pillar",
            () -> new RotatedPillarBlock(GALANOS_PROPERTIES));
    public static RegistryObject<Block> GALANOS_STAIRS = HELPER.createBlock("galanos_stairs",
            () -> new StairBlock(() -> GALANOS_BLOCK.get().defaultBlockState(), GALANOS_PROPERTIES));
    public static RegistryObject<Block> GALANOS_SLAB = HELPER.createBlock("galanos_slab",
            () -> new SlabBlock(GALANOS_PROPERTIES));
    public static RegistryObject<Block> GALANOS_VERTICAL_SLAB = HELPER.createVerticalSlabBlock("galanos_vertical_slab",
            GALANOS_SLAB, GALANOS_PROPERTIES);

    // Polished Rose Quartz Blocks
    public static final Properties POLISHED_ROSE_QUARTZ_PROPERTIES = Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F);
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ = HELPER.createBlock("polished_rose_quartz",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> CHISELED_POLISHED_ROSE_QUARTZ = HELPER.createBlock("chiseled_polished_rose_quartz",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_STAIRS = HELPER.createBlock("polished_rose_quartz_stairs",
            () -> new RoseQuartzStairBlock(POLISHED_ROSE_QUARTZ.get().defaultBlockState(), POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_SLAB = HELPER.createBlock("polished_rose_quartz_slab",
            () -> new RoseQuartzSlabBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_VERTICAL_SLAB = HELPER.createBlock("polished_rose_quartz_vertical_slab",
            ModList.get().isLoaded(CompatOPlenty.QUARK_ID)
                    ? COPQuark.roseQuartzVerticalSlab(POLISHED_ROSE_QUARTZ_SLAB, POLISHED_ROSE_QUARTZ_PROPERTIES)
                    : () -> new Block(POLISHED_ROSE_QUARTZ_PROPERTIES));

    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICKS = HELPER.createBlock("polished_rose_quartz_bricks",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> CRACKED_POLISHED_ROSE_QUARTZ_BRICKS = HELPER.createBlock("cracked_polished_rose_quartz_bricks",
            () -> new AmethystBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_STAIRS = HELPER.createBlock("polished_rose_quartz_brick_stairs",
            () -> new RoseQuartzStairBlock(POLISHED_ROSE_QUARTZ_BRICKS.get().defaultBlockState(), POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_SLAB = HELPER.createBlock("polished_rose_quartz_brick_slab",
            () -> new RoseQuartzSlabBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB = HELPER.createBlock("polished_rose_quartz_brick_vertical_slab",
            ModList.get().isLoaded(CompatOPlenty.QUARK_ID)
                    ? COPQuark.roseQuartzVerticalSlab(POLISHED_ROSE_QUARTZ_SLAB, POLISHED_ROSE_QUARTZ_PROPERTIES)
                    : () -> new Block(POLISHED_ROSE_QUARTZ_PROPERTIES));
    public static RegistryObject<Block> POLISHED_ROSE_QUARTZ_BRICK_WALL = HELPER.createBlock("polished_rose_quartz_brick_wall",
            () -> new RoseQuartzWallBlock(POLISHED_ROSE_QUARTZ_PROPERTIES));

    // Misc
    public static RegistryObject<Block> ORIGIN_LEAF_PILE = HELPER.createBlock("origin_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> ORIGIN_LEAF_CARPET = HELPER.createLeafCarpetBlock("origin_leaf_carpet",
            () -> BOPBlocks.ORIGIN_LEAVES);
    public static RegistryObject<Block> ORIGIN_HEDGE = HELPER.createHedgeBlock("origin_hedge",
            () -> Blocks.OAK_FENCE, () -> BOPBlocks.ORIGIN_LEAVES, 300);

    public static RegistryObject<Block> FLOWERING_OAK_LEAF_PILE = HELPER.createBlock("flowering_oak_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> FLOWERING_OAK_LEAF_CARPET = HELPER.createLeafCarpetBlock("flowering_oak_leaf_carpet",
            () -> BOPBlocks.FLOWERING_OAK_LEAVES);
    public static RegistryObject<Block> FLOWERING_OAK_HEDGE = HELPER.createHedgeBlock("flowering_oak_hedge",
            () -> Blocks.OAK_FENCE, () -> BOPBlocks.FLOWERING_OAK_LEAVES, 300);

    public static RegistryObject<Block> RAINBOW_BIRCH_LEAF_PILE = HELPER.createBlock("rainbow_birch_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> RAINBOW_BIRCH_LEAF_CARPET = HELPER.createLeafCarpetBlock("rainbow_birch_leaf_carpet",
            () -> BOPBlocks.RAINBOW_BIRCH_LEAVES);
    public static RegistryObject<Block> RAINBOW_BIRCH_HEDGE = HELPER.createHedgeBlock("rainbow_birch_hedge",
            () -> Blocks.BIRCH_FENCE, () -> BOPBlocks.RAINBOW_BIRCH_LEAVES, 300);

    public static RegistryObject<Block> CYPRESS_LEAF_PILE = HELPER.createBlock("cypress_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> CYPRESS_LEAF_CARPET = HELPER.createLeafCarpetBlock("cypress_leaf_carpet",
            () -> BOPBlocks.CYPRESS_LEAVES);
    public static RegistryObject<Block> CYPRESS_HEDGE = HELPER.createHedgeBlock("cypress_hedge",
            () -> Blocks.SPRUCE_FENCE, () -> BOPBlocks.CYPRESS_LEAVES, 300);

    public static RegistryObject<Block> SNOWBLOSSOM_LEAF_PILE = HELPER.createBlock("snowblossom_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> SNOWBLOSSOM_LEAF_CARPET = HELPER.createLeafCarpetBlock("snowblossom_leaf_carpet",
            () -> BOPBlocks.SNOWBLOSSOM_LEAVES);
    public static RegistryObject<Block> SNOWBLOSSOM_HEDGE = HELPER.createHedgeBlock("snowblossom_hedge",
            () -> Blocks.CHERRY_FENCE, () -> BOPBlocks.SNOWBLOSSOM_LEAVES, 300);

    public static RegistryObject<Block> RED_MAPLE_LEAF_PILE = HELPER.createBlock("red_maple_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> RED_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("red_maple_leaf_carpet",
            () -> BOPBlocks.RED_MAPLE_LEAVES);
    public static RegistryObject<Block> RED_MAPLE_HEDGE = HELPER.createHedgeBlock("red_maple_hedge",
            () -> BOPBlocks.MAPLE_FENCE, () -> BOPBlocks.RED_MAPLE_LEAVES, 300);

    public static RegistryObject<Block> ORANGE_MAPLE_LEAF_PILE = HELPER.createBlock("orange_maple_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> ORANGE_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("orange_maple_leaf_carpet",
            () -> BOPBlocks.ORANGE_MAPLE_LEAVES);
    public static RegistryObject<Block> ORANGE_MAPLE_HEDGE = HELPER.createHedgeBlock("orange_maple_hedge",
            () -> BOPBlocks.MAPLE_FENCE, () -> BOPBlocks.ORANGE_MAPLE_LEAVES, 300);

    public static RegistryObject<Block> YELLOW_MAPLE_LEAF_PILE = HELPER.createBlock("yellow_maple_leaf_pile",
            () -> new LeafPileBlock(WOOD_PROPERTIES.get(BOPWoodTypes.WILLOW).leafPile()));
    public static RegistryObject<Block> YELLOW_MAPLE_LEAF_CARPET = HELPER.createLeafCarpetBlock("yellow_maple_leaf_carpet",
            () -> BOPBlocks.YELLOW_MAPLE_LEAVES);
    public static RegistryObject<Block> YELLOW_MAPLE_HEDGE = HELPER.createHedgeBlock("yellow_maple_hedge",
            () -> BOPBlocks.MAPLE_FENCE, () -> BOPBlocks.YELLOW_MAPLE_LEAVES, 300);
}
