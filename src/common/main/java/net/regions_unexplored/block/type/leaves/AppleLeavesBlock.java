package net.regions_unexplored.block.type.leaves;

import dev.worldgen.lithostitched.api.util.WeightedList;
import dev.worldgen.lithostitched.api.worldgen.stateprovider.LithostitchedStateProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.registry.data.RULootTables;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public class AppleLeavesBlock extends RUTintedParticlesLeavesBlock implements BonemealableBlock{
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public AppleLeavesBlock(Properties properties) {
        super(properties, RUParticleTypes.STANDARD_LEAVES, TintGetter.DEFAULT, RUTintedParticlesLeavesBlock.DEFAULT_PARTICLE_CHANCE);
        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(DISTANCE, 7)
                .setValue(PERSISTENT, false)
                .setValue(WATERLOGGED, false)
                .setValue(AGE, 0)
        );
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if(!this.decaying(state)) {
            if (i < 4 && level.getRawBrightness(pos.above(), 0) >= 9) {
                BlockState blockstate = state.setValue(AGE, i + 1);
                level.setBlock(pos, blockstate, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            }
        }
        else {
            if (state.getValue(AGE) == 4) {
                popResourceFromFace(level, pos, Direction.DOWN, new ItemStack(Items.APPLE, 1));
            }
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }
    
    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {
    
    }
    
    @Override
    protected InteractionResult useItemOn(
        final ItemStack itemStack,
        final BlockState state,
        final Level level,
        final BlockPos pos,
        final Player player,
        final InteractionHand hand,
        final BlockHitResult hitResult
    ) {
        int age = state.getValue(AGE);
        boolean isMaxAge = age == 4;
        return (!isMaxAge && itemStack.is(Items.BONE_MEAL)
            ? InteractionResult.PASS
            : super.useItemOn(itemStack, state, level, pos, player, hand, hitResult));
    }
    
    @Override
    protected InteractionResult useWithoutItem(
        final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult
    ) {
        if (state.getValue(AGE) > 3) {
            if (level instanceof ServerLevel serverLevel) {
                Block.dropFromBlockInteractLootTable(
                    serverLevel,
                    RULootTables.HARVEST_APPLE_OAK_LEAVES,
                    state,
                    level.getBlockEntity(pos),
                    null,
                    player,
                    (serverlvl, itemStack) -> Block.popResource(serverlvl, pos, itemStack)
                );
                serverLevel.playSound(
                    null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + serverLevel.getRandom().nextFloat() * 0.4F
                );
                BlockState newState = state.setValue(AGE, 0);
                serverLevel.setBlock(pos, newState, 2);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            }
            
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(DISTANCE, PERSISTENT, WATERLOGGED, AGE);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean b) {
        return state.getValue(AGE) < 4;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(AGE) < 4;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int i = Math.min(4, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
    }

    public static BlockStateProvider createStateProvider(int normalWeight) {
        return LithostitchedStateProviders.weighted(WeightedList.<BlockStateProvider>builder()
            .add(simple(Blocks.OAK_LEAVES), normalWeight)
            .add(new RandomizedIntStateProvider(simple(RUBlocks.APPLE_OAK_NATURAL_SET.getLeaves()), AppleLeavesBlock.AGE, UniformInt.of(2, 4)), 1)
        .build());
    }
}