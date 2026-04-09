package net.regions_unexplored.datagen.provider.registry.placed_feature;

import dev.worldgen.lithostitched.api.worldgen.blockpredicate.LithostitchedBlockPredicates;
import dev.worldgen.lithostitched.api.worldgen.placementmodifier.LithostitchedPlacementModifiers;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuMiscOverworldFeatures;
import net.regions_unexplored.datagen.provider.registry.RUPlacedFeatureBootstrap;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUPlacedFeatures;

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.*;
import static net.regions_unexplored.registry.data.RUPlacedFeatures.key;

public class RuMiscOverworldPlacements {
    //FALLEN_TREES
    public static final ResourceKey<PlacedFeature> FALLEN_LARCH = key("tree/fallen_larch");
    public static final ResourceKey<PlacedFeature> FALLEN_MAPLE = key("tree/fallen_maple");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_SPARSE = key("tree/fallen_oak_tree_sparse");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_DENSE = key("tree/fallen_oak_tree_dense");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE = key("tree/fallen_pine");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE_ON_DIRT = key("tree/fallen_pine_on_dirt");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE_ON_SNOW = key("tree/fallen_pine_on_snow");
    public static final ResourceKey<PlacedFeature> FALLEN_SILVER_BIRCH = key("tree/fallen_silver_birch");
    //CAVE_FEATURES
    public static final ResourceKey<PlacedFeature> POINTED_REDSTONE = key("pointed_redstone");
    public static final ResourceKey<PlacedFeature> LARGE_POINTED_REDSTONE = key("large_pointed_redstone");
    public static final ResourceKey<PlacedFeature> POINTED_REDSTONE_CLUSTER = key("pointed_redstone_cluster");
    public static final ResourceKey<PlacedFeature> ORE_REDSTONE_LARGE = key("ore_redstone_large");
    public static final ResourceKey<PlacedFeature> PRISMARITE_CLUSTERS = key("prismarite_clusters");
    public static final ResourceKey<PlacedFeature> HANGING_PRISMARITE_CLUSTER = key("hanging_prismarite_cluster");
    public static final ResourceKey<PlacedFeature> LAVA_FALL = key("lava_fall");
    public static final ResourceKey<PlacedFeature> OVERWORLD_LAVA_DELTA = key("overworld_lava_delta");
    public static final ResourceKey<PlacedFeature> ASH_VENT = key("ash_vent");
    public static final ResourceKey<PlacedFeature> BASALT_BLOB = key("basalt_blob");
    //OTHER_FEATURES
    public static final ResourceKey<PlacedFeature> MINERAL_POOL = key("mineral_pool");

    public static final ResourceKey<PlacedFeature> MOSS_PATCH_WITH_WATER = key("moss_patch_with_water");
    public static final ResourceKey<PlacedFeature> MOSS_PATCH_WITH_WATER_UNCOMMON = key("moss_patch_with_water_uncommon");
    public static final ResourceKey<PlacedFeature> MOSS_PATCH_WITH_WATER_RARE = key("moss_patch_with_water_rare");
    public static final ResourceKey<PlacedFeature> MARSH = key("marsh");
    public static final ResourceKey<PlacedFeature> WATER_EDGE = key("water_edge");
    public static final ResourceKey<PlacedFeature> ICICLE_UP = key("icicle_up");
    public static final ResourceKey<PlacedFeature> ROCK_ON_SNOW = key("rock_on_snow");
    public static final ResourceKey<PlacedFeature> NOISE_PUMPKINS = key("noise_pumpkins");

    public static final ResourceKey<PlacedFeature> ROCK_GROUP_HIGHLAND_FIELDS = key("rock/group/highland_fields");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_ROCKY_MEADOW = key("rock/group/rocky_meadow");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_TEMPERATE_GROVE = key("rock/group/temperate_grove");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_TUNDRA = key("rock/group/tundra");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        var rockMixedStone = getter.getOrThrow(RuMiscOverworldFeatures.ROCK_MIXED_STONE);
        //FALLEN_TREES
        final Holder<ConfiguredFeature<?, ?>> FALLEN_OAK = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_OAK);
        final Holder<ConfiguredFeature<?, ?>> FALLEN_PINE = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_PINE);
        final Holder<ConfiguredFeature<?, ?>> FALLEN_SNOW_PINE = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_SNOW_PINE);
        //CAVE_FEATURES
        //OTHER_FEATURES
        final Holder<ConfiguredFeature<?, ?>> MOSS_PATCH_WITH_WATER = getter.getOrThrow(RuMiscOverworldFeatures.MOSS_PATCH_WITH_WATER);

        //--------------------PLACEMENTS--------------------//
        //FALLEN_TREES
        register(context, RuMiscOverworldPlacements.FALLEN_LARCH, surfaceSpread(0.333, Heightmap.Types.OCEAN_FLOOR, RUBlocks.LARCH_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_SILVER_BIRCH, surfaceSpread(0.5, Heightmap.Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_MAPLE, surfaceSpread(0.5, Heightmap.Types.OCEAN_FLOOR, RUBlocks.MAPLE_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_OAK_SPARSE, FALLEN_OAK, surfaceSpread(0.16, Heightmap.Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_OAK_DENSE, FALLEN_OAK, surfaceSpread(0.5, Heightmap.Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE, surfaceSpread(0.5, Heightmap.Types.OCEAN_FLOOR, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE_ON_DIRT, FALLEN_PINE, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onDirtPredicate), BiomeFilter.biome()));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE_ON_SNOW, FALLEN_SNOW_PINE, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));
        //CAVE_FEATURES
        register(context, RuMiscOverworldPlacements.POINTED_REDSTONE, CountPlacement.of(UniformInt.of(192, 256)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, CountPlacement.of(UniformInt.of(1, 5)), RandomOffsetPlacement.of(ClampedNormalInt.of(0.0F, 3.0F, -10, 10), ClampedNormalInt.of(0.0F, 0.6F, -2, 2)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.LARGE_POINTED_REDSTONE, CountPlacement.of(UniformInt.of(10, 48)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.POINTED_REDSTONE_CLUSTER, CountPlacement.of(UniformInt.of(78, 126)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.ORE_REDSTONE_LARGE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(112))));

        register(context, RuMiscOverworldPlacements.PRISMARITE_CLUSTERS, CountOnEveryLayerPlacement.of(8), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.HANGING_PRISMARITE_CLUSTER, CountPlacement.of(UniformInt.of(78, 126)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

        register(context, RuMiscOverworldPlacements.MINERAL_POOL, CountPlacement.of(70), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());

        var notInStructurePredicate = BlockPredicateFilter.forPredicate(BlockPredicate.not(LithostitchedBlockPredicates.inStructure(4)));
        register(context, RuMiscOverworldPlacements.LAVA_FALL,
                CountOnEveryLayerPlacement.of(1),
                BiomeFilter.biome(),
                notInStructurePredicate
        );
        register(context, RuMiscOverworldPlacements.OVERWORLD_LAVA_DELTA,
                CountPlacement.of(115),
                InSquarePlacement.spread(),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                BiomeFilter.biome(),
                notInStructurePredicate
        );
        register(context, RuMiscOverworldPlacements.ASH_VENT,
                CountOnEveryLayerPlacement.of(7),
                BiomeFilter.biome(),
                notInStructurePredicate
        );
        register(context, RuMiscOverworldPlacements.BASALT_BLOB, CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());
        //OTHER_FEATURES
        register(context, RuMiscOverworldPlacements.MOSS_PATCH_WITH_WATER, MOSS_PATCH_WITH_WATER, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.MOSS_PATCH_WITH_WATER_UNCOMMON, MOSS_PATCH_WITH_WATER, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.MOSS_PATCH_WITH_WATER_RARE, MOSS_PATCH_WITH_WATER, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.MARSH, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.WATER_EDGE, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,  BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.ICICLE_UP, CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()) ;
        register(context, RuMiscOverworldPlacements.ROCK_ON_SNOW, rockMixedStone, CountPlacement.of(1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.NOISE_PUMPKINS, NoiseBasedCountPlacement.of(4, 40, 0.4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

        register(context, RuMiscOverworldPlacements.ROCK_GROUP_HIGHLAND_FIELDS, surfaceSpread(1, Heightmap.Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_ROCKY_MEADOW, surfaceSpread(1, Heightmap.Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_TEMPERATE_GROVE, surfaceSpread(0.5, Heightmap.Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_TUNDRA,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, -3, 2, 1, 0),
            rarityFilter(12),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        );

        final Holder<ConfiguredFeature<?, ?>> singlePieceOfGrass = getter.getOrThrow(VegetationFeatures.SINGLE_PIECE_OF_GRASS);
        PlacementModifier airCheck = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR));
        register(context, RUPlacedFeatures.BONEMEAL_ARGILLITE_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_CHALK_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_DEEPSLATE_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_PEAT_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_SILT_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_STONE_GRASS, singlePieceOfGrass, airCheck);
    }
    private static PlacementModifier[] orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return new PlacementModifier[] {
            placementModifier,
            InSquarePlacement.spread(),
            placementModifier1,
            BiomeFilter.biome()
        };
    }

    private static PlacementModifier[] commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }
}
