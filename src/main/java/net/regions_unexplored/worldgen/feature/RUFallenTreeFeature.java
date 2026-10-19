package net.regions_unexplored.worldgen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.regions_unexplored.worldgen.feature.config.FallenTreeConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class RUFallenTreeFeature extends Feature<FallenTreeConfig> {
	
	public RUFallenTreeFeature() {
		super(FallenTreeConfig.CODEC);
	}
	
	@Override
	public boolean place(FeaturePlaceContext<FallenTreeConfig> context) {
		this.placeFallenTree(context.config(), context.origin(), context.level(), context.random());
		return true;
	}
	
	private void placeFallenTree(FallenTreeConfig config, BlockPos origin, WorldGenLevel level, RandomSource random) {
		this.placeStump(config, level, random, origin.mutable());
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
		int logLength = config.logLength().sample(random) - 2;
		BlockPos.MutableBlockPos logStartPos = origin.relative(direction, 2 + random.nextInt(2)).mutable();
		this.setGroundHeightForFallenLogStartPos(level, logStartPos);
		if (this.canPlaceEntireFallenLog(level, logLength, logStartPos, direction)) {
			this.placeFallenLog(config, level, random, logLength, logStartPos, direction);
		}
	}
	
	private void setGroundHeightForFallenLogStartPos(WorldGenLevel level, BlockPos.MutableBlockPos logStartPos) {
		logStartPos.move(Direction.UP, 1);
		for (int i = 0; i < 6; ++i) {
			if (this.mayPlaceOn(level, logStartPos)) {
				return;
			}
			logStartPos.move(Direction.DOWN);
		}
	}
	
	private void placeStump(FallenTreeConfig config, WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos stumpPos) {
		BlockPos stump = this.placeLogBlock(config, level, random, stumpPos, Function.identity());
		this.decorateLogs(level, random, Set.of(stump), config.stumpDecorators());
	}
	
	private boolean canPlaceEntireFallenLog(WorldGenLevel level, int logLength, BlockPos.MutableBlockPos logStartPos, Direction direction) {
		int gapInGround = 0;
		for (int i = 0; i < logLength; ++i) {
			if (!TreeFeature.validTreePos(level, logStartPos)) {
				return false;
			}
			if (!this.isOverSolidGround(level, logStartPos)) {
				if (++gapInGround > 2) {
					return false;
				}
			} else {
				gapInGround = 0;
			}
			logStartPos.move(direction);
		}
		logStartPos.move(direction.getOpposite(), logLength);
		return true;
	}
	
	private void placeFallenLog(FallenTreeConfig config, WorldGenLevel level, RandomSource random, int logLength, BlockPos.MutableBlockPos logStartPos, Direction direction) {
		HashSet<BlockPos> fallenLog = new HashSet<>();
		for (int i = 0; i < logLength; ++i) {
			fallenLog.add(this.placeLogBlock(config, level, random, logStartPos, RUFallenTreeFeature.getSidewaysStateModifier(direction)));
			logStartPos.move(direction);
		}
		this.decorateLogs(level, random, fallenLog, config.logDecorators());
	}
	
	private boolean mayPlaceOn(LevelAccessor level, BlockPos blockPos) {
		return TreeFeature.validTreePos(level, blockPos) && this.isOverSolidGround(level, blockPos);
	}
	
	private boolean isOverSolidGround(LevelAccessor level, BlockPos blockPos) {
		return level.getBlockState(blockPos.below()).isFaceSturdy(level, blockPos, Direction.UP);
	}
	
	private BlockPos placeLogBlock(FallenTreeConfig config, WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos blockPos, Function<BlockState, BlockState> sidewaysStateModifier) {
		level.setBlock(blockPos, sidewaysStateModifier.apply(config.trunkProvider().getState(random, blockPos)), 3);
		this.markAboveForPostProcessing(level, blockPos);
		return blockPos.immutable();
	}
	
	private void decorateLogs(WorldGenLevel level, RandomSource random, Set<BlockPos> logs, List<TreeDecorator> decorators) {
		if (!decorators.isEmpty()) {
			TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, this.getDecorationSetter(level), random, logs, Set.of(), Set.of());
			decorators.forEach(decorator -> decorator.place(decoratorContext));
		}
	}
	
	private BiConsumer<BlockPos, BlockState> getDecorationSetter(WorldGenLevel level) {
		return (pos, state) -> level.setBlock(pos, state, 19);
	}
	
	private static Function<BlockState, BlockState> getSidewaysStateModifier(Direction direction) {
		return state -> (BlockState)state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
	}
}

