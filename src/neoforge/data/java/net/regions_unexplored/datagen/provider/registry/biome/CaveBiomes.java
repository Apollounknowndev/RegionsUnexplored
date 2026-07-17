package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.RUEntityTypes;
import net.regions_unexplored.registry.RUParticleTypes;

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.*;

public class CaveBiomes {
    private static MobSpawnSettings.Builder baseCaveSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        BiomeDefaultFeatures.monsters(spawnBuilder, 95, 5, 0, 100, false);
	    return spawnBuilder;
    }
    private static MobSpawnSettings.Builder baseLushCaveSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.AXOLOTLS, 10, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 4, 6));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 25, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 8, 8));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseCaveGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addPlainGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addPlainVegetation(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        return builder;
    }
    private static BiomeGenerationSettings.Builder baseLushCaveGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean addClay) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addPlainGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        if(addClay){
            BiomeDefaultFeatures.addLushCavesSpecialOres(builder);
        }
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        return builder;
    }

    public static Biome ancientDelta(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(-13369345)
            .foliageColorOverride(-10118056)
            .grassColorOverride(16318340);

        //add features
        BiomeGenerationSettings.Builder builder = baseLushCaveGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.SPECIAL_CALCITE_POOL);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_BLADED_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SINGLE_CORPSE_FLOWER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SINGLE_DUSKMELON);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SINGLE_DUSKTRAP);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DROPLEAF);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseLushCaveSpawning();

        return biomeBuilder(1.125f, 0.8f, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome bioshroomCaves(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-11093361)
            .grassColorOverride(-11093410);

        //add features
        BiomeGenerationSettings.Builder builder = baseLushCaveGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BIOSHROOM_CAVES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_CAVE_BIOSHROOMS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_TALL_GRASS_CAVES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_CAVES);
        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseLushCaveSpawning();
        
        return biomeBuilder(1.25f, 0.9f, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome prismachasm(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-16737793)
            .grassColorOverride(-6625354);

        //add features
        BiomeGenerationSettings.Builder builder = baseCaveGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_PRISMOSS_SPROUT);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_CAVE_HYSSOP);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.PATCH_PRISMARITE_CLUSTER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.SPECIAL_HANGING_PRISMARITE_CLUSTER);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCaveSpawning();
        
        return biomeBuilder(0.925f, 0.9f, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(RUParticleTypes.PRISMARITE_SPARKLE.get(), 0.001f)))
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome redstoneCaves(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(NORMAL_WATER_COLOR)
            .foliageColorOverride(-6044317)
            .grassColorOverride(-6044317);

        //add features
        BiomeGenerationSettings.Builder builder = baseCaveGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RuMiscOverworldPlacements.SPECIAL_ORE_REDSTONE_LARGE);
        
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.SPECIAL_POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.SPECIAL_LARGE_POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.SPECIAL_POINTED_REDSTONE_CLUSTER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_REDSTONE_BUD);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_REDSTONE_BULB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCaveSpawning();

        return biomeBuilder(0.8f, 0.7f, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome inferno(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .waterColor(NORMAL_WATER_COLOR)
                .foliageColorOverride(-8949914)
                .grassColorOverride(-8621472);

        //add features
        BiomeGenerationSettings.Builder builder = baseCaveGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, RuMiscOverworldPlacements.SPECIAL_LAVA_FALL);
        builder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, RuMiscOverworldPlacements.SPECIAL_INFERNO_LAVA_DELTA);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.PATCH_ASH_VENTS_INFERNO);
        //builder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, RuMiscOverworldPlacements.SPECIAL_BASALT_BLOB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseCaveSpawning()
            .addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(RUEntityTypes.ASHEN.get(), 4, 4));

        return biomeBuilder(2f, 0f, true)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.003f)))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
}
