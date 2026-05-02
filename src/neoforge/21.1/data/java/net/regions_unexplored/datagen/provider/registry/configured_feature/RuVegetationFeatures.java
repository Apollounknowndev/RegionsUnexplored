package net.regions_unexplored.datagen.provider.registry.configured_feature;

import dev.worldgen.lithostitched.api.worldgen.stateprovider.LithostitchedStateProviders;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUFeatureTypes;
import net.regions_unexplored.registry.data.RUConfiguredFeatures;
import net.regions_unexplored.worldgen.stateprovider.RandomizedGroundCoverStateProvider;
import net.regions_unexplored.world.level.block.plant.food.DuskmelonBlock;
import net.regions_unexplored.world.level.block.plant.food.SalmonBerryBushBlock;
import net.regions_unexplored.world.level.block.plant.grass.AshenGrassBlock;

import java.util.List;

import static net.regions_unexplored.datagen.provider.registry.util.RUFeatureUtils.*;
import static net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements.*;
import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;

public class RuVegetationFeatures {
    //GRASS
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FERN = patch("fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SHORT_GRASS = patch("short_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_GRASS = patch("tall_grass");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SANDY_GRASS = patch("sandy_grass");
    //FLOWERS
    public static final ResourceKey<ConfiguredFeature<?, ?>> SINGLE_TASSEL = key("single/tassel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BARLEY = patch("barley");
    //BIOSHROOM
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUE_BIOSHROOM = patch("blue_bioshroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_GREEN_BIOSHROOM = patch("green_bioshroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PINK_BIOSHROOM = patch("pink_bioshroom");
    //OTHER
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ELEPHANT_EAR = patch("elephant_ear");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        SimpleWeightedRandomList.Builder<BlockState> duskMelon = SimpleWeightedRandomList.builder();
        duskMelon.add(RUBlocks.DUSKMELON.get().defaultBlockState().setValue(DuskmelonBlock.AGE, 1), 3).add(RUBlocks.DUSKMELON.get().defaultBlockState().setValue(DuskmelonBlock.AGE, 2), 2);

        registerPlaced(context, SINGLE_BLACKWOOD_BIOSHROOMS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
            new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(RUBlocks.BLUE_BIOSHROOM.get().defaultBlockState(), 3)
                .add(RUBlocks.PINK_BIOSHROOM.get().defaultBlockState(), 3)
                .add(RUBlocks.TALL_BLUE_BIOSHROOM.get().defaultBlockState(), 1)
                .add(RUBlocks.TALL_PINK_BIOSHROOM.get().defaultBlockState(), 1))
        ));
        //---------------------FEATURES---------------------//
        //SIMPLE_RANDOM_SELECTOR
        registerPlaced(context, PATCH_TALL_FLOWERS, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, simple(RUBlocks.TASSEL.get()))),
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.DAY_LILY.get())))),
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(RUBlocks.MEADOW_SAGE.get())))),
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILAC)))),
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH)))),
            PlacementUtils.inlinePlaced(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.PEONY)))),
            PlacementUtils.inlinePlaced(Feature.NO_BONEMEAL_FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_OF_THE_VALLEY))))
        )));
        //SIMPLE_BLOCK
        registerPlaced(context, SINGLE_ASTER, Feature.SIMPLE_BLOCK, simple(RUBlocks.ASTER.get()));
        registerPlaced(context, SINGLE_CORPSE_FLOWER, Feature.SIMPLE_BLOCK, simple(RUBlocks.CORPSE_FLOWER.get()));
        registerPlaced(context, SINGLE_DAY_LILY, Feature.SIMPLE_BLOCK, simple(RUBlocks.DAY_LILY.get()));
        registerPlaced(context, SINGLE_DUSKTRAP, Feature.SIMPLE_BLOCK, simple(RUBlocks.DUSKTRAP.get()));
        registerPlaced(context, SINGLE_MEADOW_SAGE, Feature.SIMPLE_BLOCK, simple(RUBlocks.MEADOW_SAGE.get()));
        registerPlaced(context, SINGLE_BARREL_CACTUS, Feature.SIMPLE_BLOCK, simple(RUBlocks.BARREL_CACTUS.get()));
        register(context, SINGLE_TASSEL, Feature.SIMPLE_BLOCK, simple(RUBlocks.TASSEL.get()));
        registerPlaced(context, SINGLE_SNOWBELLE, Feature.SIMPLE_BLOCK, simple(RUBlocks.SNOWBELLES.getWhite().get()));
        //RANDOM_PATCH
        registerPlaced(context, PATCH_ASHEN_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.ASHEN_GRASS.get()), 32, BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), RUBlocks.ASHEN_DIRT.get())));
        registerPlaced(context, PATCH_ASHEN_GRASS_SMOULDERING, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.ASHEN_GRASS.get().defaultBlockState().setValue(AshenGrassBlock.SMOULDERING, true)), 64, BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), RUBlocks.ASH.get(), Blocks.BASALT, Blocks.POLISHED_BASALT)));
        registerPlaced(context, PATCH_ASH_VENTS, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 6, 0, Holder.direct(new PlacedFeature(
            Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(
                    inlinePlaced(new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(LithostitchedStateProviders.randomBlock(Blocks.BASALT, Blocks.SMOOTH_BASALT)))),
                    0.9f
                )),
                inlinePlaced(new ConfiguredFeature<>(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                    List.of(
                        new BlockColumnConfiguration.Layer(UniformInt.of(0, 4), BlockStateProvider.simple(Blocks.BASALT)),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(RUBlocks.ASH_VENT.get()))
                    ),
                    Direction.UP,
                    BlockPredicate.allOf(
                        BlockPredicate.matchesTag(BlockTags.AIR),
                        BlockPredicate.not(BlockPredicate.anyOf(
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.north(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.east(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.south(), RUBlocks.ASH_VENT.get()),
                            BlockPredicate.matchesBlocks(Vec3i.ZERO.west(), RUBlocks.ASH_VENT.get())
                        ))
                    ),
                    true
                )))
            ))),
            List.of(
                RUFeatureUtils.airAndBlocksBelow(RUBlocks.ASH.get()),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1))
            )
        ))));
        registerPlaced(context, PATCH_FLOWERS_TUNDRA, Feature.RANDOM_PATCH, randomPatch(new NoiseProvider(
            923586L,
            new NormalNoise.NoiseParameters(-7, 2, 1.3),
            1.5f,
            List.of(
               Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
               RUBlocks.BLEEDING_HEART.get().defaultBlockState(),
               Blocks.POPPY.defaultBlockState()
            )
        ), 48));
        registerPlaced(context, PATCH_FLOWERS_WISTERIA_GROVE, Feature.RANDOM_PATCH, randomPatch(new NoiseProvider(
            530167L,
            new NormalNoise.NoiseParameters(-3, 1),
            0.1f,
            List.of(
                RUBlocks.HYSSOP.get().defaultBlockState(),
                RUBlocks.FIREWEED.get().defaultBlockState(),
                RUBlocks.DAISY.get().defaultBlockState()
            )
        ), 8));
        
        register(context, PATCH_SHORT_GRASS, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(Blocks.SHORT_GRASS), pair(RUBlocks.GRASS_SPROUTS.get())), 64));
        register(context, PATCH_SANDY_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.SANDY_GRASS.get()), 64));
        registerPlaced(context, PATCH_STEPPE_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.STEPPE_GRASS.get()), 64, BlockPredicate.matchesTag(Vec3i.ZERO.below(), BlockTags.DIRT)));
        registerPlaced(context, PATCH_DESERT_SHRUB_ON_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.SMALL_DESERT_SHRUB.get()), 64, BlockPredicate.matchesTag(Vec3i.ZERO.below(), BlockTags.DIRT)));
        registerPlaced(context, PATCH_DESERT_SHRUB_ON_SAND, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.SMALL_DESERT_SHRUB.get()), 64, BlockPredicate.matchesTag(Vec3i.ZERO.below(), BlockTags.SAND)));
        registerPlaced(context, PATCH_STEPPE_SHRUB_ON_SAND, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.DEAD_STEPPE_SHRUB.get()), 64, BlockPredicate.matchesTag(Vec3i.ZERO.below(), BlockTags.SAND)));

        register(context, PATCH_BARLEY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, simple(RUBlocks.BARLEY.get())));
        registerPlaced(context, PATCH_BLADED_GRASS, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(RUBlocks.BLADED_GRASS.get(), 4), pair(RUBlocks.BLADED_TALL_GRASS.get()), pair(Blocks.SHORT_GRASS, 4)), 64));
        registerPlaced(context, PATCH_CAVE_HYSSOP, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.CAVE_HYSSOP.get().defaultBlockState()), 32));
        registerPlaced(context, PATCH_CLOVER, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, RandomizedGroundCoverStateProvider.asConfig(RUBlocks.CLOVER))));
        register(context, PATCH_FERN, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(Blocks.FERN), 32));
        registerPlaced(context, PATCH_PRISMOSS_SPROUT, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.PRISMOSS_SPROUT.get()), 32));
        registerPlaced(context, PATCH_REDSTONE_BUD, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.REDSTONE_BUD.get()), 128));
        registerPlaced(context, PATCH_REDSTONE_BULB, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.REDSTONE_BULB.get()), 64));
        registerPlaced(context, PATCH_FERN_REDWOODS, Feature.RANDOM_PATCH, randomPatch(weightedStates(
            pair(Blocks.FERN, 5),
            pair(Blocks.LARGE_FERN, 2),
            pair(Blocks.SHORT_GRASS, 2),
            pair(RUBlocks.GRASS_SPROUTS.get())
        ), 128));
        registerPlaced(context, PATCH_GRASSES_STEPPE, Feature.FLOWER, randomPatch(new DualNoiseProvider(
            new InclusiveRange<>(2, 3),
            new NormalNoise.NoiseParameters(-6, 2.5),
            0.7f,
            9987,
            new NormalNoise.NoiseParameters(-2, 1),
            1,
            List.of(
                state(RUBlocks.STEPPE_GRASS),
                state(Blocks.SHORT_GRASS),
                state(RUBlocks.GRASS_SPROUTS),
                state(RUBlocks.DEAD_STEPPE_SHRUB),
                state(RUBlocks.SMALL_DESERT_SHRUB)
            )
        ), 32));
        randomPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
            .add(RUBlocks.STEPPE_GRASS.get().defaultBlockState(), 10).add(RUBlocks.STEPPE_SHRUB.get().defaultBlockState(), 10)
            .add(RUBlocks.SMALL_DESERT_SHRUB.get().defaultBlockState(), 1).add(RUBlocks.STEPPE_TALL_GRASS.get().defaultBlockState(), 1)
            .add(RUBlocks.DEAD_STEPPE_SHRUB.get().defaultBlockState(), 10)), 32);
        registerPlaced(context, PATCH_FROZEN_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.FROZEN_GRASS.get().defaultBlockState()), 32));
        registerPlaced(context, PATCH_GRASS_SPROUTS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.GRASS_SPROUTS.get().defaultBlockState()), 32));
        register(context, PATCH_TALL_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(Blocks.TALL_GRASS.defaultBlockState()), 32));
        registerPlaced(context, PATCH_WINDSWEPT_GRASS, Feature.RANDOM_PATCH, randomPatch(BlockStateProvider.simple(RUBlocks.WINDSWEPT_GRASS.get().defaultBlockState()), 32));
        //FLOWER
        registerPlaced(context, PATCH_SNOWY_FLOWERS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.BLEEDING_HEART.get().defaultBlockState(), 3).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 2)))));
        registerPlaced(context, PATCH_BAMBOO_FLOWERS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.FIREWEED.get().defaultBlockState(), 3).add(RUBlocks.TSUBAKI.get().defaultBlockState(), 2).add(RUBlocks.PINK_LUPINE.get().defaultBlockState(), 3).add(Blocks.PINK_TULIP.defaultBlockState(), 3)))));
        registerPlaced(context, PATCH_ALPHA_DANDELION, Feature.FLOWER, randomPatch(RUBlocks.ALPHA_DANDELION, 96, 6, 2));
        registerPlaced(context, PATCH_ALPHA_ROSE, Feature.FLOWER, randomPatch(RUBlocks.ALPHA_ROSE, 96, 6, 2));
        registerPlaced(context, PATCH_AZURE_DAISY, Feature.FLOWER, new RandomPatchConfiguration(32, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(RUBlocks.FELICIA_DAISY.get().defaultBlockState(), 2))))));
        registerPlaced(context, PATCH_DAISY, Feature.FLOWER, randomPatch(RUBlocks.DAISY, 8, 1, 2));
        registerPlaced(context, PATCH_ELEPHANT_EAR_UNRECOVERABLY_DENSE, Feature.SIMPLE_BLOCK, simple(RUBlocks.ELEPHANT_EAR.get()));
        register(context, PATCH_ELEPHANT_EAR, Feature.FLOWER, randomPatch(RUBlocks.ELEPHANT_EAR, 8, 6, 2));
        registerPlaced(context, PATCH_WARATAH, Feature.FLOWER, randomPatch(RUBlocks.WARATAH, 8, 1, 2));
        registerPlaced(context, PATCH_DAISIES, Feature.FLOWER, randomPatch(new DualNoiseProvider(
            new InclusiveRange<>(2),
            new NormalNoise.NoiseParameters(-7, 2.5),
            0.6f,
            9987,
            new NormalNoise.NoiseParameters(-4, 1),
            1,
            List.of(
                state(RUBlocks.DAISY),
                state(Blocks.SHORT_GRASS),
                state(RUBlocks.FELICIA_DAISY)
            )
        ), 32));
        registerPlaced(context, PATCH_LUPINES, Feature.FLOWER, randomPatch(new DualNoiseProvider(
            new InclusiveRange<>(2),
            new NormalNoise.NoiseParameters(-7, 1),
            0.75f,
            9989,
            new NormalNoise.NoiseParameters(-4, 1),
            1,
            List.of(
                state(RUBlocks.YELLOW_LUPINE),
                state(RUBlocks.RED_LUPINE),
                state(RUBlocks.BLUE_LUPINE),
                state(RUBlocks.PURPLE_LUPINE),
                state(RUBlocks.PINK_LUPINE)
            )
        ), 64));

        registerPlaced(context, PATCH_POPPIES, Feature.FLOWER, randomPatch(
            new NoiseProvider(498625, new NormalNoise.NoiseParameters(-6, 1.3D), 1F, List.of(
                RUBlocks.POPPY_BUSH.get().defaultBlockState(),
                Blocks.POPPY.defaultBlockState(),
                RUBlocks.SALMON_POPPY.get().defaultBlockState(),
                RUBlocks.SALMON_POPPY_BUSH.get().defaultBlockState()
            )),
            64
        ));
        registerPlaced(context, PATCH_PRAIRIE_FLOWERS, Feature.FLOWER, new RandomPatchConfiguration(32, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.POPPY_BUSH.get().defaultBlockState(), 3).add(RUBlocks.RED_LUPINE.get().defaultBlockState(), 2).add(RUBlocks.YELLOW_LUPINE.get().defaultBlockState(), 1))))));
        registerPlaced(context, PATCH_ORANGE_CONEFLOWER, Feature.FLOWER, new RandomPatchConfiguration(36, 4, 2,
            PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, RandomizedGroundCoverStateProvider.asConfig(RUBlocks.ORANGE_CONEFLOWER))
        ));
        registerPlaced(context, PATCH_PURPLE_CONEFLOWER, Feature.FLOWER, new RandomPatchConfiguration(36, 4, 2,
            PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, RandomizedGroundCoverStateProvider.asConfig(RUBlocks.PURPLE_CONEFLOWER))
        ));
        registerPlaced(context, PATCH_SHRUBLAND_FLOWERS, Feature.FLOWER, new RandomPatchConfiguration(32, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.RED_LUPINE.get().defaultBlockState(), 1).add(RUBlocks.BLUE_LUPINE.get().defaultBlockState(), 1))))));
        registerPlaced(context, PATCH_WILLOW_FLOWERS, Feature.FLOWER, new RandomPatchConfiguration(32, 4, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RUBlocks.FELICIA_DAISY.get().defaultBlockState(), 2).add(RUBlocks.BLUE_LUPINE.get().defaultBlockState(), 2).add(Blocks.ALLIUM.defaultBlockState(), 1).add(Blocks.CORNFLOWER.defaultBlockState(), 2))))));
        registerPlaced(context, PATCH_SMALL_FLOWERS, Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseProvider(2345L, new NormalNoise.NoiseParameters(0, 1.0D), 0.075F, List.of(RUBlocks.MALLOW.get().defaultBlockState(), RUBlocks.YELLOW_LUPINE.get().defaultBlockState(), Blocks.PINK_TULIP.defaultBlockState(), RUBlocks.POPPY_BUSH.get().defaultBlockState(), Blocks.OXEYE_DAISY.defaultBlockState(), RUBlocks.RED_LUPINE.get().defaultBlockState(), Blocks.CORNFLOWER.defaultBlockState(), RUBlocks.PINK_LUPINE.get().defaultBlockState(), RUBlocks.TSUBAKI.get().defaultBlockState(), Blocks.ORANGE_TULIP.defaultBlockState(), RUBlocks.WARATAH.get().defaultBlockState(), Blocks.WHITE_TULIP.defaultBlockState(), RUBlocks.HYSSOP.get().defaultBlockState(), Blocks.ALLIUM.defaultBlockState(), RUBlocks.BLUE_LUPINE.get().defaultBlockState(), RUBlocks.BLEEDING_HEART.get().defaultBlockState(), RUBlocks.SALMON_POPPY_BUSH.get().defaultBlockState(), RUBlocks.WHITE_TRILLIUM.get().defaultBlockState(), Blocks.BLUE_ORCHID.defaultBlockState(), RUBlocks.FIREWEED.get().defaultBlockState(), RUBlocks.DAISY.get().defaultBlockState(), RUBlocks.PURPLE_LUPINE.get().defaultBlockState(), Blocks.RED_TULIP.defaultBlockState(), RUBlocks.FELICIA_DAISY.get().defaultBlockState(), Blocks.POPPY.defaultBlockState(), Blocks.LILY_OF_THE_VALLEY.defaultBlockState()))))));
        registerPlaced(context, PATCH_TULIPS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.WHITE_TULIP.defaultBlockState(), 4).add(Blocks.PINK_TULIP.defaultBlockState(), 2).add(Blocks.ORANGE_TULIP.defaultBlockState(), 2).add(Blocks.RED_TULIP.defaultBlockState(), 2)))));
        registerPlaced(context, PATCH_WHITE_TRILLIUM, Feature.FLOWER, randomPatch(RUBlocks.WHITE_TRILLIUM, 32, 4, 2));
        registerPlaced(context, PATCH_WILTING_TRILLIUM, Feature.FLOWER, randomPatch(RUBlocks.WILTING_TRILLIUM, 32, 4, 2));
        registerPlaced(context, PATCH_TSUBAKI, Feature.FLOWER, randomPatch(RUBlocks.TSUBAKI, 32, 1, 2));
        registerPlaced(context, PATCH_HIBISCUS, Feature.FLOWER, randomPatch(RUBlocks.HIBISCUS, 14, 1, 2));
        registerPlaced(context, PATCH_MALLOW, Feature.FLOWER, randomPatch(RUBlocks.MALLOW, 16, 6, 2));
        registerPlaced(context, PATCH_HYSSOP, Feature.FLOWER, randomPatch(RUBlocks.HYSSOP, 16, 1, 2));
        
        var tassel = registerPlaced(context, PATCH_TASSEL, Feature.FLOWER, randomPatch(RUBlocks.TASSEL, 96, 7, 3));
        var lilac = registerPlaced(context, PATCH_LILAC, Feature.FLOWER, randomPatch(() -> Blocks.LILAC, 96, 7, 3));
        var peony = registerPlaced(context, PATCH_PEONY, Feature.FLOWER, randomPatch(() -> Blocks.PEONY, 96, 7, 3));
        registerSelector(context, PATCH_TALL_FLOWERS_WISTERIA_GROVE, b -> b
            .add(direct(tassel))
            .add(direct(lilac))
            .add(direct(peony))
        );
        
        //FOOD_PLANTS
        registerPlaced(context, PATCH_SALMONBERRY_BUSH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
            Feature.SIMPLE_BLOCK, simple(RUBlocks.SALMONBERRY_BUSH.get().defaultBlockState().setValue(SalmonBerryBushBlock.AGE, 3)),
            List.of(Blocks.GRASS_BLOCK, Blocks.PODZOL, RUBlocks.PEAT_GRASS_BLOCK.get(), RUBlocks.SILT_GRASS_BLOCK.get(), RUBlocks.PEAT_PODZOL.get(), RUBlocks.SILT_PODZOL.get())));
        registerPlaced(context, SINGLE_DUSKMELON, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(duskMelon)));
        //BIOSHROOM
        var blueBioshroomPatch = register(context, PATCH_BLUE_BIOSHROOM, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(RUBlocks.TALL_BLUE_BIOSHROOM, 1), pair(RUBlocks.BLUE_BIOSHROOM, 10)), 16));
        var greenBioshroomPatch = register(context, PATCH_GREEN_BIOSHROOM, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(RUBlocks.TALL_GREEN_BIOSHROOM, 1), pair(RUBlocks.GREEN_BIOSHROOM, 10)), 16));
        var pinkBioshroomPatch = register(context, PATCH_PINK_BIOSHROOM, Feature.RANDOM_PATCH, randomPatch(weightedStates(pair(RUBlocks.TALL_PINK_BIOSHROOM, 1), pair(RUBlocks.PINK_BIOSHROOM, 10)), 16));
        
        registerSelector(context, PATCH_CAVE_BIOSHROOMS, builder -> builder
            .add(direct(blueBioshroomPatch), 7)
            .add(direct(greenBioshroomPatch), 7)
            .add(direct(pinkBioshroomPatch), 1)
        );
        //OTHER
        registerPlaced(context, PATCH_FLOWERING_LILY_PAD, Feature.RANDOM_PATCH, randomPatch(RUBlocks.FLOWERING_LILY_PAD, 10, 7, 3));
        registerPlaced(context, SPECIAL_GIANT_LILY, RUFeatureTypes.GIANT_LILY.get(), FeatureConfiguration.NONE);
        registerPlaced(context, PATCH_DROPLEAF, Feature.RANDOM_PATCH, new RandomPatchConfiguration(16, 4, 2,
            PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 19), 2).add(UniformInt.of(0, 2), 3).add(UniformInt.of(0, 6), 10).build()), BlockStateProvider.simple(RUBlocks.DROPLEAF_PLANT.get())),
                    BlockColumnConfiguration.layer(ConstantInt.of(1), new RandomizedIntStateProvider(BlockStateProvider.simple(RUBlocks.DROPLEAF.get()), "age", UniformInt.of(22, 24)))
                ), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true
            ),
            BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.hasSturdyFace(Vec3i.ZERO.above(), Direction.DOWN), BlockPredicate.matchesTag(BlockTags.AIR))))));
        registerPlaced(context, PATCH_DUCKWEED, Feature.RANDOM_PATCH, randomPatch(RUBlocks.DUCKWEED, 24, 4, 0));
    }
    
    private static SimpleBlockConfiguration simple(Block block) {
        return new SimpleBlockConfiguration(BlockStateProvider.simple(block));
    }
    
    private static SimpleBlockConfiguration simple(BlockState state) {
        return new SimpleBlockConfiguration(BlockStateProvider.simple(state));
    }
}
