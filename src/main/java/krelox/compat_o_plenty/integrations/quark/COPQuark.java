package krelox.compat_o_plenty.integrations.quark;

import krelox.compat_o_plenty.common.blocks.RoseQuartzVerticalSlabBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.violetmoon.quark.content.building.block.*;

import java.util.function.Supplier;

public class COPQuark {
    private COPQuark() {
    }

    public static Supplier<Block> hollowLog(Supplier<Block> log, boolean flammable) {
        return () -> new HollowLogBlock(log.get(), null, flammable);
    }

    public static Supplier<Block> verticalSlab(Supplier<Block> slab, BlockBehaviour.Properties properties) {
        return () -> new VerticalSlabBlock(slab, properties);
    }

    public static Supplier<Block> roseQuartzVerticalSlab(Supplier<Block> slab, BlockBehaviour.Properties properties) {
        return () -> new RoseQuartzVerticalSlabBlock(slab, properties);
    }

    public static Supplier<Block> hedge(Supplier<Block> fence, Supplier<Block> leaves) {
        return () -> new HedgeBlock(null, null, fence.get(), leaves.get());
    }

    public static Supplier<Block> leafCarpet(Supplier<Block> leaves) {
        return () -> new LeafCarpetBlock(null, leaves.get(), null);
    }

    public static Supplier<Block> woodPost(Block fence, SoundType soundType) {
        return () -> new WoodPostBlock(null, fence, null, soundType);
    }
}
