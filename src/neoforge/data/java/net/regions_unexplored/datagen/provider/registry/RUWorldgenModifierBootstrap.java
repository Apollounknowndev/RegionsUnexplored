package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.processor.LithostitchedProcessorLists;
import dev.worldgen.lithostitched.api.worldgen.processor.LithostitchedProcessors;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
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
        
        context.register(
            key("structure_repalette/mansion"),
            WorldgenModifier.builder().addProcessorListProcessors(
                registry.getOrThrow(LithostitchedProcessorLists.WOODLAND_MANSION),
                LithostitchedProcessors.reference(registry.getOrThrow(RUProcessorLists.REPALETTE_WOODLAND_MANSION))
            )
        );
        
        addVanilla(context, "badlands_saguaros", RUPlacedFeatures.VANILLA_BADLANDS_SAGUAROS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS);
        addVanilla(context, "badlands_steppe_grass", RUPlacedFeatures.VANILLA_BADLANDS_STEPPE_GRASS, Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS);
        addVanilla(context, "basalt_deltas_ash_vents", RUPlacedFeatures.VANILLA_BASALT_DELTAS_ASH_VENTS, Biomes.BASALT_DELTAS);
        addVanilla(context, "beach_palm_trees", RUPlacedFeatures.VANILLA_BEACH_PALM_TREES, Biomes.BEACH);
        addVanilla(context, "birch_orange_coneflowers", RUPlacedFeatures.VANILLA_BIRCH_ORANGE_CONEFLOWERS, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
        addVanilla(context, "desert_sandy_grass", RUPlacedFeatures.VANILLA_DESERT_SANDY_GRASS, Biomes.DESERT);
        addVanilla(context, "forest_flowers", RUPlacedFeatures.VANILLA_FOREST_FLOWERS, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.DARK_FOREST);
        addVanilla(context, "jungle_bamboo_trees", RUPlacedFeatures.VANILLA_JUNGLE_BAMBOO_TREES, Biomes.BAMBOO_JUNGLE, Biomes.JUNGLE);
        addVanilla(context, "jungle_elephant_ears", RUPlacedFeatures.VANILLA_JUNGLE_ELEPHANT_EARS, Biomes.BAMBOO_JUNGLE, Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
        addVanilla(context, "jungle_hibiscuses", RUPlacedFeatures.VANILLA_JUNGLE_HIBISCUSES, Biomes.BAMBOO_JUNGLE, Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
        addVanilla(context, "mangrove_flowering_lilies", RUPlacedFeatures.VANILLA_MANGROVE_FLOWERING_LILIES, Biomes.MANGROVE_SWAMP);
        removeVanilla(context, "remove_mangrove_lily_pads", "mangrove_flowering_lilies", VegetationPlacements.PATCH_WATERLILY, Biomes.MANGROVE_SWAMP);
        addVanilla(context, "plains_bushes", RUPlacedFeatures.VANILLA_PLAINS_BUSHES, Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);
        addVanilla(context, "savanna_bushes", RUPlacedFeatures.VANILLA_SAVANNA_BUSHES, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        addVanilla(context, "snowy_frozen_grass", RUPlacedFeatures.VANILLA_SNOWY_FROZEN_GRASS, Biomes.GROVE, Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA);
        removeVanilla(context, "remove_snowy_plains_grass", "snowy_frozen_grass", VegetationPlacements.PATCH_GRASS_BADLANDS, Biomes.SNOWY_PLAINS);
        removeVanilla(context, "remove_snowy_taiga_grass", "snowy_frozen_grass", VegetationPlacements.PATCH_GRASS_TAIGA_2, Biomes.SNOWY_TAIGA);
        addVanilla(context, "swamp_cattails", RUPlacedFeatures.VANILLA_SWAMP_CATTAILS, Biomes.SWAMP, Biomes.MANGROVE_SWAMP);
        addVanilla(context, "swamp_willow_trees", RUPlacedFeatures.VANILLA_SWAMP_TREES, Biomes.SWAMP);
        removeVanilla(context, "remove_swamp_trees", "swamp_willow_trees", VegetationPlacements.TREES_SWAMP, Biomes.SWAMP);
        addVanilla(context, "taiga_purple_coneflowers", RUPlacedFeatures.VANILLA_TAIGA_PURPLE_CONEFLOWERS, Biomes.TAIGA);
        
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
