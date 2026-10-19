//? if fabric {
package net.regions_unexplored.platform.fabric.block.compat;


import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.regions_unexplored.block.compat.CompostableBlocks;

public class CompostableBlocksFabric {
    public static void setup() {
        CompostableBlocks.COMPOSTABLES.forEach(CompostableRegistry.INSTANCE::add);
    }
}
//? }
