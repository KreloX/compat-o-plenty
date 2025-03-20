package krelox.compat_o_plenty.integrations.boatload;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.api.block.BOPWoodTypes;
import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.core.registry.COPBlocks;
import krelox.compat_o_plenty.core.registry.COPItems;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashMap;
import java.util.function.Supplier;

public class COPBoatTypes {
    private COPBoatTypes() {
    }

    public static final HashMap<WoodType, BoatloadBoatType> BOAT_TYPES = Util.make(new HashMap<>(), builder -> {
        for (var woodType : COPBlocks.WOOD_PROPERTIES.keySet()) {
            String materialName = woodType.name().replace(BOPAPI.MOD_ID + ":", "");
            builder.put(woodType, BoatloadBoatType.register(BoatloadBoatType.create(CompatOPlenty.modLoc(materialName),
                    CompatOPlenty.bopItem(materialName + "_planks"), CompatOPlenty.bopItem(materialName + "_boat"), CompatOPlenty.bopItem(materialName + "_chest_boat"),
                    () -> COPItems.FURNACE_BOATS.get(woodType).get(), () -> COPItems.LARGE_BOATS.get(woodType).get(), woodType == BOPWoodTypes.HELLBARK, false)));
        }
    });

    public static Supplier<Item> furnaceBoat(WoodType woodType) {
        return () -> new FurnaceBoatItem(BOAT_TYPES.get(woodType));
    }

    public static Supplier<Item> largeBoat(WoodType woodType) {
        return () -> new LargeBoatItem(BOAT_TYPES.get(woodType));
    }
}
