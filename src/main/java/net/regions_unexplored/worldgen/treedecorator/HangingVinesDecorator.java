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

public class HangingVinesDecorator extends TreeDecorator {
	public static final MapCodec<HangingVinesDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(HangingVinesDecorator::block),
		Codec.floatRange(0, 1).fieldOf("probability").forGetter(HangingVinesDecorator::probability)
	).apply(i, HangingVinesDecorator::new));
	public static final TreeDecoratorType<HangingVinesDecorator> TYPE = new TreeDecoratorType<>(CODEC);
	public static final float CONTINUE_EXTENSION_CHANCE = 0.4f;
	
	private final HangingVinesBlock block;
	private final float probability;
	
	public static HangingVinesDecorator create(NaturalSet set, float probability) {
		return new HangingVinesDecorator(set.getVines(), probability);
	}
	
	public HangingVinesDecorator(Block block, float probability) {
		if (!(block instanceof HangingVinesBlock hangingLeaves)) {
			throw new IllegalArgumentException("`hanging_leaves` tree decorator requires instance of HangingVinesBlock, got: " + block.getClass().getSimpleName());
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
					while (context.isAir(pos.below()) && context.random().nextFloat() > CONTINUE_EXTENSION_CHANCE) {
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
