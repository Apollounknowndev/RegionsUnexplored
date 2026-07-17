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
}
