package com.seleneandmana.compatoplenty.core;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.api.item.BOPItems;
import com.seleneandmana.compatoplenty.core.data.client.COPBlockStateProvider;
import com.seleneandmana.compatoplenty.core.data.client.COPItemModelProvider;
import com.seleneandmana.compatoplenty.core.data.client.COPLanguageProvider;
import com.seleneandmana.compatoplenty.core.data.server.COPLootTableProvider;
import com.seleneandmana.compatoplenty.core.data.server.COPRecipeProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.COPBlockTagsProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.COPItemTagsProvider;
import com.seleneandmana.compatoplenty.core.other.COPClientCompat;
import com.seleneandmana.compatoplenty.core.other.COPCompat;
import com.seleneandmana.compatoplenty.core.registry.COPItems;
import com.seleneandmana.compatoplenty.core.registry.helper.COPBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.seleneandmana.compatoplenty.core.registry.COPBlocks.*;

@Mod(CompatOPlenty.MOD_ID)
public class CompatOPlenty {
    public static final String MOD_ID = "compatoplenty";
    public static final String QUARK_ID = "quark";
    public static final String WOODWORKS_ID = "woodworks";
    public static final String TWIGS_ID = "twigs";
    public static final String FARMERS_DELIGHT_ID = "farmersdelight";
    public static final String BOATLOAD_ID = "boatload";

    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper ->
            helper.putSubHelper(ForgeRegistries.BLOCKS, new COPBlockSubRegistryHelper(helper)));

    public CompatOPlenty() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(bus);

        bus.addListener(this::setupTabEditors);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        if (ModList.get().isLoaded("everycomp")) {
            EveryCompat.addOtherCompatMod(MOD_ID, BOPAPI.MOD_ID,
                    WOODWORKS_ID, BOATLOAD_ID, FARMERS_DELIGHT_ID, TWIGS_ID, QUARK_ID);
        }
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation bopLoc(String path) {
        return new ResourceLocation(BOPAPI.MOD_ID, path);
    }

    public static Supplier<Item> bopItem(String path) {
        return () -> ForgeRegistries.ITEMS.getValue(bopLoc(path));
    }

    public static Supplier<Block> bopBlock(String path) {
        return () -> ForgeRegistries.BLOCKS.getValue(bopLoc(path));
    }


    // BOP doesn't expose its creative tab key, so we have to do it using the event
    private void setupTabEditors(BuildCreativeModeTabContentsEvent event) {
        var entries = event.getEntries();
        BiConsumer<Item, Supplier<? extends ItemLike>> after = (item, toPut) -> entries.putAfter(item.getDefaultInstance(),
                toPut.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        BiConsumer<Item, Supplier<? extends ItemLike>> before = (item, toPut) -> entries.putBefore(item.getDefaultInstance(),
                toPut.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        if (!event.getTabKey().location().equals(bopLoc("main"))) return;

        for (var woodType : WOOD_PROPERTIES.keySet()) {
            String materialName = woodType.name().replace(BOPAPI.MOD_ID + ":", "");
            Item chestBoat = bopItem(materialName + "_chest_boat").get();
            Item leaves = bopItem(materialName + "_leaves").get();
            Item planks = bopItem(materialName + "_planks").get();
            Item sign = bopItem(materialName + "_sign").get();
            Item hangingSign = bopItem(materialName + "_hanging_sign").get();
            Item log = bopItem(materialName + "_log").get();
            Item slab = bopItem(materialName + "_slab").get();

            if (ModList.get().isLoaded(BOATLOAD_ID)) {
                after.accept(chestBoat, COPItems.LARGE_BOATS.get(woodType));
                after.accept(chestBoat, COPItems.FURNACE_BOATS.get(woodType));
            }
            if (ModList.get().isLoaded(WOODWORKS_ID) || ModList.get().isLoaded(QUARK_ID)) {
                before.accept(sign, LADDERS.get(woodType));
                if (ModList.get().isLoaded(WOODWORKS_ID)) {
                    after.accept(leaves, LEAF_PILES.get(woodType));
                    after.accept(planks, BOARDS.get(woodType));
                    before.accept(sign, BEEHIVES.get(woodType));
                }
                before.accept(sign, BOOKSHELVES.get(woodType));
                after.accept(hangingSign, TRAPPED_CHESTS.get(woodType));
                after.accept(hangingSign, CHESTS.get(woodType));
                if (ModList.get().isLoaded(QUARK_ID)) {
                    before.accept(log, HOLLOW_LOGS.get(woodType));
                    after.accept(leaves, HEDGES.get(woodType));
                    after.accept(planks, VERTICAL_PLANKS.get(woodType));
                    after.accept(slab, STRIPPED_POSTS.get(woodType));
                    after.accept(slab, POSTS.get(woodType));
                    after.accept(slab, VERTICAL_SLABS.get(woodType));
                }
            }
            if (ModList.get().isLoaded(FARMERS_DELIGHT_ID)) {
                before.accept(sign, CABINETS.get(woodType));
            }
            if (ModList.get().isLoaded(TWIGS_ID)) {
                before.accept(sign, TABLES.get(woodType));
            }
        }

        if (ModList.get().isLoaded(WOODWORKS_ID)) {
            after.accept(BOPItems.ORIGIN_LEAVES, ORIGIN_LEAF_PILE);
            after.accept(BOPItems.FLOWERING_OAK_LEAVES, FLOWERING_OAK_LEAF_PILE);
            after.accept(BOPItems.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_LEAF_PILE);
            after.accept(BOPItems.RED_MAPLE_LEAVES, RED_MAPLE_LEAF_PILE);
            after.accept(BOPItems.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_LEAF_PILE);
            after.accept(BOPItems.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_LEAF_PILE);
        }
        if (ModList.get().isLoaded(QUARK_ID)) {
            after.accept(BOPItems.WHITE_SANDSTONE_SLAB, WHITE_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.SMOOTH_WHITE_SANDSTONE_SLAB, SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.CUT_WHITE_SANDSTONE_SLAB, CUT_WHITE_SANDSTONE_VERTICAL_SLAB);

            after.accept(BOPItems.ORANGE_SANDSTONE_SLAB, ORANGE_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.SMOOTH_ORANGE_SANDSTONE_SLAB, SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.CUT_ORANGE_SANDSTONE_SLAB, CUT_ORANGE_SANDSTONE_VERTICAL_SLAB);

            after.accept(BOPItems.BLACK_SANDSTONE_SLAB, BLACK_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.SMOOTH_BLACK_SANDSTONE_SLAB, SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB);
            after.accept(BOPItems.CUT_BLACK_SANDSTONE_SLAB, CUT_BLACK_SANDSTONE_VERTICAL_SLAB);

            after.accept(BOPItems.WHITE_SANDSTONE_WALL, WHITE_SANDSTONE_BRICK_WALL);
            after.accept(BOPItems.WHITE_SANDSTONE_WALL, WHITE_SANDSTONE_BRICK_VERTICAL_SLAB);
            after.accept(BOPItems.WHITE_SANDSTONE_WALL, WHITE_SANDSTONE_BRICK_SLAB);
            after.accept(BOPItems.WHITE_SANDSTONE_WALL, WHITE_SANDSTONE_BRICK_STAIRS);
            after.accept(BOPItems.WHITE_SANDSTONE_WALL, WHITE_SANDSTONE_BRICKS);

            after.accept(BOPItems.ORANGE_SANDSTONE_WALL, ORANGE_SANDSTONE_BRICK_WALL);
            after.accept(BOPItems.ORANGE_SANDSTONE_WALL, ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB);
            after.accept(BOPItems.ORANGE_SANDSTONE_WALL, ORANGE_SANDSTONE_BRICK_SLAB);
            after.accept(BOPItems.ORANGE_SANDSTONE_WALL, ORANGE_SANDSTONE_BRICK_STAIRS);
            after.accept(BOPItems.ORANGE_SANDSTONE_WALL, ORANGE_SANDSTONE_BRICKS);

            after.accept(BOPItems.BLACK_SANDSTONE_WALL, BLACK_SANDSTONE_BRICK_WALL);
            after.accept(BOPItems.BLACK_SANDSTONE_WALL, BLACK_SANDSTONE_BRICK_VERTICAL_SLAB);
            after.accept(BOPItems.BLACK_SANDSTONE_WALL, BLACK_SANDSTONE_BRICK_SLAB);
            after.accept(BOPItems.BLACK_SANDSTONE_WALL, BLACK_SANDSTONE_BRICK_STAIRS);
            after.accept(BOPItems.BLACK_SANDSTONE_WALL, BLACK_SANDSTONE_BRICKS);

            after.accept(BOPItems.GLOWING_MOSS_CARPET, COPItems.GLOWING_MOSS_PASTE);
            after.accept(BOPItems.GLOWING_MOSS_CARPET, GALANOS_VERTICAL_SLAB);
            after.accept(BOPItems.GLOWING_MOSS_CARPET, GALANOS_SLAB);
            after.accept(BOPItems.GLOWING_MOSS_CARPET, GALANOS_STAIRS);
            after.accept(BOPItems.GLOWING_MOSS_CARPET, GALANOS_PILLAR);
            after.accept(BOPItems.GLOWING_MOSS_CARPET, GALANOS_BLOCK);

            after.accept(BOPItems.ORIGIN_LEAVES, ORIGIN_HEDGE);
            after.accept(BOPItems.FLOWERING_OAK_LEAVES, FLOWERING_OAK_HEDGE);
            after.accept(BOPItems.RAINBOW_BIRCH_LEAVES, RAINBOW_BIRCH_HEDGE);
            after.accept(BOPItems.RED_MAPLE_LEAVES, RED_MAPLE_HEDGE);
            after.accept(BOPItems.ORANGE_MAPLE_LEAVES, ORANGE_MAPLE_HEDGE);
            after.accept(BOPItems.YELLOW_MAPLE_LEAVES, YELLOW_MAPLE_HEDGE);
        }
        if (ModList.get().isLoaded(TWIGS_ID)) {
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, CHISELED_POLISHED_ROSE_QUARTZ);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_STAIRS);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_SLAB);
            if (ModList.get().isLoaded(QUARK_ID))
                before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_VERTICAL_SLAB);

            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICKS);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, CRACKED_POLISHED_ROSE_QUARTZ_BRICKS);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_STAIRS);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_SLAB);
            if (ModList.get().isLoaded(QUARK_ID))
                before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB);
            before.accept(BOPItems.ROSE_QUARTZ_BLOCK, POLISHED_ROSE_QUARTZ_BRICK_WALL);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(COPCompat::registerCompat);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(COPClientCompat::registerClientCompat);
    }

    private void dataSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        var fileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();
        var packOutput = generator.getPackOutput();

        Consumer<DataProvider> client = provider -> generator.addProvider(event.includeClient(), provider);
        Consumer<DataProvider> server = provider -> generator.addProvider(event.includeServer(), provider);

        client.accept(new COPLanguageProvider(packOutput));
        client.accept(new COPBlockStateProvider(packOutput, fileHelper));
        client.accept(new COPItemModelProvider(packOutput, fileHelper));

        var blockTags = new COPBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        server.accept(blockTags);
        server.accept(new COPItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        server.accept(new COPLootTableProvider(packOutput));
        server.accept(new COPRecipeProvider(packOutput));
    }
}
