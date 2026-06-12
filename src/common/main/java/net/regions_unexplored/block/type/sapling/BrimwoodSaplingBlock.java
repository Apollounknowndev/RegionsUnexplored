package net.regions_unexplored.block.type.sapling;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.tag.RUBlockTags;

public class BrimwoodSaplingBlock extends RUSaplingBlock {
	public BrimwoodSaplingBlock(RUTreeGrower treeGrower, Properties properties) {
		super(treeGrower, properties);
	}
	
	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.is(RUBlockTags.SUPPORTS_INFERNAL_PLANT);
	}
}
