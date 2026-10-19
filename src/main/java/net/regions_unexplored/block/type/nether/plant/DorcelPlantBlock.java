package net.regions_unexplored.block.type.nether.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.tag.*;
import net.regions_unexplored.registry.data.RUDamageTypes;

public class DorcelPlantBlock extends FlowerBlock {
    protected static final VoxelShape SHAPE = RUBlockUtils.column(12, 0, 13);

    public DorcelPlantBlock(Holder<MobEffect> suspiciousStewEffect, float effectSeconds, Properties properties) {
        super(suspiciousStewEffect, effectSeconds, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.hurt(level.damageSources().source(RUDamageTypes.DORCEL), 1.0F);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(RUBlockTags.SUPPORTS_INFERNAL_PLANT);
    }
}
