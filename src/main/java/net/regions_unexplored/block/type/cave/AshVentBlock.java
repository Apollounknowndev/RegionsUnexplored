package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AshVentBlock extends Block {
    public AshVentBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any());
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        int smokeParticles = 20;
        int lavaParticles = 2;
        if (level.getBlockState(pos.below()).is(Blocks.MAGMA_BLOCK)) {
            smokeParticles *= 3;
            lavaParticles *= 3;
        }
        
        for(int i = 0; i < smokeParticles; ++i) {
            level.addParticle(
                ParticleTypes.SMOKE,
                pos.getX() + 0.5 + random.nextDouble() / 4.0 * (random.nextBoolean() ? 1 : -1),
                pos.getY() + 0.7,
                pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (random.nextBoolean() ? 1 : -1),
                0.0, 0.005, 0.0
            );
        }
        for(int i = 0; i < lavaParticles; ++i) {
            level.addParticle(
                ParticleTypes.LAVA,
                pos.getX() + 0.5,
                pos.getY() + 0.8,
                pos.getZ() + 0.5,
                random.nextFloat() / 2, 0.00005, random.nextFloat() / 2
            );
        }
        if (random.nextInt(10) == 0) {
            level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(), random.nextFloat() * 0.7F + 0.6F, false);
        }
    }
}
