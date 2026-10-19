package net.regions_unexplored.block.compat;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.set.NaturalSet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CompostableBlocks {
    public static void setup(BiConsumer<ItemLike, Float> consumer) {
        // Saplings (0.3F)
        for (Block block : RUBlocks.NATURAL_SETS.stream().map(NaturalSet::getSapling).toList()) {
            if (block == null) continue;
            consumer.accept(block, 0.3f);
        }

        // Leaves (0.3F)
        for (Block block : RUBlocks.NATURAL_SETS.stream().map(NaturalSet::getLeaves).toList()) {
            if (block == null) continue;
            consumer.accept(block, 0.3f);
        }
        
        // Vines (0.3F)
        for (Block block : RUBlocks.NATURAL_SETS.stream().map(NaturalSet::getVines).toList()) {
            if (block == null) continue;
            consumer.accept(block, 0.3f);
        }

        // Branches (0.3F)
        for (Block block : RUBlocks.NATURAL_SETS.stream().map(NaturalSet::getBranch).toList()) {
            if (block == null) continue;
            consumer.accept(block, 0.3f);
        }

        // Shrubs (0.65F)
        for (Block block : RUBlocks.NATURAL_SETS.stream().map(NaturalSet::getShrub).toList()) {
            if (block == null) continue;
            consumer.accept(block, 0.65f);
        }

        // Bioshrooms (0.35F)
        consumer.accept(RUBlocks.GREEN_BIOSHROOM.get(), 0.35F);
        consumer.accept(RUBlocks.BLUE_BIOSHROOM.get(), 0.35F);
        consumer.accept(RUBlocks.PINK_BIOSHROOM.get(), 0.35F);
        consumer.accept(RUBlocks.YELLOW_BIOSHROOM.get(), 0.35F);

        // Tall Bioshrooms (0.65F)
        consumer.accept(RUBlocks.TALL_GREEN_BIOSHROOM.get(), 0.65F);
        consumer.accept(RUBlocks.TALL_BLUE_BIOSHROOM.get(), 0.65F);
        consumer.accept(RUBlocks.TALL_PINK_BIOSHROOM.get(), 0.65F);
        consumer.accept(RUBlocks.TALL_YELLOW_BIOSHROOM.get(), 0.65F);

        // Small grass/shrubs (0.3F)
        consumer.accept(RUBlocks.TALL_DEAD_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.FROZEN_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.SANDY_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.RED_SANDY_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.SHORT_DEAD_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.GRASS_SPROUTS.get(), 0.3F);

        // Tall grass (0.5F)
        consumer.accept(RUBlocks.ELEPHANT_EAR.get(), 0.5F);
        consumer.accept(RUBlocks.TALL_SANDY_GRASS.get(), 0.5F);
        consumer.accept(RUBlocks.TALL_RED_SANDY_GRASS.get(), 0.5F);
        consumer.accept(RUBlocks.WINDSWEPT_GRASS.get(), 0.5F);

        // Flowers (0.4F)
        consumer.accept(RUBlocks.ALPHA_DANDELION.get(), 0.4F);
        consumer.accept(RUBlocks.ALPHA_ROSE.get(), 0.4F);
        consumer.accept(RUBlocks.ASTER.get(), 0.4F);
        consumer.accept(RUBlocks.BLEEDING_HEART.get(), 0.4F);
        consumer.accept(RUBlocks.BLUE_LUPINE.get(), 0.4F);
        consumer.accept(RUBlocks.DAISY.get(), 0.4F);
        consumer.accept(RUBlocks.DORCEL.get(), 0.4F);
        consumer.accept(RUBlocks.FELICIA_DAISY.get(), 0.4F);
        consumer.accept(RUBlocks.FIREWEED.get(), 0.4F);
        consumer.accept(RUBlocks.HIBISCUS.get(), 0.4F);
        consumer.accept(RUBlocks.HYSSOP.get(), 0.4F);
        consumer.accept(RUBlocks.MALLOW.get(), 0.4F);
        consumer.accept(RUBlocks.PINK_LUPINE.get(), 0.4F);
        consumer.accept(RUBlocks.POPPY_BUSH.get(), 0.4F);
        consumer.accept(RUBlocks.SALMON_POPPY.get(), 0.4F);
        consumer.accept(RUBlocks.SALMON_POPPY_BUSH.get(), 0.4F);
        consumer.accept(RUBlocks.PURPLE_LUPINE.get(), 0.4F);
        consumer.accept(RUBlocks.RED_LUPINE.get(), 0.4F);
        consumer.accept(RUBlocks.WARATAH.get(), 0.4F);
        consumer.accept(RUBlocks.WHITE_TRILLIUM.get(), 0.4F);
        consumer.accept(RUBlocks.WILTING_TRILLIUM.get(), 0.4F);
        consumer.accept(RUBlocks.YELLOW_LUPINE.get(), 0.4F);
        consumer.accept(RUBlocks.TSUBAKI.get(), 0.4F);

        // Coneflowers (0.25F)
        consumer.accept(RUBlocks.ORANGE_CONEFLOWER.get(), 0.25F);
        consumer.accept(RUBlocks.PURPLE_CONEFLOWER.get(), 0.25F);
        consumer.accept(RUBlocks.BLUE_MAGNOLIA_FLOWERS.get(), 0.25F);
        consumer.accept(RUBlocks.PINK_MAGNOLIA_FLOWERS.get(), 0.25F);
        consumer.accept(RUBlocks.WHITE_MAGNOLIA_FLOWERS.get(), 0.25F);

        // Clover (0.2F)
        consumer.accept(RUBlocks.CLOVER.get(), 0.2F);

        // Snowbelles (0.4F)
        for (Block snowbelle : RUBlocks.SNOWBELLES.getAll()) {
            consumer.accept(snowbelle, 0.4f);
        }

        // Leaf piles (0.1F)
        consumer.accept(RUBlocks.MAPLE_LEAF_LITTER.get(), 0.1F);
        consumer.accept(RUBlocks.RED_MAPLE_LEAF_LITTER.get(), 0.1F);
        consumer.accept(RUBlocks.ORANGE_MAPLE_LEAF_LITTER.get(), 0.1F);
        consumer.accept(RUBlocks.SILVER_BIRCH_LEAF_LITTER.get(), 0.1F);

        // Tall plants (0.6F)
        consumer.accept(RUBlocks.MEADOW_SAGE.get(), 0.6F);
        consumer.accept(RUBlocks.BARLEY.get(), 0.6F);
        consumer.accept(RUBlocks.CATTAIL.get(), 0.6F);
        consumer.accept(RUBlocks.TASSEL.get(), 0.6F);
        consumer.accept(RUBlocks.DAY_LILY.get(), 0.6F);

        // Cacti and vines (varied)
        consumer.accept(RUBlocks.BARREL_CACTUS.get(), 0.4F);
        consumer.accept(RUBlocks.CAVE_HYSSOP.get(), 0.4F);
        consumer.accept(RUBlocks.DUCKWEED.get(), 0.15F);
        consumer.accept(RUBlocks.SPANISH_MOSS.get(), 0.3F);
        consumer.accept(RUBlocks.KAPOK_VINES.get(), 0.3F);
        consumer.accept(RUBlocks.FLOWERING_LILY_PAD.get(), 0.3F);

        // Berries (0.25F)
        consumer.accept(RUBlocks.SALMONBERRY_BUSH.get(), 0.25F);

        // Misc plants (varied)
        consumer.accept(RUBlocks.PRISMOSS_SPROUT.get(), 0.2F);
        consumer.accept(RUBlocks.CORPSE_FLOWER.get(), 0.6F);
        consumer.accept(RUBlocks.BLADED_GRASS.get(), 0.3F);
        consumer.accept(RUBlocks.BLADED_TALL_GRASS.get(), 0.5F);
        consumer.accept(RUBlocks.DROPLEAF.get(), 0.15F);
        consumer.accept(RUBlocks.DUSKMELON.get(), 0.5F);
        consumer.accept(RUBlocks.DUSKTRAP.get(), 0.3F);

        // Brimsprout (0.1F)
        consumer.accept(RUBlocks.BRIMSPROUT.get(), 0.1F);

        // Cobalt plants (varied)
        consumer.accept(RUBlocks.COBALT_EARLIGHT.get(), 0.3F);
        consumer.accept(RUBlocks.TALL_COBALT_EARLIGHT.get(), 0.35F);
        consumer.accept(RUBlocks.COBALT_ROOTS.get(), 0.2F);
        consumer.accept(RUBlocks.HANGING_EARLIGHT.get(), 0.1F);

        // Glistering plants (varied)
        consumer.accept(RUBlocks.GLISTERING_IVY.get(), 0.1F);
        consumer.accept(RUBlocks.GLISTERING_SPROUT.get(), 0.2F);
        consumer.accept(RUBlocks.GLISTER_BULB.get(), 0.4F);
        consumer.accept(RUBlocks.GLISTER_SPIRE.get(), 0.3F);

        // Mycotoxic plants (varied)
        consumer.accept(RUBlocks.MYCOTOXIC_MUSHROOMS.get(), 0.1F);
        consumer.accept(RUBlocks.MYCOTOXIC_DAISY.get(), 0.3F);
        consumer.accept(RUBlocks.MYCOTOXIC_GRASS.get(), 0.2F);
    }
}