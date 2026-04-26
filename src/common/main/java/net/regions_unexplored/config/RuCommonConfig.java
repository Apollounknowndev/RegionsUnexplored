package net.regions_unexplored.config;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.RegionsUnexplored;
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
    public static final ConfigValue<Integer> TOGGLE_ARID_MOUNTAINS = weightedBiome(RUBiomes.ARID_MOUNTAINS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ASHEN_WOODLAND = weightedBiome(RUBiomes.ASHEN_WOODLAND, 20);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_AUTUMNAL_MAPLE_FOREST = weightedBiome(RUBiomes.AUTUMNAL_MAPLE_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BAMBOO_FOREST = weightedBiome(RUBiomes.BAMBOO_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BAOBAB_SAVANNA = weightedBiome(RUBiomes.BAOBAB_SAVANNA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BARLEY_FIELDS = weightedBiome(RUBiomes.BARLEY_FIELDS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_BAYOU = toggledBiome(RUBiomes.BAYOU);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BLACKWOOD_TAIGA = weightedBiome(RUBiomes.BLACKWOOD_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BOREAL_TAIGA = weightedBiome(RUBiomes.BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_CHALK_CLIFFS = toggledBiome(RUBiomes.CHALK_CLIFFS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_CLOVER_PLAINS = weightedBiome(RUBiomes.CLOVER_PLAINS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_COLD_BOREAL_TAIGA = weightedBiome(RUBiomes.COLD_BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_COLD_DECIDUOUS_FOREST = weightedBiome(RUBiomes.COLD_DECIDUOUS_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_COLD_RIVER = toggledBiome(RUBiomes.COLD_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_DECIDUOUS_FOREST = weightedBiome(RUBiomes.DECIDUOUS_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_DRY_BUSHLAND = weightedBiome(RUBiomes.DRY_BUSHLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_EUCALYPTUS_FOREST = weightedBiome(RUBiomes.EUCALYPTUS_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_FEN = toggledBiome(RUBiomes.FEN);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_FLOWER_FIELDS = weightedBiome(RUBiomes.FLOWER_FIELDS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_FROZEN_PINE_TAIGA = weightedBiome(RUBiomes.FROZEN_PINE_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_FUNGAL_FEN = toggledBiome(RUBiomes.FUNGAL_FEN);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GOLDEN_BOREAL_TAIGA = weightedBiome(RUBiomes.GOLDEN_BOREAL_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GRASSLAND = weightedBiome(RUBiomes.GRASSLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GRASSY_BEACH = weightedBiome(RUBiomes.GRASSY_BEACH, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GRAVEL_BEACH = weightedBiome(RUBiomes.GRAVEL_BEACH, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_HIGHLAND_FIELDS = weightedBiome(RUBiomes.HIGHLAND_FIELDS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_HYACINTH_DEEPS = weightedBiome(RUBiomes.HYACINTH_DEEPS, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ICY_HEIGHTS = weightedBiome(RUBiomes.ICY_HEIGHTS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_JOSHUA_DESERT = weightedBiome(RUBiomes.JOSHUA_DESERT);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MAGNOLIA_WOODLAND = weightedBiome(RUBiomes.MAGNOLIA_WOODLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MAPLE_FOREST = weightedBiome(RUBiomes.MAPLE_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MARSH = weightedBiome(RUBiomes.MARSH, 60);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MOUNTAINS = weightedBiome(RUBiomes.MOUNTAINS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_MUDDY_RIVER = toggledBiome(RUBiomes.MUDDY_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_OLD_GROWTH_BAYOU = toggledBiome(RUBiomes.OLD_GROWTH_BAYOU);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ORCHARD = weightedBiome(RUBiomes.ORCHARD);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_OUTBACK = weightedBiome(RUBiomes.OUTBACK);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PINE_SLOPES = weightedBiome(RUBiomes.PINE_SLOPES);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PINE_TAIGA = weightedBiome(RUBiomes.PINE_TAIGA);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_POPPY_FIELDS = weightedBiome(RUBiomes.POPPY_FIELDS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PRAIRIE = weightedBiome(RUBiomes.PRAIRIE);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PUMPKIN_FIELDS = weightedBiome(RUBiomes.PUMPKIN_FIELDS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_RAINFOREST = weightedBiome(RUBiomes.RAINFOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ROCKY_REEF = weightedBiome(RUBiomes.ROCKY_REEF, 50);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_REDWOODS = weightedBiome(RUBiomes.REDWOODS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SAGUARO_DESERT = weightedBiome(RUBiomes.SAGUARO_DESERT);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SHRUBLAND = weightedBiome(RUBiomes.SHRUBLAND);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SILVER_BIRCH_FOREST = weightedBiome(RUBiomes.SILVER_BIRCH_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SPARSE_RAINFOREST = weightedBiome(RUBiomes.SPARSE_RAINFOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SPARSE_REDWOODS = weightedBiome(RUBiomes.SPARSE_REDWOODS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SPIRES = weightedBiome(RUBiomes.SPIRES);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_STEPPE = weightedBiome(RUBiomes.STEPPE);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TEMPERATE_GROVE = weightedBiome(RUBiomes.TEMPERATE_GROVE);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TOWERING_CLIFFS = weightedBiome(RUBiomes.TOWERING_CLIFFS);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Boolean> TOGGLE_TROPICAL_RIVER = toggledBiome(RUBiomes.TROPICAL_RIVER);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TROPICS = weightedBiome(RUBiomes.TROPICS, 20);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_TUNDRA = weightedBiome(RUBiomes.TUNDRA, 100);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_WILLOW_FOREST = weightedBiome(RUBiomes.WILLOW_FOREST);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_WISTERIA_GROVE = weightedBiome(RUBiomes.WISTERIA_GROVE);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> RIVER_WEIGHT = integer(60);
    @ConfigOption(category = "overworld_biome_toggles")
    public static final ConfigValue<Integer> SWAMP_WEIGHT = integer(100);

    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_ANCIENT_DELTA = weightedBiome(RUBiomes.ANCIENT_DELTA, 25);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BIOSHROOM_CAVES = weightedBiome(RUBiomes.BIOSHROOM_CAVES);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_PRISMACHASM = weightedBiome(RUBiomes.PRISMACHASM);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_REDSTONE_CAVES = weightedBiome(RUBiomes.REDSTONE_CAVES);
    @ConfigOption(category = "overworld_cave_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_SCORCHING_CAVES = weightedBiome(RUBiomes.SCORCHING_CAVES);

    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_BLACKSTONE_BASIN = weightedBiome(RUBiomes.BLACKSTONE_BASIN);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_GLISTERING_MEADOW = weightedBiome(RUBiomes.GLISTERING_MEADOW);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_INFERNAL_HOLT = weightedBiome(RUBiomes.INFERNAL_HOLT);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_MYCOTOXIC_UNDERGROWTH = weightedBiome(RUBiomes.MYCOTOXIC_UNDERGROWTH);
    @ConfigOption(category = "nether_biome_toggles")
    public static final ConfigValue<Integer> TOGGLE_REDSTONE_ABYSS = weightedBiome(RUBiomes.REDSTONE_ABYSS);

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