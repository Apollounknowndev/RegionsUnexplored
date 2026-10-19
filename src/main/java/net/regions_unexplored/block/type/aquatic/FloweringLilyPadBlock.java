package net.regions_unexplored.block.type.aquatic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LilyPadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;

public class FloweringLilyPadBlock extends LilyPadBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = RUBlockUtils.column(12, 0, 2);

    public FloweringLilyPadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any());
    }

    @Override
    public VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockPos belowPos = pos.below();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (
                level.getBlockState(pos.relative(direction)).isAir() &&
                level.getBlockState(pos.relative(direction.getClockWise())).isAir() &&
                level.getBlockState(pos.relative(direction.getClockWise()).relative(direction)).isAir() &&
                level.getFluidState(belowPos).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction)).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction.getClockWise())).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction.getClockWise()).relative(direction)).is(Fluids.WATER)
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        GiantLilyPadBlock.tryPlace(level, pos, random);
    }
}