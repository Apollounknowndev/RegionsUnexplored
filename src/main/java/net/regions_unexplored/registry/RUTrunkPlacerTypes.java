package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.regions_unexplored.module.platform.Registrar;
import net.regions_unexplored.worldgen.trunkplacer.*;

import java.util.function.Supplier;

public interface RUTrunkPlacerTypes {
    Supplier<TrunkPlacerType<AspenTrunkPlacer>> ASPEN = register("aspen", AspenTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<MagnoliaTrunkPlacer>> MAGNOLIA = register("magnolia", MagnoliaTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<RedwoodTrunkPlacer>> REDWOOD = register("redwood", RedwoodTrunkPlacer.TYPE);
    
    static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> register(String name, TrunkPlacerType<T> type) {
        Registrar.register(BuiltInRegistries.TRUNK_PLACER_TYPE, name, () -> type);
        return () -> type;
    }

    static void init() {
    }
}
