package net.regions_unexplored.datagen.provider.registry.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RUShrubFeatures;
import net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuAquaticPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;
import net.regions_unexplored.registry.RUEntityTypes;
import net.regions_unexplored.registry.data.RUBiomes;

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.util.RUBiomeUtils.*;

public class AquaticBiomes {
    private static MobSpawnSettings.Builder baseIslandSpawning(boolean isTropical) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        if(isTropical){
            spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
            spawnBuilder.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 1, 2));
            spawnBuilder.addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 1, 1));
        }
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }
    
    private static MobSpawnSettings.Builder baseOceanSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 3, 4, 15);
        return spawnBuilder;
    }
    
    private static MobSpawnSettings.Builder baseRiverSpawning(boolean moreDrowned) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 4));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 1, 5));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.MONSTER, moreDrowned ? 100 : 1, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 1, 1));
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseOceanGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addWaterTrees(builder);
        BiomeDefaultFeatures.addDefaultGrass(builder);
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        return builder;
    }
    private static BiomeGenerationSettings.Builder baseRiverGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        //add default flowers
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
        return builder;
    }
    private static BiomeGenerationSettings.Builder baseIslandGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean isTropical) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeUtils.globalOverworldGeneration(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        //add default flowers
        BiomeDefaultFeatures.addDefaultMushrooms(builder);
        if(isTropical){
            BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
            BiomeDefaultFeatures.addJungleVines(builder);
            BiomeDefaultFeatures.addJungleMelons(builder);
        }
        return builder;
    }

    public static Biome alphaGrove(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(1857757)
            .foliageColorOverride(6028091)
            .grassColorOverride(8901207);

        //add features
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_ALPHA_GROVE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ALPHA_DANDELION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ALPHA_ROSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseIslandSpawning(false);

        return biomeBuilder(0.6f, 0.6f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x446fd2)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome coldRiver(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .waterColor(NORMAL_WATER_COLOR)
                .foliageColorOverride(-5718172)
                .grassColorOverride(-4733087);

        //add features
        BiomeGenerationSettings.Builder builder = baseRiverGeneration(featureGetter, carverGetter);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseRiverSpawning(false);

        return biomeBuilder(0.4f, 0.7f)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome hyacinthDeeps(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(3770057)
            .foliageColorOverride(-8275350)
            .grassColorOverride(-9782677);

        //add features
        BiomeGenerationSettings.Builder builder = baseOceanGeneration(featureGetter, carverGetter);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_NORMAL);
        BiomeDefaultFeatures.addLukeWarmKelp(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, RuAquaticPlacements.SPECIAL_HYACINTH_ROCKS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuAquaticPlacements.SPECIAL_TALL_HYACINTH_STOCK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuAquaticPlacements.SPECIAL_HYACINTH_PLANTS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuAquaticPlacements.SPECIAL_HYACINTH_FLOWERS);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseOceanSpawning();
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 1, 5));

        return biomeBuilder(0.5f, 0.5f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x052133)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome muddyRiver(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(0x3f6fb4)
            .foliageColorOverride(-7159980)
            .grassColorOverride(-6044317);

        //add features
        BiomeGenerationSettings.Builder builder = baseRiverGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuAquaticPlacements.PATCH_CATTAIL);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_TALL_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_SHORT_GRASS_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseRiverSpawning(true);

        return biomeBuilder(0.6f, 0.7f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x717868)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome tropicalRiver(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(2202835)
            .foliageColorOverride(4237620)
            .grassColorOverride(6798388);

        //add features
        BiomeGenerationSettings.Builder builder = baseRiverGeneration(featureGetter, carverGetter);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_TROPICAL_RIVER);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseRiverSpawning(false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.TURTLE, 1, 1));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, 25, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 8, 8));

        return biomeBuilder(0.8f, 0.7f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x0a57a6)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome rockyReef(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .waterColor(-13255466)
                .foliageColorOverride(-11617740)
                .grassColorOverride(-11225797);

        //add features
        BiomeGenerationSettings.Builder builder = baseOceanGeneration(featureGetter, carverGetter);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEA_PICKLE);
        BiomeDefaultFeatures.addLukeWarmKelp(builder);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, RuAquaticPlacements.SPECIAL_ROCKY_REEF_ROCKS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_ROCKY_REEF);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.ROCKY_REEF));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuAquaticPlacements.SPECIAL_MAGNOLIAS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_UNRECOVERABLY_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = (new MobSpawnSettings.Builder())
                .addSpawn(MobCategory.WATER_CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 3))
                .addSpawn(MobCategory.WATER_AMBIENT, 25, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 8, 8))
                .addSpawn(MobCategory.WATER_CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 1, 2));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        return biomeBuilder(0.8f, 0.5f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x558aa8)
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome ashenWoodland(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(0x8B949A)
            .foliageColorOverride(15326658)
            .grassColorOverride(12434605);

        //add features
        BiomeGenerationSettings.Builder builder = baseIslandGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, RuVegetationPlacements.PATCH_ASH_VENTS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_ASHEN_WOODLAND);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.ASHEN_WOODLAND));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_WILTING_TRILLIUM);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ASHEN_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ASHEN_GRASS_SMOULDERING);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(RUEntityTypes.ASHEN.get(), 4, 4));
        spawnBuilder.addMobCharge(RUEntityTypes.ASHEN.get(), 1, 0.25);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        return biomeBuilder(2f, 0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xb1ae9c)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x9e958f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x558aa8)
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0f)
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.ASH, 0.005F)))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }

    public static Biome tropics(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
            .waterColor(0xff35bcd6)
            .foliageColorOverride(-11617740)
            .grassColorOverride(-11225797);

        //add features
        BiomeGenerationSettings.Builder builder = baseIslandGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_TROPICS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUShrubFeatures.get(RUBiomes.TROPICS));
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_HIBISCUS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ELEPHANT_EAR_DENSE);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseIslandSpawning(true);

        return biomeBuilder(1.05f, 0.95f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x558aa8)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_JUNGLE))
            .specialEffects(effectBuilder.build())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(builder.build())
            .build();
    }
}
