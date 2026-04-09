package net.regions_unexplored.datagen.provider.registry;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.util.WeightedList;
import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.registry.tag.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RUDatagenFeatureUtils {
    
    // Block Predicates
    
    public static final BlockPredicate DIRT_OR_PODZOL_BELOW = BlockPredicate.matchesTag(Vec3i.ZERO.below(), RUBlockTags.DIRT_AND_PODZOL);
    
    // Registration
    
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder.Reference<ConfiguredFeature<?, ?>> register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        return context.register(key, new ConfiguredFeature<>(feature, config));
    }
    
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerPlaced(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<PlacedFeature> key, F feature, FC config) {
        context.register(RUConfiguredFeatures.fromPlaced(key), new ConfiguredFeature<>(feature, config));
    }
    
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerSelector(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<PlacedFeature> key, UnaryOperator<WeightedList.Builder<Holder<PlacedFeature>>> operator) {
        context.register(RUConfiguredFeatures.fromPlaced(key), new ConfiguredFeature<>(LithostitchedFeatures.WEIGHTED_SELECTOR, LithostitchedFeatures.weightedSelector(operator.apply(WeightedList.builder()).build())));
    }
    
    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, PlacementModifier... placement) {
        register(context, key, RUConfiguredFeatures.fromPlaced(key), placement);
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... placement) {
        register(context, key, context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), placement);
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... placement) {
        context.register(key, new PlacedFeature(feature, List.of(placement)));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placement) {
        context.register(key, new PlacedFeature(feature, placement));
    }


    public static Holder<PlacedFeature> direct(Holder.Reference<ConfiguredFeature<?, ?>> feature) {
        return Holder.direct(new PlacedFeature(feature, List.of()));
    }
    
    // Features
    
    public static ConfiguredFeature<?, ?> block(Block block) {
        return new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)));
    }
    
    public static Holder<PlacedFeature> inlinePlaced(ConfiguredFeature<?, ?> feature) {
        return Holder.direct(new PlacedFeature(Holder.direct(feature), List.of()));
    }
    
    public static RandomPatchConfiguration patch(Supplier<Block> block, int tries, int radiusXZ, int radiusY) {
        return new RandomPatchConfiguration(tries, radiusXZ, radiusY, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block.get()))));
    }
    
    public static RandomPatchConfiguration patch(BlockStateProvider stateProvider, int count) {
        return FeatureUtils.simpleRandomPatchConfiguration(count, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }
    
    public static RandomPatchConfiguration patch(BlockStateProvider stateProvider, int count, BlockPredicate predicate) {
        return FeatureUtils.simpleRandomPatchConfiguration(count, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, predicate)));
    }
    // Placement Modifiers
    
    public static PlacementModifier count(int count) {
        return CountPlacement.of(count);
    }
    
    public static PlacementModifier rarityFilter(int chance) {
        return RarityFilter.onAverageOnceEvery(chance);
    }
    
    public static PlacementModifier inSquare() {
        return InSquarePlacement.spread();
    }
    
    public static PlacementModifier notSubmerged() {
        return SurfaceWaterDepthFilter.forMaxDepth(0);
    }

    public static PlacementModifier airAndBlocksBelow(Block... blocks) {
        return BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), blocks)));
    }
    
    public static PlacementModifier[] surfaceSpread(double count, Heightmap.Types heightmap) {
        return spread(count, 0, heightmap);
    }

    public static PlacementModifier[] spread(double count, int maxWaterDepth, Heightmap.Types heightmap) {
        return new PlacementModifier[] {
            count >= 1 ? CountPlacement.of((int) count) : RarityFilter.onAverageOnceEvery((int) (1 / count)),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(maxWaterDepth),
            HeightmapPlacement.onHeightmap(heightmap),
            BiomeFilter.biome()
        };
    }
    
    public static PlacementModifier[] surfaceSpread(double count, Heightmap.Types heightmap, Block survivesBelow) {
        return spread(count, 0, heightmap, survivesBelow);
    }
    
    public static PlacementModifier[] spread(double count, int maxWaterDepth, Heightmap.Types heightmap, Block survivesBelow) {
        return new PlacementModifier[] {
            count >= 1 ? CountPlacement.of((int) count) : RarityFilter.onAverageOnceEvery((int) (1 / count)),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(maxWaterDepth),
            HeightmapPlacement.onHeightmap(heightmap),
            PlacementUtils.filteredByBlockSurvival(survivesBelow),
            BiomeFilter.biome()
        };
    }
    
    // Block State (Providers)

    public static BlockState state(Supplier<Block> block) {
        return block.get().defaultBlockState();
    }

    public static BlockState state(Block block) {
        return block.defaultBlockState();
    }

    @SafeVarargs
    public static BlockStateProvider weightedStates(Pair<BlockState, Integer>... entries) {
        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for (var pair : entries) {
            builder.add(pair.getFirst(), pair.getSecond());
        }
        return new WeightedStateProvider(builder);
    }

    @SafeVarargs
    public static WeightedListInt weightedInts(Pair<Integer, Integer>... entries) {
        SimpleWeightedRandomList.Builder<IntProvider> builder = SimpleWeightedRandomList.builder();
        for (var pair : entries) {
            builder.add(ConstantInt.of(pair.getFirst()), pair.getSecond());
        }
        return new WeightedListInt(builder.build());
    }

    public static Pair<BlockState, Integer> pair(Block block) {
        return pair(block.defaultBlockState(), 1);
    }

    public static Pair<BlockState, Integer> pair(Block block, int weight) {
        return pair(block.defaultBlockState(), weight);
    }

    public static <T> Pair<T, Integer> pair(T state) {
        return pair(state, 1);
    }

    public static <T> Pair<T, Integer> pair(T object, int weight) {
        return Pair.of(object, weight);
    }
}
