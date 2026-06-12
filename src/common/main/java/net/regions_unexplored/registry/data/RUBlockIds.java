package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.RegionsUnexplored;

public interface RUBlockIds {
    ResourceKey<Block> PEAT_DIRT = key("peat_dirt");
    ResourceKey<Block> SILT_DIRT = key("silt_dirt");
    ResourceKey<Block> HANGING_EARLIGHT = key("hanging_earlight");
    ResourceKey<Block> HANGING_EARLIGHT_PLANT = key("hanging_earlight_plant");
    ResourceKey<Block> GLISTERING_IVY = key("glistering_ivy");
    ResourceKey<Block> GLISTERING_IVY_PLANT = key("glistering_ivy_plant");
    ResourceKey<Block> DROPLEAF = key("dropleaf");
    ResourceKey<Block> DROPLEAF_PLANT = key("dropleaf_plant");
    ResourceKey<Block> SPANISH_MOSS = key("spanish_moss");
    ResourceKey<Block> SPANISH_MOSS_PLANT = key("spanish_moss_plant");
    ResourceKey<Block> KAPOK_VINES = key("kapok_vines");
    ResourceKey<Block> KAPOK_VINES_PLANT = key("kapok_vines_plant");

    static ResourceKey<Block> key(String name) {
        return RegionsUnexplored.key(Registries.BLOCK, name);
    }
}
