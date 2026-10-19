package net.regions_unexplored.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.module.platform.Registrar;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RUItemUtils {
    // Calling this directly works
    public static Supplier<Item> register(String name, Function<Item.Properties, Item> factory) {
        ResourceKey<Item> key = RegionsUnexplored.key(Registries.ITEM, name);
        return Registrar.register(BuiltInRegistries.ITEM, name, () -> factory.apply(new Item.Properties().setId(key)));
    }

    // Calling this to call the first method *doesn't* work
    public static <T extends Block> Supplier<Item> registerBlock(String name, Supplier<T> block) {
        return register(name, p -> new BlockItem(block.get(), p.useBlockDescriptionPrefix()));
    }

    public static Supplier<Item> registerPlaceOnWaterBlock(String name, Supplier<Block> block) {
        return register(name, p -> new PlaceOnWaterBlockItem(block.get(), p.useBlockDescriptionPrefix()));
    }

    public static FoodProperties food(int nutrition, float saturation) {
        return new FoodProperties(nutrition, saturation, false);
    }
    
    public static Consumable consumable(UnaryOperator<Consumable.Builder> operator) {
        return operator.apply(Consumables.defaultFood()).build();
    }
}
