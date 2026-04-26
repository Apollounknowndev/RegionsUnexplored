package net.regions_unexplored.config;

import dev.worldgen.lithostitched.api.event.AddRegionsEvent.RegionConsumer;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RURegions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

// TODO: Make this fully config-driven
@SuppressWarnings("unused")
public interface BiomeTargets {
	List<BiomeTarget> ALL = new ArrayList<>();
	
	// Islands
	BiomeTarget ALPHA_GROVE = BiomeTarget.create(Level.OVERWORLD, RUBiomes.ALPHA_GROVE, Biomes.MUSHROOM_FIELDS);
	BiomeTarget ASHEN_WOODLAND = BiomeTarget.create(Level.OVERWORLD, RUBiomes.ASHEN_WOODLAND, Biomes.MUSHROOM_FIELDS);
	BiomeTarget TROPICS = BiomeTarget.create(Level.OVERWORLD, RUBiomes.TROPICS, Biomes.MUSHROOM_FIELDS);
	// Oceans
	BiomeTarget HYACINTH_DEEPS = BiomeTarget.create(Level.OVERWORLD, RUBiomes.HYACINTH_DEEPS, Biomes.DEEP_FROZEN_OCEAN);
	BiomeTarget ROCKY_REEF = BiomeTarget.create(Level.OVERWORLD, RUBiomes.ROCKY_REEF, Biomes.WARM_OCEAN);
	// Beaches
	BiomeTarget GRASSY_BEACH = BiomeTarget.create(Level.OVERWORLD, RUBiomes.GRASSY_BEACH, Biomes.BEACH);
	BiomeTarget GRAVEL_BEACH = BiomeTarget.create(Level.OVERWORLD, RUBiomes.GRAVEL_BEACH, Biomes.BEACH);
	// Cliffs
	/// Chalk Cliffs in additional
	// Rivers
	BiomeTarget COLD_RIVER = BiomeTarget.create(Level.OVERWORLD, RURegions.RIVERS, RUBiomes.COLD_RIVER, b -> b
		.climateMax(ClimateParameter.TEMPERATURE, -0.1)
	, Biomes.RIVER);
	BiomeTarget MUDDY_RIVER = BiomeTarget.create(Level.OVERWORLD, RURegions.RIVERS, RUBiomes.MUDDY_RIVER, b -> b
		.climateRange(ClimateParameter.TEMPERATURE, -0.1, 0.55)
	, Biomes.RIVER);
	BiomeTarget TROPICAL_RIVER = BiomeTarget.create(Level.OVERWORLD, RURegions.RIVERS, RUBiomes.TROPICAL_RIVER, b -> b
		.climateMin(ClimateParameter.TEMPERATURE, 0.55)
	, Biomes.RIVER);
	// Swamps
	BiomeTarget FEN = BiomeTarget.create(Level.OVERWORLD, RURegions.SWAMPS, RUBiomes.FEN, b -> b
		.climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.1)
		.climateMin(ClimateParameter.HUMIDITY, -0.35)
	, Biomes.SWAMP);
	BiomeTarget FUNGAL_FEN = BiomeTarget.create(Level.OVERWORLD, RURegions.SWAMPS, RUBiomes.FUNGAL_FEN, b -> b
		.climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.1)
		.climateMax(ClimateParameter.HUMIDITY, -0.35)
	, Biomes.SWAMP);
	BiomeTarget BAYOU = BiomeTarget.create(Level.OVERWORLD, RURegions.SWAMPS, RUBiomes.BAYOU, b -> b
		.climateRange(ClimateParameter.TEMPERATURE, -0.1, 0.2)
		.climateMax(ClimateParameter.HUMIDITY, -0.3)
	, Biomes.SWAMP);
	BiomeTarget OLD_GROWTH_BAYOU = BiomeTarget.create(Level.OVERWORLD, RURegions.SWAMPS, RUBiomes.OLD_GROWTH_BAYOU, b -> b
		.climateRange(ClimateParameter.TEMPERATURE, -0.1, 0.2)
		.climateMin(ClimateParameter.HUMIDITY, 0.3)
	, Biomes.SWAMP);
	BiomeTarget MARSH = BiomeTarget.create(Level.OVERWORLD, RUBiomes.MARSH, Biomes.MANGROVE_SWAMP);
	
	static void applyAdditionalRegions(Registry<Biome> registry, RegionConsumer consumer) {
		var river = registry.getHolder(Biomes.RIVER);
		if (river.isPresent() && RuCommonConfig.RIVER_WEIGHT.get() >= 0) {
			consumer.accept(
				RURegions.RIVERS,
				Level.OVERWORLD,
				HolderSet.direct(river.get()),
				RuCommonConfig.RIVER_WEIGHT.get()
			);
		}
		
		var swamp = registry.getHolder(Biomes.SWAMP);
		if (swamp.isPresent() && RuCommonConfig.SWAMP_WEIGHT.get() >= 0) {
			consumer.accept(
				RURegions.SWAMPS,
				Level.OVERWORLD,
				HolderSet.direct(swamp.get()),
				RuCommonConfig.SWAMP_WEIGHT.get()
			);
		}
	}
	
	static void applyAdditionalInjectors(Registry<Biome> registry, BiConsumer<Identifier, BiomeInjector> consumer) {
		var chalkCliffs = registry.getHolder(RUBiomes.CHALK_CLIFFS);
		if (chalkCliffs.isPresent() && RuCommonConfig.TOGGLE_CHALK_CLIFFS.get()) {
			consumer.accept(
				RUBiomes.CHALK_CLIFFS.identifier(),
				BiomeInjector.builder(Level.OVERWORLD).replaceFully(
					chalkCliffs.get(),
					ParameterBuilder.create()
						.climateRange(ClimateParameter.CONTINENTALNESS, -0.19, -0.11)
						.climateMax(ClimateParameter.EROSION, -0.6)
						.climateRange(ClimateParameter.TEMPERATURE, -0.1, 0.2)
				)
			);
		}
	}
}
