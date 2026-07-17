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

public class MagnoliaFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<MagnoliaFoliagePlacer> CODEC = MapCodec.unit(MagnoliaFoliagePlacer::new);
    public static FoliagePlacerType<MagnoliaFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public MagnoliaFoliagePlacer() {
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
        
        placeSquare(context, 3, 1, false, 0);
        placeSquare(context, 1, 2, false);
        placeSquare(context, 1, 0, false);
        
        boolean clockwise = random.nextBoolean();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos pos = origin.above().relative(direction, 2);
            placeSingle(context, pos.below());
            placeSingle(context, pos.above());
            placeSingle(context, pos.below().relative(clockwise ? direction.getClockWise() : direction.getCounterClockWise()));
            placeSingle(context, pos.above().relative(clockwise ? direction.getClockWise() : direction.getCounterClockWise()));
        }
    }
    
    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }
}
