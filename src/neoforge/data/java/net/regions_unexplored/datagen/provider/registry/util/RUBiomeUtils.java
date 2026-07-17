package net.regions_unexplored.datagen.provider.registry.util;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;

public class RUBiomeUtils {
    public static final int NORMAL_WATER_COLOR = 4159204;
    public static final int NORMAL_WATER_FOG_COLOR = 329011;
    public static final int OVERWORLD_FOG_COLOR = 12638463;
    
    public static int calculateSkyColor(float temperature) {
        float temp = temperature / 3.0F;
        temp = Mth.clamp(temp, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - temp * 0.05F, 0.5F + temp * 0.1F, 1.0F);
    }
    
    public static BiomeSpecialEffects.Builder effectBuilder() {
        return new BiomeSpecialEffects.Builder().waterColor(NORMAL_WATER_COLOR);
    }
    
    public static Biome.BiomeBuilder biomeBuilder(float temperature, float downfall) {
        return biomeBuilder(temperature, downfall, true);
    }
    
    public static Biome.BiomeBuilder biomeBuilder(float temperature, float downfall, boolean hasPrecipitation) {
        return new Biome.BiomeBuilder()
            .temperature(temperature)
            .downfall(downfall)
            .hasPrecipitation(hasPrecipitation)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, calculateSkyColor(temperature));
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
