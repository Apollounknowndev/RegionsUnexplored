package net.regions_unexplored.block.type.leaves;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.regions_unexplored.block.RUBlockUtils;

public class BrimwoodLeavesBlock extends LeavesBlock {
    public BrimwoodLeavesBlock(BlockBehaviour.Properties properties) {
        super(0, properties.mapColor(MapColor.COLOR_BROWN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(BrimwoodLeavesBlock::ocelotOrParrot).isSuffocating(BrimwoodLeavesBlock::never).isViewBlocking(BrimwoodLeavesBlock::never).pushReaction(PushReaction.DESTROY).isRedstoneConductor(RUBlockUtils::never));
    }

    private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
    
    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return null;
    }
    
    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {
    
    }
}