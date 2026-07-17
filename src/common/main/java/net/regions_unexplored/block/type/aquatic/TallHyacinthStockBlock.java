package net.regions_unexplored.block.type.aquatic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.block.properties.RUBlockProperties;
import net.regions_unexplored.block.properties.type.TallHyacinthStockShape;

public class TallHyacinthStockBlock extends Block implements LiquidBlockContainer {
    public static final EnumProperty<TallHyacinthStockShape> TALL_HYACINTH_STOCK_SHAPE = RUBlockProperties.TALL_HYACINTH_STOCK_SHAPE;
    protected static final VoxelShape SHAPE = RUBlockUtils.column(8, 0, 16);

    public TallHyacinthStockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TALL_HYACINTH_STOCK_SHAPE, TallHyacinthStockShape.BASE_TIP));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(TALL_HYACINTH_STOCK_SHAPE);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos);
        if (fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            return belowState.isFaceSturdy(level, belowPos, Direction.UP) || belowState.is(RUBlocks.TALL_HYACINTH_STOCK.get());
        }
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState belowState = context.getLevel().getBlockState(context.getClickedPos().below());
        TallHyacinthStockShape shape = belowState.is(RUBlocks.TALL_HYACINTH_STOCK.get()) ? TallHyacinthStockShape.TIP : TallHyacinthStockShape.BASE_TIP;
        return this.defaultBlockState().setValue(TALL_HYACINTH_STOCK_SHAPE, shape);
    }

    @Override
    protected BlockState updateShape(
        BlockState state,
        LevelReader level,
        ScheduledTickAccess ticks,
        BlockPos pos,
        Direction directionToNeighbour,
        BlockPos neighbourPos,
        BlockState neighbourState,
        RandomSource random
    ) {
        TallHyacinthStockShape shape = state.getValue(TALL_HYACINTH_STOCK_SHAPE);
        BlockState belowState = level.getBlockState(pos.below());
        BlockState aboveState = level.getBlockState(pos.above());
        
        if (!belowState.is(RUBlocks.TALL_HYACINTH_STOCK.get())) {
            if (!aboveState.is(RUBlocks.TALL_HYACINTH_STOCK.get())) {
                shape = TallHyacinthStockShape.BASE_TIP;
            } else if (aboveState.getValue(TALL_HYACINTH_STOCK_SHAPE) == TallHyacinthStockShape.TIP) {
                shape = TallHyacinthStockShape.BASE_FRUSTUM;
            } else {
                shape = TallHyacinthStockShape.BASE;
            }
        } else {
            if (!aboveState.is(RUBlocks.TALL_HYACINTH_STOCK.get())) {
                shape = TallHyacinthStockShape.TIP;
            } else if (aboveState.getValue(TALL_HYACINTH_STOCK_SHAPE) == TallHyacinthStockShape.TIP) {
                shape = TallHyacinthStockShape.FRUSTUM;
            } else if (aboveState.getValue(TALL_HYACINTH_STOCK_SHAPE) == TallHyacinthStockShape.FRUSTUM) {
                shape = TallHyacinthStockShape.MIDDLE;
            }
        }

        if (!this.canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        
        return state.setValue(TALL_HYACINTH_STOCK_SHAPE, shape);
    }
    
    @Override
    protected FluidState getFluidState(final BlockState state) {
        return Fluids.WATER.getSource(false);
    }
    
    @Override
    public boolean canPlaceLiquid(LivingEntity livingEntity, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return false;
    }
    
    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() >= 0.0667) return;

        int plantX = pos.getX();
        int plantY = pos.getY();
        int plantZ = pos.getZ();

        BlockPos ambientPos = new BlockPos(plantX + Mth.nextInt(random, -2, 2), plantY + random.nextInt(3), plantZ + Mth.nextInt(random, -2, 2));
        BlockState particlePosState = level.getBlockState(ambientPos);
        if (particlePosState.isCollisionShapeFullBlock(level, ambientPos)) return;

        ColorParticleOption particle = ColorParticleOption.create(RUParticleTypes.FLOATING_HYACINTH.get(), 0xCCDDFF);
        level.addParticle(particle, (double)ambientPos.getX() + random.nextDouble(), (double)ambientPos.getY() + random.nextDouble(), (double)ambientPos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
    }
}

