package net.regions_unexplored.block.type.sapling;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.tag.RUBlockTags;

public class CobaltSaplingBlock extends RUSaplingBlock {
	public CobaltSaplingBlock(RUTreeGrower treeGrower, Properties properties) {
		super(treeGrower, properties);
	}
	
	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
		return state.is(BlockTags.NYLIUM);
	}
}
