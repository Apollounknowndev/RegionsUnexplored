//? if fabric {
package net.regions_unexplored.platform.fabric.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.RUBlocks;

public class RuBlockEntitiesFabric {
    public static void addBlockEntities() {
        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (set.getSign() != null) {
                BlockEntityType.SIGN.addSupportedBlock(set.getSign());
            }
            if (set.getWallSign() != null) {
                BlockEntityType.SIGN.addSupportedBlock(set.getWallSign());
            }

            if (set.getHangingSign() != null) {
                BlockEntityType.HANGING_SIGN.addSupportedBlock(set.getHangingSign());
            }
            if (set.getWallHangingSign() != null) {
                BlockEntityType.HANGING_SIGN.addSupportedBlock(set.getWallHangingSign());
            }
        }
    }
}
//? }