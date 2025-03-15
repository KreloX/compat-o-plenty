package com.seleneandmana.compatoplenty.client;

import biomesoplenty.init.ModClient;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

import static com.seleneandmana.compatoplenty.core.registry.COPBlocks.*;

@Mod.EventBusSubscriber(modid = CompatOPlenty.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockColoring {
    private BlockColoring() {
    }

    private static final Supplier<Block[]> NORMAL = () -> new Block[]{
            FLOWERING_OAK_LEAF_CARPET.get(), FLOWERING_OAK_LEAF_PILE.get(), FLOWERING_OAK_HEDGE.get(),
            LEAF_CARPETS.get(WoodMaterial.MAHOGANY).get(), LEAF_PILES.get(WoodMaterial.MAHOGANY).get(), HEDGES.get(WoodMaterial.MAHOGANY).get(),
            LEAF_CARPETS.get(WoodMaterial.WILLOW).get(), LEAF_PILES.get(WoodMaterial.WILLOW).get(), HEDGES.get(WoodMaterial.WILLOW).get(),
            LEAF_CARPETS.get(WoodMaterial.PALM).get(), LEAF_PILES.get(WoodMaterial.PALM).get(), HEDGES.get(WoodMaterial.PALM).get()
    };

    @SubscribeEvent
    public static void onBiomeColoring(RegisterColorHandlersEvent.Block event) {
        var blockColors = event.getBlockColors();
        //Normal
        blockColors.register((state, world, pos, tint) -> world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor(),
                NORMAL.get());
        //Rainbow
        blockColors.register((state, world, pos, tint) -> world != null && pos != null
                        ? ModClient.getRainbowBirchColor(world, pos)
                        : FoliageColor.getDefaultColor(),
                RAINBOW_BIRCH_LEAF_CARPET.get(), RAINBOW_BIRCH_LEAF_PILE.get(), RAINBOW_BIRCH_HEDGE.get());
    }

    @SubscribeEvent
    public static void onItemColoring(RegisterColorHandlersEvent.Item event) {
        var blockColors = event.getBlockColors();
        var itemColors = event.getItemColors();

        itemColors.register((stack, tint) -> {
            var state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

            return tint == 0 ? blockColors.getColor(state, null, null, tint) : 0xFFFFFFFF;
        }, NORMAL.get());
    }
}
