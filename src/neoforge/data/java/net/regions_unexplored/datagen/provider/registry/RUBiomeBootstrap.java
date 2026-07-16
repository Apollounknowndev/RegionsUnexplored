package net.regions_unexplored.datagen.provider.registry;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.biome.*;
import net.regions_unexplored.registry.data.RUBiomes;

public class RUBiomeBootstrap {
    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carversGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> featuresGetter = context.lookup(Registries.PLACED_FEATURE);

        //FOREST
        register(context, RUBiomes.AUTUMNAL_MAPLE_FOREST, ForestBiomes.autumnalMapleForest(featuresGetter, carversGetter));
        register(context, RUBiomes.BAMBOO_FOREST, ForestBiomes.bambooForest(featuresGetter, carversGetter));
        register(context, RUBiomes.MAGNOLIA_WOODLAND, ForestBiomes.magnoliaHighlands(featuresGetter, carversGetter));
        register(context, RUBiomes.OLD_GROWTH_FOREST, ForestBiomes.oldGrowthForest(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_DECIDUOUS_FOREST, ForestBiomes.oldGrowthForest(featuresGetter, carversGetter));
        register(context, RUBiomes.MAPLE_FOREST, ForestBiomes.mapleForest(featuresGetter, carversGetter));
        register(context, RUBiomes.ORCHARD, ForestBiomes.orchard(featuresGetter, carversGetter));
        register(context, RUBiomes.SILVER_BIRCH_FOREST, ForestBiomes.silverBirchForest(featuresGetter, carversGetter));
        register(context, RUBiomes.WINDSWEPT_MAPLE_FOREST, ForestBiomes.windsweptMapleForest(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_TEMPERATE_GROVE, ForestBiomes.windsweptMapleForest(featuresGetter, carversGetter));
        register(context, RUBiomes.WILLOW_FOREST, ForestBiomes.willowForest(featuresGetter, carversGetter));
        register(context, RUBiomes.WISTERIA_GROVE, ForestBiomes.wisteriaGrove(featuresGetter, carversGetter));
        //TAIGA
        register(context, RUBiomes.BLACKWOOD_TAIGA, TaigaBiomes.blackwoodTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.BOREAL_TAIGA, TaigaBiomes.borealTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.OLD_GROWTH_BOREAL_TAIGA, TaigaBiomes.oldGrowthBorealTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.OLD_GROWTH_GOLDEN_BOREAL_TAIGA, TaigaBiomes.oldGrowthGoldenBorealTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_GOLDEN_BOREAL_TAIGA, TaigaBiomes.oldGrowthGoldenBorealTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.PINE_TAIGA, TaigaBiomes.pineTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.REDWOODS, TaigaBiomes.redwoods(featuresGetter, carversGetter));
        register(context, RUBiomes.SPARSE_REDWOODS, TaigaBiomes.sparseRedwoods(featuresGetter, carversGetter));
        //PLAINS
        register(context, RUBiomes.REMOVED_BARLEY_FIELDS, PlainsBiomes.prairie(featuresGetter, carversGetter));
        register(context, RUBiomes.FLOWER_FIELDS, PlainsBiomes.flowerFields(featuresGetter, carversGetter));
        register(context, RUBiomes.GRASSLAND, PlainsBiomes.grassland(featuresGetter, carversGetter));
        register(context, RUBiomes.CLOVER_PLAINS, PlainsBiomes.cloverPlains(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_ROCKY_MEADOW, ForestBiomes.wisteriaGrove(featuresGetter, carversGetter));
        register(context, RUBiomes.POPPY_FIELDS, PlainsBiomes.poppyFields(featuresGetter, carversGetter));
        register(context, RUBiomes.PRAIRIE, PlainsBiomes.prairie(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_PUMPKIN_FIELDS, ForestBiomes.autumnalMapleForest(featuresGetter, carversGetter));
        register(context, RUBiomes.SHRUBLAND, PlainsBiomes.shrubland(featuresGetter, carversGetter));
        //WET
        register(context, RUBiomes.BAYOU, WetBiomes.bayou(featuresGetter, carversGetter));
        register(context, RUBiomes.EUCALYPTUS_FOREST, WetBiomes.eucalyptusForest(featuresGetter, carversGetter));
        register(context, RUBiomes.FEN, WetBiomes.fen(featuresGetter, carversGetter));
        register(context, RUBiomes.MARSH, WetBiomes.marsh(featuresGetter, carversGetter));
        register(context, RUBiomes.FUNGAL_FEN, WetBiomes.fungalFen(featuresGetter, carversGetter));
        register(context, RUBiomes.OLD_GROWTH_BAYOU, WetBiomes.oldGrowthBayou(featuresGetter, carversGetter));
        register(context, RUBiomes.SPARSE_RAINFOREST, WetBiomes.sparseRainforest(featuresGetter, carversGetter));
        register(context, RUBiomes.RAINFOREST, WetBiomes.rainforest(featuresGetter, carversGetter));
        //ARID
        register(context, RUBiomes.BAOBAB_SAVANNA, AridBiomes.baobabSavanna(featuresGetter, carversGetter));
        register(context, RUBiomes.DRY_BUSHLAND, AridBiomes.dryBushland(featuresGetter, carversGetter));
        register(context, RUBiomes.JOSHUA_DESERT, AridBiomes.joshuaDesert(featuresGetter, carversGetter));
        register(context, RUBiomes.OUTBACK, AridBiomes.outback(featuresGetter, carversGetter));
        register(context, RUBiomes.SAGUARO_DESERT, AridBiomes.saguaroDesert(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_STEPPE, AridBiomes.steppe(featuresGetter, carversGetter));
        //MOUNTAIN
        register(context, RUBiomes.REMOVED_ARID_MOUNTAINS, OverworldBiomes.badlands(featuresGetter, carversGetter, false));
        register(context, RUBiomes.HIGHLAND_FIELDS, MountainBiomes.highlandFields(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_MOUNTAINS, MountainBiomes.mountains(featuresGetter, carversGetter));
        register(context, RUBiomes.PINE_SLOPES, MountainBiomes.pineSlopes(featuresGetter, carversGetter));
        register(context, RUBiomes.TOWERING_CLIFFS, MountainBiomes.toweringCliffs(featuresGetter, carversGetter));
        //COASTAL
        register(context, RUBiomes.CHALK_CLIFFS, CoastalBiomes.chalkCliffs(featuresGetter, carversGetter));
        register(context, RUBiomes.GRASSY_BEACH, CoastalBiomes.grassyBeach(featuresGetter, carversGetter));
        register(context, RUBiomes.GRAVEL_BEACH, CoastalBiomes.gravelBeach(featuresGetter, carversGetter));
        //AQUATIC
        register(context, RUBiomes.ALPHA_GROVE, AquaticBiomes.alphaGrove(featuresGetter, carversGetter));
        register(context, RUBiomes.COLD_RIVER, AquaticBiomes.coldRiver(featuresGetter, carversGetter));
        register(context, RUBiomes.HYACINTH_DEEPS, AquaticBiomes.hyacinthDeeps(featuresGetter, carversGetter));
        register(context, RUBiomes.MUDDY_RIVER, AquaticBiomes.muddyRiver(featuresGetter, carversGetter));
        register(context, RUBiomes.ROCKY_REEF, AquaticBiomes.rockyReef(featuresGetter, carversGetter));
        register(context, RUBiomes.ASHEN_WOODLAND, AquaticBiomes.ashenWoodland(featuresGetter, carversGetter));
        register(context, RUBiomes.TROPICAL_RIVER, AquaticBiomes.tropicalRiver(featuresGetter, carversGetter));
        register(context, RUBiomes.TROPICS, AquaticBiomes.tropics(featuresGetter, carversGetter));
        //FROZEN
        register(context, RUBiomes.COLD_BOREAL_TAIGA, FrozenBiomes.coldBorealTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_COLD_DECIDUOUS_FOREST, FrozenBiomes.coldDeciduousForest(featuresGetter, carversGetter));
        register(context, RUBiomes.FROZEN_PINE_TAIGA, FrozenBiomes.frozenPineTaiga(featuresGetter, carversGetter));
        register(context, RUBiomes.TUNDRA, FrozenBiomes.tundra(featuresGetter, carversGetter));
        register(context, RUBiomes.ICY_HEIGHTS, FrozenBiomes.icyHeights(featuresGetter, carversGetter));
        register(context, RUBiomes.SPIRES, FrozenBiomes.spires(featuresGetter, carversGetter));
        //CAVE
        register(context, RUBiomes.ANCIENT_DELTA, CaveBiomes.ancientDelta(featuresGetter, carversGetter));
        register(context, RUBiomes.BIOSHROOM_CAVES, CaveBiomes.bioshroomCaves(featuresGetter, carversGetter));
        register(context, RUBiomes.PRISMACHASM, CaveBiomes.prismachasm(featuresGetter, carversGetter));
        register(context, RUBiomes.REDSTONE_CAVES, CaveBiomes.redstoneCaves(featuresGetter, carversGetter));
        register(context, RUBiomes.INFERNO, CaveBiomes.inferno(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_SCORCHING_CAVES, CaveBiomes.inferno(featuresGetter, carversGetter));
        //NETHER
        register(context, RUBiomes.BLACKSTONE_BASIN, NetherBiomes.blackstoneBasin(featuresGetter, carversGetter));
        register(context, RUBiomes.INFERNAL_HOLT, NetherBiomes.infernalHolt(featuresGetter, carversGetter));
        register(context, RUBiomes.GLISTERING_MEADOW, NetherBiomes.glisteringMeadow(featuresGetter, carversGetter));
        register(context, RUBiomes.MYCOTOXIC_UNDERGROWTH, NetherBiomes.mycotoxicUndergrowth(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_REDSTONE_ABYSS, NetherBiomes.redstoneAbyss(featuresGetter, carversGetter));
        
        // Removed
        register(context, RUBiomes.REMOVED_FROZEN_TUNDRA, FrozenBiomes.tundra(featuresGetter, carversGetter));
        register(context, RUBiomes.REMOVED_MAUVE_HILLS, ForestBiomes.wisteriaGrove(featuresGetter, carversGetter));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }
}