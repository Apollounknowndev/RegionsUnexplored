package net.regions_unexplored.lithostitched;

import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent.RegionConsumer;
import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.api.worldgen.densityfunction.LithostitchedDensityFunctions;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.util.NoiseRouterTarget;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.common.BiomeTarget;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RUDensityFunctions;
import net.regions_unexplored.registry.data.RURegions;
import net.regions_unexplored.world.surface.RUSurfaceRuleBuilder;

public class RULithostitched {
    public static void init() {
        AddWorldgenModifiersEvent.EVENT.register((registries, consumer) -> {
            consumer.accept(
                RegionsUnexplored.id("surface_rule/nether"),
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
	        Registry<Biome> registry = registries.registryOrThrow(Registries.BIOME);
            for (var entry : RUConfigHandler.COMMON.biomeGroups.groups.entrySet()) {
                addRegion(consumer, registry, entry.getKey(), entry.getValue());
            }
            for (var entry : RUConfigHandler.COMMON.biomePlacements.placements.entrySet()) {
                addRegion(consumer, registry, entry.getKey().identifier().getPath(), entry.getValue());
            }
        });
        
        AddBiomeInjectorsEvent.EVENT.register((registries, consumer) -> {
            var registry = registries.registryOrThrow(Registries.BIOME);
            for (var entry : RUConfigHandler.COMMON.biomePlacements.placements.entrySet()) {
                ResourceKey<Biome> biome = entry.getKey();
                BiomeTarget target = entry.getValue();
                BiomeInjector injector;
                if (!target.canGenerate()) continue;
                
                // Weighted
                if (target.weight.orElse(0) > 0 && target.canReplace.isPresent()) {
                    injector = BiomeInjector.builder(target.dimension).replacePartially(
                        BiomeTarget.getTargets(registry, target.canReplace.get()),
                        registry.getHolderOrThrow(biome),
                        target.getParameters().region(RURegions.key(biome))
                    );
                }
                // Toggled
                else if (target.canReplace.isPresent()) {
                    ParameterBuilder parameters = target.getParameters();
	                target.group.ifPresent(g -> parameters.region(RURegions.key(g)));
                    
                    injector = BiomeInjector.builder(target.dimension).replacePartially(
                        BiomeTarget.getTargets(registry, target.canReplace.get()),
                        registry.getHolderOrThrow(biome),
                        parameters
                    );
                }
                // Special
                else {
                    injector = BiomeTarget.createSpecialInjector(registries, registry.getHolderOrThrow(biome), target);
                }
                
                if (injector != null) {
                    consumer.accept(biome.identifier(), injector);
                }
            }
        });
    }
    
    private static void addRegion(RegionConsumer consumer, Registry<Biome> registry, String name, BiomeTarget target) {
        int weight = target.weight.orElse(0);
        if (weight <= 0 || target.canReplace.isEmpty()) return;
        consumer.accept(
            RURegions.key(name),
            target.dimension,
            BiomeTarget.getTargets(registry, target.canReplace.get()),
            weight
        );
    }
}
