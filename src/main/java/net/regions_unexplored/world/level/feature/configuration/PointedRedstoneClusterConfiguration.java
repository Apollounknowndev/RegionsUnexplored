package net.regions_unexplored.world.level.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record PointedRedstoneClusterConfiguration(BlockState baseBlock, BlockState pointedBlock, HolderSet<Block> replaceableBlocks, int floorToCeilingSearchRange, IntProvider height, IntProvider radius, int maxStalagmiteStalactiteHeightDiff, int heightDeviation, IntProvider speleothemBlockLayerThickness, FloatProvider density, FloatProvider wetness, float chanceOfSpeleothemAtMaxDistanceFromCenter, int maxDistanceFromEdgeAffectingChanceOfSpeleothem, int maxDistanceFromCenterAffectingHeightBias) implements FeatureConfiguration {
    public static final Codec<PointedRedstoneClusterConfiguration> CODEC = RecordCodecBuilder.create(i -> i.group(
        BlockState.CODEC.fieldOf("base_block").forGetter(PointedRedstoneClusterConfiguration::baseBlock),
        BlockState.CODEC.fieldOf("pointed_block").forGetter(PointedRedstoneClusterConfiguration::pointedBlock),
        RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("replaceable_blocks").forGetter(PointedRedstoneClusterConfiguration::replaceableBlocks),
        Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").forGetter(PointedRedstoneClusterConfiguration::floorToCeilingSearchRange),
        IntProviders.codec(1, 128).fieldOf("height").forGetter(PointedRedstoneClusterConfiguration::height),
        IntProviders.codec(1, 128).fieldOf("radius").forGetter(PointedRedstoneClusterConfiguration::radius),
        Codec.intRange(0, 64).fieldOf("max_stalagmite_stalactite_height_diff").forGetter(PointedRedstoneClusterConfiguration::maxStalagmiteStalactiteHeightDiff),
        Codec.intRange(1, 64).fieldOf("height_deviation").forGetter(PointedRedstoneClusterConfiguration::heightDeviation),
        IntProviders.codec(0, 128).fieldOf("speleothem_block_layer_thickness").forGetter(PointedRedstoneClusterConfiguration::speleothemBlockLayerThickness),
        FloatProviders.codec(0.0F, 2.0F).fieldOf("density").forGetter(PointedRedstoneClusterConfiguration::density),
        FloatProviders.codec(0.0F, 2.0F).fieldOf("wetness").forGetter(PointedRedstoneClusterConfiguration::wetness),
        Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_speleothem_at_max_distance_from_center").forGetter(PointedRedstoneClusterConfiguration::chanceOfSpeleothemAtMaxDistanceFromCenter),
        Codec.intRange(1, 64).fieldOf("max_distance_from_edge_affecting_chance_of_speleothem").forGetter(PointedRedstoneClusterConfiguration::maxDistanceFromEdgeAffectingChanceOfSpeleothem),
        Codec.intRange(1, 64).fieldOf("max_distance_from_center_affecting_height_bias").forGetter(PointedRedstoneClusterConfiguration::maxDistanceFromCenterAffectingHeightBias)
    ).apply(i, PointedRedstoneClusterConfiguration::new));
}
