package net.regions_unexplored.datagen.provider.registry;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuMiscOverworldPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuNetherPlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuTreePlacements;
import net.regions_unexplored.datagen.provider.registry.placed_feature.RuVegetationPlacements;

public class RUBiomeFeatures {
    public static void grassSprouts(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_GRASS_SPROUTS_SPARSE);
    }
    
    public static void redwoodDecoration(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.FOREST_ROCK);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_FERNS_DENSE);
        addRareSalmonberryBush(builder);
    }
    
    public static void pointedRedstone(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RuMiscOverworldPlacements.ORE_REDSTONE_LARGE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.LARGE_POINTED_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuMiscOverworldPlacements.POINTED_REDSTONE_CLUSTER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDSTONE_BUD);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDSTONE_BULB);
    }
    
    public static void netherPointedRedstone(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.POINTED_REDSTONE_NETHER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.LARGE_POINTED_REDSTONE_NETHER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuNetherPlacements.POINTED_REDSTONE_CLUSTER_NETHER);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDSTONE_BUD);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.REDSTONE_BULB);
    }

    public static void addHibiscus(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.HIBISCUS);
    }

    public static void addMallow(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.MALLOW);
    }

    public static void addWaratah(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WARATAH);
    }

    public static void addDaisies(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_DAISIES);
    }

    public static void addBamboo(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, RuVegetationPlacements.BAMBOO);
    }

    public static void addHyssop(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.HYSSOP);
    }
    
    public static void addPinkFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PINK_FLOWERS);
    }
    
    public static void bleedingHeart(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.FROZEN_FLOWERS);
    }
    
    public static void addWillowFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WILLOW_FLOWERS);
    }
    
    public static void addTulips(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TULIPS);
    }
    
    public static void shrublandFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SHRUBLAND_FLOWERS);
    }
    
    public static void addPrairieFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PRAIRIE_FLOWERS);
    }
    
    public static void addDirtSurfaceVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.DIRT_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.GRASS_VEGETATION);
    }
    
    public static void addTaigaFlowerPatch(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.AZURE_DAISY);
    }
    
    public static void addBayouVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.BAYOU_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.FLOWERING_LILY);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WILLOW_CYPRESS_SHRUB_MIX);
    }
    
    public static void addRareSalmonberryBush(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.RARE_SALMONBERRY_BUSH);
    }
    
    public static void addOrangeCornflower(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_ORANGE_CONEFLOWER);
    }
    
    public static void addTsubaki(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TSUBAKI);
    }
    
    public static void addLupineVegetation(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.PATCH_LUPINES);
    }
    
    public static void addTrillium(BiomeGenerationSettings.Builder builder){
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.WHITE_TRILLIUM);
    }
    
    public static void flowerFieldsFlowers(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.SMALL_FLOWERS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuVegetationPlacements.TALL_FLOWERS);
    }
    
    public static void sakuraTrees(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.MAGNOLIA);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.PINK_MAGNOLIA);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RuTreePlacements.WHITE_MAGNOLIA);
    }
    
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }
}
