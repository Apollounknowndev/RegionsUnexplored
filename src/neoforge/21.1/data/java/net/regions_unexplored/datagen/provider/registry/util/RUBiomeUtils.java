package net.regions_unexplored.datagen.provider.registry.util;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuNetherPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;

public class RUBiomeUtils {
    public static final int NORMAL_WATER_COLOR = 4159204;
    public static final int NORMAL_WATER_FOG_COLOR = 329011;
    public static final int OVERWORLD_FOG_COLOR = 12638463;
    
    public static int calculateSkyColor(float temperature) {
        float temp = temperature / 3.0F;
        temp = Mth.clamp(temp, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - temp * 0.05F, 0.5F + temp * 0.1F, 1.0F);
    }
    
    public static BiomeSpecialEffects.Builder effectBuilder(float temperature) {
        return new BiomeSpecialEffects.Builder()
            .skyColor(calculateSkyColor(temperature))
            .fogColor(OVERWORLD_FOG_COLOR)
            .waterColor(NORMAL_WATER_COLOR)
            .waterFogColor(NORMAL_WATER_FOG_COLOR)
            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
        ;
    }
    
    public static Biome.BiomeBuilder biomeBuilder(float temperature, float downfall) {
        return biomeBuilder(temperature, downfall, true);
    }
    
    public static Biome.BiomeBuilder biomeBuilder(float temperature, float downfall, boolean hasPrecipitation) {
        return new Biome.BiomeBuilder().temperature(temperature).downfall(downfall).hasPrecipitation(hasPrecipitation);
    }
    
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }
}
