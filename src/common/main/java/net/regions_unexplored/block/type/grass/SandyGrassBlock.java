package net.regions_unexplored.block.type.grass;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.tag.*;
import net.regions_unexplored.block.type.plant.SandyTallGrassBlock;
import net.regions_unexplored.block.properties.RUBlockProperties;

import static net.minecraft.world.level.block.DoublePlantBlock.copyWaterloggedFrom;

public class SandyGrassBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<? extends SandyGrassBlock> CODEC = simpleCodec(SandyGrassBlock::new);
    public static final BooleanProperty IS_RED = RUBlockProperties.IS_RED;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public SandyGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_RED, false));
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos2) {
        boolean red = level.getBlockState(pos.below()).is(RUBlockTags.SUPPORTS_RED_SANDY_PLANTS);
        state.setValue(IS_RED, red);
        
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, level, pos, pos2);
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(IS_RED);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean red = context.getLevel().getBlockState(context.getClickedPos().below()).is(RUBlockTags.SUPPORTS_RED_SANDY_PLANTS);
        return this.defaultBlockState().setValue(IS_RED, red);
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
        boolean isRed = state.getValue(IS_RED);
        if(state.is(RUBlocks.SANDY_GRASS.get())){
            Block sandyTallGrassBlock = RUBlocks.SANDY_TALL_GRASS.get();
            if (sandyTallGrassBlock.defaultBlockState().canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
                placeAt(level, sandyTallGrassBlock.defaultBlockState().setValue(IS_RED, isRed), pos, 2);
                placeAt(level, sandyTallGrassBlock.defaultBlockState().setValue(SandyTallGrassBlock.HALF, DoubleBlockHalf.UPPER).setValue(IS_RED, isRed), pos.above(), 2);
            }
        }
    }
    
    public static void placeAt(LevelAccessor level, BlockState state, BlockPos pos, int i) {
        level.setBlock(pos, copyWaterloggedFrom(level, pos, state), i);
    }
}
