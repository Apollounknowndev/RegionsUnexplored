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
import net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.data.RUBiomes;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.*;

public class MountainBiomes {
    private static MobSpawnSettings.Builder baseMountainSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 5, 1, 3));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }
    
    private static MobSpawnSettings.Builder baseSlopeSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 5, 1, 3));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }
    
    private static MobSpawnSettings.Builder baseExtremeHillsSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.LLAMA, 5, 4, 6));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseMountainGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addExtraEmeralds(builder);
        BiomeDefaultFeatures.addInfestedStone(builder);
        return builder;
    }
    private static BiomeGenerationSettings.Builder baseSlopeGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addPlainGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addExtraEmeralds(builder);
        BiomeDefaultFeatures.addInfestedStone(builder);
        return builder;
    }
    private static BiomeGenerationSettings.Builder baseExtremeHillsGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        BiomeDefaultFeatures.addExtraEmeralds(builder);
        BiomeDefaultFeatures.addInfestedStone(builder);
        return builder;
    }

    public static Biome aridMountains(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(1.5f)
            .foliageColorOverride(0x98a53a)
            .grassColorOverride(0xbeb44a)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS));

        //add features
        BiomeGenerationSettings.Builder builder = baseMountainGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_ARID_MOUNTAINS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseExtremeHillsSpawning();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 6, 1, 2));

        return biomeBuilder(2, 0, false)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome highlandFields(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(2)
            .foliageColorOverride(0x81ba6a)
            .grassColorOverride(0x6aba6b)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW));

        //add features
        BiomeGenerationSettings.Builder builder = baseSlopeGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, RuMiscOverworldPlacements.ROCK_GROUP_HIGHLAND_FIELDS);
        
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_HIGHLAND_FIELDS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DAISIES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSlopeSpawning();

        return biomeBuilder(0.65f, 0.6f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
    
    public static Biome pineSlopes(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.4f)
            .foliageColorOverride(0x758646)
            .grassColorOverride(0x84a75a)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_GROVE));
        
        //add features
        BiomeGenerationSettings.Builder builder = baseMountainGeneration(featureGetter, carverGetter);
        
        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_PINE_SLOPES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_PINE_ON_DIRT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.PINE_SLOPES));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);
        
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseMountainSpawning();
        
        return biomeBuilder(0.4f, 0.4f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome mountains(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.4f)
            .foliageColorOverride(0x758646)
            .grassColorOverride(0x84a75a)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS));

        //add features
        BiomeGenerationSettings.Builder builder = baseMountainGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_MOUNTAINS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_PINE_ON_DIRT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.REMOVED_MOUNTAINS));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseMountainSpawning();

        return biomeBuilder(0.25f, 0.6f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome toweringCliffs(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.7f)
            .foliageColorOverride(0x8fa960)
            .grassColorOverride(0x7e9052)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS));

        //add features
        BiomeGenerationSettings.Builder builder = baseExtremeHillsGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_TOWERING_CLIFFS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_PINE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.TOWERING_CLIFFS));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_HYSSOP);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseExtremeHillsSpawning();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 5, 1, 3));

        return biomeBuilder(0.75f, 0.8f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

}
