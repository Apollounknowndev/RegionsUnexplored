package net.regions_unexplored.block.type.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.RUBlockUtils;

public class RUGrowingPlantHeadBlock extends GrowingPlantHeadBlock {
	public static final MapCodec<? extends RUGrowingPlantHeadBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		ResourceKey.codec(Registries.BLOCK).fieldOf("body_block").forGetter(b -> b.bodyBlock),
		Codec.floatRange(0, 16).fieldOf("width").forGetter(b -> b.width),
		Codec.floatRange(0, 16).fieldOf("min_y").forGetter(b -> b.minY),
		propertiesCodec()
	).apply(i, RUGrowingPlantHeadBlock::new));
	
	protected final ResourceKey<Block> bodyBlock;
	protected final float width;
	protected final float minY;
	
	public RUGrowingPlantHeadBlock(ResourceKey<Block> bodyBlock, float width, float minY, Properties properties) {
		super(properties, Direction.DOWN, RUBlockUtils.column(width, minY, 16), false, 0.1);
		this.bodyBlock = bodyBlock;
		this.width = width;
		this.minY = minY;
	}
	
	@Override
	protected MapCodec<? extends RUGrowingPlantHeadBlock> codec() {
		return CODEC;
	}
	
	@Override
	protected Block getBodyBlock() {
		return BuiltInRegistries.BLOCK.getValue(this.bodyBlock);
	}
	
	@Override
	protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
		return NetherVines.getBlocksToGrowWhenBonemealed(random);
	}
	
	@Override
	protected boolean canGrowInto(BlockState state) {
		return state.isAir();
	}
}
