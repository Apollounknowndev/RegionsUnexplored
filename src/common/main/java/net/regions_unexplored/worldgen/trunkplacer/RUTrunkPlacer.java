package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.datafixers.Products;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;

import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.tag.*;

import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class RUTrunkPlacer extends TrunkPlacer {
    protected final IntProvider height;
    public RUTrunkPlacer(IntProvider height) {
        super(0, 0, 0);
        this.height = height;
    }

    protected static <P extends RUTrunkPlacer> Products.P1<RecordCodecBuilder.Mu<P>, IntProvider> heightField(RecordCodecBuilder.Instance<P> instance) {
        return instance.group(IntProviders.POSITIVE_CODEC.fieldOf("height").forGetter(placer -> placer.height));
    }

    public int getTreeHeight(RandomSource random) {
        return this.height.sample(random);
    }

    public static FoliageAttachment attachment(BlockPos pos) {
        return new FoliageAttachment(pos, 0, false);
    }
    
    public static Function<BlockState, BlockState> setAxis(Direction direction) {
        return state -> state.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
    }
}
