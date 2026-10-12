package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ShrubTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<ShrubTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, ShrubTrunkPlacer::new));
    public static final TrunkPlacerType<ShrubTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public ShrubTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight + 1))) {
            return List.of();
        }
        convertGrassBelow(level, trunkSetter, origin);
        convertNyliumBelow(level, trunkSetter, origin);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        return List.of(attachment(origin.above(treeHeight)));
    }
}
