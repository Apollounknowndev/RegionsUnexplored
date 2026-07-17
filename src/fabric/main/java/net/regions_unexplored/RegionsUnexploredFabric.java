package net.regions_unexplored;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.registry.FabricRegistry;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.regions_unexplored.block.RuBlockEntitiesFabric;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.compat.FurnaceBurnTimesFabric;
import net.regions_unexplored.registry.RUCreativeModeTabs;
import net.regions_unexplored.registry.RUItems;

public class RegionsUnexploredFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RegionsUnexplored.init();
        RuBlockEntitiesFabric.addBlockEntities();
        RegionsUnexploredFabric.afterRegistriesFreeze();
        ResourceConditions.register(RUConfigCondition.TYPE);

        RUBlocks.applyAliases((a, b) -> ((FabricRegistry) BuiltInRegistries.BLOCK).addAlias(a, b));
        RUItems.applyAliases((a, b) -> ((FabricRegistry) BuiltInRegistries.ITEM).addAlias(a, b));
    }

    public static void afterRegistriesFreeze() {
        RegionsUnexplored.afterRegistriesFreeze();
        FurnaceBurnTimesFabric.setup();
        
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToBuildingBlocks(entries::insertAfter);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToColoredBlocks(entries::insertAfter);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            RUCreativeModeTabs.addToFunctionalBlocks(entries::insertAfter);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            RUCreativeModeTabs.addToToolsAndUtilities(entries::insertAfter);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            RUCreativeModeTabs.addToFoodAndDrinks(entries::insertAfter);
        });
    }
}
