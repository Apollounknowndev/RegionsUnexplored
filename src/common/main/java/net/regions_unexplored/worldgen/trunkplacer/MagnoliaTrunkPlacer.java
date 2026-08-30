package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class MagnoliaTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<MagnoliaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> MagnoliaTrunkPlacer.heightField(i).and(i.group(
        IntProviders.POSITIVE_CODEC.fieldOf("primary_branch_length").forGetter(t -> t.primaryBranchLength),
        IntProviders.POSITIVE_CODEC.fieldOf("secondary_branch_length").forGetter(t -> t.secondaryBranchLength)
    )).apply(i, MagnoliaTrunkPlacer::new));
    public static final TrunkPlacerType<MagnoliaTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    protected final IntProvider primaryBranchLength;
    protected final IntProvider secondaryBranchLength;

    public MagnoliaTrunkPlacer(IntProvider height, IntProvider primaryBranchLength, IntProvider secondaryBranchLength) {
        super(height);
        this.primaryBranchLength = primaryBranchLength;
        this.secondaryBranchLength = secondaryBranchLength;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(
        final WorldGenLevel level,
        final BiConsumer<BlockPos, BlockState> trunkSetter,
        final RandomSource random,
        final int treeHeight,
        final BlockPos origin,
        final TreeConfiguration config
    ) {
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        
        List<FoliageAttachment> attachments = new ArrayList<>();
        // Middle
        for (int y = 0; y < treeHeight; ++y) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        
        // Upper
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos pos = origin.above(treeHeight - 1).mutable();
        for (int i = 0; i < this.primaryBranchLength.sample(random); i++) {
            if (i % 3 == 0) {
                pos.move(direction);
            } else {
                pos.move(Direction.UP);
            }
            this.placeLog(level, trunkSetter, random, pos.immutable(), config);
        }
        attachments.add(attachment(pos.immutable()));
        
        pos.set(origin.above(treeHeight - 1));
        for (int i = 0; i < this.secondaryBranchLength.sample(random); i++) {
            if (i % 2 == 0) {
                pos.move(Direction.UP);
            }
            pos.move(direction.getOpposite());
            this.placeLog(level, trunkSetter, random, pos.immutable(), config, setAxis(direction));
        }
        attachments.add(attachment(pos.immutable()));
        
        return attachments;
    }
}
