package net.regions_unexplored.world.surface;

import com.google.common.collect.ImmutableList;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceConditions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.config.RuCommonConfig;
import net.regions_unexplored.registry.data.RUNoises;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.block.type.dirt.AshenDirtBlock;

import java.util.function.Supplier;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class RUSurfaceRuleBuilder {
    //FILL_BLOCKS
    private static final RuleSource AIR = makeStateRule(Blocks.CAVE_AIR);
    private static final RuleSource LAVA = makeStateRule(Blocks.LAVA);
    
    private static final RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final RuleSource END_STONE = makeStateRule(Blocks.END_STONE);
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    
    private static final RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    //NETHER_BLOCKS
    private static final RuleSource BRIMSPROUT_NYLIUM = makeStateRule(RUBlocks.BRIMSPROUT_NYLIUM.get());
    private static final RuleSource MYCOTOXIC_NYLIUM = makeStateRule(RUBlocks.MYCOTOXIC_NYLIUM.get());
    private static final RuleSource GLISTERING_NYLIUM = makeStateRule(RUBlocks.GLISTERING_NYLIUM.get());
    private static final RuleSource GLISTERING_WART = makeStateRule(RUBlocks.GLISTERING_WART.get());
    private static final RuleSource COBALT_NYLIUM = makeStateRule(RUBlocks.COBALT_NYLIUM.get());
    private static final RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);

    //stateRule Method
    private static RuleSource makeStateRule(Block block) {
        return state(block.defaultBlockState());
    }

    public static RuleSource nether() {
        ConditionSource above31 = yBlockCheck(VerticalAnchor.absolute(31), 0);
        ConditionSource above32 = yBlockCheck(VerticalAnchor.absolute(32), 0);
        ConditionSource start30 = yStartCheck(VerticalAnchor.absolute(30), 0);
        ConditionSource end35 = not(yStartCheck(VerticalAnchor.absolute(35), 0));
        ConditionSource belowTop5 = yBlockCheck(VerticalAnchor.belowTop(5), 0);
        ConditionSource hole = hole();
        ConditionSource soulSandLayerNoise = noiseCondition(Noises.SOUL_SAND_LAYER, -0.012D);
        ConditionSource gravelLayerNoise = noiseCondition(Noises.GRAVEL_LAYER, -0.012D);
        ConditionSource patchNoise = noiseCondition(Noises.PATCH, -0.012D);
        ConditionSource netherrackNoise = noiseCondition(Noises.NETHERRACK, 0.54D);
        ConditionSource wartNoise = noiseCondition(Noises.NETHER_WART, 1.17D);
        ConditionSource stateSelectorNoise = noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0D);
        RuleSource gravelPatch =
                ifTrue(patchNoise,
                        ifTrue(start30,
                                ifTrue(end35, GRAVEL)));

        return sequence(
                //Nether Roof/Floor
                ifTrue(verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                ifTrue(not(verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                ifTrue(belowTop5, NETHERRACK),

                ifTrue(isBiome(RUBiomes.INFERNAL_HOLT), sequence(ifTrue(UNDER_CEILING, ifTrue(stateSelectorNoise, ifTrue(not(UNDER_FLOOR), BLACKSTONE))), ifTrue(UNDER_FLOOR, ifTrue(netherrackNoise, BLACKSTONE)))),

                ifTrue(ON_FLOOR,
                        sequence(
                                ifTrue(not(above32), ifTrue(hole, LAVA)),
                                ifTrue(isBiome(RUBiomes.BLACKSTONE_BASIN), sequence(ifTrue(netherrackNoise, BLACKSTONE), ifTrue(above31, COBALT_NYLIUM))),
                                ifTrue(isBiome(RUBiomes.GLISTERING_MEADOW), ifTrue(not(noiseCondition(Noises.NETHERRACK, 0.45D)), ifTrue(above31, sequence(ifTrue(wartNoise, GLISTERING_WART), GLISTERING_NYLIUM)))),
                                ifTrue(isBiome(RUBiomes.MYCOTOXIC_UNDERGROWTH), ifTrue(not(netherrackNoise), ifTrue(above31, sequence(ifTrue(wartNoise, NETHERRACK), MYCOTOXIC_NYLIUM)))),
                                ifTrue(isBiome(RUBiomes.INFERNAL_HOLT), sequence(ifTrue(netherrackNoise, BLACKSTONE), ifTrue(above31, BRIMSPROUT_NYLIUM)))
                        )
                ),
                ifTrue(isBiome(RUBiomes.BLACKSTONE_BASIN), sequence(ifTrue(UNDER_CEILING, ifTrue(stateSelectorNoise, BLACKSTONE)), ifTrue(UNDER_FLOOR, BLACKSTONE))),
                ifTrue(isBiome(RUBiomes.REDSTONE_ABYSS), sequence(ifTrue(UNDER_FLOOR, ifTrue(soulSandLayerNoise, sequence(ifTrue(not(hole), ifTrue(start30, ifTrue(end35, SOUL_SAND))), NETHERRACK))), ifTrue(ON_FLOOR, ifTrue(above31, ifTrue(end35, ifTrue(gravelLayerNoise, sequence(ifTrue(above32, GRAVEL), ifTrue(not(hole), GRAVEL))))))))
        );
    }

    private static RuleSource block(Supplier<Block> block) {
        return SurfaceRules.state(block.get().defaultBlockState());
    }

    public static RuleSource end() {
        return END_STONE;
    }

    public static RuleSource air() {
        return AIR;
    }

    private static ConditionSource noiseAbove(ResourceKey<NormalNoise.NoiseParameters> noise, double min) {
        return noiseCondition(noise, min / 8.25D, Double.MAX_VALUE);
    }
    private static ConditionSource noiseBetween(ResourceKey<NormalNoise.NoiseParameters> noise, double min, double max) {
        return noiseCondition(noise, min / 8.25D, max / 8.25D);
    }

    private static ConditionSource surfaceNoiseAbove(double noise) {
        return noiseCondition(Noises.SURFACE, noise / 8.25D, Double.MAX_VALUE);
    }

    private static ConditionSource shieldNoise(double min, double max) {
        return noiseCondition(RUNoises.SHIELD, min / 8.25D, max / 8.25D);
    }

    private static ConditionSource shieldNoise(double noise) {
        return noiseCondition(RUNoises.SHIELD, noise / 8.25D, Double.MAX_VALUE);
    }

}