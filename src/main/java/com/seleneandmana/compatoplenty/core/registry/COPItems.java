package com.seleneandmana.compatoplenty.core.registry;

import com.seleneandmana.compatoplenty.core.other.WoodMaterial;
import com.seleneandmana.compatoplenty.integrations.boatload.COPBoatTypes;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;

import static com.seleneandmana.compatoplenty.core.CompatOPlenty.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class COPItems {
    private COPItems() {
    }

    public static final ItemSubRegistryHelper HELPER = REGISTRY_HELPER.getItemSubHelper();

    public static final EnumMap<WoodMaterial, RegistryObject<Item>> FURNACE_BOATS = new EnumMap<>(WoodMaterial.class);
    public static final EnumMap<WoodMaterial, RegistryObject<Item>> LARGE_BOATS = new EnumMap<>(WoodMaterial.class);

    static {
        for (var woodMaterial : WoodMaterial.WOOD_MATERIALS) {
            FURNACE_BOATS.put(woodMaterial, HELPER.createItem(woodMaterial.getName() + "_furnace_boat", ModList.get().isLoaded(BOATLOAD_ID)
                    ? COPBoatTypes.furnaceBoat(woodMaterial.getName())
                    : () -> new Item(new Item.Properties())));
            LARGE_BOATS.put(woodMaterial, HELPER.createItem("large_" + woodMaterial.getName() + "_boat", ModList.get().isLoaded(BOATLOAD_ID)
                    ? COPBoatTypes.largeBoat(woodMaterial.getName())
                    : () -> new Item(new Item.Properties())));
        }
    }

    public static final RegistryObject<Item> GLOWING_MOSS_PASTE = HELPER.createItem("glowing_moss_paste", () -> new Item(new Item.Properties()));
}
