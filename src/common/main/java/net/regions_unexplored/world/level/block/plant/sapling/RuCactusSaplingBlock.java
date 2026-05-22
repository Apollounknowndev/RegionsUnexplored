package net.regions_unexplored.world.level.block.plant.sapling;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.regions_unexplored.registry.RUBlocks;

public class RuCactusSaplingBlock extends SaplingBlock implements BonemealableBlock {

    public RuCactusSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if ((level.getMaxLocalRawBrightness(pos.above()) >= 9 && randomSource.nextInt(7) == 0)&&(!(level.getBlockState(pos.below()).is(Blocks.CACTUS)||level.getBlockState(pos.below()).is(RUBlocks.SAGUARO_CACTUS.get())))) {
            this.advanceTree(level, pos, state, randomSource);
        }

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        return mayPlaceOn(level.getBlockState(belowPos), level, belowPos);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.SAND) || state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND)|| state.is(RUBlocks.SAGUARO_CACTUS.get())|| state.is(Blocks.CACTUS);
    }
}

