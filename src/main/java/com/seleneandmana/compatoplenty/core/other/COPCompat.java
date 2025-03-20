package com.seleneandmana.compatoplenty.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;

import static com.seleneandmana.compatoplenty.core.registry.COPBlocks.*;

public class COPCompat {
    private COPCompat() {
    }

    public static void registerCompat() {
        registerFlammables();
        registerCompostables();
    }

    public static void registerFlammables() {
        for (var entry : WOOD_PROPERTIES.entrySet()) {
            var woodType = entry.getKey();
            var properties = entry.getValue();
            if (properties.planks().ignitedByLava) {
                DataUtil.registerFlammable(LEAF_CARPETS.get(woodType).get(), 30, 60);
                DataUtil.registerFlammable(HEDGES.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(POSTS.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(STRIPPED_POSTS.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(VERTICAL_SLABS.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(VERTICAL_PLANKS.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(BEEHIVES.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(BOOKSHELVES.get(woodType).get(), 30, 20);
                DataUtil.registerFlammable(TABLES.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(BOARDS.get(woodType).get(), 5, 20);
                DataUtil.registerFlammable(LEAF_PILES.get(woodType).get(), 30, 60);
            }
        }
        DataUtil.registerFlammable(RAINBOW_BIRCH_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(ORANGE_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(YELLOW_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(RED_MAPLE_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(ORIGIN_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(FLOWERING_OAK_LEAF_CARPET.get(), 30, 60);

        DataUtil.registerFlammable(RAINBOW_BIRCH_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(ORANGE_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(YELLOW_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(RED_MAPLE_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(ORIGIN_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(FLOWERING_OAK_HEDGE.get(), 5, 20);

        DataUtil.registerFlammable(FLOWERING_OAK_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(RAINBOW_BIRCH_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(ORIGIN_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(RED_MAPLE_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(ORANGE_MAPLE_LEAF_PILE.get(), 30, 60);
        DataUtil.registerFlammable(YELLOW_MAPLE_LEAF_PILE.get(), 30, 60);
    }

    public static void registerCompostables() {
        for (var woodType : WOOD_PROPERTIES.keySet()) {
            DataUtil.registerCompostable(LEAF_CARPETS.get(woodType).get(), 0.3f);
            DataUtil.registerCompostable(HEDGES.get(woodType).get(), 0.3f);
        }
        DataUtil.registerCompostable(RAINBOW_BIRCH_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(ORANGE_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(YELLOW_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(RED_MAPLE_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(ORIGIN_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(FLOWERING_OAK_LEAF_CARPET.get(), 0.3f);

        DataUtil.registerCompostable(RAINBOW_BIRCH_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(ORANGE_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(YELLOW_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(RED_MAPLE_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(ORIGIN_HEDGE.get(), 0.3f);
        DataUtil.registerCompostable(FLOWERING_OAK_HEDGE.get(), 0.3f);
    }
}
