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
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SkinnyPineFoliagePlacer extends BlobFoliagePlacer {
    public static final MapCodec<SkinnyPineFoliagePlacer> CODEC = IntProviders.CODEC.fieldOf("offset").xmap(SkinnyPineFoliagePlacer::new, p -> p.offset);
    public static final FoliagePlacerType<SkinnyPineFoliagePlacer> TYPE = new FoliagePlacerType<>(CODEC);

    public SkinnyPineFoliagePlacer(IntProvider offset) {
        this(ConstantInt.of(1), offset, 1);
    }

    public SkinnyPineFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
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
        for (int yo = 2 + offset; yo >= -(5 + random.nextInt(1)); yo--) {
            boolean top = yo >= offset;
            this.placeLeavesRow(level, foliageSetter, random, config, foliageAttachment.pos(), top ? 0 : 1, yo, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return dx == currentRadius && dz == currentRadius && currentRadius != 0 && random.nextFloat() < 0.8;
    }
}
