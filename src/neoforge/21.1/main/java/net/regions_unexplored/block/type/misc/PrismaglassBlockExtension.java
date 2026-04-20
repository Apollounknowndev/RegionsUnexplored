package net.regions_unexplored.block.type.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.msrandom.classextensions.ClassExtension;
import net.msrandom.classextensions.ExtensionInject;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import net.regions_unexplored.client.color.RuColors;

@ClassExtension(PrismaglassBlock.class)
public class PrismaglassBlockExtension implements IBlockExtension {
	@ExtensionInject
	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader levelReader, BlockPos pos, BlockPos beaconPos) {
		if (levelReader instanceof Level level) {
			return RuColors.getRainbowColor(pos.getX(), pos.getZ() + level.getGameTime() / 8f);
		}
		return DyeColor.WHITE.getTextureDiffuseColor();
	}
}
