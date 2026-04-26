package net.regions_unexplored.world;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Parameter;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.RuCommonConfig;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.world.surface.RUSurfaceRuleBuilder;
import net.regions_unexplored.internal.config.ConfigValue;

public class RuBiolith {
    public static void init() {
        // Caves
        if (RuCommonConfig.TOGGLE_ANCIENT_DELTA.get() > 0) {
            // High humidity, high temperature, positive weirdness
            BiomePlacement.addSubOverworld(Biomes.DRIPSTONE_CAVES, RUBiomes.ANCIENT_DELTA, CriterionBuilder.allOf(
                CriterionBuilder.valueMax(BiomeParameterTargets.HUMIDITY, -0.3f)
            ));
        }
        if (RuCommonConfig.TOGGLE_BIOSHROOM_CAVES.get() > 0) {
            // High humidity, high erosion, positive weirdness
            BiomePlacement.addSubOverworld(Biomes.LUSH_CAVES, RUBiomes.BIOSHROOM_CAVES, CriterionBuilder.allOf(
                CriterionBuilder.valueMin(BiomeParameterTargets.EROSION, 0.4f),
                CriterionBuilder.valueMin(BiomeParameterTargets.WEIRDNESS, 0f)
            ));
        }
        if (RuCommonConfig.TOGGLE_PRISMACHASM.get() > 0) {
            // Low humidity, positive continents
            BiomePlacement.addOverworld(RUBiomes.PRISMACHASM, new Climate.ParameterPoint(
                Parameter.span(-1, 1),
                Parameter.span(-1, -0.8f),
                Parameter.span(-1.2f, 1),
                Parameter.span(0f, 1f),
                Parameter.span(0.2f, 0.9f),
                Parameter.span(-1, 1),
                0
            ));
        }
        if (RuCommonConfig.TOGGLE_REDSTONE_CAVES.get() > 0) {
            // Low humidity, negative continents
            BiomePlacement.addOverworld(RUBiomes.REDSTONE_CAVES, new Climate.ParameterPoint(
                Parameter.span(-1, 1),
                Parameter.span(-1, -0.8f),
                Parameter.span(-1.2f, 1),
                Parameter.span(-1f, 0f),
                Parameter.span(0.2f, 0.9f),
                Parameter.span(-1, 1),
                0
            ));
        }
        if (RuCommonConfig.TOGGLE_SCORCHING_CAVES.get() > 0) {
            // Low to medium humidity, below 1 depth
            BiomePlacement.addOverworld(RUBiomes.SCORCHING_CAVES, new Climate.ParameterPoint(
                Parameter.span(-1, 1f),
                Parameter.span(-1, 1),
                Parameter.span(-1.2f, 1),
                Parameter.span(-0.375f, 0.05f),
                Parameter.point(1.1f),
                Parameter.span(-1, 1),
                0
            ));
        }

        // Swamps
        if (RuCommonConfig.TOGGLE_SPIRES.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.FROZEN_RIVER, RUBiomes.SPIRES, CriterionBuilder.valueMin(
                BiomeParameterTargets.EROSION, 0.6f
            ));
            BiomePlacement.addSubOverworld(Biomes.ICE_SPIKES, RUBiomes.SPIRES, CriterionBuilder.valueMin(
                BiomeParameterTargets.EROSION, 0.6f
            ));
            BiomePlacement.addSubOverworld(Biomes.SNOWY_PLAINS, RUBiomes.SPIRES, CriterionBuilder.valueMin(
                BiomeParameterTargets.EROSION, 0.6f
            ));
            BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, RUBiomes.SPIRES, CriterionBuilder.valueMin(
                BiomeParameterTargets.EROSION, 0.6f
            ));
            BiomePlacement.addSubOverworld(Biomes.TAIGA, RUBiomes.SPIRES, CriterionBuilder.allOf(
                CriterionBuilder.valueMax(BiomeParameterTargets.TEMPERATURE, -0.45f),
                CriterionBuilder.valueMin(BiomeParameterTargets.EROSION, 0.6f)
            ));
        }

        // Plains/Meadows
        if (RuCommonConfig.TOGGLE_FLOWER_FIELDS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SUNFLOWER_PLAINS, RUBiomes.FLOWER_FIELDS, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_CLOVER_PLAINS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SUNFLOWER_PLAINS, RUBiomes.CLOVER_PLAINS, 0.7f);
        }
        if (RuCommonConfig.TOGGLE_SHRUBLAND.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.PLAINS, RUBiomes.SHRUBLAND, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_GRASSLAND.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.PLAINS, RUBiomes.GRASSLAND, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_POPPY_FIELDS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.PLAINS, RUBiomes.POPPY_FIELDS, 0.2f);
        }
        if (RuCommonConfig.TOGGLE_PUMPKIN_FIELDS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.PLAINS, RUBiomes.PUMPKIN_FIELDS, 0.2f);
        }
        if (RuCommonConfig.TOGGLE_HIGHLAND_FIELDS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.MEADOW, RUBiomes.HIGHLAND_FIELDS, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_WISTERIA_GROVE.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.MEADOW, RUBiomes.WISTERIA_GROVE, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_TUNDRA.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SNOWY_PLAINS, RUBiomes.TUNDRA, 0.45f);
        }

        // Forests
        if (RuCommonConfig.TOGGLE_ORCHARD.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.FLOWER_FOREST, RUBiomes.ORCHARD, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_WILLOW_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, RUBiomes.WILLOW_FOREST, 0.3f);
            BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, RUBiomes.WILLOW_FOREST, CriterionBuilder.alternate(RUBiomes.WILLOW_FOREST, Biomes.BIRCH_FOREST));
        }
        if (RuCommonConfig.TOGGLE_AUTUMNAL_MAPLE_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, RUBiomes.AUTUMNAL_MAPLE_FOREST, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_SILVER_BIRCH_FOREST.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, RUBiomes.SILVER_BIRCH_FOREST, CriterionBuilder.alternate(RUBiomes.AUTUMNAL_MAPLE_FOREST, Biomes.BIRCH_FOREST));
        }
        if (RuCommonConfig.TOGGLE_BLACKWOOD_TAIGA.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.DARK_FOREST, RUBiomes.BLACKWOOD_TAIGA, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_MAPLE_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.FOREST, RUBiomes.MAPLE_FOREST, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_TEMPERATE_GROVE.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.FOREST, RUBiomes.TEMPERATE_GROVE, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_DECIDUOUS_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.FOREST, RUBiomes.DECIDUOUS_FOREST, 0.3f);
            BiomePlacement.addSubOverworld(RUBiomes.DECIDUOUS_FOREST, Biomes.FOREST, CriterionBuilder.valueMin(BiomeParameterTargets.TEMPERATURE, -0.1f));
        }
        if (RuCommonConfig.TOGGLE_COLD_DECIDUOUS_FOREST.get() > 0) {
            var noiseCriterion = CriterionBuilder.allOf(
                CriterionBuilder.alternate(RUBiomes.DECIDUOUS_FOREST, Biomes.FOREST),
                CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, -0.1f, 0.1f)
            );
            BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, RUBiomes.COLD_DECIDUOUS_FOREST, noiseCriterion);
            BiomePlacement.addSubOverworld(Biomes.SNOWY_PLAINS, RUBiomes.COLD_DECIDUOUS_FOREST, noiseCriterion);
        }
        if (RuCommonConfig.TOGGLE_MAGNOLIA_WOODLAND.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.CHERRY_GROVE, RUBiomes.MAGNOLIA_WOODLAND, 0.35f);
        }

        // Taigas
        if (RuCommonConfig.TOGGLE_REDWOODS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, RUBiomes.REDWOODS, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_SPARSE_REDWOODS.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, RUBiomes.SPARSE_REDWOODS, CriterionBuilder.alternate(RUBiomes.REDWOODS, Biomes.OLD_GROWTH_PINE_TAIGA));
        }
        if (RuCommonConfig.TOGGLE_BOREAL_TAIGA.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.TAIGA, RUBiomes.BOREAL_TAIGA, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_GOLDEN_BOREAL_TAIGA.get() > 0) {
            BiomePlacement.addSubOverworld(RUBiomes.BOREAL_TAIGA, RUBiomes.GOLDEN_BOREAL_TAIGA, CriterionBuilder.valueMin(BiomeParameterTargets.WEIRDNESS, 0));
        }
        if (RuCommonConfig.TOGGLE_COLD_BOREAL_TAIGA.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, RUBiomes.COLD_BOREAL_TAIGA, CriterionBuilder.alternate(RUBiomes.BOREAL_TAIGA, Biomes.TAIGA));
            BiomePlacement.addSubOverworld(RUBiomes.BOREAL_TAIGA, RUBiomes.COLD_BOREAL_TAIGA, CriterionBuilder.valueMax(BiomeParameterTargets.TEMPERATURE, -0.45f));
        }
        if (RuCommonConfig.TOGGLE_PINE_TAIGA.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.TAIGA, RUBiomes.PINE_TAIGA, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_FROZEN_PINE_TAIGA.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, RUBiomes.FROZEN_PINE_TAIGA, CriterionBuilder.alternate(RUBiomes.PINE_TAIGA, Biomes.TAIGA));
        }
        if (RuCommonConfig.TOGGLE_MOUNTAINS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.JAGGED_PEAKS, RUBiomes.MOUNTAINS, 0.45f);
            BiomePlacement.addSubOverworld(RUBiomes.MOUNTAINS, Biomes.JAGGED_PEAKS, CriterionBuilder.valueMin(BiomeParameterTargets.TEMPERATURE, -0.45f));
            BiomePlacement.addSubOverworld(Biomes.FROZEN_PEAKS, RUBiomes.MOUNTAINS, CriterionBuilder.allOf(
                CriterionBuilder.valueMin(BiomeParameterTargets.TEMPERATURE, -0.45f),
                CriterionBuilder.alternate(RUBiomes.MOUNTAINS, Biomes.JAGGED_PEAKS)
            ));
        }
        if (RuCommonConfig.TOGGLE_PINE_SLOPES.get() > 0) {
            BiomePlacement.addSubOverworld(Biomes.SNOWY_SLOPES, RUBiomes.PINE_SLOPES, CriterionBuilder.allOf(
                    CriterionBuilder.valueMin(BiomeParameterTargets.TEMPERATURE, -0.45f),
                    CriterionBuilder.alternate(RUBiomes.MOUNTAINS, Biomes.JAGGED_PEAKS)
            ));
            BiomePlacement.addSubOverworld(Biomes.GROVE, RUBiomes.PINE_SLOPES, CriterionBuilder.allOf(
                CriterionBuilder.valueMin(BiomeParameterTargets.TEMPERATURE, -0.45f),
                CriterionBuilder.alternate(RUBiomes.MOUNTAINS, Biomes.JAGGED_PEAKS)
            ));
        }

        // Savannas
        if (RuCommonConfig.TOGGLE_PRAIRIE.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SAVANNA, RUBiomes.PRAIRIE, 0.3f);
            BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, RUBiomes.PRAIRIE, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_BARLEY_FIELDS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SAVANNA, RUBiomes.BARLEY_FIELDS, 0.3f);
            BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, RUBiomes.BARLEY_FIELDS, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_DRY_BUSHLAND.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SAVANNA, RUBiomes.DRY_BUSHLAND, 0.45f);
            BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, RUBiomes.DRY_BUSHLAND, 0.45f);
        }

        // Jungles
        if (RuCommonConfig.TOGGLE_BAMBOO_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.BAMBOO_JUNGLE, RUBiomes.BAMBOO_FOREST, 0.45f);
        }
        if (RuCommonConfig.TOGGLE_RAINFOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.JUNGLE, RUBiomes.RAINFOREST, 0.4f);
        }
        if (RuCommonConfig.TOGGLE_SPARSE_RAINFOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.SPARSE_JUNGLE, RUBiomes.SPARSE_RAINFOREST, 0.4f);
        }

        // Deserts/Badlands
        if (RuCommonConfig.TOGGLE_OUTBACK.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.BADLANDS, RUBiomes.OUTBACK, 0.7f);
            BiomePlacement.addSubOverworld(RUBiomes.OUTBACK, Biomes.BADLANDS, CriterionBuilder.not(CriterionBuilder.allOf(
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -0.35f, 0.35f),
                CriterionBuilder.valueMin(BiomeParameterTargets.EROSION, -0.2225f)
            )));
        }
        if (RuCommonConfig.TOGGLE_BAOBAB_SAVANNA.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.ERODED_BADLANDS, RUBiomes.BAOBAB_SAVANNA, 0.6f);
            BiomePlacement.addSubOverworld(RUBiomes.BAOBAB_SAVANNA, Biomes.ERODED_BADLANDS, CriterionBuilder.valueMax(BiomeParameterTargets.EROSION, -0.375f));
        }
        if (RuCommonConfig.TOGGLE_STEPPE.get() > 0) {
            BiomePlacement.addSubOverworld(RUBiomes.BAOBAB_SAVANNA, RUBiomes.STEPPE, CriterionBuilder.not(CriterionBuilder.allOf(
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -0.45f, 0.45f),
                CriterionBuilder.valueMin(BiomeParameterTargets.EROSION, -0.25f)
            )));
        }
        if (RuCommonConfig.TOGGLE_EUCALYPTUS_FOREST.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.WOODED_BADLANDS, RUBiomes.EUCALYPTUS_FOREST, 0.5f);
        }
        if (RuCommonConfig.TOGGLE_JOSHUA_DESERT.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.DESERT, RUBiomes.JOSHUA_DESERT, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_SAGUARO_DESERT.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.DESERT, RUBiomes.SAGUARO_DESERT, 0.3f);
        }
        if (RuCommonConfig.TOGGLE_ARID_MOUNTAINS.get() > 0) {
            var noiseCriterion = CriterionBuilder.allOf(
                CriterionBuilder.anyOf(
                    CriterionBuilder.valueMax(BiomeParameterTargets.EROSION, -0.78f),
                    CriterionBuilder.allOf(
                            CriterionBuilder.valueMax(BiomeParameterTargets.EROSION, -0.375f),
                            CriterionBuilder.valueMin(BiomeParameterTargets.CONTINENTALNESS, 0.03f)
                    )
                ),
                CriterionBuilder.anyOf(
                    CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -0.767f, -0.567f),
                    CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, 0.567f, 0.767f)
                )
            );
            BiomePlacement.addSubOverworld(Biomes.ERODED_BADLANDS, RUBiomes.ARID_MOUNTAINS, noiseCriterion);
            BiomePlacement.addSubOverworld(Biomes.BADLANDS, RUBiomes.ARID_MOUNTAINS, noiseCriterion);
            BiomePlacement.addSubOverworld(Biomes.WOODED_BADLANDS, RUBiomes.ARID_MOUNTAINS, noiseCriterion);
            BiomePlacement.addSubOverworld(RUBiomes.BAOBAB_SAVANNA, RUBiomes.ARID_MOUNTAINS, noiseCriterion);
            BiomePlacement.addSubOverworld(RUBiomes.EUCALYPTUS_FOREST, RUBiomes.ARID_MOUNTAINS, noiseCriterion);
        }

        // Misc.
        if (RuCommonConfig.TOGGLE_TOWERING_CLIFFS.get() > 0) {
            BiomePlacement.replaceOverworld(Biomes.WINDSWEPT_SAVANNA, RUBiomes.TOWERING_CLIFFS, 0.5f);
        }
        if (RuCommonConfig.TOGGLE_ICY_HEIGHTS.get() > 0) {
            var noiseCriterion = CriterionBuilder.allOf(
                CriterionBuilder.value(BiomeParameterTargets.EROSION, 0.45f, 0.55f),
                CriterionBuilder.valueMax(BiomeParameterTargets.CONTINENTALNESS, 0.03f),
                CriterionBuilder.valueMin(BiomeParameterTargets.WEIRDNESS, 0f)
            );
            BiomePlacement.addSubOverworld(Biomes.ICE_SPIKES, RUBiomes.ICY_HEIGHTS, noiseCriterion);
            BiomePlacement.addSubOverworld(Biomes.SNOWY_PLAINS, RUBiomes.ICY_HEIGHTS, noiseCriterion);
            BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, RUBiomes.ICY_HEIGHTS, noiseCriterion);
        }

        nether(RuCommonConfig.TOGGLE_MYCOTOXIC_UNDERGROWTH, Biomes.NETHER_WASTES, RUBiomes.MYCOTOXIC_UNDERGROWTH);
        nether(RuCommonConfig.TOGGLE_GLISTERING_MEADOW, Biomes.SOUL_SAND_VALLEY, RUBiomes.GLISTERING_MEADOW);
        nether(RuCommonConfig.TOGGLE_BLACKSTONE_BASIN, Biomes.CRIMSON_FOREST, RUBiomes.BLACKSTONE_BASIN);
        nether(RuCommonConfig.TOGGLE_INFERNAL_HOLT, Biomes.BASALT_DELTAS, RUBiomes.INFERNAL_HOLT);
        nether(RuCommonConfig.TOGGLE_REDSTONE_ABYSS, Biomes.WARPED_FOREST, RUBiomes.REDSTONE_ABYSS);

        SurfaceGeneration.addNetherSurfaceRules(RegionsUnexplored.id("rules/nether"), RUSurfaceRuleBuilder.nether());
    }

    private static void nether(ConfigValue<Integer> config, ResourceKey<Biome> replacedBiome, ResourceKey<Biome> biome) {
        if (config.get() > 0) {
            BiomePlacement.replaceNether(replacedBiome, biome, 0.4);
        }
    }
}
