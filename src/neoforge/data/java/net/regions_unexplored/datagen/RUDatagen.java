package net.regions_unexplored.datagen;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.datagen.provider.client.RUModelProvider;
import net.regions_unexplored.datagen.provider.registry.*;
import net.regions_unexplored.datagen.provider.*;
import net.regions_unexplored.datagen.provider.tag.*;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = RegionsUnexplored.MOD_ID)
public class RUDatagen {
    private static final RegistrySetBuilder BOOTSTRAPS = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, RUConfiguredFeatureBootstrap::bootstrap)
        .add(Registries.PLACED_FEATURE, RUPlacedFeatureBootstrap::bootstrap)
        .add(Registries.BIOME, RUBiomeBootstrap::bootstrap)
        .add(Registries.NOISE, RUNoiseBootstrap::bootstrap)
        .add(Registries.DAMAGE_TYPE, RUDamageTypeBootstrap::bootstrap)
        .add(Registries.TEMPLATE_POOL, RUTemplatePoolBootstrap::bootstrap)
        .add(Registries.PROCESSOR_LIST, RUProcessorListBootstrap::bootstrap)
        .add(LithostitchedRegistries.WORLDGEN_MODIFIER, RUWorldgenModifierBootstrap::bootstrap)
        .add(LithostitchedRegistries.SURFACE_RULE, RUSurfaceRuleBootstrap::bootstrap)
    ;
    
    @SubscribeEvent
    public static void gatherDataClient(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        
        generator.addProvider(true, new RUModelProvider(output));

        var datapackRegistries = new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BOOTSTRAPS, Set.of(RegionsUnexplored.MOD_ID));

        generator.addProvider(true, datapackRegistries);

        generator.addProvider(true, new RUAdvancementProvider(output, datapackRegistries.getRegistryProvider()));


        generator.addProvider(true, new RURecipeProvider.Runner(output, registries));
        //generator.addProvider(true, RULootTableProvider.create(output, registries));
        generator.addProvider(true, new RULanguageProvider(output));
        
        generator.addProvider(true, new RUBlockTagProvider(output, datapackRegistries.getRegistryProvider()));
        generator.addProvider(true, new RUItemTagProvider(output, datapackRegistries.getRegistryProvider()));
        generator.addProvider(true, new RUEntityTypeTagProvider(output, datapackRegistries.getRegistryProvider()));
        generator.addProvider(true, new RUBiomeTagProvider(output, datapackRegistries.getRegistryProvider()));
        generator.addProvider(true, new RUTemplatePoolTagProvider(output, datapackRegistries.getRegistryProvider()));
        generator.addProvider(true, new RUProcessorListTagProvider(output, datapackRegistries.getRegistryProvider()));

        generator.addProvider(true, new RUDataMapGenerator(output, registries));
    }
}