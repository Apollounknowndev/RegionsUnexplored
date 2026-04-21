package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.RegionsUnexplored;

public interface RUBlockIds {
    ResourceKey<Block> PEAT_DIRT = key("peat_dirt");
    ResourceKey<Block> SILT_DIRT = key("silt_dirt");

    static ResourceKey<Block> key(String name) {
        return RegionsUnexplored.key(Registries.BLOCK, name);
    }
}
