package net.regions_unexplored.block.type.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.base.SpeleothemBlock;

import java.util.Optional;
import java.util.function.Supplier;

public class IcicleBlock extends SpeleothemBlock {
	public IcicleBlock(Optional<Supplier<Block>> blockToGrowOn, Properties properties) {
		super(blockToGrowOn, properties);
	}
	
	@Override
	protected boolean shouldFall(final BlockState state, final ServerLevel level, final BlockPos pos) {
		return false;
	}
}
