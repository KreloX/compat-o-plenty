package com.seleneandmana.compatoplenty.core;

import biomesoplenty.api.BOPAPI;
import com.seleneandmana.compatoplenty.core.data.client.COPBlockStateProvider;
import com.seleneandmana.compatoplenty.core.data.client.COPItemModelProvider;
import com.seleneandmana.compatoplenty.core.data.client.COPLanguageProvider;
import com.seleneandmana.compatoplenty.core.data.server.COPLootTableProvider;
import com.seleneandmana.compatoplenty.core.data.server.COPRecipeProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.COPBlockTagsProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.COPItemTagsProvider;
import com.seleneandmana.compatoplenty.core.other.COPClientCompat;
import com.seleneandmana.compatoplenty.core.other.COPCompat;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.COPBlocks;
import com.seleneandmana.compatoplenty.core.registry.helper.COPBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
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
    }

    // BOP doesn't expose its creative tab key, so we have to do it using the event
    private void setupTabEditors(BuildCreativeModeTabContentsEvent event) {
        var entries = event.getEntries();
        BiConsumer<Item, ItemLike> after = (item, toPut) -> entries.putAfter(item.getDefaultInstance(),
                toPut.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        BiConsumer<Item, ItemLike> before = (item, toPut) -> entries.putBefore(item.getDefaultInstance(),
                toPut.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        if (!event.getTabKey().location().equals(bopLoc("main"))) return;

        for (WoodMaterial woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            Item chestBoat = bopItem(woodMaterial.getName() + "_chest_boat").get();
            Item leaves = bopItem(woodMaterial.getName() + "_leaves").get();
            Item planks = bopItem(woodMaterial.getName() + "_planks").get();
            Item sign = bopItem(woodMaterial.getName() + "_sign").get();
            Item hangingSign = bopItem(woodMaterial.getName() + "_hanging_sign").get();
            Item slab = bopItem(woodMaterial.getName() + "_slab").get();

            if (ModList.get().isLoaded(BOATLOAD_ID)) {
                after.accept(chestBoat, ForgeRegistries.ITEMS.getValue(modLoc("large_" + woodMaterial.getName() + "_boat")));
                after.accept(chestBoat, ForgeRegistries.ITEMS.getValue(modLoc(woodMaterial.getName() + "_furnace_boat")));
            }
            if (ModList.get().isLoaded(WOODWORKS_ID) || ModList.get().isLoaded(QUARK_ID)) {
                before.accept(sign, COPBlocks.LADDERS.get(woodMaterial).get());
                if (ModList.get().isLoaded(WOODWORKS_ID)) {
                    after.accept(leaves, COPBlocks.LEAF_PILES.get(woodMaterial).get());
                    after.accept(planks, COPBlocks.BOARDS.get(woodMaterial).get());
                    before.accept(sign, COPBlocks.BEEHIVES.get(woodMaterial).get());
                }
                before.accept(sign, COPBlocks.BOOKSHELVES.get(woodMaterial).get());
                after.accept(hangingSign, COPBlocks.TRAPPED_CHESTS.get(woodMaterial).get());
                after.accept(hangingSign, COPBlocks.CHESTS.get(woodMaterial).get());
                if (ModList.get().isLoaded(QUARK_ID)) {
                    after.accept(leaves, COPBlocks.HEDGES.get(woodMaterial).get());
                    after.accept(planks, COPBlocks.VERTICAL_PLANKS.get(woodMaterial).get());
                    after.accept(slab, COPBlocks.STRIPPED_POSTS.get(woodMaterial).get());
                    after.accept(slab, COPBlocks.POSTS.get(woodMaterial).get());
                    after.accept(slab, COPBlocks.VERTICAL_SLABS.get(woodMaterial).get());
                }
            }
            if (ModList.get().isLoaded(FARMERS_DELIGHT_ID)) {
                before.accept(sign, COPBlocks.CABINETS.get(woodMaterial).get());
            }
            if (ModList.get().isLoaded(TWIGS_ID)) {
                before.accept(sign, COPBlocks.TABLES.get(woodMaterial).get());
            }
        }
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation bopLoc(String path) {
        return new ResourceLocation(BOPAPI.MOD_ID, path);
    }

    public static Supplier<Item> bopItem(String path) {
        return () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(BOPAPI.MOD_ID, path));
    }

    public static Supplier<Block> bopBlock(String path) {
        return () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(BOPAPI.MOD_ID, path));
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
