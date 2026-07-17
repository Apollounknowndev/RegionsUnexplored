package net.regions_unexplored.block.type.food;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.RUItems;
import net.regions_unexplored.registry.data.RULootTables;

public class DuskmelonBlock extends VegetationBlock implements BonemealableBlock {
   public static final MapCodec<? extends DuskmelonBlock> CODEC = simpleCodec(DuskmelonBlock::new);
   public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
   private static final VoxelShape SAPLING_SHAPE = RUBlockUtils.column(10, 0, 8);
   private static final VoxelShape MID_GROWTH_SHAPE = RUBlockUtils.column(14, 0, 16);
   
   @Override
   protected MapCodec<? extends VegetationBlock> codec() {
      return CODEC;
   }

   public DuskmelonBlock(Properties properties) {
      super(properties);
      this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
   }

   @Override
   public ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state, final boolean includeData) {
      return new ItemStack(RUItems.DUSKMELON_SLICE.get());
   }

   @Override
   public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
      if (blockState.getValue(AGE) == 0) {
         return SAPLING_SHAPE;
      } else {
         return blockState.getValue(AGE) < 2 ? MID_GROWTH_SHAPE : super.getShape(blockState, blockGetter, blockPos, collisionContext);
      }
   }
   
   @Override
   public boolean isRandomlyTicking(BlockState blockState) {
      return blockState.getValue(AGE) < 2;
   }
   
   @Override
   public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
      int age = blockState.getValue(AGE);
      if (age < 2 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9) {
         BlockState blockstate = blockState.setValue(AGE, age + 1);
         serverLevel.setBlock(blockPos, blockstate, 2);
         serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockstate));
      }
   }
   
   @Override
   protected InteractionResult useWithoutItem(
       final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult
   ) {
      if (state.getValue(AGE) > 1) {
         if (level instanceof ServerLevel serverLevel) {
            Block.dropFromBlockInteractLootTable(
                serverLevel,
                RULootTables.HARVEST_DUSKMELON,
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
   
   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
      stateBuilder.add(AGE);
   }
   
   @Override
   public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
      return blockState.getValue(AGE) < 2;
   }

   @Override
   public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
      return true;
   }
   
   @Override
   public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
      int i = Math.min(3, blockState.getValue(AGE) + 1);
      serverLevel.setBlock(blockPos, blockState.setValue(AGE, i), 2);
   }
}
