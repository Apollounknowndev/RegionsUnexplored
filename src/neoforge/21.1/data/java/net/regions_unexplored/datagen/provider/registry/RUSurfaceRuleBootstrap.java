package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceConditions;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.regions_unexplored.block.type.dirt.AshenDirtBlock;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUSurfaceRules;
import net.regions_unexplored.registry.tag.RUBiomeTags;

import java.util.function.Supplier;

import static dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules.*;
import static dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceConditions.*;
import static net.minecraft.world.level.levelgen.SurfaceRules.*;
import static net.regions_unexplored.registry.data.RUSurfaceRules.*;

public class RUSurfaceRuleBootstrap {
    private static final RuleSource GRASS_BLOCK = block(Blocks.GRASS_BLOCK);
    private static final RuleSource DIRT = block(Blocks.DIRT);
    private static final RuleSource COARSE_DIRT = block(Blocks.COARSE_DIRT);
    private static final RuleSource PODZOL = block(Blocks.PODZOL);
    private static final RuleSource MUD = block(Blocks.MUD);
    
    private static final RuleSource COBBLESTONE = block(Blocks.COBBLESTONE);
    private static final RuleSource STONE = block(Blocks.STONE);
    private static final RuleSource MOSSY_STONE = block(RUBlocks.MOSSY_STONE);
    private static final RuleSource GRAVEL = block(Blocks.GRAVEL);
    
    private static final RuleSource SAND = block(Blocks.SAND);
    private static final RuleSource SANDSTONE = block(Blocks.SANDSTONE);
    private static final RuleSource RED_SAND = block(Blocks.RED_SAND);
    private static final RuleSource RED_SANDSTONE = block(Blocks.RED_SANDSTONE);

    private static final RuleSource SNOW_BLOCK = block(Blocks.SNOW_BLOCK);
    private static final RuleSource TERRACOTTA = block(Blocks.TERRACOTTA);
    
    private static final ConditionSource RANDOM = noiseAbove(RUNoises.WEIGHTED, 0);
    
    public static void bootstrap(BootstrapContext<RuleSource> context) {
        ConditionSource aboveWater = waterBlockCheck(0, 0);
        ConditionSource notUnderwater = waterBlockCheck(-1, 0);
        ConditionSource notUnderDeepWater = waterStartCheck(-6, -1);
        ConditionSource deepslateGradient = verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8));
        
        RuleSource grassOrDirtIfUnderwater = sequence(ifTrue(aboveWater, GRASS_BLOCK), DIRT);
        RuleSource sandOrSandstoneIfCeiling = sequence(ifTrue(ON_CEILING, SANDSTONE), SAND);
        RuleSource redSandOrRedSandstoneIfCeiling = sequence(ifTrue(ON_CEILING, RED_SANDSTONE), RED_SAND);
        RuleSource gravelOrStoneIfCeiling = sequence(ifTrue(ON_CEILING, STONE), GRAVEL);
        
        RuleSource powderSnowUnderRule = ifTrue(
            allOf(noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D), aboveWater),
            block(Blocks.POWDER_SNOW)
        );
        RuleSource powderSnowSurfaceRule = ifTrue(
            allOf(noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D), aboveWater),
            block(Blocks.POWDER_SNOW)
        );
        
        var swamp = context.register(SWAMP, ifTrue(
            allOf(
                isBiome(RUBiomes.FEN),
                aboveY(62),
                belowY(63),
                noiseCondition(Noises.SWAMP, 0)
            ),
            block(Blocks.WATER)
        ));
        
        String prefix = "surface_and_under_surface";
        var surfaceAndUnderSurface = context.register(SURFACE_AND_UNDER_SURFACE, reference(HolderSet.direct(
            biome(context, prefix, sequence(
                ifTrue(belowY(138), DIRT),
                ifTrue(aboveY(186), SNOW_BLOCK),
                ifTrue(noiseBetween(Noises.CALCITE, -0.0125D, 0.0125D), block(Blocks.CALCITE)),
                STONE
            ), RUBiomes.REMOVED_MOUNTAINS),
            biomes(context, prefix + "/common/snow", ifTrue(notUnderwater, SNOW_BLOCK), RUBiomes.ICY_HEIGHTS, RUBiomes.SPIRES),
            biome(context, prefix, TERRACOTTA, RUBiomes.REMOVED_ARID_MOUNTAINS),
            biome(context, prefix, ifTrue(noiseAbove(0.25), TERRACOTTA), RUBiomes.BAOBAB_SAVANNA),
            biome(context, prefix, ifTrue(noiseAbove(RUNoises.SHIELD, 0), sandOrSandstoneIfCeiling), RUBiomes.JOSHUA_DESERT),
            biome(context, prefix, sandOrSandstoneIfCeiling, RUBiomes.SAGUARO_DESERT),
            biome(context, prefix, sequence(
                ifTrue(noiseBetween(RUNoises.SHIELD, -0.025, 0.025d), TERRACOTTA),
                ifTrue(noiseBetween(RUNoises.SHIELD,-0.06d, 0.06d), ifTrue(RANDOM, TERRACOTTA)),
                ifTrue(noiseAbove(RUNoises.SHIELD, 0.025d), redSandOrRedSandstoneIfCeiling)
            ), RUBiomes.OUTBACK),
            biome(context, prefix, sequence(
                ifTrue(belowY(66), gravelOrStoneIfCeiling),
                ifTrue(ON_FLOOR, waterSelector(RUBlocks.ALPHA_GRASS_BLOCK.get(), Blocks.DIRT)),
                DIRT
            ), RUBiomes.ALPHA_GROVE),
            biome(context, prefix, ifTrue(belowY(64), sandOrSandstoneIfCeiling), RUBiomes.TROPICS),
            biome(context, prefix, configSelector(RUBlocks.PEAT_MUD, Blocks.MUD), RUBiomes.MUDDY_RIVER),
            biomes(context, prefix + "/common/gravel", gravelOrStoneIfCeiling, RUBiomes.COLD_RIVER, RUBiomes.GRAVEL_BEACH),
            biomes(context, prefix + "/common/sand", sandOrSandstoneIfCeiling, RUBiomes.ROCKY_REEF, RUBiomes.TROPICAL_RIVER, RUBiomes.GRASSY_BEACH)
        )));
        
        prefix = "surface";
        var grassSurface = context.register(RUSurfaceRules.key("overworld/surface/common/grass"), waterSelector(GRASS_BLOCK, DIRT));
        
        var surface = context.register(SURFACE, reference(HolderSet.direct(
            biome(context, prefix, sequence(
                powderSnowSurfaceRule,
                ifTrue(aboveWater, SNOW_BLOCK)
            ), RUBiomes.FROZEN_PINE_TAIGA),
            surfaceAndUnderSurface,
            biome(context, prefix, sequence(
                ifTrue(
                    noiseAbove(0.21),
                    sequence(
                        ifTrue(noiseAbove(Noises.SWAMP, 0.25D), COBBLESTONE),
                        ifTrue(noiseAbove(Noises.SWAMP, 0.0D), GRAVEL),
                        STONE
                    )
                ),
                ifTrue(
                    noiseAbove(-0.06),
                    sequence(
                        ifTrue(noiseAbove(Noises.SWAMP, 0.25D), GRASS_BLOCK),
                        ifTrue(noiseAbove(Noises.SWAMP, -0.25D), COARSE_DIRT),
                        COBBLESTONE
                    )
                )
            ), RUBiomes.TOWERING_CLIFFS),
            biome(context, prefix, sequence(
                ifTrue(
                    noiseAbove(RUNoises.SHIELD, 0.2),
                    sequence(
                        ifTrue(noiseAbove(Noises.SWAMP, 0.25D), STONE),
                        ifTrue(noiseAbove(Noises.SWAMP, 0.0D), MOSSY_STONE),
                        STONE
                    )
                ),
                ifTrue(
                    noiseAbove(RUNoises.SHIELD, 0),
                    sequence(
                        ifTrue(noiseAbove(Noises.SWAMP, 0.25D), GRASS_BLOCK),
                        ifTrue(noiseAbove(Noises.SWAMP, -0.25D), COARSE_DIRT),
                        MOSSY_STONE
                    )
                )
            ), RUBiomes.MAPLE_FOREST),
            biome(context, prefix, sequence(
                ifTrue(noiseAbove(0.2), COARSE_DIRT),
                ifTrue(noiseAbove(-0.12), PODZOL)
            ), RUBiomes.DECIDUOUS_FOREST),
            biome(context, prefix, sequence(
                ifTrue(noiseAbove(0.2), configSelector(RUBlocks.PEAT_COARSE_DIRT, Blocks.COARSE_DIRT)),
                ifTrue(noiseAbove(-0.12), configSelector(RUBlocks.PEAT_PODZOL, Blocks.PODZOL))
            ), RUBiomes.PINE_TAIGA),
            biome(context, prefix, ifTrue(noiseAbove(0.15), ifTrue(
                anyOf(noiseAbove(RUNoises.SHIELD, 0.25), allOf(noiseAbove(RUNoises.SHIELD, 0.15), RANDOM)),
                configSelector(RUBlocks.SILT_COARSE_DIRT, Blocks.COARSE_DIRT))
            ), RUBiomes.DRY_BUSHLAND),
            biome(context, prefix, ifTrue(noiseAbove(0.15), ifTrue(
                anyOf(noiseAbove(RUNoises.SHIELD, 0.25), allOf(noiseAbove(RUNoises.SHIELD, 0.15), RANDOM)),
                configSelector(RUBlocks.SILT_PODZOL, Blocks.PODZOL))
            ), RUBiomes.AUTUMNAL_MAPLE_FOREST),
            biome(context, prefix, ifTrue(noiseAbove(0.15), configSelector(RUBlocks.PEAT_COARSE_DIRT, Blocks.COARSE_DIRT)), RUBiomes.FEN),
            biome(context, prefix, ifTrue(
                anyOf(noiseAbove(0.2), allOf(noiseAbove(-0.06), RANDOM)),
                block(Blocks.MYCELIUM)
            ), RUBiomes.FUNGAL_FEN),
            biome(context, prefix, sequence(
                ifTrue(
                    allOf(noiseBetween(RUNoises.SURFACE_MEDIUM, 0.18, 0.42), RANDOM),
                    sequence(
                        ifTrue(noiseAbove(RUNoises.WEIGHTED, 0.3), state(AshenDirtBlock.getSmouldering())),
                        block(RUBlocks.ASHEN_DIRT)
                    )
                ),
                ifTrue(noiseAbove(RUNoises.SURFACE_MEDIUM, 0.3), block(RUBlocks.ASH)),
                block(RUBlocks.ASHEN_DIRT)
            ), RUBiomes.ASHEN_WOODLAND),
            biome(context, prefix, ifTrue(
                allOf(belowY(64), noiseAbove(Noises.SWAMP, 0)),
                configSelector(RUBlocks.PEAT_MUD, Blocks.MUD)
            ), RUBiomes.BAYOU),
            biome(context, prefix, ifTrue(
                anyOf(noiseAbove(-0.11), noiseCondition(Noises.SWAMP, 0)),
                block(Blocks.MUD)
            ), RUBiomes.OLD_GROWTH_BAYOU),
            biome(context, prefix, ifTrue(allOf(belowY(65), noiseAbove(Noises.SWAMP, 0)), sandOrSandstoneIfCeiling), RUBiomes.TROPICS),
            biome(context, prefix, ifTrue(noiseAbove(0.2), COARSE_DIRT), RUBiomes.BAOBAB_SAVANNA),
            biome(context, prefix, COARSE_DIRT, RUBiomes.PINE_SLOPES),
            biome(context, prefix, PODZOL, RUBiomes.REDWOODS),
            biome(context, prefix, ifTrue(
                anyOf(noiseAbove(RUNoises.SURFACE_MEDIUM, 0.3), allOf(noiseAbove(RUNoises.SURFACE_MEDIUM, 0.25), RANDOM)),
                COARSE_DIRT
            ), RUBiomes.TUNDRA),
            biome(context, prefix, sequence(
                ifTrue(
                    anyOf(LithostitchedSurfaceConditions.slope(new InclusiveRange<>(3, Integer.MAX_VALUE)), not(aboveWater)),
                    block(RUBlocks.CHALK)
                ),
                block(RUBlocks.CHALK_GRASS_BLOCK)
            ), RUBiomes.CHALK_CLIFFS),
            biomes(context, prefix + "/common/peat", waterSelector(
                configSelector(RUBlocks.PEAT_GRASS_BLOCK, Blocks.GRASS_BLOCK),
                configSelector(RUBlocks.PEAT_DIRT, Blocks.DIRT)
            ), RUBiomeTags.SURFACE_PEAT),
            biomes(context, prefix + "/common/silt", waterSelector(
                configSelector(RUBlocks.SILT_GRASS_BLOCK, Blocks.GRASS_BLOCK),
                configSelector(RUBlocks.SILT_DIRT, Blocks.DIRT)
            ), RUBiomeTags.SURFACE_SILT)
            , grassSurface
        )));
        
        prefix = "under_surface";
        var dirtUnderSurface = context.register(RUSurfaceRules.key("overworld/under_surface/common/dirt"), DIRT);
        
        var underSurface = context.register(UNDER_SURFACE, reference(HolderSet.direct(
            biomes(context, prefix + "/common/powder_snow", powderSnowUnderRule, RUBiomes.ICY_HEIGHTS, RUBiomes.SPIRES, RUBiomes.FROZEN_PINE_TAIGA),
            surfaceAndUnderSurface,
            biome(context, prefix, ifTrue(noiseAbove(0.225), STONE), RUBiomes.TOWERING_CLIFFS),
            biome(context, prefix, ifTrue(noiseAbove(RUNoises.SHIELD, 0.175), STONE), RUBiomes.MAPLE_FOREST),
            biome(context, prefix, STONE, RUBiomes.HYACINTH_DEEPS),
            biome(context, prefix, block(RUBlocks.ASHEN_DIRT), RUBiomes.ASHEN_WOODLAND),
            biome(context, prefix, ifTrue(
                allOf(noiseAbove(-0.12), belowY(64), noiseAbove(Noises.SWAMP, 0)),
                MUD
            ), RUBiomes.OLD_GROWTH_BAYOU),
            biomes(context, prefix + "/common/peat", configSelector(RUBlocks.PEAT_DIRT, Blocks.DIRT), RUBiomeTags.SURFACE_PEAT),
            biomes(context, prefix + "/common/silt", configSelector(RUBlocks.SILT_DIRT, Blocks.DIRT), RUBiomeTags.SURFACE_SILT),
            dirtUnderSurface
        )));
        
        var caves = reference(HolderSet.direct(
            biome(context, "caves", ifTrue(
                allOf(noiseAbove(Noises.SWAMP, 0), RANDOM),
                block(RUBlocks.RAW_REDSTONE_BLOCK)
            ), RUBiomes.REDSTONE_CAVES),
            biome(context, "caves", ifTrue(
                ON_FLOOR,
                sequence(
                    ifTrue(deepslateGradient, waterSelector(RUBlocks.DEEPSLATE_VIRIDESCENT_NYLIUM.get(), Blocks.DEEPSLATE)),
                    waterSelector(RUBlocks.VIRIDESCENT_NYLIUM.get(), Blocks.STONE)
                )
            ), RUBiomes.BIOSHROOM_CAVES),
            biome(context, "caves", ifTrue(
                allOf(ON_FLOOR, noiseAbove(RUNoises.SHIELD, -0.12)),
                waterSelector(RUBlocks.ARGILLITE_GRASS_BLOCK.get(), RUBlocks.ARGILLITE.get())
            ), RUBiomes.ANCIENT_DELTA),
            biome(context, "caves", ifTrue(
                allOf(ON_FLOOR, anyOf(noiseAbove(0.06))),
                sequence(
                    ifTrue(deepslateGradient, waterSelector(RUBlocks.DEEPSLATE_PRISMOSS.get(), Blocks.DEEPSLATE)),
                    waterSelector(RUBlocks.PRISMOSS.get(), Blocks.STONE)
                )
            ), RUBiomes.PRISMACHASM)
        ));
        
        context.register(OVERWORLD, sequence(
            ifTrue(
                allOf(
                    abovePreliminarySurface(),
                    inBiomeTag(context, RUBiomeTags.ALL_OVERWORLD)
                ),
                sequence(
                    ifTrue(
                        ON_FLOOR,
                        sequence(
                            reference(swamp),
                            ifTrue(
                                notUnderwater,
                                reference(surface)
                            ),
                            ifTrue(
                                isBiome(RUBiomes.HYACINTH_DEEPS),
                                sequence(
                                    ifTrue(noiseCondition(Noises.SWAMP, 0.15), MOSSY_STONE),
                                    GRAVEL
                                )
                            )
                        )
                    ),
                    ifTrue(notUnderDeepWater, sequence(
                        ifTrue(VERY_DEEP_UNDER_FLOOR, sequence(
                            ifTrue(isBiome(RUBiomes.CHALK_CLIFFS), block(RUBlocks.CHALK)),
                            ifTrue(isBiome(RUBiomes.REMOVED_ARID_MOUNTAINS, RUBiomes.BAOBAB_SAVANNA), TERRACOTTA)
                        )),
                        ifTrue(UNDER_FLOOR, reference(underSurface)),
                        ifTrue(DEEP_UNDER_FLOOR, ifTrue(inBiomeTag(context, RUBiomeTags.SURFACE_SAND), SANDSTONE)),
                        ifTrue(VERY_DEEP_UNDER_FLOOR, sequence(
                            ifTrue(isBiome(RUBiomes.SAGUARO_DESERT), SANDSTONE),
                            ifTrue(isBiome(RUBiomes.ICY_HEIGHTS, RUBiomes.SPIRES), block(Blocks.PACKED_ICE))
                        ))
                    )),
                    ifTrue(ON_FLOOR, sequence(
                        ifTrue(isBiome(RUBiomes.ROCKY_REEF), sandOrSandstoneIfCeiling),
                        ifTrue(isBiome(RUBiomes.MUDDY_RIVER), configSelector(RUBlocks.PEAT_MUD, Blocks.MUD))
                    ))
                )
            ),
            ifTrue(
                allOf(UNDER_FLOOR, inBiomeTag(context, RUBiomeTags.COLLECTION_CAVES)),
                caves
            ),
            ifTrue(
                allOf(UNDER_CEILING, isBiome(RUBiomes.ANCIENT_DELTA)),
                block(RUBlocks.ARGILLITE)
            )
        ));
    }
    
    private static RuleSource configSelector(Supplier<Block> ruBlock, Block vanillaBlock) {
        return block(ruBlock.get());
    }
    
    private static RuleSource waterSelector(Block aboveWater, Block underWater) {
        return waterSelector(block(aboveWater), block(underWater));
    }
    
    private static RuleSource waterSelector(RuleSource aboveWater, RuleSource underWater) {
        return sequence(
            ifTrue(waterBlockCheck(0, 0), aboveWater),
            underWater
        );
    }
    
    private static ConditionSource noiseAbove(double min) {
        return noiseCondition(Noises.SURFACE, min, Double.MAX_VALUE);
    }
    
    private static ConditionSource noiseBetween(double min, double max) {
        return noiseCondition(Noises.SURFACE, min, max);
    }
    
    private static ConditionSource noiseAbove(ResourceKey<NormalNoise.NoiseParameters> noise, double min) {
        return noiseCondition(noise, min, Double.MAX_VALUE);
    }
    
    private static ConditionSource noiseBetween(ResourceKey<NormalNoise.NoiseParameters> noise, double min, double max) {
        return noiseCondition(noise, min, max);
    }
    
    private static ConditionSource aboveY(int y) {
        return yBlockCheck(VerticalAnchor.absolute(y), 0);
    }
    
    private static ConditionSource belowY(int y) {
        return not(yBlockCheck(VerticalAnchor.absolute(y), 0));
    }
    
    private static ConditionSource inBiomeTag(BootstrapContext<RuleSource> context, TagKey<Biome> tag) {
        HolderSet<Biome> biomes = context.lookup(Registries.BIOME).getOrThrow(tag);
        return LithostitchedSurfaceConditions.biome(biomes);
    }
    
    private static Holder<RuleSource> biome(BootstrapContext<RuleSource> context, String prefix, RuleSource source, ResourceKey<Biome> biome) {
        return context.register(RUSurfaceRules.key("overworld/" + prefix + "/" + biome.identifier().getPath()), ifTrue(
            isBiome(biome),
            source
        ));
    }
    
    @SafeVarargs
    private static Holder<RuleSource> biomes(BootstrapContext<RuleSource> context, String name, RuleSource source, ResourceKey<Biome>... biomes) {
        return context.register(RUSurfaceRules.key("overworld/" + name), ifTrue(
            isBiome(biomes),
            source
        ));
    }
    
    private static Holder<RuleSource> biomes(BootstrapContext<RuleSource> context, String name, RuleSource source, TagKey<Biome> tag) {
        return context.register(RUSurfaceRules.key("overworld/" + name), ifTrue(
            inBiomeTag(context, tag),
            source
        ));
    }
    
    private static RuleSource block(Supplier<Block> block) {
        return block(block.get());
    }
    
    private static RuleSource block(Block block) {
        return state(block.defaultBlockState());
    }
}
