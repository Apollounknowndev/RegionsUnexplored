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

public class TaigaBiomes {
    protected static final int NORMAL_WATER_COLOR = 4159204;
    protected static final int NORMAL_WATER_FOG_COLOR = 329011;
    private static final int OVERWORLD_FOG_COLOR = 12638463;

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static MobSpawnSettings.Builder baseTaigaSpawning() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 8, 4, 4)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3)).addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FOX, 8, 2, 4));
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        return spawnBuilder;
    }

    private static BiomeGenerationSettings.Builder baseTaigaGeneration(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter, boolean hasSweetBerries) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        RUBiomeFeatures.globalOverworldGeneration(builder);
        RUBiomeFeatures.grassSprouts(builder);
        BiomeDefaultFeatures.addFerns(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        if(hasSweetBerries) {
            BiomeDefaultFeatures.addCommonBerryBushes(builder);
        }
        return builder;
    }

    public static Biome blackwoodTaiga(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(11454879)
                .fogColor(11454879)
                .waterColor(3108258)
                .waterFogColor(4220035)
                .foliageColorOverride(4347179)
                .grassColorOverride(4089639)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BLACKWOOD_TAIGA_PRIMARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BLACKWOOD_TAIGA_SECONDARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_BLACKWOOD_TAIGA_TERTIARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.BLACKWOOD_DECORATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.BLACKWOOD_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.BLACKWOOD_DARK_OAK_SHRUB_MIX);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.825f)
                .downfall(0.765f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome borealTaiga(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.55F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(-12619852)
                .waterFogColor(7436392)
                .foliageColorOverride(8103502)
                .grassColorOverride(8957796)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.LARCH_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.GOLDEN_LARCH_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIRCH_ASPEN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_LARCH);

        RUBiomeFeatures.addTaigaFlowerPatch(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.LARCH_SHRUB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.4f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome goldenBorealTaiga(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.65F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(-12619852)
                .waterFogColor(7436392)
                .foliageColorOverride(12562512)
                .grassColorOverride(10400607)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.GOLDEN_LARCH_DENSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.LARCH_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.BIRCH_ASPEN);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_LARCH);


        RUBiomeFeatures.addTaigaFlowerPatch(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GOLDEN_LARCH_SHRUB_MIX);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.45f)
                .downfall(0.3f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome pineTaiga(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.7F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(7702086)
                .grassColorOverride(8693594)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, true);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_PINE_TAIGA);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.PINE_SHRUB_ON_GRASS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.FALLEN_PINE_ON_DIRT);

        RUBiomeFeatures.addTaigaFlowerPatch(builder);
        RUBiomeFeatures.addDirtSurfaceVegetation(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PINE_SHRUB);


        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.4f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome redwoods(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(0x7e9539)
                .grassColorOverride(0x7b9f39)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_REDWOODS_PRIMARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.TREE_GROUP_REDWOODS_SECONDARY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SINGLE);

        RUBiomeFeatures.redwoodDecoration(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDWOOD_SHRUB);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(1f)
                .downfall(0.8f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static Biome sparseRedwoods(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        BiomeSpecialEffects.Builder effectBuilder = (new BiomeSpecialEffects.Builder())
                .skyColor(calculateSkyColor(0.8F))
                .fogColor(OVERWORLD_FOG_COLOR)
                .waterColor(NORMAL_WATER_COLOR)
                .waterFogColor(NORMAL_WATER_FOG_COLOR)
                .foliageColorOverride(7512632)
                .grassColorOverride(8240185)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA));

        //add features
        BiomeGenerationSettings.Builder builder = baseTaigaGeneration(featureGetter, carverGetter, false);

        //add RU features
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.GIANT_REDWOOD_SPARSE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.OAK_BUSH_SPARSE);

        RUBiomeFeatures.redwoodDecoration(builder);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDWOOD_SHRUB);

        //add mob spawns
        MobSpawnSettings.Builder spawnBuilder = baseTaigaSpawning();

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.95f)
                .downfall(0.8f)
                .specialEffects(effectBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
}
