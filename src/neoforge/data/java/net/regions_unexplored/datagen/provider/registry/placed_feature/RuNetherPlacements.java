package net.regions_unexplored.datagen.provider.registry.placed_feature;

import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.registry.RUBlocks;

import static net.regions_unexplored.registry.data.RUPlacedFeatures.*;
import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;

public class RuNetherPlacements {
    public static final ResourceKey<PlacedFeature> TREE_GROUP_MYCOTOXIC_UNDERGROWTH = nether("tree/mycotoxic_undergrowth");
    public static final ResourceKey<PlacedFeature> PATCH_MYCOTOXIC_MUSHROOMS = nether("patch/mycotoxic_mushrooms");
    public static final ResourceKey<PlacedFeature> PATCH_MYCOTOXIC_GRASS = nether("patch/mycotoxic_grass");
    public static final ResourceKey<PlacedFeature> PATCH_MYCOTOXIC_DAISY = nether("patch/mycotoxic_daisy");
    public static final ResourceKey<PlacedFeature> PATCH_YELLOW_BIOSHROOMS = nether("patch/yellow_bioshrooms");
    
    public static final ResourceKey<PlacedFeature> TREE_GROUP_INFERNAL_HOLT = nether("tree/infernal_holt");
    public static final ResourceKey<PlacedFeature> PATCH_BRIMSPROUT = nether("patch/brimsprout");
    public static final ResourceKey<PlacedFeature> PATCH_INFERNAL_HOLT_FIRE = nether("patch/fire_unrestricted");
    public static final ResourceKey<PlacedFeature> SINGLE_DORCEL = nether("dorcel");
    public static final ResourceKey<PlacedFeature> SINGLE_BRIMWOOD_SHRUB = nether("brimwood_shrub");

    public static final ResourceKey<PlacedFeature> GLISTERING_MEADOW_ROCK = nether("glistering_meadow_rock");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTERING_IVY = nether("patch/glistering_ivy");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTERING_SPROUT = nether("patch/glistering_sprout");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTERING_FERN = nether("patch/glistering_fern");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTERING_BLOOM = nether("patch/glistering_bloom");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTER_SPIRE = nether("patch/glister_spire");
    public static final ResourceKey<PlacedFeature> PATCH_GLISTER_BULB = nether("patch/glister_bulb");

    public static final ResourceKey<PlacedFeature> TREE_GROUP_BLACKSTONE_BASIN = nether("tree/blackstone_basin");
    public static final ResourceKey<PlacedFeature> PATCH_HANGING_EARLIGHT = nether("patch/hanging_earlight");
    public static final ResourceKey<PlacedFeature> PATCH_COBALT_ROOTS = nether("patch/cobalt_roots");
    public static final ResourceKey<PlacedFeature> PATCH_COBALT_EARLIGHT = nether("patch/cobalt_earlight");
    public static final ResourceKey<PlacedFeature> TALL_COBALT_EARLIGHT = nether("tall_cobalt_earlight");
    public static final ResourceKey<PlacedFeature> OBSIDIAN_SPIRE = nether("obsidian_spire");

    public static final ResourceKey<PlacedFeature> POINTED_REDSTONE = nether("pointed_redstone");
    public static final ResourceKey<PlacedFeature> LARGE_POINTED_REDSTONE = nether("large_pointed_redstone");
    public static final ResourceKey<PlacedFeature> POINTED_REDSTONE_CLUSTER = nether("pointed_redstone_cluster");
    
    private static ResourceKey<PlacedFeature> nether(String name) {
        return key("nether/" + name);
    }
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        register(context, TREE_GROUP_MYCOTOXIC_UNDERGROWTH, placementNether(4).filter(RUBlocks.MYCOTOXIC_GRASS.get()));
        register(context, RuNetherPlacements.PATCH_MYCOTOXIC_MUSHROOMS, placementNether(2));
        register(context, RuNetherPlacements.PATCH_MYCOTOXIC_GRASS, placementNether(30));
        register(context, RuNetherPlacements.PATCH_MYCOTOXIC_DAISY, placementNether(3));
        register(context, RuNetherPlacements.PATCH_YELLOW_BIOSHROOMS, placementNether(3).filter(RUBlocks.MYCOTOXIC_GRASS.get()));

        register(context, RuNetherPlacements.TREE_GROUP_INFERNAL_HOLT, placementNether(4));
        register(context, RuNetherPlacements.PATCH_BRIMSPROUT, placementNether(30));
        register(context, RuNetherPlacements.PATCH_INFERNAL_HOLT_FIRE, placementNether(4));
        register(context, RuNetherPlacements.SINGLE_DORCEL, placementNether(4).filter(RUBlocks.BRIMSPROUT.get()));
        register(context, RuNetherPlacements.SINGLE_BRIMWOOD_SHRUB, placementNether(1).filter(RUBlocks.BRIMSPROUT.get()));

        register(context, RuNetherPlacements.GLISTERING_MEADOW_ROCK, placementNether(1).add(RarityFilter.onAverageOnceEvery(4)));
        register(context, RuNetherPlacements.PATCH_GLISTERING_IVY, CountPlacement.of(155), inSquare(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
        register(context, RuNetherPlacements.PATCH_GLISTERING_SPROUT, placementNether(10));
        register(context, RuNetherPlacements.PATCH_GLISTERING_FERN, placementNether(15));
        register(context, RuNetherPlacements.PATCH_GLISTERING_BLOOM, placementNether(7));
        register(context, RuNetherPlacements.PATCH_GLISTER_SPIRE, placementNether(3));
        register(context, RuNetherPlacements.PATCH_GLISTER_BULB, placementNether(2));

        register(context, RuNetherPlacements.PATCH_HANGING_EARLIGHT, CountPlacement.of(225), inSquare(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
        register(context, RuNetherPlacements.PATCH_COBALT_ROOTS, placementNether(8));
        register(context, RuNetherPlacements.PATCH_COBALT_EARLIGHT, placementNether(3));
        register(context, RuNetherPlacements.TALL_COBALT_EARLIGHT, placementNether(3));
        register(context, RuNetherPlacements.OBSIDIAN_SPIRE, placementNether(1));
        register(context, RuNetherPlacements.TREE_GROUP_BLACKSTONE_BASIN, placementNether(1).filter(RUBlocks.COBALT_EARLIGHT.get()));

        register(context, RuNetherPlacements.POINTED_REDSTONE, CountPlacement.of(UniformInt.of(192, 256)), inSquare(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, CountPlacement.of(UniformInt.of(1, 5)), RandomOffsetPlacement.of(ClampedNormalInt.of(0.0F, 3.0F, -10, 10), ClampedNormalInt.of(0.0F, 0.6F, -2, 2)), BiomeFilter.biome());
        register(context, RuNetherPlacements.LARGE_POINTED_REDSTONE, CountPlacement.of(UniformInt.of(10, 48)), inSquare(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
        register(context, RuNetherPlacements.POINTED_REDSTONE_CLUSTER, CountPlacement.of(UniformInt.of(78, 126)), inSquare(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
    }
}
