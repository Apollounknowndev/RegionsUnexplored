package net.regions_unexplored.config.state.common;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RUDensityFunctions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*;
import static dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.TEMPERATURE;
import static net.regions_unexplored.config.json5.CommentedMapCodec.*;

public class BiomeTarget {
	public static final Codec<BiomeTarget> SPECIAL_CODEC = commented(
		Codec.BOOL, "special_enabled", "This biome has special placement rules. It can only be enabled or disabled."
	).codec().xmap(BiomeTarget::new, target -> target.specialEnabled.orElse(false));
	
	public static final Codec<BiomeTarget> FULL_CODEC = RecordCodecBuilder.create(i -> i.group(
		ResourceKey.codec(Registries.DIMENSION).lenientOptionalFieldOf("dimension", Level.OVERWORLD).forGetter(t -> t.dimension),
		Codec.STRING.lenientOptionalFieldOf("group").forGetter(t -> t.group),
		Codec.BOOL.fieldOf("enabled").forGetter(t -> t.enabled.orElse(false)),
		commented(ExtraCodecs.NON_NEGATIVE_INT.lenientOptionalFieldOf("weight"), "weight", "By default, all other biomes have a weight of 100.").forGetter(t -> t.weight),
		ResourceKey.codec(Registries.BIOME).listOf().lenientOptionalFieldOf("can_replace").forGetter(t -> t.canReplace),
		commented(Codec.unboundedMap(ClimateParameter.CODEC, DoubleRange.CODEC).lenientOptionalFieldOf("parameters"), "parameters", "Advanced feature! Edit this with caution.").forGetter(t -> t.parameters)
	).apply(i, BiomeTarget::new));
	
	public static final Codec<BiomeTarget> CODEC = Codec.either(SPECIAL_CODEC, FULL_CODEC).xmap(
		Either::unwrap,
		target -> target.specialEnabled.isPresent() ? Either.left(target) : Either.right(target)
	);
	
	public ResourceKey<Level> dimension;
	public Optional<String> group = Optional.empty();
	public Optional<Boolean> specialEnabled = Optional.empty();
	public Optional<Boolean> enabled = Optional.empty();
	public Optional<Integer> weight = Optional.empty();
	public Integer baseWeight = null;
	public Optional<List<ResourceKey<Biome>>> canReplace = Optional.empty();
	public Optional<Map<ClimateParameter, DoubleRange>> parameters = Optional.empty();
	
	public BiomeTarget() {
		this.dimension = Level.OVERWORLD;
		this.enabled = Optional.of(true);
	}
	
	public BiomeTarget(boolean specialEnabled) {
		this.dimension = Level.OVERWORLD;
		this.specialEnabled = Optional.of(specialEnabled);
	}
	
	public BiomeTarget(ResourceKey<Level> dimension, Optional<String> group, boolean enabled, Optional<Integer> weight, Optional<List<ResourceKey<Biome>>> canReplace, Optional<Map<ClimateParameter, DoubleRange>> parameters) {
		this.dimension = dimension;
		this.group = group;
		this.enabled = Optional.of(enabled);
		this.weight = weight;
		weight.ifPresent(i -> this.baseWeight = i);
		this.canReplace = canReplace;
		this.parameters = parameters;
	}
	
	public static BiomeTarget ofSpecial() {
		return new BiomeTarget(true);
	}
	
	public static BiomeTarget ofGroupToggle(String group, ResourceKey<Biome> canReplace) {
		BiomeTarget target = new BiomeTarget();
		target.group = Optional.of(group);
		target.canReplace = Optional.of(List.of(canReplace));
		return target;
	}
	
	public static BiomeTarget ofGroupToggle(String group, ResourceKey<Biome> canReplace, Map<ClimateParameter, DoubleRange> parameters) {
		BiomeTarget target = ofGroupToggle(group, canReplace);
		target.parameters = Optional.of(parameters);
		return target;
	}
	
	public static BiomeTarget ofToggle(List<ResourceKey<Biome>> canReplace, Map<ClimateParameter, DoubleRange> parameters) {
		BiomeTarget target = new BiomeTarget();
		target.canReplace = Optional.of(canReplace);
		target.parameters = Optional.of(parameters);
		return target;
	}
	
	@SafeVarargs
	public static BiomeTarget ofWeighted(int weight, ResourceKey<Biome>... canReplace) {
		BiomeTarget target = new BiomeTarget();
		target.weight = Optional.of(weight);
		target.baseWeight = weight;
		target.canReplace = Optional.of(List.of(canReplace));
		return target;
	}
	
	public static BiomeTarget ofWeighted(int weight, ResourceKey<Biome> canReplace, Map<ClimateParameter, DoubleRange> parameters) {
		BiomeTarget target = ofWeighted(weight, canReplace);
		target.parameters = Optional.of(parameters);
		return target;
	}
	
	public static BiomeTarget nether(int weight, ResourceKey<Biome> canReplace) {
		BiomeTarget target = ofWeighted(weight, canReplace);
		target.dimension = Level.NETHER;
		return target;
	}
	
	public static HolderSet<Biome> getTargets(Registry<Biome> registry, List<ResourceKey<Biome>> targets) {
		return HolderSet.direct(targets.stream().map(registry::getHolder).filter(Optional::isPresent).map(Optional::get).toList());
	}
	
	public static BiomeInjector createSpecialInjector(RegistryAccess registries, Holder<Biome> biome, BiomeTarget target) {
		BiomeInjector.InjectorBuilder builder = BiomeInjector.builder(target.dimension);
		if (biome.is(RUBiomes.PRISMACHASM)) {
			return builder.addPoints(new Climate.ParameterList<>(List.of(new Pair<>(
				new Climate.ParameterPoint(
					Climate.Parameter.span(-1, 1),
					Climate.Parameter.span(-1, -0.8f),
					Climate.Parameter.span(-1.2f, 1),
					Climate.Parameter.span(0f, 1f),
					Climate.Parameter.span(0.2f, 0.9f),
					Climate.Parameter.span(-1, 1),
					0
				),
				biome
			))));
		}
		if (biome.is(RUBiomes.REDSTONE_CAVES)) {
			return builder.addPoints(new Climate.ParameterList<>(List.of(new Pair<>(
				new Climate.ParameterPoint(
					Climate.Parameter.span(-1, 1),
					Climate.Parameter.span(-1, -0.8f),
					Climate.Parameter.span(-1.2f, 1),
					Climate.Parameter.span(-1f, 0f),
					Climate.Parameter.span(0.2f, 0.9f),
					Climate.Parameter.span(-1, 1),
					0
				),
				biome
			))));
		}
		if (biome.is(RUBiomes.INFERNO)) {
			Registry<DensityFunction> dfs = registries.registryOrThrow(Registries.DENSITY_FUNCTION);
			return builder.forcePlacement(
				biome,
				ParameterBuilder.create().climateMin(DEPTH, 0.2).densityFunctionMin(dfs.getHolderOrThrow(RUDensityFunctions.INFERNO_WEIGHT), 0.001)
			);
		}
		if (biome.is(RUBiomes.CHALK_CLIFFS)) {
			return builder.forcePlacement(
				biome,
				ParameterBuilder.create().climateRange(CONTINENTALNESS, -0.19, -0.11).climateMax(EROSION, -0.6).climateRange(TEMPERATURE, -0.1, 0.2)
			);
		}
		
		return null;
	}
	
	public ParameterBuilder getParameters() {
		ParameterBuilder builder = ParameterBuilder.create();
		if (this.parameters.isEmpty()) return builder;
		for (var entry : this.parameters.get().entrySet()) {
			DoubleRange range = entry.getValue();
			builder.climateRange(entry.getKey(), range.min(), range.max());
		}
		return builder;
	}
	
	public void setCanGenerate(boolean canGenerate) {
		if (this.specialEnabled.isPresent()) {
			this.specialEnabled = Optional.of(canGenerate);
		}
		if (this.enabled.isPresent()) {
			this.enabled = Optional.of(canGenerate);
		}
	}
	
	public boolean canGenerate() {
		return this.specialEnabled.orElseGet(
			() -> this.enabled.orElse(false)
		);
	}
	
	public record DoubleRange(double min, double max) {
		public static final Codec<DoubleRange> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.DOUBLE.lenientOptionalFieldOf("min", -Double.MAX_VALUE).forGetter(DoubleRange::min),
			Codec.DOUBLE.lenientOptionalFieldOf("max", +Double.MAX_VALUE).forGetter(DoubleRange::max)
		).apply(i, DoubleRange::new));
		
		public static DoubleRange below(double value) {
			return new DoubleRange(-Double.MAX_VALUE, value);
		}
		
		public static DoubleRange between(double min, double max) {
			return new DoubleRange(min, max);
		}
		
		public static DoubleRange above(double value) {
			return new DoubleRange(value, Double.MAX_VALUE);
		}
	}
}
