package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.regions_unexplored.platform.Registrar;
import net.regions_unexplored.worldgen.foliageplacer.*;
import net.regions_unexplored.worldgen.rootplacer.MagnoliaRootPlacer;
import net.regions_unexplored.worldgen.rootplacer.WillowRootPlacer;

import java.util.function.Supplier;

public interface RURootPlacerTypes {
    Supplier<RootPlacerType<MagnoliaRootPlacer>> MAGNOLIA = register("magnolia", MagnoliaRootPlacer.TYPE);
    Supplier<RootPlacerType<WillowRootPlacer>> WILLOW = register("willow", WillowRootPlacer.TYPE);

    static <T extends RootPlacer> Supplier<RootPlacerType<T>> register(String name, RootPlacerType<T> type) {
        Registrar.register(BuiltInRegistries.ROOT_PLACER_TYPE, name, () -> type);
        return () -> type;
    }

    static void init() {
    }
}
