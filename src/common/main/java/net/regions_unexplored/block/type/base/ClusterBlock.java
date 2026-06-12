package net.regions_unexplored.block.type.base;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ClusterBlock extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<ClusterBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Codec.FLOAT.fieldOf("height").forGetter(b -> b.height),
		Codec.FLOAT.fieldOf("aabb_offset").forGetter(b -> b.aabbOffset),
		propertiesCodec()
	).apply(i, ClusterBlock::new));
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
	private final float height;
	private final float aabbOffset;
	private final Map<Direction, VoxelShape> shapes;
	
	@Override
	public MapCodec<ClusterBlock> codec() {
		return CODEC;
	}
	
	public ClusterBlock(final float height, final float aabbOffset, final Properties props) {
		super(props);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.UP));
		this.height = height;
		this.aabbOffset = aabbOffset;
		this.shapes = Map.of(
			Direction.UP, Block.box(aabbOffset, 0.0F, aabbOffset, (16.0F - aabbOffset), height, (16.0F - aabbOffset)),
			Direction.DOWN, Block.box(aabbOffset, (16.0F - height), aabbOffset, (16.0F - aabbOffset), 16.0F, (16.0F - aabbOffset)),
			Direction.NORTH, Block.box(aabbOffset, aabbOffset, (16.0F - height), (16.0F - aabbOffset), (16.0F - aabbOffset), 16.0F),
			Direction.SOUTH, Block.box(aabbOffset, aabbOffset, 0.0F, (16.0F - aabbOffset), (16.0F - aabbOffset), height),
			Direction.EAST, Block.box(0.0F, aabbOffset, aabbOffset, height, (16.0F - aabbOffset), (16.0F - aabbOffset)),
			Direction.WEST, Block.box((16.0F - height), aabbOffset, aabbOffset, 16.0F, (16.0F - aabbOffset), (16.0F - aabbOffset))
		);
	}
	
	@Override
	protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
		Direction facing = state.getValue(FACING);
		VoxelShape baseShape = this.shapes.get(facing);
		if (facing != Direction.DOWN) return baseShape;
		
		Vec3 offset = state.getOffset(level, pos);
		return baseShape.move(
			offset.x,
			0,
			offset.z
		);
	}
	
	@Override
	protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos adjacentPos = pos.relative(direction.getOpposite());
		return level.getBlockState(adjacentPos).isFaceSturdy(level, adjacentPos, direction);
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
		
		return directionToNeighbour == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, directionToNeighbour, neighbourState, level, pos, neighbourPos);
	}
	
	@Override
	public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
		LevelAccessor level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		return this.defaultBlockState()
			.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER))
			.setValue(FACING, context.getClickedFace())
		;
	}
	
	@Override
	protected BlockState rotate(final BlockState state, final Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}
	
	@Override
	protected BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
	
	@Override
	protected FluidState getFluidState(final BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	protected void createBlockStateDefinition(final Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, FACING);
	}
}