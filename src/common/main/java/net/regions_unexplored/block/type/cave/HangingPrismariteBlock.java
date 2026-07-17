package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.block.properties.type.HangingPrismariteShape;
import net.regions_unexplored.block.properties.RUBlockProperties;

import java.util.Optional;

public class HangingPrismariteBlock extends Block {
    public static final EnumProperty<HangingPrismariteShape> HANGING_PRISMARITE_SHAPE = RUBlockProperties.HANGING_PRISMARITE_SHAPE;
    protected static final VoxelShape SHAPE = RUBlockUtils.column(8, 0, 16);

    public HangingPrismariteBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HANGING_PRISMARITE_SHAPE, HangingPrismariteShape.TIP));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HANGING_PRISMARITE_SHAPE);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        return aboveState.isFaceSturdy(level, pos, Direction.DOWN) || aboveState.is(RUBlocks.HANGING_PRISMARITE.get());
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
        HangingPrismariteShape shape = state.getValue(HANGING_PRISMARITE_SHAPE);
        BlockState belowState = level.getBlockState(pos.below());
        Optional<HangingPrismariteShape> belowShape = belowState.getOptionalValue(HANGING_PRISMARITE_SHAPE);
        if (belowShape.isPresent()) {
            if (belowShape.get() == HangingPrismariteShape.TIP) {
                shape = HangingPrismariteShape.FRUSTUM;
            } else if (belowShape.get() == HangingPrismariteShape.FRUSTUM) {
                shape = HangingPrismariteShape.COLUMN;
            }
        } else {
            shape = HangingPrismariteShape.TIP;
        }
        if (!this.canSurvive(state, level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state.setValue(HANGING_PRISMARITE_SHAPE, shape);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() >= (0.15 * RUConfigHandler.CLIENT.particleRates.prismarite)) return;

        int plantX = pos.getX();
        int plantY = pos.getY();
        int plantZ = pos.getZ();

        BlockPos.MutableBlockPos ambientPos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < 3; ++i) {
            ambientPos.set(plantX + Mth.nextInt(random, -2, 2), plantY + Mth.nextInt(random, -2, 2), plantZ + Mth.nextInt(random, -2, 2));
            BlockState particlePosState = level.getBlockState(ambientPos);
            if (particlePosState.isCollisionShapeFullBlock(level, ambientPos)) continue;

            level.addParticle(RUParticleTypes.PRISMARITE_SPARKLE.get(), (double)ambientPos.getX() + random.nextDouble(), (double)ambientPos.getY() + random.nextDouble(), (double)ambientPos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}

