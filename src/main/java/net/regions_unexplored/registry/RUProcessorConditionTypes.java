package net.regions_unexplored.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.lithostitched.api.registry.LithostitchedBuiltInRegistries;
import dev.worldgen.lithostitched.api.worldgen.processorcondition.ProcessorCondition;
import net.regions_unexplored.module.platform.Registrar;
import net.regions_unexplored.worldgen.processorcondition.ConfigCondition;

import java.util.function.Supplier;

public interface RUProcessorConditionTypes {
    Supplier<MapCodec<ConfigCondition>> CONFIG = register("config", ConfigCondition.CODEC);
    
    static <T extends ProcessorCondition> Supplier<MapCodec<T>> register(String name, MapCodec<T> codec) {
        return Registrar.register(LithostitchedBuiltInRegistries.PROCESSOR_CONDITION_TYPE, name, () -> codec);
    }

    static void init() {
    }
}
