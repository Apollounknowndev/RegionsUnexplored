package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.*;

public class BioshroomFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<BioshroomFoliagePlacer> CODEC = BlockStateProvider.CODEC.fieldOf("secondary_provider").xmap(BioshroomFoliagePlacer::new, BioshroomFoliagePlacer::secondaryProvider);
    public static final FoliagePlacerType<BioshroomFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);
    private final BlockStateProvider secondaryProvider;
    
    public BioshroomFoliagePlacer(BlockStateProvider secondaryProvider) {
        super(ConstantInt.ZERO, ConstantInt.ZERO, 0);
        this.secondaryProvider = secondaryProvider;
    }
    
    public BlockStateProvider secondaryProvider() {
        return this.secondaryProvider;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }

    @Override
    protected void createFoliage(
        final WorldGenLevel level,
        final FoliageSetter foliageSetter,
        final RandomSource random,
        final TreeConfiguration config,
        final int treeHeight,
        final FoliageAttachment foliageAttachment,
        final int foliageHeight,
        final int leafRadius,
        final int offset
    ) {
        BlockPos origin = foliageAttachment.pos();
        Context context = new Context(level, foliageSetter, random, config.foliageProvider, origin, offset);
        if (random.nextBoolean()) {
            placeSquare(context, 1, 0, false);
        } else {
            placeSingle(context, origin);
            placeSingle(context, origin.north(), this.secondaryProvider);
            placeSingle(context, origin.north().east());
            placeSingle(context, origin.east(), this.secondaryProvider);
            placeSingle(context, origin.east().south());
            placeSingle(context, origin.south(), this.secondaryProvider);
            placeSingle(context, origin.south().west());
            placeSingle(context, origin.west(), this.secondaryProvider);
            placeSingle(context, origin.west().north());
            placeDiamond(context, 1, 1, false);
        }
    }
}
