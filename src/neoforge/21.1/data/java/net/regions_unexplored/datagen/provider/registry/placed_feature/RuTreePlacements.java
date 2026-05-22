package net.regions_unexplored.datagen.provider.registry.placed_feature;

import dev.worldgen.lithostitched.api.worldgen.placementcondition.LithostitchedPlacementConditions;
import dev.worldgen.lithostitched.api.worldgen.placementmodifier.LithostitchedPlacementModifiers;
import dev.worldgen.lithostitched.api.worldgen.util.NoiseRouterTarget;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.datagen.provider.registry.RUPlacedFeatureBootstrap;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUPlacedFeatures;

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuTreePlacements {
    //-----------------------KEYS-----------------------//
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ALPHA_GROVE = group("alpha_grove");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ARID_MOUNTAINS = group("arid_mountains");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ASHEN_WOODLAND = group("ashen_woodland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_AUTUMNAL_MAPLE_FOREST = group("autumnal_maple_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAMBOO_FOREST_PRIMARY = group("bamboo_forest_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAMBOO_FOREST_SECONDARY = group("bamboo_forest_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAOBAB_SAVANNA = group("baobab_savanna");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAYOU = group("bayou");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BIOSHROOM_CAVES = group("bioshroom_caves");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY = group("blackwood_taiga_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY = group("blackwood_taiga_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY = group("blackwood_taiga_tertiary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BOREAL_TAIGA = group("boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_CHALK_CLIFFS = group("chalk_river");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_COLD_BOREAL_TAIGA = group("cold_boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_COLD_DECIDUOUS_FOREST = group("cold_deciduous_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_DRY_BUSHLAND = group("dry_bushland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_EUCALYPTUS_FOREST = group("eucalyptus_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_FEN = group("fen");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_FROZEN_PINE_TAIGA = group("frozen_pine_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_FUNGAL_FEN = group("fungal_fen");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_GRASSLAND = group("grassland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_GRASSY_BEACH = group("grassy_beach");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_HIGHLAND_FIELDS = group("highland_fields");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ICY_HEIGHTS = group("icy_heights");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_JOSHUA_DESERT = group("joshua_desert");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MAGNOLIA_WOODLAND = group("magnolia_woodland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MAPLE_FOREST = group("maple_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MARSH = group("marsh");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MOUNTAINS = group("mountains");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OLD_GROWTH_BAYOU = group("old_growth_bayou");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OLD_GROWTH_BOREAL_TAIGA = group("old_growth_boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OLD_GROWTH_FOREST = group("old_growth_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OLD_GROWTH_GOLDEN_BOREAL_TAIGA = group("old_growth_golden_boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ORCHARD = group("orchard");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OUTBACK = group("outback");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_SLOPES = group("pine_slopes");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_TAIGA_PRIMARY = group("pine_taiga_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_TAIGA_SECONDARY = group("pine_taiga_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_POPPY_FIELDS = group("poppy_fields");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PRAIRIE = group("prairie");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_RAINFOREST = group("rainforest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_PRIMARY = group("redwoods_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_SECONDARY = group("redwoods_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_TERTIARY = group("redwoods_tertiary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ROCKY_REEF = group("rocky_reef");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SAGUARO_DESERT = group("saguaro_desert");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SHRUBLAND = group("shrubland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SILVER_BIRCH_FOREST = group("silver_birch_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_RAINFOREST = group("sparse_rainforest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_REDWOODS_PRIMARY = group("sparse_redwoods_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_REDWOODS_SECONDARY = group("sparse_redwoods_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPIRES = group("spires");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TOWERING_CLIFFS = group("towering_cliffs");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TROPICAL_RIVER = group("tropical_river");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TROPICS = group("tropics");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TUNDRA = group("tundra");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TUNDRA_BUSHES = group("tundra_bushes");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_WINDSWEPT_MAPLE_FOREST = group("windswept_maple_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_WILLOW_FOREST = group("willow_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_WISTERIA_GROVE = group("wisteria_grove");

    public static final ResourceKey<PlacedFeature> ICE_SPIRE = key("ice_spire");
    

    private static ResourceKey<PlacedFeature> group(String name) {
        return key("group/" + name);
    }
    
    private static ResourceKey<PlacedFeature> vanillaGroup(String name) {
        return RUPlacedFeatures.vanilla("tree_group/" + name);
    }

    private static ResourceKey<PlacedFeature> key(String name) {
        return RegionsUnexplored.key(Registries.PLACED_FEATURE, "tree/" + name);
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);

        //---------------------FEATURES---------------------//
        var acaciaShrub = getter.getOrThrow(RUConfiguredFeatures.TREE_ACACIA_SHRUB);
        var iceSpire = getter.getOrThrow(RUConfiguredFeatures.TREE_ICE_SPIRE);
        
        //--------------------PLACEMENTS--------------------//
        register(context, RuTreePlacements.TREE_GROUP_BIOSHROOM_CAVES, placementCave(100, Direction.DOWN).filter(RUPlacedFeatureBootstrap.onViridescentNyliumPredicate));
        
        register(context, RuTreePlacements.TREE_GROUP_ALPHA_GROVE, getter.getOrThrow(RUConfiguredFeatures.TREE_ALPHA_OAK), simpleSpread(count(7), Blocks.OAK_SAPLING));

        register(context, TREE_GROUP_ASHEN_WOODLAND,
            treeDensity(-5, 8),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(saplingWouldSurvive(RUBlocks.ASHEN_NATURAL_SET)),
            BiomeFilter.biome()
        );

        register(context, RuTreePlacements.TREE_GROUP_BAMBOO_FOREST_PRIMARY, getter.getOrThrow(RUConfiguredFeatures.TREE_BAMBOO),
            treeDensity(-4, 6),
            count(3),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BlockPredicateFilter.forPredicate(saplingWouldSurvive(RUBlocks.BAMBOO_NATURAL_SET)),
            BiomeFilter.biome()
        );

        register(context, RuTreePlacements.TREE_GROUP_BAMBOO_FOREST_SECONDARY, getter.getOrThrow(VegetationFeatures.BAMBOO_SOME_PODZOL),
            treeDensity(3, 2),
            count(6),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(saplingWouldSurvive(RUBlocks.BAMBOO_NATURAL_SET)),
            BiomeFilter.biome()
        );
        
        register(context, RuTreePlacements.TREE_GROUP_BAOBAB_SAVANNA, placementTree(10, RUBlocks.BAOBAB_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_DRY_BUSHLAND, placementTree(6, RUBlocks.SOCOTRA_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_OUTBACK, acaciaShrub, placementTree(6, RUBlocks.SOCOTRA_NATURAL_SET));
        
        
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY, surfaceSpread(24, Types.OCEAN_FLOOR, RUBlocks.BLACKWOOD_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY, surfaceSpread(36, Types.OCEAN_FLOOR, RUBlocks.BLACKWOOD_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY, surfaceSpread(48, Types.OCEAN_FLOOR));
        
        register(context, RuTreePlacements.TREE_GROUP_OLD_GROWTH_BAYOU, placement(4, Types.OCEAN_FLOOR).maxWaterDepth(2).filter(RUBlocks.CYPRESS_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_BAYOU, placement(4, Types.OCEAN_FLOOR).maxWaterDepth(2).filter(RUBlocks.CYPRESS_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_MARSH, placementTree(0.1f, Blocks.OAK_SAPLING));
        
        register(context, RuTreePlacements.TREE_GROUP_FEN, placementTree(9, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_EUCALYPTUS_FOREST, placementTree(24, RUBlocks.EUCALYPTUS_NATURAL_SET));

        register(context, RuTreePlacements.TREE_GROUP_JOSHUA_DESERT,
            count(4),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(RUBlocks.JOSHUA_NATURAL_SET.getSapling()),
            BiomeFilter.biome()
        );

        register(context, RuTreePlacements.TREE_GROUP_RAINFOREST, placementTree(18, RUBlocks.KAPOK_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_SPARSE_RAINFOREST, placementTree(6, RUBlocks.KAPOK_NATURAL_SET));

        register(context, RuTreePlacements.TREE_GROUP_AUTUMNAL_MAPLE_FOREST,
            NoiseThresholdCountPlacement.of(0, 5, 8),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                saplingWouldSurvive(RUBlocks.MAPLE_NATURAL_SET),
                RUPlacedFeatureBootstrap.onGrassBlockPredicate
            )),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_OLD_GROWTH_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_OLD_GROWTH_GOLDEN_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_COLD_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_MAPLE_FOREST, surfaceSpread(8, Types.OCEAN_FLOOR, RUBlocks.MAPLE_NATURAL_SET.getSapling()));
        
        register(context, RuTreePlacements.TREE_GROUP_ORCHARD, placementTree(3, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_COLD_DECIDUOUS_FOREST, placementSnowyTree(10, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_OLD_GROWTH_FOREST, placementTree(10, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_ARID_MOUNTAINS, placementTree(1, Blocks.GRASS_BLOCK));
        register(context, RuTreePlacements.TREE_GROUP_GRASSLAND, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_GRASSY_BEACH, placementTree(0.2f, RUBlocks.PALM_NATURAL_SET).add(LithostitchedPlacementModifiers.condition(LithostitchedPlacementConditions.sampleNoiseRouter(NoiseRouterTarget.TEMPERATURE, new InclusiveRange<>(0.2, 0.55)))));
        
        register(context, RuTreePlacements.TREE_GROUP_CHALK_CLIFFS, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_TROPICAL_RIVER, placement(1, Types.OCEAN_FLOOR).notSubmerged().filter(RUBlocks.PALM_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_ROCKY_REEF, placement(32, Types.OCEAN_FLOOR).notSubmerged().filter(RUBlocks.PALM_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_TROPICS, placementTree(7, RUBlocks.PALM_NATURAL_SET));
        
        register(context, RuTreePlacements.TREE_GROUP_PINE_TAIGA_PRIMARY,
            count(16),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                saplingWouldSurvive(RUBlocks.PINE_NATURAL_SET),
                DIRT_OR_PODZOL_BELOW
            )),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_HIGHLAND_FIELDS,
            rarityFilter(64),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(saplingWouldSurvive(RUBlocks.PINE_NATURAL_SET)),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_MOUNTAINS, placementTree(13, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_PINE_SLOPES, placementTree(11, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_PINE_TAIGA_SECONDARY, placementTree(2, RUBlocks.PINE_NATURAL_SET).filter(RUPlacedFeatureBootstrap.onGrassBlockPredicate));
        register(context, RuTreePlacements.TREE_GROUP_TOWERING_CLIFFS, placementTree(2, RUBlocks.PINE_NATURAL_SET));
        
        
        register(context, RuTreePlacements.TREE_GROUP_ICY_HEIGHTS, placementSnowyTree(4, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_FROZEN_PINE_TAIGA, placementSnowyTree(10, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_FUNGAL_FEN, placementTree(4, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_WINDSWEPT_MAPLE_FOREST, placementTree(4, Blocks.OAK_SAPLING));
        
        register(context, RuTreePlacements.TREE_GROUP_REDWOODS_PRIMARY,
            treeDensity(2, 2),
            count(4),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(saplingWouldSurvive(RUBlocks.REDWOOD_NATURAL_SET)),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_REDWOODS_SECONDARY,
            treeDensity(-2, 6),
            count(4),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                saplingWouldSurvive(RUBlocks.REDWOOD_NATURAL_SET),
                BlockPredicate.not(BlockPredicate.anyOf(
                    BlockPredicate.matchesBlocks(Vec3i.ZERO.above().north(), RUBlocks.REDWOOD_WOOD_SET.getLog()),
                    BlockPredicate.matchesBlocks(Vec3i.ZERO.above().south(), RUBlocks.REDWOOD_WOOD_SET.getLog()),
                    BlockPredicate.matchesBlocks(Vec3i.ZERO.above().east(), RUBlocks.REDWOOD_WOOD_SET.getLog()),
                    BlockPredicate.matchesBlocks(Vec3i.ZERO.above().west(), RUBlocks.REDWOOD_WOOD_SET.getLog())
                ))
            )),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_REDWOODS_TERTIARY, placementTree(1, Blocks.OAK_SAPLING));

        register(context, RuTreePlacements.TREE_GROUP_SPARSE_REDWOODS_PRIMARY, placement().count(NoiseBasedCountPlacement.of(1, 80.0D, 0.3D)).notSubmerged().heightmap(Types.OCEAN_FLOOR).filter(Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_SPARSE_REDWOODS_SECONDARY, placementTree(4, Blocks.OAK_SAPLING));

        register(context, TREE_GROUP_POPPY_FIELDS, surfaceSpread(0.0666, Types.OCEAN_FLOOR_WG, RUBlocks.MAGNOLIA_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_MAGNOLIA_WOODLAND, surfaceSpread(3, Types.OCEAN_FLOOR_WG, RUBlocks.MAGNOLIA_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_PRAIRIE, placement()
            .count(LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, 4, -5, 1.5, 0))
            .notSubmerged()
            .heightmap(Types.OCEAN_FLOOR)
            .filter(Blocks.OAK_SAPLING)
        );
        
        register(context, RuTreePlacements.TREE_GROUP_SAGUARO_DESERT, placement()
            .count(NoiseBasedCountPlacement.of(1, 75.0D, 0.0D))
            .notSubmerged()
            .heightmap(Types.OCEAN_FLOOR)
            .filter(RUBlocks.SAGUARO_CACTUS_NATURAL_SET.getSapling())
        );
        
        register(context, TREE_GROUP_TUNDRA,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, 5, -6, 1, 0),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING),
            BiomeFilter.biome()
        );
        
        register(context, TREE_GROUP_TUNDRA_BUSHES,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, -3, -1, 1, 0),
            rarityFilter(3),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
            BiomeFilter.biome()
        );
        
        register(context, RuTreePlacements.TREE_GROUP_SPIRES, placementSnowyTree(4, Blocks.SPRUCE_SAPLING));
        
        register(context, RuTreePlacements.ICE_SPIRE, iceSpire, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));

        register(context, TREE_GROUP_SILVER_BIRCH_FOREST,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, -8, 6, 2, 0),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING),
            BiomeFilter.biome()
        );
        
        register(context, TREE_GROUP_SHRUBLAND, placementTree(4, Blocks.OAK_SAPLING));
        register(context, TREE_GROUP_WILLOW_FOREST, placementTree(4, RUBlocks.WILLOW_NATURAL_SET));
        register(context, TREE_GROUP_WISTERIA_GROVE, surfaceSpread(3, Types.OCEAN_FLOOR, Blocks.CHERRY_SAPLING));
    }

    protected static PlacementModifier treeDensity(int slope, int offset) {
        return LithostitchedPlacementModifiers.noiseSlope(RUNoises.TREE_DENSITY, slope, offset, 1, 0);
    }
    
    protected static PlacementModifier[] simpleSpread(PlacementModifier count, Block sapling) {
        return new PlacementModifier[] {
            count,
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(sapling),
            BiomeFilter.biome()
        };
    }

    private static BlockPredicate saplingWouldSurvive(NaturalSet set) {
        return BlockPredicate.wouldSurvive(set.getSapling().defaultBlockState(), Vec3i.ZERO);
    }
}