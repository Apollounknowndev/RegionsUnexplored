package net.regions_unexplored.block.type.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.regions_unexplored.registry.tag.*;
import net.regions_unexplored.block.properties.RUBlockProperties;
import org.jetbrains.annotations.Nullable;

public class SandyTallGrassBlock extends DoublePlantBlock {
    public static final BooleanProperty IS_RED = RUBlockProperties.IS_RED;

    public SandyTallGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(IS_RED, false));
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.SUPPORTS_SANDY_PLANTS);
    }
    
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(HALF, IS_RED);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        BlockPos abovePos = pos.above();
        boolean red = level.getBlockState(pos.below()).is(RUBlockTags.SUPPORTS_RED_SANDY_PLANTS);
        level.setBlock(abovePos, copyWaterloggedFrom(level, abovePos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(IS_RED, red)), 3);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced()) {
            boolean red = level.getBlockState(pos.below()).is(RUBlockTags.SUPPORTS_RED_SANDY_PLANTS);
            return this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(IS_RED, red);
        }
        return super.getStateForPlacement(context);
    }
}
