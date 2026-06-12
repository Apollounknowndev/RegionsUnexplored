package net.regions_unexplored.block.type.grass;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.*;
import net.regions_unexplored.block.properties.RUBlockProperties;

public class AshenGrassBlock extends VegetationBlock {
    public static final MapCodec<? extends AshenGrassBlock> CODEC = simpleCodec(AshenGrassBlock::new);
    public static final BooleanProperty SMOULDERING = RUBlockProperties.SMOULDERING;
    protected static final VoxelShape SHAPE = RUBlockUtils.column(12, 0, 10);
    
    @Override
    protected MapCodec<? extends AshenGrassBlock> codec() {
        return CODEC;
    }

    public AshenGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SMOULDERING, false));
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(SMOULDERING);
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (state.getValue(SMOULDERING) && random.nextInt(20) == 0) {
            level.addParticle(ParticleTypes.FLAME, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 0.25D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 0.25D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.SUPPORTS_ASHEN_GRASS);
    }
    
    public static boolean isSmouldering(BlockState state) {
        return state.getValue(SMOULDERING);
    }
}
