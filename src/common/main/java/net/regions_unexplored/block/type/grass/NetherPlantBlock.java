package net.regions_unexplored.block.type.grass;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.RUBlockTags;

public class NetherPlantBlock extends VegetationBlock {
    public static final MapCodec<? extends NetherPlantBlock> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        Codec.floatRange(0, 16).fieldOf("height").forGetter(b -> b.height),
        propertiesCodec()
    ).apply(i, NetherPlantBlock::new));
    private final float height;
    private final VoxelShape shape;

    public NetherPlantBlock(float height, Properties properties) {
        super(properties);
        this.height = height;
        this.shape = RUBlockUtils.column(12, 0, height);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return this.shape.move(offset.x, offset.y, offset.z);
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.SUPPORTS_NETHER_PLANTS);
    }
}
