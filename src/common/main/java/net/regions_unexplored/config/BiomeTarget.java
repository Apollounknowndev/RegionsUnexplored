package net.regions_unexplored.config;

import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.internal.config.ConfigValue;
import net.regions_unexplored.registry.data.RURegions;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

// TODO: Make this fully config-driven
public record BiomeTarget(ResourceKey<Level> level, ResourceKey<Region> region, ResourceKey<Biome> biome, List<ResourceKey<Biome>> targets, ConfigValue<Integer> weight, UnaryOperator<ParameterBuilder> operator) {
	public BiomeTarget {
		BiomeTargets.ALL.add(this);
	}
	
	@SafeVarargs
	public static BiomeTarget create(ResourceKey<Biome> biome, ResourceKey<Biome>... targets) {
		return create(Level.OVERWORLD, RURegions.key(biome), biome, List.of(targets), b -> b);
	}
	
	public static BiomeTarget create(ResourceKey<Region> region, ResourceKey<Biome> biome, ResourceKey<Biome> target, UnaryOperator<ParameterBuilder> operator) {
		return create(Level.OVERWORLD, region, biome, List.of(target), operator);
	}
	
	public static BiomeTarget create(ResourceKey<Level> level, ResourceKey<Region> region, ResourceKey<Biome> biome, ResourceKey<Biome> target, UnaryOperator<ParameterBuilder> operator) {
		return create(level, region, biome, List.of(target), operator);
	}
	
	public static BiomeTarget create(ResourceKey<Level> level, ResourceKey<Region> region, ResourceKey<Biome> biome, List<ResourceKey<Biome>> targets, UnaryOperator<ParameterBuilder> operator) {
		return new BiomeTarget(level, region, biome, targets, RuCommonConfig.BIOME_WEIGHTS.get(biome), operator);
	}
	
	public Optional<BiomeInjector> createInjector(Registry<Biome> registry) {
		if (this.weight == null) {
			boolean enabled = RuCommonConfig.BIOME_TOGGLES.get(this.biome).get();
			if (!enabled) return Optional.empty();
		}
		
		Optional<Holder.Reference<Biome>> optional = registry.getHolder(this.biome);
		return optional.map(biome -> BiomeInjector.builder(this.level)
			.replacePartially(getTargets(registry, this.targets), biome, operator.apply(ParameterBuilder.create().region(this.region)))
		);
	}
	
	public static HolderSet<Biome> getTargets(Registry<Biome> registry, List<ResourceKey<Biome>> targets) {
		return HolderSet.direct(targets.stream().map(registry::getHolder).filter(Optional::isPresent).map(Optional::get).toList());
	}
}
