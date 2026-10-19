package net.regions_unexplored.block.type.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.component.SuspiciousStewEffects.Entry;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.registry.tag.*;

import java.util.List;

public class BioshroomBlock extends SaplingBlock implements SuspiciousEffectHolder {
    protected static final VoxelShape SHAPE = RUBlockUtils.column(10, 0, 13);
    private final SuspiciousStewEffects suspiciousStewEffects;
    private final int color;

    public BioshroomBlock(TreeGrower tree, Holder<MobEffect> suspiciousStewEffect, final float effectSeconds, int color, Properties properties) {
        super(tree, properties);
        this.suspiciousStewEffects = makeEffectList(suspiciousStewEffect, effectSeconds);
        this.color = color;
    }
    
    @Override
    public SuspiciousStewEffects getSuspiciousEffects() {
        return this.suspiciousStewEffects;
    }
    
    protected static SuspiciousStewEffects makeEffectList(final Holder<MobEffect> suspiciousStewEffect, final float effectSeconds) {
        return new SuspiciousStewEffects(List.of(new Entry(suspiciousStewEffect, Mth.floor(effectSeconds * 20.0F))));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return false;
    }
    
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.below()).is(RUBlockTags.BIOSHROOM_GROW_BLOCK);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isFaceSturdy(level, pos, Direction.UP);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() >= 0.1) return;

        int plantX = pos.getX();
        int plantY = pos.getY();
        int plantZ = pos.getZ();

        BlockPos.MutableBlockPos ambientPos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < 2; ++i) {
            ambientPos.set(plantX + Mth.nextInt(random, -2, 2), plantY + random.nextInt(3), plantZ + Mth.nextInt(random, -2, 2));
            BlockState particlePosState = level.getBlockState(ambientPos);
            if (particlePosState.isCollisionShapeFullBlock(level, ambientPos)) continue;

            ColorParticleOption particle = ColorParticleOption.create(RUParticleTypes.FLOATING_SPORE.get(), this.color);
            level.addParticle(particle, (double)ambientPos.getX() + random.nextDouble(), (double)ambientPos.getY() + random.nextDouble(), (double)ambientPos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}
