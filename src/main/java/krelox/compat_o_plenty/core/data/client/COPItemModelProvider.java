package krelox.compat_o_plenty.core.data.client;

import krelox.compat_o_plenty.core.CompatOPlenty;
import krelox.compat_o_plenty.core.registry.COPItems;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.ExistingFileHelper;

public class COPItemModelProvider extends BlueprintItemModelProvider {
    public COPItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, CompatOPlenty.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        for (var item : COPItems.HELPER.getDeferredRegister().getEntries()) {
            if (item.get() instanceof BlockItem) continue;
            basicItem(item.get());
        }
    }
}
