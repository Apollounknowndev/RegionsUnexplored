package net.regions_unexplored.datagen.provider.registry.configured_feature;

import dev.worldgen.lithostitched.api.worldgen.blockpredicate.LithostitchedBlockPredicates;
import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import dev.worldgen.lithostitched.worldgen.blockpredicate.GridPredicate;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.world.level.block.wood.AspenLogBlock;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneClusterConfiguration;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneConfiguration;
import net.regions_unexplored.worldgen.feature.config.CarvedLimitedPoolFeatureConfig;
import net.regions_unexplored.worldgen.feature.config.FallenTreeConfig;
import net.regions_unexplored.worldgen.feature.config.RockFeatureConfig;
import net.regions_unexplored.worldgen.treedecorator.AttachedToLogsDecorator;

import java.util.List;

import static dev.worldgen.lithostitched.api.worldgen.stateprovider.LithostitchedStateProviders.randomBlock;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.direct;

import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;
import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements.*;
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuMiscOverworldFeatures {
    //FALLEN_TREES
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK = key("tree/fallen/oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_PINE = key("tree/fallen/pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SNOW_PINE = key("tree/fallen/snow_pine");
    //OTHER_FEATURES
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPECIAL_MOSS_PATCH_WITH_WATER = key("special/moss_patch_with_water");
    
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
        register(context, FALLEN_SNOW_PINE, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.PINE_WOOD_SET.getStrippedLog().defaultBlockState(), 7, 12, List.of(new AttachedToLogsDecorator(0.4f, simple(Blocks.SNOW), List.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST), true))));
        registerPlaced(context, FALLEN_SILVER_BIRCH, RUFeatureTypes.FALLEN_TREE.get(), FallenTreeConfig.of(RUBlocks.SILVER_BIRCH_WOOD_SET.getLog().defaultBlockState().setValue(AspenLogBlock.IS_BASE, true), 6, 10));
        //CAVE_FEATURES
        registerPlaced(context, SPECIAL_POINTED_REDSTONE, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1))), PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1))))));
        registerPlaced(context, SPECIAL_LARGE_POINTED_REDSTONE, LithostitchedFeatures.LARGE_DRIPSTONE, LithostitchedFeatures.largeDripstone(simple(RUBlocks.RAW_REDSTONE_BLOCK.get()), context.lookup(Registries.BLOCK).getOrThrow(BlockTags.BASE_STONE_OVERWORLD), 30, UniformInt.of(1, 6), UniformFloat.of(0.4F, 2.0F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        registerPlaced(context, SPECIAL_POINTED_REDSTONE_CLUSTER, RUFeatureTypes.POINTED_REDSTONE_CLUSTER.get(), new PointedRedstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F), 0.1F, 3, 8));
        registerPlaced(context, SPECIAL_ORE_REDSTONE_LARGE, Feature.ORE, new OreConfiguration(ORE_REDSTONE_TARGET_LIST, 20));

        registerPlaced(context, PATCH_PRISMARITE_CLUSTER, Feature.RANDOM_PATCH, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.LARGE_PRISMARITE_CLUSTER.get().defaultBlockState(), 1).add(RUBlocks.PRISMARITE_CLUSTER.get().defaultBlockState(), 5)), 32));
        registerPlaced(context, SPECIAL_HANGING_PRISMARITE_CLUSTER, RUFeatureTypes.HANGING_PRISMARITE.get(), FeatureConfiguration.NONE);

        registerPlaced(context, SPECIAL_CALCITE_POOL, Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE, simple(Blocks.CALCITE), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(CaveFeatures.POINTED_DRIPSTONE)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F));

        registerPlaced(context, SPECIAL_LAVA_FALL, Feature.SPRING, new SpringConfiguration(
            Fluids.LAVA.defaultFluidState(),
            false,
            4,
            1,
            context.lookup(Registries.BLOCK).getOrThrow(BlockTags.BASE_STONE_OVERWORLD)
        ));
        registerPlaced(context, SPECIAL_INFERNO_LAVA_DELTA, Feature.DISK, new DiskConfiguration(
            new RuleBasedBlockStateProvider(simple(Blocks.MAGMA_BLOCK), List.of(
                new RuleBasedBlockStateProvider.Rule(
                    BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.solid(Vec3i.ZERO.above())), LithostitchedBlockPredicates.randomChance(0.25f)),
                    simple(Blocks.LAVA)
                )
            )),
            new GridPredicate(
                1,
                1,
                BlockPredicate.not(BlockPredicate.anyOf(BlockPredicate.solid(), BlockPredicate.matchesBlocks(Blocks.LAVA))),
                new InclusiveRange<>(0)
            ),
            UniformInt.of(3, 4),
            0
        ));
        
        
        
        
        
        registerPlaced(context, PATCH_ASH_VENTS_INFERNO, Feature.RANDOM_PATCH, new RandomPatchConfiguration(144, 5, 1, Holder.direct(new PlacedFeature(
            Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(
                    inlinePlaced(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        weightedStates(pair(Blocks.BASALT, 8), pair(Blocks.SMOOTH_BASALT, 8), pair(Blocks.MAGMA_BLOCK))
                    ))),
                    0.7f
                )),
                inlinePlaced(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                    List.of(
                        new BlockColumnConfiguration.Layer(UniformInt.of(0, 4), simple(Blocks.BASALT)),
                        new BlockColumnConfiguration.Layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(0), 9).add(ConstantInt.of(1), 1).build()), simple(RUBlocks.ASH_VENT.get()))
                    ),
                    Direction.UP,
                    BlockPredicate.allOf(
                        BlockPredicate.matchesTag(BlockTags.AIR),
                        BlockPredicate.not(BlockPredicate.anyOf(
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.north(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.east(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.south(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.west(), RUBlocks.ASH_VENT.get())
                        ))
                    ),
                    true
                )))
            ))),
            List.of(
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    BlockPredicate.hasSturdyFace(Vec3i.ZERO.below(), Direction.UP))
                ),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1))
            )
        ))));
        
        
        
        
        registerPlaced(context, SPECIAL_BASALT_BLOB, RUFeatureTypes.BASALT_BLOB.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4)));
        //OTHER_FEATURES
        register(context, SPECIAL_MOSS_PATCH_WITH_WATER, Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.LUSH_GROUND_REPLACEABLE, simple(Blocks.MOSS_BLOCK), PlacementUtils.inlinePlaced(holderGetter.getOrThrow(RuVegetationFeatures.PATCH_SHORT_GRASS)), CaveSurface.FLOOR, ConstantInt.of(3), 0.8F, 5, 0.1F, UniformInt.of(4, 7), 0.7F));
        registerPlaced(context, SPECIAL_MARSH, RUFeatureTypes.MARSH.get(), FeatureConfiguration.NONE);
        registerPlaced(context, SPECIAL_WATER_EDGE, RUFeatureTypes.WATER_EDGE.get(), FeatureConfiguration.NONE);
        registerPlaced(context, SPECIAL_CARVED_LIMITED_POOL, RUFeatureTypes.CARVED_LIMITED_POOL.get(), new CarvedLimitedPoolFeatureConfig(3, ConstantInt.of(4), BlockPredicate.matchesBlocks(Blocks.MUD), BlockPredicate.matchesBlocks(Blocks.MUD), BlockStateProvider.simple(Blocks.DIRT), BlockStateProvider.simple(Blocks.GRASS_BLOCK)));
        registerPlaced(context, SPECIAL_ICICLE_UP, RUFeatureTypes.ICICLE_UP.get(), FeatureConfiguration.NONE);
        registerPlaced(context, PATCH_SILT_PODZOL_PUMPKINS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.PUMPKIN.defaultBlockState(), 96)
                .add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.NORTH), 1)
                .add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.SOUTH), 1)
                .add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.EAST), 1)
                .add(Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.WEST), 1))
        ), List.of(RUBlocks.SILT_PODZOL.get()), 16));
        
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
        
        registerSelector(context, ROCK_GROUP_WINDSWEPT_MAPLE_FOREST, builder -> builder
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

        register(context, RUConfiguredFeatures.BONEMEAL_ALPHA_GRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(simple(RUBlocks.ALPHA_ROSE.get())));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int i) {
        return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }
}
