package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.*;

public class AspenFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<AspenFoliagePlacer> CODEC = MapCodec.unit(AspenFoliagePlacer::new);
    public static FoliagePlacerType<AspenFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public AspenFoliagePlacer() {
        super(ConstantInt.ZERO, ConstantInt.ZERO, 0);
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
        placeSquare(context, 0, 2, false);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            placeSingle(context, origin.above(2).relative(direction), 0.5f);
        }
        placeDiamond(context, 1, 1, false);
        placeSquare(context, 1, 0, false);
        placeDiamond(context, 2, -1, false);
    }
    
    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }
}
