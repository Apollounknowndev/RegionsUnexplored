package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.processor.LithostitchedProcessors;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RUShrubFeatures;
import net.regions_unexplored.datagen.provider.registry.configured_feature.RuTreeFeatures;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuAquaticPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.lithostitched.ConfigPredicate;
import net.regions_unexplored.registry.data.RUPlacedFeatures;
import net.regions_unexplored.registry.data.RUProcessorLists;
import net.regions_unexplored.registry.data.RUSurfaceRules;

public class RUWorldgenModifierBootstrap {
    public static void bootstrap(BootstrapContext<WorldgenModifier> context) {
        context.register(
            key("surface_rule/overworld"),
            WorldgenModifier.builder().addSurfaceRule(Level.OVERWORLD, InjectionType.PREPEND, LithostitchedSurfaceRules.reference(
                context.lookup(LithostitchedRegistries.SURFACE_RULE).getOrThrow(RUSurfaceRules.OVERWORLD)
            ))
        );
        
        HolderGetter<StructureProcessorList> registry = context.lookup(Registries.PROCESSOR_LIST);
        context.register(
            key("processor_list/village_path_fix"),
            WorldgenModifier.builder().addProcessorListProcessors(
                HolderSet.direct(registry::getOrThrow, ProcessorLists.STREET_PLAINS, ProcessorLists.STREET_SAVANNA, ProcessorLists.STREET_SNOWY_OR_TAIGA),
                LithostitchedProcessors.reference(registry.getOrThrow(RUProcessorLists.VILLAGE_PATH_FIX))
            )
        );
        
        addVanilla(context, "swamp_cattails", RUPlacedFeatures.VANILLA_SWAMP_CATTAILS, Biomes.SWAMP, Biomes.MANGROVE_SWAMP);
        
        addVanilla(context, "mangrove_flowering_lilies", RUPlacedFeatures.VANILLA_MANGROVE_FLOWERING_LILIES, Biomes.MANGROVE_SWAMP);
        removeVanilla(context, "remove_mangrove_lily_pads", "mangrove_flowering_lilies", VegetationPlacements.PATCH_WATERLILY, Biomes.MANGROVE_SWAMP);
        
        addVanilla(context, "swamp_willow_trees", RuTreePlacements.TREE_GROUP_SWAMP, Biomes.SWAMP);
        removeVanilla(context, "remove_swamp_trees", "swamp_willow_trees", VegetationPlacements.TREES_SWAMP, Biomes.SWAMP);
        
        for (var entry : RUShrubFeatures.MAP.entrySet()) {
            ResourceKey<Biome> biome = entry.getKey();
            if (biome.identifier().getNamespace().equals("minecraft")) {
                String path = biome.identifier().getPath();
                addVanilla(context, "shrub_group/" + path, "common_shrubs", entry.getValue().placed(), biome);
            }
        }
    }
    
    @SafeVarargs
    private static void addVanilla(BootstrapContext<WorldgenModifier> context, String name, ResourceKey<PlacedFeature> feature, ResourceKey<Biome>... applicableBiomes) {
        addVanilla(context, name, name, feature, applicableBiomes);
    }
    
    @SafeVarargs
    private static void addVanilla(BootstrapContext<WorldgenModifier> context, String modifierName, String predicateName, ResourceKey<PlacedFeature> feature, ResourceKey<Biome>... applicableBiomes) {
        var biomes = context.lookup(Registries.BIOME);
        context.register(
            key("vanilla_changes/" + modifierName),
            WorldgenModifier.builder(new ConfigPredicate("vanilla_changes/" + predicateName)).addFeatures(
                HolderSet.direct(biomes::getOrThrow, applicableBiomes),
                context.lookup(Registries.PLACED_FEATURE).getOrThrow(feature),
                Decoration.VEGETAL_DECORATION
            )
        );
    }
    
    @SafeVarargs
    private static void removeVanilla(BootstrapContext<WorldgenModifier> context, String modifierName, String configName, ResourceKey<PlacedFeature> feature, ResourceKey<Biome>... applicableBiomes) {
        var biomes = context.lookup(Registries.BIOME);
        context.register(
            key("vanilla_changes/" + modifierName),
            WorldgenModifier.builder(new ConfigPredicate("vanilla_changes/" + configName)).removeFeatures(
                HolderSet.direct(biomes::getOrThrow, applicableBiomes),
                context.lookup(Registries.PLACED_FEATURE).getOrThrow(feature),
                Decoration.VEGETAL_DECORATION
            )
        );
    }
    
    private static ResourceKey<WorldgenModifier> key(String name) {
        return RegionsUnexplored.key(LithostitchedRegistries.WORLDGEN_MODIFIER, name);
    }
}
