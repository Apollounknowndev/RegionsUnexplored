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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.common.RUCommonConfig.Misc.BranchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SmallOakTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<SmallOakTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> SmallOakTrunkPlacer.heightField(i).and(
        BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter(t -> t.branchProvider)
    ).apply(i, SmallOakTrunkPlacer::new));
    public static final TrunkPlacerType<SmallOakTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    protected final BlockStateProvider branchProvider;

    public SmallOakTrunkPlacer(IntProvider height, BlockStateProvider branchProvider) {
        super(height);
        this.branchProvider = branchProvider;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (!RUConfigHandler.COMMON.misc.smallOakTrees) {
            return List.of();
        }
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight + 2))) {
            return List.of();
        }
        List<FoliageAttachment> attachments = new ArrayList<>();
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        attachments.add(attachment(origin.above(treeHeight)));

        BlockStateProvider branchProvider = RUConfigHandler.COMMON.getBranchMode() == BranchMode.PLACE_BRANCHES ? this.branchProvider : config.trunkProvider;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos branch = origin.above(treeHeight - 2 - random.nextInt(2)).relative(direction);
            if (!this.validTreePos(level, branch)) {
                continue;
            }
            BlockState state = branchProvider.getState(random, branch)
                .trySetValue(BlockStateProperties.AXIS, direction.getAxis())
                .trySetValue(BlockStateProperties.HORIZONTAL_FACING, direction);
            trunkSetter.accept(branch, state);
            attachments.add(new FoliageAttachment(branch, -1, false));
        }
        return attachments;
    }
}
