package net.regions_unexplored.block.type.nether.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.block.type.base.BonemealableSegmentedBlock;

public class NetherGroundCoverBlock extends BonemealableSegmentedBlock {
   public NetherGroundCoverBlock(Properties properties) {
      super(properties);
   }

   @Override
   protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
      return state.is(BlockTags.NYLIUM);
   }
}