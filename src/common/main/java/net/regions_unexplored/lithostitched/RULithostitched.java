package net.regions_unexplored.lithostitched;

import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.densityfunction.LithostitchedDensityFunctions;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import dev.worldgen.lithostitched.api.worldgen.util.NoiseRouterTarget;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.BiomeTarget;
import net.regions_unexplored.config.BiomeTargets;
import net.regions_unexplored.config.RuCommonConfig;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RUDensityFunctions;
import net.regions_unexplored.registry.data.RURegions;
import net.regions_unexplored.registry.data.RUSurfaceRules;
import net.regions_unexplored.world.surface.RUSurfaceRuleBuilder;

import java.util.Optional;

public class RULithostitched {
    public static void init() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                RegionsUnexplored.id("add_nether_surface"),
                WorldgenModifier.builder().addSurfaceRule(Level.NETHER, InjectionType.PREPEND, RUSurfaceRuleBuilder.nether())
            );
            
            var biomes = registries.registryOrThrow(Registries.BIOME);
            var features = registries.registryOrThrow(Registries.PLACED_FEATURE);
            consumer.accept(
                RegionsUnexplored.id("inferno/no_water_springs"),
                WorldgenModifier.builder().removeFeatures(
                    biomes.getHolderOrThrow(RUBiomes.INFERNO),
                    features.getHolderOrThrow(MiscOverworldPlacements.SPRING_WATER),
                    GenerationStep.Decoration.FLUID_SPRINGS
                )
            );
            
            var dfs = registries.registryOrThrow(Registries.DENSITY_FUNCTION);
            consumer.accept(
                RegionsUnexplored.id("inferno/no_aquifers"),
                WorldgenModifier.builder().wrapNoiseRouter(Level.OVERWORLD, NoiseRouterTarget.FLUID_LEVEL_FLOODEDNESS, DensityFunctions.rangeChoice(
                    dfs.getOrThrow(RUDensityFunctions.INFERNO_WEIGHT),
                    0.001,
                    64,
                    DensityFunctions.constant(0),
                    LithostitchedDensityFunctions.wrappedMarker()
                ))
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
                    BiomeTarget.getTargets(registry, target.targets()),
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
            BiomeTargets.applyAdditionalInjectors(registries, consumer);
        });
    }
}
