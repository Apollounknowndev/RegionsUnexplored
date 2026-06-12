package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.base.ClusterBlock;

public class RedstoneBulbBlock extends ClusterBlock {
	public RedstoneBulbBlock(float height, float aabbOffset, Properties props) {
		super(height, aabbOffset, props);
	}
	
	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}
	
	@Override
	public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 13;
	}
}
