package net.regions_unexplored.block.type.flower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.base.SegmentedBlock;

public class RULeafLitterBlock extends SegmentedBlock {
    public RULeafLitterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
    
    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.isFaceSturdy(level, pos, Direction.UP);
    }
}

