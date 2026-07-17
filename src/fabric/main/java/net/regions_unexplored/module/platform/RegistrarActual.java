package net.regions_unexplored.module.platform;

import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Actual;
import net.regions_unexplored.RegionsUnexplored;

import java.util.function.Supplier;

public class RegistrarActual {
    @Actual
    public static <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
        T registered = Registry.register(registry, RegionsUnexplored.id(name), value.get());
        return () -> registered;
    }
}
