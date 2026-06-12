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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuAquaticFeatures;
import net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuVegetationFeatures;
import net.regions_unexplored.datagen.provider.registry.RUPlacedFeatureBootstrap;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUPlacedFeatures;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;
import static net.regions_unexplored.registry.data.RUPlacedFeatures.*;

public class RuVegetationPlacements {
    //-----------------------KEYS-----------------------//
    public static final ResourceKey<PlacedFeature> SINGLE_BLACKWOOD_BIOSHROOMS = key("single/blackwood_bioshrooms");
    //GRASS
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS_CAVES = patch("tall_grass_caves");
    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS_CAVES = patch("short_grass_caves");
    public static final ResourceKey<PlacedFeature> PATCH_FROZEN_GRASS = patch("frozen_grass");
    public static final ResourceKey<PlacedFeature> PATCH_WINDSWEPT_GRASS = patch("windswept_grass");

    public static final ResourceKey<PlacedFeature> PATCH_GRASS_SPROUTS = patch("grass_sprouts");
    public static final ResourceKey<PlacedFeature> PATCH_ASHEN_GRASS = patch("ashen_grass");
    public static final ResourceKey<PlacedFeature> PATCH_ASHEN_GRASS_SMOULDERING = patch("smouldering_ashen_grass");
    public static final ResourceKey<PlacedFeature> PATCH_LUPINES = patch("lupines");
    public static final ResourceKey<PlacedFeature> PATCH_ELEPHANT_EAR_SPARSE = patch("elephant_ear_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_ELEPHANT_EAR_DENSE = patch("elephant_ear_dense");
    public static final ResourceKey<PlacedFeature> PATCH_ELEPHANT_EAR_UNRECOVERABLY_DENSE = patch("elephant_ear_unrecoverably_dense");
    public static final ResourceKey<PlacedFeature> PATCH_ASH_VENTS = patch("ash_vents");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_SPARSE = patch("fern_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_FERN = patch("fern");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_DENSE = patch("fern_dense");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_REDWOODS = patch("fern_redwoods");
    public static final ResourceKey<PlacedFeature> PATCH_CLOVER = patch("clover");
    public static final ResourceKey<PlacedFeature> PATCH_DAISIES = patch("daisies");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERS_TUNDRA = patch("flowers/tundra");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_FLOWERS_WISTERIA_GROVE = patch("tall_flowers/wisteria_grove");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERS_WISTERIA_GROVE = patch("flowers/wisteria_grove");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERS_AUTUMNAL_MAPLE_FOREST = patch("flowers/autumnal_maple_forest");

    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS_SPARSE = patch("short_grass_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS = patch("short_grass");
    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS_DENSE = patch("short_grass_dense");
    
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS = patch("tall_grass");
    
    public static final ResourceKey<PlacedFeature> PATCH_SANDY_GRASS_SPARSE = patch("sandy_grass_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_SANDY_GRASS_DENSE = patch("sandy_grass_dense");
    public static final ResourceKey<PlacedFeature> PATCH_DESERT_SHRUB_ON_GRASS = patch("desert_shrub_on_grass");
    public static final ResourceKey<PlacedFeature> PATCH_DESERT_SHRUB_ON_SAND = patch("desert_shrub_on_sand");
    public static final ResourceKey<PlacedFeature> PATCH_STEPPE_SHRUB_ON_SAND = patch("steppe_grass_on_sand");
    
    public static final ResourceKey<PlacedFeature> PATCH_GRASSES_STEPPE = patch("grasses_steppe");
    public static final ResourceKey<PlacedFeature> PATCH_REDSTONE_BUD = patch("redstone_bud");
    public static final ResourceKey<PlacedFeature> PATCH_PRISMOSS_SPROUT = patch("prismoss_sprout");
    public static final ResourceKey<PlacedFeature> PATCH_BLADED_GRASS = patch("bladed_grass");
    //FLOWERS
    public static final ResourceKey<PlacedFeature> PATCH_CAVE_HYSSOP = patch("cave_hyssop");
    public static final ResourceKey<PlacedFeature> SINGLE_ASTER = key("single/aster");
    public static final ResourceKey<PlacedFeature> PATCH_TULIPS = patch("tulips");
    public static final ResourceKey<PlacedFeature> PATCH_SMALL_FLOWERS = patch("small_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_FLOWERS = patch("tall_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_ALPHA_DANDELION = patch("alpha_dandelion");
    public static final ResourceKey<PlacedFeature> PATCH_ALPHA_ROSE = patch("alpha_rose");
    public static final ResourceKey<PlacedFeature> PATCH_WILTING_TRILLIUM = patch("wilting_trillium");
    public static final ResourceKey<PlacedFeature> PATCH_AZURE_DAISY = patch("azure_daisy");
    public static final ResourceKey<PlacedFeature> PATCH_DAISY = patch("daisy");
    public static final ResourceKey<PlacedFeature> PATCH_WARATAH = patch("waratah");
    public static final ResourceKey<PlacedFeature> PATCH_PRAIRIE_FLOWERS = patch("prairie_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_SHRUBLAND_FLOWERS = patch("shrubland_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_WILLOW_FLOWERS = patch("willow_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_POPPIES = patch("poppies");
    public static final ResourceKey<PlacedFeature> SINGLE_TASSEL_SPARSE = key("single/tassel_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_TASSEL = patch("tassel");
    public static final ResourceKey<PlacedFeature> PATCH_LILAC = patch("lilac");
    public static final ResourceKey<PlacedFeature> PATCH_PEONY = patch("peony");
    public static final ResourceKey<PlacedFeature> SINGLE_CORPSE_FLOWER = key("single/corpse_flower");
    public static final ResourceKey<PlacedFeature> SINGLE_DUSKTRAP = key("single/dusktrap");
    public static final ResourceKey<PlacedFeature> SINGLE_DAY_LILY = key("single/day_lily");
    public static final ResourceKey<PlacedFeature> PATCH_TSUBAKI = patch("tsubaki");
    public static final ResourceKey<PlacedFeature> PATCH_HIBISCUS = patch("hibiscus");
    public static final ResourceKey<PlacedFeature> PATCH_MALLOW = patch("mallow");
    public static final ResourceKey<PlacedFeature> PATCH_HYSSOP = patch("hyssop");
    public static final ResourceKey<PlacedFeature> PATCH_SNOWY_FLOWERS = patch("snowy_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_BAMBOO_FLOWERS = patch("bamboo_flowers");
    public static final ResourceKey<PlacedFeature> SINGLE_SNOWBELLE = key("single/snowbelle");
    public static final ResourceKey<PlacedFeature> PATCH_BARLEY_SPARSE = patch("barley_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_BARLEY_DENSE = patch("barley_dense");
    public static final ResourceKey<PlacedFeature> SINGLE_MEADOW_SAGE = key("single/meadow_sage");
    public static final ResourceKey<PlacedFeature> PATCH_REDSTONE_BULB = patch("redstone_bulb");

    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_CONEFLOWER = patch("orange_coneflower");
    public static final ResourceKey<PlacedFeature> PATCH_PURPLE_CONEFLOWER = patch("purple_coneflower");
    //FOOD_PLANTS
    public static final ResourceKey<PlacedFeature> PATCH_SALMONBERRY_BUSH = patch("salmonberry_bush");
    public static final ResourceKey<PlacedFeature> SINGLE_DUSKMELON = key("single/duskmelon");
    //BIOSHROOM
    public static final ResourceKey<PlacedFeature> PATCH_CAVE_BIOSHROOMS = patch("cave_bioshrooms");
    public static final ResourceKey<PlacedFeature> PATCH_PINK_BIOSHROOM = patch("pink_bioshroom");
    //OTHER
    public static final ResourceKey<PlacedFeature> PATCH_CACTUS_DENSE = patch("cactus_dense");
    public static final ResourceKey<PlacedFeature> SINGLE_BARREL_CACTUS = key("single/barrel_cactus");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERING_LILY_PAD = patch("flowering_lily_pad");
    public static final ResourceKey<PlacedFeature> SPECIAL_GIANT_LILY = key("special/giant_lily");
    public static final ResourceKey<PlacedFeature> PATCH_DROPLEAF = patch("dropleaf");
    public static final ResourceKey<PlacedFeature> PATCH_DUCKWEED = patch("duckweed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        
        var patchElephantEar = getter.getOrThrow(RuVegetationFeatures.PATCH_ELEPHANT_EAR);
        var patchFern = getter.getOrThrow(RuVegetationFeatures.PATCH_FERN);
        var patchGrass = getter.getOrThrow(RuVegetationFeatures.PATCH_SHORT_GRASS);
        var patchTallGrass = getter.getOrThrow(RuVegetationFeatures.PATCH_TALL_GRASS);
        var patchSandyGrass = getter.getOrThrow(RuVegetationFeatures.PATCH_SANDY_GRASS);
        var tassel = getter.getOrThrow(RuVegetationFeatures.SINGLE_TASSEL);
        var patchBarley = getter.getOrThrow(RuVegetationFeatures.PATCH_BARLEY);
        var patchCactus = getter.getOrThrow(VegetationFeatures.PATCH_CACTUS);
        
        
        register(context, RuVegetationPlacements.PATCH_BAMBOO_FLOWERS, placement(0.2f, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_SNOWY_FLOWERS, placement(0.1f, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.SINGLE_BLACKWOOD_BIOSHROOMS, placement(0.25f, Types.OCEAN_FLOOR_WG)
            .notSubmerged()
            .add(SurfaceRelativeThresholdFilter.of(Types.OCEAN_FLOOR, Integer.MIN_VALUE, -16))
            .filter(BlockPredicate.allOf(
                BlockPredicate.wouldSurvive(RUBlocks.BLUE_BIOSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                BlockPredicate.ONLY_IN_AIR_PREDICATE
            ))
        );
        //GRASS
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS_CAVES, patchGrass, placementCave(150, Direction.DOWN));
        register(context, RuVegetationPlacements.PATCH_TALL_GRASS_CAVES, patchTallGrass, placementCave(75, Direction.DOWN));
        register(context, RuVegetationPlacements.PATCH_FROZEN_GRASS, surfaceSpread(7, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_WINDSWEPT_GRASS, surfaceSpread(6, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_GRASS_SPROUTS, surfaceSpread(12, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_ASHEN_GRASS, count(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUFeatureUtils.airAndBlocksBelow(RUBlocks.ASHEN_DIRT.get()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_ASHEN_GRASS_SMOULDERING, count(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUFeatureUtils.airAndBlocksBelow(RUBlocks.ASHEN_DIRT.get(), Blocks.BASALT, Blocks.SMOOTH_BASALT), BiomeFilter.biome());
        
        register(context, RuVegetationPlacements.PATCH_ELEPHANT_EAR_SPARSE, patchElephantEar, placement(1, Types.OCEAN_FLOOR_WG).notSubmerged().filter(RUBlocks.ELEPHANT_EAR.get()));
        register(context, RuVegetationPlacements.PATCH_ELEPHANT_EAR_DENSE, patchElephantEar, placement(8, Types.OCEAN_FLOOR_WG).notSubmerged().filter(RUBlocks.ELEPHANT_EAR.get()));
        register(context, RuVegetationPlacements.PATCH_ELEPHANT_EAR_UNRECOVERABLY_DENSE, patchElephantEar, placement(48, Types.MOTION_BLOCKING_NO_LEAVES).notSubmerged().filter(RUBlocks.ELEPHANT_EAR.get()));
        
        register(context, RuVegetationPlacements.PATCH_ASH_VENTS, count(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUFeatureUtils.airAndBlocksBelow(RUBlocks.ASH.get()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_FERN_SPARSE, patchFern, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_FERN, patchFern, surfaceSpread(4, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_FERN_DENSE, patchFern, surfaceSpread(10, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_FERN_REDWOODS, placement(20, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_LUPINES,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 2, 1, 1, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        
        register(context, RuVegetationPlacements.PATCH_FLOWERS_TUNDRA,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 4, 0, 1, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        
        register(context, RuVegetationPlacements.PATCH_FLOWERS_WISTERIA_GROVE, surfaceSpread(0.5, Types.MOTION_BLOCKING_NO_LEAVES));
        
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS_SPARSE, patchGrass, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS, patchGrass, surfaceSpread(4, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE, patchGrass, surfaceSpread(8, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_TALL_GRASS, patchTallGrass, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SANDY_GRASS_SPARSE, patchSandyGrass, surfaceSpread(0.33, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SANDY_GRASS_DENSE, patchSandyGrass, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_GRASS, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_SAND, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_STEPPE_SHRUB_ON_SAND, surfaceSpread(1, Types.WORLD_SURFACE_WG));

        register(context, RuVegetationPlacements.PATCH_GRASSES_STEPPE, surfaceSpread(12, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_REDSTONE_BUD, placementCave(150, Direction.DOWN).notInStructure());
        register(context, RuVegetationPlacements.PATCH_PRISMOSS_SPROUT, placementCave(100, Direction.DOWN));
        
        register(context, RuVegetationPlacements.PATCH_BLADED_GRASS, placementCave(200, Direction.DOWN));
        
        
        //FLOWERS
        register(context, RuVegetationPlacements.SINGLE_ASTER, placement(1, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_TULIPS, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_CAVE_HYSSOP, placementCave(25, Direction.DOWN));
        register(context, RuVegetationPlacements.PATCH_SMALL_FLOWERS, NoiseThresholdCountPlacement.of(-0.8D, 5, 6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_TALL_FLOWERS, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_ALPHA_DANDELION, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));

        PlacementModifier airCheck = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR));
        register(context, RUPlacedFeatures.BONEMEAL_ALPHA_GRASS, getter.getOrThrow(RUConfiguredFeatures.BONEMEAL_ALPHA_GRASS), RarityFilter.onAverageOnceEvery(25), airCheck);
        register(context, RuVegetationPlacements.PATCH_ALPHA_ROSE, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_WILTING_TRILLIUM, surfaceSpread(0.25, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_FLOWERS_AUTUMNAL_MAPLE_FOREST, placement(2f, Types.WORLD_SURFACE));
        register(context, RuVegetationPlacements.PATCH_AZURE_DAISY, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DAISY, surfaceSpread(0.333, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_WARATAH, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DAISIES, surfaceSpread(5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_PRAIRIE_FLOWERS, surfaceSpread(0.0625, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SHRUBLAND_FLOWERS, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_WILLOW_FLOWERS, surfaceSpread(0.25, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_POPPIES,
            noiseCount(8, 6, 2),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.SINGLE_TASSEL_SPARSE, tassel, placement(2, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_TALL_FLOWERS_WISTERIA_GROVE, rarityFilter(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.SINGLE_SNOWBELLE, placement(2, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.SINGLE_CORPSE_FLOWER, placementCave(80, Direction.DOWN));
        register(context, RuVegetationPlacements.SINGLE_DUSKTRAP, placementCave(50, Direction.DOWN));
        register(context, RuVegetationPlacements.SINGLE_DAY_LILY, placement(1, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_TSUBAKI, placement(0.5f, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_HIBISCUS, placement(0.5f, Types.MOTION_BLOCKING_NO_LEAVES));
        register(context, RuVegetationPlacements.PATCH_MALLOW, placement(0.125f, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_HYSSOP, placement(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_BARLEY_SPARSE, patchBarley,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 3, -1, 1, 0),
            count(2),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        
        register(context, RuVegetationPlacements.PATCH_BARLEY_DENSE, patchBarley, placement()
            .count(NoiseBasedCountPlacement.of(155, 75.0D, 0.0D))
            .notSubmerged()
            .heightmap(Types.WORLD_SURFACE)
            .filter(RUPlacedFeatureBootstrap.onGrassBlockPredicate)
        );
        register(context, RuVegetationPlacements.SINGLE_MEADOW_SAGE, placement(0.25f, Types.WORLD_SURFACE));
        register(context, RuVegetationPlacements.PATCH_REDSTONE_BULB, placementCave(25, Direction.DOWN).notInStructure());

        register(context, RuVegetationPlacements.PATCH_ORANGE_CONEFLOWER, placement().count(noiseCount(22, -6, 2)).heightmap(Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_PURPLE_CONEFLOWER, placement().count(noiseCount(28, -10, 2)).heightmap(Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.PATCH_CLOVER, placement(3, Types.MOTION_BLOCKING));
        //FOOD_PLANTS
        register(context, RuVegetationPlacements.PATCH_SALMONBERRY_BUSH, placement(0.02f, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.SINGLE_DUSKMELON, placementCave(30, Direction.DOWN));
        //BIOSHROOM
        register(context, RuVegetationPlacements.PATCH_CAVE_BIOSHROOMS, placementCave(30, Direction.DOWN).notInStructure());
        register(context, RuVegetationPlacements.PATCH_PINK_BIOSHROOM, placement(2, Types.MOTION_BLOCKING));
        //OTHER
        register(context, RuVegetationPlacements.PATCH_CACTUS_DENSE, patchCactus, placement(0.33f, Types.MOTION_BLOCKING));
        register(context, RuVegetationPlacements.SINGLE_BARREL_CACTUS, placement().heightmap(Types.WORLD_SURFACE_WG).filter(BlockPredicate.ONLY_IN_AIR_PREDICATE));
        register(context, RuVegetationPlacements.PATCH_FLOWERING_LILY_PAD, placement(4, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.SPECIAL_GIANT_LILY, placement(3, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DROPLEAF, placementCave(100, Direction.UP).notInStructure());
        register(context, RuVegetationPlacements.PATCH_DUCKWEED,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 32, 0, 3, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        );
        
        register(context, VANILLA_BADLANDS_SAGUAROS, getter.getOrThrow(RUConfiguredFeatures.TREE_SAGUARO_CACTUS), placement(0.02f, Types.OCEAN_FLOOR).filter(RUBlocks.SAGUARO_CACTUS_NATURAL_SET.getSapling()));
        register(context, VANILLA_BADLANDS_STEPPE_GRASS, placement(0.5f, Types.MOTION_BLOCKING));
        register(context, VANILLA_BASALT_DELTAS_ASH_VENTS, placementNether(6).add(RUFeatureUtils.airAndBlocksBelow(Blocks.BASALT)).add(RandomOffsetPlacement.vertical(ConstantInt.of(-1))));
        register(context, VANILLA_BEACH_PALM_TREES, getter.getOrThrow(RUConfiguredFeatures.TREE_PALM), placementTree(0.1f, RUBlocks.PALM_NATURAL_SET).add(LithostitchedPlacementModifiers.condition(LithostitchedPlacementConditions.sampleNoiseRouter(NoiseRouterTarget.TEMPERATURE, new InclusiveRange<>(0.2, 0.55)))));
        register(context, VANILLA_BIRCH_ORANGE_CONEFLOWERS, placement(0.05f, Types.OCEAN_FLOOR_WG).notSubmerged());
        register(context, VANILLA_DESERT_SANDY_GRASS, getter.getOrThrow(RuVegetationFeatures.PATCH_SANDY_GRASS), placement(0.33f, Types.MOTION_BLOCKING));
        register(context, VANILLA_FOREST_FLOWERS, placement(0.025f, Types.MOTION_BLOCKING));
        register(context, VANILLA_JUNGLE_BAMBOO_TREES, getter.getOrThrow(RUConfiguredFeatures.TREE_BAMBOO), placementTree(0.1f, RUBlocks.BAMBOO_NATURAL_SET));
        register(context, VANILLA_JUNGLE_ELEPHANT_EARS, getter.getOrThrow(RuVegetationFeatures.PATCH_ELEPHANT_EAR), placement(0.5f, Types.WORLD_SURFACE_WG));
        register(context, VANILLA_JUNGLE_HIBISCUSES, getter.getOrThrow(RUConfiguredFeatures.fromPlaced(PATCH_HIBISCUS)), placement(0.2f, Types.WORLD_SURFACE_WG));
        register(context, VANILLA_MANGROVE_FLOWERING_LILIES, placement(2, Types.WORLD_SURFACE_WG));
        register(context, VANILLA_PLAINS_BUSHES, getter.getOrThrow(RUConfiguredFeatures.TREE_OAK_SHRUB_SMALL), placementTree(0.2f, Blocks.OAK_SAPLING));
        register(context, VANILLA_SAVANNA_BUSHES, placementTree(1, Blocks.ACACIA_SAPLING));
        register(context, VANILLA_SNOWY_FROZEN_GRASS, getter.getOrThrow(RUConfiguredFeatures.fromPlaced(PATCH_FROZEN_GRASS)), placement(1, Types.WORLD_SURFACE_WG));
        register(context, VANILLA_SWAMP_CATTAILS, getter.getOrThrow(RuAquaticFeatures.PATCH_CATTAIL), placement().count(0.5f).atHeight(VerticalAnchor.absolute(62)));
        register(context, VANILLA_SWAMP_TREES, placement(2, Types.OCEAN_FLOOR).maxWaterDepth(2).filter(Blocks.OAK_SAPLING));
        register(context, VANILLA_TAIGA_PURPLE_CONEFLOWERS, placement(0.05f, Types.OCEAN_FLOOR_WG).notSubmerged());
    }
}
