package net.regions_unexplored.client.color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.config.state.client.RUClientConfig.EucalyptusColors;
import net.regions_unexplored.registry.RUBlocks;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class RUColors {
    public static void tintBlocks() {
        BlockColors colors = Minecraft.getInstance().getBlockColors();
        
        colors.register(
            List.of(BlockTintSources.grassBlock()),
            RUBlocks.PEAT_GRASS_BLOCK.get(),
            RUBlocks.SILT_GRASS_BLOCK.get(),
            RUBlocks.STONE_GRASS_BLOCK.get(),
            RUBlocks.ARGILLITE_GRASS_BLOCK.get(),
            RUBlocks.DEEPSLATE_GRASS_BLOCK.get(),
            RUBlocks.CHALK_GRASS_BLOCK.get(),
            RUBlocks.GRASS_SPROUTS.get(),
            RUBlocks.ORANGE_CONEFLOWER.get(),
            RUBlocks.PURPLE_CONEFLOWER.get(),
            RUBlocks.TASSEL.get(),
            RUBlocks.CLOVER.get(),
            RUBlocks.BLADED_GRASS.get(),
            RUBlocks.BLADED_TALL_GRASS.get()
        );
        
        colors.register(
            List.of(BlockTintSources.foliage()),
            RUBlocks.ELEPHANT_EAR.get(),
            RUBlocks.BAOBAB_NATURAL_SET.getLeaves(),
            RUBlocks.MAGNOLIA_NATURAL_SET.getLeaves(),
            RUBlocks.APPLE_OAK_NATURAL_SET.getLeaves(),
            RUBlocks.FLOWERING_NATURAL_SET.getLeaves(),
            RUBlocks.CYPRESS_NATURAL_SET.getLeaves(),
            RUBlocks.EUCALYPTUS_NATURAL_SET.getLeaves(),
            RUBlocks.PALM_NATURAL_SET.getLeaves(),
            RUBlocks.JOSHUA_NATURAL_SET.getLeaves(),
            RUBlocks.PINE_NATURAL_SET.getLeaves(),
            RUBlocks.REDWOOD_NATURAL_SET.getLeaves(),
            RUBlocks.WILLOW_NATURAL_SET.getLeaves(),
            RUBlocks.MAPLE_NATURAL_SET.getLeaves(),
            RUBlocks.MAPLE_LEAF_LITTER.get(),
            RUBlocks.WINDSWEPT_GRASS.get(),
            RUBlocks.SOCOTRA_NATURAL_SET.getLeaves(),
            RUBlocks.KAPOK_NATURAL_SET.getLeaves(),
            RUBlocks.KAPOK_VINES.get(),
            RUBlocks.KAPOK_VINES_PLANT.get()
        );
        
        colors.register(
            List.of(RUBlockTintSources.prismarite()),
            RUBlocks.HANGING_PRISMARITE.get(),
            RUBlocks.PRISMARITE_CLUSTER.get(),
            RUBlocks.LARGE_PRISMARITE_CLUSTER.get(),
            RUBlocks.PRISMOSS.get(),
            RUBlocks.DEEPSLATE_PRISMOSS.get(),
            RUBlocks.PRISMOSS_SPROUT.get()
        );
        
        colors.register(
            List.of(RUBlockTintSources.posBasedOrFoliage(RUColors::getRainbowGlassColor)),
            RUBlocks.PRISMAGLASS.get()
        );
        
        colors.register(
            List.of(RUBlockTintSources.posBasedOrFoliage(RUColors::getRainbowGlassColor)),
            RUBlocks.EUCALYPTUS_WOOD_SET.getLog(),
            RUBlocks.EUCALYPTUS_WOOD_SET.getWood()
        );
        
        colors.register(
            List.of(RUBlockTintSources.posBasedOrFoliage(RUColors::getRainbowGlassColor)),
            RUBlocks.SILVER_BIRCH_NATURAL_SET.getLeaves(),
            RUBlocks.SILVER_BIRCH_LEAF_LITTER.get()
        );
        
        colors.register(
            List.of(BlockTintSources.constant(0xff81cff9)),
            RUBlocks.SKY_WISTERIA_NATURAL_SET.getLeaves(),
            RUBlocks.SKY_WISTERIA_NATURAL_SET.getVines()
        );
        
        colors.register(
            List.of(BlockTintSources.constant(0xffc394ef)),
            RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getLeaves(),
            RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getVines()
        );
        
        colors.register(
            List.of(BlockTintSources.constant(0xffffa3ad)),
            RUBlocks.SALMON_WISTERIA_NATURAL_SET.getLeaves(),
            RUBlocks.SALMON_WISTERIA_NATURAL_SET.getVines()
        );
    }


    public static void tintItems() {
        // TODO: Fix. Oh god, oh no.
        /*TintHelper.tintItems((itemStack, i) -> GrassColor.get(0.5D, 1.0D),
                RUBlocks.PEAT_GRASS_BLOCK.get(),
                RUBlocks.SILT_GRASS_BLOCK.get(),
                RUBlocks.STONE_GRASS_BLOCK.get(),
                RUBlocks.ARGILLITE_GRASS_BLOCK.get(),
                RUBlocks.DEEPSLATE_GRASS_BLOCK.get(),
                RUBlocks.CHALK_GRASS_BLOCK.get(),
                RUBlocks.GRASS_SPROUTS.get(),
                RUBlocks.BLADED_GRASS.get(),
                RUBlocks.CLOVER.get(),
                RUBlocks.BLADED_TALL_GRASS.get()
        );

        TintHelper.tintItems((itemStack, i) -> FoliageColor.get(0.5D, 1.0D),
                RUBlocks.ELEPHANT_EAR.get(),
                RUBlocks.BAOBAB_NATURAL_SET.getLeaves(),
                RUBlocks.MAGNOLIA_NATURAL_SET.getLeaves(),
                RUBlocks.APPLE_OAK_NATURAL_SET.getLeaves(),
                RUBlocks.FLOWERING_NATURAL_SET.getLeaves(),
                RUBlocks.JOSHUA_NATURAL_SET.getLeaves(),
                RUBlocks.CYPRESS_NATURAL_SET.getLeaves(),
                RUBlocks.EUCALYPTUS_NATURAL_SET.getLeaves(),
                RUBlocks.PALM_NATURAL_SET.getLeaves(),
                RUBlocks.PINE_NATURAL_SET.getLeaves(),
                RUBlocks.REDWOOD_NATURAL_SET.getLeaves(),
                RUBlocks.WILLOW_NATURAL_SET.getLeaves(),
                RUBlocks.MAPLE_NATURAL_SET.getLeaves(),
                RUBlocks.MAPLE_LEAF_LITTER.get(),
                RUBlocks.WINDSWEPT_GRASS.get(),
                RUBlocks.SOCOTRA_NATURAL_SET.getLeaves(),
                RUBlocks.KAPOK_NATURAL_SET.getLeaves(),
                RUBlocks.KAPOK_VINES.get(),
                RUBlocks.KAPOK_VINES_PLANT.get()
        );
        
        TintHelper.tintItems((stack, index) -> 0x81cff9, RUBlocks.SKY_WISTERIA_NATURAL_SET.getLeaves());
        TintHelper.tintItems((stack, index) -> 0xc394ef, RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getLeaves());
        TintHelper.tintItems((stack, index) -> 0xffa3ad, RUBlocks.SALMON_WISTERIA_NATURAL_SET.getLeaves());
        
        TintHelper.tintItems((stack, index) -> 0x81cff9, RUBlocks.SKY_WISTERIA_NATURAL_SET.getVines());
        TintHelper.tintItems((stack, index) -> 0xc394ef, RUBlocks.LAVENDER_WISTERIA_NATURAL_SET.getVines());
        TintHelper.tintItems((stack, index) -> 0xffa3ad, RUBlocks.SALMON_WISTERIA_NATURAL_SET.getVines());*/
    }

    public static int getAspenColor(BlockPos pos) {
        Color aspen = Color.getHSBColor(((Mth.sin(((float)pos.getX()/10) + Mth.sin(((float)pos.getZ() + (float)pos.getX()) / 50) * 3)) / 75)+0.15F, 0.75F, 0.8F);
        return aspen.getRGB();
    }

    public static int getRainbowColor(BlockPos pos, float brightness) {
        return getRainbowColor(pos.getX(), pos.getZ(), brightness);
    }
    
    public static int getRainbowColor(float x, float z, float saturation) {
        Color rainbow = Color.getHSBColor((x + z) / 50.0F, saturation, 1.0F);
        return rainbow.getRGB();
    }

    private static int getRainbowEucalyptusColor(@NotNull BlockPos pos) {
        EucalyptusColors eucalyptusColors = RUConfigHandler.CLIENT.eucalyptusColors;
        Color rainbow = Color.getHSBColor(
            (pos.getX() + pos.getY() + pos.getZ()) / (float) eucalyptusColors.transitionSize,
            (float) eucalyptusColors.saturation,
            (float) eucalyptusColors.brightness
        );
        return rainbow.getRGB();
    }

    private static int getRainbowGlassColor(BlockPos pos) {
        Color rainbow = Color.getHSBColor(((float)pos.getX() + (float)pos.getY() + (float)pos.getZ()) / 35.0F, 1.0F, 1.0F);
        return rainbow.getRGB();
    }


    public static int getPrismariteSparkleColor(BlockPos pos) {
        Color baseColor = new Color(getRainbowColor(pos, 0.9f));
        int average = (baseColor.getRed() + baseColor.getGreen() + baseColor.getBlue()) / 3;
        return new Color(
            Math.min(255, baseColor.getRed() + average),
            Math.min(255, baseColor.getGreen() + average),
            Math.min(255, baseColor.getBlue() + average)
        ).getRGB();
    }
}
