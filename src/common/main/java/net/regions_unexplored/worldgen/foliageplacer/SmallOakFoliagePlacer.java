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

public class SmallOakFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<SmallOakFoliagePlacer> CODEC = MapCodec.unit(SmallOakFoliagePlacer::new);
    public static final FoliagePlacerType<SmallOakFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public SmallOakFoliagePlacer() {
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
        if (foliageAttachment.radiusOffset() < 0) {
            for (Direction direction : Direction.values()) {
                placeSingle(context, origin.relative(direction));
            }
            return;
        }
        placeSquare(context, 1, -2, false);
        placeDiamond(context, 1, -1, false);
        placeDiamond(context, 1, 0, false);
        placeSingle(context, origin.above());
        placeSingle(context, origin.above(2));
    }
}
