package net.regions_unexplored.datagen.provider.registry.configured_feature;

import com.google.common.collect.ImmutableList;
import dev.worldgen.lithostitched.api.util.WeightedList;
import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.worldgen.foliageplacer.*;
import net.regions_unexplored.worldgen.rootplacer.MagnoliaRootPlacer;
import net.regions_unexplored.worldgen.rootplacer.WillowRootPlacer;
import net.regions_unexplored.worldgen.treedecorator.*;
import net.regions_unexplored.block.type.leaves.AppleLeavesBlock;
import net.regions_unexplored.block.type.wood.BambooLogBlock;
import net.regions_unexplored.world.level.feature.configuration.GiantBioshroomConfiguration;
import net.regions_unexplored.world.level.feature.configuration.RUTreeConfiguration;
import net.regions_unexplored.worldgen.trunkplacer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;
import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;
import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements.*;
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuTreeFeatures {
    public static final TreeDecorator PINE_BRANCH = RandomBranchDecorator.create(0.1f, RUBlocks.PINE_NATURAL_SET, RUBlocks.PINE_WOOD_SET, 3);
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var giantBlueBioshroom = register(context, TREE_GIANT_BLUE_BIOSHROOM, RUFeatureTypes.GIANT_BLUE_BIOSHROOM.get(), new GiantBioshroomConfiguration(simple(RUBlocks.BLUE_BIOSHROOM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.BLUE_BIOSHROOM_BLOCK.get().defaultBlockState()), simple(RUBlocks.GLOWING_BLUE_BIOSHROOM_BLOCK.get().defaultBlockState()), 7, 7));
        var giantGreenBioshroom = register(context, TREE_GIANT_GREEN_BIOSHROOM, RUFeatureTypes.GIANT_GREEN_BIOSHROOM.get(), new GiantBioshroomConfiguration(simple(RUBlocks.GREEN_BIOSHROOM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.GREEN_BIOSHROOM_BLOCK.get().defaultBlockState()), simple(RUBlocks.GLOWING_GREEN_BIOSHROOM_BLOCK.get().defaultBlockState()), 8, 5));
        var giantPinkBioshroom = register(context, TREE_GIANT_PINK_BIOSHROOM, RUFeatureTypes.GIANT_PINK_BIOSHROOM.get(), new GiantBioshroomConfiguration(simple(RUBlocks.PINK_BIOSHROOM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINK_BIOSHROOM_BLOCK.get().defaultBlockState()), simple(RUBlocks.GLOWING_PINK_BIOSHROOM_BLOCK.get().defaultBlockState()), 7, 8));
        var giantBrownBioshroom = register(context, TREE_GIANT_BROWN_MUSHROOM, Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfiguration(simple(Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 3));
        var giantRedMushroom = register(context, TREE_GIANT_RED_MUSHROOM, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfiguration(simple(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), simple(Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))), 2));

        registerSelector(context, TREE_GROUP_BIOSHROOM_CAVES, builder -> builder
            .add(direct(giantBlueBioshroom), 1)
            .add(direct(giantGreenBioshroom), 1)
        );
        
        var ashen = register(context, TREE_ASHEN, RUFeatureTypes.ASHEN_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.ASHEN_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.ASHEN_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.DEAD_NATURAL_SET.getBranch().defaultBlockState()), 12, 5));
        var ashenPine = register(context, TREE_ASHEN_PINE, RUFeatureTypes.ASHEN_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.ASHEN_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.DEAD_NATURAL_SET.getBranch().defaultBlockState()), 12, 7));
        registerPlaced(context, TREE_GROUP_ASHEN_WOODLAND, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(direct(ashen), direct(ashenPine))));
        
        var acacia = register(context, TREE_ACACIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.ACACIA_LOG), new ForkingTrunkPlacer(5, 2, 2), simple(Blocks.ACACIA_LEAVES), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());
        var acaciaShrub = register(context, TREE_ACACIA_SHRUB, Feature.TREE, bushSmall(Blocks.ACACIA_LOG, Blocks.ACACIA_LEAVES));
        
        register(context, TREE_ALPHA_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.ALPHA_WOOD_SET.getLog().defaultBlockState()), new StraightTrunkPlacer(4, 2, 0), simple(RUBlocks.ALPHA_NATURAL_SET.getLeaves().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
        
        register(context, TREE_BAMBOO, RUFeatureTypes.BAMBOO_TREE.get(), new RUTreeConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.BAMBOO_LOG.get().defaultBlockState(), 1).add(RUBlocks.BAMBOO_LOG.get().defaultBlockState().setValue(BambooLogBlock.LEAVES, true), 2)), simple(RUBlocks.BAMBOO_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.OAK_NATURAL_SET.getBranch().defaultBlockState()), 12, 8));
        
        register(context, TREE_FLOWERING_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()),new StraightTrunkPlacer(4, 3, 0),new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 3).add(RUBlocks.FLOWERING_NATURAL_SET.getLeaves().defaultBlockState(), 1)),new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
        register(context, TREE_BIG_FLOWERING_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()),new FancyTrunkPlacer(8, 11, 0),new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 3).add(RUBlocks.FLOWERING_NATURAL_SET.getLeaves().defaultBlockState(), 1)),new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
        
        var appleOak = register(context, TREE_APPLE_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            simple(Blocks.OAK_LOG),
            new StraightTrunkPlacer(4, 2, 2),
            AppleLeavesBlock.createStateProvider(29),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
            new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().build());
        var bigAppleOak = register(context, TREE_BIG_APPLE_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            simple(Blocks.OAK_LOG),
            new FancyTrunkPlacer(5, 4, 4),
            AppleLeavesBlock.createStateProvider(49),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
        ).decorators(List.of(new RandomBranchDecorator(0.1f, RUBlocks.OAK_NATURAL_SET.getBranch(), Blocks.OAK_LOG, 2, Optional.of(AppleLeavesBlock.createStateProvider(2))))).ignoreVines().build());
        
        var baobabMega = register(context, TREE_MEGA_BAOBAB, RUFeatureTypes.MEGA_BAOBAB_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.BAOBAB_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.BAOBAB_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.BAOBAB_NATURAL_SET.getBranch()), 5, 5));
        var baobabUltra = register(context, TREE_ULTRA_BAOBAB, RUFeatureTypes.ULTRA_BAOBAB_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.BAOBAB_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.BAOBAB_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.BAOBAB_NATURAL_SET.getBranch()), 12, 6));
        var oakShrubSmall = register(context, TREE_OAK_SHRUB_SMALL, Feature.TREE, bushSmall(Blocks.OAK_LOG, Blocks.OAK_LEAVES));
        
        registerSelector(context, TREE_GROUP_BAOBAB_SAVANNA, builder -> builder
            .add(direct(baobabMega), 2)
            .add(direct(baobabUltra), 1)
            .add(direct(acaciaShrub), 3)
            .add(direct(oakShrubSmall), 4)
        );
        
        var blackwood = register(context, TREE_BLACKWOOD, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.BLACKWOOD_WOOD_SET.getLog().defaultBlockState()), new StraightTrunkPlacer(12, 4, 2), simple(RUBlocks.BLACKWOOD_NATURAL_SET.getLeaves().defaultBlockState()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(2, 2), UniformInt.of(5, 5)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());
        var bigBlackwood = register(context, TREE_BIG_BLACKWOOD, RUFeatureTypes.BLACKWOOD_TREE.get(), new RUTreeConfiguration(
            simple(RUBlocks.BLACKWOOD_WOOD_SET.getLog().defaultBlockState()),
            simple(RUBlocks.BLACKWOOD_NATURAL_SET.getLeaves().defaultBlockState()),
            simple(RUBlocks.BLACKWOOD_NATURAL_SET.getBranch().defaultBlockState()),
            19,
            5
        ));
        var tallDarkOak = register(context, TREE_TALL_DARK_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.DARK_OAK_LOG), new DarkOakTrunkPlacer(8, 4, 1), simple(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).ignoreVines().build());
        
        var blueBioshroom = register(context, TREE_BLUE_BIOSHROOM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            simple(RUBlocks.BLUE_BIOSHROOM_WOOD_SET.getLog()),
            new StraightTrunkPlacer(1, 1, 1),
            simple(RUBlocks.BLUE_BIOSHROOM_BLOCK.get()),
            new BioshroomFoliagePlacer(simple(Blocks.SHROOMLIGHT)),
            new TwoLayersFeatureSize(0, 0, 0)
        ).ignoreVines().build());
        var pinkBioshroom = register(context, TREE_PINK_BIOSHROOM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            simple(RUBlocks.PINK_BIOSHROOM_WOOD_SET.getLog()),
            new StraightTrunkPlacer(1, 1, 1),
            simple(RUBlocks.PINK_BIOSHROOM_BLOCK.get()),
            new BioshroomFoliagePlacer(simple(Blocks.SHROOMLIGHT)),
            new TwoLayersFeatureSize(0, 0, 0)
        ).ignoreVines().build());
        
        registerPlaced(context, TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(bigBlackwood)));
        registerPlaced(context, TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(blackwood)));
        registerSelector(context, TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY, builder -> builder
           .add(Holder.direct(new PlacedFeature(tallDarkOak, List.of(
               BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DARK_OAK_SAPLING.defaultBlockState(), Vec3i.ZERO))
           ))), 18)
           .add(Holder.direct(new PlacedFeature(blueBioshroom, List.of(
               HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
               SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR, Integer.MIN_VALUE, -24),
               BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                   BlockPredicate.wouldSurvive(RUBlocks.BLUE_BIOSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                   BlockPredicate.ONLY_IN_AIR_PREDICATE
               ))
           ))))
           .add(Holder.direct(new PlacedFeature(pinkBioshroom, List.of(
               HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
               SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR, Integer.MIN_VALUE, -24),
               BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                   BlockPredicate.wouldSurvive(RUBlocks.PINK_BIOSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                   BlockPredicate.ONLY_IN_AIR_PREDICATE
               ))
           ))))
       );
       
       var birchAspen = register(context, TREE_BIRCH_ASPEN, RUFeatureTypes.ASPEN_TREE.get(), new RUTreeConfiguration(simple(Blocks.BIRCH_LOG.defaultBlockState()), simple(Blocks.BIRCH_LEAVES.defaultBlockState()), simple(RUBlocks.BIRCH_NATURAL_SET.getBranch().defaultBlockState()), 4, 3));
       var birchAspen2 = register(context, TREE_BIRCH_ASPEN_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(Blocks.BIRCH_LOG),
           new AspenTrunkPlacer(BiasedToBottomInt.of(6, 11), 0.5f),
           simple(Blocks.BIRCH_LEAVES),
           new AspenFoliagePlacer(),
           new TwoLayersFeatureSize(1, 0, 1)
       ).decorators(List.of(new BeehiveDecorator(0.5f), new RandomBranchDecorator(0.1f, RUBlocks.BIRCH_NATURAL_SET.getBranch(), Blocks.BIRCH_LOG, 4, Optional.empty()))).build());



       var magnolia = register(context, TREE_MAGNOLIA, RUFeatureTypes.SAKURA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getBranch().defaultBlockState()), 1, 4));
       var blueMagnolia = register(context, TREE_BLUE_MAGNOLIA, RUFeatureTypes.SAKURA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.BLUE_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getBranch().defaultBlockState()), 1, 4));
       var pinkMagnolia = register(context, TREE_PINK_MAGNOLIA, RUFeatureTypes.SAKURA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINK_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getBranch().defaultBlockState()), 1, 4));
       var whiteMagnolia = register(context, TREE_WHITE_MAGNOLIA, RUFeatureTypes.SAKURA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.WHITE_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getBranch().defaultBlockState()), 1, 5));
       register(context, TREE_BIG_MAGNOLIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()),new SakuraFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       register(context, TREE_BIG_BLUE_MAGNOLIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.BLUE_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()),new SakuraFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       register(context, TREE_BIG_PINK_MAGNOLIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.PINK_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()),new SakuraFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       register(context, TREE_BIG_WHITE_MAGNOLIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAGNOLIA_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.WHITE_MAGNOLIA_NATURAL_SET.getLeaves().defaultBlockState()),new SakuraFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       
       registerPlaced(context, TREE_GROUP_POPPY_FIELDS, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(magnolia)));
       
       registerSelector(context, TREE_GROUP_MAGNOLIA_WOODLAND, builder -> builder
           .add(direct(magnolia), 1)
           .add(direct(pinkMagnolia), 1)
           .add(direct(whiteMagnolia), 1)
        );
       
       var cypress = register(context, TREE_CYPRESS, RUFeatureTypes.CYPRESS_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.CYPRESS_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.CYPRESS_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.CYPRESS_NATURAL_SET.getBranch().defaultBlockState()), 17, 4));
       var giantCypress = register(context, TREE_GIANT_CYPRESS, RUFeatureTypes.GIANT_CYPRESS_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.CYPRESS_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.CYPRESS_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.CYPRESS_NATURAL_SET.getBranch().defaultBlockState()), 25, 5));
       registerPlaced(context, TREE_GROUP_OLD_GROWTH_BAYOU, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
           List.of(new WeightedPlacedFeature(direct(cypress), 0.4f)),
           direct(giantCypress)
       ));

       register(context, TREE_CHERRY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.CHERRY_LOG), new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)), simple(Blocks.CHERRY_LEAVES), new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

       var deadBog = register(context, TREE_DEAD_BOG, RUFeatureTypes.DEAD_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.DEAD_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.DEAD_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.DEAD_NATURAL_SET.getBranch().defaultBlockState()), 6, 2));

       registerRedirector(context, TREE_GROUP_MARSH, deadBog);
       
       register(context, TREE_DEAD, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.DEAD_WOOD_SET.getLog().defaultBlockState()), new StraightTrunkPlacer(6, 2, 0), simple(RUBlocks.DEAD_NATURAL_SET.getLeaves().defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
       register(context, TREE_BIG_DEAD, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.DEAD_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(12, 3, 0), simple(RUBlocks.DEAD_NATURAL_SET.getLeaves().defaultBlockState()),new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(2), 3), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       
       var deadPine = register(context, TREE_DEAD_PINE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(10, 4, 0),
           leaves(RUBlocks.DEAD_PINE_NATURAL_SET),
           new FancyPineFoliagePlacer(UniformInt.of(0, 1)),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH)).build());

       var deadPineTall = register(context, TREE_DEAD_PINE_TALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(14, 5, 0),
           leaves(RUBlocks.DEAD_PINE_NATURAL_SET),
           new FancyPineFoliagePlacer(UniformInt.of(0, 1)),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH)).build());

       var deadPineStripped = register(context, TREE_DEAD_STRIPPED_PINE, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.DEAD_PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 10, 4));
       var deadPineStrippedTall = register(context, TREE_DEAD_STRIPPED_PINE_TALL, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.DEAD_PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 14, 5));
       var deadPineStrippedMountain = register(context, TREE_DEAD_STRIPPED_PINE_MOUNTAIN, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.DEAD_PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 15, 7));

       var eucalyptusSmall = register(context, TREE_SMALL_EUCALYPTUS, RUFeatureTypes.SMALL_EUCALYPTUS_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.EUCALYPTUS_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.EUCALYPTUS_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.EUCALYPTUS_NATURAL_SET.getBranch().defaultBlockState()), 13, 8));
       var eucalyptus = register(context, TREE_EUCALYPTUS, RUFeatureTypes.EUCALYPTUS_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.EUCALYPTUS_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.EUCALYPTUS_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.EUCALYPTUS_NATURAL_SET.getBranch().defaultBlockState()), 14, 8));
       
       var joshuaSmall = register(context, TREE_JOSHUA_SMALL, RUFeatureTypes.SMALL_JOSHUA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.JOSHUA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.JOSHUA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.JOSHUA_NATURAL_SET.getBranch().defaultBlockState()), 1, 1));
       var joshuaMedium = register(context, TREE_JOSHUA_MEDIUM, RUFeatureTypes.MEDIUM_JOSHUA_TREE.get(), FeatureConfiguration.NONE);
       var joshuaLarge = register(context, TREE_JOSHUA_LARGE, RUFeatureTypes.LARGE_JOSHUA_TREE.get(), FeatureConfiguration.NONE);

       registerPlaced(context, TREE_GROUP_JOSHUA_DESERT, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(joshuaSmall), 4)
           .add(direct(joshuaMedium), 3)
           .add(direct(joshuaLarge), 2)
       .build()));

       var jungle = register(context, TREE_JUNGLE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.JUNGLE_LOG.defaultBlockState()), new StraightTrunkPlacer(6, 5, 0), simple(Blocks.JUNGLE_LEAVES.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).decorators(ImmutableList.of(new LeaveVineDecorator(0.25f))).build());
       var bigJungle = register(context, TREE_BIG_JUNGLE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.JUNGLE_LOG.defaultBlockState()), new FancyTrunkPlacer(9, 11, 0), simple(Blocks.JUNGLE_LEAVES.defaultBlockState()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator(0.25f))).build());

       var kapok = register(context, TREE_KAPOK, RUFeatureTypes.KAPOK_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.KAPOK_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.KAPOK_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.KAPOK_NATURAL_SET.getBranch().defaultBlockState()), 20, 7));
        
       
        TreeDecorator larchBranch = RandomBranchDecorator.create(0.1f, RUBlocks.LARCH_NATURAL_SET, RUBlocks.LARCH_WOOD_SET, 3);
        var larch = register(context, TREE_LARCH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            log(RUBlocks.LARCH_WOOD_SET),
            new StraightTrunkPlacer(6, 3, 3),
            leaves(RUBlocks.LARCH_NATURAL_SET),
            new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(1, 2), UniformInt.of(1, 2)),
            new TwoLayersFeatureSize(2, 0, 2)
        ).decorators(List.of(larchBranch)).ignoreVines().build());
        var larchPine = register(context, TREE_LARCH_PINE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            log(RUBlocks.LARCH_WOOD_SET),
            new StraightTrunkPlacer(8, 4, 2),
            leaves(RUBlocks.LARCH_NATURAL_SET),
            new FancyPineFoliagePlacer(UniformInt.of(1, 2)),
            new TwoLayersFeatureSize(2, 0, 2)
        ).decorators(List.of(larchBranch)).ignoreVines().build());
        var larchLarge = register(context, TREE_LARCH_LARGE, RUFeatureTypes.LARCH_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.LARCH_WOOD_SET.getLog()), simple(RUBlocks.LARCH_NATURAL_SET.getLeaves()), simple(RUBlocks.LARCH_NATURAL_SET.getBranch()), 18, 5));
       
       
        TreeDecorator goldenLarchBranch = RandomBranchDecorator.create(0.1f, RUBlocks.LARCH_NATURAL_SET.getBranch(), RUBlocks.LARCH_WOOD_SET.getLog(), RUBlocks.GOLDEN_LARCH_NATURAL_SET.getLeaves(), 3);
        var goldenLarch = register(context, TREE_LARCH_GOLDEN, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            log(RUBlocks.LARCH_WOOD_SET),
            new StraightTrunkPlacer(6, 3, 3),
            leaves(RUBlocks.GOLDEN_LARCH_NATURAL_SET),
            new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(1, 2), UniformInt.of(1, 2)),
            new TwoLayersFeatureSize(2, 0, 2)
        ).decorators(List.of(goldenLarchBranch)).ignoreVines().build());
        var goldenLarchPine = register(context, TREE_LARCH_GOLDEN_PINE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            log(RUBlocks.LARCH_WOOD_SET),
            new StraightTrunkPlacer(8, 4, 2),
            leaves(RUBlocks.GOLDEN_LARCH_NATURAL_SET),
            new FancyPineFoliagePlacer(UniformInt.of(1, 2)),
            new TwoLayersFeatureSize(2, 0, 2)
        ).decorators(List.of(goldenLarchBranch)).ignoreVines().build());
        var goldenLarchLarge = register(context, TREE_LARCH_GOLDEN_LARGE, RUFeatureTypes.LARCH_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.LARCH_WOOD_SET.getLog()), simple(RUBlocks.GOLDEN_LARCH_NATURAL_SET.getLeaves()), simple(RUBlocks.LARCH_NATURAL_SET.getBranch()), 18, 5));
       
       
       var maple = register(context, TREE_MAPLE, Feature.TREE, mapleSmall(RUBlocks.MAPLE_NATURAL_SET, RUBlocks.MAPLE_LEAF_LITTER));
       var bigMaple = register(context, TREE_BIG_MAPLE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAPLE_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.MAPLE_NATURAL_SET.getLeaves().defaultBlockState()),new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(List.of(PlaceOnGroundDecorator.leafLitter(RUBlocks.MAPLE_LEAF_LITTER.get(), 64))).ignoreVines().build());
       var redMaple = register(context, TREE_RED_MAPLE, Feature.TREE, mapleSmall(RUBlocks.RED_MAPLE_NATURAL_SET, RUBlocks.RED_MAPLE_LEAF_LITTER));
       var bigRedMaple = register(context, TREE_BIG_RED_MAPLE, Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAPLE_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.RED_MAPLE_NATURAL_SET.getLeaves().defaultBlockState()),new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(List.of(PlaceOnGroundDecorator.leafLitter(RUBlocks.RED_MAPLE_LEAF_LITTER.get(), 64))).ignoreVines().build());
       var orangeMaple = register(context, TREE_ORANGE_MAPLE, Feature.TREE, mapleSmall(RUBlocks.ORANGE_MAPLE_NATURAL_SET, RUBlocks.ORANGE_MAPLE_LEAF_LITTER));
       var bigOrangeMaple = register(context, TREE_BIG_ORANGE_MAPLE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(RUBlocks.MAPLE_WOOD_SET.getLog().defaultBlockState()),new FancyTrunkPlacer(8, 11, 0), simple(RUBlocks.ORANGE_MAPLE_NATURAL_SET.getLeaves().defaultBlockState()),new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).decorators(List.of(PlaceOnGroundDecorator.leafLitter(RUBlocks.ORANGE_MAPLE_LEAF_LITTER.get(), 64))).ignoreVines().build());

       var silverBirch = register(context, TREE_SILVER_BIRCH, RUFeatureTypes.ASPEN_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.SILVER_BIRCH_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.SILVER_BIRCH_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.SILVER_BIRCH_NATURAL_SET.getBranch().defaultBlockState()), List.of(PlaceOnGroundDecorator.leafLitter(RUBlocks.SILVER_BIRCH_LEAF_LITTER.get(), 48)), 4, 4));
       var silverBirchTall = register(context, TREE_SILVER_BIRCH_TALL, RUFeatureTypes.ASPEN_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.SILVER_BIRCH_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.SILVER_BIRCH_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.SILVER_BIRCH_NATURAL_SET.getBranch().defaultBlockState()), List.of(PlaceOnGroundDecorator.leafLitter(RUBlocks.SILVER_BIRCH_LEAF_LITTER.get(), 48)), 5, 5));
       
       
       registerPlaced(context, TREE_GROUP_SILVER_BIRCH_FOREST, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(silverBirch), 3)
           .add(direct(silverBirchTall), 1)
           .build()
       ));
       
       registerPlaced(context, TREE_GROUP_AUTUMNAL_MAPLE_FOREST, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(maple), 2)
           .add(direct(redMaple), 2)
           .add(direct(orangeMaple), 2)
           .add(direct(silverBirch), 2)
           .add(direct(bigRedMaple))
           .add(direct(bigOrangeMaple))
       .build()));

       register(context, TREE_OAK_WITH_FLOWERS, RUFeatureTypes.ASPEN_TREE.get(), new RUTreeConfiguration(simple(Blocks.OAK_LOG.defaultBlockState()), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 3).add(RUBlocks.FLOWERING_NATURAL_SET.getLeaves().defaultBlockState(), 1)), simple(RUBlocks.OAK_NATURAL_SET.getBranch().defaultBlockState()), 5, 5));
       
       var oakWithBranch = register(context, TREE_OAK_WITH_BRANCH, Feature.TREE, mapleSmall(Blocks.OAK_LOG, Blocks.OAK_LEAVES, RUBlocks.OAK_NATURAL_SET.getBranch(), null));
       var oak = register(context, TREE_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()), new StraightTrunkPlacer(5, 3, 0), simple(Blocks.OAK_LEAVES.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).decorators(List.of(new BeehiveDecorator(0.005f))).ignoreVines().build());
       var tallOak = register(context, TREE_OAK_TALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()), new StraightTrunkPlacer(6, 4, 0), simple(Blocks.OAK_LEAVES.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1)).decorators(List.of(new BeehiveDecorator(0.005f))).ignoreVines().build());
       var bigOak = register(context, TREE_BIG_OAK, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()), new FancyTrunkPlacer(7, 10, 0), simple(Blocks.OAK_LEAVES.defaultBlockState()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
       var oakBush = register(context, TREE_OAK_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()), new StraightTrunkPlacer(1, 0, 0), simple(Blocks.OAK_LEAVES.defaultBlockState()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).build());
       var oakBushWithFlowers = register(context, TREE_OAK_BUSH_WITH_FLOWERS, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.OAK_LOG.defaultBlockState()), new StraightTrunkPlacer(1, 0, 0), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OAK_LEAVES.defaultBlockState(), 3).add(RUBlocks.FLOWERING_NATURAL_SET.getLeaves().defaultBlockState(), 1)), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0)).build());
       var smallOak = register(context, TREE_SMALL_OAK, RUFeatureTypes.SMALL_OAK_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.SMALL_OAK_LOG.get()), simple(Blocks.OAK_LEAVES), simple(RUBlocks.OAK_NATURAL_SET.getBranch()), 5, 4));
       
       registerPlaced(context, TREE_GROUP_ARID_MOUNTAINS, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(oakShrubSmall)));
       
       registerSelector(context, TREE_GROUP_EUCALYPTUS_FOREST, builder -> builder
           .add(direct(eucalyptus), 1)
           .add(direct(eucalyptusSmall), 1)
           .add(direct(oakBush), 1)
       );
       
       registerSelector(context, TREE_GROUP_PRAIRIE, builder -> builder
           .add(direct(oak), 2)
           .add(direct(bigOak), 1)
       );
       
       registerSelector(context, TREE_GROUP_WINDSWEPT_MAPLE_FOREST, builder -> builder
           .add(direct(maple), 3)
           .add(direct(bigMaple), 1)
           .add(direct(oakWithBranch), 1)
           .add(direct(birchAspen), 1)
       );
       
       registerSelector(context, TREE_GROUP_ORCHARD, builder -> builder
           .add(direct(appleOak), 7)
           .add(direct(bigAppleOak), 2)
           .add(direct(bigOak), 1)
       );
       registerSelector(context, TREE_GROUP_OLD_GROWTH_FOREST, builder -> builder
           .add(direct(bigOak), 4)
           .add(direct(smallOak), 4)
           .add(direct(tallOak), 2)
           .add(direct(oakBush), 1)
       );
       
       registerSelector(context, TREE_GROUP_COLD_BOREAL_TAIGA, builder -> builder
           .add(direct(larch), 6)
           .add(direct(larchPine), 2)
           .add(direct(birchAspen), 1)
           .add(direct(oakBush), 1)
       );
       
       registerSelector(context, TREE_GROUP_BOREAL_TAIGA, builder -> builder
           .add(direct(larch), 6)
           .add(direct(larchPine), 2)
           .add(direct(goldenLarch), 3)
           .add(direct(goldenLarchPine), 1)
           .add(direct(birchAspen), 1)
       );
       
        registerSelector(context, TREE_GROUP_OLD_GROWTH_BOREAL_TAIGA, builder -> builder
            .add(direct(larchLarge), 7)
            .add(direct(larch), 3)
            .add(direct(larchPine), 2)
            .add(direct(goldenLarchLarge), 2)
            .add(direct(goldenLarch), 1)
            .add(direct(birchAspen), 1)
            .add(direct(oakBush), 1)
        );
        
       registerSelector(context, TREE_GROUP_OLD_GROWTH_GOLDEN_BOREAL_TAIGA, builder -> builder
           .add(direct(goldenLarchLarge), 7)
           .add(direct(goldenLarch), 3)
           .add(direct(goldenLarchPine), 2)
           .add(direct(larchLarge), 2)
           .add(direct(larch), 1)
           .add(direct(birchAspen), 1)
           .add(direct(oakBush), 1)
       );
       
       var oakShrubLarge = register(context, TREE_OAK_SHRUB_LARGE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(Blocks.OAK_LOG.defaultBlockState()),
           new StraightTrunkPlacer(1, 0, 0),
           simple(Blocks.OAK_LEAVES.defaultBlockState()),
           new PineFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), ConstantInt.of(3)),
           new TwoLayersFeatureSize(0, 0, 0)
       ).build());
       
       registerPlaced(context, TREE_GROUP_TUNDRA_BUSHES, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(oakShrubSmall), 3)
           .add(direct(oakShrubLarge), 2)
           .build()));
       
       var jungleAquatic = register(context, TREE_JUNGLE_AQUATIC, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(Blocks.JUNGLE_LOG),
           new MagnoliaTrunkPlacer(UniformInt.of(3, 6), UniformInt.of(4, 5), UniformInt.of(2, 4)),
           simple(Blocks.JUNGLE_LEAVES),
           new MagnoliaFoliagePlacer(),
           Optional.of(new MagnoliaRootPlacer(ConstantInt.ZERO, simple(Blocks.JUNGLE_LOG), Optional.empty())),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().build());
       
       var palm = register(context, TREE_PALM, RUFeatureTypes.PALM_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PALM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getBranch().defaultBlockState()), 8, 5));
       var palmTall = register(context, TREE_TALL_PALM, RUFeatureTypes.PALM_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PALM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getBranch().defaultBlockState()), 12, 5));
       var palmShrub = register(context, TREE_PALM_SHRUB, RUFeatureTypes.PALM_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PALM_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PALM_NATURAL_SET.getBranch().defaultBlockState()), 2, 1));
       
       registerSelector(context, TREE_GROUP_GRASSY_BEACH, builder -> builder
           .add(direct(palm), 2)
           .add(direct(palmTall), 1)
       );
       
       registerSelector(context, TREE_GROUP_RAINFOREST, builder -> builder
           .add(direct(palmTall), 4)
           .add(direct(kapok), 2)
           .add(direct(bigJungle), 4)
           .add(direct(palmShrub), 1)
           .add(direct(oakBushWithFlowers), 4)
       );
       
       registerSelector(context, TREE_GROUP_SPARSE_RAINFOREST, builder -> builder
           .add(direct(palm), 4)
           .add(direct(kapok), 2)
           .add(direct(bigJungle), 4)
           .add(direct(palmShrub), 1)
           .add(direct(oakBush), 4)
       );
       
       registerSelector(context, TREE_GROUP_TROPICS, builder -> builder
           .add(direct(palm), 2)
           .add(direct(palmShrub), 1)
           .add(direct(jungle), 1)
           .add(direct(bigJungle), 1)
           .add(direct(oakBushWithFlowers), 3)
       );
       
       registerSelector(context, TREE_GROUP_ROCKY_REEF, builder -> builder
           .add(direct(jungleAquatic), 3)
           .add(direct(palm), 2)
       );
       
       registerPlaced(context, TREE_GROUP_GRASSLAND, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(oakShrubSmall)));
       registerPlaced(context, TREE_GROUP_CHALK_CLIFFS, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(oakBushWithFlowers)));
       
       registerPlaced(context, TREE_GROUP_TROPICAL_RIVER, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(palm)));

       var pine = register(context, TREE_PINE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(10, 4, 0),
           leaves(RUBlocks.PINE_NATURAL_SET),
           new FancyPineFoliagePlacer(ConstantInt.of(0), UniformInt.of(0, 1), 0),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH, new BeehiveDecorator(0.001f))).build());
       
       var pineBees = register(context, TREE_PINE_BEES, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(9, 3, 0),
           leaves(RUBlocks.PINE_NATURAL_SET),
           new FancyPineFoliagePlacer(ConstantInt.of(0), UniformInt.of(1, 2), 0),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH, new BeehiveDecorator(1f))).build());

       var pineSkinny = register(context, TREE_PINE_SKINNY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            log(RUBlocks.PINE_WOOD_SET),
            new StraightTrunkPlacer(10, 4, 0),
            leaves(RUBlocks.PINE_NATURAL_SET),
            new SkinnyPineFoliagePlacer(ConstantInt.of(0), UniformInt.of(0, 1), 5),
            new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH, new BeehiveDecorator(0.001f))).build());

       var pineTall = register(context, TREE_PINE_TALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(14, 5, 0),
           leaves(RUBlocks.PINE_NATURAL_SET),
           new FancyPineFoliagePlacer(ConstantInt.of(0), UniformInt.of(0, 1), 0),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH, new BeehiveDecorator(0.001f))).build());

       var pineSkinnyTall = register(context, TREE_PINE_SKINNY_TALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.PINE_WOOD_SET),
           new StraightTrunkPlacer(14, 5, 0),
           leaves(RUBlocks.PINE_NATURAL_SET),
           new SkinnyPineFoliagePlacer(ConstantInt.of(0), UniformInt.of(0, 1), 5),
           new TwoLayersFeatureSize(3, 0, 1)
       ).decorators(List.of(PINE_BRANCH, new BeehiveDecorator(0.001f))).build());
       
       registerPlaced(context, TREE_GROUP_PINE_TAIGA_PRIMARY, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(pine), 12)
           .add(direct(pineSkinny), 3)
           .add(direct(pineTall), 4)
           .add(direct(pineSkinnyTall), 1)
        .build()));
       
       registerPlaced(context, TREE_GROUP_HIGHLAND_FIELDS, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(pineBees), 4)
           .add(direct(pine), 1)
        .build()));

       var pineStripped = register(context, TREE_STRIPPED_PINE, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 10, 4));
       var pineStrippedTall = register(context, TREE_STRIPPED_PINE_TALL, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 14, 5));
       var pineStrippedMountain = register(context, TREE_STRIPPED_PINE_MOUNTAIN, RUFeatureTypes.STRIPPED_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 15, 7));
       var pineShrub = register(context, TREE_PINE_SHRUB, RUFeatureTypes.TREE_SHRUB.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 1, 2));

       var lushPine = register(context, TREE_LUSH_PINE, RUFeatureTypes.LUSH_PINE_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.PINE_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.PINE_NATURAL_SET.getBranch().defaultBlockState()), 19, 4));
       
       
       registerSelector(context, TREE_GROUP_FEN, builder -> builder
           .add(direct(pine), 4)
           .add(direct(pineStripped), 4)
           .add(direct(deadBog), 1)
       );
       
       registerRedirector(context, TREE_GROUP_MOUNTAINS, pine);
       registerRedirector(context, TREE_GROUP_PINE_SLOPES, pineStripped);
       registerRedirector(context, TREE_GROUP_PINE_TAIGA_SECONDARY, pineShrub);
       
       registerSelector(context, TREE_GROUP_TOWERING_CLIFFS, builder -> builder
           .add(direct(pineStrippedMountain), 19)
           .add(direct(deadPineStrippedMountain), 1)
       );
       
       registerSelector(context, TREE_GROUP_ICY_HEIGHTS, builder -> builder
           .add(direct(pineStrippedMountain), 40)
           .add(direct(pineShrub), 9)
           .add(direct(deadPineStrippedMountain), 1)
       );
       
       registerSelector(context, TREE_GROUP_FROZEN_PINE_TAIGA, builder -> builder
           .add(direct(pineStripped), 7)
           .add(direct(pineStrippedTall), 2)
           .add(direct(pineShrub), 1)
       );
       
       registerSelector(context, TREE_GROUP_FUNGAL_FEN, builder -> builder
           .add(direct(lushPine), 2)
           .add(direct(giantPinkBioshroom), 1)
           .add(direct(giantRedMushroom), 1)
           .add(direct(giantBrownBioshroom), 1)
       );
       
       var saguaroCactus = register(context, TREE_SAGUARO_CACTUS, RUFeatureTypes.SAGUARO_CACTUS.get(), new RUTreeConfiguration(simple(RUBlocks.SAGUARO_CACTUS.get().defaultBlockState()), simple(RUBlocks.SAGUARO_CACTUS_NATURAL_SET.getSapling().defaultBlockState()), simple(RUBlocks.REDWOOD_NATURAL_SET.getBranch().defaultBlockState()), 7, 2));
       
       registerPlaced(context, TREE_GROUP_SAGUARO_DESERT, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(saguaroCactus)));
       
       register(context, TREE_ICE_SPIRE, RUFeatureTypes.SPIRE.get(), new RUTreeConfiguration(simple(Blocks.PACKED_ICE.defaultBlockState()), simple(Blocks.ICE.defaultBlockState()), simple(Blocks.BLUE_ICE), 14, 9));
       
       var spruceTall = register(context, TREE_SPRUCE_TALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(simple(Blocks.SPRUCE_LOG.defaultBlockState()), new StraightTrunkPlacer(13, 2, 2), simple(Blocks.SPRUCE_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(2, 2), UniformInt.of(5, 5)), new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());
       var spruceShrub = register(context, TREE_SPRUCE_SHRUB, RUFeatureTypes.TREE_SHRUB.get(), new RUTreeConfiguration(simple(Blocks.SPRUCE_LOG.defaultBlockState()), simple(Blocks.SPRUCE_LEAVES.defaultBlockState()), simple(RUBlocks.OAK_NATURAL_SET.getBranch().defaultBlockState()), 1, 0));
       
       var spruceTundra = register(context, TREE_SPRUCE_TUNDRA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.PINE_WOOD_SET.getLog()),
           new StraightTrunkPlacer(6, 3, 1),
           simple(Blocks.SPRUCE_LEAVES),
           new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(1, 2), UniformInt.of(1, 2)),
           new TwoLayersFeatureSize(2, 0, 2)
       ).decorators(List.of(PINE_BRANCH)).ignoreVines().build());
       
       var spruceFancyTundra = register(context, TREE_PINE_TUNDRA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.PINE_WOOD_SET.getLog()),
           new StraightTrunkPlacer(8, 4, 0),
           simple(Blocks.SPRUCE_LEAVES),
           new FancyPineFoliagePlacer(UniformInt.of(1, 2)),
           new TwoLayersFeatureSize(2, 0, 2)
       ).decorators(List.of(PINE_BRANCH)).ignoreVines().build());
       
       registerSelector(context, TREE_GROUP_SHRUBLAND, builder -> builder
           .add(direct(oakShrubSmall), 65)
           .add(direct(spruceShrub), 65)
           .add(direct(oakBush), 19)
           .add(direct(spruceTall), 1)
       );
       
       registerSelector(context, TREE_GROUP_COLD_DECIDUOUS_FOREST, builder -> builder
           .add(direct(oakWithBranch), 1)
           .add(direct(bigOak), 2)
           .add(direct(bigRedMaple), 2)
           .add(direct(spruceTall), 4)
       );
       
       
       registerSelector(context, TREE_GROUP_MAPLE_FOREST, builder -> builder
           .add(direct(maple), 4)
           .add(direct(redMaple), 1)
           .add(direct(bigMaple), 1)
           .add(direct(spruceTall), 4)
       );
       
       registerSelector(context, TREE_GROUP_TUNDRA, builder -> builder
           .add(direct(spruceTundra), 4)
           .add(direct(spruceFancyTundra), 1)
       );
       
       registerPlaced(context, TREE_GROUP_SPIRES, LithostitchedFeatures.PLACED, LithostitchedFeatures.placed(direct(spruceTall)));
       
       var socotraLarge = register(context, TREE_LARGE_SOCOTRA, RUFeatureTypes.LARGE_SOCOTRA_TREE.get(), new RUTreeConfiguration(simple(RUBlocks.SOCOTRA_WOOD_SET.getLog().defaultBlockState()), simple(RUBlocks.SOCOTRA_NATURAL_SET.getLeaves().defaultBlockState()), simple(RUBlocks.SOCOTRA_NATURAL_SET.getBranch()), 8, 5));
       var socotraSmall = register(context, TREE_SMALL_SOCOTRA, RUFeatureTypes.SMALL_SOCOTRA_TREE.get(), FeatureConfiguration.NONE);
       
       registerSelector(context, TREE_GROUP_DRY_BUSHLAND, builder -> builder
           .add(direct(socotraLarge), 1)
           .add(direct(socotraSmall), 1)
           .add(direct(acacia), 1)
           .add(direct(acaciaShrub), 3)
           .add(direct(oakShrubSmall), 3)
       );
       
       var redwoodSmall = register(context, TREE_REDWOOD_SMALL, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.REDWOOD_WOOD_SET.getLog().defaultBlockState()),
           new RedwoodTrunkPlacer(
               UniformInt.of(20, 40),
               List.of(ConstantInt.of(2), weightedInts(pair(2, 4), pair(3, 1)), weightedInts(pair(0, 4), pair(1, 1))),
               3,
               List.of()
           ),
           simple(RUBlocks.REDWOOD_NATURAL_SET.getLeaves().defaultBlockState()),
           new RedwoodFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 0),
           new TwoLayersFeatureSize(6, 1, 0))
           .decorators(List.of(RandomBranchDecorator.create(0.06f, RUBlocks.REDWOOD_NATURAL_SET, RUBlocks.REDWOOD_WOOD_SET, 3)))
           .build()
       );
       var redwoodMedium = register(context, TREE_REDWOOD_MEDIUM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.REDWOOD_WOOD_SET.getLog().defaultBlockState()),
           new RedwoodTrunkPlacer(
               UniformInt.of(30, 45),
               List.of(ConstantInt.of(2), weightedInts(pair(2, 4), pair(3, 1)), ConstantInt.of(2)),
               3,
               List.of(UniformInt.of(15, 20), UniformInt.of(3, 7), BiasedToBottomInt.of(0, 3))
           ),
           simple(RUBlocks.REDWOOD_NATURAL_SET.getLeaves().defaultBlockState()),
           new RedwoodFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 0),
           new TwoLayersFeatureSize(6, 1, 0))
           .decorators(List.of(RandomBranchDecorator.create(0.06f, RUBlocks.REDWOOD_NATURAL_SET, RUBlocks.REDWOOD_WOOD_SET, 3)))
           .build()
       );
       var redwoodLarge = register(context, TREE_REDWOOD_LARGE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.REDWOOD_WOOD_SET.getLog().defaultBlockState()),
           new RedwoodTrunkPlacer(
               UniformInt.of(40, 55),
               List.of(ConstantInt.of(1), weightedInts(pair(2, 4), pair(3, 1)), weightedInts(pair(2, 4), pair(3, 1)), ConstantInt.of(1)),
               3,
               List.of(UniformInt.of(35, 42), UniformInt.of(19, 21), UniformInt.of(10, 12), UniformInt.of(3, 6))
           ),
           simple(RUBlocks.REDWOOD_NATURAL_SET.getLeaves().defaultBlockState()),
           new RedwoodFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 0),
           new TwoLayersFeatureSize(8, 1, 0))
           .decorators(List.of(RandomBranchDecorator.create(0.12f, RUBlocks.REDWOOD_NATURAL_SET, RUBlocks.REDWOOD_WOOD_SET, 3)))
           .build()
       );
       var redwoodEmergent = register(context, TREE_REDWOOD_EMERGENT, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           simple(RUBlocks.REDWOOD_WOOD_SET.getLog().defaultBlockState()),
           new RedwoodTrunkPlacer(
               BiasedToBottomInt.of(60, 90),
               List.of(ConstantInt.of(2), weightedInts(pair(3, 4), pair(4, 1)), weightedInts(pair(3, 4), pair(4, 1)), ConstantInt.of(1)),
               3,
               List.of(UniformInt.of(40, 55), UniformInt.of(25, 35), UniformInt.of(10, 20), UniformInt.of(4, 8))
           ),
           simple(RUBlocks.REDWOOD_NATURAL_SET.getLeaves().defaultBlockState()),
           new RedwoodFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 0),
           new TwoLayersFeatureSize(8, 1, 0))
           .decorators(List.of(RandomBranchDecorator.create(0.12f, RUBlocks.REDWOOD_NATURAL_SET, RUBlocks.REDWOOD_WOOD_SET, 3)))
           .build()
       );

       registerPlaced(context, TREE_GROUP_REDWOODS_PRIMARY, LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(WeightedList.<Holder<PlacedFeature>>builder()
           .add(direct(redwoodMedium), 14)
           .add(direct(redwoodLarge), 4)
           .add(direct(redwoodEmergent), 1)
        .build()));
       registerRedirector(context, TREE_GROUP_REDWOODS_SECONDARY, redwoodSmall);
       registerRedirector(context, TREE_GROUP_REDWOODS_TERTIARY, oakShrubLarge);
       
       registerRedirector(context, TREE_GROUP_SPARSE_REDWOODS_PRIMARY, redwoodMedium);
       registerRedirector(context, TREE_GROUP_SPARSE_REDWOODS_SECONDARY, oakBush);
       

       var willow = register(context, TREE_WILLOW, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.WILLOW_WOOD_SET),
           new StraightTrunkPlacer(8, 2, 0),
           leaves(RUBlocks.WILLOW_NATURAL_SET),
           new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
           WillowRootPlacer.create(RUBlocks.WILLOW_WOOD_SET, 0.5f),
           new TwoLayersFeatureSize(1, 0, 1)
       ).build());
       
       var willowBig = register(context, TREE_BIG_WILLOW, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.WILLOW_WOOD_SET),
           new FancyTrunkPlacer(9, 9, 0),
           leaves(RUBlocks.WILLOW_NATURAL_SET),
           new WillowFoliagePlacer(0.25F),
           WillowRootPlacer.create(RUBlocks.WILLOW_WOOD_SET, 0.5f),
           new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
       ).ignoreVines().build());
       
       var willowSwamp = register(context, TREE_WILLOW_SWAMP, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           log(RUBlocks.WILLOW_WOOD_SET),
           new StraightTrunkPlacer(7, 2, 1),
           leaves(RUBlocks.WILLOW_NATURAL_SET),
           new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
           WillowRootPlacer.create(RUBlocks.WILLOW_WOOD_SET, 1),
           new TwoLayersFeatureSize(1, 0, 1)
       ).decorators(ImmutableList.of(new LeaveVineDecorator(0.25f))).build());
        
        var oakSwamp = register(context, TREE_OAK_SWAMP, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            new StraightTrunkPlacer(7, 2, 1),
            BlockStateProvider.simple(Blocks.OAK_LEAVES),
            new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
            WillowRootPlacer.create(Blocks.OAK_LOG, 0.5f),
            new TwoLayersFeatureSize(1, 0, 1)
        ).decorators(ImmutableList.of(new LeaveVineDecorator(0.25f))).build());
       
       
       registerSelector(context, TREE_GROUP_BAYOU, builder -> builder
           .add(direct(cypress), 2)
           .add(direct(willowSwamp), 2)
           .add(direct(oakBush), 1)
       );
       
       BlockStateProvider wisteriaLog = simple(RUBlocks.WISTERIA_WOOD_SET.getLog());
       var wisteriaSky = register(context, TREE_WISTERIA_SKY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new MagnoliaTrunkPlacer(UniformInt.of(2, 5), UniformInt.of(4, 5), UniformInt.of(2, 3)),
           simple(RUBlocks.SKY_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new MagnoliaFoliagePlacer(),
           Optional.of(new MagnoliaRootPlacer(ConstantInt.ZERO, wisteriaLog, Optional.empty())),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.SKY_WISTERIA_NATURAL_SET, 0.3f),
           new BeehiveDecorator(0.002f)
       )).build());
       var wisteriaLavender = register(context, TREE_WISTERIA_LAVENDER, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new MagnoliaTrunkPlacer(UniformInt.of(2, 5), UniformInt.of(4, 5), UniformInt.of(3, 4)),
           simple(RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new MagnoliaFoliagePlacer(),
           Optional.of(new MagnoliaRootPlacer(ConstantInt.ZERO, wisteriaLog, Optional.empty())),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.LAVENDER_WISTERIA_NATURAL_SET, 0.3f),
           new BeehiveDecorator(0.002f)
       )).build());
       var wisteriaSalmon = register(context, TREE_WISTERIA_SALMON, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new MagnoliaTrunkPlacer(UniformInt.of(2, 5), UniformInt.of(4, 5), UniformInt.of(2, 3)),
           simple(RUBlocks.SALMON_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new MagnoliaFoliagePlacer(),
           Optional.of(new MagnoliaRootPlacer(ConstantInt.ZERO, wisteriaLog, Optional.empty())),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.SALMON_WISTERIA_NATURAL_SET, 0.3f),
           new BeehiveDecorator(0.002f)
       )).build());
       
       var wisteriaLargeSky = register(context, TREE_WISTERIA_LARGE_SKY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new FancyTrunkPlacer(9, 9, 0),
           simple(RUBlocks.SKY_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new WillowFoliagePlacer(0.5F),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.SKY_WISTERIA_NATURAL_SET, 0.4f),
           new BeehiveDecorator(0.002f),
           RandomBranchDecorator.create(0.1f, RUBlocks.WISTERIA_NATURAL_SET, RUBlocks.WISTERIA_WOOD_SET, 3, simple(RUBlocks.SKY_WISTERIA_NATURAL_SET.getLeaves()))
       )).build());
       var wisteriaLargeLavender = register(context, TREE_WISTERIA_LARGE_LAVENDER, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new FancyTrunkPlacer(9, 9, 0),
           simple(RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new WillowFoliagePlacer(0.5F),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.LAVENDER_WISTERIA_NATURAL_SET, 0.4f),
           new BeehiveDecorator(0.002f),
           RandomBranchDecorator.create(0.1f, RUBlocks.WISTERIA_NATURAL_SET, RUBlocks.WISTERIA_WOOD_SET, 3, simple(RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getLeaves()))
       )).build());
       var wisteriaLargeSalmon = register(context, TREE_WISTERIA_LARGE_SALMON, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
           wisteriaLog,
           new FancyTrunkPlacer(9, 9, 0),
           simple(RUBlocks.SALMON_WISTERIA_NATURAL_SET.getLeaves().defaultBlockState()),
           new WillowFoliagePlacer(0.5F),
           new TwoLayersFeatureSize(1, 0, 1)
       ).ignoreVines().decorators(List.of(
           HangingVinesDecorator.create(RUBlocks.SALMON_WISTERIA_NATURAL_SET, 0.4f),
           new BeehiveDecorator(0.002f),
           RandomBranchDecorator.create(0.1f, RUBlocks.WISTERIA_NATURAL_SET, RUBlocks.WISTERIA_WOOD_SET, 3, simple(RUBlocks.SALMON_WISTERIA_NATURAL_SET.getLeaves()))
       )).build());
       
       registerSelector(context, TREE_GROUP_WILLOW_FOREST, builder -> builder
           .add(direct(willow), 5)
           .add(direct(willowBig), 3)
           .add(direct(smallOak), 1)
           .add(direct(blueMagnolia), 1)
       );
       
       registerSelector(context, TREE_GROUP_WISTERIA_GROVE, builder -> builder
           .add(direct(wisteriaSky), 1)
           .add(direct(wisteriaLavender), 1)
           .add(direct(wisteriaSalmon), 1)
           .add(direct(wisteriaLargeSky), 4)
           .add(direct(wisteriaLargeLavender), 4)
           .add(direct(wisteriaLargeSalmon), 4)
       );
    }

    private static BlockStateProvider log(WoodSet wood) {
       return simple(wood.getLog());
    }
    
    private static BlockStateProvider leaves(NaturalSet leaves) {
      return simple(leaves.getLeaves());
    }
    
    private static TreeConfiguration bushSmall(Block log, Block leaves) {
       return new TreeConfiguration.TreeConfigurationBuilder(
           simple(log),
           new StraightTrunkPlacer(1, 0, 0),
           simple(leaves),
           new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), ConstantInt.of(2)),
           new TwoLayersFeatureSize(0, 0, 0)
       ).build();
    }
    
    private static TreeConfiguration mapleSmall(NaturalSet natural, Supplier<Block> leafLitter) {
        return mapleSmall(RUBlocks.MAPLE_WOOD_SET.getLog(), natural.getLeaves(), RUBlocks.MAPLE_NATURAL_SET.getBranch(), leafLitter);
    }
    
    private static TreeConfiguration mapleSmall(Block log, Block leaves, Block branch, Supplier<Block> leafLitter) {
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(GroupBranchDecorator.createWithoutLeaves(1, branch, log, 3));
        if (leafLitter != null) {
            decorators.add(PlaceOnGroundDecorator.leafLitter(leafLitter.get(), 48));
        }
        
        return new TreeConfiguration.TreeConfigurationBuilder(
            simple(log),
            new StraightTrunkPlacer(7, 2, 2),
            simple(leaves),
            new MapleFoliagePlacer(),
            new TwoLayersFeatureSize(1, 0, 1)
        ).decorators(decorators).build();
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerPlaced(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<PlacedFeature> key, F feature, FC config) {
       context.register(RUConfiguredFeatures.fromPlaced(key), new ConfiguredFeature<>(feature, config));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder.Reference<ConfiguredFeature<?, ?>> register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        return context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
