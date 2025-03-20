package krelox.compat_o_plenty.core.registry;

import biomesoplenty.api.BOPAPI;
import krelox.compat_o_plenty.integrations.boatload.COPBoatTypes;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import krelox.compat_o_plenty.core.CompatOPlenty;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = CompatOPlenty.MOD_ID)
public class COPItems {
    private COPItems() {
    }

    public static final ItemSubRegistryHelper HELPER = CompatOPlenty.REGISTRY_HELPER.getItemSubHelper();

    public static final HashMap<WoodType, RegistryObject<Item>> FURNACE_BOATS = new HashMap<>();
    public static final HashMap<WoodType, RegistryObject<Item>> LARGE_BOATS = new HashMap<>();

    static {
        if (ModList.get().isLoaded(CompatOPlenty.BOATLOAD_ID)) {
            for (var woodType : COPBlocks.WOOD_PROPERTIES.keySet()) {
                String materialName = woodType.name().replace(BOPAPI.MOD_ID + ":", "");
                FURNACE_BOATS.put(woodType, HELPER.createItem(materialName + "_furnace_boat", ModList.get().isLoaded(CompatOPlenty.BOATLOAD_ID)
                        ? COPBoatTypes.furnaceBoat(woodType)
                        : () -> new Item(new Item.Properties())));
                LARGE_BOATS.put(woodType, HELPER.createItem("large_" + materialName + "_boat", ModList.get().isLoaded(CompatOPlenty.BOATLOAD_ID)
                        ? COPBoatTypes.largeBoat(woodType)
                        : () -> new Item(new Item.Properties())));
            }
        }
    }

    public static final RegistryObject<Item> GLOWING_MOSS_PASTE = HELPER.createItem("glowing_moss_paste", () -> new Item(new Item.Properties()));
}
