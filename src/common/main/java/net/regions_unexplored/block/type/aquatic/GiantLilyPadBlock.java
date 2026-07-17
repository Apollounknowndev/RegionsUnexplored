package net.regions_unexplored.block.type.aquatic;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;

public class GiantLilyPadBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<? extends GiantLilyPadBlock> CODEC = simpleCodec(GiantLilyPadBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    
    protected static final VoxelShape NORTH = Block.box(0, 0, 2, 14, 2, 16);
    protected static final VoxelShape EAST = Block.box(0, 0, 0, 14, 2, 14);
    protected static final VoxelShape SOUTH = Block.box(2, 0, 0, 16, 2, 14);
    protected static final VoxelShape WEST = Block.box(2, 0, 2, 16, 2, 16);

    public GiantLilyPadBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state, final boolean includeData) {
        return new ItemStack(RUBlocks.FLOWERING_LILY_PAD.get());
    }

    @Override
    public void fallOn(final Level level, final BlockState state, final BlockPos pos, final Entity entity, final double fallDistance) {
        if(entity.getType() != EntityType.FROG) {
            if (entity.isSuppressingBounce()) {
                super.fallOn(level, state, pos, entity, fallDistance);
            } else {
                entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
            }
        }
    }

    @Override
    public void updateEntityMovementAfterFallOn(final BlockGetter level, final Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < -0.7D) {
            if(entity.getType() != EntityType.FROG) {
                if (entity.isSuppressingBounce()) {
                    super.updateEntityMovementAfterFallOn(level, entity);
                } else {
                    this.bounceUp(entity);
                }
            }
        }
        else{
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1, 0, 1));
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1, 0, 1));
        }
    }

    private void bounceUp(Entity entity) {
        if(entity.getType() != EntityType.FROG) {
            Vec3 vec3 = entity.getDeltaMovement();
            if (vec3.y < 0) {
                double d0 = entity instanceof LivingEntity ? 1 : 0.8D;
                entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
            }
        }
    }

    public VoxelShape getShape(BlockState state, BlockGetter block, BlockPos pos, CollisionContext context) {
        if(state== this.defaultBlockState().setValue(FACING, Direction.NORTH)){
            return NORTH;
        }
        else if(state== this.defaultBlockState().setValue(FACING, Direction.EAST)){
            return EAST;
        }
        else if(state== this.defaultBlockState().setValue(FACING, Direction.SOUTH)){
            return SOUTH;
        }
        else{
            return WEST;
        }
    }
    
    @Override
    protected BlockState updateShape(
        final BlockState state,
        final LevelReader level,
        final ScheduledTickAccess ticks,
        final BlockPos pos,
        final Direction directionToNeighbour,
        final BlockPos neighbourPos,
        final BlockState neighbourState,
        final RandomSource random
    ) {
        return !state.canSurvive(level, pos)
            ? Blocks.AIR.defaultBlockState()
            : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (level.getFluidState(pos.below()).is(Fluids.WATER)) {
            if (state == this.defaultBlockState().setValue(FACING, Direction.NORTH)) {
                return
                    level.getBlockState(pos.west()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST) &
                    level.getBlockState(pos.south().west()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH) &
                    level.getBlockState(pos.south()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST);
            } else if (state == this.defaultBlockState().setValue(FACING, Direction.EAST)) {
                return
                    level.getBlockState(pos.north()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH) &
                    level.getBlockState(pos.west()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH) &
                    level.getBlockState(pos.west().north()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST);
            } else if (state == this.defaultBlockState().setValue(FACING, Direction.SOUTH)) {
                return
                    level.getBlockState(pos.north().east()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH) &
                    level.getBlockState(pos.east()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST) &
                    level.getBlockState(pos.north()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST);
            } else {
                return
                    level.getBlockState(pos.east()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH) &
                    level.getBlockState(pos.south().east()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST) &
                    level.getBlockState(pos.south()) == RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH);
            }
        }
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection().getOpposite();

        if(context.getHorizontalDirection().getOpposite()==Direction.NORTH){
            if(!level.isClientSide()){
                level.setBlock(pos.west(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST), 20);
                level.setBlock(pos.south().west(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 20);
                level.setBlock(pos.south(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 20);
                level.updateNeighborsAt(pos.west(), Blocks.AIR);
                level.updateNeighborsAt(pos.south().west(), Blocks.AIR);
                level.updateNeighborsAt(pos.south(), Blocks.AIR);
            }
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        else if(context.getHorizontalDirection().getOpposite()==Direction.EAST){
            if(!level.isClientSide()){
                level.setBlock(pos.north(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 20);
                level.setBlock(pos.west(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 20);
                level.setBlock(pos.west().north(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST), 20);
            }
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        else if(context.getHorizontalDirection().getOpposite()==Direction.SOUTH){
            if(!level.isClientSide()){
                level.setBlock(pos.north().east(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 20);
                level.setBlock(pos.east(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 20);
                level.setBlock(pos.north(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.WEST), 20);
            }
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
        else{
            if(!level.isClientSide()){
                level.setBlock(pos.east(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 20);
                level.setBlock(pos.south().east(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 20);
                level.setBlock(pos.south(), this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 20);
            }
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state) {
        return false;
    }
    
    public static boolean tryPlace(CommonLevelAccessor level, BlockPos pos, RandomSource random) {
        for (Direction direction : Direction.Plane.HORIZONTAL.shuffledCopy(random)) {
            BlockPos belowPos = pos.below();
            if (
                level.getBlockState(pos.relative(direction)).isAir() &&
                level.getBlockState(pos.relative(direction.getClockWise())).isAir() &&
                level.getBlockState(pos.relative(direction.getClockWise()).relative(direction)).isAir() &&
                level.getFluidState(belowPos).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction)).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction.getClockWise())).is(Fluids.WATER) &&
                level.getFluidState(belowPos.relative(direction.getClockWise()).relative(direction)).is(Fluids.WATER)
            ) {
                level.setBlock(pos, GiantLilyPadBlock.getState(Direction.SOUTH, direction), 27);
                level.setBlock(pos.relative(direction), GiantLilyPadBlock.getState(Direction.WEST, direction), 27);
                level.setBlock(pos.relative(direction.getClockWise()), GiantLilyPadBlock.getState(Direction.EAST, direction), 27);
                level.setBlock(pos.relative(direction.getClockWise()).relative(direction), GiantLilyPadBlock.getState(Direction.NORTH, direction), 27);
                return true;
            }
        }
        return false;
    }
    
    public static BlockState getState(Direction facing, Direction rotation) {
        facing = switch (rotation) {
            case NORTH, UP, DOWN -> facing;
            case EAST -> facing.getClockWise();
            case SOUTH -> facing.getOpposite();
            case WEST -> facing.getCounterClockWise();
        };
        
        return RUBlocks.GIANT_LILY_PAD.get().defaultBlockState().setValue(FACING, facing);
    }
}
