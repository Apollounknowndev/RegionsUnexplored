package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.regions_unexplored.RegionsUnexplored;

public interface RUTemplatePools {
    ResourceKey<StructureTemplatePool> TRIAL_CHAMBERS_ASHEN = key("trial_chambers/ashen");
    
    private static ResourceKey<StructureTemplatePool> key(String name) {
        return RegionsUnexplored.key(Registries.TEMPLATE_POOL, name);
    }
}
