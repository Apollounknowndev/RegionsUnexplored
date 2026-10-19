package net.regions_unexplored.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CarvedLimitedPoolFeatureConfig(int depth, IntProvider slopeDepth, BlockPredicate pool, BlockPredicate wall, BlockStateProvider slope, BlockStateProvider slopeTop) implements FeatureConfiguration {
    public static final Codec<CarvedLimitedPoolFeatureConfig> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.intRange(0, 16).fieldOf("depth").forGetter(placer -> placer.depth),
            IntProvider.codec(0, 16).fieldOf("slopeDepth").forGetter(placer -> placer.slopeDepth),
            BlockPredicate.CODEC.fieldOf("pool_allowed").forGetter(placer -> placer.pool),
            BlockPredicate.CODEC.fieldOf("wall_allowed").forGetter(placer -> placer.wall),
            BlockStateProvider.CODEC.fieldOf("slope").forGetter(CarvedLimitedPoolFeatureConfig::slope),
            BlockStateProvider.CODEC.fieldOf("slopeTop").forGetter(CarvedLimitedPoolFeatureConfig::slopeTop)
    ).apply(builder, CarvedLimitedPoolFeatureConfig::new));
}