package net.regions_unexplored.block.type.dirt;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;

import javax.annotation.Nullable;

public class RUDirtPathBlock extends Block {
    private static final VoxelShape SHAPE = RUBlockUtils.column(16.0, 0.0, 15.0);
    public static final MapCodec<RUDirtPathBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        ResourceKey.codec(Registries.BLOCK).fieldOf("base_block").forGetter(block -> block.baseBlock),
        propertiesCodec()
    ).apply(i, RUDirtPathBlock::new));
    
    private final ResourceKey<Block> baseBlock;
    
    @Override
    public MapCodec<RUDirtPathBlock> codec() {
        return CODEC;
    }
    
    public RUDirtPathBlock(ResourceKey<Block> baseBlock, Properties properties) {
        super(properties);
        this.baseBlock = baseBlock;
    }
    
    @Override
    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }
    
    @Override
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        if (this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos())) {
            return super.getStateForPlacement(context);
        }
        return Block.pushEntitiesUp(this.defaultBlockState(), this.getBaseBlock(context.getLevel()), context.getLevel(), context.getClickedPos());
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
        if (directionToNeighbour == Direction.UP && !state.canSurvive(level, pos)) {
            ticks.scheduleTick(pos, this, 1);
        }
        
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }
    
    @Override
    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        turnToDirt(null, state, level, pos);
    }
    
    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isSolid() || aboveState.getBlock() instanceof FenceGateBlock;
    }
    
    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        return false;
    }
    
    protected void turnToDirt(@Nullable Entity sourceEntity, BlockState state, Level level, BlockPos pos) {
        BlockState newState = pushEntitiesUp(state, this.getBaseBlock(level), level, pos);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
    }
    
    protected BlockState getBaseBlock(Level level) {
        return level.registryAccess().lookupOrThrow(Registries.BLOCK).getValue(this.baseBlock).defaultBlockState();
    }
}