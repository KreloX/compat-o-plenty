package krelox.compat_o_plenty.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RoseQuartzWallBlock extends WallBlock {
    public RoseQuartzWallBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos pos = result.getBlockPos();
            level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
            level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 1.2F);
        }
    }
}
