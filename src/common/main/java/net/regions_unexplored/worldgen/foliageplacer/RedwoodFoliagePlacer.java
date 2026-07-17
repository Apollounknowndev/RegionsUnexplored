package net.regions_unexplored.worldgen.foliageplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.Context;

import static net.regions_unexplored.worldgen.foliageplacer.RUFoliagePlacerUtils.*;

public class RedwoodFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<RedwoodFoliagePlacer> CODEC = IntProviders.CODEC.fieldOf("offset").xmap(RedwoodFoliagePlacer::new, p -> p.offset);
    public static final FoliagePlacerType<RedwoodFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public RedwoodFoliagePlacer(IntProvider offset) {
        this(ConstantInt.of(1), offset, 1);
    }

    public RedwoodFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
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
        Context context = new Context(level, foliageSetter, random, config.foliageProvider, foliageAttachment.pos(), offset);
        placeDiamond(context, 1, -1, false);
        placeDiamond(context, 2, 0, false);
        placeSquare(context, 1, 1, false, 0.33f);
        placeDiamond(context, 1, 2, false);
        placeSquare(context, 0, 3, false);
        placeSquare(context, 0, 4, false);
    }
}
