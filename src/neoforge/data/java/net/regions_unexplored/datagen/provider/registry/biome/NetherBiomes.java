package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuNetherPlacements;

import java.util.List;
import java.util.Optional;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.biomeBuilder;

public class NetherBiomes {

    public static Biome blackstoneBasin(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(5463027)
            .foliageColorOverride(5463027)
            .grassColorOverride(5463027);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        builder.addCarver(Carvers.NETHER_CAVE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.BLACKSTONE_BLOBS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.OBSIDIAN_SPIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.TREE_GROUP_BLACKSTONE_BASIN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_COBALT_ROOTS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_COBALT_EARLIGHT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.TALL_COBALT_EARLIGHT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_HANGING_EARLIGHT);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 4, 4))
                .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 1, 4))
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 2))
                .addSpawn(MobCategory.CREATURE, 60, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2));

        return biomeBuilder(2, 0, false)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 395547)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 395547)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 395547)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.of(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2d)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111d))
            ))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WARPED_SPORE, 0.02F)))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome infernalHolt(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(3479565)
            .foliageColorOverride(7295817)
            .grassColorOverride(7295817);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        builder.addCarver(Carvers.NETHER_CAVE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.TREE_GROUP_INFERNAL_HOLT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_BRIMSPROUT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.SINGLE_DORCEL);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_INFERNAL_HOLT_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.SINGLE_BRIMWOOD_SHRUB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 2, 4))
                .addSpawn(MobCategory.MONSTER, 9, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 3, 4))
                .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 3, 4))
                .addSpawn(MobCategory.CREATURE, 60, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2));

        return biomeBuilder(2, 0, false)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 3479565)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 3479565)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 3479565)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.of(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2d)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111d))
            ))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WHITE_ASH, 0.03F)))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome glisteringMeadow(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(12058781)
            .foliageColorOverride(12058781)
            .grassColorOverride(12058781);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        builder.addCarver(Carvers.NETHER_CAVE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.GLISTERING_MEADOW_ROCK);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTER_BULB);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTERING_SPROUT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTERING_FERN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTERING_BLOOM);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTER_SPIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_GLISTERING_IVY);



        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 1, 1))
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 1))
                .addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2))
                .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 1, 3));

        return biomeBuilder(2, 0, false)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 4328552)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 4328552)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 12058781)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.of(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2d)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111d))
            ))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.WARPED_SPORE, 0.02F)))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_WARPED_FOREST))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome mycotoxicUndergrowth(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(10717988)
            .foliageColorOverride(10717988)
            .grassColorOverride(10717988);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        builder.addCarver(Carvers.NETHER_CAVE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.TREE_GROUP_MYCOTOXIC_UNDERGROWTH);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_MYCOTOXIC_MUSHROOMS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_MYCOTOXIC_GRASS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_MYCOTOXIC_DAISY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, RuNetherPlacements.PATCH_YELLOW_BIOSHROOMS);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 4, 4))
                .addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 3, 4))
                .addSpawn(MobCategory.CREATURE, 60, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2));

        return biomeBuilder(2, 0, false)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 10717988)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 10717988)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 10717988)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.of(SoundEvents.AMBIENT_WARPED_FOREST_LOOP),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2d)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111d))
            ))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(RUParticleTypes.MYCOTOXIC_SPORE.get(), 0.01F)))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_WARPED_FOREST))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome redstoneAbyss(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(10623252)
            .foliageColorOverride(10623252)
            .grassColorOverride(10623252);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED);
        BiomeDefaultFeatures.addNetherDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.LARGE_POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.POINTED_REDSTONE_CLUSTER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_REDSTONE_BUD);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_REDSTONE_BULB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 4, 4))
                .addSpawn(MobCategory.CREATURE, 60, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2));

        return biomeBuilder(2, 0, false)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 5439488)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 5439488)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 10623252)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.of(SoundEvents.AMBIENT_NETHER_WASTES_LOOP),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2d)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111d))
            ))
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.CRIMSON_SPORE, 0.025F)))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
}
