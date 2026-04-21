package net.regions_unexplored.block.type.dirt;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;

import javax.annotation.Nullable;

public class RUFarmlandBlock extends Block {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    private static final VoxelShape SHAPE = RUBlockUtils.column(16.0, 0.0, 15.0);
    public static final MapCodec<RUFarmlandBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        ResourceKey.codec(Registries.BLOCK).fieldOf("base_block").forGetter(block -> block.baseBlock),
        propertiesCodec()
    ).apply(i, RUFarmlandBlock::new));
    
    private final ResourceKey<Block> baseBlock;
    
    @Override
    public MapCodec<RUFarmlandBlock> codec() {
        return CODEC;
    }
    
    public RUFarmlandBlock(ResourceKey<Block> baseBlock, Properties properties) {
        super(properties);
        this.baseBlock = baseBlock;
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
    }
    
    @Override
    protected BlockState updateShape(
        BlockState state,
        Direction directionToNeighbour,
        BlockState neighbourState,
        LevelAccessor level,
        BlockPos pos,
        BlockPos neighbourPos
    ) {
        if (directionToNeighbour == Direction.UP && !state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        
        return super.updateShape(state, directionToNeighbour, neighbourState, level, pos, neighbourPos);
    }
        
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isSolid() || shouldMaintainFarmland(level, pos);
    }
        
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos())) {
            return super.getStateForPlacement(context);
        }
        return this.getBaseBlock(context.getLevel());
    }
        
    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
        
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
        
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToDirt(null, state, level, pos);
        }
    }
        
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!shouldMaintainFarmland(level, pos)) {
                turnToDirt(null, state, level, pos);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }
        
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (level instanceof ServerLevel serverLevel
            && level.getRandom().nextFloat() < fallDistance - 0.5
            && entity instanceof LivingEntity
            && (entity instanceof Player || serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
            && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
            turnToDirt(entity, state, level, pos);
        }
        
        super.fallOn(level, state, pos, entity, fallDistance);
    }
        
    public void turnToDirt(@Nullable Entity sourceEntity, BlockState state, Level level, BlockPos pos) {
        BlockState newState = pushEntitiesUp(state, this.getBaseBlock(level), level, pos);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
    }
        
    private static boolean shouldMaintainFarmland(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }
        
    private static boolean isNearWater(LevelReader level, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                return true;
            }
        }
        
        return false;
    }
    
    protected BlockState getBaseBlock(Level level) {
        return level.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(this.baseBlock).defaultBlockState();
    }
        
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }
        
    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
