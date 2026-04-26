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
	public static BiomeTarget create(ResourceKey<Level> level, ResourceKey<Biome> biome, ResourceKey<Biome>... targets) {
		return create(level, RURegions.key(biome), biome, b -> b, targets);
	}
	
	@SafeVarargs
	public static BiomeTarget create(ResourceKey<Level> level, ResourceKey<Region> region, ResourceKey<Biome> biome, UnaryOperator<ParameterBuilder> operator, ResourceKey<Biome>... targets) {
		return new BiomeTarget(level, region, biome, List.of(targets), RuCommonConfig.BIOME_WEIGHTS.get(biome), operator);
	}
	
	public Optional<BiomeInjector> createInjector(Registry<Biome> registry) {
		if (this.weight == null) {
			boolean enabled = RuCommonConfig.BIOME_TOGGLES.get(this.biome).get();
			if (!enabled) return Optional.empty();
		}
		
		Optional<Holder.Reference<Biome>> optional = registry.getHolder(this.biome);
		return optional.map(biome -> BiomeInjector.builder(this.level)
			.replacePartially(getTargets(registry), biome, operator.apply(ParameterBuilder.create().region(this.region)))
		);
	}
	
	public HolderSet<Biome> getTargets(Registry<Biome> registry) {
		return HolderSet.direct(this.targets.stream().map(registry::getHolder).filter(Optional::isPresent).map(Optional::get).toList());
	}
}
