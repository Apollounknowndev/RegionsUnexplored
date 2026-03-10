package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.regions_unexplored.lithostitched.BiomeEnabledPredicate;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RURegions;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class RUBiomeInjectorBootstrap {
    public static void bootstrap(BootstrapContext<BiomeInjector> context) {
        /*replaceFull(context, RURegions.OVERWORLD_PRIMARY, Biomes.OLD_GROWTH_PINE_TAIGA, RUBiomes.REDWOODS);
        replaceFull(context, RURegions.OVERWORLD_SECONDARY, Biomes.OLD_GROWTH_PINE_TAIGA, RUBiomes.REDWOODS);

        var meadowLike = targetSet(context, Biomes.MEADOW, Biomes.CHERRY_GROVE);

        replaceFull(context, RURegions.OVERWORLD_PRIMARY, meadowLike, RUBiomes.HIGHLAND_FIELDS);
        replaceFull(context, RURegions.OVERWORLD_SECONDARY, meadowLike, RUBiomes.ROCKY_MEADOW);*/
    }

    private static void replaceFull(BootstrapContext<BiomeInjector> context, ResourceKey<Region> region, ResourceKey<Biome> target, ResourceKey<Biome> replacement) {
        replaceFull(context, region, targetSet(context, target), replacement);
    }

    private static void replaceFull(BootstrapContext<BiomeInjector> context, ResourceKey<Region> region, HolderSet<Biome> target, ResourceKey<Biome> replacement) {
        register(context, region, replacement, builder -> builder.replacePartially(target, get(context, replacement), ParameterBuilder.create().region(region)));
    }

    private static void replacePartial(BootstrapContext<BiomeInjector> context, ResourceKey<Region> region, ResourceKey<Biome> target, ResourceKey<Biome> replacement, UnaryOperator<ParameterBuilder> operator) {
        register(context, region, replacement, builder -> builder.replacePartially(get(context, target), get(context, replacement), operator.apply(ParameterBuilder.create().region(region))));
    }

    private static void register(BootstrapContext<BiomeInjector> context, ResourceKey<Region> region, ResourceKey<Biome> biome, Function<BiomeInjector.InjectorBuilder, BiomeInjector> builder) {
        context.register(key(region, biome), builder.apply(BiomeInjector.builder(Level.OVERWORLD, new BiomeEnabledPredicate(biome))));
    }

    @SafeVarargs
    private static HolderSet<Biome> targetSet(BootstrapContext<BiomeInjector> context, ResourceKey<Biome>... biomes) {
        return HolderSet.direct(Arrays.stream(biomes).map(key -> context.lookup(Registries.BIOME).getOrThrow(key)).toList());
    }

    private static Holder<Biome> get(BootstrapContext<BiomeInjector> context, ResourceKey<Biome> biome) {
        return context.lookup(Registries.BIOME).getOrThrow(biome);
    }

    private static void register(BootstrapContext<BiomeInjector> context, ResourceKey<Biome> target, ResourceKey<Biome> replacement, ResourceKey<Region> region, UnaryOperator<ParameterBuilder> operator) {
        HolderGetter<Biome> registry = context.lookup(Registries.BIOME);
        context.register(key(region, replacement), BiomeInjector.builder(Level.OVERWORLD, new BiomeEnabledPredicate(replacement)).replacePartially(
            registry.getOrThrow(target),
            registry.getOrThrow(replacement),
            operator.apply(ParameterBuilder.create().region(region))
        ));
    }

    private static ResourceKey<BiomeInjector> key(ResourceKey<Region> region, ResourceKey<Biome> biome) {
        return ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, region.identifier().withSuffix("/" + biome.identifier().getPath()));
    }
}
