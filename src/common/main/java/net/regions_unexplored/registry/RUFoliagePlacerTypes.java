package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.regions_unexplored.platform.Registrar;
import net.regions_unexplored.worldgen.foliageplacer.*;

import java.util.function.Supplier;

public interface RUFoliagePlacerTypes {
    Supplier<FoliagePlacerType<BioshroomFoliagePlacer>> BIOSHROOM = register("bioshroom", BioshroomFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<MagnoliaFoliagePlacer>> MAGNOLIA = register("magnolia", MagnoliaFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<RUPineFoliagePlacer>> PINE = register("pine", RUPineFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<RedwoodFoliagePlacer>> REDWOOD = register("redwood", RedwoodFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<SakuraFoliagePlacer>> SAKURA = register("sakura", SakuraFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<SkinnyPineFoliagePlacer>> SKINNY_PINE = register("skinny_pine", SkinnyPineFoliagePlacer.TYPE);
    Supplier<FoliagePlacerType<WillowFoliagePlacer>> WILLOW = register("willow", WillowFoliagePlacer.TYPE);

    static <T extends FoliagePlacer> Supplier<FoliagePlacerType<T>> register(String name, FoliagePlacerType<T> type) {
        Registrar.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, name, () -> type);
        return () -> type;
    }

    static void init() {
    }
}
