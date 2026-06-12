package net.regions_unexplored.block.type.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.properties.RUBlockProperties;

public class PineLogBlock extends Block {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final BooleanProperty TRANSITION_BLOCK = RUBlockProperties.TRANSITION_BLOCK;
	public static final BooleanProperty IS_STRIPPED = RUBlockProperties.IS_STRIPPED;
	
	public PineLogBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(TRANSITION_BLOCK, false).setValue(IS_STRIPPED, false));
	}
	
	public BlockState rotate(BlockState state, Rotation rotation) {
		return switch (rotation) {
			case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
				case X -> state.setValue(AXIS, Direction.Axis.Z);
				case Z -> state.setValue(AXIS, Direction.Axis.X);
				default -> state;
			};
			default -> state;
		};
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos1) {
		BlockState belowState = level.getBlockState(pos.below());
		boolean isTransition = (
			!state.getValue(IS_STRIPPED) &&
             state.getValue(AXIS) == Direction.Axis.Y &&
             (belowState.is(RUBlocks.PINE_WOOD_SET.getStrippedLog()) ||
              belowState.getOptionalValue(PineLogBlock.IS_STRIPPED).orElse(false))
		);
		
		return state.setValue(TRANSITION_BLOCK, isTransition);
	}
	
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(AXIS, TRANSITION_BLOCK, IS_STRIPPED);
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState belowState = context.getLevel().getBlockState(context.getClickedPos().below());
		boolean isTransition = (
			context.getClickedFace().getAxis() == Direction.Axis.Y &&
             (belowState.is(RUBlocks.PINE_WOOD_SET.getStrippedLog()) ||
              belowState.getOptionalValue(PineLogBlock.IS_STRIPPED).orElse(false))
		);
		
		return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(TRANSITION_BLOCK, isTransition);
	}
}