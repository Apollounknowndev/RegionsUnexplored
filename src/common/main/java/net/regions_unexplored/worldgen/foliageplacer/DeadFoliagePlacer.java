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

public class DeadFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<DeadFoliagePlacer> CODEC = MapCodec.unit(DeadFoliagePlacer::new);
    public static final FoliagePlacerType<DeadFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public DeadFoliagePlacer() {
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
        placeDiamond(context, 1, -1, false);
        placeSingle(context, origin);
        for (int y = 2; y <= treeHeight - 3; y++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (random.nextBoolean()) {
                    placeSingle(context, origin.below(y).relative(direction));
                }
            }
        }
    }
}
