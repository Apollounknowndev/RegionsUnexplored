package net.regions_unexplored.registry.data;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.resources.ResourceKey;
import net.regions_unexplored.RegionsUnexplored;

public interface RURegions {
    ResourceKey<Region> OVERWORLD_PRIMARY = key("overworld_primary");
    ResourceKey<Region> OVERWORLD_SECONDARY = key("overworld_secondary");
    ResourceKey<Region> NETHER = key("nether");

    static ResourceKey<Region> key(String name) {
        return RegionsUnexplored.key(LithostitchedRegistries.REGION, name);
    }
}
