package com.seleneandmana.compatoplenty.core.data.client;

import com.seleneandmana.compatoplenty.core.CompatOPlenty;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CompatBlockStateProvider extends BlueprintBlockStateProvider {
    public CompatBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, CompatOPlenty.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}
