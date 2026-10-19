package net.regions_unexplored.block.type.base;

import com.google.common.annotations.VisibleForTesting;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.BackportedBlockTags;
import org.jetbrains.annotations.Nullable;

public class SpeleothemBlock extends Block implements SimpleWaterloggedBlock, Fallable {
	public static final EnumProperty<Direction> TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
	public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape SHAPE_TIP_MERGE = RUBlockUtils.column(6.0, 0.0, 16.0);
	private static final VoxelShape SHAPE_TIP_UP = RUBlockUtils.column(6.0, 0.0, 11.0);
	protected static final VoxelShape SHAPE_TIP_DOWN = RUBlockUtils.column(6.0, 5.0, 16.0);
	private static final VoxelShape SHAPE_FRUSTUM = RUBlockUtils.column(8.0, 0.0, 16.0);
	private static final VoxelShape SHAPE_MIDDLE = RUBlockUtils.column(10.0, 0.0, 16.0);
	private static final VoxelShape SHAPE_BASE = RUBlockUtils.column(12.0, 0.0, 16.0);
	
	private static final float MAX_HORIZONTAL_OFFSET = (float)SHAPE_BASE.min(Axis.X);
	
	protected final Optional<Supplier<Block>> blockToGrowOn;
	
	public SpeleothemBlock(Optional<Supplier<Block>> blockToGrowOn, final Properties properties) {
		super(properties);
		this.blockToGrowOn = blockToGrowOn;
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(TIP_DIRECTION, Direction.UP)
			.setValue(THICKNESS, DripstoneThickness.TIP)
			.setValue(WATERLOGGED, false)
		);
	}
	
	@Override
	protected void createBlockStateDefinition(final Builder<Block, BlockState> builder) {
		builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
	}
	
	@Override
	protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
		return this.isValidSpeleothemPlacement(level, pos, state.getValue(TIP_DIRECTION));
	}
	
	@Override
	protected BlockState updateShape(
		final BlockState state,
		final Direction directionToNeighbour,
		final BlockState neighbourState,
		final LevelAccessor level,
		final BlockPos pos,
		final BlockPos neighbourPos
	) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		
		if (directionToNeighbour != Direction.UP && directionToNeighbour != Direction.DOWN) {
			return state;
		} else {
			Direction tipDirection = state.getValue(TIP_DIRECTION);
			if (tipDirection == Direction.DOWN && level.getBlockTicks().hasScheduledTick(pos, this)) {
				return state;
			} else if (directionToNeighbour == tipDirection.getOpposite() && !this.canSurvive(state, level, pos)) {
				if (tipDirection == Direction.DOWN) {
					level.scheduleTick(pos, this, 2);
				} else {
					level.scheduleTick(pos, this, 1);
				}
				
				return state;
			} else {
				boolean mergeOpposingTips = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
				DripstoneThickness newThickness = this.calculateDripstoneThickness(level, pos, tipDirection, mergeOpposingTips);
				return state.setValue(THICKNESS, newThickness);
			}
		}
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(final BlockPlaceContext context) {
		LevelAccessor level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Direction defaultTipDirection = context.getNearestLookingVerticalDirection().getOpposite();
		Direction tipDirection = this.calculateTipDirection(level, pos, defaultTipDirection);
		if (tipDirection == null) {
			return null;
		} else {
			boolean mergeOpposingTips = !context.isSecondaryUseActive();
			DripstoneThickness thickness = this.calculateDripstoneThickness(level, pos, tipDirection, mergeOpposingTips);
			return this.defaultBlockState()
				.setValue(TIP_DIRECTION, tipDirection)
				.setValue(THICKNESS, thickness)
				.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
		}
	}
	
	@Nullable
	private Direction calculateTipDirection(final LevelReader level, final BlockPos pos, final Direction defaultTipDirection) {
		Direction tipDirection;
		if (this.isValidSpeleothemPlacement(level, pos, defaultTipDirection)) {
			tipDirection = defaultTipDirection;
		} else {
			if (!this.isValidSpeleothemPlacement(level, pos, defaultTipDirection.getOpposite())) {
				return null;
			}
			
			tipDirection = defaultTipDirection.getOpposite();
		}
		
		return tipDirection;
	}
	
	private DripstoneThickness calculateDripstoneThickness(
		final LevelReader level, final BlockPos pos, final Direction tipDirection, final boolean mergeOpposingTips
	) {
		Direction baseDirection = tipDirection.getOpposite();
		BlockState inFrontState = level.getBlockState(pos.relative(tipDirection));
		if (isSpeleothemWithDirection(inFrontState, baseDirection) && inFrontState.is(this)) {
			return !mergeOpposingTips && inFrontState.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE
				? DripstoneThickness.TIP
				: DripstoneThickness.TIP_MERGE;
		} else if (!isSpeleothemWithDirection(inFrontState, tipDirection)) {
			return DripstoneThickness.TIP;
		} else {
			DripstoneThickness inFrontThickness = inFrontState.getValue(THICKNESS);
			if (inFrontThickness != DripstoneThickness.TIP && inFrontThickness != DripstoneThickness.TIP_MERGE) {
				BlockState behindState = level.getBlockState(pos.relative(baseDirection));
				return !isSpeleothemWithDirection(behindState, tipDirection) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
			} else {
				return DripstoneThickness.FRUSTUM;
			}
		}
	}
	
	private boolean isValidSpeleothemPlacement(final LevelReader level, final BlockPos pos, final Direction tipDirection) {
		BlockPos behindPos = pos.relative(tipDirection.getOpposite());
		BlockState behindState = level.getBlockState(behindPos);
		return behindState.isFaceSturdy(level, behindPos, tipDirection) || isSpeleothemWithDirection(behindState, tipDirection) && behindState.is(this);
	}
	
	private static boolean isSpeleothemWithDirection(final BlockState blockState, final Direction tipDirection) {
		return blockState.is(BackportedBlockTags.SPELEOTHEMS) && blockState.getValue(TIP_DIRECTION) == tipDirection;
	}
	
	@Override
	protected void onProjectileHit(final Level level, final BlockState state, final BlockHitResult blockHit, final Projectile projectile) {
		if (!level.isClientSide()) {
			BlockPos blockPos = blockHit.getBlockPos();
			if (level instanceof ServerLevel serverLevel
				&& projectile.mayInteract(serverLevel, blockPos)
				&& projectile.mayBreak(serverLevel)
				&& projectile instanceof ThrownTrident
				&& projectile.getDeltaMovement().length() > 0.6) {
				level.destroyBlock(blockPos, true);
			}
		}
	}
	
	@Override
	protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
		if (shouldFall(state, level, pos)) {
			spawnFallingStalactite(state, level, pos);
		} else {
			level.destroyBlock(pos, true);
		}
	}
	
	protected boolean shouldFall(final BlockState state, final ServerLevel level, final BlockPos pos) {
		return !(isStalagmite(state) && !this.canSurvive(state, level, pos));
	}
	
	private static void spawnFallingStalactite(final BlockState state, final ServerLevel level, final BlockPos pos) {
		MutableBlockPos fallPos = pos.mutable();
		BlockState fallState = state;
		
		while (isStalactite(fallState)) {
			FallingBlockEntity entity = FallingBlockEntity.fall(level, fallPos, fallState);
			if (isTip(fallState, true)) {
				int size = Math.max(1 + pos.getY() - fallPos.getY(), 6);
				float damagePerFallDistance = 1.0F * size;
				entity.setHurtsEntities(damagePerFallDistance, 40);
				break;
			}
			
			fallPos.move(Direction.DOWN);
			fallState = level.getBlockState(fallPos);
		}
	}
	
	private static boolean isStalagmite(final BlockState state) {
		return isSpeleothemWithDirection(state, Direction.UP);
	}
	
	protected static boolean isStalactite(final BlockState state) {
		return isSpeleothemWithDirection(state, Direction.DOWN);
	}
	
	private static boolean isTip(final BlockState state, final boolean includeMergedTip) {
		if (!state.is(BackportedBlockTags.SPELEOTHEMS)) {
			return false;
		} else {
			DripstoneThickness thickness = state.getValue(THICKNESS);
			return thickness == DripstoneThickness.TIP || includeMergedTip && thickness == DripstoneThickness.TIP_MERGE;
		}
	}
	
	@Override
	public void onBrokenAfterFall(final Level level, final BlockPos pos, final FallingBlockEntity entity) {
		if (!entity.isSilent()) {
			level.levelEvent(LevelEvent.SOUND_POINTED_DRIPSTONE_LAND, pos, 0);
		}
	}
	
	@Override
	public DamageSource getFallDamageSource(final Entity entity) {
		return entity.damageSources().fallingStalactite(entity);
	}
	
	@Override
	protected FluidState getFluidState(final BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		VoxelShape shape = switch (state.getValue(THICKNESS)) {
			case TIP_MERGE -> SHAPE_TIP_MERGE;
			case TIP -> state.getValue(TIP_DIRECTION) == Direction.DOWN ? SHAPE_TIP_DOWN : SHAPE_TIP_UP;
			case FRUSTUM -> SHAPE_FRUSTUM;
			case MIDDLE -> SHAPE_MIDDLE;
			case BASE -> SHAPE_BASE;
		};
		
		Vec3 offset = state.getOffset(level, pos);
		return shape.move(offset.x, 0, offset.z);
	}
	
	@Override
	protected boolean isCollisionShapeFullBlock(final BlockState state, final BlockGetter level, final BlockPos pos) {
		return false;
	}
	
	@Override
	protected float getMaxHorizontalOffset() {
		return MAX_HORIZONTAL_OFFSET;
	}
	
	@Override
	protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
		return false;
	}
	
	@Override
	protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
		if (random.nextFloat() < 0.011377778F && isStalactiteStartPos(state, level, pos)) {
			this.growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
		}
	}
	
	protected static boolean isStalactiteStartPos(final BlockState state, final LevelReader level, final BlockPos pos) {
		return isStalactite(state) && !level.getBlockState(pos.above()).is(state.getBlock());
	}
	
	@VisibleForTesting
	public void growStalactiteOrStalagmiteIfPossible(
		final BlockState stalactiteStartState, final ServerLevel level, final BlockPos stalactiteStartPos, final RandomSource random
	) {
		if (this.canGrow(level, stalactiteStartPos)) {
			BlockPos stalactiteTipPos = findTip(stalactiteStartState, level, stalactiteStartPos, this.getMaxGrowthLength(), false);
			if (stalactiteTipPos != null) {
				BlockState stalactiteTipState = level.getBlockState(stalactiteTipPos);
				if (isFreeHangingStalactite(stalactiteTipState) && this.canTipGrow(stalactiteTipState, level, stalactiteTipPos)) {
					if (random.nextBoolean()) {
						this.grow(level, stalactiteTipPos, Direction.DOWN);
					} else {
						this.growStalagmiteBelow(level, stalactiteTipPos);
					}
				}
			}
		}
	}
	
	protected boolean canGrow(final LevelReader level, final BlockPos pos) {
		return this.blockToGrowOn.isPresent() && level.getBlockState(pos.above()).is(this.blockToGrowOn.get().get());
	}
	
	protected static @Nullable BlockPos findTip(
		final BlockState speleothemState, final LevelAccessor level, final BlockPos speleothemPos, final int maxSearchLength, final boolean includeMergedTip
	) {
		if (isTip(speleothemState, includeMergedTip)) {
			return speleothemPos;
		} else {
			Direction searchDirection = speleothemState.getValue(TIP_DIRECTION);
			BiPredicate<BlockPos, BlockState> pathPredicate = (pos, state) -> state.is(speleothemState.getBlock())
				&& state.getValue(TIP_DIRECTION) == searchDirection;
			return findBlockVertical(
				level,
				speleothemPos,
				searchDirection.getAxisDirection(),
				pathPredicate,
				speleothem -> isTip(speleothem, includeMergedTip),
				maxSearchLength
			).orElse(null);
		}
	}
	
	protected static Optional<BlockPos> findBlockVertical(
		final LevelAccessor level,
		final BlockPos pos,
		final AxisDirection axisDirection,
		final BiPredicate<BlockPos, BlockState> pathPredicate,
		final Predicate<BlockState> targetPredicate,
		final int maxSteps
	) {
		Direction direction = Direction.get(axisDirection, Axis.Y);
		MutableBlockPos mutablePos = pos.mutable();
		
		for (int i = 1; i < maxSteps; i++) {
			mutablePos.move(direction);
			BlockState state = level.getBlockState(mutablePos);
			if (targetPredicate.test(state)) {
				return Optional.of(mutablePos.immutable());
			}
			
			if (level.isOutsideBuildHeight(mutablePos.getY()) || !pathPredicate.test(mutablePos, state)) {
				return Optional.empty();
			}
		}
		
		return Optional.empty();
	}
	
	private boolean canTipGrow(final BlockState tipState, final ServerLevel level, final BlockPos tipPos) {
		Direction growDirection = tipState.getValue(TIP_DIRECTION);
		BlockPos growPos = tipPos.relative(growDirection);
		BlockState stateAtGrowPos = level.getBlockState(growPos);
		if (!stateAtGrowPos.getFluidState().isEmpty()) {
			return false;
		} else {
			return stateAtGrowPos.isAir() ? true : this.isUnmergedTipWithDirection(stateAtGrowPos, growDirection.getOpposite());
		}
	}
	
	private boolean isUnmergedTipWithDirection(final BlockState state, final Direction tipDirection) {
		return isTip(state, false) && state.getValue(TIP_DIRECTION) == tipDirection && state.is(this);
	}
	
	private void grow(final ServerLevel level, final BlockPos growFromPos, final Direction growToDirection) {
		BlockPos targetPos = growFromPos.relative(growToDirection);
		BlockState existingStateAtTargetPos = level.getBlockState(targetPos);
		if (this.isUnmergedTipWithDirection(existingStateAtTargetPos, growToDirection.getOpposite())) {
			this.createMergedTips(existingStateAtTargetPos, level, targetPos);
		} else if (existingStateAtTargetPos.isAir() || existingStateAtTargetPos.is(Blocks.WATER)) {
			this.createSpeleothem(level, targetPos, growToDirection, DripstoneThickness.TIP);
		}
	}
	
	private void createSpeleothem(final LevelAccessor level, final BlockPos pos, final Direction direction, final DripstoneThickness thickness) {
		BlockState state = this.defaultBlockState()
			.setValue(TIP_DIRECTION, direction)
			.setValue(THICKNESS, thickness)
			.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
		level.setBlock(pos, state, 3);
	}
	
	private void createMergedTips(final BlockState tipState, final LevelAccessor level, final BlockPos tipPos) {
		BlockPos stalactitePos;
		BlockPos stalagmitePos;
		if (tipState.getValue(TIP_DIRECTION) == Direction.UP) {
			stalagmitePos = tipPos;
			stalactitePos = tipPos.above();
		} else {
			stalactitePos = tipPos;
			stalagmitePos = tipPos.below();
		}
		
		this.createSpeleothem(level, stalactitePos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
		this.createSpeleothem(level, stalagmitePos, Direction.UP, DripstoneThickness.TIP_MERGE);
	}
	
	private void growStalagmiteBelow(final ServerLevel level, final BlockPos posAboveStalagmite) {
		MutableBlockPos pos = posAboveStalagmite.mutable();
		
		for (int i = 0; i < 10; i++) {
			pos.move(Direction.DOWN);
			BlockState state = level.getBlockState(pos);
			if (!state.getFluidState().isEmpty()) {
				return;
			}
			
			if (this.isUnmergedTipWithDirection(state, Direction.UP) && this.canTipGrow(state, level, pos)) {
				this.grow(level, pos, Direction.UP);
				return;
			}
			
			if (this.isValidSpeleothemPlacement(level, pos, Direction.UP) && !level.isWaterAt(pos.below())) {
				this.grow(level, pos.below(), Direction.UP);
				return;
			}
			
			if (this.blocksStalagmiteScan(level, pos, state)) {
				return;
			}
		}
	}
	
	protected boolean blocksStalagmiteScan(final LevelReader level, final BlockPos pos, final BlockState state) {
		return false;
	}
	
	protected static boolean isFreeHangingStalactite(final BlockState state) {
		return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !(Boolean)state.getValue(WATERLOGGED);
	}
	
	protected int getMaxGrowthLength() {
		return 7;
	}
}
