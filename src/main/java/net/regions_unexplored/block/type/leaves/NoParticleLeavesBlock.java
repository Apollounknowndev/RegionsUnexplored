package net.regions_unexplored.block.type.leaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;

public class NoParticleLeavesBlock extends LeavesBlock {
	public NoParticleLeavesBlock(Properties properties) {
		super(0, properties);
	}
	
	@Override
	public MapCodec<? extends LeavesBlock> codec() {
		return null;
	}
	
	@Override
	protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {
	
	}
}
