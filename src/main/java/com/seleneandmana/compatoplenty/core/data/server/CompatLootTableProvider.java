package com.seleneandmana.compatoplenty.core.data.server;

import com.seleneandmana.compatoplenty.core.registry.CompatBlocks;
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

public class CompatLootTableProvider extends LootTableProvider {
    public CompatLootTableProvider(PackOutput output) {
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
            // Vertical Slabs
            for (var item : CompatBlocks.VERTICAL_SLABS.values()) {
                add(item.get(), this::createSlabItemTable);
            }
            add(CompatBlocks.WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.CUT_WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.CUT_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.CUT_BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.SMOOTH_WHITE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.SMOOTH_ORANGE_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.SMOOTH_BLACK_SANDSTONE_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.WHITE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.ORANGE_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.BLACK_SANDSTONE_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.GALANOS_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.POLISHED_ROSE_QUARTZ_VERTICAL_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_VERTICAL_SLAB.get(), this::createSlabItemTable);

            // Bookshelves
            for (var item : CompatBlocks.BOOKSHELVES.values()) {
                add(item.get(), createSingleItemTableWithSilkTouch(item.get(), Items.BOOK, ConstantValue.exactly(3.0F)));
            }

            // Ladders
            for (var item : CompatBlocks.LADDERS.values()) {
                dropSelf(item.get());
            }

            // Stripped Posts
            for (var item : CompatBlocks.STRIPPED_POSTS.values()) {
                dropSelf(item.get());
            }

            // Posts
            for (var item : CompatBlocks.POSTS.values()) {
                dropSelf(item.get());
            }

            // Hedges
            for (var item : CompatBlocks.HEDGES.values()) {
                dropSelf(item.get());
            }
            dropSelf(CompatBlocks.FLOWERING_OAK_HEDGE.get());
            dropSelf(CompatBlocks.RAINBOW_BIRCH_HEDGE.get());
            dropSelf(CompatBlocks.ORIGIN_HEDGE.get());
            dropSelf(CompatBlocks.RED_MAPLE_HEDGE.get());
            dropSelf(CompatBlocks.YELLOW_MAPLE_HEDGE.get());
            dropSelf(CompatBlocks.ORANGE_MAPLE_HEDGE.get());

            // Leaf Carpets
            for (var item : CompatBlocks.LEAF_CARPETS.values()) {
                dropSelf(item.get());
            }
            dropSelf(CompatBlocks.FLOWERING_OAK_LEAF_CARPET.get());
            dropSelf(CompatBlocks.RAINBOW_BIRCH_LEAF_CARPET.get());
            dropSelf(CompatBlocks.ORIGIN_LEAF_CARPET.get());
            dropSelf(CompatBlocks.RED_MAPLE_LEAF_CARPET.get());
            dropSelf(CompatBlocks.YELLOW_MAPLE_LEAF_CARPET.get());
            dropSelf(CompatBlocks.ORANGE_MAPLE_LEAF_CARPET.get());

            // Chests
            for (var item : CompatBlocks.CHESTS.values()) {
                dropSelf(item.get());
            }

            // Trapped Chests
            for (var item : CompatBlocks.TRAPPED_CHESTS.values()) {
                dropSelf(item.get());
            }

            // Beehives
            for (var item : CompatBlocks.BEEHIVES.values()) {
                add(item.get(), createBeeHiveDrop(item.get()));
            }

            // Cabinets
            for (var item : CompatBlocks.CABINETS.values()) {
                dropSelf(item.get());
            }

            // Tables
            for (var item : CompatBlocks.TABLES.values()) {
                dropSelf(item.get());
            }

            // Vertical Planks
            for (var item : CompatBlocks.VERTICAL_PLANKS.values()) {
                dropSelf(item.get());
            }

            // Sandstone Bricks
            dropSelf(CompatBlocks.WHITE_SANDSTONE_BRICKS.get());
            dropSelf(CompatBlocks.ORANGE_SANDSTONE_BRICKS.get());
            dropSelf(CompatBlocks.BLACK_SANDSTONE_BRICKS.get());
            dropSelf(CompatBlocks.WHITE_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(CompatBlocks.ORANGE_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(CompatBlocks.BLACK_SANDSTONE_BRICK_STAIRS.get());
            dropSelf(CompatBlocks.WHITE_SANDSTONE_BRICK_WALL.get());
            dropSelf(CompatBlocks.ORANGE_SANDSTONE_BRICK_WALL.get());
            dropSelf(CompatBlocks.BLACK_SANDSTONE_BRICK_WALL.get());
            add(CompatBlocks.WHITE_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.ORANGE_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.BLACK_SANDSTONE_BRICK_SLAB.get(), this::createSlabItemTable);

            // Galanos Blocks
            dropSelf(CompatBlocks.GALANOS_BLOCK.get());
            dropSelf(CompatBlocks.GALANOS_STAIRS.get());
            dropSelf(CompatBlocks.GALANOS_PILLAR.get());
            add(CompatBlocks.GALANOS_SLAB.get(), this::createSlabItemTable);

            // Polished Rose Quartz Blocks
            dropSelf(CompatBlocks.POLISHED_ROSE_QUARTZ.get());
            dropSelf(CompatBlocks.POLISHED_ROSE_QUARTZ_STAIRS.get());
            dropSelf(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICKS.get());
            dropSelf(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS.get());
            dropSelf(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL.get());
            dropSelf(CompatBlocks.CRACKED_POLISHED_ROSE_QUARTZ_BRICKS.get());
            dropSelf(CompatBlocks.CHISELED_POLISHED_ROSE_QUARTZ.get());
            add(CompatBlocks.POLISHED_ROSE_QUARTZ_SLAB.get(), this::createSlabItemTable);
            add(CompatBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB.get(), this::createSlabItemTable);

            // Boards
            for (var item : CompatBlocks.BOARDS.values()) {
                dropSelf(item.get());
            }

            // Leaf Piles
            for (var item : CompatBlocks.LEAF_PILES.values()) {
                add(item.get(), this::createLeafPileDrops);
            }
            add(CompatBlocks.FLOWERING_OAK_LEAF_PILE.get(), this::createLeafPileDrops);
            add(CompatBlocks.RAINBOW_BIRCH_LEAF_PILE.get(), this::createLeafPileDrops);
            add(CompatBlocks.ORIGIN_LEAF_PILE.get(), this::createLeafPileDrops);
            add(CompatBlocks.RED_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
            add(CompatBlocks.ORANGE_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
            add(CompatBlocks.YELLOW_MAPLE_LEAF_PILE.get(), this::createLeafPileDrops);
        }

        @Override
        protected @NotNull Iterable<Block> getKnownBlocks() {
            return CompatBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
        }

        private LootTable.Builder createLeafPileDrops(Block block) {
            return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)));
        }
    }
}
