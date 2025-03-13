package com.seleneandmana.compatoplenty.core;

import biomesoplenty.api.BOPAPI;
import com.seleneandmana.compatoplenty.core.data.client.CompatBlockStateProvider;
import com.seleneandmana.compatoplenty.core.data.client.CompatLanguageProvider;
import com.seleneandmana.compatoplenty.core.data.server.CompatLootTableProvider;
import com.seleneandmana.compatoplenty.core.data.server.CompatRecipeProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.CompatBlockTagsProvider;
import com.seleneandmana.compatoplenty.core.data.server.tags.CompatItemTagsProvider;
import com.seleneandmana.compatoplenty.core.other.CompatCompat;
import com.seleneandmana.compatoplenty.core.registry.helper.CompatBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

@Mod(CompatOPlenty.MOD_ID)
public class CompatOPlenty {
    public static final String MOD_ID = "compatoplenty";
    public static final String QUARK_ID = "quark";
    public static final String WOODWORKS_ID = "woodworks";
    public static final String TWIGS_ID = "twigs";
    public static final String FARMERS_DELIGHT_ID = "farmersdelight";
    public static final String BOATLOAD_ID = "boatload";

    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper ->
            helper.putSubHelper(ForgeRegistries.BLOCKS, new CompatBlockSubRegistryHelper(helper)));

    public CompatOPlenty() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRY_HELPER.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::compatSetup);
        bus.addListener(this::gatherData);
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation bopLoc(String path) {
        return new ResourceLocation(BOPAPI.MOD_ID, path);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var fileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();
        var packOutput = generator.getPackOutput();

        Consumer<DataProvider> client = provider -> generator.addProvider(event.includeClient(), provider);
        Consumer<DataProvider> server = provider -> generator.addProvider(event.includeServer(), provider);

        client.accept(new CompatLanguageProvider(packOutput));
        client.accept(new CompatBlockStateProvider(packOutput, fileHelper));

        var blockTags = new CompatBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        server.accept(blockTags);
        server.accept(new CompatItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        server.accept(new CompatLootTableProvider(packOutput));
        server.accept(new CompatRecipeProvider(packOutput));
    }

    private void compatSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CompatCompat::compatList);
    }
}
