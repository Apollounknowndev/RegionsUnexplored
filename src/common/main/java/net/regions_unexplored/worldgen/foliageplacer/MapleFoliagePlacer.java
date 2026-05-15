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

public class MapleFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<MapleFoliagePlacer> CODEC = MapCodec.unit(MapleFoliagePlacer::new);
    public static FoliagePlacerType<MapleFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public MapleFoliagePlacer() {
        super(ConstantInt.ZERO, ConstantInt.ZERO, 0);
    }
    
    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        BlockPos origin = foliageAttachment.pos();
        Context context = new Context(level, foliageSetter, random, config.foliageProvider, origin, offset);
        placeSquare(context, 0, 1, false);
        placeSquare(context, 1, 0, false, 0.4f);
        placeSquare(context, 1, -1, false, 0.4f);
        placeSquare(context, 1, -2, false);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            placeSingle(context, origin.below(2).relative(direction, 2), 0.5f);
        }
        placeSquare(context, 2, -3, false, 0.5f);
        placeSquare(context, 2, -4, false, 0.5f);
        placeDiamond(context, 1, -5, false);
        
    }
    
    @Override
    protected FoliagePlacerType<?> type() {
        return TYPE;
    }
}
