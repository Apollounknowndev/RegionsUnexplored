package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.base.ClusterBlock;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.registry.RUParticleTypes;

public class PrismariteClusterBlock extends ClusterBlock {
	public PrismariteClusterBlock(float height, float aabbOffset, Properties props) {
		super(height, aabbOffset, props);
	}
	
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (random.nextFloat() >= (0.05 * RUConfigHandler.CLIENT.particleRates.prismarite)) return;
		
		int plantX = pos.getX();
		int plantY = pos.getY();
		int plantZ = pos.getZ();
		
		BlockPos.MutableBlockPos ambientPos = new BlockPos.MutableBlockPos();
		for (int i = 0; i < 2; ++i) {
			ambientPos.set(plantX + Mth.nextInt(random, -2, 2), plantY + random.nextInt(3), plantZ + Mth.nextInt(random, -2, 2));
			BlockState particlePosState = level.getBlockState(ambientPos);
			if (particlePosState.isCollisionShapeFullBlock(level, ambientPos)) continue;
			
			level.addParticle(RUParticleTypes.PRISMARITE_SPARKLE.get(), (double)ambientPos.getX() + random.nextDouble(), (double)ambientPos.getY() + random.nextDouble(), (double)ambientPos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
		}
	}
}
