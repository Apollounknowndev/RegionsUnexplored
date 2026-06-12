package net.regions_unexplored.block.type.plant.desert;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;

public class DeadGrassBlock extends VegetationBlock {
    public static final MapCodec<DeadGrassBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        Codec.floatRange(0, 16).fieldOf("height").forGetter(b -> b.height),
        propertiesCodec()
    ).apply(i, DeadGrassBlock::new));
    
    protected final float height;
    protected final VoxelShape shape;

    public DeadGrassBlock(float height, Properties properties) {
        super(properties);
        this.height = height;
        this.shape = RUBlockUtils.column(12, 0, height);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.shape;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }
}