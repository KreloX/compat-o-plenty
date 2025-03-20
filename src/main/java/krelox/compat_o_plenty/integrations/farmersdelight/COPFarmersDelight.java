package krelox.compat_o_plenty.integrations.farmersdelight;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class COPFarmersDelight {
    private COPFarmersDelight() {
    }

    public static Supplier<Block> cabinet(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
    }
}
