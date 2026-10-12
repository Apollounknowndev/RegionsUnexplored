package net.regions_unexplored.worldgen.trunkplacer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CobaltShrubTrunkPlacer extends RUTrunkPlacer {
    public static final MapCodec<CobaltShrubTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i -> heightField(i).apply(i, CobaltShrubTrunkPlacer::new));
    public static final TrunkPlacerType<CobaltShrubTrunkPlacer> TYPE = new TrunkPlacerType<>(CODEC);

    public CobaltShrubTrunkPlacer(IntProvider height) {
        super(height);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TYPE;
    }

    @Override
    public List<FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        List<FoliageAttachment> attachments = new ArrayList<>();
        convertNyliumBelow(level, trunkSetter, origin);
        for (int y = 0; y < treeHeight; y++) {
            this.placeLog(level, trunkSetter, random, origin.above(y), config);
        }
        attachments.add(attachment(origin.above(treeHeight)));

        if (treeHeight > 3 || treeHeight == 3 && random.nextInt(4) == 0) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos top = origin.above(treeHeight - 1);
            this.placeLog(level, trunkSetter, random, top.relative(direction), config, setAxis(direction));
            this.placeLog(level, trunkSetter, random, top.below().relative(direction), config, setAxis(direction));
            BlockPos arm = top.relative(direction, 2);
            for (int y = 0; y < 3; y++) {
                this.placeLog(level, trunkSetter, random, arm.above(y), config);
            }
            attachments.add(attachment(arm.above(3)));
        }
        return attachments;
    }
}
