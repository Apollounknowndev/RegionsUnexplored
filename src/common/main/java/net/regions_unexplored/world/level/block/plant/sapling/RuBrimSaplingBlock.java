package net.regions_unexplored.world.level.block.plant.sapling;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.tag.*;

public class RuBrimSaplingBlock extends SaplingBlock implements BonemealableBlock {

    public RuBrimSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return mayPlaceOn(level.getBlockState(below), level, below);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.BRIM_PLANT_CAN_SURVIVE_ON);
    }
}

