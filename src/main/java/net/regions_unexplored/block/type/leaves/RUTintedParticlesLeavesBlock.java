package net.regions_unexplored.block.type.leaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.BlockFactory;
import net.regions_unexplored.registry.RUParticleTypes;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class RUTintedParticlesLeavesBlock extends LeavesBlock {
    public static final float DEFAULT_PARTICLE_CHANCE = 0.025f;

    private final Supplier<ParticleType<ColorParticleOption>> particle;
    private final TintGetter tintGetter;

    public RUTintedParticlesLeavesBlock(Properties properties, Supplier<ParticleType<ColorParticleOption>> particle, TintGetter getter, float particleChance) {
        super(particleChance, properties);
        this.particle = particle;
        this.tintGetter = getter;
    }
    
    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return null;
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> small(TintGetter tint) {
        return p -> new RUTintedParticlesLeavesBlock(p, RUParticleTypes.SMALL_LEAVES, tint, DEFAULT_PARTICLE_CHANCE);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> large(TintGetter tint) {
        return p -> new RUTintedParticlesLeavesBlock(p, RUParticleTypes.LARGE_LEAVES, tint, 0.01f);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> pine(int tint) {
        return p -> new RUTintedParticlesLeavesBlock(p, RUParticleTypes.PINE_LEAVES, TintGetter.constant(tint), 0.01f);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> standard() {
        return standard(TintGetter.DEFAULT);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> standard(TintGetter tint) {
        return standard(RUParticleTypes.STANDARD_LEAVES, tint);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> standard(Supplier<ParticleType<ColorParticleOption>> type, TintGetter tint) {
        return standard(type, tint, DEFAULT_PARTICLE_CHANCE);
    }

    public static BlockFactory<RUTintedParticlesLeavesBlock> standard(Supplier<ParticleType<ColorParticleOption>> type, TintGetter tint, float particleChance) {
        return p -> new RUTintedParticlesLeavesBlock(p, type, tint, particleChance);
    }
    
    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {
        ColorParticleOption particle = ColorParticleOption.create(this.particle.get(), this.tintGetter.apply(level, blockPos));
        ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, particle);
    }

    public interface TintGetter extends BiFunction<Level, BlockPos, Integer> {
        TintGetter DEFAULT = (level, pos) -> {
            BlockState state = level.getBlockState(pos);
            return Minecraft.getInstance().getBlockColors().getTintSource(state, 0).colorInWorld(state, (BlockAndTintGetter) level, pos);
        };

        static TintGetter defaultDarken(float amount) {
            return (level, pos) -> {
                int base = DEFAULT.apply(level, pos);
                int r = (int) (((base >> 16) & 0xFF) * amount) << 16;
                int g = (int) (((base >> 8) & 0xFF) * amount) << 8;
                int b = (int) ((base & 0xFF) * amount);
                return r | g | b;
            };
        }

        static TintGetter constant(int tint) {
            return (level, pos) -> tint;
        }
    }
}
