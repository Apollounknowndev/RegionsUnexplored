package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.regions_unexplored.module.platform.Registrar;
import net.regions_unexplored.worldgen.trunkplacer.*;

import java.util.function.Supplier;

public interface RUTrunkPlacerTypes {
    Supplier<TrunkPlacerType<AspenTrunkPlacer>> ASPEN = register("aspen", AspenTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<BambooTrunkPlacer>> BAMBOO = register("bamboo", BambooTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<CobaltShrubTrunkPlacer>> COBALT_SHRUB = register("cobalt_shrub", CobaltShrubTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<DeadTrunkPlacer>> DEAD = register("dead", DeadTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<LargeJoshuaTrunkPlacer>> LARGE_JOSHUA = register("large_joshua", LargeJoshuaTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<MagnoliaTrunkPlacer>> MAGNOLIA = register("magnolia", MagnoliaTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<MediumJoshuaTrunkPlacer>> MEDIUM_JOSHUA = register("medium_joshua", MediumJoshuaTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<PalmTrunkPlacer>> PALM = register("palm", PalmTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<RedwoodTrunkPlacer>> REDWOOD = register("redwood", RedwoodTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<SaguaroTrunkPlacer>> SAGUARO = register("saguaro", SaguaroTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<ShrubTrunkPlacer>> SHRUB = register("shrub", ShrubTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<SmallOakTrunkPlacer>> SMALL_OAK = register("small_oak", SmallOakTrunkPlacer.TYPE);
    Supplier<TrunkPlacerType<SmallSocotraTrunkPlacer>> SMALL_SOCOTRA = register("small_socotra", SmallSocotraTrunkPlacer.TYPE);

    static <T extends TrunkPlacer> Supplier<TrunkPlacerType<T>> register(String name, TrunkPlacerType<T> type) {
        Registrar.register(BuiltInRegistries.TRUNK_PLACER_TYPE, name, () -> type);
        return () -> type;
    }

    static void init() {
    }
}
