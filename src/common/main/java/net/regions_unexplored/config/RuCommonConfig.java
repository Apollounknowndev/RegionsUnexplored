package net.regions_unexplored.config;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.internal.config.Config;
import net.regions_unexplored.internal.config.ConfigValue;
import net.regions_unexplored.internal.config.annotation.ConfigOption;
import net.regions_unexplored.registry.data.RUBiomes;

import java.util.HashMap;
import java.util.Map;

public class RuCommonConfig extends Config {
    public static final Map<ResourceKey<Biome>, ConfigValue<Integer>> BIOME_WEIGHTS = new HashMap<>();
    public static final Map<ResourceKey<Biome>, ConfigValue<Boolean>> BIOME_TOGGLES = new HashMap<>();
    
    @ConfigOption(category = "features")
    public static final ConfigValue<Boolean> USE_LOGS_FOR_BRANCHES = bool(false);

    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ALPHA_GROVE = weightedBiome(RUBiomes.ALPHA_GROVE, 20);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ASHEN_WOODLAND = weightedBiome(RUBiomes.ASHEN_WOODLAND, 20);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_AUTUMNAL_MAPLE_FOREST = weightedBiome(RUBiomes.AUTUMNAL_MAPLE_FOREST, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BAMBOO_FOREST = weightedBiome(RUBiomes.BAMBOO_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_BAOBAB_SAVANNA = toggledBiome(RUBiomes.BAOBAB_SAVANNA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_BAYOU = toggledBiome(RUBiomes.BAYOU);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BLACKWOOD_TAIGA = weightedBiome(RUBiomes.BLACKWOOD_TAIGA, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_BOREAL_TAIGA = toggledBiome(RUBiomes.BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_CHALK_CLIFFS = toggledBiome(RUBiomes.CHALK_CLIFFS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_CLOVER_PLAINS = toggledBiome(RUBiomes.CLOVER_PLAINS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_COLD_BOREAL_TAIGA = toggledBiome(RUBiomes.COLD_BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_COLD_RIVER = toggledBiome(RUBiomes.COLD_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_DECIDUOUS_FOREST = toggledBiome(RUBiomes.DECIDUOUS_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_DRY_BUSHLAND = weightedBiome(RUBiomes.DRY_BUSHLAND, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_EUCALYPTUS_FOREST = weightedBiome(RUBiomes.EUCALYPTUS_FOREST, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_FEN = toggledBiome(RUBiomes.FEN);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_FLOWER_FIELDS = weightedBiome(RUBiomes.FLOWER_FIELDS, 30);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_FROZEN_PINE_TAIGA = toggledBiome(RUBiomes.FROZEN_PINE_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_FUNGAL_FEN = toggledBiome(RUBiomes.FUNGAL_FEN);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_GRASSLAND = toggledBiome(RUBiomes.GRASSLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GRASSY_BEACH = weightedBiome(RUBiomes.GRASSY_BEACH, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GRAVEL_BEACH = weightedBiome(RUBiomes.GRAVEL_BEACH, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_HIGHLAND_FIELDS = weightedBiome(RUBiomes.HIGHLAND_FIELDS, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_HYACINTH_DEEPS = weightedBiome(RUBiomes.HYACINTH_DEEPS, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_ICY_HEIGHTS = toggledBiome(RUBiomes.ICY_HEIGHTS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_JOSHUA_DESERT = weightedBiome(RUBiomes.JOSHUA_DESERT, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MAGNOLIA_WOODLAND = weightedBiome(RUBiomes.MAGNOLIA_WOODLAND, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_MAPLE_FOREST = toggledBiome(RUBiomes.MAPLE_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MARSH = weightedBiome(RUBiomes.MARSH, 60);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_MUDDY_RIVER = toggledBiome(RUBiomes.MUDDY_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_OLD_GROWTH_BAYOU = toggledBiome(RUBiomes.OLD_GROWTH_BAYOU);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_OLD_GROWTH_BOREAL_TAIGA = toggledBiome(RUBiomes.OLD_GROWTH_BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_OLD_GROWTH_GOLDEN_BOREAL_TAIGA = toggledBiome(RUBiomes.OLD_GROWTH_GOLDEN_BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ORCHARD = weightedBiome(RUBiomes.ORCHARD, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_OUTBACK = toggledBiome(RUBiomes.OUTBACK);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> PINE_SLOPES_WEIGHT = weightedBiome(RUBiomes.PINE_SLOPES, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_PINE_TAIGA = toggledBiome(RUBiomes.PINE_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_POPPY_FIELDS = weightedBiome(RUBiomes.POPPY_FIELDS, 70);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PRAIRIE = weightedBiome(RUBiomes.PRAIRIE, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_RAINFOREST = toggledBiome(RUBiomes.RAINFOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ROCKY_REEF = weightedBiome(RUBiomes.ROCKY_REEF, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_REDWOODS = toggledBiome(RUBiomes.REDWOODS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SAGUARO_DESERT = weightedBiome(RUBiomes.SAGUARO_DESERT, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_SHRUBLAND = toggledBiome(RUBiomes.SHRUBLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SILVER_BIRCH_FOREST = weightedBiome(RUBiomes.SILVER_BIRCH_FOREST, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_SPARSE_RAINFOREST = toggledBiome(RUBiomes.SPARSE_RAINFOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_SPARSE_REDWOODS = toggledBiome(RUBiomes.SPARSE_REDWOODS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_SPIRES = toggledBiome(RUBiomes.SPIRES);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TOWERING_CLIFFS = weightedBiome(RUBiomes.TOWERING_CLIFFS, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_TROPICAL_RIVER = toggledBiome(RUBiomes.TROPICAL_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TROPICS = weightedBiome(RUBiomes.TROPICS, 20);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TUNDRA = weightedBiome(RUBiomes.TUNDRA, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_WILLOW_FOREST = toggledBiome(RUBiomes.WILLOW_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_WINDSWEPT_MAPLE_FOREST = weightedBiome(RUBiomes.WINDSWEPT_MAPLE_FOREST, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_WISTERIA_GROVE = weightedBiome(RUBiomes.WISTERIA_GROVE, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> RIVER_WEIGHT = integer(60);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> SWAMP_WEIGHT = integer(100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> PLAINS_WEIGHT = integer(100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> FOREST_WEIGHT = integer(50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> PRIMARY_TAIGA_WEIGHT = integer(75);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> SECONDARY_TAIGA_WEIGHT = integer(75);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> RAINFOREST_WEIGHT = integer(50);

    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_ANCIENT_DELTA = toggledBiome(RUBiomes.ANCIENT_DELTA);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_BIOSHROOM_CAVES = toggledBiome(RUBiomes.BIOSHROOM_CAVES);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_PRISMACHASM = toggledBiome(RUBiomes.PRISMACHASM);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_REDSTONE_CAVES = toggledBiome(RUBiomes.REDSTONE_CAVES);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_INFERNO = toggledBiome(RUBiomes.INFERNO);

    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BLACKSTONE_BASIN = weightedBiome(RUBiomes.BLACKSTONE_BASIN, 60);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GLISTERING_MEADOW = weightedBiome(RUBiomes.GLISTERING_MEADOW, 60);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_INFERNAL_HOLT = weightedBiome(RUBiomes.INFERNAL_HOLT, 60);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MYCOTOXIC_UNDERGROWTH = weightedBiome(RUBiomes.MYCOTOXIC_UNDERGROWTH, 60);

    @ConfigOption(category = "worldgen_surface_rules")
    public static final ConfigValue<Boolean> TOGGLE_CUSTOM_DIRTS = bool(true);
    
    private static ConfigValue<Boolean> toggledBiome(ResourceKey<Biome> biome) {
        var config = bool(true);
        BIOME_TOGGLES.put(biome, config);
        return config;
    }
    
    private static ConfigValue<Integer> weightedBiome(ResourceKey<Biome> biome) {
        return weightedBiome(biome, 100);
    }
    
    private static ConfigValue<Integer> weightedBiome(ResourceKey<Biome> biome, int weight) {
        var config = integer(weight);
        config.setRange(0, Integer.MAX_VALUE);
        BIOME_WEIGHTS.put(biome, config);
        return config;
    }
    
    static {
        RIVER_WEIGHT.setRange(0, Integer.MAX_VALUE);
        BIOME_WEIGHTS.put(RUBiomes.COLD_RIVER, RIVER_WEIGHT);
        BIOME_WEIGHTS.put(RUBiomes.MUDDY_RIVER, RIVER_WEIGHT);
        BIOME_WEIGHTS.put(RUBiomes.TROPICAL_RIVER, RIVER_WEIGHT);
    }
}