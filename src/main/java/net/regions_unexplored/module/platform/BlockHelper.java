package net.regions_unexplored.module.platform;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.regions_unexplored.RegionsUnexplored;

//? if fabric
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

public class BlockHelper {
    public static WoodType setWoodType(String path, BlockSetType blockSetType) {
        //? if fabric
        return new WoodTypeBuilder().register(RegionsUnexplored.id(path), blockSetType);
        //? else
        //return WoodType.register(new WoodType(RegionsUnexplored.id(path).toString(), blockSetType));
    }
}
