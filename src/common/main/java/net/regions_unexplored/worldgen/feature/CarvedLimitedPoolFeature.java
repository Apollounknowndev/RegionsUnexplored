package net.regions_unexplored.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.regions_unexplored.worldgen.feature.config.CarvedLimitedPoolFeatureConfig;

import javax.annotation.Nonnull;


/**
 * This code was implemented in an equivalent way to the Natural Philosophy CarvedLimitedPoolFeature.
 * It is a partial reimplementation of Terralith's Yellowstone hot springs, in code form
 * @author VoidsongDragonfly
 */
public class CarvedLimitedPoolFeature extends Feature<CarvedLimitedPoolFeatureConfig> {
    public CarvedLimitedPoolFeature(Codec<CarvedLimitedPoolFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(@Nonnull FeaturePlaceContext<CarvedLimitedPoolFeatureConfig> context) {
        // Basic information we need for placement
        CarvedLimitedPoolFeatureConfig configuration = context.config();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockPos.MutableBlockPos mut = origin.mutable();
        RandomSource random = context.random();
        // We don't want to continue scanning for the entire feature depth if we don't need to
        int maxDepth = 1;
        // Iterate over the area from the origin, we iterate N times, where N is max depth
        // We do not use that offset but instead match to heightmap each time for checks
        for (int d = 0;d<maxDepth;d++) {
            // Boolean storage
            boolean increasedDepth = false;
            boolean[][] waterPlacement = new boolean[18][18];
            // We need to iterate over one extra block to ensure we "adjust" border regions where another chunk placing
            // May have affected overall placement of the pools at depth >1
            // This removes ugly lines common to many pools along chunk borders in the direction of generation
            for (int i = -1; i < 17; i++) {
                for (int j = -1; j < 17; j++) {
                    // If this is already water, mark it as such and move on so we know for the future if it's depth 0
                    // This has to be first because the loop will be continued through & may skip if it's after the later checks
                    if (d == 0 && level.isWaterAt(level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, origin.offset(i, 0, j)).below())) {
                        waterPlacement[i+1][j+1] = true;
                    }
                    // Get heightmap position and the state there
                    mut = mut.set(level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, origin.offset(i, 0, j)).below());
                    // Test if the pool can be placed first
                    if (configuration.pool().test(level, mut)) {
                        // Check if the block below this is sound
                        if (!configuration.wall().test(level, mut.below())) continue;
                        // If the pool can be placed, check further if the space above is "open"
                        boolean open = true;
                        for (BlockPos check : BlockPos.betweenClosed(mut.offset(-1, 1, -1), mut.offset(1, 1, 1))) {
                            open = open && (level.isEmptyBlock(check) || level.isWaterAt(check));
                            if (!open) break;
                        }
                        if (!open) continue;
                        // If the pool can be placed, check if the sides are an allowable block
                        boolean walled = true;
                        for (BlockPos check : BlockPos.betweenClosed(mut.offset(-1, 0, -1), mut.offset(1, 0, 1))) {
                            walled = walled && (configuration.wall().test(level, check) || level.isWaterAt(check));
                            if (!walled) break;
                        }
                        if (!walled) continue;
                        // If all of this is true, place
                        level.setBlock(mut, Blocks.WATER.defaultBlockState(), 3);
                        waterPlacement[i+1][j+1] = true;
                        // We check to see if we placed any blocks this time, and if we did, decrease depth
                        if (!increasedDepth && maxDepth < configuration.depth()) maxDepth++;
                    }
                }
            }
            // For the top layer only, we then go back and place the "steep" block
            if (d == 0) {
                // This is another scan because it's simpler than trying to detect places water _will_ be placed before
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {
                        // We check the boolean array we made earlier so as not to reduce world state queries
                        boolean water = false;
                        // We want to check all eight blocks surrounding this block, as well as this block itself
                        for (int k = -1; k < 2; k++) {
                            for (int l = -1; l < 2; l++) {
                                water = water || waterPlacement[i+1+k][j+1+l];
                                if (water) break;
                            }
                            if (water) break;
                        }
                        // Skip the rest of this iteration if we have found water
                        if (water) continue;
                        // If none of these are true, we can place a column to column depth of our block
                        for (int m = 1; m <= configuration.slopeDepth().sample(context.random())+1; m++) {
                            mut = mut.set(level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, origin.offset(i, 0, j)).below(m));
                            // We have to make sure it's the right state where we're placing it
                            if (configuration.wall().test(level, mut))
                                level.setBlock(mut, m == 1 ? configuration.slopeTop().getState(level, random, mut) : configuration.slope().getState(level, random, mut), 3);
                            else
                                break;
                        }
                    }
                }
            }
        }
        return true;
    }
}