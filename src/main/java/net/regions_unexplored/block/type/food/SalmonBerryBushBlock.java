package net.regions_unexplored.block.type.food;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.regions_unexplored.registry.RUItems;

public class SalmonBerryBushBlock extends SweetBerryBushBlock {
   public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

   public SalmonBerryBushBlock(Properties properties) {
      super(properties);
      this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
   }

   @Override
   public ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
      return new ItemStack(RUItems.SALMONBERRY.get());
   }
   
   @Override
   protected InteractionResult useWithoutItem(
       final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult
   ) {
      int age = state.getValue(AGE);
      if (age > 1) {
         if (level instanceof ServerLevel serverLevel) {
            int count = 1 + level.getRandom().nextInt(2);
            popResource(level, pos, new ItemStack(RUItems.SALMONBERRY.get(), count + (age == 3 ? 1 : 0)));
            serverLevel.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + serverLevel.getRandom().nextFloat() * 0.4F);
            BlockState newState = state.setValue(AGE, 1);
            serverLevel.setBlock(pos, newState, 2);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
         }
         return InteractionResult.sidedSuccess(level.isClientSide);
      } else {
         return super.useWithoutItem(state, level, pos, player, hitResult);
      }
   }
}
