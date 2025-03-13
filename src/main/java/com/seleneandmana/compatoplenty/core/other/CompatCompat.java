package com.seleneandmana.compatoplenty.core.other;

import com.seleneandmana.compatoplenty.core.registry.CompatBlocks;
import com.teamabnormals.blueprint.core.util.DataUtil;

public class CompatCompat {
    private CompatCompat() {
    }

    public static void compatList() {
        registerFlammables();
        registerCompostables();
    }

    public static void registerFlammables() {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            DataUtil.registerFlammable(CompatBlocks.LEAF_CARPETS.get(woodMaterial).get(), 30, 60);
            DataUtil.registerFlammable(CompatBlocks.HEDGES.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.POSTS.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.STRIPPED_POSTS.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.VERTICAL_SLABS.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.VERTICAL_PLANKS.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.BEEHIVES.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.BOOKSHELVES.get(woodMaterial).get(), 30, 20);
            DataUtil.registerFlammable(CompatBlocks.TABLES.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.BOARDS.get(woodMaterial).get(), 5, 20);
            DataUtil.registerFlammable(CompatBlocks.LEAF_PILES.get(woodMaterial).get(), 30, 60);
        }
        DataUtil.registerFlammable(CompatBlocks.RAINBOW_BIRCH_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.ORANGE_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.YELLOW_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.RED_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.ORIGIN_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.FLOWERING_OAK_LEAF_CARPET.get(), 30, 60);

        DataUtil.registerFlammable(CompatBlocks.RAINBOW_BIRCH_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CompatBlocks.ORANGE_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CompatBlocks.YELLOW_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CompatBlocks.RED_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CompatBlocks.ORIGIN_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(CompatBlocks.FLOWERING_OAK_HEDGE.get(), 5, 20);

        DataUtil.registerFlammable(CompatBlocks.FLOWERING_OAK_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.RAINBOW_BIRCH_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.ORIGIN_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.RED_MAPLE_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.ORANGE_MAPLE_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(CompatBlocks.YELLOW_MAPLE_LEAF_PILE.get(), 30, 60);
    }

    public static void registerCompostables() {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            DataUtil.registerCompostable(CompatBlocks.LEAF_CARPETS.get(woodMaterial).get(), 0.3f);
            DataUtil.registerCompostable(CompatBlocks.HEDGES.get(woodMaterial).get(), 0.3f);
        }
        DataUtil.registerCompostable(CompatBlocks.RAINBOW_BIRCH_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.ORANGE_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.YELLOW_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.RED_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.ORIGIN_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.FLOWERING_OAK_LEAF_CARPET.get(), 0.3f);

        DataUtil.registerCompostable(CompatBlocks.RAINBOW_BIRCH_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.ORANGE_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.YELLOW_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.RED_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.ORIGIN_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(CompatBlocks.FLOWERING_OAK_HEDGE.get(), 0.3f);
    }
}
