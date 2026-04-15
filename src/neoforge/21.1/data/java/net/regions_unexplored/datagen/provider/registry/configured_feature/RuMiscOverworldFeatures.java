package net.regions_unexplored.datagen.provider.registry.configured_feature;

import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.world.level.block.other.AshBlock;
import net.regions_unexplored.world.level.block.wood.AspenLogBlock;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneClusterConfiguration;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneConfiguration;
import net.regions_unexplored.worldgen.feature.config.FallenTreeConfig;
import net.regions_unexplored.worldgen.feature.config.RockFeatureConfig;
import net.regions_unexplored.worldgen.treedecorator.AttachedToLogsDecorator;

import java.util.List;

import static dev.worldgen.lithostitched.api.worldgen.stateprovider.LithostitchedStateProviders.randomBlock;
import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.direct;

import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;
import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements.*;
import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.*;

public class RuMiscOverworldFeatures {
    //FALLEN_TREES
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK = key("tree/fallen_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_PINE = key("tree/fallen_pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SNOW_PINE = key("tree/fallen_snow_pine");
    //OTHER_FEATURES
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_PATCH_WITH_WATER = key("moss_patch_with_water");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_COBBLESTONE = key("rock/cobblestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_MIXED_COBBLESTONE = key("rock/mixed_cobblestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_MIXED_COBBLESTONE_LARGE = key("rock/mixed_cobblestone_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_MIXED_STONE = key("rock/mixed_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_STONE_LARGE = key("rock/stone_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_MOSSY_STONE_LARGE = key("rock/mossy_stone_large");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        RuleTest stoneOreTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> ORE_REDSTONE_TARGET_LIST = List.of(OreConfiguration.target(stoneOreTest, Blocks.REDSTONE_ORE.defaultBlockState()), OreConfiguration.target(deepslateOreTest, Blocks.DEEPSLATE_REDSTONE_ORE.defaultBlockState()));

        //---------------------FEATURES---------------------//
        //FALLEN_TREES
        registerPlaced(context, FALLEN_LARCH, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.LARCH_WOOD_SET.getLog().defaultBlockState(), 7, 12));
        registerPlaced(context, FALLEN_MAPLE, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.MAPLE_WOOD_SET.getLog().defaultBlockState(), 6, 8));
        register(context, FALLEN_OAK, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(Blocks.OAK_LOG.defaultBlockState(), 6, 8));
        register(context, FALLEN_PINE, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState(), 7, 12));
        register(context, FALLEN_SNOW_PINE, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.PINE_WOOD_SET.getStrippedLog().defaultBlockState(), 7, 12, List.of(new AttachedToLogsDecorator(0.4f, BlockStateProvider.simple(Blocks.SNOW), List.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST), true))));
        registerPlaced(context, FALLEN_SILVER_BIRCH, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.SILVER_BIRCH_WOOD_SET.getLog().defaultBlockState().setValue(AspenLogBlock.IS_BASE, true), 6, 10));
        //CAVE_FEATURES
        registerPlaced(context, POINTED_REDSTONE, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1))), PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1))))));
        registerPlaced(context, LARGE_POINTED_REDSTONE, LithostitchedFeatures.LARGE_DRIPSTONE, LithostitchedFeatures.largeDripstone(BlockStateProvider.simple(RUBlocks.RAW_REDSTONE_BLOCK.get()), context.lookup(Registries.BLOCK).getOrThrow(BlockTags.BASE_STONE_OVERWORLD), 30, UniformInt.of(1, 6), UniformFloat.of(0.4F, 2.0F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        registerPlaced(context, POINTED_REDSTONE_CLUSTER, RUFeatureTypes.POINTED_REDSTONE_CLUSTER.get(), new PointedRedstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8));
        registerPlaced(context, ORE_REDSTONE_LARGE, Feature.ORE, new OreConfiguration(ORE_REDSTONE_TARGET_LIST, 20));

        registerPlaced(context, PRISMARITE_CLUSTERS, Feature.RANDOM_PATCH, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.LARGE_PRISMARITE_CLUSTER.get().defaultBlockState(), 1).add(RUBlocks.PRISMARITE_CLUSTER.get().defaultBlockState(), 5)), 32));
        registerPlaced(context, HANGING_PRISMARITE_CLUSTER, RUFeatureTypes.HANGING_PRISMARITE.get(), FeatureConfiguration.NONE);

        registerPlaced(context, MINERAL_POOL, Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE, BlockStateProvider.simple(Blocks.CALCITE), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(CaveFeatures.POINTED_DRIPSTONE)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F));

        registerPlaced(context, LAVA_FALL, RUFeatureTypes.LAVA_FALL.get(), FeatureConfiguration.NONE);
        registerPlaced(context, OVERWORLD_LAVA_DELTA, RUFeatureTypes.OVERWORLD_LAVA_DELTA.get(), new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE, BlockStateProvider.simple(RUBlocks.VOLCANIC_ASH.get().defaultBlockState().setValue(AshBlock.HAS_GRAVITY, false)), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(CaveFeatures.POINTED_DRIPSTONE)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F));
        registerPlaced(context, ASH_VENT, RUFeatureTypes.ASH_VENT.get(), FeatureConfiguration.NONE);
        registerPlaced(context, BASALT_BLOB, RUFeatureTypes.BASALT_BLOB.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4)));
        //OTHER_FEATURES
        register(context, MOSS_PATCH_WITH_WATER, Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE, BlockStateProvider.simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(RuVegetationFeatures.PATCH_SHORT_GRASS)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F));
        registerPlaced(context, MARSH, RUFeatureTypes.MARSH.get(), FeatureConfiguration.NONE);
        registerPlaced(context, WATER_EDGE, RUFeatureTypes.WATER_EDGE.get(), FeatureConfiguration.NONE);
        registerPlaced(context, ICICLE_UP, RUFeatureTypes.ICICLE_UP.get(), FeatureConfiguration.NONE);
        registerPlaced(context, NOISE_PUMPKINS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.PUMPKIN.defaultBlockState(), 96).add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.NORTH), 1).add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.SOUTH), 1).add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.EAST), 1).add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.WEST), 1))), List.of(RUBlocks.SILT_PODZOL.get(), Blocks.SNOW_BLOCK), 16));
        
        var rockCobblestone = register(context, ROCK_COBBLESTONE, RUFeatureTypes.ROCK.get(), RockFeatureConfig.create(Blocks.COBBLESTONE));
        var rockMixedCobblestone = register(context, ROCK_MIXED_COBBLESTONE, RUFeatureTypes.ROCK.get(),
            RockFeatureConfig.create(randomBlock(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE))
        );
        var rockMixedCobblestoneLarge = register(context, ROCK_MIXED_COBBLESTONE_LARGE, RUFeatureTypes.ROCK.get(),
            RockFeatureConfig.createLarge(randomBlock(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE))
        );
        var rockMixedStone = register(context, ROCK_MIXED_STONE, RUFeatureTypes.ROCK.get(), RockFeatureConfig.create(randomBlock(Blocks.STONE, RUBlocks.MOSSY_STONE.get())));
        var rockStoneLarge = register(context, ROCK_STONE_LARGE, RUFeatureTypes.ROCK.get(), RockFeatureConfig.createLarge(Blocks.STONE));
        var rockMossyStoneLarge = register(context, ROCK_MOSSY_STONE_LARGE, RUFeatureTypes.ROCK.get(), RockFeatureConfig.createLarge(RUBlocks.MOSSY_STONE.get()));
        
        registerSelector(context, ROCK_GROUP_ICY_HEIGHTS, builder -> builder
            .add(direct(rockMixedStone), 2)
            .add(direct(rockMixedCobblestone), 1)
        );
        
        registerSelector(context, ROCK_GROUP_HIGHLAND_FIELDS, builder -> builder
            .add(direct(rockCobblestone), 1)
            .add(direct(rockStoneLarge), 2)
            .add(direct(rockMossyStoneLarge), 3)
        );
        
        registerSelector(context, ROCK_GROUP_TEMPERATE_GROVE, builder -> builder
            .add(direct(rockMixedCobblestone), 1)
            .add(direct(rockMixedStone), 2)
        );
        
        registerSelector(context, ROCK_GROUP_ROCKY_MEADOW, builder -> builder
            .add(direct(rockMixedStone), 1)
            .add(direct(rockStoneLarge), 2)
            .add(direct(rockMossyStoneLarge), 2)
        );
        
        registerSelector(context, RuMiscOverworldPlacements.ROCK_GROUP_TUNDRA, builder -> builder
            .add(direct(rockCobblestone), 1)
            .add(direct(rockMixedCobblestoneLarge), 2)
        );

        register(context, RUConfiguredFeatures.BONEMEAL_ALPHA_GRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.ALPHA_ROSE.get())));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int i) {
        return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }
}
