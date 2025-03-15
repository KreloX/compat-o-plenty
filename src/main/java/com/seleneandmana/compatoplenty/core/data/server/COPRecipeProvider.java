package com.seleneandmana.compatoplenty.core.data.server;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.COPItems;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.woodworks.core.WoodworksConfig;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.config.FlagCondition;
import org.violetmoon.zetaimplforge.registry.ForgeCraftingExtensionsRegistry.Zeta2ForgeCondition;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.seleneandmana.compatoplenty.core.registry.COPBlocks.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class COPRecipeProvider extends RecipeProvider {
    public static final ResourceLocation QUARK_FLAG = new ResourceLocation("quark", "flag");
    public static final String VERTICAL_SLABS_FLAG = "vertical_slabs";
    public static final String SANDSTONE_BRICKS_FLAG = "sandstone_bricks";
    public static final String MIDORI_FLAG = "midori";
    public static final String VARIANT_CHESTS_FLAG = "variant_chests";
    public static final String WOOD_TO_CHEST_RECIPES_FLAG = "wood_to_chest_recipes";
    public static final String VERTICAL_PLANKS_FLAG = "vertical_planks";
    public static final String VARIANT_BOOKSHELVES_FLAG = "variant_bookshelves";
    public static final String VARIANT_LADDERS_FLAG = "variant_ladders";
    public static final String WOODEN_POSTS_FLAG = "wooden_posts";
    public static final String HEDGES_FLAG = "hedges";
    public static final String LEAF_CARPET_FLAG = "leaf_carpet";

    public COPRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            Function<String, Item> getItem = name -> ForgeRegistries.ITEMS.getValue(CompatOPlenty.bopLoc(name));
            String woodName = woodMaterial.getName();
            Item slab = getItem.apply(woodName + "_slab");
            Item planks = getItem.apply(woodName + "_planks");
            Item leaves = getItem.apply(woodName + "_leaves");
            verticalSlabRecipe(slab, VERTICAL_SLABS.get(woodMaterial).get(), consumer);
            bookshelfRecipe(planks, BOOKSHELVES.get(woodMaterial).get(), consumer);
            ladderRecipe(getItem.apply(woodName + "_ladder"), LADDERS.get(woodMaterial).get(), consumer);
            postRecipe(getItem.apply(woodName + "_wood"), POSTS.get(woodMaterial).get(), consumer);
            postRecipe(getItem.apply("stripped_" + woodName + "_wood"), STRIPPED_POSTS.get(woodMaterial).get(), consumer);
            hedgeRecipe(logTag(woodName), leaves, HEDGES.get(woodMaterial).get(), consumer);
            leafCarpetRecipe(leaves, LEAF_CARPETS.get(woodMaterial).get(), consumer);
            leafPileRecipe(leaves, LEAF_PILES.get(woodMaterial).get(), consumer);
            chestRecipes(planks, logTag(woodName), CHESTS.get(woodMaterial), TRAPPED_CHESTS.get(woodMaterial), consumer);
            beehiveRecipe(planks, BEEHIVES.get(woodMaterial).get(), consumer);
            verticalPlankRecipe(planks, VERTICAL_PLANKS.get(woodMaterial).get(), consumer);
            cabinetRecipe(slab, getItem.apply(woodName + "_trapdoor"), CABINETS.get(woodMaterial).get(), consumer);
            tableRecipe(slab, getItem.apply(woodName + "_fence"), TABLES.get(woodMaterial).get(), consumer);
            boardsRecipe(planks, BOARDS.get(woodMaterial).get(), consumer);

            furnaceBoatRecipe(getItem.apply(woodName + "_boat"), COPItems.FURNACE_BOATS.get(woodMaterial).get(), consumer);
            largeBoatRecipe(getItem.apply(woodName + "_boat"), planks, COPItems.LARGE_BOATS.get(woodMaterial).get(), consumer);
        }

        //Vertical Slabs
        verticalSlabRecipe(BOPBlocks.BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BLACK_SANDSTONE_BRICK_SLAB.get(), BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(), consumer);

        verticalSlabRecipe(BOPBlocks.ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(ORANGE_SANDSTONE_BRICK_SLAB.get(), ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), consumer);

        verticalSlabRecipe(BOPBlocks.WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(WHITE_SANDSTONE_BRICK_SLAB.get(), WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), consumer);

        verticalSlabRecipe(POLISHED_ROSE_QUARTZ_SLAB.get(), POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(), consumer);
        verticalSlabRecipe(POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), consumer);

        verticalSlabRecipe(GALANOS_SLAB.get(), GALANOS_VERTICAL_SLAB.get(), consumer);

        //Hedges
        hedgeRecipe(ItemTags.OAK_LOGS, BOPBlocks.FLOWERING_OAK_LEAVES, FLOWERING_OAK_HEDGE.get(), consumer);
        hedgeRecipe(ItemTags.BIRCH_LOGS, BOPBlocks.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_HEDGE.get(), consumer);
        hedgeRecipe(ItemTags.OAK_LOGS, BOPBlocks.ORIGIN_LEAVES, ORIGIN_HEDGE.get(), consumer);
        hedgeRecipe(ItemTags.OAK_LOGS, BOPBlocks.RED_MAPLE_LEAVES, RED_MAPLE_HEDGE.get(), consumer);
        hedgeRecipe(ItemTags.DARK_OAK_LOGS, BOPBlocks.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_HEDGE.get(), consumer);
        hedgeRecipe(ItemTags.BIRCH_LOGS, BOPBlocks.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_HEDGE.get(), consumer);

        //Leaf Carpets
        leafCarpetRecipe(BOPBlocks.FLOWERING_OAK_LEAVES, FLOWERING_OAK_LEAF_CARPET.get(), consumer);
        leafCarpetRecipe(BOPBlocks.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_LEAF_CARPET.get(), consumer);
        leafCarpetRecipe(BOPBlocks.ORIGIN_LEAVES, ORIGIN_LEAF_CARPET.get(), consumer);
        leafCarpetRecipe(BOPBlocks.RED_MAPLE_LEAVES, RED_MAPLE_LEAF_CARPET.get(), consumer);
        leafCarpetRecipe(BOPBlocks.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_LEAF_CARPET.get(), consumer);
        leafCarpetRecipe(BOPBlocks.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_LEAF_CARPET.get(), consumer);

        //Leaf Piles
        leafPileRecipe(BOPBlocks.FLOWERING_OAK_LEAVES, FLOWERING_OAK_LEAF_PILE.get(), consumer);
        leafPileRecipe(BOPBlocks.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_LEAF_PILE.get(), consumer);
        leafPileRecipe(BOPBlocks.ORIGIN_LEAVES, ORIGIN_LEAF_PILE.get(), consumer);
        leafPileRecipe(BOPBlocks.RED_MAPLE_LEAVES, RED_MAPLE_LEAF_PILE.get(), consumer);
        leafPileRecipe(BOPBlocks.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_LEAF_PILE.get(), consumer);
        leafPileRecipe(BOPBlocks.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_LEAF_PILE.get(), consumer);

        //Stairs
        sandstoneStairsRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_STAIRS.get(), consumer);
        sandstoneStairsRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_STAIRS.get(), consumer);
        sandstoneStairsRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_STAIRS.get(), consumer);
        galanosStairsRecipe(GALANOS_BLOCK.get(), GALANOS_STAIRS.get(), consumer);
        polishedRoseStairsRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_STAIRS.get(), consumer);
        polishedRoseStairsRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(), consumer);

        //Slabs
        sandstoneSlabRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_SLAB.get(), consumer);
        sandstoneSlabRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_SLAB.get(), consumer);
        sandstoneSlabRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_SLAB.get(), consumer);
        galanosSlabRecipe(GALANOS_BLOCK.get(), GALANOS_SLAB.get(), consumer);
        polishedRoseSlabRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_SLAB.get(), consumer);
        polishedRoseSlabRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), consumer);

        //Walls
        sandstoneWallRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_WALL.get(), consumer);
        sandstoneWallRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_WALL.get(), consumer);
        sandstoneWallRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_WALL.get(), consumer);
        polishedRoseWallRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_WALL.get(), consumer);

        //2x2
        polishedRoseRecipe(consumer);
        galanosRecipe(consumer);

        //Chiseled
        chiseledBlockRecipe(POLISHED_ROSE_QUARTZ_SLAB.get(), CHISELED_POLISHED_ROSE_QUARTZ.get(), CompatOPlenty.TWIGS_ID, consumer);

        /*
        Stonecutting
        */

        //Sandstone
        quarkFlagStoneCutterRecipe(BOPBlocks.BLACK_SANDSTONE, BLACK_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.SMOOTH_BLACK_SANDSTONE, SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_BLACK_SANDSTONE, CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.BLACK_SANDSTONE, BLACK_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_BLACK_SANDSTONE, BLACK_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_STAIRS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BLACK_SANDSTONE_BRICKS.get(), BLACK_SANDSTONE_BRICK_WALL.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.ORANGE_SANDSTONE, ORANGE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.SMOOTH_ORANGE_SANDSTONE, SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_ORANGE_SANDSTONE, CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.ORANGE_SANDSTONE, ORANGE_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_ORANGE_SANDSTONE, ORANGE_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_STAIRS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(ORANGE_SANDSTONE_BRICKS.get(), ORANGE_SANDSTONE_BRICK_WALL.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.WHITE_SANDSTONE, WHITE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.SMOOTH_WHITE_SANDSTONE, SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_WHITE_SANDSTONE, CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.WHITE_SANDSTONE, WHITE_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(BOPBlocks.CUT_WHITE_SANDSTONE, WHITE_SANDSTONE_BRICKS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_STAIRS.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), 2, SANDSTONE_BRICKS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(WHITE_SANDSTONE_BRICKS.get(), WHITE_SANDSTONE_BRICK_WALL.get(), 1, SANDSTONE_BRICKS_FLAG, consumer);

        //Galanos
        quarkFlagStoneCutterRecipe(GALANOS_BLOCK.get(), GALANOS_STAIRS.get(), 1, MIDORI_FLAG, consumer);
        quarkFlagStoneCutterRecipe(GALANOS_BLOCK.get(), GALANOS_SLAB.get(), 2, MIDORI_FLAG, consumer);
        quarkFlagStoneCutterRecipe(GALANOS_BLOCK.get(), GALANOS_VERTICAL_SLAB.get(), 2, VERTICAL_SLABS_FLAG, consumer);
        quarkFlagStoneCutterRecipe(GALANOS_BLOCK.get(), GALANOS_PILLAR.get(), 1, MIDORI_FLAG, consumer);

        //Polished Rose Quartz
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_STAIRS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, CHISELED_POLISHED_ROSE_QUARTZ.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICKS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_WALL.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_STAIRS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), CHISELED_POLISHED_ROSE_QUARTZ.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_BRICKS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_BRICK_WALL.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, consumer);
        modLoadedStoneCutterRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_WALL.get(), 1, CompatOPlenty.TWIGS_ID, consumer);
        multiModStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, VERTICAL_SLABS_FLAG, consumer);
        multiModStoneCutterRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, VERTICAL_SLABS_FLAG, consumer);
        multiModStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, VERTICAL_SLABS_FLAG, consumer);
        multiModStoneCutterRecipe(POLISHED_ROSE_QUARTZ.get(), POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, VERTICAL_SLABS_FLAG, consumer);
        multiModStoneCutterRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), 2, CompatOPlenty.TWIGS_ID, VERTICAL_SLABS_FLAG, consumer);

        /*
        Smelting
        */

        flaggedFurnaceRecipe(BOPBlocks.GLOWING_MOSS_BLOCK, COPItems.GLOWING_MOSS_PASTE.get(), 1.0f, MIDORI_FLAG, consumer);
        modLoadedFurnaceRecipe(POLISHED_ROSE_QUARTZ_BRICKS.get(), CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get(), 0.1f, CompatOPlenty.TWIGS_ID, consumer);

        /*
        Cutting Board
        */

        //Stripped Logs
        stripLogCuttingRecipe(BOPBlocks.JACARANDA_LOG, BOPBlocks.STRIPPED_JACARANDA_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.FIR_LOG, BOPBlocks.STRIPPED_FIR_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.REDWOOD_LOG, BOPBlocks.STRIPPED_REDWOOD_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.MAHOGANY_LOG, BOPBlocks.STRIPPED_MAHOGANY_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.WILLOW_LOG, BOPBlocks.STRIPPED_WILLOW_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.MAGIC_LOG, BOPBlocks.STRIPPED_MAGIC_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.DEAD_LOG, BOPBlocks.STRIPPED_DEAD_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.UMBRAN_LOG, BOPBlocks.STRIPPED_UMBRAN_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.PALM_LOG, BOPBlocks.STRIPPED_PALM_LOG, consumer);
        stripLogCuttingRecipe(BOPBlocks.HELLBARK_LOG, BOPBlocks.STRIPPED_HELLBARK_LOG, consumer);

        //Stripped Wood
        stripLogCuttingRecipe(BOPBlocks.JACARANDA_WOOD, BOPBlocks.STRIPPED_JACARANDA_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.FIR_WOOD, BOPBlocks.STRIPPED_FIR_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.REDWOOD_WOOD, BOPBlocks.STRIPPED_REDWOOD_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.MAHOGANY_WOOD, BOPBlocks.STRIPPED_MAHOGANY_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.WILLOW_WOOD, BOPBlocks.STRIPPED_WILLOW_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.MAGIC_WOOD, BOPBlocks.STRIPPED_MAGIC_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.DEAD_WOOD, BOPBlocks.STRIPPED_DEAD_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.UMBRAN_WOOD, BOPBlocks.STRIPPED_UMBRAN_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.PALM_WOOD, BOPBlocks.STRIPPED_PALM_WOOD, consumer);
        stripLogCuttingRecipe(BOPBlocks.HELLBARK_WOOD, BOPBlocks.STRIPPED_HELLBARK_WOOD, consumer);

        //Flowers
        dyeCuttingRecipe(BOPBlocks.BLUE_HYDRANGEA, Items.LIGHT_BLUE_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.BURNING_BLOSSOM, Items.ORANGE_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.GLOWFLOWER, Items.CYAN_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.GOLDENROD, Items.YELLOW_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.LAVENDER, Items.PURPLE_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.ORANGE_COSMOS, Items.ORANGE_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.PINK_DAFFODIL, Items.PINK_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.PINK_HIBISCUS, Items.PINK_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.ROSE, Items.RED_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.VIOLET, Items.PURPLE_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.WILDFLOWER, Items.MAGENTA_DYE, consumer);
        dyeCuttingRecipe(BOPBlocks.WILTED_LILY, Items.GRAY_DYE, consumer);

        //Furniture Salvage
        furnitureSalvageCuttingRecipes(BOPBlocks.JACARANDA_PLANKS, BOPBlocks.JACARANDA_DOOR, BOPBlocks.JACARANDA_TRAPDOOR, BOPBlocks.JACARANDA_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.FIR_PLANKS, BOPBlocks.FIR_DOOR, BOPBlocks.FIR_TRAPDOOR, BOPBlocks.FIR_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.REDWOOD_PLANKS, BOPBlocks.REDWOOD_DOOR, BOPBlocks.REDWOOD_TRAPDOOR, BOPBlocks.REDWOOD_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.MAHOGANY_PLANKS, BOPBlocks.MAHOGANY_DOOR, BOPBlocks.MAHOGANY_TRAPDOOR, BOPBlocks.MAHOGANY_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.WILLOW_PLANKS, BOPBlocks.WILLOW_DOOR, BOPBlocks.WILLOW_TRAPDOOR, BOPBlocks.WILLOW_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.MAGIC_PLANKS, BOPBlocks.MAGIC_DOOR, BOPBlocks.MAGIC_TRAPDOOR, BOPBlocks.MAGIC_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.DEAD_PLANKS, BOPBlocks.DEAD_DOOR, BOPBlocks.DEAD_TRAPDOOR, BOPBlocks.DEAD_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.UMBRAN_PLANKS, BOPBlocks.UMBRAN_DOOR, BOPBlocks.UMBRAN_TRAPDOOR, BOPBlocks.UMBRAN_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.PALM_PLANKS, BOPBlocks.PALM_DOOR, BOPBlocks.PALM_TRAPDOOR, BOPBlocks.PALM_SIGN, consumer);
        furnitureSalvageCuttingRecipes(BOPBlocks.HELLBARK_PLANKS, BOPBlocks.HELLBARK_DOOR, BOPBlocks.HELLBARK_TRAPDOOR, BOPBlocks.HELLBARK_SIGN, consumer);

        //Misc
        genericCuttingRecipe(BOPBlocks.ROSE_QUARTZ_BLOCK, new ToolActionIngredient(ToolActions.PICKAXE_DIG), BOPItems.ROSE_QUARTZ_CHUNK, 4, ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.AMETHYST_BLOCK_BREAK).toString(), consumer);
    }

    public static TagKey<Item> logTag(String woodType) {
        return TagUtil.itemTag("biomesoplenty", woodType + "_logs");
    }

    private static Zeta2ForgeCondition<FlagCondition> quarkCondition(String flag) {
        return new Zeta2ForgeCondition<>(new FlagCondition(Quark.ZETA.configManager.getConfigFlagManager(), flag, QUARK_FLAG, () -> false));
    }

    public static void verticalSlabRecipe(ItemLike slab, ItemLike verticalSlab, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VERTICAL_SLABS_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, verticalSlab, 3).define('S', slab).pattern("S").pattern("S").pattern("S").unlockedBy(getHasName(slab), has(slab)).save(consumer1, CompatOPlenty.modLoc(getItemName(verticalSlab))))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(verticalSlab)));

        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VERTICAL_SLABS_FLAG))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, slab).requires(verticalSlab).unlockedBy(getHasName(verticalSlab), has(verticalSlab)).save(consumer1, CompatOPlenty.modLoc(getItemName(verticalSlab) + "_revert")))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(verticalSlab) + "_revert"));
    }

    public static void verticalPlankRecipe(ItemLike plank, ItemLike verticalPlank, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VERTICAL_PLANKS_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, verticalPlank, 3).define('S', plank).pattern("S").pattern("S").pattern("S").unlockedBy(getHasName(plank), has(plank)).save(consumer1, CompatOPlenty.modLoc(getItemName(verticalPlank))))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(verticalPlank)));

        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VERTICAL_PLANKS_FLAG))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, plank).requires(verticalPlank).unlockedBy(getHasName(verticalPlank), has(verticalPlank)).save(consumer1, CompatOPlenty.modLoc(getItemName(verticalPlank) + "_revert")))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(verticalPlank) + "_revert"));
    }

    public static void boardsRecipe(ItemLike plank, ItemLike board, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenBoards, "wooden_boards", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, board, 3).define('S', plank).pattern("S").pattern("S").pattern("S").unlockedBy(getHasName(plank), has(plank)).save(consumer1, CompatOPlenty.modLoc(getItemName(board))))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(board)));
    }

    public static void bookshelfRecipe(ItemLike plank, ItemLike bookshelf, Consumer<FinishedRecipe> consumer) {
        //Woodworks
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenBookshelves, "wooden_bookshelves", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bookshelf).define('#', plank).define('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###").unlockedBy(getHasName(plank), has(plank)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(bookshelf) + "_woodworks"));

        //Quark
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VARIANT_BOOKSHELVES_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bookshelf).define('#', plank).define('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###").unlockedBy(getHasName(plank), has(plank)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(bookshelf) + "_quark"));
    }

    public static void ladderRecipe(ItemLike plank, ItemLike ladder, Consumer<FinishedRecipe> consumer) {
        //Woodworks
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition("woodworks"), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenLadders, "wooden_ladders", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', Items.STICK).define('P', plank).pattern("# #").pattern("#P#").pattern("# #").unlockedBy(getHasName(plank), has(plank)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(ladder) + "_woodworks"));

        //Quark
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VARIANT_LADDERS_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', Items.STICK).define('P', plank).pattern("# #").pattern("#P#").pattern("# #").unlockedBy(getHasName(plank), has(plank)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(ladder) + "_quark"));
    }

    public static void postRecipe(ItemLike wood, ItemLike post, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(WOODEN_POSTS_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, post, 8).define('#', wood).pattern("#").pattern("#").pattern("#").unlockedBy(getHasName(wood), has(wood)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(post)));
    }

    public static void hedgeRecipe(TagKey<Item> log, ItemLike leaves, ItemLike hedge, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(HEDGES_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, hedge, 2).define('#', log).define('L', leaves).pattern("L").pattern("#").unlockedBy(getHasName(leaves), has(leaves)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(hedge)));
    }

    public static void leafCarpetRecipe(ItemLike leaves, ItemLike carpet, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(LEAF_CARPET_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, carpet, 3).define('#', leaves).pattern("##").unlockedBy(getHasName(leaves), has(leaves)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(carpet)));
    }

    public static void leafPileRecipe(ItemLike leaves, ItemLike pile, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.leafPiles, "leaf_piles", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, pile, 4).requires(leaves).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(pile)));

        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.leafPiles, "leaf_piles", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves, 1).define('#', pile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(pile), has(pile)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(leaves) + "_from_leaf_piles"));
    }

    public static void chestRecipes(ItemLike planks, TagKey<Item> log, RegistryObject<BlueprintChestBlock> chest, RegistryObject<BlueprintTrappedChestBlock> trappedChest, Consumer<FinishedRecipe> consumer) {
        Block normal = chest.get();
        Block trapped = trappedChest.get();

        //Woodworks
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenChests, "wooden_chests", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, normal).define('#', planks).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(planks), has(planks)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(normal) + "_woodworks"));

        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenChests, "wooden_chests", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, trapped).requires(normal).requires(Items.TRIPWIRE_HOOK).unlockedBy(getHasName(normal), has(normal)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(trapped) + "_woodworks"));

        //Quark
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VARIANT_CHESTS_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, normal).define('#', planks).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(planks), has(planks)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(normal) + "_quark"));

        ConditionalRecipe.builder()
                .addCondition(new AndCondition(quarkCondition(WOOD_TO_CHEST_RECIPES_FLAG), quarkCondition(VARIANT_CHESTS_FLAG)))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, normal, 4).define('#', log).pattern("###").pattern("# #").pattern("###").unlockedBy(getHasName(normal), has(normal)).save(consumer1, CompatOPlenty.modLoc("crafting/" + getItemName(normal) + "_bulk")))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(normal) + "_quark_bulk"));

        ConditionalRecipe.builder()
                .addCondition(quarkCondition(VARIANT_CHESTS_FLAG))
                .addRecipe(consumer1 -> ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, trapped).requires(normal).requires(Items.TRIPWIRE_HOOK).unlockedBy(getHasName(normal), has(normal)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(trapped) + "_quark"));
    }

    public static void beehiveRecipe(ItemLike planks, ItemLike beehive, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new BlueprintAndCondition(List.of(new ModLoadedCondition(CompatOPlenty.WOODWORKS_ID), new ConfigValueCondition(new ResourceLocation(CompatOPlenty.WOODWORKS_ID, "config"), WoodworksConfig.COMMON.woodenBeehives, "wooden_beehives", new HashMap<>(), false))))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive).define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").unlockedBy(getHasName(planks), has(planks)).save(consumer1, new ResourceLocation(CompatOPlenty.MOD_ID)))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(beehive)));
    }

    public static void cabinetRecipe(ItemLike slab, ItemLike trapdoor, ItemLike cabinet, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.FARMERS_DELIGHT_ID))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, cabinet).define('#', slab).define('T', trapdoor).pattern("###").pattern("T T").pattern("###").unlockedBy(getHasName(slab), has(slab)).save(consumer1, new ResourceLocation(CompatOPlenty.MOD_ID)))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(cabinet)));
    }

    public static void furnaceBoatRecipe(ItemLike boat, ItemLike furnaceBoat, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.BOATLOAD_ID))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, furnaceBoat, 1).group("furnace_boat").define('F', Items.FURNACE).define('B', boat).pattern("F").pattern("B").unlockedBy(getHasName(boat), has(boat)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(furnaceBoat)));
    }

    public static void largeBoatRecipe(ItemLike boat, ItemLike planks, ItemLike largeBoat, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.BOATLOAD_ID))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, largeBoat, 1).group("large_boat").define('B', boat).define('P', planks).pattern("PBP").pattern("PPP").unlockedBy(getHasName(boat), has(boat)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(largeBoat)));
    }

    public static void sandstoneStairsRecipe(ItemLike material, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(SANDSTONE_BRICKS_FLAG))
                .addRecipe(consumer1 -> stairBuilder(stairs, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(stairs)));
    }

    public static void galanosStairsRecipe(ItemLike material, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(MIDORI_FLAG))
                .addRecipe(consumer1 -> stairBuilder(stairs, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(stairs)));

    }

    public static void polishedRoseStairsRecipe(ItemLike material, ItemLike stairs, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.TWIGS_ID))
                .addRecipe(consumer1 -> stairBuilder(stairs, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(stairs)));
    }

    public static void sandstoneSlabRecipe(ItemLike material, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(SANDSTONE_BRICKS_FLAG))
                .addRecipe(consumer1 -> slabBuilder(RecipeCategory.DECORATIONS, slab, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(slab)));
    }

    public static void galanosSlabRecipe(ItemLike material, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(MIDORI_FLAG))
                .addRecipe(consumer1 -> slabBuilder(RecipeCategory.DECORATIONS, slab, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(slab)));

    }

    public static void polishedRoseSlabRecipe(ItemLike material, ItemLike slab, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.TWIGS_ID))
                .addRecipe(consumer1 -> slabBuilder(RecipeCategory.DECORATIONS, slab, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(slab)));
    }

    public static void sandstoneWallRecipe(ItemLike material, ItemLike wall, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(SANDSTONE_BRICKS_FLAG))
                .addRecipe(consumer1 -> wallBuilder(RecipeCategory.DECORATIONS, wall, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(wall)));

    }

    public static void polishedRoseWallRecipe(ItemLike material, ItemLike wall, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.TWIGS_ID))
                .addRecipe(consumer1 -> wallBuilder(RecipeCategory.DECORATIONS, wall, of(material)).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(wall)));
    }

    public static void polishedRoseRecipe(Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.TWIGS_ID))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, POLISHED_ROSE_QUARTZ.get()).define('#', BOPBlocks.ROSE_QUARTZ_BLOCK).pattern("##").pattern("##").unlockedBy(getHasName(BOPBlocks.ROSE_QUARTZ_BLOCK), has(BOPBlocks.ROSE_QUARTZ_BLOCK)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(POLISHED_ROSE_QUARTZ.get())));
    }

    public static void galanosRecipe(Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(MIDORI_FLAG))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GALANOS_BLOCK.get()).define('#', COPItems.GLOWING_MOSS_PASTE.get()).pattern("##").pattern("##").unlockedBy(getHasName(COPItems.GLOWING_MOSS_PASTE.get()), has(COPItems.GLOWING_MOSS_PASTE.get())).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(GALANOS_BLOCK.get())));
    }

    public static void tableRecipe(ItemLike slab, ItemLike fence, ItemLike table, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.TWIGS_ID))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, table).define('#', slab).define('I', fence).pattern("###").pattern("I I").pattern("I I").unlockedBy(getHasName(slab), has(slab)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(table)));
    }

    public static void chiseledBlockRecipe(ItemLike slab, ItemLike result, String modId, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(modId))
                .addRecipe(consumer1 -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result).define('#', slab).pattern("#").pattern("#").unlockedBy(getHasName(slab), has(slab)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("crafting/" + getItemName(result)));
    }

    public static void quarkFlagStoneCutterRecipe(ItemLike material, ItemLike result, int amount, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(flag))
                .addRecipe(consumer1 -> SingleItemRecipeBuilder.stonecutting(of(material), RecipeCategory.DECORATIONS, result, amount).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("stonecutting/" + getConversionRecipeName(result, material)));
    }

    public static void modLoadedStoneCutterRecipe(ItemLike material, ItemLike result, int amount, String modId, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(modId))
                .addRecipe(consumer1 -> SingleItemRecipeBuilder.stonecutting(of(material), RecipeCategory.DECORATIONS, result, amount).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("stonecutting/" + getConversionRecipeName(result, material)));
    }

    public static void multiModStoneCutterRecipe(ItemLike material, ItemLike result, int amount, String modId, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(modId))
                .addCondition(quarkCondition(flag))
                .addRecipe(consumer1 -> SingleItemRecipeBuilder.stonecutting(of(material), RecipeCategory.DECORATIONS, result, amount).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("stonecutting/" + getConversionRecipeName(result, material)));
    }

    public static void flaggedFurnaceRecipe(ItemLike material, ItemLike result, float exp, String flag, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(quarkCondition(flag))
                .addRecipe(consumer1 -> SimpleCookingRecipeBuilder.smelting(of(material), RecipeCategory.DECORATIONS, result, exp, 200).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("smelting/" + getItemName(result)));
    }

    public static void modLoadedFurnaceRecipe(ItemLike material, ItemLike result, float exp, String modId, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(modId))
                .addRecipe(consumer1 -> SimpleCookingRecipeBuilder.smelting(of(material), RecipeCategory.DECORATIONS, result, exp, 200).unlockedBy(getHasName(material), has(material)).save(consumer1))
                .build(consumer, CompatOPlenty.modLoc("smelting/" + getItemName(result)));
    }

    public static void genericCuttingRecipe(ItemLike material, Ingredient tool, ItemLike result, int count, String soundId, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.FARMERS_DELIGHT_ID))
                .addRecipe(consumer1 -> CuttingBoardRecipeBuilder.cuttingRecipe(of(material), tool, result, count).addSound(soundId).build(consumer1))
                .build(consumer, CompatOPlenty.modLoc("cutting/" + getItemName(material)));
    }

    public static void stripLogCuttingRecipe(ItemLike log, ItemLike strippedLog, Consumer<FinishedRecipe> consumer) {
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(CompatOPlenty.FARMERS_DELIGHT_ID))
                .addRecipe(consumer1 -> CuttingBoardRecipeBuilder.cuttingRecipe(of(log), new ToolActionIngredient(ToolActions.AXE_STRIP), strippedLog).addResult(ModItems.TREE_BARK.get()).addSound(ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.AXE_STRIP).toString()).build(consumer1))
                .build(consumer, CompatOPlenty.modLoc("cutting/" + getItemName(log)));
    }

    public static void dyeCuttingRecipe(ItemLike flower, ItemLike dye, Consumer<FinishedRecipe> consumer) {
        genericCuttingRecipe(flower, of(ForgeTags.TOOLS_KNIVES), dye, 2, ForgeRegistries.SOUND_EVENTS.getKey(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get()).toString(), consumer);
    }

    public static void furnitureSalvageCuttingRecipes(ItemLike plank, ItemLike door, ItemLike trapdoor, ItemLike sign, Consumer<FinishedRecipe> consumer) {
        genericCuttingRecipe(door, new ToolActionIngredient(ToolActions.AXE_DIG), plank, 1, ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.WOOD_BREAK).toString(), consumer);
        genericCuttingRecipe(sign, new ToolActionIngredient(ToolActions.AXE_DIG), plank, 1, ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.WOOD_BREAK).toString(), consumer);
        genericCuttingRecipe(trapdoor, new ToolActionIngredient(ToolActions.AXE_DIG), plank, 1, ForgeRegistries.SOUND_EVENTS.getKey(SoundEvents.WOOD_BREAK).toString(), consumer);
    }
}
