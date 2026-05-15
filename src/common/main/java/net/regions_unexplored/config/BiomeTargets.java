package net.regions_unexplored.config;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.event.AddRegionsEvent.RegionConsumer;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.regions_unexplored.internal.config.ConfigValue;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RUDensityFunctions;
import net.regions_unexplored.registry.data.RURegions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*;

// TODO: Make this fully config-driven
@SuppressWarnings("unused")
public interface BiomeTargets {
	List<BiomeTarget> ALL = new ArrayList<>();
	
	// Islands
	BiomeTarget ALPHA_GROVE = BiomeTarget.create(RUBiomes.ALPHA_GROVE, Biomes.MUSHROOM_FIELDS);
	BiomeTarget ASHEN_WOODLAND = BiomeTarget.create(RUBiomes.ASHEN_WOODLAND, Biomes.MUSHROOM_FIELDS);
	BiomeTarget TROPICS = BiomeTarget.create(RUBiomes.TROPICS, Biomes.MUSHROOM_FIELDS);
	
	
	
	// Oceans
	BiomeTarget HYACINTH_DEEPS = BiomeTarget.create(RUBiomes.HYACINTH_DEEPS, Biomes.DEEP_FROZEN_OCEAN);
	BiomeTarget ROCKY_REEF = BiomeTarget.create(RUBiomes.ROCKY_REEF, Biomes.WARM_OCEAN);
	
	
	
	// Beaches
	BiomeTarget GRASSY_BEACH = BiomeTarget.create(RUBiomes.GRASSY_BEACH, Biomes.BEACH);
	BiomeTarget GRAVEL_BEACH = BiomeTarget.create(RUBiomes.GRAVEL_BEACH, Biomes.BEACH);
	
	// Cliffs
	// - Chalk Cliffs are in additional
	
	
	
	// Rivers
	BiomeTarget COLD_RIVER = BiomeTarget.create(RURegions.RIVERS, RUBiomes.COLD_RIVER, Biomes.RIVER, b -> b
		.climateMax(TEMPERATURE, -0.1)
	);
	BiomeTarget MUDDY_RIVER = BiomeTarget.create(RURegions.RIVERS, RUBiomes.MUDDY_RIVER, Biomes.RIVER, b -> b
		.climateRange(TEMPERATURE, -0.1, 0.55)
	);
	BiomeTarget TROPICAL_RIVER = BiomeTarget.create(RURegions.RIVERS, RUBiomes.TROPICAL_RIVER, Biomes.RIVER, b -> b
		.climateMin(TEMPERATURE, 0.55)
		.climateMin(HUMIDITY, 0.3)
		.climateMin(EROSION, 0.05)
	);
	
	
	
	// Swamps
	BiomeTarget FEN = BiomeTarget.create(RURegions.SWAMPS, RUBiomes.FEN, Biomes.SWAMP, b -> b
		.climateRange(TEMPERATURE, -0.45, -0.1)
		.climateMin(HUMIDITY, -0.35)
	);
	BiomeTarget FUNGAL_FEN = BiomeTarget.create(RURegions.SWAMPS, RUBiomes.FUNGAL_FEN, Biomes.SWAMP, b -> b
		.climateRange(TEMPERATURE, -0.45, -0.1)
		.climateMax(HUMIDITY, -0.35)
	);
	BiomeTarget BAYOU = BiomeTarget.create(RURegions.SWAMPS, RUBiomes.BAYOU, Biomes.SWAMP, b -> b
		.climateRange(TEMPERATURE, -0.1, 0.2)
		.climateMax(HUMIDITY, -0.3)
	);
	BiomeTarget OLD_GROWTH_BAYOU = BiomeTarget.create(RURegions.SWAMPS, RUBiomes.OLD_GROWTH_BAYOU, Biomes.SWAMP, b -> b
		.climateRange(TEMPERATURE, -0.1, 0.2)
		.climateMin(HUMIDITY, 0.3)
	);
	BiomeTarget MARSH = BiomeTarget.create(RUBiomes.MARSH, Biomes.MANGROVE_SWAMP);
	
	
	
	// Plains / Meadows
	BiomeTarget TUNDRA = BiomeTarget.create(RUBiomes.TUNDRA, Biomes.SNOWY_PLAINS);
	BiomeTarget ORCHARD = BiomeTarget.create(RUBiomes.ORCHARD, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST);
	BiomeTarget FLOWER_FIELDS = BiomeTarget.create(RUBiomes.FLOWER_FIELDS, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST);
	BiomeTarget POPPY_FIELDS = BiomeTarget.create(RUBiomes.POPPY_FIELDS, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST);
	BiomeTarget SHRUBLAND = BiomeTarget.create(RURegions.PLAINS, RUBiomes.SHRUBLAND, Biomes.PLAINS, b -> b
		.climateMax(TEMPERATURE, -0.1)
	);
	BiomeTarget CLOVER_PLAINS = BiomeTarget.create(RURegions.PLAINS, RUBiomes.CLOVER_PLAINS, Biomes.PLAINS, b -> b
		.climateRange(TEMPERATURE, -0.1, 0.2)
	);
	BiomeTarget GRASSLAND = BiomeTarget.create(RURegions.PLAINS, RUBiomes.GRASSLAND, Biomes.PLAINS, b -> b
		.climateMin(TEMPERATURE, 0.2)
	);
	BiomeTarget HIGHLAND_FIELDS = BiomeTarget.create(RUBiomes.HIGHLAND_FIELDS, Biomes.MEADOW);
	BiomeTarget WISTERIA_GROVE = BiomeTarget.create(RUBiomes.WISTERIA_GROVE, Biomes.MEADOW);
	BiomeTarget MAGNOLIA_WOODLAND = BiomeTarget.create(RUBiomes.MAGNOLIA_WOODLAND, Biomes.CHERRY_GROVE);
	
	
	
	// Forests
	BiomeTarget WILLOW_FOREST = BiomeTarget.create(RURegions.FORESTS, RUBiomes.WILLOW_FOREST, Biomes.FOREST, b -> b
		.climateMax(TEMPERATURE, -0.1)
	);
	BiomeTarget MAPLE_FOREST = BiomeTarget.create(RURegions.FORESTS, RUBiomes.MAPLE_FOREST, Biomes.FOREST, b -> b
		.climateRange(TEMPERATURE, -0.1, 0.2)
	);
	BiomeTarget DECIDUOUS_FOREST = BiomeTarget.create(RURegions.FORESTS, RUBiomes.DECIDUOUS_FOREST, Biomes.FOREST, b -> b
		.climateMin(TEMPERATURE, 0.2)
	);
	BiomeTarget AUTUMNAL_MAPLE_FOREST = BiomeTarget.create(RUBiomes.AUTUMNAL_MAPLE_FOREST, Biomes.BIRCH_FOREST);
	BiomeTarget SILVER_BIRCH_FOREST = BiomeTarget.create(RUBiomes.SILVER_BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
	BiomeTarget BLACKWOOD_TAIGA = BiomeTarget.create(RUBiomes.BLACKWOOD_TAIGA, Biomes.DARK_FOREST);
	
	
	
	// Taigas
	BiomeTarget PINE_TAIGA = BiomeTarget.create(RURegions.PRIMARY_TAIGAS, RUBiomes.PINE_TAIGA, Biomes.TAIGA, b -> b);
	BiomeTarget FROZEN_PINE_TAIGA = BiomeTarget.create(RURegions.PRIMARY_TAIGAS, RUBiomes.FROZEN_PINE_TAIGA, Biomes.SNOWY_TAIGA, b -> b);
	BiomeTarget REDWOODS = BiomeTarget.create(RURegions.PRIMARY_TAIGAS, RUBiomes.REDWOODS, Biomes.OLD_GROWTH_SPRUCE_TAIGA, b -> b);
	BiomeTarget SPARSE_REDWOODS = BiomeTarget.create(RURegions.PRIMARY_TAIGAS, RUBiomes.SPARSE_REDWOODS, Biomes.OLD_GROWTH_PINE_TAIGA, b -> b);
	
	BiomeTarget BOREAL_TAIGA = BiomeTarget.create(RURegions.SECONDARY_TAIGAS, RUBiomes.BOREAL_TAIGA, Biomes.TAIGA, b -> b);
	BiomeTarget COLD_BOREAL_TAIGA = BiomeTarget.create(RURegions.SECONDARY_TAIGAS, RUBiomes.COLD_BOREAL_TAIGA, Biomes.SNOWY_TAIGA, b -> b);
	BiomeTarget OLD_GROWTH_BOREAL_TAIGA = BiomeTarget.create(RURegions.SECONDARY_TAIGAS, RUBiomes.OLD_GROWTH_BOREAL_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, b -> b);
	BiomeTarget OLD_GROWTH_GOLDEN_BOREAL_TAIGA = BiomeTarget.create(RURegions.SECONDARY_TAIGAS, RUBiomes.OLD_GROWTH_GOLDEN_BOREAL_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, b -> b);
	
	BiomeTarget PINE_SLOPES = BiomeTarget.create(RURegions.PINE_SLOPES, RUBiomes.PINE_SLOPES, Biomes.GROVE, b -> b.climateMin(TEMPERATURE, -0.45));
	
	
	
	// Savannas
	BiomeTarget DRY_BUSHLAND = BiomeTarget.create(RUBiomes.DRY_BUSHLAND, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
	BiomeTarget PRAIRIE = BiomeTarget.create(RUBiomes.PRAIRIE, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
	
	
	
	// Deserts
	BiomeTarget JOSHUA_DESERT = BiomeTarget.create(RUBiomes.JOSHUA_DESERT, Biomes.DESERT);
	BiomeTarget SAGUARO_DESERT = BiomeTarget.create(RUBiomes.SAGUARO_DESERT, Biomes.DESERT);
	
	
	
	// Jungles
	BiomeTarget RAINFOREST = BiomeTarget.create(RURegions.RAINFORESTS, RUBiomes.RAINFOREST, Biomes.JUNGLE, b -> b);
	BiomeTarget SPARSE_RAINFOREST = BiomeTarget.create(RURegions.RAINFORESTS, RUBiomes.SPARSE_RAINFOREST, Biomes.SPARSE_JUNGLE, b -> b);
	BiomeTarget BAMBOO_FOREST = BiomeTarget.create(RUBiomes.BAMBOO_FOREST, Biomes.BAMBOO_JUNGLE);
	
	
	
	// Misc
	BiomeTarget WINDSWEPT_MAPLE_FOREST = BiomeTarget.create(RUBiomes.WINDSWEPT_MAPLE_FOREST, Biomes.WINDSWEPT_FOREST);
	BiomeTarget TOWERING_CLIFFS = BiomeTarget.create(RUBiomes.TOWERING_CLIFFS, Biomes.WINDSWEPT_SAVANNA);
	BiomeTarget EUCALYPTUS_FOREST = BiomeTarget.create(RUBiomes.EUCALYPTUS_FOREST, Biomes.WOODED_BADLANDS);
	
	
	
	// Nether
	BiomeTarget MYCOTOXIC_UNDERGROWTH = BiomeTarget.create(RUBiomes.MYCOTOXIC_UNDERGROWTH, Biomes.NETHER_WASTES);
	BiomeTarget GLISTERING_MEADOW = BiomeTarget.create(RUBiomes.GLISTERING_MEADOW, Biomes.SOUL_SAND_VALLEY);
	BiomeTarget BLACKSTONE_BASIN = BiomeTarget.create(RUBiomes.BLACKSTONE_BASIN, Biomes.CRIMSON_FOREST);
	BiomeTarget INFERNAL_HOLT = BiomeTarget.create(RUBiomes.INFERNAL_HOLT, Biomes.BASALT_DELTAS);
	
	static void applyAdditionalRegions(Registry<Biome> registry, RegionConsumer consumer) {
		applyRegion(consumer, RURegions.RIVERS, registry.getHolder(Biomes.RIVER), RuCommonConfig.RIVER_WEIGHT);
		applyRegion(consumer, RURegions.SWAMPS, registry.getHolder(Biomes.SWAMP), RuCommonConfig.SWAMP_WEIGHT);
		applyRegion(consumer, RURegions.PLAINS, registry.getHolder(Biomes.PLAINS), RuCommonConfig.PLAINS_WEIGHT);
		applyRegion(consumer, RURegions.FORESTS, registry.getHolder(Biomes.FOREST), RuCommonConfig.FOREST_WEIGHT);
		applyRegion(consumer, RURegions.PINE_SLOPES, registry.getHolder(Biomes.GROVE), RuCommonConfig.PINE_SLOPES_WEIGHT);
		
		var taigas = HolderSet.direct(registry::getHolderOrThrow, Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.SNOWY_TAIGA);
		applyRegion(consumer, RURegions.PRIMARY_TAIGAS, taigas, RuCommonConfig.PRIMARY_TAIGA_WEIGHT);
		applyRegion(consumer, RURegions.SECONDARY_TAIGAS, taigas, RuCommonConfig.SECONDARY_TAIGA_WEIGHT);
		
		var jungles = HolderSet.direct(registry::getHolderOrThrow, Biomes.JUNGLE, Biomes.SPARSE_JUNGLE);
		applyRegion(consumer, RURegions.RAINFORESTS, jungles, RuCommonConfig.RAINFOREST_WEIGHT);
	}
	
	private static void applyRegion(RegionConsumer consumer, ResourceKey<Region> key, Optional<Holder.Reference<Biome>> biome, ConfigValue<Integer> weight) {
		if (biome.isPresent() && weight.get() > 0) {
			consumer.accept(
				key,
				Level.OVERWORLD,
				HolderSet.direct(biome.get()),
				weight.get()
			);
		}
	}
	
	private static void applyRegion(RegionConsumer consumer, ResourceKey<Region> key, HolderSet<Biome> biomes, ConfigValue<Integer> weight) {
		if (biomes.size() > 0 && weight.get() > 0) {
			consumer.accept(
				key,
				Level.OVERWORLD,
				biomes,
				weight.get()
			);
		}
	}
	
	static void applyAdditionalInjectors(RegistryAccess registries, BiConsumer<Identifier, BiomeInjector> consumer) {
		Registry<Biome> biomes = registries.registryOrThrow(Registries.BIOME);
		Registry<DensityFunction> dfs = registries.registryOrThrow(Registries.DENSITY_FUNCTION);
		
		addSpecialBiome(biomes, RUBiomes.ANCIENT_DELTA, consumer, (builder, biome) -> builder.replacePartially(
			biomes.getHolderOrThrow(Biomes.DRIPSTONE_CAVES),
			biome,
			ParameterBuilder.create().climateMax(HUMIDITY, -0.4)
		));
		
		addSpecialBiome(biomes, RUBiomes.BIOSHROOM_CAVES, consumer, (builder, biome) -> builder.replacePartially(
			biomes.getHolderOrThrow(Biomes.LUSH_CAVES),
			biome,
			ParameterBuilder.create().climateMin(EROSION, 0.3)
		));
		
		addSpecialBiome(biomes, RUBiomes.PRISMACHASM, consumer, (builder, biome) -> builder.addPoints(
			new Climate.ParameterList<>(List.of(new Pair<>(
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
			)))
		));
		
		addSpecialBiome(biomes, RUBiomes.REDSTONE_CAVES, consumer, (builder, biome) -> builder.addPoints(
			new Climate.ParameterList<>(List.of(new Pair<>(
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
			)))
		));
		
		addSpecialBiome(biomes, RUBiomes.INFERNO, consumer, (builder, biome) -> builder.forcePlacement(
			biome,
			ParameterBuilder.create().climateMin(DEPTH, 0.2).densityFunctionMin(dfs.getHolderOrThrow(RUDensityFunctions.INFERNO_WEIGHT), 0.001)
		));
		
		addSpecialBiome(biomes, RUBiomes.CHALK_CLIFFS, consumer, (builder, biome) -> builder.forcePlacement(
			biome,
			ParameterBuilder.create().climateRange(CONTINENTALNESS, -0.19, -0.11).climateMax(EROSION, -0.6).climateRange(TEMPERATURE, -0.1, 0.2)
		));
		
		addSpecialBiome(biomes, RUBiomes.OUTBACK, consumer, (builder, biome) -> builder.replacePartially(
			BiomeTarget.getTargets(biomes, List.of(Biomes.DESERT)),
			biome,
			ParameterBuilder.create().climateMax(EROSION, 0.15)
		));
		
		addSpecialBiome(biomes, RUBiomes.SPIRES, consumer, (builder, biome) -> builder.replacePartially(
			BiomeTarget.getTargets(biomes, List.of(Biomes.FROZEN_RIVER, Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA)),
			biome,
			ParameterBuilder.create().climateMax(TEMPERATURE, -0.45).climateMin(EROSION, 0.6)
		));
		
		addSpecialBiome(biomes, RUBiomes.ICY_HEIGHTS, consumer, (builder, biome) -> builder.replacePartially(
			BiomeTarget.getTargets(biomes, List.of(Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA)),
			biome,
			ParameterBuilder.create().climateRange(EROSION, 0.45, 0.55).climateMax(CONTINENTALNESS, 0.03).climateMin(WEIRDNESS, 0)
		));
		
		addSpecialBiome(biomes, RUBiomes.BAOBAB_SAVANNA, consumer, (builder, biome) -> builder.replacePartially(
			BiomeTarget.getTargets(biomes, List.of(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU)),
			biome,
			ParameterBuilder.create().climateMax(HUMIDITY, -0.35)
		));
	}
	
	static void addSpecialBiome(
		Registry<Biome> registry,
		ResourceKey<Biome> key,
		BiConsumer<Identifier, BiomeInjector> consumer,
		BiFunction<BiomeInjector.InjectorBuilder, Holder<Biome>, BiomeInjector> creator
	) {
		var biome = registry.getHolderOrThrow(key);
		if (RuCommonConfig.BIOME_TOGGLES.get(key).get()) {
			consumer.accept(
				key.identifier(),
				creator.apply(BiomeInjector.builder(Level.OVERWORLD), biome)
			);
		}
	}
}
