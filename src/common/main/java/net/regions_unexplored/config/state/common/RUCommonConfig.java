package net.regions_unexplored.config.state.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.config.json5.CommentedMapCodec;
import net.regions_unexplored.config.json5.CommentedUnboundedMapCodec;
import net.regions_unexplored.config.state.common.BiomeTarget.DoubleRange;
import net.regions_unexplored.registry.data.RUBiomes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*;
import static java.util.Map.entry;
import static net.regions_unexplored.config.json5.CommentedMapCodec.commented;
import static net.regions_unexplored.config.json5.CommentedMapCodec.optionalCommented;
import static net.regions_unexplored.config.state.common.BiomeTarget.*;

public class RUCommonConfig {
	public static final int CURRENT_VERSION = 1;
	public static final RUCommonConfig DEFAULT = new RUCommonConfig(CURRENT_VERSION, BiomeGroups.DEFAULT, BiomePlacements.DEFAULT, Misc.DEFAULT, VanillaChanges.DEFAULT);
	public static final Codec<RUCommonConfig> CODEC = RecordCodecBuilder.create(i -> i.group(
		commented(Codec.INT, "config_version", "Don't touch this!").orElse(CURRENT_VERSION).forGetter(c -> c.configVersion),
		optionalCommented(BiomeGroups.CODEC, BiomeGroups.DEFAULT, "biome_groups", "Biome groups allows several biomes of similar styles to more consistently spawn adjacent to one another.").forGetter(c -> c.biomeGroups),
		BiomePlacements.CODEC.fieldOf("biome_placements").orElse(BiomePlacements.DEFAULT).forGetter(c -> c.biomePlacements),
		Misc.CODEC.fieldOf("misc").orElse(Misc.DEFAULT).forGetter(c -> c.misc),
		VanillaChanges.CODEC.fieldOf("vanilla_changes").orElse(VanillaChanges.DEFAULT).forGetter(c -> c.vanillaChanges)
	).apply(i, RUCommonConfig::new));
	
	public int configVersion;
	public BiomeGroups biomeGroups;
	public BiomePlacements biomePlacements;
	public Misc misc;
	public VanillaChanges vanillaChanges;
	
	public RUCommonConfig(int configVersion, BiomeGroups biomeGroups, BiomePlacements biomePlacements, Misc misc, VanillaChanges vanillaChanges) {
		this.configVersion = configVersion;
		this.biomeGroups = biomeGroups;
		this.biomePlacements = biomePlacements;
		this.misc = misc;
		this.vanillaChanges = vanillaChanges;
	}
	
	public Misc.BranchMode getBranchMode() {
		return this.misc.branchMode;
	}
	
	public boolean test(String key) {
		if (key.startsWith("vanilla_changes/")) {
			return this.vanillaChanges.toggles.getOrDefault(key.substring(16), false);
		}
		return switch (key) {
			case "custom_dirts" -> this.misc.customDirts;
			case "painted_planks" -> this.misc.paintedPlanks;
			default -> {
				RegionsUnexplored.LOGGER.error("Unknown key in config predicate:  {}", key);
				yield false;
			}
		};
	}
	
	public static class BiomeGroups {
		private static final BiomeGroups DEFAULT = new BiomeGroups(Map.of(
			"rivers", ofWeighted(60, Biomes.RIVER),
			"swamps", ofWeighted(100, Biomes.SWAMP),
			"plains", ofWeighted(100, Biomes.PLAINS),
			"forests", ofWeighted(50, Biomes.FOREST),
			"boreal_taigas", ofWeighted(75, Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.SNOWY_TAIGA),
			"pine_and_redwood_taigas", ofWeighted(75, Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.SNOWY_TAIGA),
			"jungles", ofWeighted(50, Biomes.JUNGLE, Biomes.SPARSE_JUNGLE)
		));
		public static final Codec<BiomeGroups> CODEC = Codec.unboundedMap(Codec.STRING.validate(
			string -> Identifier.isValidPath(string) ? DataResult.success(string) : DataResult.error(() -> "Invalid character(s) in group name: " + string)
		), BiomeTarget.CODEC).xmap(BiomeGroups::new, g -> g.groups);
	
		public Map<String, BiomeTarget> groups;
		
		public BiomeGroups(Map<String, BiomeTarget> groups) {
			this.groups = new HashMap<>(groups);
		}
	}
	
	public static class BiomePlacements {
		private static final BiomePlacements DEFAULT = new BiomePlacements(Map.<ResourceKey<Biome>, BiomeTarget>ofEntries(
			entry(RUBiomes.ALPHA_GROVE, ofWeighted(20, Biomes.MUSHROOM_FIELDS)),
			entry(RUBiomes.ASHEN_WOODLAND, ofWeighted(20, Biomes.MUSHROOM_FIELDS)),
			entry(RUBiomes.TROPICS, ofWeighted(20, Biomes.MUSHROOM_FIELDS)),
			entry(RUBiomes.HYACINTH_DEEPS, ofWeighted(50, Biomes.DEEP_FROZEN_OCEAN)),
			entry(RUBiomes.ROCKY_REEF, ofWeighted(50, Biomes.WARM_OCEAN)),
			entry(RUBiomes.GRASSY_BEACH, ofWeighted(50, Biomes.BEACH)),
			entry(RUBiomes.GRAVEL_BEACH, ofWeighted(50, Biomes.BEACH)),
			entry(RUBiomes.COLD_RIVER, BiomeTarget.ofGroupToggle("rivers", Biomes.RIVER, Map.of(
				TEMPERATURE, DoubleRange.below(-0.1)
			))),
			entry(RUBiomes.MUDDY_RIVER, BiomeTarget.ofGroupToggle("rivers", Biomes.RIVER, Map.of(
				TEMPERATURE, DoubleRange.between(-0.1, 0.55)
			))),
			entry(RUBiomes.TROPICAL_RIVER, BiomeTarget.ofGroupToggle("rivers", Biomes.RIVER, Map.of(
				TEMPERATURE, DoubleRange.above(0.55),
				HUMIDITY, DoubleRange.above(0.3),
				EROSION, DoubleRange.above(0.15)
			))),
			entry(RUBiomes.FEN, BiomeTarget.ofGroupToggle("swamps", Biomes.SWAMP, Map.of(
				TEMPERATURE, DoubleRange.between(-0.45, -0.1),
				HUMIDITY, DoubleRange.above(-0.35)
			))),
			entry(RUBiomes.FUNGAL_FEN, BiomeTarget.ofGroupToggle("swamps", Biomes.SWAMP, Map.of(
				TEMPERATURE, DoubleRange.between(-0.45, -0.1),
				HUMIDITY, DoubleRange.below(-0.35)
			))),
			entry(RUBiomes.BAYOU, BiomeTarget.ofGroupToggle("swamps", Biomes.SWAMP, Map.of(
				TEMPERATURE, DoubleRange.between(-0.1, 0.2),
				HUMIDITY, DoubleRange.below(3)
			))),
			entry(RUBiomes.OLD_GROWTH_BAYOU, BiomeTarget.ofGroupToggle("swamps", Biomes.SWAMP, Map.of(
				TEMPERATURE, DoubleRange.between(-0.1, 0.2),
				HUMIDITY, DoubleRange.above(3)
			))),
			entry(RUBiomes.MARSH, ofWeighted(50, Biomes.SWAMP)),
			entry(RUBiomes.TUNDRA, ofWeighted(100, Biomes.SNOWY_PLAINS, Map.of(
				HUMIDITY, DoubleRange.below(-0.1)
			))),
			entry(RUBiomes.ORCHARD, ofWeighted(50, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST)),
			entry(RUBiomes.FLOWER_FIELDS, ofWeighted(30, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST)),
			entry(RUBiomes.POPPY_FIELDS, ofWeighted(70, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST)),
			entry(RUBiomes.SHRUBLAND, BiomeTarget.ofGroupToggle("plains", Biomes.PLAINS, Map.of(
				TEMPERATURE, DoubleRange.below(-0.1)
			))),
			entry(RUBiomes.CLOVER_PLAINS, BiomeTarget.ofGroupToggle("plains", Biomes.PLAINS, Map.of(
				TEMPERATURE, DoubleRange.between(-0.1, 0.2)
			))),
			entry(RUBiomes.GRASSLAND, BiomeTarget.ofGroupToggle("plains", Biomes.PLAINS, Map.of(
				TEMPERATURE, DoubleRange.above(0.2)
			))),
			entry(RUBiomes.HIGHLAND_FIELDS, ofWeighted(50, Biomes.MEADOW)),
			entry(RUBiomes.WISTERIA_GROVE, ofWeighted(50, Biomes.MEADOW)),
			entry(RUBiomes.MAGNOLIA_WOODLAND, ofWeighted(50, Biomes.CHERRY_GROVE)),
			entry(RUBiomes.WILLOW_FOREST, BiomeTarget.ofGroupToggle("forests", Biomes.FOREST, Map.of(
				TEMPERATURE, DoubleRange.below(-0.1)
			))),
			entry(RUBiomes.MAPLE_FOREST, BiomeTarget.ofGroupToggle("forests", Biomes.FOREST, Map.of(
				TEMPERATURE, DoubleRange.between(-0.1, 0.2)
			))),
			entry(RUBiomes.OLD_GROWTH_FOREST, BiomeTarget.ofGroupToggle("forests", Biomes.FOREST, Map.of(
				TEMPERATURE, DoubleRange.above(0.2)
			))),
			entry(RUBiomes.AUTUMNAL_MAPLE_FOREST, ofWeighted(50, Biomes.BIRCH_FOREST)),
			entry(RUBiomes.SILVER_BIRCH_FOREST, ofWeighted(50, Biomes.OLD_GROWTH_BIRCH_FOREST)),
			entry(RUBiomes.BLACKWOOD_TAIGA, ofWeighted(50, Biomes.DARK_FOREST)),
			entry(RUBiomes.PINE_TAIGA, ofGroupToggle("pine_and_redwood_taigas", Biomes.TAIGA)),
			entry(RUBiomes.FROZEN_PINE_TAIGA, ofGroupToggle("pine_and_redwood_taigas", Biomes.SNOWY_TAIGA)),
			entry(RUBiomes.REDWOODS, ofGroupToggle("pine_and_redwood_taigas", Biomes.OLD_GROWTH_SPRUCE_TAIGA)),
			entry(RUBiomes.SPARSE_REDWOODS, ofGroupToggle("pine_and_redwood_taigas", Biomes.OLD_GROWTH_PINE_TAIGA)),
			entry(RUBiomes.BOREAL_TAIGA, ofGroupToggle("boreal_taigas", Biomes.TAIGA)),
			entry(RUBiomes.COLD_BOREAL_TAIGA, ofGroupToggle("boreal_taigas", Biomes.SNOWY_TAIGA)),
			entry(RUBiomes.OLD_GROWTH_BOREAL_TAIGA, ofGroupToggle("boreal_taigas", Biomes.OLD_GROWTH_SPRUCE_TAIGA)),
			entry(RUBiomes.OLD_GROWTH_GOLDEN_BOREAL_TAIGA, ofGroupToggle("boreal_taigas", Biomes.OLD_GROWTH_PINE_TAIGA)),
			entry(RUBiomes.PINE_SLOPES, ofWeighted(50, Biomes.GROVE, Map.of(
				TEMPERATURE, DoubleRange.above(-0.45)
			))),
			entry(RUBiomes.DRY_BUSHLAND, ofWeighted(100, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU)),
			entry(RUBiomes.PRAIRIE, ofWeighted(100, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU)),
			entry(RUBiomes.JOSHUA_DESERT, ofWeighted(50, Biomes.DESERT)),
			entry(RUBiomes.SAGUARO_DESERT, ofWeighted(50, Biomes.DESERT)),
			entry(RUBiomes.RAINFOREST, ofGroupToggle("jungles", Biomes.JUNGLE)),
			entry(RUBiomes.SPARSE_RAINFOREST, ofGroupToggle("jungles", Biomes.SPARSE_JUNGLE)),
			entry(RUBiomes.BAMBOO_FOREST, ofWeighted(100, Biomes.BAMBOO_JUNGLE)),
			entry(RUBiomes.WINDSWEPT_MAPLE_FOREST, ofWeighted(100, Biomes.WINDSWEPT_FOREST)),
			entry(RUBiomes.TOWERING_CLIFFS, ofWeighted(100, Biomes.WINDSWEPT_SAVANNA)),
			entry(RUBiomes.EUCALYPTUS_FOREST, ofWeighted(50, Biomes.WOODED_BADLANDS)),
			entry(RUBiomes.MYCOTOXIC_UNDERGROWTH, nether(60, Biomes.NETHER_WASTES)),
			entry(RUBiomes.GLISTERING_MEADOW, nether(60, Biomes.SOUL_SAND_VALLEY)),
			entry(RUBiomes.BLACKSTONE_BASIN, nether(60, Biomes.CRIMSON_FOREST)),
			entry(RUBiomes.INFERNAL_HOLT, nether(60, Biomes.BASALT_DELTAS)),
			entry(RUBiomes.ANCIENT_DELTA, ofToggle(List.of(Biomes.DRIPSTONE_CAVES), Map.of(
				HUMIDITY, DoubleRange.below(-0.4)
			))),
			entry(RUBiomes.BIOSHROOM_CAVES, ofToggle(List.of(Biomes.LUSH_CAVES), Map.of(
				EROSION, DoubleRange.above(0.3)
			))),
			entry(RUBiomes.PRISMACHASM, ofSpecial()),
			entry(RUBiomes.REDSTONE_CAVES, ofSpecial()),
			entry(RUBiomes.INFERNO, ofSpecial()),
			entry(RUBiomes.CHALK_CLIFFS, ofSpecial()),
			entry(RUBiomes.OUTBACK, ofToggle(List.of(Biomes.DESERT), Map.of(
				EROSION, DoubleRange.below(0.15)
			))),
			entry(RUBiomes.SPIRES, ofToggle(List.of(Biomes.FROZEN_RIVER, Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA), Map.of(
				EROSION, DoubleRange.above(0.6),
				TEMPERATURE, DoubleRange.below(-0.45)
			))),
			entry(RUBiomes.ICY_HEIGHTS, ofToggle(List.of(Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA), Map.of(
				EROSION, DoubleRange.between(0.45, 0.55),
				CONTINENTALNESS, DoubleRange.below(0.03),
				WEIRDNESS, DoubleRange.above(0)
			))),
			entry(RUBiomes.BAOBAB_SAVANNA, ofToggle(List.of(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU), Map.of(
				HUMIDITY, DoubleRange.below(-0.35)
			)))
		));
		public static final Codec<BiomePlacements> CODEC = Codec.unboundedMap(ResourceKey.codec(Registries.BIOME), BiomeTarget.CODEC).xmap(BiomePlacements::create, g -> g.placements);
		
		public Map<ResourceKey<Biome>, BiomeTarget> placements;
		
		public BiomePlacements(Map<ResourceKey<Biome>, BiomeTarget> placements) {
			this.placements = new HashMap<>(placements);
		}
		
		public static BiomePlacements create(Map<ResourceKey<Biome>, BiomeTarget> basePlacements) {
			BiomePlacements placements = new BiomePlacements(basePlacements);
			for (var entry : DEFAULT.placements.entrySet()) {
				if (!placements.placements.containsKey(entry.getKey())) {
					placements.placements.put(entry.getKey(), entry.getValue());
				}
			}
			return placements;
		}
	}
	
	public static class Misc {
		public static final Misc DEFAULT = new Misc(BranchMode.PLACE_BRANCHES, true, false, false);
		public static final Codec<Misc> CODEC = RecordCodecBuilder.create(i -> i.group(
			CommentedMapCodec.optionalCommented(BranchMode.CODEC, BranchMode.PLACE_BRANCHES, "branch_mode", "\"place_branches\" = place RU's dedicated branch blocks, \"place_logs\" = place log blocks, \"dont_place\" = don't place any branches").orElse(BranchMode.PLACE_BRANCHES).forGetter(m -> m.branchMode),
			CommentedMapCodec.optionalCommented(Codec.BOOL, true, "custom_dirts", "Controls the Peat and Silt dirt block family generation").orElse(true).forGetter(m -> m.customDirts),
			CommentedMapCodec.optionalCommented(Codec.BOOL, false, "small_oak_trees", "Oak trees with thin fence-like log blocks will generate in some forests").orElse(false).forGetter(m -> m.smallOakTrees),
			CommentedMapCodec.optionalCommented(Codec.BOOL, false, "painted_planks", "Re-enables the recipes of the legacy Painted Plank blocks").orElse(false).forGetter(m -> m.paintedPlanks)
		).apply(i, Misc::new));
		
		public BranchMode branchMode;
		public boolean customDirts;
		public boolean smallOakTrees;
		public boolean paintedPlanks;
		
		public Misc(BranchMode branchMode, boolean customDirts, boolean smallOakTrees, boolean paintedPlanks) {
			this.branchMode = branchMode;
			this.customDirts = customDirts;
			this.smallOakTrees = smallOakTrees;
			this.paintedPlanks = paintedPlanks;
		}
		
		
		public enum BranchMode implements StringRepresentable {
			PLACE_BRANCHES("place_branches"),
			PLACE_LOGS("place_logs"),
			DONT_PLACE("dont_place");
			
			private static final Codec<BranchMode> CODEC = StringRepresentable.fromValues(BranchMode::values);
			private final String name;
			
			BranchMode(String name) {
				this.name = name;
			}
			
			public boolean cannotPlace() {
				return this == DONT_PLACE;
			}
			
			public BlockState selectBlock(Block branch, Block log) {
				return (this == PLACE_BRANCHES ? branch : log).defaultBlockState();
			}
			
			@Override
			@NotNull
			public String getSerializedName() {
				return this.name;
			}
		}
	}
	
	public static class VanillaChanges {
		public static final Map<String, String> TOGGLES = Map.ofEntries(
			entry("badlands_saguaros", "Saguaro Cactis will generate in Badlands and Woodland Badlands."),
			entry("badlands_steppe_grass", "Steppe Grass will generate in all Badlands."),
			entry("basalt_deltas_ash_vents", "Ash Vents will generate in Basalt Deltas."),
			entry("beach_palm_trees", "Palm trees will generate in Beaches in warm areas"),
			entry("birch_aspen_trees", "Birch trees will generate with Aspen shaping."),
			entry("birch_orange_coneflowers", "Orange Coneflower patches will generate in Birch Forests."),
			entry("common_grass_sprouts", "Grass Sprouts will generate alongside Short Grass in most biomes."),
			entry("common_shrubs", "Shrubs will generate in a variety of biomes."),
			entry("desert_sandy_grass", "Sandy Grass will generate in Deserts."),
			entry("forest_flowers", "RU's tall flowers will generate in forested biomes."),
			entry("mangrove_flowering_lilies", "Flowering Lily Pads will generate in Mangrove Swamps."),
			entry("plains_bushes", "Small bushes will generate in Plains"),
			entry("savanna_bushes", "Small bushes will generate in Savannas"),
			entry("swamp_cattails", "Cattails will generate in Swamp and Mangrove Swamps."),
			entry("swamp_willow_trees", "Swamps will generate with rooted Willow and Oak trees."),
			entry("taiga_pine_trees", "Pine trees will generate with Pine wood blocks in all Taigas."),
			entry("taiga_purple_coneflowers", "Purple Coneflower patches will generate in Taigas.")
		);
		public static final VanillaChanges DEFAULT = new VanillaChanges(TOGGLES.keySet().stream().collect(Collectors.toMap(key -> key, value -> true)));
		public static final Codec<VanillaChanges> CODEC = new CommentedUnboundedMapCodec<>(Codec.STRING, Codec.BOOL, TOGGLES).xmap(VanillaChanges::new, v -> v.toggles);
		
		public Map<String, Boolean> toggles;
		
		public VanillaChanges(Map<String, Boolean> toggles) {
			this.toggles = new HashMap<>(toggles);
			for (String key : toggles.keySet()) {
				if (!TOGGLES.containsKey(key)) {
					this.toggles.remove(key);
				}
			}
			for (var entry : TOGGLES.entrySet()) {
				if (!this.toggles.containsKey(entry.getKey())) {
					this.toggles.put(entry.getKey(), true);
				}
			}
		}
	}
}
