package net.regions_unexplored.worldgen.stateprovider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.regions_unexplored.block.type.base.BonemealableSegmentedBlock;

import java.util.function.Supplier;

public class RandomizedGroundCoverStateProvider extends BlockStateProvider {
	public static final MapCodec<RandomizedGroundCoverStateProvider> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(RandomizedGroundCoverStateProvider::block)
	).apply(i, RandomizedGroundCoverStateProvider::new));
	public static final BlockStateProviderType<RandomizedGroundCoverStateProvider> TYPE = new BlockStateProviderType<>(CODEC);
	
	private final Block block;
	
	public RandomizedGroundCoverStateProvider(Block block) {
		BlockState state = block.defaultBlockState();
		if (!(state.hasProperty(BonemealableSegmentedBlock.FACING) && state.hasProperty(BonemealableSegmentedBlock.AMOUNT))) {
			throw new IllegalStateException("randomized_ground_cover state provider requires a block with FACING and AMOUNT properties");
		}
		this.block = block;
	}
	
	public static SimpleBlockConfiguration asConfig(Supplier<Block> block) {
		return new SimpleBlockConfiguration(new RandomizedGroundCoverStateProvider(block.get()));
	}
	
	private Block block() {
		return this.block;
	}
	
	@Override
	protected BlockStateProviderType<?> type() {
		return TYPE;
	}
	
	@Override
	public BlockState getState(RandomSource random, BlockPos pos) {
		return this.block.defaultBlockState()
			.setValue(BonemealableSegmentedBlock.AMOUNT, random.nextIntBetweenInclusive(1, 4))
			.setValue(BonemealableSegmentedBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));
	}
}
