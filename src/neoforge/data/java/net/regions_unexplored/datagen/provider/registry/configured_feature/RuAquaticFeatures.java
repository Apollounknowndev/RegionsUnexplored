package net.regions_unexplored.datagen.provider.registry.configured_feature;

import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreadeableBlock;
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
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuAquaticFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CATTAIL = RUConfiguredFeatures.patch("cattail");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPECIAL_BLUE_MAGNOLIA = RUConfiguredFeatures.key("special/blue_magnolia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPECIAL_PINK_MAGNOLIA = RUConfiguredFeatures.key("special/pink_magnolia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPECIAL_WHITE_MAGNOLIA = RUConfiguredFeatures.key("special/white_magnolia");
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, PATCH_CATTAIL, LithostitchedFeatures.PLACED, randomPatch(24, 6, 0, PlacementUtils.inlinePlaced(Holder.direct(block(RUBlocks.CATTAIL.get())), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE))));
        
        registerPlaced(context, SPECIAL_TALL_HYACINTH_STOCK, RUFeatureTypes.TALL_HYACINTH_STOCK.get(), new HyacinthStockConfiguration(BlockStateProvider.simple(RUBlocks.TALL_HYACINTH_STOCK.get().defaultBlockState()), 1, 14));
        registerPlaced(context, SPECIAL_HYACINTH_PLANTS, RUFeatureTypes.HYACINTH_PLANTS.get(), new ProbabilityFeatureConfiguration(0.1F));
        HolderSet<Block> canPlaceOn = HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS);
        registerPlaced(context, SPECIAL_HYACINTH_FLOWERS, Feature.MULTIFACE_GROWTH, growth(RUBlocks.HYACINTH_FLOWERS.get(), canPlaceOn));
        registerPlaced(context, SPECIAL_HYACINTH_ROCKS, RUFeatureTypes.OCEAN_ROCK.get(), new SeaRockConfiguration(Blocks.STONE.defaultBlockState(), RUBlocks.MOSSY_STONE.get().defaultBlockState()));
        //ROCKY_REEF
        registerPlaced(context, SPECIAL_ROCKY_REEF_ROCKS, RUFeatureTypes.ROCK_PILLAR.get(), FeatureConfiguration.NONE);
        
        canPlaceOn = HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, RUBlocks.STONE_GRASS_BLOCK.get(), Blocks.JUNGLE_LOG, RUBlocks.PALM_WOOD_SET.getLog());
        var blueMagnolia = register(context, SPECIAL_BLUE_MAGNOLIA, Feature.MULTIFACE_GROWTH, growth(RUBlocks.BLUE_MAGNOLIA_FLOWERS.get(), canPlaceOn));
        var pinkMagnolia = register(context, SPECIAL_PINK_MAGNOLIA, Feature.MULTIFACE_GROWTH, growth(RUBlocks.PINK_MAGNOLIA_FLOWERS.get(), canPlaceOn));
        var whiteMagnolia = register(context, SPECIAL_WHITE_MAGNOLIA, Feature.MULTIFACE_GROWTH, growth(RUBlocks.WHITE_MAGNOLIA_FLOWERS.get(), canPlaceOn));
        registerSelector(context, SPECIAL_MAGNOLIAS, builder -> builder
            .add(direct(blueMagnolia))
            .add(direct(pinkMagnolia))
            .add(direct(whiteMagnolia))
        );
    }
    
    private static MultifaceGrowthConfiguration growth(MultifaceSpreadeableBlock block, HolderSet<Block> canPlaceOn) {
        return new MultifaceGrowthConfiguration(block, 20, false, true, true, 0.5f, canPlaceOn);
    }
}
