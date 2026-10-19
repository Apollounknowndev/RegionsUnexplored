package net.regions_unexplored.registry.data;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.RegionsUnexplored;

public interface RURegions {
    ResourceKey<Region> RIVERS = key("rivers");
    ResourceKey<Region> SWAMPS = key("swamps");
    ResourceKey<Region> PLAINS = key("plains");
    ResourceKey<Region> FORESTS = key("forests");
    ResourceKey<Region> PRIMARY_TAIGAS = key("primary_taigas");
    ResourceKey<Region> SECONDARY_TAIGAS = key("secondary_taigas");
    ResourceKey<Region> RAINFORESTS = key("rainforests");
    ResourceKey<Region> PINE_SLOPES = key("pine_slopes");
    
    static ResourceKey<Region> key(ResourceKey<Biome> biome) {
        return key(biome.identifier().getPath());
    }
    
    static ResourceKey<Region> key(String name) {
        return RegionsUnexplored.key(LithostitchedRegistries.REGION, name);
    }
}
