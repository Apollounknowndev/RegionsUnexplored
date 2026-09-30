package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.regions_unexplored.registry.RUBlocks;

import java.util.List;
import java.util.function.BiConsumer;

public class BambooTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<BambooTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, BambooTrunkPlacer::new));
    public static final TrunkPlacerType<BambooTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public BambooTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        if (level instanceof LevelHeightAccessor bounds && bounds.isOutsideBuildHeight(origin.above(treeHeight + 5))) {
            return List.of();
        }
        BlockPos below = origin.below();
        if (level.isStateAtPosition(below, state -> state.is(RUBlocks.PEAT_GRASS_BLOCK.get()))) {
            trunkSetter.accept(below, RUBlocks.PEAT_PODZOL.get().defaultBlockState());
        } else if (level.isStateAtPosition(below, state -> state.is(RUBlocks.SILT_GRASS_BLOCK.get()))) {
            trunkSetter.accept(below, RUBlocks.SILT_PODZOL.get().defaultBlockState());
        } else if (level.isStateAtPosition(below, state -> state.is(Blocks.GRASS_BLOCK) || state.is(RUBlocks.ALPHA_GRASS_BLOCK.get()))) {
            trunkSetter.accept(below, Blocks.PODZOL.defaultBlockState());
        }
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        return List.of(attachment(origin.above(treeHeight)));
    }
}
