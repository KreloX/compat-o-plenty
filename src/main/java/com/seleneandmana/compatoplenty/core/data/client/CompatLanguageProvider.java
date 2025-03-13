package com.seleneandmana.compatoplenty.core.data.client;

import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.CompatBlocks;
import com.seleneandmana.compatoplenty.core.registry.CompatItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;

public class CompatLanguageProvider extends LanguageProvider {
    public CompatLanguageProvider(PackOutput output) {
        super(output, CompatOPlenty.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (var block : CompatOPlenty.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries()) {
            addBlock(block, StringUtils.capitaliseAllWords(block.getId().getPath().replace("_hedge", "_leaf_hedge").replace('_', ' ')));
        }

        try {
            //Leaf Piles
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.JACARANDA).get(), "Pile of Jacaranda Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.FIR).get(), "Pile of Fir Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.REDWOOD).get(), "Pile of Redwood Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.MAHOGANY).get(), "Pile of Mahogany Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.WILLOW).get(), "Pile of Willow Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.MAGIC).get(), "Pile of Magic Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.DEAD).get(), "Pile of Dead Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.UMBRAN).get(), "Pile of Umbran Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.PALM).get(), "Pile of Palm Leaves");
            add(CompatBlocks.LEAF_PILES.get(WoodMaterial.HELLBARK).get(), "Pile of Hellbark Leaves");
            add(CompatBlocks.FLOWERING_OAK_LEAF_PILE.get(), "Pile of Flowering Oak Leaves");
            add(CompatBlocks.RAINBOW_BIRCH_LEAF_PILE.get(), "Pile of Rainbow Birch Leaves");
            add(CompatBlocks.ORIGIN_LEAF_PILE.get(), "Pile of Origin Leaves");
            add(CompatBlocks.RED_MAPLE_LEAF_PILE.get(), "Pile of Maple Leaves");
            add(CompatBlocks.ORANGE_MAPLE_LEAF_PILE.get(), "Pile of Orange Autumn Leaves");
            add(CompatBlocks.YELLOW_MAPLE_LEAF_PILE.get(), "Pile of Yellow Autumn Leaves");
        } catch (IllegalStateException ignored) {
            // ignore duplicate translation key
        }

        //Furnace Boats
        add(CompatItems.JACARANDA_FURNACE_BOAT.get(), "Jacaranda Boat with Furnace");
        add(CompatItems.FIR_FURNACE_BOAT.get(), "Fir Boat with Furnace");
        add(CompatItems.REDWOOD_FURNACE_BOAT.get(), "Redwood Boat with Furnace");
        add(CompatItems.MAHOGANY_FURNACE_BOAT.get(), "Mahogany Boat with Furnace");
        add(CompatItems.WILLOW_FURNACE_BOAT.get(), "Willow Boat with Furnace");
        add(CompatItems.MAGIC_FURNACE_BOAT.get(), "Magic Boat with Furnace");
        add(CompatItems.DEAD_FURNACE_BOAT.get(), "Dead Boat with Furnace");
        add(CompatItems.UMBRAN_FURNACE_BOAT.get(), "Umbran Boat with Furnace");
        add(CompatItems.PALM_FURNACE_BOAT.get(), "Palm Boat with Furnace");
        add(CompatItems.HELLBARK_FURNACE_BOAT.get(), "Hellbark Boat with Furnace");

        //Large Boats
        add(CompatItems.LARGE_JACARANDA_BOAT.get(), "Large Jacaranda Boat");
        add(CompatItems.LARGE_FIR_BOAT.get(), "Large Fir Boat");
        add(CompatItems.LARGE_REDWOOD_BOAT.get(), "Large Redwood Boat");
        add(CompatItems.LARGE_MAHOGANY_BOAT.get(), "Large Mahogany Boat");
        add(CompatItems.LARGE_WILLOW_BOAT.get(), "Large Willow Boat");
        add(CompatItems.LARGE_MAGIC_BOAT.get(), "Large Magic Boat");
        add(CompatItems.LARGE_DEAD_BOAT.get(), "Large Dead Boat");
        add(CompatItems.LARGE_UMBRAN_BOAT.get(), "Large Umbran Boat");
        add(CompatItems.LARGE_PALM_BOAT.get(), "Large Palm Boat");
        add(CompatItems.LARGE_HELLBARK_BOAT.get(), "Large Hellbark Boat");

        //Misc
        add(CompatItems.GLOWING_MOSS_PASTE.get(), "Glowing Moss Paste");
    }
}
