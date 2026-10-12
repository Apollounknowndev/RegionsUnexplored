package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;
import net.regions_unexplored.block.type.leaves.JoshuaLeavesBlock;

public class JoshuaFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<JoshuaFoliagePlacer> CODEC = MapCodec.unit(JoshuaFoliagePlacer::new);
    public static final FoliagePlacerType<JoshuaFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public JoshuaFoliagePlacer() {
        super(ConstantInt.ZERO, ConstantInt.ZERO, 0);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        BlockPos lower = foliageAttachment.pos().above(offset);
        BlockPos upper = lower.above();
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(upper)) {
            return;
        }
        if (!level.isStateAtPosition(lower, BlockState::canBeReplaced) || !level.isStateAtPosition(upper, BlockState::canBeReplaced)) {
            return;
        }
        for (DoubleBlockHalf half : new DoubleBlockHalf[]{DoubleBlockHalf.LOWER, DoubleBlockHalf.UPPER}) {
            BlockPos pos = half == DoubleBlockHalf.LOWER ? lower : upper;
            BlockState state = config.foliageProvider.getState(random, pos)
                .setValue(JoshuaLeavesBlock.NATURAL, true)
                .setValue(JoshuaLeavesBlock.HALF, half)
                .setValue(JoshuaLeavesBlock.WATERLOGGED, level.isFluidAtPosition(pos, fluid -> fluid.isSourceOfType(Fluids.WATER)));
            foliageSetter.set(pos, state);
        }
    }
}
