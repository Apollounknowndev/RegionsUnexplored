package net.regions_unexplored.client.gui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.client.RUClientConfig;
import net.regions_unexplored.config.state.common.BiomeTarget;
import net.regions_unexplored.config.state.common.RUCommonConfig;
import net.regions_unexplored.config.state.common.RUCommonConfig.Misc.BranchMode;

import java.util.Comparator;
import java.util.function.Consumer;

public interface ConfigListBuilder {
    StringWidget addCategory(String name, Font font);
    <T extends StringRepresentable> void addEnum(String name, Consumer<T> setter, T getter, T[] values, T defaultValue);
    void addBoolean(String name, Consumer<Boolean> setter, boolean getter, boolean defaultValue);
    void addSmallBoolean(String name, Consumer<Boolean> setter, boolean getter, boolean defaultValue);
    void addInteger(String name, double min, double max, double step, Consumer<Integer> setter, double getter, double defaultValue);
    void addDouble(String name, double min, double max, double step, Consumer<Double> setter, double getter, double defaultValue);
    void addEntry(AbstractWidget widget);

    default void build(Font font) {
        RUClientConfig client = RUConfigHandler.CLIENT;
        RUCommonConfig common = RUConfigHandler.COMMON;

        this.addCategory("particle_rates", font);
        this.addDouble("leaves", 0, 5, 0.1, value -> client.particleRates.leaves = value, client.particleRates.leaves, 1);
        this.addDouble("prismarite", 0, 5, 0.1, value -> client.particleRates.prismarite = value, client.particleRates.prismarite, 1);
        
        this.addCategory("eucalyptus_colors", font);
        this.addDouble("transition_size", 0, 200, 1, value -> client.eucalyptusColors.transitionSize = value, client.eucalyptusColors.transitionSize, 25);
        this.addDouble("saturation", 0, 1, 0.05, value -> client.eucalyptusColors.saturation = value, client.eucalyptusColors.saturation, 0.5);
        this.addDouble("brightness", 0, 1, 0.05, value -> client.eucalyptusColors.brightness = value, client.eucalyptusColors.brightness, 0.8);
        
        this.addCategory("misc", font);
        this.addEnum("branch_mode", value -> common.misc.branchMode = value, common.misc.branchMode, BranchMode.values(), BranchMode.PLACE_BRANCHES);
        this.addBoolean("custom_dirts", value -> common.misc.customDirts = value, common.misc.customDirts, true);
        this.addBoolean("small_oak_trees", value -> common.misc.smallOakTrees = value, common.misc.smallOakTrees, false);
        this.addBoolean("painted_planks", value -> common.misc.paintedPlanks = value, common.misc.paintedPlanks, false);
        
        
        StringWidget biomePlacements = this.addCategory("biome_placements", font);
        biomePlacements.setTooltip(Tooltip.create(Component.literal(
            "Note: Biome placement has more enhanced configurations such as biome weightings, but you need to open the config file to edit those."
        ).withStyle(ChatFormatting.GRAY)));
        
        for (var group : common.biomePlacements.placements.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getKey().identifier().getPath())).toList()) {
            BiomeTarget target = group.getValue();
            this.addSmallBoolean(group.getKey().identifier().toLanguageKey("biome"), target::setCanGenerate, target.canGenerate(), true);
        }
    }
}
