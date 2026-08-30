package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
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

public class AridBiomes {
    private static MobSpawnSettings.Builder baseSavannaSpawning(boolean hasExtraWolves) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.desertSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, hasExtraWolves ? 8 : 4, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 8));
        return spawnBuilder;
    }
    
    private static MobSpawnSettings.Builder baseDesertSpawning(boolean hasArmadilloSpawns) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 2, 6));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 2));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if(hasArmadilloSpawns) {
            spawnBuilder.addSpawn(MobCategory.CREATURE, 6, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 1, 2));
            spawnBuilder.creatureGenerationProbability(0.03F);
        }
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseSavannaGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean addGrass) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        globalOverworldGeneration(builder);
        if (addGrass) {
            BiomeDefaultFeatures.addSavannaGrass(builder);
        }
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        //add default flowers
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        return builder;
    }
    
    private static BiomeGenerationSettings.Builder baseDesertGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean denseCactus) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(builder);
        globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE_DESERT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
        if (!denseCactus) {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_CACTUS_DESERT);
        }
        else {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_CACTUS_DENSE);
        }
        BiomeDefaultFeatures.addDesertExtraDecoration(builder);
        return builder;
    }

    public static Biome baobabSavanna(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-6636971)
            .grassColorOverride(-4670891);

        //add features
        BiomeGenerationSettings.Builder builder = baseSavannaGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BAOBAB_SAVANNA);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.BAOBAB_SAVANNA));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_GRASS_SPROUTS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSavannaSpawning(true);

        return biomeBuilder(2f, 0.35f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome dryBushland(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-5060484)
            .grassColorOverride(-3552115);

        //add features
        BiomeGenerationSettings.Builder builder = baseSavannaGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_DRY_BUSHLAND);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.DRY_BUSHLAND));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_MALLOW);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_TALL_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSavannaSpawning(true);

        return biomeBuilder(1.5f, 0f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome joshuaDesert(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(0x7fae4d)
            .grassColorOverride(0xffeba3);

        //add features
        BiomeGenerationSettings.Builder builder = baseDesertGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_JOSHUA_DESERT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.JOSHUA_DESERT));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_MALLOW);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SANDY_GRASS_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_SPARSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseDesertSpawning(true);

        return biomeBuilder(2f, 0f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome outback(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-8016810)
            .grassColorOverride(-4670891);

        //add features
        BiomeGenerationSettings.Builder builder = baseDesertGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_OUTBACK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.OUTBACK));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_MALLOW);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DESERT_SHRUB_ON_SAND);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_STEPPE_SHRUB_ON_SAND);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseDesertSpawning(true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 8));
        spawnBuilder.creatureGenerationProbability(0.04F);

        return biomeBuilder(2f, 0f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome saguaroDesert(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-6836695)
            .grassColorOverride(-4212907);

        //add features
        BiomeGenerationSettings.Builder builder = baseDesertGeneration(featureGetter, carverGetter, true);

        //add RU features

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_SAGUARO_DESERT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SINGLE_BARREL_CACTUS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SANDY_GRASS_SPARSE);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseDesertSpawning(false);

        return biomeBuilder(2f, 0f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome steppe(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-5589135)
            .grassColorOverride(-5067675);

        //add features
        BiomeGenerationSettings.Builder builder = baseSavannaGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_GRASSES_STEPPE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSavannaSpawning(false);

        return biomeBuilder(1.5f, 0.15f, false)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
}
