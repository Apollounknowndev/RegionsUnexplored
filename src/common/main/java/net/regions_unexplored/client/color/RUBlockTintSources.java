package net.regions_unexplored.client.color;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class RUBlockTintSources {
	public static BlockTintSource prismarite() {
		return new BlockTintSource() {
			@Override
			public int color(BlockState state) {
				return 0xffffffff;
			}
			
			@Override
			public int colorInWorld(final BlockState state, final BlockAndTintGetter level, final BlockPos pos) {
				return RUColors.getRainbowColor(pos, 0.9f);
			}
		};
	}
	
	public static BlockTintSource posBasedOrFoliage(Function<BlockPos, Integer> getter) {
		return new BlockTintSource() {
			@Override
			public int color(BlockState state) {
				return 0xff48b518;
			}
			
			@Override
			public int colorInWorld(final BlockState state, final BlockAndTintGetter level, final BlockPos pos) {
				return getter.apply(pos);
			}
		};
	}
}
