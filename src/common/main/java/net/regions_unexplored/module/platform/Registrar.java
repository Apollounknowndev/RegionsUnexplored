package net.regions_unexplored.module.platform;

import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.function.Supplier;

public class Registrar {
    @Expect
    public static <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value);
}
