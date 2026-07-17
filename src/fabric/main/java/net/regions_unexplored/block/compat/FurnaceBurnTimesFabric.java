package net.regions_unexplored.block.compat;

import net.fabricmc.fabric.api.registry.FuelValueEvents;

import static net.regions_unexplored.block.compat.FurnaceBurnTimes.BURN_TIME_300;
import static net.regions_unexplored.block.compat.FurnaceBurnTimes.BURN_TIME_200;
import static net.regions_unexplored.block.compat.FurnaceBurnTimes.BURN_TIME_150;
import static net.regions_unexplored.block.compat.FurnaceBurnTimes.BURN_TIME_100;

public class FurnaceBurnTimesFabric {
    public static void setup() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            BURN_TIME_100.forEach(item -> builder.add(item, 100));
            BURN_TIME_150.forEach(item -> builder.add(item, 150));
            BURN_TIME_200.forEach(item -> builder.add(item, 200));
            BURN_TIME_300.forEach(item -> builder.add(item, 300));
        });
    }
}
