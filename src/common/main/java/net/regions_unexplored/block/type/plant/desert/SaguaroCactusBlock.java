package net.regions_unexplored.block.type.plant.desert;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.regions_unexplored.block.properties.RUBlockProperties;
import net.regions_unexplored.block.properties.type.SaguaroCactusShape;

public class SaguaroCactusBlock extends Block {
    public static final EnumProperty<SaguaroCactusShape> SHAPE = RUBlockProperties.CACTUS_SHAPE;

    public SaguaroCactusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SHAPE, SaguaroCactusShape.UP_DOWN));
    }

    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(SHAPE);
    }

    @Override
    public BlockState updateShape(
        final BlockState state,
        final Direction directionToNeighbour,
        final BlockState neighbourState,
        final LevelAccessor level,
        final BlockPos pos,
        final BlockPos neighbourPos
    ) {
        SaguaroCactusShape shape = state.getValue(SHAPE);

        boolean verticalCactusAbove = isCactus(level, pos.above(), SaguaroCactusShape.UP_DOWN);
        boolean verticalCactusBelow = isCactus(level, pos.below(), SaguaroCactusShape.UP_DOWN);
        
        if (verticalCactusAbove) {
            if (!verticalCactusBelow) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (state.getValue(SHAPE) == SaguaroCactusShape.getUp(direction) && !isCactus(level, pos.relative(direction), SaguaroCactusShape.getAxisAligned(direction.getAxis()))) {
                        for (Direction offset : Direction.Plane.HORIZONTAL) {
                            if (offset == direction) continue;
                            if (isCactus(level, pos.relative(offset), SaguaroCactusShape.getAxisAligned(offset.getAxis()))) {
                                shape = SaguaroCactusShape.getUp(offset);
                            }
                        }
                    }
                }
            }
            
            if (
                !isCactus(level, pos.north(), SaguaroCactusShape.NORTH_SOUTH) &&
                !isCactus(level, pos.south(), SaguaroCactusShape.NORTH_SOUTH) &&
                !isCactus(level, pos.east(), SaguaroCactusShape.EAST_WEST) &&
                !isCactus(level, pos.west(), SaguaroCactusShape.EAST_WEST)
            ) {
                shape = SaguaroCactusShape.UP_DOWN;
            }
            
            if (!verticalCactusBelow && !shape.isTurn()) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (isCactusAxisAligned(level, pos, direction)) {
                        shape = SaguaroCactusShape.getUp(direction);
                    }
                }
            }
        } else {
            if (shape == SaguaroCactusShape.NORTH_UP || shape == SaguaroCactusShape.SOUTH_UP) {
                shape = SaguaroCactusShape.NORTH_SOUTH;
            }
            else if (shape == SaguaroCactusShape.EAST_UP || shape == SaguaroCactusShape.WEST_UP) {
                shape = SaguaroCactusShape.EAST_WEST;
            }
        }
        
        return state.setValue(SHAPE, shape);
    }
    
    private boolean isCactus(LevelAccessor level, BlockPos pos, SaguaroCactusShape targetShape) {
        var shape = level.getBlockState(pos).getOptionalValue(SHAPE);
        return shape.isPresent() && shape.get() == targetShape;
    }
    
    private boolean isCactusAxisAligned(LevelAccessor level, BlockPos pos, Direction direction) {
        var shape = level.getBlockState(pos.relative(direction)).getOptionalValue(SHAPE);
        return shape.isPresent() && shape.get() == SaguaroCactusShape.getAxisAligned(direction.getAxis());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        if (level.getBlockState(pos.below()) != this.defaultBlockState() && level.getBlockState(pos.above()) == this.defaultBlockState()) {
            return this.defaultBlockState().setValue(SHAPE, SaguaroCactusShape.getUp(direction.getOpposite()));
        }
        return this.defaultBlockState().setValue(SHAPE, SaguaroCactusShape.getAxisAligned(direction.getAxis()));
    }
}