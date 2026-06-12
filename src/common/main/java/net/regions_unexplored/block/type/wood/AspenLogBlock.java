package net.regions_unexplored.block.type.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.regions_unexplored.block.properties.RUBlockProperties;

public class AspenLogBlock extends Block {
   public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
   public static final BooleanProperty IS_BASE = RUBlockProperties.IS_BASE;

   public AspenLogBlock(Properties properties) {
      super(properties);
      this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Axis.Y).setValue(IS_BASE, false));
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
      boolean isBase = state.getValue(AXIS) == Axis.Y && level.getBlockState(pos.below()).is(BlockTags.DIRT);
      return super.updateShape(state, directionToNeighbour, neighbourState, level, pos, neighbourPos).setValue(IS_BASE, isBase);
   }
   
   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(AXIS, IS_BASE);
   }
   
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isBase = context.getLevel().getBlockState(context.getClickedPos().below()).is(BlockTags.DIRT);
      return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(IS_BASE, isBase);
   }
}