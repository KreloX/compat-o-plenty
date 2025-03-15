package com.seleneandmana.compatoplenty.integrations.boatload;

import com.google.common.collect.ImmutableMap;
import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.core.registry.COPItems;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.Util;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class COPBoatTypes {
    private COPBoatTypes() {
    }

    public static final ImmutableMap<String, BoatloadBoatType> BOAT_TYPES = Util.make(new ImmutableMap.Builder<String, BoatloadBoatType>(), builder -> {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            String name = woodMaterial.getName();
            builder.put(name, BoatloadBoatType.register(BoatloadBoatType.create(CompatOPlenty.modLoc(name),
                    CompatOPlenty.bopItem(name + "_planks"), CompatOPlenty.bopItem(name + "_boat"), CompatOPlenty.bopItem(name + "_chest_boat"),
                    COPItems.FURNACE_BOATS.get(woodMaterial), COPItems.LARGE_BOATS.get(woodMaterial))));
        }
    }).build();

    public static Supplier<Item> furnaceBoat(String materialName) {
        return () -> new FurnaceBoatItem(BOAT_TYPES.get(materialName));
    }

    public static Supplier<Item> largeBoat(String materialName) {
        return () -> new LargeBoatItem(BOAT_TYPES.get(materialName));
    }
}
