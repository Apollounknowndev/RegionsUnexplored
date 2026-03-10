package net.regions_unexplored.lithostitched;

import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.Level;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.RuCommonConfig;
import net.regions_unexplored.registry.data.RURegions;
import net.regions_unexplored.world.surface.RUSurfaceRuleBuilder;

public class RULithostitched {
    public static void init() {
        /*AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                RegionsUnexplored.id("add_surface_rule/overworld"),
                WorldgenModifier.builder().prependSurfaceRule(Registries.levelToLevelStem(Level.OVERWORLD), RUSurfaceRuleBuilder.overworld())
            );
        });

        AddRegionsEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(RURegions.OVERWORLD_PRIMARY, Level.OVERWORLD, RuCommonConfig.OVERWORLD_PRIMARY_WEIGHT.get());
            consumer.accept(RURegions.OVERWORLD_SECONDARY, Level.OVERWORLD, RuCommonConfig.OVERWORLD_SECONDARY_WEIGHT.get());
            consumer.accept(RURegions.NETHER, Level.NETHER, RuCommonConfig.NETHER_WEIGHT.get());
        });*/
    }
}
