package net.regions_unexplored.module.version;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.msrandom.multiplatform.annotations.Expect;

public class VersionHelper {
	@Expect
	public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key);
}
