package net.regions_unexplored.world.level.block.plant.flower;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.tag.*;

public class RuSnowFlowerBlock extends FlowerBlock {
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public RuSnowFlowerBlock(Holder<MobEffect> mobEffect, int i, Properties properties) {
        super(mobEffect, i, properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        if(state.is(Blocks.SNOW)){
            if (state.getValue(SnowLayerBlock.LAYERS) == 8){
                return state.is(RUBlockTags.SNOW_PLANT_CAN_SURVIVE_ON);
            }
            else{
                return false;
            }
        }
        return state.is(BlockTags.DIRT) || state.is(RUBlockTags.SNOW_PLANT_CAN_SURVIVE_ON);
    }
}
