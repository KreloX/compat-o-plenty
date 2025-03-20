package com.seleneandmana.compatoplenty.core.data.server;

import com.seleneandmana.compatoplenty.core.registry.COPBlocks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class COPLootTableProvider extends LootTableProvider {
    public COPLootTableProvider(PackOutput output) {
        super(output, BuiltInLootTables.all(), List.of(new SubProviderEntry(CompatBlockLoot::new, LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(@NotNull Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationtracker) {
    }

    public static class CompatBlockLoot extends BlockLootSubProvider {
        protected CompatBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (var woodType : COPBlocks.WOOD_PROPERTIES.keySet()) {
                add(COPBlocks.VERTICAL_SLABS.get(woodType).get(), this::createSlabItemTable);
                var bookshelf = COPBlocks.BOOKSHELVES.get(woodType).get();
                add(bookshelf, createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantValue.exactly(3.0F)));
                dropSelf(COPBlocks.LADDERS.get(woodType).get());
                dropSelf(COPBlocks.STRIPPED_POSTS.get(woodType).get());
                dropSelf(COPBlocks.POSTS.get(woodType).get());
                dropSelf(COPBlocks.HEDGES.get(woodType).get());
                dropSelf(COPBlocks.LEAF_CARPETS.get(woodType).get());
                add(COPBlocks.CHESTS.get(woodType).get(), this::createNameableBlockEntityTable);
                add(COPBlocks.TRAPPED_CHESTS.get(woodType).get(), this::createNameableBlockEntityTable);
                var beehive = COPBlocks.BEEHIVES.get(woodType).get();
                add(beehive, createBeeHiveDrop(beehive));
                dropSelf(COPBlocks.CABINETS.get(woodType).get());
                dropSelf(COPBlocks.TABLES.get(woodType).get());
                dropSelf(COPBlocks.HOLLOW_LOGS.get(woodType).get());
                dropSelf(COPBlocks.VERTICAL_PLANKS.get(woodType).get());
                dropSelf(COPBlocks.BOARDS.get(woodType).get());
                add(COPBlocks.LEAF_PILES.get(woodType).get(), this::createLeafPileDrops);
            }
            // Vertical Slabs
            add(COPBlocks.WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.GALANOS_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);

            // Hedges
            dropSelf(COPBlocks.ORIGIN_HEDGE.get());
            dropSelf(COPBlocks.FLOWERING_OAK_HEDGE.get());
            dropSelf(COPBlocks.RAINBOW_BIRCH_HEDGE.get());
            dropSelf(COPBlocks.CYPRESS_HEDGE.get());
            dropSelf(COPBlocks.SNOWBLOSSOM_HEDGE.get());
            dropSelf(COPBlocks.RED_MAPLE_HEDGE.get());
            dropSelf(COPBlocks.YELLOW_MAPLE_HEDGE.get());
            dropSelf(COPBlocks.ORANGE_MAPLE_HEDGE.get());

            // Leaf Carpets
            dropSelf(COPBlocks.ORIGIN_LEAF_CARPET.get());
            dropSelf(COPBlocks.FLOWERING_OAK_LEAF_CARPET.get());
            dropSelf(COPBlocks.RAINBOW_BIRCH_LEAF_CARPET.get());
            dropSelf(COPBlocks.CYPRESS_LEAF_CARPET.get());
            dropSelf(COPBlocks.SNOWBLOSSOM_LEAF_CARPET.get());
            dropSelf(COPBlocks.RED_MAPLE_LEAF_CARPET.get());
            dropSelf(COPBlocks.YELLOW_MAPLE_LEAF_CARPET.get());
            dropSelf(COPBlocks.ORANGE_MAPLE_LEAF_CARPET.get());

            // Sandstone Bricks
            dropSelf(COPBlocks.WHITE_SANDSTONE_BRICKS.get());
            dropSelf(COPBlocks.ORANGE_SANDSTONE_BRICKS.get());
            dropSelf(COPBlocks.BLACK_SANDSTONE_BRICKS.get());
            dropSelf(COPBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(COPBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(COPBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(COPBlocks.WHITE_SANDSTONE_BRICK_WALL.get());
            dropSelf(COPBlocks.ORANGE_SANDSTONE_BRICK_WALL.get());
            dropSelf(COPBlocks.BLACK_SANDSTONE_BRICK_WALL.get());
            add(COPBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);

            // Galanos Blocks
            dropSelf(COPBlocks.GALANOS_BLOCK.get());
            dropSelf(COPBlocks.GALANOS_STAIRS.get());
            dropSelf(COPBlocks.GALANOS_PILLAR.get());
            add(COPBlocks.GALANOS_SLAB.get(), this::createSlabItemTable);

            // Polished Rose Quartz Blocks
            dropSelf(COPBlocks.POLISHED_ROSE_QUARTZ.get());
            dropSelf(COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get());
            dropSelf(COPBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get());
            dropSelf(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get());
            dropSelf(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get());
            dropSelf(COPBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get());
            dropSelf(COPBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get());
            add(COPBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(), this::createSlabItemTable);
            add(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), this::createSlabItemTable);

            // Leaf Piles
            add(COPBlocks.ORIGIN_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.FLOWERING_OAK_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.RAINBOW_BIRCH_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.CYPRESS_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.SNOWBLOSSOM_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.RED_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.ORANGE_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
            add(COPBlocks.YELLOW_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return COPBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
        }

        private LootTable.Builder createLeafPileDrops(Block block) {
            return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)));
        }
    }
}
