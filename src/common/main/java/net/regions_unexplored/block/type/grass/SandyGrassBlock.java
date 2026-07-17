package net.regions_unexplored.block.type.grass;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.tag.*;
import net.regions_unexplored.block.type.plant.SandyTallGrassBlock;

import static net.minecraft.world.level.block.DoublePlantBlock.copyWaterloggedFrom;

public class SandyGrassBlock extends VegetationBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    
    private final ResourceKey<Block> tallGrassKey;

    public SandyGrassBlock(ResourceKey<Block> tallGrassKey, Properties properties) {
        super(properties);
        this.tallGrassKey = tallGrassKey;
    }
    
    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }
    
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }
    
    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.SUPPORTS_SANDY_PLANTS);
    }
    
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState tallGrass = level.registryAccess().lookupOrThrow(Registries.BLOCK).getValue(this.tallGrassKey).defaultBlockState();
        if (tallGrass.canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
            level.setBlock(pos, tallGrass, 2);
            level.setBlock(pos.above(), tallGrass.setValue(SandyTallGrassBlock.HALF, DoubleBlockHalf.UPPER), 2);
        }
    }
}
