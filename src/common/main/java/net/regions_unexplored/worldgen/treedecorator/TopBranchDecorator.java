package net.regions_unexplored.worldgen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.regions_unexplored.config.RUConfigHandler;

public class TopBranchDecorator extends TreeDecorator {
    public static final MapCodec<TopBranchDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter(d -> d.branchProvider),
        Codec.BOOL.fieldOf("use_branch_mode").forGetter(d -> d.useBranchMode)
    ).apply(i, TopBranchDecorator::new));
    public static final TreeDecoratorType<TopBranchDecorator> TYPE = new TreeDecoratorType<>(CODEC);

    private final BlockStateProvider branchProvider;
    private final boolean useBranchMode;

    public TopBranchDecorator(BlockStateProvider branchProvider, boolean useBranchMode) {
        this.branchProvider = branchProvider;
        this.useBranchMode = useBranchMode;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TYPE;
    }

    @Override
    public void place(Context context) {
        if (this.useBranchMode && RUConfigHandler.COMMON.getBranchMode().cannotPlace()) {
            return;
        }
        int topY = context.logs().stream().mapToInt(Vec3i::getY).max().orElse(Integer.MIN_VALUE);
        for (BlockPos log : context.logs()) {
            if (log.getY() != topY) continue;
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos pos = log.relative(direction);
                if (!context.level().isStateAtPosition(pos, BlockState::canBeReplaced)) continue;
                BlockState state = this.branchProvider.getState(context.random(), pos)
                    .trySetValue(BlockStateProperties.AXIS, direction.getAxis())
                    .trySetValue(BlockStateProperties.HORIZONTAL_FACING, direction);
                context.setBlock(pos, state);
            }
        }
    }
}
