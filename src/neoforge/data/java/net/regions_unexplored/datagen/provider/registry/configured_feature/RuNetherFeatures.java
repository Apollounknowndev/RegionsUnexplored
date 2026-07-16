package net.regions_unexplored.datagen.provider.registry.configured_feature;

import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.world.level.feature.configuration.GiantBioshroomConfiguration;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneClusterConfiguration;
import net.regions_unexplored.world.level.feature.configuration.PointedRedstoneConfiguration;
import net.regions_unexplored.worldgen.stateprovider.RandomizedGroundCoverStateProvider;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;
import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuNetherPlacements.*;
import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;

public class RuNetherFeatures {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var bioshroomYellowLarge = register(context, TREE_YELLOW_BIOSHROOM_LARGE, RUFeatureTypes.GIANT_YELLOW_BIOSHROOM.get(), new GiantBioshroomConfiguration(BlockStateProvider.simple(RUBlocks.YELLOW_BIOSHROOM_WOOD_SET.getLog().defaultBlockState()), BlockStateProvider.simple(RUBlocks.YELLOW_BIOSHROOM_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(RUBlocks.GLOWING_YELLOW_BIOSHROOM_BLOCK.get().defaultBlockState()), 5, 4));
        var bioshroomYellowSmall = register(context, TREE_YELLOW_BIOSHROOM_SMALL, RUFeatureTypes.SMALL_YELLOW_BIOSHROOM.get(), FeatureConfiguration.NONE);
        registerSelector(context, TREE_GROUP_MYCOTOXIC_UNDERGROWTH, builder -> builder
            .add(direct(bioshroomYellowLarge), 3)
            .add(direct(bioshroomYellowSmall), 1)
        );
        registerPlaced(context, PATCH_YELLOW_BIOSHROOMS, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(RUBlocks.TALL_YELLOW_BIOSHROOM, 1), pair(RUBlocks.YELLOW_BIOSHROOM, 10)), 16));
        registerPlaced(context, PATCH_MYCOTOXIC_MUSHROOMS, Feature.RANDOM_PATCH, randomPatch(new RandomizedGroundCoverStateProvider(RUBlocks.MYCOTOXIC_MUSHROOMS.get()), 32));
        registerPlaced(context, PATCH_MYCOTOXIC_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.MYCOTOXIC_GRASS.get()), 32));
        registerPlaced(context, PATCH_MYCOTOXIC_DAISY, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.MYCOTOXIC_DAISY.get()), 16));

        var brimWillow = register(context, TREE_BRIM_WILLOW, RUFeatureTypes.BRIM_WILLOW.get(), FeatureConfiguration.NONE);
        var tallBrimWillow = register(context, TREE_TALL_BRIM_WILLOW, RUFeatureTypes.TALL_BRIM_WILLOW.get(), FeatureConfiguration.NONE);
        registerSelector(context, TREE_GROUP_INFERNAL_HOLT, builder -> builder
            .add(direct(brimWillow), 1)
            .add(direct(tallBrimWillow), 1)
        );
        registerPlaced(context, PATCH_BRIMSPROUT, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.BRIMSPROUT.get().defaultBlockState()), 32));
        registerPlaced(context, PATCH_INFERNAL_HOLT_FIRE, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(Blocks.FIRE.defaultBlockState()), 12, BlockPredicate.wouldSurvive(RUBlocks.BRIMSPROUT.get().defaultBlockState(), Vec3i.ZERO)));
        registerSingle(context, SINGLE_DORCEL, RUBlocks.DORCEL.get());
        registerSingle(context, SINGLE_BRIMWOOD_SHRUB, RUBlocks.BRIMWOOD_NATURAL_SET.getShrub());

        registerPlaced(context, GLISTERING_MEADOW_ROCK, RUFeatureTypes.NETHER_ROCK.get(), FeatureConfiguration.NONE);
        registerPlaced(context, PATCH_GLISTERING_IVY, RUFeatureTypes.GLISTERING_IVY.get(), FeatureConfiguration.NONE);
        registerPlaced(context, PATCH_GLISTERING_SPROUT, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.GLISTERING_SPROUT.get().defaultBlockState()), 32));
        registerPlaced(context, PATCH_GLISTERING_FERN, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.GLISTERING_FERN.get().defaultBlockState())));
        registerPlaced(context, PATCH_GLISTERING_BLOOM, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.GLISTERING_BLOOM.get().defaultBlockState())));
        registerPlaced(context, PATCH_GLISTER_SPIRE, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.GLISTER_SPIRE.get().defaultBlockState()), 16));
        registerPlaced(context, PATCH_GLISTER_BULB, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.GLISTER_BULB.get().defaultBlockState())));
        
        registerPlaced(context, PATCH_HANGING_EARLIGHT, RUFeatureTypes.HANGING_EARLIGHT.get(), FeatureConfiguration.NONE);
        registerPlaced(context, PATCH_COBALT_EARLIGHT, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.COBALT_EARLIGHT.get().defaultBlockState()), 6));
        registerPlaced(context, TALL_COBALT_EARLIGHT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.TALL_COBALT_EARLIGHT.get().defaultBlockState())));
        registerPlaced(context, PATCH_COBALT_ROOTS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.COBALT_ROOTS.get().defaultBlockState()), 32));
        registerPlaced(context, OBSIDIAN_SPIRE, RUFeatureTypes.OBSIDIAN_SPIRE.get(), FeatureConfiguration.NONE);
        var cobalt = register(context, TREE_COBALT, RUFeatureTypes.COBALT_TREE.get(), FeatureConfiguration.NONE);
        registerRedirector(context, TREE_GROUP_BLACKSTONE_BASIN, cobalt);
        
        registerPlaced(context, POINTED_REDSTONE, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1))), PlacementUtils.inlinePlaced(RUFeatureTypes.POINTED_REDSTONE.get(), new PointedRedstoneConfiguration(0.5F, 0.7F, 0.5F, 0.5F), EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1))))));
        registerPlaced(context, LARGE_POINTED_REDSTONE, LithostitchedFeatures.LARGE_DRIPSTONE, LithostitchedFeatures.largeDripstone(BlockStateProvider.simple(RUBlocks.RAW_REDSTONE_BLOCK.get()), context.lookup(Registries.BLOCK).getOrThrow(BlockTags.BASE_STONE_OVERWORLD), 30, UniformInt.of(1, 6), UniformFloat.of(0.4F, 2.0F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        registerPlaced(context, POINTED_REDSTONE_CLUSTER, RUFeatureTypes.POINTED_REDSTONE_CLUSTER.get(), new PointedRedstoneClusterConfiguration(12, UniformInt.of(3, 6), UniformInt.of(2, 8), 1, 3, UniformInt.of(2, 4), UniformFloat.of(0.3F, 0.7F), ClampedNormalFloat.of(0.0F, 0.0F, 0.0F, 0.0F), 0.1F, 3, 8));

        //BONEMEALS

        register(context, RUConfiguredFeatures.BONEMEAL_MYCOTOXIC_NYLIUM, Feature.NETHER_FOREST_VEGETATION, bonemeal(weightedStates(pair(RUBlocks.MYCOTOXIC_GRASS.get(), 95), pair(RUBlocks.YELLOW_BIOSHROOM.get(), 5))));
        register(context, RUConfiguredFeatures.BONEMEAL_GLISTERING_NYLIUM, Feature.NETHER_FOREST_VEGETATION, bonemeal(weightedStates(pair(RUBlocks.GLISTERING_SPROUT.get(), 4), pair(RUBlocks.GLISTERING_BLOOM.get(), 1))));
        register(context, RUConfiguredFeatures.BONEMEAL_COBALT_NYLIUM, Feature.NETHER_FOREST_VEGETATION, bonemeal(weightedStates(pair(RUBlocks.COBALT_ROOTS.get(), 99), pair(RUBlocks.COBALT_EARLIGHT.get(), 1))));
        register(context, RUConfiguredFeatures.BONEMEAL_BRIMSPROUT_NYLIUM, Feature.NETHER_FOREST_VEGETATION, bonemeal(weightedStates(pair(RUBlocks.BRIMSPROUT.get(), 99), pair(RUBlocks.DORCEL.get(), 1))));
    }
    
    private static NetherForestVegetationConfig bonemeal(BlockStateProvider provider) {
        return new NetherForestVegetationConfig(provider, 3, 1);
    }
}
