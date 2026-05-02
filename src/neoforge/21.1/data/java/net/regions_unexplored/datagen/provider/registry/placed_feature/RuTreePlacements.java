package net.regions_unexplored.datagen.provider.registry.placed_feature;

import dev.worldgen.lithostitched.api.worldgen.blockpredicate.LithostitchedBlockPredicates;
import dev.worldgen.lithostitched.api.worldgen.placementmodifier.LithostitchedPlacementModifiers;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
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

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuTreePlacements {
    //-----------------------KEYS-----------------------//
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BIOSHROOM_CAVES = group("bioshroom_caves");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_ALPHA_GROVE = group("alpha_grove");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_ORCHARD = key("orchard");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_ASHEN_WOODLAND = group("ashen_woodland");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAMBOO_FOREST_PRIMARY = group("bamboo_forest_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAMBOO_FOREST_SECONDARY = group("bamboo_forest_secondary");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAOBAB_SAVANNA = group("baobab_savanna");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_DRY_BUSHLAND = group("dry_bushland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OUTBACK = group("outback");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY = group("blackwood_taiga_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY = group("blackwood_taiga_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY = group("blackwood_taiga_tertiary");

    public static final ResourceKey<PlacedFeature> CYPRESS = key("cypress");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BAYOU = group("bayou");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_OLD_GROWTH_BAYOU = group("old_growth_bayou");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_MARSH = group("marsh");
    public static final ResourceKey<PlacedFeature> DEAD_BOG = key("dead_bog");

    public static final ResourceKey<PlacedFeature> DEAD_SCOTTS_PINE = key("dead_scotts_pine");
    public static final ResourceKey<PlacedFeature> DEAD_SCOTTS_PINE_MOUNTAIN = key("dead_scotts_pine_mountain");
    public static final ResourceKey<PlacedFeature> DEAD_SCOTTS_PINE_MOUNTAIN_ON_SNOW = key("dead_scotts_pine_mountain_on_snow");

    public static final ResourceKey<PlacedFeature> BIRCH_ASPEN = key("birch_aspen");
    
    public static final ResourceKey<PlacedFeature> SMALL_EUCALYPTUS = key("small_eucalyptus");
    public static final ResourceKey<PlacedFeature> EUCALYPTUS = key("eucalyptus");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_EUCALYPTUS_FOREST = group("eucalyptus_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_JOSHUA_DESERT = group("joshua_desert");

    public static final ResourceKey<PlacedFeature> BIG_JUNGLE_DENSE = key("big_jungle_dense");

    public static final ResourceKey<PlacedFeature> KAPOK_SPARSE = key("kapok_sparse");
    public static final ResourceKey<PlacedFeature> KAPOK_DENSE = key("kapok_dense");

    public static final ResourceKey<PlacedFeature> LARCH_SPARSE = key("larch_sparse");
    public static final ResourceKey<PlacedFeature> LARCH_DENSE = key("larch_dense");
    public static final ResourceKey<PlacedFeature> GOLDEN_LARCH_SPARSE = key("golden_larch_sparse");
    public static final ResourceKey<PlacedFeature> GOLDEN_LARCH_DENSE = key("golden_larch_dense");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_COLD_BOREAL_TAIGA = group("cold_boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_BOREAL_TAIGA = group("boreal_taiga");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_GOLDEN_BOREAL_TAIGA = group("golden_boreal_taiga");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_AUTUMNAL_MAPLE_FOREST = group("autumnal_maple_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PUMPKIN_FIELDS = group("pumpkin_fields");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MAPLE_FOREST = group("maple_forest");
    public static final ResourceKey<PlacedFeature> BIG_RED_MAPLE_SPARSE = key("big_red_maple_sparse");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_COLD_DECIDUOUS_FOREST = group("cold_deciduous_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_DECIDUOUS_FOREST = group("deciduous_forest");
    public static final ResourceKey<PlacedFeature> BIG_OAK_SPARSE = key("big_oak_sparse");
    public static final ResourceKey<PlacedFeature> OAK_WITH_BRANCH = key("oak_with_branch");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ARID_MOUNTAINS = group("arid_mountains");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_GRASSLAND = group("grassland");
    public static final ResourceKey<PlacedFeature> OAK_SHRUB_DENSE = key("oak_shrub_dense");
    public static final ResourceKey<PlacedFeature> OAK_BUSH_SPARSE = key("oak_bush_sparse");
    public static final ResourceKey<PlacedFeature> OAK_BUSH_DENSE = key("oak_bush_dense");
    public static final ResourceKey<PlacedFeature> OAK_BUSH_WITH_FLOWERS_DENSE = key("oak_bush_with_flowers_dense");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_RAINFOREST = group("rainforest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_RAINFOREST = group("sparse_rainforest");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_WILLOW_FOREST = group("willow_forest");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_CHALK_CLIFFS = group("chalk_river");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TROPICAL_RIVER = group("tropical_river");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TROPICS = group("tropics");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ROCKY_REEF = group("rocky_reef");
    public static final ResourceKey<PlacedFeature> PALM_DENSE = key("palm_dense");
    public static final ResourceKey<PlacedFeature> PALM_DENSE_TALL = key("palm_dense_tall");
    public static final ResourceKey<PlacedFeature> PALM_SHRUB = key("palm_shrub");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_FEN = group("fen");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_ICY_HEIGHTS = group("icy_heights");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_TAIGA_PRIMARY = group("pine_taiga_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_TAIGA_SECONDARY = group("pine_taiga_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_HIGHLAND_FIELDS = group("highland_fields");
    public static final ResourceKey<PlacedFeature> PINE = key("pine");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MOUNTAINS = group("mountains");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TOWERING_CLIFFS = group("towering_cliffs");
    public static final ResourceKey<PlacedFeature> SCOTTS_PINE_MOUNTAIN = key("scotts_pine_mountain");
    public static final ResourceKey<PlacedFeature> SCOTTS_PINE_ON_SNOW = key("scotts_pine_on_snow");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PINE_SLOPES = group("pine_slopes");
    public static final ResourceKey<PlacedFeature> SCOTTS_PINE_TALL_ON_SNOW = key("scotts_pine_tall_on_snow");
    public static final ResourceKey<PlacedFeature> SCOTTS_PINE_MOUNTAIN_ON_SNOW = key("scotts_pine_mountain_on_snow");
    public static final ResourceKey<PlacedFeature> PINE_SHRUB_ON_SNOW = key("pine_shrub_on_snow");
    public static final ResourceKey<PlacedFeature> PINE_SHRUB_ON_SNOW_SPARSE = key("pine_shrub_on_snow_sparse");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_FROZEN_PINE_TAIGA = group("frozen_pine_taiga");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_FUNGAL_FEN = group("fungal_fen");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_PRIMARY = group("redwoods_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_SECONDARY = group("redwoods_secondary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_REDWOODS_TERTIARY = group("redwoods_tertiary");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_REDWOODS_PRIMARY = key("sparse_redwoods_primary");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPARSE_REDWOODS_SECONDARY = key("sparse_redwoods_secondary");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_POPPY_FIELDS = group("poppy_fields");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MAGNOLIA_WOODLAND = group("magnolia_woodland");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_PRAIRIE = group("prairie");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_SAGUARO_DESERT = group("saguaro_desert");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TEMPERATE_GROVE = group("temperate_grove");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_SILVER_BIRCH_FOREST = group("silver_birch_forest");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TUNDRA = group("tundra");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_TUNDRA_BUSHES = group("tundra_bushes");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SPIRES = group("spires");
    public static final ResourceKey<PlacedFeature> SPRUCE_TALL_SPARSE = key("spruce_tall_sparse");
    public static final ResourceKey<PlacedFeature> SPRUCE_SHRUB_DENSE = key("spruce_shrub_dense");
    public static final ResourceKey<PlacedFeature> TREE_GROUP_SHRUBLAND = group("shrubland");

    public static final ResourceKey<PlacedFeature> ICE_SPIRE = key("ice_spire");

    public static final ResourceKey<PlacedFeature> WILLOW_VINES = key("willow_vines");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_WISTERIA_GROVE = group("wisteria_grove");

    private static ResourceKey<PlacedFeature> group(String name) {
        return key("group/" + name);
    }

    private static ResourceKey<PlacedFeature> key(String name) {
        return RegionsUnexplored.key(Registries.PLACED_FEATURE, "tree/" + name);
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);

        //---------------------FEATURES---------------------//
        var oakShrub = getter.getOrThrow(RUConfiguredFeatures.TREE_OAK_SHRUB_SMALL);
        var acaciaShrub = getter.getOrThrow(RUConfiguredFeatures.TREE_ACACIA_SHRUB);

        final Holder<ConfiguredFeature<?, ?>> BIRCH_ASPEN = getter.getOrThrow(RUConfiguredFeatures.TREE_BIRCH_ASPEN);

        final Holder<ConfiguredFeature<?, ?>> CYPRESS = getter.getOrThrow(RUConfiguredFeatures.TREE_CYPRESS);

        final Holder<ConfiguredFeature<?, ?>> DEAD_BOG = getter.getOrThrow(RUConfiguredFeatures.TREE_DEAD_BOG);

        final Holder<ConfiguredFeature<?, ?>> DEAD_SCOTTS_PINE = getter.getOrThrow(RUConfiguredFeatures.TREE_DEAD_STRIPPED_PINE);
        final Holder<ConfiguredFeature<?, ?>> DEAD_SCOTTS_PINE_MOUNTAIN = getter.getOrThrow(RUConfiguredFeatures.TREE_DEAD_STRIPPED_PINE_MOUNTAIN);

        final Holder<ConfiguredFeature<?, ?>> SMALL_EUCALYPTUS = getter.getOrThrow(RUConfiguredFeatures.TREE_SMALL_EUCALYPTUS);
        final Holder<ConfiguredFeature<?, ?>> EUCALYPTUS = getter.getOrThrow(RUConfiguredFeatures.TREE_EUCALYPTUS);

        final Holder<ConfiguredFeature<?, ?>> BIG_JUNGLE = getter.getOrThrow(RUConfiguredFeatures.TREE_BIG_JUNGLE);

        final Holder<ConfiguredFeature<?, ?>> KAPOK = getter.getOrThrow(RUConfiguredFeatures.TREE_KAPOK);

        final Holder<ConfiguredFeature<?, ?>> LARCH = getter.getOrThrow(RUConfiguredFeatures.TREE_LARCH);
        final Holder<ConfiguredFeature<?, ?>> GOLDEN_LARCH = getter.getOrThrow(RUConfiguredFeatures.TREE_GOLDEN_LARCH);

        final Holder<ConfiguredFeature<?, ?>> BIG_RED_MAPLE = getter.getOrThrow(RUConfiguredFeatures.TREE_BIG_RED_MAPLE);

        final Holder<ConfiguredFeature<?, ?>> BIG_OAK = getter.getOrThrow(RUConfiguredFeatures.TREE_BIG_OAK);
        final Holder<ConfiguredFeature<?, ?>> OAK_WITH_BRANCH = getter.getOrThrow(RUConfiguredFeatures.TREE_OAK_WITH_BRANCH);
        final Holder<ConfiguredFeature<?, ?>> OAK_BUSH_WITH_FLOWERS = getter.getOrThrow(RUConfiguredFeatures.TREE_OAK_BUSH_WITH_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> OAK_BUSH = getter.getOrThrow(RUConfiguredFeatures.TREE_OAK_BUSH);

        final Holder<ConfiguredFeature<?, ?>> PALM = getter.getOrThrow(RUConfiguredFeatures.TREE_PALM);
        final Holder<ConfiguredFeature<?, ?>> PALM_TALL = getter.getOrThrow(RUConfiguredFeatures.TREE_TALL_PALM);
        final Holder<ConfiguredFeature<?, ?>> PALM_SHRUB = getter.getOrThrow(RUConfiguredFeatures.TREE_PALM_SHRUB);

        final Holder<ConfiguredFeature<?, ?>> PINE = getter.getOrThrow(RUConfiguredFeatures.TREE_PINE);
        final Holder<ConfiguredFeature<?, ?>> SCOTTS_PINE = getter.getOrThrow(RUConfiguredFeatures.TREE_STRIPPED_PINE);
        final Holder<ConfiguredFeature<?, ?>> SCOTTS_PINE_TALL = getter.getOrThrow(RUConfiguredFeatures.TREE_STRIPPED_PINE_TALL);
        final Holder<ConfiguredFeature<?, ?>> SCOTTS_PINE_MOUNTAIN = getter.getOrThrow(RUConfiguredFeatures.TREE_STRIPPED_PINE_MOUNTAIN);
        final Holder<ConfiguredFeature<?, ?>> PINE_SHRUB = getter.getOrThrow(RUConfiguredFeatures.TREE_PINE_SHRUB);

        final Holder<ConfiguredFeature<?, ?>> SPRUCE_TALL = getter.getOrThrow(RUConfiguredFeatures.TREE_SPRUCE_TALL);
        final Holder<ConfiguredFeature<?, ?>> SPRUCE_SHRUB = getter.getOrThrow(RUConfiguredFeatures.TREE_SPRUCE_SHRUB);

        final Holder<ConfiguredFeature<?, ?>> ICE_SPIRE = getter.getOrThrow(RUConfiguredFeatures.TREE_ICE_SPIRE);

        final Holder<ConfiguredFeature<?, ?>> WILLOW_VINES = getter.getOrThrow(RUConfiguredFeatures.TREE_WILLOW_VINES);
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
        
        register(context, RuTreePlacements.BIRCH_ASPEN, BIRCH_ASPEN, placementTree(1, Blocks.OAK_SAPLING));
        
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY, surfaceSpread(24, Types.OCEAN_FLOOR, RUBlocks.BLACKWOOD_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY, surfaceSpread(36, Types.OCEAN_FLOOR, RUBlocks.BLACKWOOD_NATURAL_SET.getSapling()));
        register(context, TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY, surfaceSpread(48, Types.OCEAN_FLOOR));
        
        register(context, RuTreePlacements.CYPRESS, CYPRESS, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(1), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), BiomeFilter.biome()));
        register(context, RuTreePlacements.TREE_GROUP_OLD_GROWTH_BAYOU, placement(4, Types.OCEAN_FLOOR).maxWaterDepth(2).filter(RUBlocks.CYPRESS_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_BAYOU, placement(4, Types.OCEAN_FLOOR).maxWaterDepth(2).filter(RUBlocks.CYPRESS_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_MARSH, placementTree(0.1f, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.DEAD_BOG, DEAD_BOG, List.of(CountPlacement.of(1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));

        register(context, RuTreePlacements.DEAD_SCOTTS_PINE, DEAD_SCOTTS_PINE, placementTree(2, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.DEAD_SCOTTS_PINE_MOUNTAIN, DEAD_SCOTTS_PINE_MOUNTAIN, List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuTreePlacements.DEAD_SCOTTS_PINE_MOUNTAIN_ON_SNOW, DEAD_SCOTTS_PINE_MOUNTAIN, List.of(PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));


        register(context, RuTreePlacements.SMALL_EUCALYPTUS, SMALL_EUCALYPTUS, placementTree(9, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.EUCALYPTUS, EUCALYPTUS, placementTree(9, Blocks.OAK_SAPLING));
        
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

        register(context, RuTreePlacements.BIG_JUNGLE_DENSE, BIG_JUNGLE, placementTree(4, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_RAINFOREST, placementTree(18, RUBlocks.KAPOK_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_SPARSE_RAINFOREST, placementTree(6, RUBlocks.KAPOK_NATURAL_SET));

        register(context, RuTreePlacements.KAPOK_SPARSE, KAPOK, placementTree(2, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.KAPOK_DENSE, KAPOK, placementTree(4, Blocks.OAK_SAPLING));

        register(context, RuTreePlacements.LARCH_DENSE, LARCH, placementTree(7, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.LARCH_SPARSE, LARCH, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.GOLDEN_LARCH_DENSE, GOLDEN_LARCH, placementTree(7, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.GOLDEN_LARCH_SPARSE, GOLDEN_LARCH, placementTree(1, Blocks.OAK_SAPLING));

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
        register(context, RuTreePlacements.TREE_GROUP_PUMPKIN_FIELDS,
            CountPlacement.of(6),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                saplingWouldSurvive(RUBlocks.MAPLE_NATURAL_SET),
                BlockPredicate.anyOf(
                    RUPlacedFeatureBootstrap.onGrassBlockPredicate,
                    LithostitchedBlockPredicates.randomChance(0.02f)
                )
            )),
            BiomeFilter.biome()
        );
        register(context, RuTreePlacements.TREE_GROUP_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_GOLDEN_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_COLD_BOREAL_TAIGA, placementTree(10, RUBlocks.LARCH_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_MAPLE_FOREST, surfaceSpread(8, Types.OCEAN_FLOOR, RUBlocks.MAPLE_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.BIG_RED_MAPLE_SPARSE, BIG_RED_MAPLE, placementTree(2, Blocks.OAK_SAPLING));

        register(context, RuTreePlacements.TREE_GROUP_ORCHARD, placementTree(4, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_COLD_DECIDUOUS_FOREST, placementSnowyTree(10, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_DECIDUOUS_FOREST, placementTree(10, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.BIG_OAK_SPARSE, BIG_OAK, placementTree(2, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.OAK_WITH_BRANCH, OAK_WITH_BRANCH, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_ARID_MOUNTAINS, placementTree(1, Blocks.GRASS_BLOCK));
        register(context, RuTreePlacements.TREE_GROUP_GRASSLAND, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.OAK_SHRUB_DENSE, oakShrub, placementTree(3, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.OAK_BUSH_SPARSE, OAK_BUSH, placementTree(4, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.OAK_BUSH_DENSE, OAK_BUSH, placementTree(8, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.OAK_BUSH_WITH_FLOWERS_DENSE, OAK_BUSH_WITH_FLOWERS, placementTree(7, Blocks.OAK_SAPLING));

        register(context, RuTreePlacements.TREE_GROUP_CHALK_CLIFFS, placementTree(1, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_TROPICAL_RIVER, placement(1, Types.OCEAN_FLOOR).notSubmerged().filter(RUBlocks.PALM_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_ROCKY_REEF, placement(32, Types.OCEAN_FLOOR).notSubmerged().filter(RUBlocks.PALM_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_TROPICS, placementTree(7, RUBlocks.PALM_NATURAL_SET));
        register(context, RuTreePlacements.PALM_DENSE, PALM, placementTree(4, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.PALM_DENSE_TALL, PALM_TALL, placementTree(4, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.PALM_SHRUB, PALM_SHRUB, placementTree(1, Blocks.OAK_SAPLING));

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
        register(context, RuTreePlacements.PINE, PINE, placementTree(6, Blocks.OAK_SAPLING));
        register(context, RuTreePlacements.TREE_GROUP_MOUNTAINS, placementTree(13, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_PINE_SLOPES, placementTree(11, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.SCOTTS_PINE_MOUNTAIN, SCOTTS_PINE_MOUNTAIN, List.of(PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuTreePlacements.TREE_GROUP_PINE_TAIGA_SECONDARY, placementTree(2, RUBlocks.PINE_NATURAL_SET).filter(RUPlacedFeatureBootstrap.onGrassBlockPredicate));
        register(context, RuTreePlacements.TREE_GROUP_TOWERING_CLIFFS, placementTree(2, RUBlocks.PINE_NATURAL_SET));
        
        register(context, RuTreePlacements.SCOTTS_PINE_ON_SNOW, SCOTTS_PINE, List.of(CountPlacement.of(9), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));
        register(context, RuTreePlacements.SCOTTS_PINE_TALL_ON_SNOW, SCOTTS_PINE_TALL, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));
        register(context, RuTreePlacements.SCOTTS_PINE_MOUNTAIN_ON_SNOW, SCOTTS_PINE_MOUNTAIN, List.of(PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));
        register(context, RuTreePlacements.PINE_SHRUB_ON_SNOW, PINE_SHRUB, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));
        register(context, RuTreePlacements.PINE_SHRUB_ON_SNOW_SPARSE, PINE_SHRUB, List.of(CountPlacement.of(1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));

        register(context, RuTreePlacements.TREE_GROUP_ICY_HEIGHTS, placementSnowyTree(4, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_FROZEN_PINE_TAIGA, placementSnowyTree(10, RUBlocks.PINE_NATURAL_SET.getSapling()));
        register(context, RuTreePlacements.TREE_GROUP_FUNGAL_FEN, placementTree(4, RUBlocks.PINE_NATURAL_SET));
        register(context, RuTreePlacements.TREE_GROUP_TEMPERATE_GROVE, placementTree(4, Blocks.OAK_SAPLING));
        
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
        register(context, TREE_GROUP_PRAIRIE, placement().count(NoiseBasedCountPlacement.of(60, 30.0D, -0.5D)).notSubmerged().heightmap(Types.OCEAN_FLOOR).filter(RUPlacedFeatureBootstrap.onGrassBlockPredicate));
        
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
        
        register(context, RuTreePlacements.SPRUCE_TALL_SPARSE, SPRUCE_TALL, List.of(PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING), BiomeFilter.biome()));
        register(context, RuTreePlacements.TREE_GROUP_SPIRES, placementSnowyTree(4, Blocks.SPRUCE_SAPLING));
        register(context, RuTreePlacements.SPRUCE_SHRUB_DENSE, SPRUCE_SHRUB, placementTree(3, Blocks.OAK_SAPLING));

        register(context, RuTreePlacements.ICE_SPIRE, ICE_SPIRE, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onSnowPredicate), BiomeFilter.biome()));

        register(context, TREE_GROUP_SILVER_BIRCH_FOREST,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, -8, 6, 2, 0),
            inSquare(),
            notSubmerged(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING),
            BiomeFilter.biome()
        );
        
        register(context, RuTreePlacements.WILLOW_VINES, WILLOW_VINES, List.of(CountPlacement.of(2), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(3), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
    
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

    private static Holder.Reference<ConfiguredFeature<?, ?>> get(HolderGetter<ConfiguredFeature<?, ?>> getter, ResourceKey<ConfiguredFeature<?, ?>> key) {
        return getter.getOrThrow(key);
    }
}