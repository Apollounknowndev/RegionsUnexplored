package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.RegionsUnexplored;

public interface RUEntityTypeIds {
    ResourceKey<EntityType<?>> ASHEN = key("ashen");

    static ResourceKey<EntityType<?>> key(String name) {
        return RegionsUnexplored.key(Registries.ENTITY_TYPE, name);
    }
}
