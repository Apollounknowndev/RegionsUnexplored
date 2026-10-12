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

import java.util.List;
import java.util.function.BiConsumer;

public class DeadTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<DeadTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, DeadTrunkPlacer::new));
    public static final TrunkPlacerType<DeadTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public DeadTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight))) {
            return List.of();
        }
        convertGrassBelow(level, trunkSetter, origin);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        if (random.nextInt(5) != 0) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos stub = origin.above(treeHeight - 4 - random.nextInt(2)).relative(direction);
            this.placeLog(level, trunkSetter, random, stub, config, setAxis(direction));
        }
        return List.of(attachment(origin.above(treeHeight)));
    }
}
