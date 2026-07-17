package net.regions_unexplored.block.type.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.util.RUUtils;

import java.util.function.BiFunction;

public class SegmentedBlock extends VegetationBlock {
	public static final MapCodec<? extends SegmentedBlock> CODEC = simpleCodec(SegmentedBlock::new);
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty AMOUNT = BlockStateProperties.FLOWER_AMOUNT;
	private static final BiFunction<Direction, Integer, VoxelShape> SHAPE_BY_PROPERTIES = RUUtils.memoize((direction, amount) -> {
		VoxelShape[] shapes = new VoxelShape[]{
			Block.box(8.0F, 0.0F, 8.0F, 16.0F, 3.0F, 16.0F),
			Block.box(8.0F, 0.0F, 0.0F, 16.0F, 3.0F, 8.0F),
			Block.box(0.0F, 0.0F, 0.0F, 8.0F, 3.0F, 8.0F),
			Block.box(0.0F, 0.0F, 8.0F, 8.0F, 3.0F, 16.0F)
		};
		VoxelShape mergedShapes = Shapes.empty();
		
		for(int i = 0; i < amount; ++i) {
			int index = Math.floorMod(i - direction.get2DDataValue(), 4);
			mergedShapes = Shapes.or(mergedShapes, shapes[index]);
		}
		
		return mergedShapes.singleEncompassing();
	});
	
	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}
	
	protected SegmentedBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FACING, Direction.NORTH)
			.setValue(AMOUNT, 1)
		);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_PROPERTIES.apply(state.getValue(FACING), state.getValue(AMOUNT));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.getStateForPlacement(context, this);
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext context, Block block) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.is(block)) {
			return state.setValue(AMOUNT, Math.min(4, state.getValue(AMOUNT) + 1));
		}
		return block.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Override
	public BlockState rotate(final BlockState state, final Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}
	
	@Override
	public BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
	
	@Override
	public boolean canBeReplaced(final BlockState state, final BlockPlaceContext context) {
		return (!context.isSecondaryUseActive() && context.getItemInHand().is(state.getBlock().asItem()) && state.getValue(AMOUNT) < 4) || super.canBeReplaced(state, context);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, AMOUNT);
	}
}
