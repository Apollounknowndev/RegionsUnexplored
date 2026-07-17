package net.regions_unexplored.worldgen.rootplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

public class MagnoliaRootPlacer extends RootPlacer {
	public static final MapCodec<MagnoliaRootPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> rootPlacerParts(i).apply(i, MagnoliaRootPlacer::new));
	public static final RootPlacerType<MagnoliaRootPlacer> TYPE = new RootPlacerType<>(CODEC);
	private static final int MAX_ROOT_LENGTH = 6;
	
	public MagnoliaRootPlacer(IntProvider trunkOffsetY, BlockStateProvider rootProvider, Optional<AboveRootPlacement> aboveRootPlacement) {
		super(trunkOffsetY, rootProvider, aboveRootPlacement);
	}
	
	@Override
	protected RootPlacerType<?> type() {
		return TYPE;
	}
	
	@Override
	public boolean placeRoots(
		final WorldGenLevel level,
		final BiConsumer<BlockPos, BlockState> rootSetter,
		final RandomSource random,
		final BlockPos origin,
		final BlockPos trunkOrigin,
		final TreeConfiguration config
	) {
		Map<BlockPos, UnaryOperator<BlockState>> rootPositions = new HashMap<>();
		
		Direction rootDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		
		BlockPos.MutableBlockPos pos = trunkOrigin.relative(rootDirection).mutable();
		rootPositions.put(pos.immutable(), s -> s.trySetValue(RotatedPillarBlock.AXIS, rootDirection.getAxis()));
		for (int i = 0; i < MAX_ROOT_LENGTH; i++) {
			if (!this.canPlaceRoot(level, pos)) break;
			pos.move(Direction.DOWN);
			rootPositions.put(pos.immutable(), s -> s);
		}
		
		pos = trunkOrigin.relative(rootDirection.getClockWise()).relative(rootDirection.getOpposite()).mutable();
		rootPositions.put(pos.immutable(), s -> s);
		for (int i = 0; i < MAX_ROOT_LENGTH; i++) {
			if (!this.canPlaceRoot(level, pos)) break;
			pos.move(Direction.DOWN);
			rootPositions.put(pos.immutable(), s -> s);
		}
		
		for (var rootEntry : rootPositions.entrySet()) {
			this.placeRoot(level, wrap(rootSetter, rootEntry.getValue()), random, rootEntry.getKey(), config);
		}
		
		return true;
	}
	
	private static BiConsumer<BlockPos, BlockState> wrap(BiConsumer<BlockPos, BlockState> rootSetter, UnaryOperator<BlockState> operator) {
		return (pos, state) -> rootSetter.accept(pos, operator.apply(state));
	}
}
