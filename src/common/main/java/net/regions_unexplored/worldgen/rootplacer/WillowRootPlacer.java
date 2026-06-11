package net.regions_unexplored.worldgen.rootplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.tag.RUBlockTags;

import java.util.*;
import java.util.function.BiConsumer;

public class WillowRootPlacer extends RootPlacer {
	public static final MapCodec<WillowRootPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		IntProvider.CODEC.fieldOf("height").forGetter(c -> c.height),
		Codec.floatRange(0, 1).fieldOf("chance").forGetter(c -> c.chance),
		BlockStateProvider.CODEC.fieldOf("root_provider").forGetter(c -> c.rootProvider),
		AboveRootPlacement.CODEC.optionalFieldOf("above_root_placement").forGetter(c -> c.aboveRootPlacement)
	).apply(i, WillowRootPlacer::new));
	public static final RootPlacerType<WillowRootPlacer> TYPE = new RootPlacerType<>(CODEC);
	private static final int MAX_ROOT_LENGTH = 6;
	
	private final IntProvider height;
	private final float chance;
	
	public WillowRootPlacer(IntProvider height, float chance, BlockStateProvider rootProvider, Optional<AboveRootPlacement> aboveRootPlacement) {
		super(ConstantInt.ZERO, rootProvider, aboveRootPlacement);
		this.height = height;
		this.chance = chance;
	}
	
	public static Optional<RootPlacer> create(WoodSet set, float chance) {
		return create(set.getLog(), chance);
	}
	
	public static Optional<RootPlacer> create(Block block, float chance) {
		return Optional.of(new WillowRootPlacer(UniformInt.of(2, 3), chance, BlockStateProvider.simple(block), Optional.empty()));
	}
	
	@Override
	protected RootPlacerType<?> type() {
		return TYPE;
	}
	
	@Override
	public boolean placeRoots(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> rootSetter, RandomSource random, BlockPos origin, BlockPos trunkOrigin, TreeConfiguration config) {
		if (random.forkPositional().at(trunkOrigin).nextFloat() > this.chance) return true;
		
		List<BlockPos> rootPositions = new ArrayList<>();
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			MutableBlockPos pos = trunkOrigin.relative(direction).above(this.height.sample(random) - 1).mutable();
			rootPositions.add(pos.immutable());
			for (int i = 0; i < MAX_ROOT_LENGTH; i++) {
				pos.move(Direction.DOWN);
				rootPositions.add(pos.immutable());
				if (!this.canPlaceRoot(level, pos.above())) break;
			}
		}
		
		for (var rootPos : rootPositions) {
			this.placeRoot(level, rootSetter, random, rootPos, config);
		}
		
		return true;
	}
	
	@Override
	protected boolean canPlaceRoot(final LevelSimulatedReader level, final BlockPos pos) {
		return level.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.REPLACEABLE_BY_TREES) || state.is(RUBlockTags.REPLACEABLE_BLOCKS) || state.is(Blocks.GRASS_BLOCK));
	}
	
	@Override
	public BlockPos getTrunkOrigin(BlockPos pos, RandomSource random) {
		return pos;
	}
}
