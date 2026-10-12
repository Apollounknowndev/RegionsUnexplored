package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.*;

public class BambooFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<BambooFoliagePlacer> CODEC = MapCodec.unit(BambooFoliagePlacer::new);
    public static final FoliagePlacerType<BambooFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public BambooFoliagePlacer() {
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
        placeDiamond(context, 1, -4, false);
        placeSquare(context, 1, -3, false, 1.0f / 3.0f);
        placeSquare(context, 1, -2, false);
        placeDiamond(context, 2, 3, -1, false);
        placeSquare(context, 1, 0, false);
        placeSquare(context, 1, 1, false, 1.0f / 3.0f);
        placeDiamond(context, 1, 2, false);
        placeDiamond(context, 1, 3, false);
        placeSingle(context, origin.above(4));
        placeSingle(context, origin.above(5));
    }
}
