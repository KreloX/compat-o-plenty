package com.seleneandmana.compatoplenty.core.other;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import static com.seleneandmana.compatoplenty.core.registry.COPBlocks.*;

public class COPClientCompat {
    private COPClientCompat() {
    }

    public static void registerClientCompat() {
        registerRenderLayers();
    }

    private static void registerRenderLayers() {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            setRenderLayer(LADDERS.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(POSTS.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(STRIPPED_POSTS.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(HEDGES.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(LEAF_CARPETS.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(LEAF_PILES.get(woodMaterial).get(), RenderType.cutout());
            setRenderLayer(TABLES.get(woodMaterial).get(), RenderType.cutout());
        }
        setRenderLayer(FLOWERING_OAK_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(FLOWERING_OAK_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(FLOWERING_OAK_HEDGE.get(), RenderType.cutout());
        setRenderLayer(RAINBOW_BIRCH_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(RAINBOW_BIRCH_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(RAINBOW_BIRCH_HEDGE.get(), RenderType.cutout());
        setRenderLayer(ORIGIN_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(ORIGIN_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(ORIGIN_HEDGE.get(), RenderType.cutout());
        setRenderLayer(RED_MAPLE_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(RED_MAPLE_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(RED_MAPLE_HEDGE.get(), RenderType.cutout());
        setRenderLayer(ORANGE_MAPLE_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(ORANGE_MAPLE_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(ORANGE_MAPLE_HEDGE.get(), RenderType.cutout());
        setRenderLayer(YELLOW_MAPLE_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(YELLOW_MAPLE_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(YELLOW_MAPLE_HEDGE.get(), RenderType.cutout());
    }

    private static void setRenderLayer(Block block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block, type::equals);
    }
}
