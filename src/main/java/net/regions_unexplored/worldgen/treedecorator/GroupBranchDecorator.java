package net.regions_unexplored.worldgen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.common.RUCommonConfig.Misc.BranchMode;

import java.util.Optional;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.placeSingle;

public class GroupBranchDecorator extends TreeDecorator {
    public static final MapCodec<GroupBranchDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        Codec.floatRange(0, 1).fieldOf("probability").forGetter(d -> d.probability),
        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("branch_block").forGetter(d -> d.branchBlock),
        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("log_block").forGetter(d -> d.logBlock),
        ExtraCodecs.POSITIVE_INT.fieldOf("top_offset").forGetter(d -> d.topOffset),
        BlockStateProvider.CODEC.optionalFieldOf("leaves_provider").forGetter(d -> d.leavesProvider)
    ).apply(i, GroupBranchDecorator::new));

    public static final TreeDecoratorType<GroupBranchDecorator> TYPE = new TreeDecoratorType<>(CODEC);

    private final float probability;
    private final Block branchBlock;
    private final Block logBlock;
    private final int topOffset;
    private final Optional<BlockStateProvider> leavesProvider;

    private GroupBranchDecorator(float probability, Block branchBlock, Block logBlock, int topOffset, Optional<BlockStateProvider> leavesProvider) {
        this.probability = probability;
        this.branchBlock = branchBlock;
        this.logBlock = logBlock;
        this.topOffset = topOffset;
        this.leavesProvider = leavesProvider;
    }

    public static GroupBranchDecorator createWithoutLeaves(float probability, NaturalSet naturalSet, WoodSet woodSet, int requiredEmptyBlocks) {
        return createWithoutLeaves(probability, naturalSet.getBranch(), woodSet.getLog(), requiredEmptyBlocks);
    }
    
    public static GroupBranchDecorator createWithoutLeaves(float probability, Block branch, Block log, int requiredEmptyBlocks) {
        return new GroupBranchDecorator(probability, branch, log, requiredEmptyBlocks, Optional.empty());
    }

    public static GroupBranchDecorator create(float probability, NaturalSet naturalSet, WoodSet woodSet, int topOffset) {
        return create(probability, naturalSet, woodSet, topOffset, BlockStateProvider.simple(naturalSet.getLeaves()));
    }

    public static GroupBranchDecorator create(float probability, NaturalSet naturalSet, WoodSet woodSet, int topOffset, BlockStateProvider leavesProvider) {
        return create(probability, naturalSet, woodSet.getLog(), topOffset, leavesProvider);
    }
    
    public static GroupBranchDecorator create(float probability, NaturalSet naturalSet, Block log, int topOffset, BlockStateProvider leavesProvider) {
        return new GroupBranchDecorator(probability, naturalSet.getBranch(), log, topOffset, Optional.of(leavesProvider));
    }
    
    @Override
    protected TreeDecoratorType<?> type() {
        return TYPE;
    }

    @Override
    public void place(Context context) {
        BranchMode mode = RUConfigHandler.COMMON.getBranchMode();
        if (mode.cannotPlace()) return;
        
        RandomSource random = context.random();
        int topLogY = context.logs().stream().mapToInt(Vec3i::getY).max().orElse(Integer.MAX_VALUE);
        
        for (BlockPos logsPos : context.logs()) {
            if (!(logsPos.getY() + this.topOffset == topLogY) || !(random.nextFloat() <= this.probability)) continue;
            
            for (Direction branchDirection : Direction.Plane.HORIZONTAL) {
                BlockPos branchPos = logsPos.relative(branchDirection);
                
                BlockState toPlace = mode.selectBlock(this.branchBlock, this.logBlock)
                    .trySetValue(BlockStateProperties.AXIS, branchDirection.getAxis())
                    .trySetValue(BlockStateProperties.HORIZONTAL_FACING, branchDirection);
                
                context.setBlock(branchPos, toPlace);
                
                if (this.leavesProvider.isEmpty()) continue;
	            for (Direction leafDirection : Direction.values()) {
		            if (leafDirection == Direction.DOWN) continue;
		            placeLeaves(context, branchPos.relative(leafDirection));
	            }
            }
        }
    }

    private void placeLeaves(Context context, BlockPos pos) {
        if (context.isAir(pos)) {
            context.setBlock(pos, this.leavesProvider.get().getState(context.level(), context.random(), pos).trySetValue(LeavesBlock.DISTANCE, 1));
        }
    }
}
