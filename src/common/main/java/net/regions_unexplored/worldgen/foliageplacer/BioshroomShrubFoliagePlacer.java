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

public class BioshroomShrubFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<BioshroomShrubFoliagePlacer> CODEC = MapCodec.unit(BioshroomShrubFoliagePlacer::new);
    public static final FoliagePlacerType<BioshroomShrubFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public BioshroomShrubFoliagePlacer() {
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
        placeSquare(context, 1, 0, false);
    }
}
