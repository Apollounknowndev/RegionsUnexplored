package net.regions_unexplored.block.type.leaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.world.level.block.state.properties.RuBlockStateProperties;

public class HangingVinesBlock extends Block implements BonemealableBlock {
	public static final MapCodec<HangingVinesBlock> CODEC = simpleCodec(HangingVinesBlock::new);
	private static final VoxelShape SHAPE_BASE = RUBlockUtils.column(14.0, 0.0, 16.0);
	private static final VoxelShape SHAPE_TIP = RUBlockUtils.column(14.0, 6.0, 16.0);
	public static final BooleanProperty TIP = RuBlockStateProperties.TIP;
	
	@Override
	public MapCodec<HangingVinesBlock> codec() {
		return CODEC;
	}
	
	public HangingVinesBlock(final Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(TIP, true));
	}
	
	@Override
	protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		return state.getValue(TIP) ? SHAPE_TIP : SHAPE_BASE;
	}
	
	@Override
	protected boolean propagatesSkylightDown(final BlockState state, final BlockGetter level, final BlockPos pos) {
		return true;
	}
	
	@Override
	protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
		return this.canStayAtPosition(level, pos);
	}
	
	private boolean canStayAtPosition(final BlockGetter level, final BlockPos pos) {
		BlockPos neighbourPos = pos.relative(Direction.UP);
		BlockState blockState = level.getBlockState(neighbourPos);
		return MultifaceBlock.canAttachTo(level, Direction.UP, neighbourPos, blockState) || blockState.is(this);
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
		if (!this.canStayAtPosition(level, pos)) {
			level.scheduleTick(pos, this, 1);
		}
		
		return state.setValue(TIP, !level.getBlockState(pos.below()).is(this));
	}
	
	@Override
	protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
		if (!this.canStayAtPosition(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}
	
	@Override
	protected void createBlockStateDefinition(final Builder<Block, BlockState> builder) {
		builder.add(TIP);
	}
	
	@Override
	public boolean isValidBonemealTarget(final LevelReader level, final BlockPos pos, final BlockState state) {
		BlockPos growPos = this.getTip(level, pos).below();
		return this.canGrowInto(level.getBlockState(growPos)) && !level.isOutsideBuildHeight(growPos);
	}
	
	private boolean canGrowInto(final BlockState state) {
		return state.isAir();
	}
	
	public BlockPos getTip(final BlockGetter level, final BlockPos pos) {
		MutableBlockPos forwardPos = pos.mutable();
		
		BlockState forwardState;
		do {
			forwardPos.move(Direction.DOWN);
			forwardState = level.getBlockState(forwardPos);
		} while (forwardState.is(this));
		
		return forwardPos.relative(Direction.UP).immutable();
	}
	
	@Override
	public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state) {
		return true;
	}
	
	@Override
	public void performBonemeal(final ServerLevel level, final RandomSource random, final BlockPos pos, final BlockState state) {
		BlockPos tipPos = this.getTip(level, pos).below();
		if (this.canGrowInto(level.getBlockState(tipPos))) {
			level.setBlockAndUpdate(tipPos, state.setValue(TIP, true));
		}
	}
}