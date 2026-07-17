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

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RUItemUtils {
    public static Supplier<Item> register(String name, ItemFactory factory) {
        ResourceKey<Item> key = RegionsUnexplored.key(Registries.ITEM, name);
        return Registrar.register(BuiltInRegistries.ITEM, name, () -> factory.apply(new Item.Properties().setId(key)));
    }

    public static <T extends Block> Supplier<Item> registerBlock(String name, Supplier<T> block) {
        return register(name, p -> new BlockItem(block.get(), p));
    }

    public static Supplier<Item> registerPlaceOnWaterBlock(String name, Supplier<Block> block) {
        return register(name, p -> new PlaceOnWaterBlockItem(block.get(), p));
    }

    public static FoodProperties food(int nutrition, float saturation) {
        return new FoodProperties(nutrition, saturation, false);
    }
    
    public static Consumable consumable(UnaryOperator<Consumable.Builder> operator) {
        return operator.apply(Consumables.defaultFood()).build();
    }
}
