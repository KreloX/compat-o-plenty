package krelox.compat_o_plenty.core.data.client;

import biomesoplenty.api.block.BOPWoodTypes;
import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.core.registry.COPBlocks;
import krelox.compat_o_plenty.core.registry.COPItems;
import com.teamabnormals.blueprint.core.data.client.BlueprintLanguageProvider;
import net.minecraft.data.PackOutput;

public class COPLanguageProvider extends BlueprintLanguageProvider {
    public COPLanguageProvider(PackOutput output) {
        super(output, CompatOPlenty.MOD_ID);
    }

    @Override
    protected void addTranslations() {
        for (var item : COPItems.HELPER.getDeferredRegister().getEntries()) {
            String path = item.getId().getPath();
            if (path.contains("leaf_pile")) continue;
            addItem(item, format(path.replace("_hedge", "_leaf_hedge")).replace("Furnace Boat", "Boat with Furnace"));
        }

        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.FIR), "Pile of Fir Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.REDWOOD), "Pile of Redwood Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.MAHOGANY), "Pile of Mahogany Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.JACARANDA), "Pile of Jacaranda Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.PALM), "Pile of Palm Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.WILLOW), "Pile of Willow Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.DEAD), "Pile of Dead Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.MAGIC), "Pile of Magic Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.UMBRAN), "Pile of Umbran Leaves");
        addBlock(COPBlocks.LEAF_PILES.get(BOPWoodTypes.HELLBARK), "Pile of Hellbark Leaves");
        addBlock(COPBlocks.ORIGIN_LEAF_PILE, "Pile of Origin Leaves");
        addBlock(COPBlocks.FLOWERING_OAK_LEAF_PILE, "Pile of Flowering Oak Leaves");
        addBlock(COPBlocks.RAINBOW_BIRCH_LEAF_PILE, "Pile of Rainbow Birch Leaves");
        addBlock(COPBlocks.CYPRESS_LEAF_PILE, "Pile of Cypress Leaves");
        addBlock(COPBlocks.SNOWBLOSSOM_LEAF_PILE, "Pile of Snowblossom Leaves");
        addBlock(COPBlocks.RED_MAPLE_LEAF_PILE, "Pile of Maple Leaves");
        addBlock(COPBlocks.ORANGE_MAPLE_LEAF_PILE, "Pile of Orange Autumn Leaves");
        addBlock(COPBlocks.YELLOW_MAPLE_LEAF_PILE, "Pile of Yellow Autumn Leaves");
    }
}
