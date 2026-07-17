package net.regions_unexplored.block.type.flower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.*;

public class SnowFlowerBlock extends FlowerBlock {
    protected static final VoxelShape SHAPE = RUBlockUtils.column(6, 0, 10);

    public SnowFlowerBlock(Holder<MobEffect> suspiciousStewEffect, float effectSeconds, Properties properties) {
        super(suspiciousStewEffect, effectSeconds, properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        if (state.is(Blocks.SNOW)) {
            if (state.getValue(SnowLayerBlock.LAYERS) == 8){
                return state.is(RUBlockTags.SUPPORTS_FROZEN_GRASS);
            }
            else{
                return false;
            }
        }
        return state.is(BlockTags.DIRT) || state.is(RUBlockTags.SUPPORTS_FROZEN_GRASS);
    }
}
