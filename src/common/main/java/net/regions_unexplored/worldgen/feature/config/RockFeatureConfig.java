package net.regions_unexplored.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record RockFeatureConfig(BlockStateProvider stateProvider, IntProvider blobCount, IntProvider blobOffsetXZ) implements FeatureConfiguration {
    public static final Codec<RockFeatureConfig> CODEC = RecordCodecBuilder.create(i -> i.group(
        BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(RockFeatureConfig::stateProvider),
        IntProviders.POSITIVE_CODEC.fieldOf("blob_count").forGetter(RockFeatureConfig::blobCount),
        IntProviders.CODEC.fieldOf("blob_offset_xz").forGetter(RockFeatureConfig::blobOffsetXZ)
    ).apply(i, RockFeatureConfig::new));

    public static RockFeatureConfig create(Block block) {
        return create(BlockStateProvider.simple(block));
    }
    
    public static RockFeatureConfig create(BlockStateProvider stateProvider) {
        return new RockFeatureConfig(stateProvider, UniformInt.of(3, 4), UniformInt.of(-1, 1));
    }

    public static RockFeatureConfig createLarge(Block block) {
        return createLarge(BlockStateProvider.simple(block));
    }
    
    public static RockFeatureConfig createLarge(BlockStateProvider stateProvider) {
        return new RockFeatureConfig(stateProvider, UniformInt.of(4, 7), UniformInt.of(-1, 1));
    }
}
