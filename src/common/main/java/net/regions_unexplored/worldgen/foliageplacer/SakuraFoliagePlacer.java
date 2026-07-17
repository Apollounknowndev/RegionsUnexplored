package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;

public class SakuraFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<SakuraFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) -> foliagePlacerParts(placer).and(placer.group(
        IntProviders.codec(4, 16).fieldOf("height").forGetter(f -> f.height),
        Codec.floatRange(0.0F, 1.0F).fieldOf("flower_decoration_chance").forGetter(f -> f.flowerDecorationChance)
    )).apply(placer, SakuraFoliagePlacer::new));
    public static FoliagePlacerType<SakuraFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    protected FoliagePlacerType<?> type() {
        return TYPE;
    }

    private final IntProvider height;
    private final float flowerDecorationChance;

    public SakuraFoliagePlacer(IntProvider ip1, IntProvider ip2, IntProvider height, float flowerDecorationChance) {
        super(ip1, ip2);
        this.height = height;
        this.flowerDecorationChance = flowerDecorationChance;
    }
    
    @Override
    protected void createFoliage(
        final WorldGenLevel level,
        final FoliageSetter foliageSetter,
        final RandomSource random,
        final TreeConfiguration config,
        final int treeHeight,
        final FoliageAttachment foliageAttachment,
        final int foliageHeight,
        final int leafRadius,
        final int offset
    ) {
        BlockPos blockpos = foliageAttachment.pos().above(offset);
        if (random.nextInt(2) == 0) {
            placeLeavesBlobLeft(level, foliageSetter, random, config, blockpos);
        } else {
            placeLeavesBlobRight(level, foliageSetter, random, config, blockpos);
        }
    }

    public boolean placeLeavesBlobLeft(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        placeLeavesTopLeft(level, setter, random, treeConfiguration, pos);
        placeLeavesMiddle(level, setter, random, treeConfiguration, pos.above());
        placeLeavesTopLeft(level, setter, random, treeConfiguration, pos.above().above());
        return true;
    }

    public boolean placeLeavesBlobRight(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        placeLeavesTopRight(level, setter, random, treeConfiguration, pos);
        placeLeavesMiddle(level, setter, random, treeConfiguration, pos.above());
        placeLeavesTopRight(level, setter, random, treeConfiguration, pos.above().above());
        return true;
    }

    public void placeLeavesMiddle(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos);

        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().east().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().west());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().west().west());

        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().north());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().north().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().north().west());


        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().east().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().west());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().west().west());

        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().south());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().south().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().south().west());


        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.east().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.west());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.west().west());
    }

    public void placeLeavesTopLeft(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos);
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north().west());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south().east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.east().north());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.west());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.west().south());
    }

    public void placeLeavesTopRight(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos);
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.north());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.south());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.east());
        tryPlaceLeaf(level, setter, random, treeConfiguration, pos.west());
    }


    public int foliageHeight(final RandomSource random, final int treeHeight, final TreeConfiguration config) {
        return this.height.sample(random);
    }

    protected boolean shouldSkipLocation(
        final RandomSource random, final int dx, final int y, final int dz, final int currentRadius, final boolean doubleTrunk
    ) {
        return false;
    }

    protected static boolean tryPlaceLeaf(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos) {
        if (!TreeFeature.validTreePos(level, pos)) {
            return false;
        } else {
            BlockState blockstate = treeConfiguration.foliageProvider.getState(level, random, pos);
            if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED)) {
                blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, level.isFluidAtPosition(pos, (p_225638_) -> {
	                return p_225638_.isSourceOfType(Fluids.WATER);
                }));
            }

            setter.set(pos, blockstate);
            return true;
        }
    }
}

