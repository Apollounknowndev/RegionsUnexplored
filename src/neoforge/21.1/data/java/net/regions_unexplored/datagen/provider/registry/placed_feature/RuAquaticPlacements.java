package net.regions_unexplored.datagen.provider.registry.placed_feature;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuAquaticFeatures;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;

import static net.regions_unexplored.registry.data.RUPlacedFeatures.key;
import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.*;

import java.util.List;

public class RuAquaticPlacements {
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL = key("patch/cattail");
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL_DENSE = key("patch/cattail_dense");

    public static final ResourceKey<PlacedFeature> SPECIAL_TALL_HYACINTH_STOCK = key("special/tall_hyacinth_stock");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_PLANTS = key("special/hyacinth_plants");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_FLOWERS = key("special/hyacinth_flowers");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_ROCKS = key("special/hyacinth_rocks");

    public static final ResourceKey<PlacedFeature> MOSSY_SEA_ROCKS = key("mossy_sea_rocks");
    public static final ResourceKey<PlacedFeature> BLUE_MAGNOLIA_FLOWERS_AQUATIC = key("red_magnolia_flowers_aquatic");
    public static final ResourceKey<PlacedFeature> PINK_MAGNOLIA_FLOWERS_AQUATIC = key("pink_magnolia_flowers_aquatic");
    public static final ResourceKey<PlacedFeature> WHITE_MAGNOLIA_FLOWERS_AQUATIC = key("white_magnolia_flowers_aquatic");
    public static final ResourceKey<PlacedFeature> JUNGLE_AQUATIC = key("jungle_tree_aquatic");
    public static final ResourceKey<PlacedFeature> PALM_AQUATIC = key("palm_tree_aquatic");
    public static final ResourceKey<PlacedFeature> ELEPHANT_EAR_AQUATIC = key("elephant_ear_aquatic");
    public static final ResourceKey<PlacedFeature> PALM_SAPLING_AQUATIC = key("palm_sapling_aquatic");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> patchCattail = getter.getOrThrow(RuAquaticFeatures.PATCH_CATTAIL);

        final Holder<ConfiguredFeature<?, ?>> MOSSY_SEA_ROCKS = getter.getOrThrow(RuAquaticFeatures.MOSSY_SEA_ROCKS);
        final Holder<ConfiguredFeature<?, ?>> BLUE_MAGNOLIA_FLOWERS_AQUATIC = getter.getOrThrow(RuAquaticFeatures.BLUE_MAGNOLIA_FLOWERS_AQUATIC);
        final Holder<ConfiguredFeature<?, ?>> PINK_MAGNOLIA_FLOWERS_AQUATIC = getter.getOrThrow(RuAquaticFeatures.PINK_MAGNOLIA_FLOWERS_AQUATIC);
        final Holder<ConfiguredFeature<?, ?>> WHITE_MAGNOLIA_FLOWERS_AQUATIC = getter.getOrThrow(RuAquaticFeatures.WHITE_MAGNOLIA_FLOWERS_AQUATIC);
        final Holder<ConfiguredFeature<?, ?>> JUNGLE_AQUATIC = getter.getOrThrow(RuAquaticFeatures.JUNGLE_AQUATIC);
        final Holder<ConfiguredFeature<?, ?>> PALM_AQUATIC = getter.getOrThrow(RuAquaticFeatures.PALM_AQUATIC);
        final Holder<ConfiguredFeature<?, ?>> ELEPHANT_EAR_AQUATIC = getter.getOrThrow(RuAquaticFeatures.ELEPHANT_EAR_AQUATIC);


        register(context, RuAquaticPlacements.PATCH_CATTAIL_DENSE, patchCattail, count(6), inSquare(), HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))), BiomeFilter.biome());
        register(context, RuAquaticPlacements.PATCH_CATTAIL, patchCattail, count(3), inSquare(), HeightRangePlacement.uniform(VerticalAnchor.absolute(62), VerticalAnchor.absolute(63)), BiomeFilter.biome());

        register(context, RuAquaticPlacements.SPECIAL_TALL_HYACINTH_STOCK, NoiseBasedCountPlacement.of(20, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_PLANTS,  InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(80), BiomeFilter.biome());
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_FLOWERS, underwaterSpread(15));
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_ROCKS, underwaterSpread(1));
        register(context, RuAquaticPlacements.MOSSY_SEA_ROCKS, MOSSY_SEA_ROCKS, NoiseBasedCountPlacement.of(1, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

        register(context, RuAquaticPlacements.BLUE_MAGNOLIA_FLOWERS_AQUATIC, BLUE_MAGNOLIA_FLOWERS_AQUATIC, CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        register(context, RuAquaticPlacements.PINK_MAGNOLIA_FLOWERS_AQUATIC, PINK_MAGNOLIA_FLOWERS_AQUATIC, CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        register(context, RuAquaticPlacements.WHITE_MAGNOLIA_FLOWERS_AQUATIC, WHITE_MAGNOLIA_FLOWERS_AQUATIC, CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        register(context, RuAquaticPlacements.JUNGLE_AQUATIC, JUNGLE_AQUATIC, List.of(CountPlacement.of(24), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuAquaticPlacements.PALM_AQUATIC, PALM_AQUATIC, List.of(CountPlacement.of(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuAquaticPlacements.PALM_SAPLING_AQUATIC, getter.getOrThrow(RUConfiguredFeatures.shrub("palm")), List.of(CountPlacement.of(5), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuAquaticPlacements.ELEPHANT_EAR_AQUATIC, ELEPHANT_EAR_AQUATIC, List.of(CountPlacement.of(8), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));

    }
    
    public static PlacementModifier[] underwaterSpread(double count) {
        return new PlacementModifier[] {
            count >= 1 ? CountPlacement.of((int) count) : RarityFilter.onAverageOnceEvery((int) (1 / count)),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        };
    }
}
