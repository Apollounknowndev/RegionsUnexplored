package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.data.RUDamageTypes;
import net.regions_unexplored.block.properties.RUBlockProperties;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DusktrapBlock extends DoublePlantBlock {
    public static final BooleanProperty CLOSED = RUBlockProperties.CLOSED;
    protected static final VoxelShape CLOSED_SHAPE = RUBlockUtils.column(12, 0, 16);
    protected static final VoxelShape OPEN_SHAPE = RUBlockUtils.column(14, 0, 16);
    protected static final AABB TOUCH_AABB = RUBlockUtils.column(4, 0, 16).toAabbs().getFirst();
    protected static final Vec3 STUCK_SPEED = new Vec3(0.25, 0.25, 0.25);

    public DusktrapBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(CLOSED, false)
            .setValue(HALF, DoubleBlockHalf.LOWER)
        );
    }
    
    public boolean isClosed(BlockState state) {
        return state.getValue(CLOSED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return state.getValue(CLOSED) ? CLOSED_SHAPE : OPEN_SHAPE;
    }
    
    @Override
    public boolean isPossibleToRespawnInThis(BlockState blockState) {
        return false;
    }
    
    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        int modifier = level instanceof Level && isEntityInside((Level) level, pos, player) ? 4 : 1;
        return super.getDestroyProgress(state, player, level, pos) / modifier;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.isClosed(state)) {
            this.checkClosed(null, level, pos, state, true);
        }
    }
    
    @Override
    protected void entityInside(
	    final BlockState state, final Level level, final BlockPos pos, final Entity entity, final InsideBlockEffectApplier effectApplier, final boolean isPrecise
    ) {
        if (isEntityInside(level, pos, entity)) {
            if (!(entity instanceof ItemEntity)) {
                entity.makeStuckInBlock(state, STUCK_SPEED);
            }
            if (entity instanceof LivingEntity && !level.isClientSide()) {
                entity.hurt(level.damageSources().source(RUDamageTypes.DUSK_TRAP), 3f);
            }
        }
        if (!level.isClientSide()) {
            if (!this.isClosed(state)) {
                this.checkClosed(entity, level, pos, state, false);
            }
        }
    }
    
    private void checkClosed(final @Nullable Entity sourceEntity, final Level level, final BlockPos pos, final BlockState state, boolean wasClosed) {
	    boolean isClosed = this.anyEntitiesInside(level, pos);
        if (wasClosed != isClosed) {
            BlockState newState = this.setSignalForState(state, isClosed);
            
            if (newState.getValue(HALF) == DoubleBlockHalf.LOWER) {
                level.setBlock(pos.above(), newState.setValue(HALF, DoubleBlockHalf.UPPER), 2);
            }
            else if (newState.getValue(HALF) == DoubleBlockHalf.UPPER) {
                level.setBlock(pos.below(), newState.setValue(HALF, DoubleBlockHalf.LOWER), 2);
            }
            level.setBlock(pos, newState, 2);
            
            this.updateNeighbours(level, pos);
            level.setBlocksDirty(pos, state, newState);
        }
        
        if (!isClosed && wasClosed) {
            level.playSound(null, pos, SoundType.TWISTING_VINES.getBreakSound(), SoundSource.BLOCKS);
            level.gameEvent(sourceEntity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (isClosed && !wasClosed) {
            level.playSound(null, pos, SoundType.TWISTING_VINES.getPlaceSound(), SoundSource.BLOCKS);
            level.gameEvent(sourceEntity, GameEvent.BLOCK_ACTIVATE, pos);
        }
        
        if (isClosed) {
            level.scheduleTick(new BlockPos(pos), this, 5);
        }
    }

    protected void updateNeighbours(Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }
    
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender();
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CLOSED).add(HALF);
    }
    
    protected boolean isEntityInside(Level level, BlockPos pos, Entity entity) {
        return getEntitiesInside(level, pos).contains(entity);
    }
    
    protected boolean anyEntitiesInside(Level level, BlockPos pos) {
        return !getEntitiesInside(level, pos).isEmpty();
    }
    
    private List<Entity> getEntitiesInside(Level level, BlockPos pos) {
        return level.getEntitiesOfClass(Entity.class, TOUCH_AABB.move(pos), EntitySelector.NO_SPECTATORS.and((e) -> !e.isIgnoringBlockTriggers()));
    }
    
    protected BlockState setSignalForState(BlockState blockState, boolean closed) {
        return blockState.setValue(CLOSED, closed);
    }
}
