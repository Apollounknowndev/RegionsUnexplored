package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.storage.loot.LootTable;
import net.regions_unexplored.RegionsUnexplored;

public interface RULootTables {
    ResourceKey<LootTable> HARVEST_DUSKMELON = key("harvest/duskmelon");
    ResourceKey<LootTable> HARVEST_SALMONBERRY_BUSH = key("harvest/salmonberry_bush");
    ResourceKey<LootTable> HARVEST_APPLE_OAK_LEAVES = key("harvest/apple_oak_leaves");
    
    static ResourceKey<LootTable> key(String name) {
        return RegionsUnexplored.key(Registries.LOOT_TABLE, name);
    }
}
