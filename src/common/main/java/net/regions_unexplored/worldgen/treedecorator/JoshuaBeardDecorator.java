package net.regions_unexplored.worldgen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class JoshuaBeardDecorator extends TreeDecorator {
    public static final MapCodec<JoshuaBeardDecorator> CODEC = BlockStateProvider.CODEC.fieldOf("branch_provider")
        .xmap(JoshuaBeardDecorator::new, decorator -> decorator.branchProvider);
    public static final TreeDecoratorType<JoshuaBeardDecorator> TYPE = new TreeDecoratorType<>(CODEC);

    private final BlockStateProvider branchProvider;

    public JoshuaBeardDecorator(BlockStateProvider branchProvider) {
        this.branchProvider = branchProvider;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TYPE;
    }

    @Override
    public void place(Context context) {
        for (BlockPos log : context.logs()) {
            if (!context.leaves().contains(log.above())) continue;
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
