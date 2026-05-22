package net.regions_unexplored.datagen.provider.registry.configured_feature;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.registry.data.RUPlacedFeatures;
import net.regions_unexplored.registry.tag.RUBlockTags;

import java.util.*;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;
import static net.regions_unexplored.registry.RUBlocks.*;
import static net.regions_unexplored.registry.data.RUBiomes.*;

public class RUShrubFeatures {
    public static final Map<ResourceKey<Biome>, ShrubGroup> MAP = Map.ofEntries(
        group(ASHEN_WOODLAND, 0.25f, ASHEN_NATURAL_SET),
        group(AUTUMNAL_MAPLE_FOREST, 0.2f, entry(MAPLE_NATURAL_SET, 2), entry(RED_MAPLE_NATURAL_SET, 4), entry(ORANGE_MAPLE_NATURAL_SET, 4), entry(SILVER_BIRCH_NATURAL_SET, 4)),
        group(BAMBOO_FOREST, 0.25f, CHERRY_NATURAL_SET),
        group(BAOBAB_SAVANNA, 0.33f, entry(BAOBAB_NATURAL_SET, 2), entry(ACACIA_NATURAL_SET, 1)),
        group(BAYOU, 0.25f, entry(WILLOW_NATURAL_SET, 3), entry(CYPRESS_NATURAL_SET, 2)),
        group(BLACKWOOD_TAIGA, 0.25f, entry(BLACKWOOD_NATURAL_SET, 3), entry(DARK_OAK_NATURAL_SET, 1)),
        group(BOREAL_TAIGA, 0.2f, entry(LARCH_NATURAL_SET, 3), entry(GOLDEN_LARCH_NATURAL_SET, 1)),
        group(CHALK_CLIFFS, 0.25f, FLOWERING_NATURAL_SET),
        group(COLD_BOREAL_TAIGA, 0.25f, LARCH_NATURAL_SET),
        group(REMOVED_COLD_DECIDUOUS_FOREST, 0.25f, SPRUCE_NATURAL_SET),
        group(OLD_GROWTH_FOREST, 0.25f, OAK_NATURAL_SET),
        group(DRY_BUSHLAND, 0.25f, entry(SOCOTRA_NATURAL_SET, 3), entry(ACACIA_NATURAL_SET, 1)),
        group(EUCALYPTUS_FOREST, 0.25f, EUCALYPTUS_NATURAL_SET),
        group(FEN, 0.25f, entry(PINE_NATURAL_SET, 3), entry(DEAD_PINE_NATURAL_SET, 2)),
        group(FROZEN_PINE_TAIGA, 0.25f, PINE_NATURAL_SET),
        group(FUNGAL_FEN, 0.25f, PINE_NATURAL_SET),
        group(OLD_GROWTH_BOREAL_TAIGA, 0.33f, entry(LARCH_NATURAL_SET, 3), entry(GOLDEN_LARCH_NATURAL_SET, 1)),
        group(OLD_GROWTH_GOLDEN_BOREAL_TAIGA, 0.33f, entry(LARCH_NATURAL_SET, 1), entry(GOLDEN_LARCH_NATURAL_SET, 3)),
        group(HIGHLAND_FIELDS, 0.25f, PINE_NATURAL_SET),
        group(JOSHUA_DESERT, 0.25f, JOSHUA_NATURAL_SET),
        group(MAGNOLIA_WOODLAND, 0.25f, entry(MAGNOLIA_NATURAL_SET, 1), entry(WHITE_MAGNOLIA_NATURAL_SET, 2), entry(PINK_MAGNOLIA_NATURAL_SET, 2)),
        group(MAPLE_FOREST, 0.25f, entry(MAPLE_NATURAL_SET, 2), entry(RED_MAPLE_NATURAL_SET, 1), entry(OAK_NATURAL_SET, 1), entry(SPRUCE_NATURAL_SET, 2)),
        group(MARSH, 0.25f, DEAD_NATURAL_SET),
        group(REMOVED_MOUNTAINS, 0.25f, PINE_NATURAL_SET),
        group(OLD_GROWTH_BAYOU, 0.25f, entry(WILLOW_NATURAL_SET, 3), entry(CYPRESS_NATURAL_SET, 2)),
        group(ORCHARD, 0.2f, OAK_NATURAL_SET),
        group(OUTBACK, 0.25f, ACACIA_NATURAL_SET),
        group(PINE_SLOPES, 0.25f, PINE_NATURAL_SET),
        group(PINE_TAIGA, 0.25f, PINE_NATURAL_SET),
        group(POPPY_FIELDS, 0.25f, MAGNOLIA_NATURAL_SET),
        group(RAINFOREST, 0.25f, entry(KAPOK_NATURAL_SET, 2), entry(PALM_NATURAL_SET, 2), entry(JUNGLE_NATURAL_SET, 1)),
        group(REDWOODS, 0.25f, REDWOOD_NATURAL_SET),
        group(ROCKY_REEF, 0.25f, entry(JUNGLE_NATURAL_SET, 1), entry(PALM_NATURAL_SET, 2)),
        group(SHRUBLAND, 0.1f, SPRUCE_NATURAL_SET),
        group(SILVER_BIRCH_FOREST, 0.25f, SILVER_BIRCH_NATURAL_SET),
        group(SPARSE_RAINFOREST, 0.25f, entry(KAPOK_NATURAL_SET, 2), entry(PALM_NATURAL_SET, 2), entry(JUNGLE_NATURAL_SET, 1)),
        group(SPARSE_REDWOODS, 0.25f, REDWOOD_NATURAL_SET),
        group(SPIRES, 0.25f, SPRUCE_NATURAL_SET),
        group(TOWERING_CLIFFS, 0.25f, entry(PINE_NATURAL_SET, 1), entry(DEAD_PINE_NATURAL_SET, 2)),
        group(TROPICS, 0.25f, entry(JUNGLE_NATURAL_SET, 1), entry(PALM_NATURAL_SET, 1)),
        group(WILLOW_FOREST, 0.25f, entry(WILLOW_NATURAL_SET, 3), entry(BLUE_MAGNOLIA_NATURAL_SET, 1)),
        group(WINDSWEPT_MAPLE_FOREST, 0.1f, entry(OAK_NATURAL_SET, 3), entry(BIRCH_NATURAL_SET, 2)),
        group(WISTERIA_GROVE, 0.25f, entry(SKY_WISTERIA_NATURAL_SET, 1), entry(LAVENDER_WISTERIA_NATURAL_SET, 1), entry(SALMON_WISTERIA_NATURAL_SET, 1)),
        
        group(Biomes.MANGROVE_SWAMP, 1, MANGROVE_NATURAL_SET),
        group(Biomes.FOREST, 0.25f, entry(OAK_NATURAL_SET, 2), entry(BIRCH_NATURAL_SET, 1)),
        group(Biomes.FLOWER_FOREST, 0.33f, entry(FLOWERING_NATURAL_SET, 6), entry(OAK_NATURAL_SET, 2), entry(BIRCH_NATURAL_SET, 1)),
        group(Biomes.BIRCH_FOREST, 0.25f, BIRCH_NATURAL_SET),
        group(Biomes.DARK_FOREST, 0.33f, DARK_OAK_NATURAL_SET),
        group(Biomes.OLD_GROWTH_BIRCH_FOREST, 0.5f, BIRCH_NATURAL_SET),
        group(Biomes.OLD_GROWTH_SPRUCE_TAIGA, 0.5f, entry(SPRUCE_NATURAL_SET, 3), entry(PINE_NATURAL_SET, 1)),
        group(Biomes.OLD_GROWTH_PINE_TAIGA, 0.5f, entry(SPRUCE_NATURAL_SET, 1), entry(PINE_NATURAL_SET, 3)),
        group(Biomes.TAIGA, 0.25f, SPRUCE_NATURAL_SET),
        group(Biomes.WINDSWEPT_FOREST, 0.25f, entry(OAK_NATURAL_SET, 1), entry(SPRUCE_NATURAL_SET, 1)),
        group(Biomes.JUNGLE, 0.25f, JUNGLE_NATURAL_SET),
        group(Biomes.BAMBOO_JUNGLE, 0.25f, JUNGLE_NATURAL_SET),
        group(Biomes.CHERRY_GROVE, 0.2f, CHERRY_NATURAL_SET)
    );
    
    public static ResourceKey<PlacedFeature> get(ResourceKey<Biome> biome) {
        return MAP.get(biome).placed();
    }
    
    public static void bootstrapConfigured(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        for (ShrubGroup group : MAP.values()) {
            Map<NaturalSet, Integer> sets = group.sets();
            if (sets.size() == 1) {
                registerPlaced(context, group.placed(), Feature.SIMPLE_BLOCK, config(group.getFirstSet()));
            } else {
                registerSelector(context, group.placed(), builder -> {
                    group.sortedSets().forEach((entry) ->
                        builder.add(inlinePlaced(new ConfiguredFeature<>(
                            Feature.SIMPLE_BLOCK,
                            config(entry.getKey())
                        )), entry.getValue())
                    );
                    return builder;
                });
                
            }
        }
    }
    
    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {
        for (ShrubGroup group : MAP.values()) {
            BlockPredicate predicate = BlockPredicate.matchesTag(RUBlockTags.REPLACEABLE_BLOCKS);
            if (group.biome.equals(Biomes.MANGROVE_SWAMP)) {
                predicate = BlockPredicate.anyOf(predicate, BlockPredicate.matchesBlocks(Blocks.WATER));
            }
            register(context, group.placed(), placement(group.count(), Heightmap.Types.OCEAN_FLOOR_WG).filter(predicate).filter(group.getFirstSet().getShrub()));
        }
    }
    
    private static SimpleBlockConfiguration config(NaturalSet set) {
        return new SimpleBlockConfiguration(BlockStateProvider.simple(set.getShrub()));
    }
    
    private static Map.Entry<ResourceKey<Biome>, ShrubGroup> group(ResourceKey<Biome> biome, float count, NaturalSet set) {
        return Map.entry(biome, new ShrubGroup(biome, count, Map.of(set, 1)));
    }
    
    @SafeVarargs
    private static Map.Entry<ResourceKey<Biome>, ShrubGroup> group(ResourceKey<Biome> biome, float count, Map.Entry<NaturalSet, Integer>... entries) {
        return Map.entry(biome, new ShrubGroup(biome, count, Map.ofEntries(entries)));
    }
    
    private static Map.Entry<NaturalSet, Integer> entry(NaturalSet set, int weight) {
        return Map.entry(set, weight);
    }
    
    public record ShrubGroup(ResourceKey<Biome> biome, float count, Map<NaturalSet, Integer> sets) {
        public ResourceKey<PlacedFeature> placed() {
            Identifier id = this.biome.identifier();
            if (id.getNamespace().equals("minecraft")) {
                return RUPlacedFeatures.vanilla("shrub_group/" + id.getPath());
            }
            return RUPlacedFeatures.key("shrub/group/" + id.getPath());
        }
        
        public List<Map.Entry<NaturalSet, Integer>> sortedSets() {
            return sets.entrySet().stream().sorted(Comparator.comparingInt(entry -> entry.getKey().name.hashCode())).toList();
        }
        
        
        public NaturalSet getFirstSet() {
            return sets.entrySet().stream().min(Comparator.comparingInt(entry -> entry.getKey().name.hashCode())).get().getKey();
        }
    }
}
