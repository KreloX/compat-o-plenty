package krelox.compat_o_plenty.core.other;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import static krelox.compat_o_plenty.core.registry.COPBlocks.*;

public class COPClientCompat {
    private COPClientCompat() {
    }

    public static void registerClientCompat() {
        registerRenderLayers();
    }

    private static void registerRenderLayers() {
        for (var woodType : WOOD_PROPERTIES.keySet()) {
            setRenderLayer(LADDERS.get(woodType).get(), RenderType.cutout());
            setRenderLayer(POSTS.get(woodType).get(), RenderType.cutout());
            setRenderLayer(STRIPPED_POSTS.get(woodType).get(), RenderType.cutout());
            setRenderLayer(HEDGES.get(woodType).get(), RenderType.cutout());
            setRenderLayer(LEAF_CARPETS.get(woodType).get(), RenderType.cutout());
            setRenderLayer(LEAF_PILES.get(woodType).get(), RenderType.cutout());
            setRenderLayer(TABLES.get(woodType).get(), RenderType.cutout());
        }
        setRenderLayer(ORIGIN_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(ORIGIN_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(ORIGIN_HEDGE.get(), RenderType.cutout());

        setRenderLayer(FLOWERING_OAK_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(FLOWERING_OAK_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(FLOWERING_OAK_HEDGE.get(), RenderType.cutout());

        setRenderLayer(RAINBOW_BIRCH_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(RAINBOW_BIRCH_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(RAINBOW_BIRCH_HEDGE.get(), RenderType.cutout());

        setRenderLayer(CYPRESS_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(CYPRESS_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(CYPRESS_HEDGE.get(), RenderType.cutout());

        setRenderLayer(SNOWBLOSSOM_LEAF_CARPET.get(), RenderType.cutout());
        setRenderLayer(SNOWBLOSSOM_LEAF_PILE.get(), RenderType.cutout());
        setRenderLayer(SNOWBLOSSOM_HEDGE.get(), RenderType.cutout());

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
