package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RUShrubFeatures;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.data.RUBiomes;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.*;

public class CoastalBiomes {
    private static MobSpawnSettings.Builder baseCoastSpawning(boolean hasTurtle) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        if (hasTurtle) {
            spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.TURTLE, 5, 2, 5));
        }
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseCoastGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        return builder;
    }

    public static Biome chalkCliffs(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(-8414642)
                .grassColorOverride(-6044317)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseCoastGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_CHALK_CLIFFS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.CHALK_CLIFFS));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DAISY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SINGLE_TASSEL_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCoastSpawning(false);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.775f)
                .downfall(0.7f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome grassyBeach(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(-8933043)
                .grassColorOverride(-2697863)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseCoastGeneration(featureGetter, carverGetter);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_GRASSY_BEACH);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SANDY_GRASS_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCoastSpawning(true);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.85f)
                .downfall(0.75f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome gravelBeach(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(-8673714)
                .grassColorOverride(-7819420)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseCoastGeneration(featureGetter, carverGetter);

        //add RU features

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCoastSpawning(false);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.6f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
}
