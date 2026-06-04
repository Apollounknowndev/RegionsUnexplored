package net.regions_unexplored.module.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.RegionsUnexploredNeo;

import java.util.function.Supplier;

public class RegistrarActual {

    @Actual
    public static <T> Supplier<T> register(Registry<? super T> registry, String name, Supplier<T> value) {
        return RegionsUnexploredNeo.REGISTER_CACHE.computeIfAbsent(registry.key(), key -> DeferredRegister.create(registry.key().identifier(), RegionsUnexplored.MOD_ID)).register(name, value);
    }

    @Actual
    public static Supplier<CreativeModeTab> registerCreativeModeTab(String name, Supplier<ItemStack> icon, Supplier<CreativeModeTab.DisplayItemsGenerator> items) {
        return RegionsUnexploredNeo.REGISTER_CACHE.computeIfAbsent(BuiltInRegistries.CREATIVE_MODE_TAB.key(), resourceKey -> DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, RegionsUnexplored.MOD_ID)).register(name, () ->
                CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + RegionsUnexplored.MOD_ID + "." + name))
                .hideTitle()
                .backgroundTexture(Identifier.fromNamespaceAndPath(RegionsUnexplored.MOD_ID, "textures/gui/container/creative_inventory/tab_regions_unexplored_search.png"))
                .withSearchBar(58)
                .icon(icon)
                .displayItems(items.get())
                .withSearchBar()
                .build()
        );
    }
}
