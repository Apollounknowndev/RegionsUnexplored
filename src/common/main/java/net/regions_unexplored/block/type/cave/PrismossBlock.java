package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.RUBlocks;

public class PrismossBlock extends Block implements BonemealableBlock {
   public PrismossBlock(Properties properties) {
      super(properties);
   }
   
   @Override
   public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
      return level.getBlockState(pos.above()).isAir();
   }
   
   @Override
   public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
      return true;
   }

   @Override
   public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
      for (BlockPos offsetPos : BlockPos.betweenClosed(pos.offset(-1, 0, -1), pos.offset(1, 0, 1))) {
         BlockState offsetState = level.getBlockState(offsetPos);
         if (random.nextBoolean() || !level.getBlockState(offsetPos.above()).isAir()) continue;
         
         boolean placedPrismoss = false;
         if (offsetState.is(BlockTags.STONE_ORE_REPLACEABLES)) {
            level.setBlock(offsetPos, RUBlocks.PRISMOSS.get().defaultBlockState(), 2);
            placedPrismoss = true;
         } else if (offsetState.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) {
            level.setBlock(offsetPos, RUBlocks.DEEPSLATE_PRISMOSS.get().defaultBlockState(), 2);
            placedPrismoss = true;
         }
         
         if (placedPrismoss && random.nextBoolean()) {
            level.setBlock(offsetPos.above(), RUBlocks.PRISMOSS_SPROUT.get().defaultBlockState(), 2);
         }
      }
   }
}
