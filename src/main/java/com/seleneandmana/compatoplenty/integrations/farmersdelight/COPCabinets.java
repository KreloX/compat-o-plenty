package com.seleneandmana.compatoplenty.integrations.farmersdelight;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class COPCabinets {
    private COPCabinets() {
    }

    public static Supplier<Block> cabinet(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }
}
