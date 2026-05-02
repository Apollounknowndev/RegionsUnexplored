package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RUShrubFeatures;
import net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuAquaticPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.data.RUBiomes;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.*;

public class WetBiomes {
    private static MobSpawnSettings.Builder baseSwampSpawning(boolean hasWolfSpawns) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8));
        if(hasWolfSpawns){
            spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 2, 4));
            spawnBuilder.creatureGenerationProbability(0.03F);
        }
        return spawnBuilder;
    }
    private static MobSpawnSettings.Builder baseJungleSpawning(boolean hasWolfSpawns) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 40, 1, 2));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 2, 1, 1));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        if(hasWolfSpawns)spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 2, 4));
        return spawnBuilder;
    }
    
    private static BiomeGenerationSettings.Builder baseSwampGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean hasLilyPads, boolean hasFlowers) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        BiomeDefaultFeatures.addFossilDecoration(builder);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addSwampClayDisk(builder);
        if(hasFlowers) {
            builder.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        }
        builder.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        if(hasLilyPads) {
            builder.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        }
        builder.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        builder.addFeature(Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addSwampExtraVegetation(builder);
        builder.addFeature(Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        return builder;
    }

    private static BiomeGenerationSettings.Builder baseJungleGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean sparseMelons) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addJungleGrass(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        BiomeDefaultFeatures.addJungleVines(builder);
        if(sparseMelons) {
            BiomeDefaultFeatures.addSparseJungleMelons(builder);
        }
        else{
            BiomeDefaultFeatures.addJungleMelons(builder);
        }
        return builder;
    }

    public static Biome bayou(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .skyColor(-6110795)
            .fogColor(-5124939)
            .waterColor(-12354486)
            .waterFogColor(-10450614)
            .foliageColorOverride(-9333940)
            .grassColorOverride(-8609196)
            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP));

        //add features
        BiomeGenerationSettings.Builder builder = baseSwampGeneration(featureGetter, carverGetter, false, true);

        //add RU features
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BAYOU);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.BAYOU));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FLOWERING_LILY_PAD);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_SPARSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSwampSpawning(true);

        return biomeBuilder(1, 1)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome eucalyptusForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.7f)
            .foliageColorOverride(8828203)
            .grassColorOverride(9680182)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE));

        //add features
        BiomeGenerationSettings.Builder builder = baseJungleGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_EUCALYPTUS_FOREST);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.EUCALYPTUS_FOREST));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_WARATAH);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_SPARSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_TALL_GRASS);
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseJungleSpawning(true);

        return biomeBuilder(1.3f, 0.85f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome fen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.4f)
            .waterColor(-11629645)
            .waterFogColor(-12884349)
            .foliageColorOverride(8754506)
            .grassColorOverride(10858333)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP));

        //add features
        BiomeGenerationSettings.Builder builder = baseSwampGeneration(featureGetter, carverGetter, false, true);

        //add RU features
        builder.addFeature(Decoration.RAW_GENERATION, RuMiscOverworldPlacements.SPECIAL_WATER_EDGE);

        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_FEN);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_OAK_DENSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.FEN));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DUCKWEED);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuAquaticPlacements.PATCH_CATTAIL_DENSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN_DENSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSwampSpawning(true);

        return biomeBuilder(0.85f, 0.7f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome marsh(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = effectBuilder(0.9f)
            .waterColor(-12092489)
            .waterFogColor(-13677218)
            .foliageColorOverride(-8339092)
            .grassColorOverride(-8536223)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP));

        //add features
        BiomeGenerationSettings.Builder builder = baseSwampGeneration(featureGetter, carverGetter, true, true);

        //add RU features
        builder.addFeature(Decoration.RAW_GENERATION, RuMiscOverworldPlacements.SPECIAL_MARSH);
        builder.addFeature(Decoration.RAW_GENERATION, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER_DENSE);

        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_MARSH);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DUCKWEED);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_TALL_GRASS);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSwampSpawning(false);
        spawnBuilder.addSpawn(MobCategory.AXOLOTLS, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 5, 2, 6));

        return biomeBuilder(1, 1)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
    public static Biome fungalFen(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(calculateSkyColor(2.0F))
                .fogColor(12640721)
                .waterColor(6338444)
                .waterFogColor(25674)
                .foliageColorOverride(10667597)
                .grassColorOverride(8173383)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP));

        //add features
        BiomeGenerationSettings.Builder builder = baseSwampGeneration(featureGetter, carverGetter, true, false);

        //add RU features
        builder.addFeature(Decoration.LOCAL_MODIFICATIONS, RuMiscOverworldPlacements.SPECIAL_WATER_EDGE);

        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_FUNGAL_FEN);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.FUNGAL_FEN));

        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_PINK_BIOSHROOM);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSwampSpawning(true);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.MOOSHROOM, 8, 4, 8));
        spawnBuilder.addSpawn(MobCategory.AXOLOTLS, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 4, 2, 4));

        return biomeBuilder(1.15f, 1)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome oldGrowthBayou(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(-1350062718)
                .fogColor(-1350062718)
                .waterColor(-12354486)
                .waterFogColor(-10450614)
                .foliageColorOverride(-9333940)
                .grassColorOverride(-8609196)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP));

        //add features
        BiomeGenerationSettings.Builder builder = baseSwampGeneration(featureGetter, carverGetter, false, true);

        //add RU features
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_OLD_GROWTH_BAYOU);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.OLD_GROWTH_BAYOU));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SPECIAL_GIANT_LILY);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FLOWERING_LILY_PAD);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_DENSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERN_DENSE);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE);
        
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseSwampSpawning(false);

        return biomeBuilder(1.2f, 1)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
    
    public static Biome rainforest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .skyColor(calculateSkyColor(1F))
            .fogColor(OVERWORLD_FOG_COLOR)
            .waterColor(2202835)
            .waterFogColor(677798)
            .foliageColorOverride(-11032271)
            .grassColorOverride(-9718455)
            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE));
        
        //add features
        BiomeGenerationSettings.Builder builder = baseJungleGeneration(featureGetter, carverGetter, true);
        
        //add RU features
        builder.addFeature(Decoration.RAW_GENERATION, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER);
        
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_RAINFOREST);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.RAINFOREST));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_HIBISCUS);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_DENSE);
        
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseJungleSpawning(false);
        
        return biomeBuilder(0.95f, 0.9f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome sparseRainforest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .skyColor(calculateSkyColor(1F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(2202835)
                .waterFogColor(677798)
                .foliageColorOverride(-11032271)
                .grassColorOverride(-9718455)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE));

        //add features
        BiomeGenerationSettings.Builder builder = baseJungleGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(Decoration.RAW_GENERATION, RuMiscOverworldPlacements.SPECIAL_MOSS_PATCH_WITH_WATER_SPARSE);
        
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_SPARSE_RAINFOREST);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.RAINFOREST));
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_HIBISCUS);
        builder.addFeature(Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseJungleSpawning(true);

        return biomeBuilder(0.95f, 0.9f)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
}
