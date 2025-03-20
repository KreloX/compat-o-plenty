package com.seleneandmana.compatoplenty.integrations.twigs;

import com.ninni.twigs.block.TableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class COPTwigs {
    private COPTwigs() {
    }

    public static Supplier<Block> table(BlockBehaviour.Properties properties) {
        return () -> new TableBlock(properties);
    }
}
