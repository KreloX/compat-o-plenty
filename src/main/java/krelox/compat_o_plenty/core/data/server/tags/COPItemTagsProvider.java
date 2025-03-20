package krelox.compat_o_plenty.core.data.server.tags;

import biomesoplenty.api.block.BOPWoodTypes;
import com.ninni.twigs.TwigsTags;
import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.core.registry.COPBlocks;
import krelox.compat_o_plenty.core.registry.COPItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.violetmoon.quark.base.Quark;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class COPItemTagsProvider extends ItemTagsProvider {
    public COPItemTagsProvider(PackOutput output, CompletableFuture<Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, registries, blockTags, CompatOPlenty.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        for (var woodType : COPBlocks.WOOD_PROPERTIES.keySet()) {
            tag(ItemTags.PLANKS).add(asItem(COPBlocks.VERTICAL_PLANKS.get(woodType)));
            tag(BlueprintItemTags.WOODEN_CHESTS).add(asItem(COPBlocks.CHESTS.get(woodType)));
            tag(BlueprintItemTags.WOODEN_TRAPPED_CHESTS).add(asItem(COPBlocks.TRAPPED_CHESTS.get(woodType)));
            tag(BlueprintItemTags.WOODEN_LADDERS).add(asItem(COPBlocks.LADDERS.get(woodType)));
            tag(BlueprintItemTags.WOODEN_BEEHIVES).add(asItem(COPBlocks.BEEHIVES.get(woodType)));
            tag(BlueprintItemTags.WOODEN_BOOKSHELVES).add(asItem(COPBlocks.BOOKSHELVES.get(woodType)));
            tag(BlueprintItemTags.WOODEN_CHISELED_BOOKSHELVES); // TODO
            tag(BlueprintItemTags.WOODEN_BOARDS).add(asItem(COPBlocks.BOARDS.get(woodType)));
            tag(BlueprintItemTags.LEAF_PILES).add(asItem(COPBlocks.LEAF_PILES.get(woodType)));
            tag(BlueprintItemTags.FURNACE_BOATS).add(COPItems.FURNACE_BOATS.get(woodType).get());
            tag(BlueprintItemTags.LARGE_BOATS).add(COPItems.LARGE_BOATS.get(woodType).get());
            tag(ModTags.WOODEN_CABINETS).add(asItem(COPBlocks.CABINETS.get(woodType)));
            tag(TwigsTags.TABLES_ITEM).add(asItem(COPBlocks.TABLES.get(woodType)));
            tag(TagUtil.itemTag(Quark.MOD_ID, "hollow_logs")); // TODO
            tag(TagUtil.itemTag(Quark.MOD_ID, "vertical_slabs")).add(asItem(COPBlocks.VERTICAL_SLABS.get(woodType)));
            tag(TagUtil.itemTag(Quark.MOD_ID, "hedges")).add(asItem(COPBlocks.HEDGES.get(woodType)));
        }
        // Blueprint tags
        tag(BlueprintItemTags.LEAF_PILES).add(
                asItem(COPBlocks.ORIGIN_LEAF_PILE),
                asItem(COPBlocks.FLOWERING_OAK_LEAF_PILE),
                asItem(COPBlocks.RAINBOW_BIRCH_LEAF_PILE),
                asItem(COPBlocks.CYPRESS_LEAF_PILE),
                asItem(COPBlocks.SNOWBLOSSOM_LEAF_PILE),
                asItem(COPBlocks.RED_MAPLE_LEAF_PILE),
                asItem(COPBlocks.ORANGE_MAPLE_LEAF_PILE),
                asItem(COPBlocks.YELLOW_MAPLE_LEAF_PILE)
        );
        // Quark tags
        tag(TagUtil.itemTag(Quark.MOD_ID, "hedges")).add(
                asItem(COPBlocks.FLOWERING_OAK_HEDGE),
                asItem(COPBlocks.RAINBOW_BIRCH_HEDGE),
                asItem(COPBlocks.ORIGIN_HEDGE),
                asItem(COPBlocks.CYPRESS_HEDGE),
                asItem(COPBlocks.SNOWBLOSSOM_HEDGE),
                asItem(COPBlocks.RED_MAPLE_HEDGE),
                asItem(COPBlocks.ORANGE_MAPLE_HEDGE),
                asItem(COPBlocks.YELLOW_MAPLE_HEDGE)
        );
        // Minecraft tags
        tag(ItemTags.SLABS).add(asItem(
                        COPBlocks.BLACK_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_SLAB),
                asItem(COPBlocks.GALANOS_SLAB),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_SLAB),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_SLAB)
        );
        tag(ItemTags.STAIRS).add(
                asItem(COPBlocks.BLACK_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_STAIRS),
                asItem(COPBlocks.GALANOS_STAIRS),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_STAIRS),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_STAIRS)
        );
        tag(ItemTags.WALLS).add(
                asItem(COPBlocks.BLACK_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.ORANGE_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.WHITE_SANDSTONE_BRICK_WALL),
                asItem(COPBlocks.POLISHED_ROSE_QUARTZ_BRICK_WALL)
        );
        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                asItem(COPBlocks.TABLES.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.CABINETS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.HOLLOW_LOGS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.VERTICAL_SLABS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.VERTICAL_PLANKS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.BOARDS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.POSTS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.STRIPPED_POSTS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.BOOKSHELVES.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.LADDERS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.BEEHIVES.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.CHESTS.get(BOPWoodTypes.HELLBARK)),
                asItem(COPBlocks.TRAPPED_CHESTS.get(BOPWoodTypes.HELLBARK)),
                COPItems.LARGE_BOATS.get(BOPWoodTypes.HELLBARK).get()
        );
    }

    public static Item asItem(RegistryObject<? extends Block> block) {
        return block.get().asItem();
    }
}
