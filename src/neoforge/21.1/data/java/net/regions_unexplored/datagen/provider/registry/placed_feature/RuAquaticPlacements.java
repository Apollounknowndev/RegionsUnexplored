package net.regions_unexplored.datagen.provider.registry.placed_feature;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuAquaticFeatures;

import static net.regions_unexplored.registry.data.RUPlacedFeatures.*;
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuAquaticPlacements {
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL = patch("cattail");
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL_DENSE = patch("cattail_dense");

    public static final ResourceKey<PlacedFeature> SPECIAL_TALL_HYACINTH_STOCK = key("special/tall_hyacinth_stock");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_PLANTS = key("special/hyacinth_plants");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_FLOWERS = key("special/hyacinth_flowers");
    public static final ResourceKey<PlacedFeature> SPECIAL_HYACINTH_ROCKS = key("special/hyacinth_rocks");

    public static final ResourceKey<PlacedFeature> SPECIAL_ROCKY_REEF_ROCKS = key("special/rocky_reef_rocks");
    public static final ResourceKey<PlacedFeature> SPECIAL_MAGNOLIAS = key("special/magnolias");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> patchCattail = getter.getOrThrow(RuAquaticFeatures.PATCH_CATTAIL);


        register(context, RuAquaticPlacements.PATCH_CATTAIL_DENSE, patchCattail, placement().count(noiseCount(-3, 2, 3)).atHeight(VerticalAnchor.absolute(62)));
        register(context, RuAquaticPlacements.PATCH_CATTAIL, patchCattail, placement().count(3).atHeight(VerticalAnchor.absolute(62), VerticalAnchor.absolute(63)));
        register(context, VANILLA_SWAMP_CATTAILS, patchCattail, placement().count(0.5f).atHeight(VerticalAnchor.absolute(62)));

        register(context, RuAquaticPlacements.SPECIAL_TALL_HYACINTH_STOCK, NoiseBasedCountPlacement.of(20, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_PLANTS,  InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(80), BiomeFilter.biome());
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_FLOWERS, underwaterSpread(15));
        register(context, RuAquaticPlacements.SPECIAL_HYACINTH_ROCKS, underwaterSpread(1));
        register(context, RuAquaticPlacements.SPECIAL_ROCKY_REEF_ROCKS, NoiseBasedCountPlacement.of(1, 80.0D, 0.3D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

        register(context, RuAquaticPlacements.SPECIAL_MAGNOLIAS, placement(64, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES).filter(BlockPredicate.ONLY_IN_AIR_PREDICATE));
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
