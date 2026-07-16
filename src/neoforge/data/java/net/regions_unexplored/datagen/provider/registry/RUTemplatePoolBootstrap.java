package net.regions_unexplored.datagen.provider.registry;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.processor.LithostitchedProcessors;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.data.RUProcessorLists;
import net.regions_unexplored.registry.data.RUSurfaceRules;
import net.regions_unexplored.registry.data.RUTemplatePools;

import java.util.List;

public class RUTemplatePoolBootstrap {
    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        Holder<StructureTemplatePool> emptyPool = context.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY);
        
        context.register(
            RUTemplatePools.TRIAL_CHAMBERS_ASHEN,
            new StructureTemplatePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.single(RegionsUnexplored.stringId("trial_chambers/ashen")), 1)
            ), StructureTemplatePool.Projection.RIGID)
        );
    }
}
