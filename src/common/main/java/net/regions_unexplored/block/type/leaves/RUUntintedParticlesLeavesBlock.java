package net.regions_unexplored.block.type.leaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.regions_unexplored.block.BlockFactory;

import java.util.function.Supplier;

public class RUUntintedParticlesLeavesBlock extends LeavesBlock {
    public static final float PARTICLE_CHANCE = 0.025f;

    private final Supplier<SimpleParticleType> particle;

    public RUUntintedParticlesLeavesBlock(Properties properties, Supplier<SimpleParticleType> particle) {
        super(PARTICLE_CHANCE, properties);
        this.particle = particle;
    }

    public static BlockFactory<RUUntintedParticlesLeavesBlock> of(Supplier<SimpleParticleType> type) {
        return p -> new RUUntintedParticlesLeavesBlock(p, type);
    }
    
    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return null;
    }
    
    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        ParticleUtils.spawnParticleBelow(level, pos, random, this.particle.get());
    }
}
