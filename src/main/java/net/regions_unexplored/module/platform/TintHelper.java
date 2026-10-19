package net.regions_unexplored.module.platform;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

//? if fabric
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
//? else
//import net.regions_unexplored.platform.neoforge.client.TintRegistration;

public class TintHelper {
    public static void tintBlocks(BlockColor blockColor, Block... blocks) {
        //? if fabric
        ColorProviderRegistry.BLOCK.register(blockColor, blocks);
        //? else
        //TintRegistration.BLOCK_TINTS.computeIfAbsent(blockColor, b -> blocks);
    }
    
    public static void tintItems(ItemColor itemColor, ItemLike... items) {
        //? if fabric
        ColorProviderRegistry.ITEM.register(itemColor, items);
        //? else
        //TintRegistration.ITEM_TINTS.computeIfAbsent(itemColor, i -> items);
    }
}
