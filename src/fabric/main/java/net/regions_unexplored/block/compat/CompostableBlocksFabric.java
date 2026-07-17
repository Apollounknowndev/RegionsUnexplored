package net.regions_unexplored.block.compat;


import net.fabricmc.fabric.api.registry.CompostableRegistry;

public class CompostableBlocksFabric {
    public static void setup() {
        CompostableBlocks.COMPOSTABLES.forEach(CompostableRegistry.INSTANCE::add);
    }
}
