package net.regions_unexplored.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.function.Supplier;

public class Registrar {
    @Expect
    public static <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value);

    @Expect
    public static Supplier<CreativeModeTab> registerCreativeModeTab(String name, Supplier<ItemStack> icon, Supplier<CreativeModeTab.DisplayItemsGenerator> items);
}
