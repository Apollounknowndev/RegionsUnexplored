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
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.world.level.block.plant.other.SaguaroCactusBlock;
import net.regions_unexplored.world.level.block.state.properties.SaguaroCactusShape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SaguaroTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<SaguaroTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, SaguaroTrunkPlacer::new));
    public static final TrunkPlacerType<SaguaroTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public SaguaroTrunkPlacer(IntProvider height) {
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
        BlockPos center = origin.above(3);
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-1, 0, -1), center.offset(1, 0, 1))) {
            if (level.isStateAtPosition(pos, state -> state.is(RUBlocks.SAGUARO_CACTUS.get()))) {
                return List.of();
            }
        }
        List<FoliageAttachment> attachments = new ArrayList<>();
        convertGrassBelow(level, trunkSetter, origin);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        attachments.add(attachment(origin.above(treeHeight)));

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos limb = origin.above(3 + random.nextInt(2)).relative(direction);
            BlockPos elbow = limb.relative(direction);
            if (!this.validTreePos(level, limb) || !this.validTreePos(level, elbow) || !this.validTreePos(level, elbow.above()) || !this.validTreePos(level, elbow.above(2))) {
                continue;
            }
            this.placeLog(level, trunkSetter, random, limb, config, setShape(direction.getAxis() == Direction.Axis.X ? SaguaroCactusShape.EAST_WEST : SaguaroCactusShape.NORTH_SOUTH));
            this.placeLog(level, trunkSetter, random, elbow, config, setShape(switch (direction) {
                case NORTH -> SaguaroCactusShape.SOUTH_UP;
                case SOUTH -> SaguaroCactusShape.NORTH_UP;
                case EAST -> SaguaroCactusShape.WEST_UP;
                default -> SaguaroCactusShape.EAST_UP;
            }));
            this.placeLog(level, trunkSetter, random, elbow.above(), config);
            this.placeLog(level, trunkSetter, random, elbow.above(2), config);
            attachments.add(attachment(elbow.above(3)));
        }
        return attachments;
    }

    private static Function<BlockState, BlockState> setShape(SaguaroCactusShape shape) {
        return state -> state.trySetValue(SaguaroCactusBlock.SHAPE, shape);
    }
}
