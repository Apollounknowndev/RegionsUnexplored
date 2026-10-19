package net.regions_unexplored.block.type.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.client.color.RUColors;

public class PrismaglassBlock extends TransparentBlock implements BeaconBeamBlock {
	public PrismaglassBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public DyeColor getColor() {
		// Placeholder value, actually is RGB
		return DyeColor.WHITE;
	}
	
	//? if neoforge {
	/*@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader levelReader, BlockPos pos, BlockPos beaconPos) {
		if (levelReader instanceof Level level) {
			return RUColors.getRainbowColor(pos.getX(), pos.getZ() + level.getGameTime() / 8f, 0.8f);
		}
		return DyeColor.WHITE.getTextureDiffuseColor();
	}
	*///? }
}
