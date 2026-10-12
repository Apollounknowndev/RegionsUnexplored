package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SmallSocotraTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<SmallSocotraTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, SmallSocotraTrunkPlacer::new));
    public static final TrunkPlacerType<SmallSocotraTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public SmallSocotraTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        List<FoliageAttachment> attachments = new ArrayList<>();
        convertGrassBelow(level, trunkSetter, origin);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        BlockPos branchOrigin = origin.above(treeHeight - 2);
        for (Direction direction : List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
            BlockPos branch = branchOrigin.relative(direction);
            if (this.placeLog(level, trunkSetter, random, branch, config, setAxis(direction))) {
                attachments.add(attachment(branch.above()));
            }
        }
        attachments.add(attachment(origin.above(treeHeight)));
        return attachments;
    }
}
