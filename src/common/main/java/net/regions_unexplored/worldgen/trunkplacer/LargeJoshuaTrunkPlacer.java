package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class LargeJoshuaTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<LargeJoshuaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, LargeJoshuaTrunkPlacer::new));
    public static final TrunkPlacerType<LargeJoshuaTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public LargeJoshuaTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight + 1))) {
            return List.of();
        }
        List<FoliageAttachment> attachments = new ArrayList<>();
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        attachments.add(attachment(origin.above(treeHeight)));

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos branchOrigin = origin.above(treeHeight - 6 + random.nextInt(2));
            BlockPos first = branchOrigin.relative(direction);
            BlockPos second = branchOrigin.relative(direction, 2).above();
            BlockPos third = branchOrigin.relative(direction, 3).above(2);
            BlockPos top = third.above();
            if (!this.validTreePos(level, first) || !this.validTreePos(level, second) || !this.validTreePos(level, third) || !this.validTreePos(level, top)) {
                continue;
            }
            this.placeLog(level, trunkSetter, random, first, config, setAxis(direction));
            this.placeLog(level, trunkSetter, random, second, config);
            this.placeLog(level, trunkSetter, random, third, config);
            this.placeLog(level, trunkSetter, random, top, config);
            attachments.add(attachment(top.above()));
        }

        for (Direction direction : List.of(Direction.NORTH, Direction.SOUTH)) {
            BlockPos branchOrigin = origin.above(treeHeight - 7 + random.nextInt(3));
            for (Direction side : List.of(Direction.WEST, Direction.EAST)) {
                BlockPos first = branchOrigin.relative(direction).relative(side);
                BlockPos second = branchOrigin.relative(direction, 2).relative(side, 2).above();
                BlockPos top = second.above();
                if (!this.validTreePos(level, first) || !this.validTreePos(level, second) || !this.validTreePos(level, top)) {
                    continue;
                }
                this.placeLog(level, trunkSetter, random, first, config);
                this.placeLog(level, trunkSetter, random, second, config);
                this.placeLog(level, trunkSetter, random, top, config);
                attachments.add(attachment(top.above()));
            }
        }
        return attachments;
    }
}
