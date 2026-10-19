package net.regions_unexplored.module.platform;

import net.minecraft.core.Registry;
import net.regions_unexplored.RegionsUnexplored;
import java.util.function.Supplier;

//? if neoforge {
/*import net.neoforged.neoforge.registries.DeferredRegister;
import net.regions_unexplored.platform.neoforge.RegionsUnexploredNeo;
*///? }

public class Registrar {
    public static <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
        //? if neoforge
        //return RegionsUnexploredNeo.REGISTER_CACHE.computeIfAbsent(registry.key(), key -> DeferredRegister.create(registry.key().location(), RegionsUnexplored.MOD_ID)).register(name, value);
        //? else {
        T registered = Registry.register(registry, RegionsUnexplored.id(name), value.get());
        return () -> registered;
        //? }
    }
}
