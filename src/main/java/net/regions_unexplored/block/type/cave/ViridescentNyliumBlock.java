package net.regions_unexplored.block.type.cave;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUParticleTypes;

public class ViridescentNyliumBlock extends Block implements BonemealableBlock {
   public ViridescentNyliumBlock(Properties properties) {
      super(properties);
   }

   @Override
   public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
      super.animateTick(state, level, pos, random);
      if (random.nextInt(10) == 0) {
         level.addParticle(RUParticleTypes.GROUND_SPORE.get(), (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.1, (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
      }
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
         
         boolean placedNylium = false;
         if (offsetState.is(BlockTags.STONE_ORE_REPLACEABLES)) {
            level.setBlock(offsetPos, RUBlocks.VIRIDESCENT_NYLIUM.get().defaultBlockState(), 2);
            placedNylium = true;
         } else if (offsetState.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) {
            level.setBlock(offsetPos, RUBlocks.DEEPSLATE_VIRIDESCENT_NYLIUM.get().defaultBlockState(), 2);
            placedNylium = true;
         }
         
         if (placedNylium && random.nextBoolean()) {
            level.setBlock(offsetPos.above(), Blocks.SHORT_GRASS.defaultBlockState(), 2);
         }
      }
   }
}