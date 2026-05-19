package net.regions_unexplored.client.gui;

import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.client.RUClientConfig;
import net.regions_unexplored.config.state.common.BiomeTarget;
import net.regions_unexplored.config.state.common.RUCommonConfig;

import java.util.Comparator;
import java.util.function.Consumer;

public interface ConfigListBuilder {
    void addCategory(String name, Font font);
    void addBoolean(String name, Consumer<Boolean> setter, boolean getter, boolean defaultValue);
    void addSmallBoolean(String name, Consumer<Boolean> setter, boolean getter, boolean defaultValue);
    void addInteger(String name, double min, double max, double step, Consumer<Integer> setter, double getter, double defaultValue);
    void addDouble(String name, double min, double max, double step, Consumer<Double> setter, double getter, double defaultValue);

    default void build(Font font) {
        RUClientConfig client = RUConfigHandler.CLIENT;
        RUCommonConfig common = RUConfigHandler.COMMON;

        this.addCategory("Particle Rates", font);
        this.addDouble("Leaves", 0, 5, 0.1, value -> client.particleRates.leaves = value, client.particleRates.leaves, 1);
        this.addDouble("Prismarite", 0, 5, 0.1, value -> client.particleRates.prismarite = value, client.particleRates.prismarite, 1);
        
        this.addCategory("Eucalyptus Colors", font);
        this.addDouble("Transition Size", 0, 200, 1, value -> client.eucalyptusColors.transitionSize = value, client.eucalyptusColors.transitionSize, 25);
        this.addDouble("Saturation", 0, 1, 0.05, value -> client.eucalyptusColors.saturation = value, client.eucalyptusColors.saturation, 0.5);
        this.addDouble("Brightness", 0, 1, 0.05, value -> client.eucalyptusColors.brightness = value, client.eucalyptusColors.brightness, 0.8);
        
        this.addCategory("Biome Placements", font);
        for (var group : common.biomePlacements.placements.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().identifier().getPath())).toList()) {
            BiomeTarget target = group.getValue();
            this.addSmallBoolean(stringify(group.getKey()), target::setCanGenerate, target.canGenerate(), true);
        }
    }
    
    private static String stringify(ResourceKey<Biome> biome) {
        char[] chars = biome.identifier().getPath().toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '_') {
                found = false;
            }
        }
        return String.valueOf(chars).replace("_", " ");
    }
    
    enum DisplayMode {
        ALL,
        MOD_ONLY;
    }
}
