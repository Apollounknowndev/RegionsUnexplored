package net.regions_unexplored.worldgen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;

public class HangingLeavesDecorator extends TreeDecorator {
	public static final MapCodec<HangingLeavesDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(HangingLeavesDecorator::block),
		Codec.floatRange(0, 1).fieldOf("probability").forGetter(HangingLeavesDecorator::probability)
	).apply(i, HangingLeavesDecorator::new));
	public static final TreeDecoratorType<HangingLeavesDecorator> TYPE = new TreeDecoratorType<>(CODEC);
	
	private final HangingVinesBlock block;
	private final float probability;
	
	public static HangingLeavesDecorator create(NaturalSet set, float probability) {
		return new HangingLeavesDecorator(set.getVines(), probability);
	}
	
	public HangingLeavesDecorator(Block block, float probability) {
		if (!(block instanceof HangingVinesBlock hangingLeaves)) {
			throw new IllegalArgumentException("`hanging_leaves` tree decorator requires instance of HangingLeavesBlock, got: " + block.getClass().getSimpleName());
		}
		this.block = hangingLeaves;
		this.probability = probability;
	}
	
	public HangingVinesBlock block() {
		return block;
	}
	
	public float probability() {
		return probability;
	}
	
	@Override
	public void place(Context context) {
		for (BlockPos leafPos : context.leaves()) {
			if (this.probability > context.random().nextFloat()) {
				BlockPos pos = leafPos.below();
				if (context.isAir(pos)) {
					while (context.isAir(pos.below()) && !(context.random().nextFloat() < 0.5)) {
						context.setBlock(pos, this.block.defaultBlockState().setValue(HangingVinesBlock.TIP, false));
						pos = pos.below();
					}
					
					context.setBlock(pos, this.block.defaultBlockState().setValue(HangingVinesBlock.TIP, true));
				}
			}
		}
	}
	
	@Override
	protected TreeDecoratorType<?> type() {
		return TYPE;
	}
}
