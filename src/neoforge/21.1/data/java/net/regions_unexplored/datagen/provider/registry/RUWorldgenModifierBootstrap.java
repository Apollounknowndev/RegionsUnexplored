package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.processor.LithostitchedProcessors;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.data.RUProcessorLists;
import net.regions_unexplored.registry.data.RUSurfaceRules;

import static net.regions_unexplored.registry.data.RUDamageTypes.DORCEL;
import static net.regions_unexplored.registry.data.RUDamageTypes.DUSK_TRAP;

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
    }
    
    private static ResourceKey<WorldgenModifier> key(String name) {
        return RegionsUnexplored.key(LithostitchedRegistries.WORLDGEN_MODIFIER, name);
    }
}
