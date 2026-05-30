package net.regions_unexplored.block.sapling;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import static net.regions_unexplored.registry.data.RUConfiguredFeatures.*;

public interface RUTreeGrowers {
    TreeGrower ASHEN = create("ashen", TREE_ASHEN);
    TreeGrower ALPHA_OAK = create("alpha_oak", TREE_ALPHA_OAK);
    TreeGrower APPLE_OAK = create("apple_oak", 0.2F, TREE_APPLE_OAK, TREE_BIG_APPLE_OAK);

    TreeGrower BLUE_BIOSHROOM = create("blue_bioshroom", TREE_GIANT_BLUE_BIOSHROOM);
    TreeGrower GREEN_BIOSHROOM = create("green_bioshroom", TREE_GIANT_GREEN_BIOSHROOM);
    TreeGrower PINK_BIOSHROOM = create("pink_bioshroom", TREE_GIANT_PINK_BIOSHROOM);
    TreeGrower YELLOW_BIOSHROOM = create("yellow_bioshroom", TREE_YELLOW_BIOSHROOM_LARGE);
    TreeGrower BAMBOO = create("bamboo", TREE_BAMBOO);
    RuUltraFromMegaTreeGrower BAOBAB = new RuUltraFromMegaTreeGrower("baobab", of(TREE_ULTRA_BAOBAB), of(TREE_MEGA_BAOBAB), empty(), empty());
    TreeGrower BLACKWOOD = new TreeGrower("blackwood", 0.1F, of(TREE_GIANT_BLACKWOOD), empty(), of(TREE_BIG_BLACKWOOD), of(TREE_BLACKWOOD), empty(), empty());
    TreeGrower SAGUARO_CACTUS = new TreeGrower("saguaro_cactus", empty(), of(TREE_SAGUARO_CACTUS), empty());
    TreeGrower CYPRESS = new TreeGrower("cypress", 0.25F, empty(), empty(), of(TREE_CYPRESS), of(TREE_GIANT_CYPRESS), empty(), empty());
    TreeGrower DEAD_PINE = new TreeGrower("dead_pine", 0.1F, empty(), empty(), of(TREE_DEAD_PINE), of(TREE_DEAD_STRIPPED_PINE), of(TREE_DEAD_PINE_TALL), of(TREE_DEAD_STRIPPED_PINE_MOUNTAIN));
    TreeGrower DEAD = new TreeGrower("dead", 0.1F, empty(), empty(), of(TREE_DEAD), of(TREE_BIG_DEAD), of(TREE_DEAD_BOG), empty());
    TreeGrower EUCALYPTUS = new TreeGrower("eucalyptus", 0.33F, empty(), empty(), of(TREE_EUCALYPTUS), of(TREE_SMALL_EUCALYPTUS), empty(), empty());
    TreeGrower FLOWERING_OAK = new TreeGrower("flowering_oak", 0.2F, empty(), empty(), of(TREE_FLOWERING_OAK), of(TREE_BIG_FLOWERING_OAK), empty(), empty());
    TreeGrower GOLDEN_LARCH = new TreeGrower("golden_larch", 0.25F, empty(), empty(), of(TREE_LARCH_GOLDEN), of(TREE_LARCH_GOLDEN_LARGE), empty(), empty());
    TreeGrower LARCH = new TreeGrower("larch", 0.25F, empty(), empty(), of(TREE_LARCH), of(TREE_LARCH_LARGE), empty(), empty());
    TreeGrower JOSHUA = new TreeGrower("joshua", 0.33F, empty(), empty(), of(TREE_JOSHUA_MEDIUM), of(TREE_JOSHUA_LARGE), empty(), empty());
    TreeGrower MAPLE = new TreeGrower("maple", 0.1F, empty(), empty(), of(TREE_MAPLE), of(TREE_BIG_MAPLE), empty(), empty());
    TreeGrower RED_MAPLE = new TreeGrower("red_maple", 0.1F, empty(), empty(), of(TREE_RED_MAPLE), of(TREE_BIG_RED_MAPLE), empty(), empty());
    TreeGrower ORANGE_MAPLE = new TreeGrower("orange_maple", 0.1F, empty(), empty(), of(TREE_ORANGE_MAPLE), of(TREE_BIG_ORANGE_MAPLE), empty(), empty());
    TreeGrower BRIMWOOD = new TreeGrower("brimwood", 0.25F, empty(), empty(), of(TREE_BRIM_WILLOW), of(TREE_TALL_BRIM_WILLOW), empty(), empty());
    TreeGrower COBALT = new TreeGrower("cobalt", empty(), of(TREE_COBALT), empty());
    TreeGrower SILVER_BIRCH = new TreeGrower("silver_birch", 0.25F, empty(), empty(), of(TREE_SILVER_BIRCH), of(TREE_SILVER_BIRCH_TALL), empty(), empty());
    TreeGrower SMALL_OAK = new TreeGrower("small_oak", empty(), of(TREE_SMALL_OAK), empty());
    TreeGrower SOCOTRA = new TreeGrower("socotra", 0.1F, empty(), empty(), of(TREE_LARGE_SOCOTRA), of(TREE_SMALL_SOCOTRA), empty(), empty());
    TreeGrower WILLOW = new TreeGrower("willow", 0.1F, empty(), empty(), of(TREE_WILLOW), of(TREE_BIG_WILLOW), empty(), empty());
    TreeGrower MAGNOLIA = new TreeGrower("magnolia", 0.1F, empty(), empty(), of(TREE_MAGNOLIA), of(TREE_BIG_MAGNOLIA), empty(), empty());
    TreeGrower WHITE_MAGNOLIA = new TreeGrower("white_magnolia", 0.1F, empty(), empty(), of(TREE_WHITE_MAGNOLIA), of(TREE_BIG_WHITE_MAGNOLIA), empty(), empty());
    TreeGrower PINK_MAGNOLIA = new TreeGrower("pink_magnolia", 0.1F, empty(), empty(), of(TREE_PINK_MAGNOLIA), of(TREE_BIG_PINK_MAGNOLIA), empty(), empty());
    TreeGrower BLUE_MAGNOLIA = new TreeGrower("blue_magnolia", 0.1F, empty(), empty(), of(TREE_BLUE_MAGNOLIA), of(TREE_BIG_BLUE_MAGNOLIA), empty(), empty());
    RuUltraFromSuperTreeGrower KAPOK = new RuUltraFromSuperTreeGrower("kapok", empty(), of(TREE_KAPOK), empty(), empty());
    RuUltraFromSuperTreeGrower REDWOOD = new RuUltraFromSuperTreeGrower("redwood", of(TREE_REDWOOD_LARGE), of(TREE_REDWOOD_MEDIUM), of(TREE_REDWOOD_SMALL), empty());
    TreeGrower PALM = create("palm", TREE_PALM, TREE_TALL_PALM);
    TreeGrower PINE = create("pine", TREE_PINE, TREE_PINE_TALL);
    TreeGrower SKY_WISTERIA = create("sky_wisteria", TREE_WISTERIA_SKY, TREE_WISTERIA_LARGE_SKY);
    TreeGrower LAVENDER_WISTERIA = create("lavender_wisteria", TREE_WISTERIA_LAVENDER, TREE_WISTERIA_LARGE_LAVENDER);
    TreeGrower SALMON_WISTERIA = create("salmon_wisteria", TREE_WISTERIA_SALMON, TREE_WISTERIA_LARGE_SALMON);
    
    static TreeGrower create(
        final String name,
        final ResourceKey<ConfiguredFeature<?, ?>> tree
    ) {
        return new TreeGrower(name, 0.0f, Optional.empty(), Optional.empty(), Optional.of(tree), Optional.empty(), Optional.empty(), Optional.empty());
    }
    
    static TreeGrower create(
        final String name,
        final ResourceKey<ConfiguredFeature<?, ?>> tree,
        final ResourceKey<ConfiguredFeature<?, ?>> secondaryTree
    ) {
        return create(name, 0.1f, tree, secondaryTree);
    }
    
    static TreeGrower create(
        final String name,
        float secondaryChance,
        final ResourceKey<ConfiguredFeature<?, ?>> tree,
        final ResourceKey<ConfiguredFeature<?, ?>> secondaryTree
    ) {
        return new TreeGrower(name, secondaryChance, Optional.empty(), Optional.empty(), Optional.of(tree), Optional.of(secondaryTree), Optional.empty(), Optional.empty());
    }
}
