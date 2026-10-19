package net.regions_unexplored.block.type.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CobaltObsidianBlock extends Block {
    public CobaltObsidianBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            Direction direction = Direction.getRandom(random);
            if (direction != Direction.UP) {
                BlockPos relativePos = pos.relative(direction);
                BlockState blockState = level.getBlockState(relativePos);
                if (!state.canOcclude() || !blockState.isFaceSturdy(level, relativePos, direction.getOpposite())) {
                    double xOffset = direction.getStepX() == 0 ? random.nextDouble() : 0.5 + direction.getStepX() * 0.6;
                    double yOffset = direction.getStepY() == 0 ? random.nextDouble() : 0.5 + direction.getStepY() * 0.6;
                    double zOffset = direction.getStepZ() == 0 ? random.nextDouble() : 0.5 + direction.getStepZ() * 0.6;
                    level.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset, 0.0, 0.0, 0.0);
                }
            }
        }
    }
}
