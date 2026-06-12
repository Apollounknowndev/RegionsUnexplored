package net.regions_unexplored.block.type.grass;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.*;

public class FrozenGrassBlock extends VegetationBlock {
    public static final MapCodec<? extends FrozenGrassBlock> CODEC = simpleCodec(FrozenGrassBlock::new);
    protected static final VoxelShape SHAPE = RUBlockUtils.column(12, 0, 13);
    
    @Override
    protected MapCodec<? extends FrozenGrassBlock> codec() {
        return CODEC;
    }
    
    public FrozenGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        if (state.is(Blocks.SNOW) && state.getValue(SnowLayerBlock.LAYERS) < 8) return false;
        return state.is(RUBlockTags.SUPPORTS_FROZEN_GRASS);
    }
}
