package net.regions_unexplored.module.version;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.msrandom.multiplatform.annotations.Actual;

public class VersionHelperActual {
	@Actual
	public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key) {
		return registries.registryOrThrow(key);
	}
}
