package net.regions_unexplored.lithostitched;

import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.BiomeTarget;
import net.regions_unexplored.config.BiomeTargets;
import net.regions_unexplored.config.RuCommonConfig;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RURegions;
import net.regions_unexplored.registry.data.RUSurfaceRules;
import net.regions_unexplored.world.surface.RUSurfaceRuleBuilder;

import java.util.Optional;

public class RULithostitched {
    public static void init() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                RegionsUnexplored.id("add_surface_rule/nether"),
                WorldgenModifier.builder().addSurfaceRule(Registries.levelToLevelStem(Level.NETHER), InjectionType.PREPEND, RUSurfaceRuleBuilder.nether())
            );
        });

        AddRegionsEvent.EVENT.register((registries, consumer) -> {
            var registry = registries.registryOrThrow(Registries.BIOME);
            for (BiomeTarget target : BiomeTargets.ALL) {
                if (target.weight() == null) continue;
                int weight = target.weight().get();
                if (weight <= 0) continue;
                consumer.accept(
                    RURegions.key(target.biome()),
                    target.level(),
                    target.getTargets(registry),
                    weight
                );
            }
            BiomeTargets.applyAdditionalRegions(registry, consumer);
        });
        
        AddBiomeInjectorsEvent.EVENT.register((registries, consumer) -> {
            var registry = registries.registryOrThrow(Registries.BIOME);
            for (BiomeTarget target : BiomeTargets.ALL) {
                Optional<BiomeInjector> injector = target.createInjector(registry);
                if (injector.isEmpty()) continue;
                consumer.accept(target.biome().identifier(), injector.get());
            }
            BiomeTargets.applyAdditionalInjectors(registry, consumer);
        });
    }
}
