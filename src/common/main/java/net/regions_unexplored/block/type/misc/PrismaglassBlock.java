package net.regions_unexplored.block.type.misc;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.TransparentBlock;

public class PrismaglassBlock extends TransparentBlock implements BeaconBeamBlock {
	public PrismaglassBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public DyeColor getColor() {
		// Placeholder value, actually is RGB
		return DyeColor.WHITE;
	}
}
