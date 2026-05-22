package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.RegionsUnexplored;

public interface RUPlacedFeatures {
    ResourceKey<PlacedFeature> BONEMEAL_ALPHA_GRASS = bonemeal("alpha");
    ResourceKey<PlacedFeature> BONEMEAL_ARGILLITE_GRASS = bonemeal("argillite");
    ResourceKey<PlacedFeature> BONEMEAL_CHALK_GRASS = bonemeal("chalk");
    ResourceKey<PlacedFeature> BONEMEAL_DEEPSLATE_GRASS = bonemeal("deepslate");
    ResourceKey<PlacedFeature> BONEMEAL_PEAT_GRASS = bonemeal("peat");
    ResourceKey<PlacedFeature> BONEMEAL_SILT_GRASS = bonemeal("silt");
    ResourceKey<PlacedFeature> BONEMEAL_STONE_GRASS = bonemeal("stone");
    
    ResourceKey<PlacedFeature> VANILLA_BADLANDS_SAGUAROS = vanilla("badlands_saguaros");
    ResourceKey<PlacedFeature> VANILLA_BADLANDS_STEPPE_GRASS = vanilla("badlands_steppe_grass");
    ResourceKey<PlacedFeature> VANILLA_BASALT_DELTAS_ASH_VENTS = vanilla("basalt_deltas_ash_vents");
    ResourceKey<PlacedFeature> VANILLA_BEACH_PALM_TREES = vanilla("beach_palm_trees");
    ResourceKey<PlacedFeature> VANILLA_BIRCH_ORANGE_CONEFLOWERS = vanilla("birch_orange_coneflowers");
    ResourceKey<PlacedFeature> VANILLA_DESERT_SANDY_GRASS = vanilla("desert_sandy_grass");
    ResourceKey<PlacedFeature> VANILLA_FOREST_FLOWERS = vanilla("forest_flowers");
    ResourceKey<PlacedFeature> VANILLA_JUNGLE_BAMBOO_TREES = vanilla("jungle_bamboo_trees");
    ResourceKey<PlacedFeature> VANILLA_JUNGLE_ELEPHANT_EARS = vanilla("jungle_elephant_ears");
    ResourceKey<PlacedFeature> VANILLA_JUNGLE_HIBISCUSES = vanilla("jungle_hibiscuses");
    ResourceKey<PlacedFeature> VANILLA_MANGROVE_FLOWERING_LILIES = vanilla("mangrove_flowering_lilies");
    ResourceKey<PlacedFeature> VANILLA_PLAINS_BUSHES = vanilla("plains_bushes");
    ResourceKey<PlacedFeature> VANILLA_SAVANNA_BUSHES = vanilla("savanna_bushes");
    ResourceKey<PlacedFeature> VANILLA_SNOWY_FROZEN_GRASS = vanilla("snowy_frozen_grass");
    ResourceKey<PlacedFeature> VANILLA_SWAMP_CATTAILS = vanilla("swamp_cattails");
    ResourceKey<PlacedFeature> VANILLA_SWAMP_TREES = vanilla("swamp_trees");
    ResourceKey<PlacedFeature> VANILLA_TAIGA_PURPLE_CONEFLOWERS = vanilla("taiga_purple_coneflowers");
    
    static ResourceKey<PlacedFeature> bonemeal(String name) {
        return key("bonemeal/grass/" + name);
    }
    
    static ResourceKey<PlacedFeature> vanilla(String name) {
        return key("vanilla_changes/" + name);
    }
    
    static ResourceKey<PlacedFeature> patch(String name) {
        return key("patch/" + name);
    }
    
    static ResourceKey<PlacedFeature> key(String name) {
        return RegionsUnexplored.key(Registries.PLACED_FEATURE, name);
    }
}
