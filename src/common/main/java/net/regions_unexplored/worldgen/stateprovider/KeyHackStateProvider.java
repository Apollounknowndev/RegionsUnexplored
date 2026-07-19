package net.regions_unexplored.worldgen.stateprovider;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class KeyHackStateProvider extends BlockStateProvider {
	public static final MapCodec<KeyHackStateProvider> CODEC = MapCodec.unit(new KeyHackStateProvider(BlockIds.DIRT));
	public static final BlockStateProviderType<KeyHackStateProvider> TYPE = new BlockStateProviderType<>(CODEC);
	private final ResourceKey<Block> block;
	
	public KeyHackStateProvider(ResourceKey<Block> block) {
		this.block = block;
	}
	
	@Override
	protected BlockStateProviderType<?> type() {
		return TYPE;
	}
	
	@Override
	public BlockState getState(WorldGenLevel level, RandomSource random, BlockPos pos) {
		return BuiltInRegistries.BLOCK.getValue(this.block).defaultBlockState();
	}
}
