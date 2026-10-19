package net.regions_unexplored.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.regions_unexplored.module.platform.Registrar;
import net.regions_unexplored.worldgen.rulesource.ConfigRuleSource;

import java.util.function.Supplier;

public interface RURuleSources {
    Supplier<MapCodec<ConfigRuleSource>> CONFIG = register("config", ConfigRuleSource.CODEC);

    static <T extends RuleSource> Supplier<MapCodec<T>> register(String name, KeyDispatchDataCodec<T> type) {
        Registrar.register(BuiltInRegistries.MATERIAL_RULE, name, type::codec);
        return type::codec;
    }

    static void init() {
    }
}
