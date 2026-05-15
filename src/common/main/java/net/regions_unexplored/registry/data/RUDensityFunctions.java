package net.regions_unexplored.registry.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.regions_unexplored.RegionsUnexplored;

public interface RUDensityFunctions {
    ResourceKey<DensityFunction> INFERNO_WEIGHT = key("inferno_weight");

    static ResourceKey<DensityFunction> key(String name) {
        return RegionsUnexplored.key(Registries.DENSITY_FUNCTION, name);
    }
}
