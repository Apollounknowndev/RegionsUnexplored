package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.RUBiomeFeatures;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;

public class ForestBiomes {
    protected static final int NORMAL_WATER_COLOR = 4159204;
    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static MobSpawnSettings.Builder baseForestSpawning(boolean hasWolfSpawns) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if(hasWolfSpawns)spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseForestGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean addDefaultFlowers) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeFeatures.globalOverworldGeneration(builder);
        RUBiomeFeatures.grassSprouts(builder);
        if(addDefaultFlowers){
            BiomeDefaultFeatures.addForestFlowers(builder);
        }
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        return builder;
    }

    public static Biome autumnalMapleForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.5F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(9877306)
                .grassColorOverride(12896058)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_AUTUMNAL_MAPLE_FOREST);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        RUBiomeFeatures.addTrillium(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.AUTUMNAL_SHRUB_MIX);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.25f)
                .downfall(0.35f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome bambooForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(10666421)
                .fogColor(11652277)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(11140963)
                .grassColorOverride(11853428)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BAMBOO_FOREST_PRIMARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BAMBOO_FOREST_SECONDARY);

        RUBiomeFeatures.addPinkFlowers(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.FERNS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.CHERRY_SHRUB);

        RUBiomeFeatures.addBamboo(builder);
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 80, 1, 2));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.875f)
                .downfall(0.8f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome magnoliaHighlands(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.75F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(8437033)
                .grassColorOverride(10406459)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, false);

        //add RU features
        RUBiomeFeatures.sakuraTrees(builder);

        RUBiomeFeatures.addTsubaki(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.DAY_LILY);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.MAGNOLIA_SHRUB_MIX);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.95f)
                .downfall(0.8f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome deciduousForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.7F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(7578936)
                .grassColorOverride(8700997)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIG_OAK_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.SMALL_OAK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_TALL);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SPARSE);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_OAK_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.DECIDUOUS_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.OAK_SHRUB);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.75f)
                .downfall(0.8f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome mapleForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.6F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(6462505)
                .grassColorOverride(9550928)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_MAPLE_FOREST);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_MAPLE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.MAPLE_SHRUB_MIX);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WHITE_TRILLIUM);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.5f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome mauveHills(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(1.0F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(5546361)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(3966928)
                .grassColorOverride(7518100)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        RUBiomeFeatures.mauveTrees(builder);

        RUBiomeFeatures.addAster(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TASSEL_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.MAUVE_ENCHANTED_SHRUB_MIX);
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(1.1f)
                .downfall(0.85f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome orchard(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.7F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(10669160)
                .grassColorOverride(11717735)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.APPLE_OAK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIG_APPLE_OAK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIG_OAK_SPARSE);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TASSEL_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.OAK_SHRUB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 8, 4, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.7f)
                .downfall(0.4f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome silverBirchForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.5F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(8960834)
                .grassColorOverride(11585338)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_SILVER_BIRCH_FOREST);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_SILVER_BIRCH);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TASSEL_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        RUBiomeFeatures.addOrangeCornflower(builder);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.6f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome temperateGrove(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(7054415)
                .grassColorOverride(8432236)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, RuMiscOverworldPlacements.ROCK_GROUP_TEMPERATE_GROVE);
        
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_WITH_BRANCH);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.MAPLE_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIRCH_ASPEN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_OAK_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        RUBiomeFeatures.addPurpleCornflower(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.OAK_SHRUB);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 15, 3, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.725f)
                .downfall(0.6f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome willowForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.7F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(7778144)
                .grassColorOverride(8037223)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST));

        //add features
        BiomeGenerationSettings.Builder builder = baseForestGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIG_WILLOW);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.WILLOW);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.SMALL_OAK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SINGLE);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BLUE_MAGNOLIA);

        RUBiomeFeatures.addWillowFlowers(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.FERNS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WILLOW_MAGNOLIA_SHRUB_MIX);
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseForestSpawning(true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 10, 3, 4));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.5f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
}
