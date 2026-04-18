package net.regions_unexplored.datagen.provider.registry.placed_feature;

import dev.worldgen.lithostitched.api.worldgen.placementmodifier.LithostitchedPlacementModifiers;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuVegetationFeatures;
import net.regions_unexplored.datagen.provider.registry.RUPlacedFeatureBootstrap;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUPlacedFeatures;

import static net.regions_unexplored.datagen.provider.registry.RUDatagenFeatureUtils.*;
import static net.regions_unexplored.registry.data.RUPlacedFeatures.key;

import java.util.List;

public class RuVegetationPlacements {
    //-----------------------KEYS-----------------------//
    public static final ResourceKey<PlacedFeature> BLACKWOOD_BIOSHROOMS = key("blackwood_bioshrooms");
    public static final ResourceKey<PlacedFeature> BLACKWOOD_DECORATION = key("blackwood_decoration");
    public static final ResourceKey<PlacedFeature> MEADOW_VEGETATION = key("meadow_vegetation");
    //GRASS
    public static final ResourceKey<PlacedFeature> SANDY_GRASS = key("sandy_grass");
    public static final ResourceKey<PlacedFeature> FERNS = key("ferns");
    public static final ResourceKey<PlacedFeature> GRASS = key("grass");
    public static final ResourceKey<PlacedFeature> CAVE_GRASS = key("cave_grass");
    public static final ResourceKey<PlacedFeature> TALL_GRASS = key("tall_grass");
    public static final ResourceKey<PlacedFeature> CAVE_TALL_GRASS = key("cave_tall_grass");
    public static final ResourceKey<PlacedFeature> SNOW_GRASS = key("snow_grass");
    public static final ResourceKey<PlacedFeature> WINDSWEPT_GRASS = key("windswept_grass");


    public static final ResourceKey<PlacedFeature> PATCH_GRASS_SPROUTS_DENSE = key("patch/grass_sprouts_dense");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_SPROUTS_SPARSE = key("patch/grass_sprouts_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_ASHEN_GRASS = key("patch/ashen_grass");
    public static final ResourceKey<PlacedFeature> PATCH_ASHEN_GRASS_SMOULDERING = key("patch/smouldering_ashen_grass");
    public static final ResourceKey<PlacedFeature> PATCH_LUPINES = key("patch/lupines");
    public static final ResourceKey<PlacedFeature> PATCH_ASH_VENTS = key("patch/ash_vents");
    public static final ResourceKey<PlacedFeature> PATCH_FERNS_DENSE = key("patch/ferns_dense");
    public static final ResourceKey<PlacedFeature> PATCH_CLOVERS_DENSE = key("patch/clovers_dense");
    public static final ResourceKey<PlacedFeature> PATCH_DAISIES = key("patch/daisies");
    public static final ResourceKey<PlacedFeature> PATCH_TUNDRA_FLOWERS = key("patch/tundra_flowers");

    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS_SPARSE = key("patch/short_grass_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS = key("patch/short_grass");
    public static final ResourceKey<PlacedFeature> PATCH_SHORT_GRASS_DENSE = key("patch/short_grass_dense");
    public static final ResourceKey<PlacedFeature> PATCH_FERN = key("patch/fern");
    public static final ResourceKey<PlacedFeature> PATCH_STEPPE_GRASS = key("patch/steppe_grass");
    public static final ResourceKey<PlacedFeature> PATCH_SANDY_GRASS_SPARSE = key("patch/sandy_grass_sparse");
    public static final ResourceKey<PlacedFeature> PATCH_DESERT_SHRUB_ON_GRASS = key("patch/desert_shrub_on_grass");
    public static final ResourceKey<PlacedFeature> PATCH_DESERT_SHRUB_ON_SAND = key("patch/desert_shrub_on_sand");
    public static final ResourceKey<PlacedFeature> PATCH_STEPPE_SHRUB_ON_SAND = key("patch/steppe_shrub_on_sand");


    public static final ResourceKey<PlacedFeature> BLACKWOOD_VEGETATION = key("blackwood_vegetation");
    public static final ResourceKey<PlacedFeature> DECIDUOUS_VEGETATION = key("deciduous_vegetation");
    public static final ResourceKey<PlacedFeature> FEN_VEGETATION = key("fen_vegetation");
    public static final ResourceKey<PlacedFeature> SHRUBLAND_VEGETATION = key("shrubland_vegetation");
    public static final ResourceKey<PlacedFeature> MOUNTAIN_VEGETATION = key("mountain_vegetation");
    public static final ResourceKey<PlacedFeature> STEPPE_VEGETATION = key("steppe_vegetation");
    public static final ResourceKey<PlacedFeature> SOCOTRA_VEGETATION = key("socotra_vegetation");
    public static final ResourceKey<PlacedFeature> BAYOU_VEGETATION = key("bayou_vegetation");
    public static final ResourceKey<PlacedFeature> DIRT_VEGETATION = key("dirt_vegetation");
    public static final ResourceKey<PlacedFeature> SANDY_GRASS_VEGETATION = key("sandy_grass_vegetation");
    public static final ResourceKey<PlacedFeature> GRASS_VEGETATION = key("grass_vegetation");
    public static final ResourceKey<PlacedFeature> REDSTONE_BUD = key("redstone_bud");
    public static final ResourceKey<PlacedFeature> PRISMOSS_SPROUT = key("prismoss_sprout");
    public static final ResourceKey<PlacedFeature> BLADED_GRASS = key("bladed_grass");
    //FLOWERS
    public static final ResourceKey<PlacedFeature> CAVE_HYSSOP = key("cave_hyssop");
    public static final ResourceKey<PlacedFeature> ASTER = key("aster");
    public static final ResourceKey<PlacedFeature> TULIPS = key("tulips");
    public static final ResourceKey<PlacedFeature> SMALL_FLOWERS = key("small_flowers");
    public static final ResourceKey<PlacedFeature> TALL_FLOWERS = key("tall_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_ALPHA_DANDELION = key("patch/alpha_dandelion");
    public static final ResourceKey<PlacedFeature> PATCH_ALPHA_ROSE = key("patch/alpha_rose");
    public static final ResourceKey<PlacedFeature> PATCH_WILTING_TRILLIUM = key("patch_wilting_trillium");
    public static final ResourceKey<PlacedFeature> WHITE_TRILLIUM = key("white_trillium");
    public static final ResourceKey<PlacedFeature> AZURE_DAISY = key("azure_daisy");
    public static final ResourceKey<PlacedFeature> DAISY = key("daisy");
    public static final ResourceKey<PlacedFeature> WARATAH = key("waratah");
    public static final ResourceKey<PlacedFeature> PRAIRIE_FLOWERS = key("prairie_flowers");
    public static final ResourceKey<PlacedFeature> SHRUBLAND_FLOWERS = key("shrubland_flowers");
    public static final ResourceKey<PlacedFeature> WILLOW_FLOWERS = key("willow_flowers");
    public static final ResourceKey<PlacedFeature> PATCH_POPPIES = key("patch/poppies");
    public static final ResourceKey<PlacedFeature> TASSEL_SPARSE = key("tassel_sparse");
    public static final ResourceKey<PlacedFeature> TASSEL_DENSE = key("tassel_dense");
    public static final ResourceKey<PlacedFeature> CORPSE_FLOWER = key("corpse_flower");
    public static final ResourceKey<PlacedFeature> DUSKTRAP = key("dusktrap");
    public static final ResourceKey<PlacedFeature> DAY_LILY = key("day_lily");
    public static final ResourceKey<PlacedFeature> TSUBAKI = key("tsubaki");
    public static final ResourceKey<PlacedFeature> HIBISCUS = key("hibiscus");
    public static final ResourceKey<PlacedFeature> MALLOW = key("mallow");
    public static final ResourceKey<PlacedFeature> HYSSOP = key("hyssop");
    public static final ResourceKey<PlacedFeature> FROZEN_FLOWERS = key("frozen_flowers");
    public static final ResourceKey<PlacedFeature> PINK_FLOWERS = key("pink_flowers");
    public static final ResourceKey<PlacedFeature> SNOWBELLE = key("snowbelle");
    public static final ResourceKey<PlacedFeature> BARLEY_SPARSE = key("barley_sparse");
    public static final ResourceKey<PlacedFeature> BARLEY_DENSE = key("barley_dense");
    public static final ResourceKey<PlacedFeature> MEADOW_SAGE = key("meadow_sage");
    public static final ResourceKey<PlacedFeature> REDSTONE_BULB = key("redstone_bulb");

    public static final ResourceKey<PlacedFeature> PATCH_ORANGE_CONEFLOWER = key("patch/orange_coneflower");
    public static final ResourceKey<PlacedFeature> PATCH_PURPLE_CONEFLOWER = key("patch/purple_coneflower");
    //FOOD_PLANTS
    public static final ResourceKey<PlacedFeature> RARE_SALMONBERRY_BUSH = key("rare_salmonberry_bush");
    public static final ResourceKey<PlacedFeature> DUSKMELON = key("duskmelon");
    //BIOSHROOM
    public static final ResourceKey<PlacedFeature> BLUE_BIOSHROOM = key("blue_bioshroom");
    public static final ResourceKey<PlacedFeature> GREEN_BIOSHROOM = key("green_bioshroom");
    public static final ResourceKey<PlacedFeature> PINK_BIOSHROOM = key("pink_bioshroom");
    public static final ResourceKey<PlacedFeature> PINK_BIOSHROOM_DENSE = key("pink_bioshroom_dense");
    //OTHER
    public static final ResourceKey<PlacedFeature> CACTUS_DENSE = key("cactus_dense");
    public static final ResourceKey<PlacedFeature> BARREL_CACTUS = key("barrel_cactus");
    public static final ResourceKey<PlacedFeature> BAMBOO = key("bamboo");
    public static final ResourceKey<PlacedFeature> FLOWERING_LILY = key("flowering_lily");
    public static final ResourceKey<PlacedFeature> GIANT_LILY = key("giant_lily");
    public static final ResourceKey<PlacedFeature> ELEPHANT_EAR_SPARSE = key("elephant_ear_sparse");
    public static final ResourceKey<PlacedFeature> ELEPHANT_EAR_DENSE = key("elephant_ear_dense");
    public static final ResourceKey<PlacedFeature> DROPLEAF = key("dropleaf");
    public static final ResourceKey<PlacedFeature> DUCKWEED = key("duckweed");
    //SHRUBS
    public static final ResourceKey<PlacedFeature> ASHEN_SHRUB = key("shrub/ashen");
    public static final ResourceKey<PlacedFeature> ACACIA_SHRUB = key("shrub/acacia");
    public static final ResourceKey<PlacedFeature> CHERRY_SHRUB = key("shrub/cherry");
    public static final ResourceKey<PlacedFeature> EUCALYPTUS_SHRUB = key("shrub/eucalyptus");
    public static final ResourceKey<PlacedFeature> FLOWERING_SHRUB = key("shrub/flowering");
    public static final ResourceKey<PlacedFeature> JOSHUA_SHRUB = key("shrub/joshua");
    public static final ResourceKey<PlacedFeature> LARCH_SHRUB = key("shrub/larch");
    public static final ResourceKey<PlacedFeature> OAK_SHRUB = key("shrub/oak");
    public static final ResourceKey<PlacedFeature> PINE_SHRUB = key("shrub/pine");
    public static final ResourceKey<PlacedFeature> REDWOOD_SHRUB = key("shrub/redwood");
    public static final ResourceKey<PlacedFeature> SOCOTRA_SHRUB = key("shrub/socotra");
    public static final ResourceKey<PlacedFeature> SPRUCE_SHRUB = key("shrub/spruce");
    //mixes
    public static final ResourceKey<PlacedFeature> BAOBAB_ACACIA_SHRUB_MIX = key("baobab_acacia_shrub_mix");
    public static final ResourceKey<PlacedFeature> AUTUMNAL_SHRUB_MIX = key("autumnal_shrub_mix");
    public static final ResourceKey<PlacedFeature> BLACKWOOD_DARK_OAK_SHRUB_MIX = key("blackwood_dark_oak_shrub_mix");
    public static final ResourceKey<PlacedFeature> MAGNOLIA_SHRUB_MIX = key("magnolia_shrub_mix");
    public static final ResourceKey<PlacedFeature> GOLDEN_LARCH_SHRUB_MIX = key("golden_larch_shrub_mix");
    public static final ResourceKey<PlacedFeature> MAPLE_SHRUB_MIX = key("maple_shrub_mix");
    public static final ResourceKey<PlacedFeature> MAUVE_ENCHANTED_SHRUB_MIX = key("mauve_enchanted_shrub_mix");
    public static final ResourceKey<PlacedFeature> PALM_JUNGLE_SHRUB_MIX = key("palm_jungle_shrub_mix");
    public static final ResourceKey<PlacedFeature> PINE_DEAD_SHRUB_MIX = key("pine_dead_shrub_mix");
    public static final ResourceKey<PlacedFeature> WILLOW_CYPRESS_SHRUB_MIX = key("willow_cypress_shrub_mix");
    public static final ResourceKey<PlacedFeature> WILLOW_MAGNOLIA_SHRUB_MIX = key("willow_magnolia_shrub_mix");

    public static final ResourceKey<PlacedFeature> PRAIRIE_TREES = key("prairie_trees");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        
        Holder<ConfiguredFeature<?, ?>> patchGrass = getter.getOrThrow(RuVegetationFeatures.PATCH_SHORT_GRASS);

        //---------------------FEATURES---------------------//
        final Holder<ConfiguredFeature<?, ?>> BLACKWOOD_DECORATION = getter.getOrThrow(RuVegetationFeatures.BLACKWOOD_DECORATION);
        final Holder<ConfiguredFeature<?, ?>> MEADOW_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_MEADOW_VEGETATION);
        //GRASS
        final Holder<ConfiguredFeature<?, ?>> SANDY_GRASS = getter.getOrThrow(RuVegetationFeatures.SANDY_GRASS);
        final Holder<ConfiguredFeature<?, ?>> SNOW_GRASS = getter.getOrThrow(RuVegetationFeatures.PATCH_SNOW_GRASS);
        final Holder<ConfiguredFeature<?, ?>> FERNS = getter.getOrThrow(RuVegetationFeatures.PATCH_FERNS);
        final Holder<ConfiguredFeature<?, ?>> TALL_GRASS = getter.getOrThrow(RuVegetationFeatures.PATCH_TALL_GRASS);
        final Holder<ConfiguredFeature<?, ?>> WINDSWEPT_GRASS = getter.getOrThrow(RuVegetationFeatures.PATCH_WINDSWEPT_GRASS);
        final Holder<ConfiguredFeature<?, ?>> PATCH_GRASS_SPROUTS = getter.getOrThrow(RuVegetationFeatures.PATCH_GRASS_SPROUTS);
        final Holder<ConfiguredFeature<?, ?>> BLACKWOOD_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_BLACKWOOD_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> DECIDUOUS_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_DECIDUOUS_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> FEN_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_FEN_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> SHRUBLAND_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_SHRUBLAND_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> MOUNTAIN_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_MOUNTAIN_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> STEPPE_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_STEPPE_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> SOCOTRA_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_SOCOTRA_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> BAYOU_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_BAYOU_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> SANDY_GRASS_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_SANDY_GRASS_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> DIRT_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_DIRT_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> GRASS_VEGETATION = getter.getOrThrow(RuVegetationFeatures.PATCH_GRASS_VEGETATION);
        final Holder<ConfiguredFeature<?, ?>> REDSTONE_BUD = getter.getOrThrow(RuVegetationFeatures.PATCH_REDSTONE_BUD);
        final Holder<ConfiguredFeature<?, ?>> PRISMOSS_SPROUT = getter.getOrThrow(RuVegetationFeatures.PATCH_PRISMOSS_SPROUT);
        final Holder<ConfiguredFeature<?, ?>> BLADED_GRASS = getter.getOrThrow(RuVegetationFeatures.PATCH_BLADED_GRASS);
       //FLOWERS
        final Holder<ConfiguredFeature<?, ?>> ASTER = getter.getOrThrow(RuVegetationFeatures.ASTER);
        final Holder<ConfiguredFeature<?, ?>> TULIPS = getter.getOrThrow(RuVegetationFeatures.PATCH_TULIPS);
        final Holder<ConfiguredFeature<?, ?>> CAVE_HYSSOP = getter.getOrThrow(RuVegetationFeatures.PATCH_CAVE_HYSSOP);
        final Holder<ConfiguredFeature<?, ?>> SMALL_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_SMALL_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> TALL_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_TALL_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> WHITE_TRILLIUM = getter.getOrThrow(RuVegetationFeatures.PATCH_WHITE_TRILLIUM);
        final Holder<ConfiguredFeature<?, ?>> AZURE_DAISY = getter.getOrThrow(RuVegetationFeatures.PATCH_AZURE_DAISY);
        final Holder<ConfiguredFeature<?, ?>> WARATAH = getter.getOrThrow(RuVegetationFeatures.PATCH_WARATAH);
        final Holder<ConfiguredFeature<?, ?>> DAISY = getter.getOrThrow(RuVegetationFeatures.PATCH_DAISY);
        final Holder<ConfiguredFeature<?, ?>> PRAIRIE_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_PRAIRIE_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> SHRUBLAND_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_SHRUBLAND_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> WILLOW_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_WILLOW_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> TASSEL = getter.getOrThrow(RuVegetationFeatures.TASSEL);
        final Holder<ConfiguredFeature<?, ?>> SNOWBELLE = getter.getOrThrow(RuVegetationFeatures.WHITE_SNOWBELLE);
        final Holder<ConfiguredFeature<?, ?>> CORPSE_FLOWER = getter.getOrThrow(RuVegetationFeatures.CORPSE_FLOWER);
        final Holder<ConfiguredFeature<?, ?>> DUSKTRAP = getter.getOrThrow(RuVegetationFeatures.DUSKTRAP);
        final Holder<ConfiguredFeature<?, ?>> DAY_LILY = getter.getOrThrow(RuVegetationFeatures.DAY_LILY);
        final Holder<ConfiguredFeature<?, ?>> TSUBAKI = getter.getOrThrow(RuVegetationFeatures.PATCH_TSUBAKI);
        final Holder<ConfiguredFeature<?, ?>> HIBISCUS = getter.getOrThrow(RuVegetationFeatures.PATCH_HIBISCUS);
        final Holder<ConfiguredFeature<?, ?>> MALLOW = getter.getOrThrow(RuVegetationFeatures.PATCH_MALLOW);
        final Holder<ConfiguredFeature<?, ?>> HYSSOP = getter.getOrThrow(RuVegetationFeatures.PATCH_HYSSOP);
        final Holder<ConfiguredFeature<?, ?>> FROZEN_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_FROZEN_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> PINK_FLOWERS = getter.getOrThrow(RuVegetationFeatures.PATCH_PINK_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> BARLEY = getter.getOrThrow(RuVegetationFeatures.PATCH_BARLEY);
        final Holder<ConfiguredFeature<?, ?>> MEADOW_SAGE = getter.getOrThrow(RuVegetationFeatures.MEADOW_SAGE);
        final Holder<ConfiguredFeature<?, ?>> REDSTONE_BULB = getter.getOrThrow(RuVegetationFeatures.PATCH_REDSTONE_BULB);

        final Holder<ConfiguredFeature<?, ?>> CLOVER = getter.getOrThrow(RuVegetationFeatures.PATCH_CLOVER);
        //FOOD_PLANTS
        final Holder<ConfiguredFeature<?, ?>> SALMONBERRY_BUSH = getter.getOrThrow(RuVegetationFeatures.PATCH_SALMONBERRY_BUSH);
        final Holder<ConfiguredFeature<?, ?>> DUSKMELON = getter.getOrThrow(RuVegetationFeatures.DUSKMELON);
        //BIOSHROOM
        final Holder<ConfiguredFeature<?, ?>> BLUE_BIOSHROOM = getter.getOrThrow(RuVegetationFeatures.PATCH_BLUE_BIOSHROOM);
        final Holder<ConfiguredFeature<?, ?>> GREEN_BIOSHROOM = getter.getOrThrow(RuVegetationFeatures.PATCH_GREEN_BIOSHROOM);
        final Holder<ConfiguredFeature<?, ?>> PINK_BIOSHROOM = getter.getOrThrow(RuVegetationFeatures.PATCH_PINK_BIOSHROOM);
        //OTHER
        final Holder<ConfiguredFeature<?, ?>> CACTUS = getter.getOrThrow(VegetationFeatures.PATCH_CACTUS);
        final Holder<ConfiguredFeature<?, ?>> BARREL_CACTUS = getter.getOrThrow(RuVegetationFeatures.BARREL_CACTUS);
        final Holder<ConfiguredFeature<?, ?>> BAMBOO = getter.getOrThrow(RuVegetationFeatures.BAMBOO);
        final Holder<ConfiguredFeature<?, ?>> FLOWERING_LILY = getter.getOrThrow(RuVegetationFeatures.FLOWERING_LILY);
        final Holder<ConfiguredFeature<?, ?>> GIANT_LILY = getter.getOrThrow(RuVegetationFeatures.GIANT_LILY);
        final Holder<ConfiguredFeature<?, ?>> ELEPHANT_EAR = getter.getOrThrow(RuVegetationFeatures.ELEPHANT_EAR);
        final Holder<ConfiguredFeature<?, ?>> DROPLEAF = getter.getOrThrow(RuVegetationFeatures.DROPLEAF);
        final Holder<ConfiguredFeature<?, ?>> DUCKWEED = getter.getOrThrow(RuVegetationFeatures.DUCKWEED);
        // Shrub Mixes
        final Holder<ConfiguredFeature<?, ?>> BAOBAB_ACACIA_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.BAOBAB_ACACIA_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> AUTUMNAL_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.AUTUMNAL_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> BLACKWOOD_DARK_OAK_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.BLACKWOOD_DARK_OAK_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> GOLDEN_LARCH_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.GOLDEN_LARCH_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> MAPLE_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.MAPLE_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> MAUVE_ENCHANTED_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.MAUVE_ENCHANTED_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> PALM_JUNGLE_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.PALM_JUNGLE_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> PINE_DEAD_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.PINE_DEAD_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> WILLOW_CYPRESS_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.WILLOW_CYPRESS_SHRUB_MIX);
        final Holder<ConfiguredFeature<?, ?>> WILLOW_MAGNOLIA_SHRUB_MIX = getter.getOrThrow(RuVegetationFeatures.WILLOW_MAGNOLIA_SHRUB_MIX);

        final Holder<ConfiguredFeature<?, ?>> PRAIRIE_MIX = getter.getOrThrow(RuVegetationFeatures.PRAIRIE_MIX);

        //--------------------PLACEMENTS--------------------//
        register(context, RuVegetationPlacements.PINK_FLOWERS, PINK_FLOWERS, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.FROZEN_FLOWERS, FROZEN_FLOWERS, RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.BLACKWOOD_BIOSHROOMS,
            count(2),
            inSquare(),
            notSubmerged(),
            HeightmapPlacement.onHeightmap(Types.OCEAN_FLOOR_WG),
            SurfaceRelativeThresholdFilter.of(Types.OCEAN_FLOOR, Integer.MIN_VALUE, -8),
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                BlockPredicate.wouldSurvive(RUBlocks.BLUE_BIOSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                BlockPredicate.ONLY_IN_AIR_PREDICATE
            )),
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.BLACKWOOD_DECORATION, BLACKWOOD_DECORATION, List.of(CountPlacement.of(8), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.MEADOW_VEGETATION, MEADOW_VEGETATION, NoiseThresholdCountPlacement.of(-0.8D, 5, 8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        //GRASS
        register(context, RuVegetationPlacements.SANDY_GRASS, SANDY_GRASS, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.FERNS, FERNS, surfaceSpread(7, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.GRASS, patchGrass, surfaceSpread(6, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.CAVE_GRASS, patchGrass, List.of(CountOnEveryLayerPlacement.of(70), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE),  BiomeFilter.biome()));
        register(context, RuVegetationPlacements.TALL_GRASS, TALL_GRASS, surfaceSpread(7, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.CAVE_TALL_GRASS, TALL_GRASS, List.of(CountOnEveryLayerPlacement.of(15), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.SNOW_GRASS, SNOW_GRASS, surfaceSpread(7, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.WINDSWEPT_GRASS, WINDSWEPT_GRASS, surfaceSpread(6, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_GRASS_SPROUTS_DENSE, PATCH_GRASS_SPROUTS, surfaceSpread(12, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_GRASS_SPROUTS_SPARSE, PATCH_GRASS_SPROUTS, surfaceSpread(6, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_ASHEN_GRASS, count(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUDatagenFeatureUtils.airAndBlocksBelow(RUBlocks.ASHEN_DIRT.get()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_ASHEN_GRASS_SMOULDERING, count(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUDatagenFeatureUtils.airAndBlocksBelow(RUBlocks.ASHEN_DIRT.get(), Blocks.BASALT, Blocks.SMOOTH_BASALT), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_ASH_VENTS, count(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RUDatagenFeatureUtils.airAndBlocksBelow(RUBlocks.ASH.get()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_FERNS_DENSE,
            count(20),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.PATCH_LUPINES,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 2, 1, 1, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        
        register(context, RuVegetationPlacements.PATCH_TUNDRA_FLOWERS,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 4, 0, 1, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS_SPARSE, patchGrass, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS, patchGrass, surfaceSpread(4, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE, patchGrass, surfaceSpread(8, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_FERN, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_STEPPE_GRASS, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_SANDY_GRASS_SPARSE, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_GRASS, surfaceSpread(2, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_SAND, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_STEPPE_SHRUB_ON_SAND, surfaceSpread(1, Types.WORLD_SURFACE_WG));

        register(context, RuVegetationPlacements.BLACKWOOD_VEGETATION, BLACKWOOD_VEGETATION, surfaceSpread(10, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.DECIDUOUS_VEGETATION, DECIDUOUS_VEGETATION, NoiseThresholdCountPlacement.of(-0.8D, 5, 24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.FEN_VEGETATION, FEN_VEGETATION, NoiseThresholdCountPlacement.of(-0.8D, 5, 24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.SHRUBLAND_VEGETATION, SHRUBLAND_VEGETATION, surfaceSpread(10, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.MOUNTAIN_VEGETATION, MOUNTAIN_VEGETATION, surfaceSpread(12, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.STEPPE_VEGETATION, STEPPE_VEGETATION, surfaceSpread(24, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.SOCOTRA_VEGETATION, SOCOTRA_VEGETATION, surfaceSpread(24, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.SANDY_GRASS_VEGETATION, SANDY_GRASS_VEGETATION, surfaceSpread(12, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.BAYOU_VEGETATION, BAYOU_VEGETATION, surfaceSpread(14, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.DIRT_VEGETATION, DIRT_VEGETATION, NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onDirtPredicate), BiomeFilter.biome());
        register(context, RuVegetationPlacements.GRASS_VEGETATION, GRASS_VEGETATION, NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onGrassBlockPredicate), BiomeFilter.biome());
        register(context, RuVegetationPlacements.REDSTONE_BUD, REDSTONE_BUD, List.of(CountPlacement.of(255), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
        register(context, RuVegetationPlacements.PRISMOSS_SPROUT, PRISMOSS_SPROUT, List.of(CountOnEveryLayerPlacement.of(25), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.BLADED_GRASS, BLADED_GRASS, CountOnEveryLayerPlacement.of(155), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        //FLOWERS
        register(context, RuVegetationPlacements.ASTER, ASTER, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.TULIPS, TULIPS, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.CAVE_HYSSOP, CAVE_HYSSOP, List.of(CountOnEveryLayerPlacement.of(2), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.SMALL_FLOWERS, SMALL_FLOWERS, NoiseThresholdCountPlacement.of(-0.8D, 5, 6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.TALL_FLOWERS, TALL_FLOWERS, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.PATCH_ALPHA_DANDELION, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));

        PlacementModifier airCheck = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR));
        register(context, RUPlacedFeatures.BONEMEAL_ALPHA_GRASS, getter.getOrThrow(RUConfiguredFeatures.BONEMEAL_ALPHA_GRASS), RarityFilter.onAverageOnceEvery(25), airCheck);
        register(context, RuVegetationPlacements.PATCH_ALPHA_ROSE, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_WILTING_TRILLIUM, surfaceSpread(0.25, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.WHITE_TRILLIUM, WHITE_TRILLIUM, List.of(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, RuVegetationPlacements.AZURE_DAISY, AZURE_DAISY, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.DAISY, DAISY, surfaceSpread(0.333, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.WARATAH, WARATAH, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_DAISIES, surfaceSpread(5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PRAIRIE_FLOWERS, PRAIRIE_FLOWERS, surfaceSpread(0.0625, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.SHRUBLAND_FLOWERS, SHRUBLAND_FLOWERS, surfaceSpread(0.0833, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.WILLOW_FLOWERS, WILLOW_FLOWERS, surfaceSpread(0.25, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.PATCH_POPPIES,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 8, 6, 2, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.TASSEL_SPARSE, TASSEL, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.TASSEL_DENSE, TASSEL, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.SNOWBELLE, SNOWBELLE, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.CORPSE_FLOWER, CORPSE_FLOWER, CountOnEveryLayerPlacement.of(10), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.DUSKTRAP, DUSKTRAP, CountOnEveryLayerPlacement.of(4), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.DAY_LILY, DAY_LILY, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.TSUBAKI, TSUBAKI, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.HIBISCUS, HIBISCUS, surfaceSpread(0.5, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.MALLOW, MALLOW, surfaceSpread(0.125, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.HYSSOP, HYSSOP, surfaceSpread(1, Types.WORLD_SURFACE_WG));
        register(context, RuVegetationPlacements.BARLEY_SPARSE, BARLEY, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)), BiomeFilter.biome());
        register(context, RuVegetationPlacements.BARLEY_DENSE, BARLEY, NoiseBasedCountPlacement.of(155, 75.0D, 0.0D), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onGrassBlockPredicate), BiomeFilter.biome());
        register(context, RuVegetationPlacements.MEADOW_SAGE, MEADOW_SAGE, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, RuVegetationPlacements.REDSTONE_BULB, REDSTONE_BULB, List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

        register(context, RuVegetationPlacements.PATCH_ORANGE_CONEFLOWER,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 22, -6, 2, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.PATCH_PURPLE_CONEFLOWER,
            LithostitchedPlacementModifiers.noiseSlope(RUNoises.FLOWER_DENSITY, 28, -10, 2, 0),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.PATCH_CLOVERS_DENSE, CLOVER,
            count(3),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        );
        //FOOD_PLANTS
        register(context, RuVegetationPlacements.RARE_SALMONBERRY_BUSH, SALMONBERRY_BUSH, RarityFilter.onAverageOnceEvery(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, RuVegetationPlacements.DUSKMELON, DUSKMELON, CountOnEveryLayerPlacement.of(10), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        //BIOSHROOM
        register(context, RuVegetationPlacements.BLUE_BIOSHROOM, BLUE_BIOSHROOM, List.of(CountOnEveryLayerPlacement.of(5), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.GREEN_BIOSHROOM, GREEN_BIOSHROOM, List.of(CountOnEveryLayerPlacement.of(5), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.PINK_BIOSHROOM, PINK_BIOSHROOM, List.of(CountOnEveryLayerPlacement.of(2), BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome()));
        register(context, RuVegetationPlacements.PINK_BIOSHROOM_DENSE, PINK_BIOSHROOM, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        //OTHER
        register(context, RuVegetationPlacements.CACTUS_DENSE, CACTUS, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        register(context, RuVegetationPlacements.BARREL_CACTUS, BARREL_CACTUS, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.BAMBOO, BAMBOO, NoiseBasedCountPlacement.of(30, -0.8D, 2.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, RuVegetationPlacements.FLOWERING_LILY, FLOWERING_LILY, List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, RuVegetationPlacements.GIANT_LILY, GIANT_LILY, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, RuVegetationPlacements.ELEPHANT_EAR_SPARSE, ELEPHANT_EAR, CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.ELEPHANT_EAR_DENSE, ELEPHANT_EAR, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE), BiomeFilter.biome());
        register(context, RuVegetationPlacements.DROPLEAF, DROPLEAF, List.of(
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.matchesTag(BlockTags.AIR), 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        ));
        register(context, RuVegetationPlacements.DUCKWEED, DUCKWEED, List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        //SHRUBS
        register(context, RuVegetationPlacements.ASHEN_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.ACACIA_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.CHERRY_SHRUB, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.EUCALYPTUS_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.FLOWERING_SHRUB, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.JOSHUA_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.LARCH_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.OAK_SHRUB, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PINE_SHRUB, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.REDWOOD_SHRUB,
            count(2),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()),
            BiomeFilter.biome()
        );
        register(context, RuVegetationPlacements.SOCOTRA_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.SPRUCE_SHRUB, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        //mixes
        register(context, RuVegetationPlacements.BAOBAB_ACACIA_SHRUB_MIX, BAOBAB_ACACIA_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.AUTUMNAL_SHRUB_MIX, AUTUMNAL_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.BLACKWOOD_DARK_OAK_SHRUB_MIX, BLACKWOOD_DARK_OAK_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.MAGNOLIA_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.GOLDEN_LARCH_SHRUB_MIX, GOLDEN_LARCH_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.MAPLE_SHRUB_MIX, MAPLE_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.MAUVE_ENCHANTED_SHRUB_MIX, MAUVE_ENCHANTED_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PALM_JUNGLE_SHRUB_MIX, PALM_JUNGLE_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.PINE_DEAD_SHRUB_MIX, PINE_DEAD_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.WILLOW_CYPRESS_SHRUB_MIX, WILLOW_CYPRESS_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());
        register(context, RuVegetationPlacements.WILLOW_MAGNOLIA_SHRUB_MIX, WILLOW_MAGNOLIA_SHRUB_MIX, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, PlacementUtils.filteredByBlockSurvival(RUBlocks.OAK_NATURAL_SET.getShrub()), BiomeFilter.biome());

        register(context, RuVegetationPlacements.PRAIRIE_TREES, PRAIRIE_MIX, NoiseBasedCountPlacement.of(60, 30.0D, -0.5D), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(RUPlacedFeatureBootstrap.onGrassBlockPredicate), BiomeFilter.biome());
    }
}
