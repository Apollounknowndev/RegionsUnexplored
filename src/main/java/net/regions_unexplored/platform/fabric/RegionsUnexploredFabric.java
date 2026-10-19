//? if fabric {
package net.regions_unexplored.platform.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.compat.CompostableBlocks;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.platform.fabric.block.RuBlockEntitiesFabric;
import net.regions_unexplored.platform.fabric.block.compat.FurnaceBurnTimesFabric;
import net.regions_unexplored.registry.RUCreativeModeTabs;
import net.regions_unexplored.registry.RUItems;

public class RegionsUnexploredFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RegionsUnexplored.init();
        RuBlockEntitiesFabric.addBlockEntities();
        RegionsUnexploredFabric.afterRegistriesFreeze();
        ResourceConditions.register(RUConfigCondition.TYPE);

        RUBlocks.applyAliases(BuiltInRegistries.BLOCK::addAlias);
        RUItems.applyAliases(BuiltInRegistries.ITEM::addAlias);
    }

    public static void afterRegistriesFreeze() {
        RegionsUnexplored.afterRegistriesFreeze();
        CompostableBlocks.setup(CompostingChanceRegistry.INSTANCE::add);
        FurnaceBurnTimesFabric.setup();
        
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToBuildingBlocks(entries::addAfter);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToColoredBlocks(entries::addAfter);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToFunctionalBlocks(entries::addAfter);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            RUCreativeModeTabs.addToToolsAndUtilities(entries::addAfter);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            RUCreativeModeTabs.addToFoodAndDrinks(entries::addAfter);
        });
    }
}
//? }