package net.regions_unexplored.worldgen.stateprovider;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

import java.util.function.Supplier;

public class SupplierHackStateProvider extends BlockStateProvider {
	public static final MapCodec<SupplierHackStateProvider> CODEC = MapCodec.unit(new SupplierHackStateProvider(() -> Blocks.AIR));
	public static final BlockStateProviderType<SupplierHackStateProvider> TYPE = new BlockStateProviderType<>(CODEC);
	private final Supplier<Block> block;
	
	public SupplierHackStateProvider(Supplier<Block> block) {
		this.block = block;
	}
	
	@Override
	protected BlockStateProviderType<?> type() {
		return TYPE;
	}
	
	@Override
	public BlockState getState(WorldGenLevel worldGenLevel, RandomSource randomSource, BlockPos blockPos) {
		return this.block.get().defaultBlockState();
	}
}
