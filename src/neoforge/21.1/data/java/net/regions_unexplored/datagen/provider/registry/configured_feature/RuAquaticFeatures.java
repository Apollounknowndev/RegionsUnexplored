package net.regions_unexplored.datagen.provider.registry.configured_feature;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.world.level.feature.configuration.HyacinthStockConfiguration;
import net.regions_unexplored.world.level.feature.configuration.RUTreeConfiguration;
import net.regions_unexplored.world.level.feature.configuration.SeaRockConfiguration;

import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuAquaticPlacements.*;
import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.*;

public class RuAquaticFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CATTAIL = RUConfiguredFeatures.key("patch/cattail");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_SEA_ROCKS = RUConfiguredFeatures.key("mossy_sea_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_MAGNOLIA_FLOWERS_AQUATIC = RUConfiguredFeatures.key("red_magnolia_flowers_aquatic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_MAGNOLIA_FLOWERS_AQUATIC = RUConfiguredFeatures.key("pink_magnolia_flowers_aquatic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_MAGNOLIA_FLOWERS_AQUATIC = RUConfiguredFeatures.key("white_magnolia_flowers_aquatic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_AQUATIC = RUConfiguredFeatures.key("jungle_tree_aquatic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_AQUATIC = RUConfiguredFeatures.key("palm_tree_aquatic");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ELEPHANT_EAR_AQUATIC = RUConfiguredFeatures.key("elephant_ear_aquatic");
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, PATCH_CATTAIL, Feature.RANDOM_PATCH, new RandomPatchConfiguration(24, 6, 0, PlacementUtils.inlinePlaced(Holder.direct(block(RUBlocks.CATTAIL.get())), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE))));
        
        registerPlaced(context, SPECIAL_TALL_HYACINTH_STOCK, RUFeatureTypes.TALL_HYACINTH_STOCK.get(), new HyacinthStockConfiguration(BlockStateProvider.simple(RUBlocks.TALL_HYACINTH_STOCK.get().defaultBlockState()), 1, 14));
        registerPlaced(context, SPECIAL_HYACINTH_PLANTS, RUFeatureTypes.HYACINTH_PLANTS.get(), new ProbabilityFeatureConfiguration(0.1F));
        registerPlaced(context, SPECIAL_HYACINTH_FLOWERS, Feature.MULTIFACE_GROWTH,
            new MultifaceGrowthConfiguration((MultifaceBlock) RUBlocks.HYACINTH_FLOWERS.get(), 20, true, true, true, 1.0F, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS))
        );
        registerPlaced(context, SPECIAL_HYACINTH_ROCKS, RUFeatureTypes.OCEAN_ROCK.get(), new SeaRockConfiguration(Blocks.STONE.defaultBlockState(), RUBlocks.MOSSY_STONE.get().defaultBlockState()));
        //ROCKY_REEF
        register(context, MOSSY_SEA_ROCKS, RUFeatureTypes.ROCK_PILLAR.get(), FeatureConfiguration.NONE);
        register(context, BLUE_MAGNOLIA_FLOWERS_AQUATIC, RUFeatureTypes.AIR_MULTIFACE_GROWTH.get(), new MultifaceGrowthConfiguration((MultifaceBlock) RUBlocks.BLUE_MAGNOLIA_FLOWERS.get(), 20, true, true, true, 1.0F, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, RUBlocks.STONE_GRASS_BLOCK.get())));
        register(context, PINK_MAGNOLIA_FLOWERS_AQUATIC, RUFeatureTypes.AIR_MULTIFACE_GROWTH.get(), new MultifaceGrowthConfiguration((MultifaceBlock) RUBlocks.PINK_MAGNOLIA_FLOWERS.get(), 20, true, true, true, 1.0F, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, RUBlocks.STONE_GRASS_BLOCK.get())));
        register(context, WHITE_MAGNOLIA_FLOWERS_AQUATIC, RUFeatureTypes.AIR_MULTIFACE_GROWTH.get(), new MultifaceGrowthConfiguration((MultifaceBlock) RUBlocks.WHITE_MAGNOLIA_FLOWERS.get(), 20, true, true, true, 1.0F, HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, RUBlocks.STONE_GRASS_BLOCK.get())));
        register(context, JUNGLE_AQUATIC, RUFeatureTypes.SAKURA_TREE.get(), new RUTreeConfiguration(BlockStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState()), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()), BlockStateProvider.simple(RUBlocks.JUNGLE_NATURAL_SET.getBranch().defaultBlockState()), 1, 4));

        register(context, PALM_AQUATIC, RUFeatureTypes.PALM_TREE.get(), new RUTreeConfiguration(BlockStateProvider.simple(RUBlocks.PALM_WOOD_SET.getLog().defaultBlockState()), BlockStateProvider.simple(RUBlocks.PALM_NATURAL_SET.getLeaves().defaultBlockState()), BlockStateProvider.simple(RUBlocks.PALM_NATURAL_SET.getBranch().defaultBlockState()), 8, 4));
        register(context, ELEPHANT_EAR_AQUATIC, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(RUBlocks.ELEPHANT_EAR.get().defaultBlockState()), 32));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int i) {
        return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }
}
