package net.regions_unexplored.block.type.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RUNyliumBlock extends NyliumBlock implements BonemealableBlock {
    protected final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public RUNyliumBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> bonemeal) {
        super(properties);
        this.feature = bonemeal;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        ChunkGenerator generator = level.getChunkSource().getGenerator();
        Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        this.place(registry, this.feature, level, generator, random, abovePos);
    }
    
    private void place(
        final Registry<ConfiguredFeature<?, ?>> registry,
        final ResourceKey<ConfiguredFeature<?, ?>> id,
        final ServerLevel level,
        final ChunkGenerator generator,
        final RandomSource random,
        final BlockPos pos
    ) {
        if (level.isInWorldBounds(pos)) {
            registry.get(id).ifPresent(h -> h.value().place(level, generator, random, pos));
        }
    }
}