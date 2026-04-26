package net.regions_unexplored.registry.data;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.regions_unexplored.RegionsUnexplored;

public interface RUSurfaceRules {
    ResourceKey<RuleSource> OVERWORLD = key("overworld");
    
    ResourceKey<RuleSource> CAVES = key("overworld/caves");
    ResourceKey<RuleSource> SWAMP = key("overworld/swamp");
    ResourceKey<RuleSource> SURFACE = key("overworld/surface");
    ResourceKey<RuleSource> SURFACE_AND_UNDER_SURFACE = key("overworld/surface_and_under_surface");
    ResourceKey<RuleSource> UNDER_SURFACE = key("overworld/under_surface");
    
    ResourceKey<RuleSource> NETHER = key("nether");

    static ResourceKey<RuleSource> key(String name) {
        return RegionsUnexplored.key(LithostitchedRegistries.SURFACE_RULE, name);
    }
}
