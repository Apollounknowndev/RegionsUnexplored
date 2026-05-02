package net.regions_unexplored.world.level.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;

import java.util.List;

public class AirMultifaceGrowthFeature extends Feature<MultifaceGrowthConfiguration> {
   public AirMultifaceGrowthFeature(Codec<MultifaceGrowthConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext<MultifaceGrowthConfiguration> context) {
      WorldGenLevel level = context.level();
      BlockPos origin = context.origin();
      RandomSource random = context.random();
      MultifaceGrowthConfiguration config = context.config();
      if (!isAir(level.getBlockState(origin))) {
         return false;
      } else {
         List<Direction> list = config.getShuffledDirections(random);
         if (placeGrowthIfPossible(level, origin, level.getBlockState(origin), config, random, list)) {
            return true;
         } else {
            BlockPos.MutableBlockPos pos = origin.mutable();

            for(Direction direction : list) {
               pos.set(origin);
               List<Direction> list1 = config.getShuffledDirectionsExcept(random, direction.getOpposite());

               for(int i = 0; i < config.searchRange; ++i) {
                  pos.setWithOffset(origin, direction);
                  BlockState blockstate = level.getBlockState(pos);
                  if (!isAir(blockstate) && !blockstate.is(config.placeBlock)) {
                     break;
                  }

                  if (placeGrowthIfPossible(level, pos, blockstate, config, random, list1)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   public static boolean placeGrowthIfPossible(WorldGenLevel p_225158_, BlockPos p_225159_, BlockState p_225160_, MultifaceGrowthConfiguration p_225161_, RandomSource p_225162_, List<Direction> p_225163_) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = p_225159_.mutable();

      for(Direction direction : p_225163_) {
         BlockState blockstate = p_225158_.getBlockState(blockpos$mutableblockpos.setWithOffset(p_225159_, direction));
         if (blockstate.is(p_225161_.canBePlacedOn)) {
            BlockState blockstate1 = p_225161_.placeBlock.getStateForPlacement(p_225160_, p_225158_, p_225159_, direction);
            if (blockstate1 == null) {
               return false;
            }

            p_225158_.setBlock(p_225159_, blockstate1, 3);
            p_225158_.getChunk(p_225159_).markPosForPostprocessing(p_225159_);
            if (p_225162_.nextFloat() < p_225161_.chanceOfSpreading) {
               p_225161_.placeBlock.getSpreader().spreadFromFaceTowardRandomDirection(blockstate1, p_225158_, p_225159_, direction, p_225162_, true);
            }

            return true;
         }
      }

      return false;
   }

   private static boolean isAir(BlockState p_225167_) {
      return p_225167_.isAir();
   }
}