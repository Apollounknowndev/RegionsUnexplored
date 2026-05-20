package net.regions_unexplored.datagen.provider.registry.placed_feature;

import dev.worldgen.lithostitched.api.worldgen.blockpredicate.LithostitchedBlockPredicates;
import dev.worldgen.lithostitched.api.worldgen.placementmodifier.LithostitchedPlacementModifiers;
import dev.worldgen.lithostitched.impl.predicate.NotPredicate;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.levelgen.Heightmap.Types;
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

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;
import static net.regions_unexplored.registry.data.RUPlacedFeatures.*;

public class RuMiscOverworldPlacements {
    //FALLEN_TREES
    public static final ResourceKey<PlacedFeature> FALLEN_LARCH = key("tree/fallen/larch");
    public static final ResourceKey<PlacedFeature> FALLEN_MAPLE = key("tree/fallen/maple");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_SPARSE = key("tree/fallen/oak_sparse");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_DENSE = key("tree/fallen/oak_dense");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE = key("tree/fallen/pine");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE_ON_DIRT = key("tree/fallen/pine_on_dirt");
    public static final ResourceKey<PlacedFeature> FALLEN_PINE_ON_SNOW = key("tree/fallen/pine_on_snow");
    public static final ResourceKey<PlacedFeature> FALLEN_SILVER_BIRCH = key("tree/fallen/silver_birch");
    //CAVE_FEATURES
    public static final ResourceKey<PlacedFeature> SPECIAL_POINTED_REDSTONE = key("special/pointed_redstone");
    public static final ResourceKey<PlacedFeature> SPECIAL_LARGE_POINTED_REDSTONE = key("special/large_pointed_redstone");
    public static final ResourceKey<PlacedFeature> SPECIAL_POINTED_REDSTONE_CLUSTER = key("special/pointed_redstone_cluster");
    public static final ResourceKey<PlacedFeature> SPECIAL_ORE_REDSTONE_LARGE = key("special/ore_redstone_large");
    public static final ResourceKey<PlacedFeature> PATCH_PRISMARITE_CLUSTER = patch("prismarite_cluster");
    public static final ResourceKey<PlacedFeature> SPECIAL_HANGING_PRISMARITE_CLUSTER = key("special/hanging_prismarite_cluster");
    public static final ResourceKey<PlacedFeature> SPECIAL_LAVA_FALL = key("special/lava_fall");
    public static final ResourceKey<PlacedFeature> SPECIAL_INFERNO_LAVA_DELTA = key("special/overworld_lava_delta");
    public static final ResourceKey<PlacedFeature> PATCH_ASH_VENTS_INFERNO = key("patch/ash_vents_inferno");
    public static final ResourceKey<PlacedFeature> SPECIAL_BASALT_BLOB = key("special/basalt_blob");
    //OTHER_FEATURES
    public static final ResourceKey<PlacedFeature> SPECIAL_CALCITE_POOL = key("special/calcite_pool");

    public static final ResourceKey<PlacedFeature> SPECIAL_MOSS_PATCH_WITH_WATER_DENSE = key("special/moss_patch_with_water_dense");
    public static final ResourceKey<PlacedFeature> SPECIAL_MOSS_PATCH_WITH_WATER = key("special/moss_patch_with_water");
    public static final ResourceKey<PlacedFeature> SPECIAL_MOSS_PATCH_WITH_WATER_SPARSE = key("special/moss_patch_with_water_sparse");
    public static final ResourceKey<PlacedFeature> SPECIAL_MARSH = key("special/marsh");
    public static final ResourceKey<PlacedFeature> SPECIAL_CARVED_LIMITED_POOL = key("special/carved_limited_pool");
    public static final ResourceKey<PlacedFeature> SPECIAL_WATER_EDGE = key("special/water_edge");
    public static final ResourceKey<PlacedFeature> SPECIAL_ICICLE_UP = key("special/icicle_up");
    public static final ResourceKey<PlacedFeature> PATCH_SILT_PODZOL_PUMPKINS = patch("silt_podzol_pumpkins");

    public static final ResourceKey<PlacedFeature> ROCK_GROUP_ICY_HEIGHTS = key("rock/group/icy_heights");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_HIGHLAND_FIELDS = key("rock/group/highland_fields");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_ROCKY_MEADOW = key("rock/group/rocky_meadow");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_WINDSWEPT_MAPLE_FOREST = key("rock/group/windswept_maple_forest");
    public static final ResourceKey<PlacedFeature> ROCK_GROUP_TUNDRA = key("rock/group/tundra");
    
    private static final BlockPredicateFilter NOT_IN_STRUCTURE = BlockPredicateFilter.forPredicate(BlockPredicate.not(LithostitchedBlockPredicates.inStructure(4)));

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        
        var fallenOak = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_OAK);
        var fallenPine = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_PINE);
        var fallenSnowPine = getter.getOrThrow(RuMiscOverworldFeatures.FALLEN_SNOW_PINE);
        var mossPatchWithWater = getter.getOrThrow(RuMiscOverworldFeatures.SPECIAL_MOSS_PATCH_WITH_WATER);
        var singlePieceOfGrass = getter.getOrThrow(VegetationFeatures.SINGLE_PIECE_OF_GRASS);
	    
	    
	    
	    register(context, RuMiscOverworldPlacements.FALLEN_LARCH, surfaceSpread(0.333, Types.OCEAN_FLOOR, RUBlocks.LARCH_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_SILVER_BIRCH, surfaceSpread(0.5, Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_MAPLE, surfaceSpread(0.5, Types.OCEAN_FLOOR, RUBlocks.MAPLE_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_OAK_SPARSE, fallenOak, surfaceSpread(0.16, Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_OAK_DENSE, fallenOak, surfaceSpread(0.5, Types.OCEAN_FLOOR, Blocks.BIRCH_SAPLING));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE, surfaceSpread(0.5, Types.OCEAN_FLOOR, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE_ON_DIRT, fallenPine, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onDirtPredicate), BiomeFilter.biome()));
        register(context, RuMiscOverworldPlacements.FALLEN_PINE_ON_SNOW, fallenSnowPine, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));

        register(context, RuMiscOverworldPlacements.SPECIAL_POINTED_REDSTONE, CountPlacement.of(UniformInt.of(192, 256)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, CountPlacement.of(UniformInt.of(1, 5)), RandomOffsetPlacement.of(ClampedNormalInt.of(0.0F, 3.0F, -10, 10), ClampedNormalInt.of(0.0F, 0.6F, -2, 2)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_LARGE_POINTED_REDSTONE, CountPlacement.of(UniformInt.of(10, 48)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_POINTED_REDSTONE_CLUSTER, CountPlacement.of(UniformInt.of(78, 126)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_ORE_REDSTONE_LARGE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(112))));

        register(context, RuMiscOverworldPlacements.PATCH_PRISMARITE_CLUSTER, placementCave(75, Direction.DOWN).notInStructure());
        register(context, RuMiscOverworldPlacements.SPECIAL_HANGING_PRISMARITE_CLUSTER, CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

        register(context, RuMiscOverworldPlacements.SPECIAL_CALCITE_POOL, CountPlacement.of(70), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());

        register(context, RuMiscOverworldPlacements.SPECIAL_LAVA_FALL, placement().count(100)
            .add(PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT)
            .add(NOT_IN_STRUCTURE)
        );
        register(context, RuMiscOverworldPlacements.SPECIAL_INFERNO_LAVA_DELTA, cavePlacement(100, Direction.DOWN, false));
        register(context, RuMiscOverworldPlacements.PATCH_ASH_VENTS_INFERNO, cavePlacement(100, Direction.DOWN, true));
        
        register(context, RuMiscOverworldPlacements.SPECIAL_BASALT_BLOB, CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());

        register(context, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER_DENSE, mossPatchWithWater, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.MUD))), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER, mossPatchWithWater, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER_SPARSE, mossPatchWithWater, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_MARSH, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.MUD))), BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_CARVED_LIMITED_POOL, BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_WATER_EDGE, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,  BiomeFilter.biome());
        register(context, RuMiscOverworldPlacements.SPECIAL_ICICLE_UP, CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()) ;
        register(context, RuMiscOverworldPlacements.PATCH_SILT_PODZOL_PUMPKINS, placement(0.5f, Types.MOTION_BLOCKING));

        register(context, RuMiscOverworldPlacements.ROCK_GROUP_ICY_HEIGHTS, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_HIGHLAND_FIELDS, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_ROCKY_MEADOW, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_WINDSWEPT_MAPLE_FOREST, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));
        register(context, RuMiscOverworldPlacements.ROCK_GROUP_TUNDRA,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, -3, 2, 1, 0),
            rarityFilter(12),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        );
        PlacementModifier airCheck = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR));
        register(context, RUPlacedFeatures.BONEMEAL_ARGILLITE_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_CHALK_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_DEEPSLATE_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_PEAT_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_SILT_GRASS, singlePieceOfGrass, airCheck);
        register(context, RUPlacedFeatures.BONEMEAL_STONE_GRASS, singlePieceOfGrass, airCheck);
    }
    
    private static PlacementBuilder cavePlacement(int count, Direction direction, boolean offset) {
        PlacementBuilder builder = placement().count(count)
            .add(PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT)
            .add(EnvironmentScanPlacement.scanningFor(direction, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12));
        if (offset) {
            builder.add(RandomOffsetPlacement.vertical(ConstantInt.of(-direction.getStepY())));
        }
        return builder.add(NOT_IN_STRUCTURE);
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
