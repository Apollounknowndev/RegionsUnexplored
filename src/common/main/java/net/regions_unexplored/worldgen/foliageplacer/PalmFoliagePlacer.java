package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.*;

public class PalmFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = MapCodec.unit(PalmFoliagePlacer::new);
    public static final FoliagePlacerType<PalmFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public PalmFoliagePlacer() {
        super(ConstantInt.ZERO, ConstantInt.ZERO, 0);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        BlockPos origin = foliageAttachment.pos();
        Context context = new Context(level, foliageSetter, random, config.foliageProvider, origin, offset);
        for (int x : new int[]{-1, 1}) {
            for (int z : new int[]{-1, 1}) {
                placeSingle(context, origin.offset(x, -1, z));
            }
        }
        placeSquare(context, 1, 0, false);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            placeSingle(context, origin.above().relative(direction));
            placeSingle(context, origin.above().relative(direction, 2));
            placeSingle(context, origin.above(2).relative(direction, 2));
            placeSingle(context, origin.above().relative(direction, 3));
            placeSingle(context, origin.relative(direction, 3));
        }
    }
}
