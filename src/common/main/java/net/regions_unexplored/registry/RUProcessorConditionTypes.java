package net.regions_unexplored.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.lithostitched.api.registry.LithostitchedBuiltInRegistries;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.worldgen.processorcondition.ProcessorCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.regions_unexplored.platform.Registrar;
import net.regions_unexplored.worldgen.processorcondition.ConfigCondition;
import net.regions_unexplored.worldgen.processorcondition.MatchingBiomesCondition;
import net.regions_unexplored.worldgen.trunkplacer.RedwoodTrunkPlacer;

import java.util.function.Supplier;

public interface RUProcessorConditionTypes {
    Supplier<MapCodec<MatchingBiomesCondition>> MATCHING_BIOMES = register("matching_biomes", MatchingBiomesCondition.CODEC);
    Supplier<MapCodec<ConfigCondition>> CONFIG = register("config", ConfigCondition.CODEC);
    
    static <T extends ProcessorCondition> Supplier<MapCodec<T>> register(String name, MapCodec<T> codec) {
        return Registrar.register(LithostitchedBuiltInRegistries.PROCESSOR_CONDITION_TYPE, name, () -> codec);
    }

    static void init() {
    }
}
