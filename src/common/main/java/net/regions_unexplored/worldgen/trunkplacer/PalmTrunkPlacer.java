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

public class PalmTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, PalmTrunkPlacer::new));
    public static final TrunkPlacerType<PalmTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public PalmTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight + 2))) {
            return List.of();
        }
        boolean bend = random.nextInt(3) != 0;
        Direction direction;
        if (random.nextInt(4) == 0) {
            direction = Direction.NORTH;
        } else if (random.nextInt(4) == 0) {
            direction = Direction.SOUTH;
        } else if (random.nextInt(4) == 0) {
            direction = Direction.EAST;
        } else {
            direction = Direction.WEST;
        }
        List<BlockPos> logs = new ArrayList<>();
        BlockPos.MutableBlockPos pos = origin.mutable();
        for (int y = 0; y < treeHeight; y++) {
            if (!this.validTreePos(level, pos)) {
                return List.of();
            }
            logs.add(pos.immutable());
            pos.move(Direction.UP);
            if (bend && y > 0 && y % 3 == 0) {
                pos.move(direction);
            }
        }
        convertGrassBelow(level, trunkSetter, origin);
        for (BlockPos log : logs) {
            this.placeLog(level, trunkSetter, random, log, config);
        }
        return List.of(attachment(logs.getLast().above()));
    }
}
