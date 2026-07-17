package net.regions_unexplored.datagen.provider;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.EnterBlockTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.common.Tags;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.tag.RUItemTags;
import net.regions_unexplored.registry.RUItems;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RURecipeProvider extends RecipeProvider {
    public static final Supplier<ImmutableList<ItemLike>> REDSTONE_SMELTABLES = Suppliers.memoize(() -> ImmutableList.of(RUBlocks.RAW_REDSTONE_BLOCK.get(), RUBlocks.REDSTONE_BULB.get()));
    public static final Supplier<ImmutableList<ItemLike>> MOSSY_STONE_SMELTABLES = Suppliers.memoize(() ->ImmutableList.of(Blocks.MOSSY_COBBLESTONE));

    public RURecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        
        this.shaped(RecipeCategory.MISC, RUItems.IRIDESCENT_RING.get(), 1)
            .define('P', RUBlocks.PRISMARITE_CLUSTER.get())
            .define('G', Items.GOLD_INGOT)
            .pattern(" P ")
            .pattern("G G")
            .pattern(" G ")
            .unlockedBy("has_prismarite_cluster", has(RUBlocks.PRISMARITE_CLUSTER.get()))
            .save(this.output);
        
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.PRISMOSS.get(), 1)
            .define('#', Blocks.STONE)
            .define('X', RUBlocks.PRISMOSS_SPROUT.get())
            .pattern("X")
            .pattern("#")
            .group("prismoss")
            .unlockedBy("has_stone", has(Blocks.STONE))
            .save(this.output);
        
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.DEEPSLATE_PRISMOSS.get(), 1).define('#', Blocks.DEEPSLATE).define('X', RUBlocks.PRISMOSS_SPROUT.get()).pattern("X").pattern("#").group("prismoss").unlockedBy("has_deepslate", has(Blocks.DEEPSLATE)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.HANGING_PRISMARITE.get(), 1).define('#', RUBlocks.PRISMARITE_CLUSTER.get()).pattern("#").pattern("#").pattern("#").group("prismarite").unlockedBy("has_prismarite", has(RUBlocks.PRISMARITE_CLUSTER.get())).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.LARGE_PRISMARITE_CLUSTER.get(), 1).define('#', RUBlocks.PRISMARITE_CLUSTER.get()).pattern("#").pattern("#").group("prismarite").unlockedBy("has_prismarite", has(RUBlocks.PRISMARITE_CLUSTER.get())).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.PRISMAGLASS.get(), 2).define('#', Tags.Items.GLASS_BLOCKS).define('X', RUItemTags.PRISMARITE_CRYSTALS).pattern(" X ").pattern("X#X").pattern(" X ").group("stained_glass").unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS)).save(this.output);

        this.shaped(RecipeCategory.REDSTONE, RUBlocks.RAW_REDSTONE_BLOCK.get(), 1).define('#', Items.REDSTONE).pattern("##").pattern("##").group("redstone").unlockedBy("has_redstone", has(Items.REDSTONE)).save(this.output);
        oreSmelting(REDSTONE_SMELTABLES.get(), RecipeCategory.REDSTONE, CookingBookCategory.MISC, Items.REDSTONE, 0.7F, 200, "redstone");
        oreBlasting(REDSTONE_SMELTABLES.get(), RecipeCategory.REDSTONE, CookingBookCategory.MISC, Items.REDSTONE, 0.7F, 100, "redstone");
        this.shaped(RecipeCategory.REDSTONE, RUBlocks.REDSTONE_BUD.get(), 2).define('#', Items.REDSTONE).pattern("###").group("redstone").unlockedBy("has_redstone", has(Items.REDSTONE)).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.STONE_GRASS_BLOCK.get(), 1).define('#', Blocks.STONE).define('X', RUItemTags.GRASS).pattern("X").pattern("#").group("stone_grass").unlockedBy("has_stone", has(Blocks.STONE)).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.DEEPSLATE_GRASS_BLOCK.get(), 1).define('#', Blocks.DEEPSLATE).define('X', RUItemTags.GRASS).pattern("X").pattern("#").group("stone_grass").unlockedBy("has_deepslate", has(Blocks.DEEPSLATE)).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.VIRIDESCENT_NYLIUM.get(), 1).define('#', Blocks.STONE).define('X', TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "mushrooms"))).pattern("X").pattern("#").group("viridescent_nylium").unlockedBy("has_stone", has(Blocks.STONE)).unlockedBy("has_mushroom", has(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "mushrooms")))).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.DEEPSLATE_VIRIDESCENT_NYLIUM.get(), 1).define('#', Blocks.DEEPSLATE).define('X', TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "mushrooms"))).pattern("X").pattern("#").group("viridescent_nylium").unlockedBy("has_deepslate", has(Blocks.DEEPSLATE)).unlockedBy("has_mushroom", has(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "mushrooms")))).save(this.output);

        /*-----------------PLANTS-----------------*/
        this.oneToOneConversionRecipe(Items.BROWN_DYE, RUBlocks.TALL_DEAD_GRASS.get(), "brown_dye");
        this.oneToOneConversionRecipe(Items.BROWN_DYE, RUBlocks.SHORT_DEAD_GRASS.get(), "brown_dye");


        this.oneToOneConversionRecipe(Items.YELLOW_DYE, RUBlocks.ALPHA_DANDELION.get(), "yellow_dye");
        this.oneToOneConversionRecipe(Items.RED_DYE, RUBlocks.ALPHA_ROSE.get(), "red_dye");
        this.oneToOneConversionRecipe(Items.MAGENTA_DYE, RUBlocks.BLEEDING_HEART.get(), "magenta_dye");
        this.oneToOneConversionRecipe(Items.BLUE_DYE, RUBlocks.BLUE_LUPINE.get(), "blue_dye");
        this.oneToOneConversionRecipe(Items.LIGHT_GRAY_DYE, RUBlocks.DAISY.get(), "light_gray_dye");
        this.oneToOneConversionRecipe(Items.BLACK_DYE, RUBlocks.DORCEL.get(), "black_dye");
        this.oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, RUBlocks.FELICIA_DAISY.get(), "light_blue_dye");
        this.oneToOneConversionRecipe(Items.MAGENTA_DYE, RUBlocks.FIREWEED.get(), "magenta_dye");
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.GLISTERING_BLOOM.get(), "pink_dye");
        this.oneToOneConversionRecipe(Items.YELLOW_DYE, RUBlocks.HIBISCUS.get(), "yellow_dye");
        this.oneToOneConversionRecipe(Items.ORANGE_DYE, RUBlocks.MALLOW.get(), "orange_dye");
        this.oneToOneConversionRecipe(Items.PURPLE_DYE, RUBlocks.HYSSOP.get(), "purple_dye");
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.PINK_LUPINE.get(), "pink_dye");
        this.oneToOneConversionRecipe(Items.RED_DYE, RUBlocks.POPPY_BUSH.get(), "red_dye");
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.SALMON_POPPY.get(), "pink_dye");
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.SALMON_POPPY_BUSH.get(), "pink_dye");
        this.oneToOneConversionRecipe(Items.PURPLE_DYE, RUBlocks.PURPLE_LUPINE.get(), "purple_dye");
        this.oneToOneConversionRecipe(Items.RED_DYE, RUBlocks.RED_LUPINE.get(), "red_dye");
        this.oneToOneConversionRecipe(Items.RED_DYE, RUBlocks.WARATAH.get(), "red_dye");
        this.oneToOneConversionRecipe(Items.WHITE_DYE, RUBlocks.WHITE_TRILLIUM.get(), "white_dye");
        this.oneToOneConversionRecipe(Items.BROWN_DYE, RUBlocks.WILTING_TRILLIUM.get(), "brown_dye");
        this.oneToOneConversionRecipe(Items.YELLOW_DYE, RUBlocks.YELLOW_LUPINE.get(), "yellow_dye");
        this.oneToOneConversionRecipe(Items.RED_DYE, RUBlocks.TSUBAKI.get(), "red_dye");

        //SNOWBELLE
        for (Map.Entry<DyeColor, Block> entry : RUBlocks.SNOWBELLES.getMap().entrySet()) {
            this.oneToOneConversionRecipe(BuiltInRegistries.ITEM.getValue(Identifier.withDefaultNamespace(entry.getKey().getSerializedName() + "_dye")), entry.getValue(), entry.getKey().getName() + "_dye");
            snowbelle(entry.getValue(), TagKey.create(Registries.ITEM, cId("dyes/" + entry.getKey().getName())));
        }

        this.oneToOneConversionRecipe(Items.ORANGE_DYE, RUBlocks.ORANGE_CONEFLOWER.get(), "orange_dye");
        this.oneToOneConversionRecipe(Items.PURPLE_DYE, RUBlocks.PURPLE_CONEFLOWER.get(), "purple_dye");
        this.oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, RUBlocks.ASTER.get(), "light_blue_dye");

        this.oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, RUBlocks.BLUE_MAGNOLIA_FLOWERS.get(), "light_blue_dye");
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.BLUE_MAGNOLIA_FLOWERS.get(), 6).define('#', RUBlocks.BLUE_MAGNOLIA_NATURAL_SET.getLeaves()).pattern("###").group("multiface_flowers").unlockedBy("has_blue_magnolia_leaves", has(RUBlocks.BLUE_MAGNOLIA_NATURAL_SET.getLeaves())).save(this.output);
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.PINK_MAGNOLIA_FLOWERS.get(), "pink_dye");
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.PINK_MAGNOLIA_FLOWERS.get(), 6).define('#', RUBlocks.PINK_MAGNOLIA_NATURAL_SET.getLeaves()).pattern("###").group("multiface_flowers").unlockedBy("has_pink_magnolia_leaves", has(RUBlocks.PINK_MAGNOLIA_NATURAL_SET.getLeaves())).save(this.output);
        this.oneToOneConversionRecipe(Items.WHITE_DYE, RUBlocks.WHITE_MAGNOLIA_FLOWERS.get(), "white_dye");
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.WHITE_MAGNOLIA_FLOWERS.get(), 6).define('#', RUBlocks.WHITE_MAGNOLIA_NATURAL_SET.getLeaves()).pattern("###").group("multiface_flowers").unlockedBy("has_white_magnolia_leaves", has(RUBlocks.WHITE_MAGNOLIA_NATURAL_SET.getLeaves())).save(this.output);

        this.oneToOneConversionRecipe(Items.BLUE_DYE, RUItems.MEADOW_SAGE.get(), "blue_dye");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RUBlocks.BARLEY.get()), RecipeCategory.FOOD, CookingBookCategory.FOOD, Items.BREAD, 0.35F, 200).unlockedBy("has_barley", has(RUBlocks.BARLEY.get())).save(this.output, recipe("barley_smelting"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(RUBlocks.BARLEY.get()), RecipeCategory.FOOD, Items.BREAD, 0.35F, 100).unlockedBy("has_barley", has(RUBlocks.BARLEY.get())).save(this.output, recipe("barley_smoking"));
        this.oneToOneConversionRecipe(Items.BROWN_DYE, RUBlocks.CATTAIL.get(), "brown_dye");
        this.oneToOneConversionRecipe(Items.LIGHT_GRAY_DYE, RUBlocks.TASSEL.get(), "light_gray_dye");
        this.oneToOneConversionRecipe(Items.ORANGE_DYE, RUBlocks.DAY_LILY.get(), "orange_dye");
        
        for (NaturalSet set : RUBlocks.NATURAL_SETS) {
            Block sapling = set.getSapling();
            Block shrub = set.getShrub();
            if (sapling != null && shrub != null) {
                this.oneToOneConversionRecipe(sapling, shrub, "saplings", 2);
                this.shaped(RecipeCategory.DECORATIONS, shrub, 1)
                    .define('#', sapling)
                    .pattern("#")
                    .pattern("#")
                    .group("shrubs")
                    .unlockedBy("has_sapling", has(sapling))
                    .save(this.output);
            }
        }
        
        this.oneToOneConversionRecipe(Items.MAGENTA_DYE, RUBlocks.SAGUARO_CACTUS_NATURAL_SET.getSapling(), "magenta_dye");

        this.oneToOneConversionRecipe(Blocks.ACACIA_SAPLING, RUBlocks.ACACIA_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.BIRCH_SAPLING, RUBlocks.BIRCH_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.CHERRY_SAPLING, RUBlocks.CHERRY_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.DARK_OAK_SAPLING, RUBlocks.DARK_OAK_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.JUNGLE_SAPLING, RUBlocks.JUNGLE_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.MANGROVE_PROPAGULE, RUBlocks.MANGROVE_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.OAK_SAPLING, RUBlocks.OAK_NATURAL_SET.getShrub(), "saplings", 2);
        this.oneToOneConversionRecipe(Blocks.SPRUCE_SAPLING, RUBlocks.SPRUCE_NATURAL_SET.getShrub(), "saplings", 2);

        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.ACACIA_NATURAL_SET.getShrub(), 1).define('#', Blocks.ACACIA_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_acacia_sapling", has(Blocks.ACACIA_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.BIRCH_NATURAL_SET.getShrub(), 1).define('#', Blocks.BIRCH_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_birch_sapling", has(Blocks.BIRCH_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.CHERRY_NATURAL_SET.getShrub(), 1).define('#', Blocks.CHERRY_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_cherry_sapling", has(Blocks.CHERRY_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.DARK_OAK_NATURAL_SET.getShrub(), 1).define('#', Blocks.DARK_OAK_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_dark_oak_sapling", has(Blocks.DARK_OAK_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.JUNGLE_NATURAL_SET.getShrub(), 1).define('#', Blocks.JUNGLE_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_jungle_sapling", has(Blocks.JUNGLE_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.MANGROVE_NATURAL_SET.getShrub(), 1).define('#', Blocks.MANGROVE_PROPAGULE).pattern("#").pattern("#").group("shrubs").unlockedBy("has_mangrove_propagule", has(Blocks.MANGROVE_PROPAGULE)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.OAK_NATURAL_SET.getShrub(), 1).define('#', Blocks.OAK_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_oak_sapling", has(Blocks.OAK_SAPLING)).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.SPRUCE_NATURAL_SET.getShrub(), 1).define('#', Blocks.SPRUCE_SAPLING).pattern("#").pattern("#").group("shrubs").unlockedBy("has_spruce_sapling", has(Blocks.SPRUCE_SAPLING)).save(this.output);

        this.oneToOneConversionRecipe(RUBlocks.BLUE_BIOSHROOM.get(), RUBlocks.TALL_BLUE_BIOSHROOM.get(), "bioshrooms", 2);
        this.oneToOneConversionRecipe(Items.BLUE_DYE, RUBlocks.BLUE_BIOSHROOM.get(), "blue_dye");
        this.oneToOneConversionRecipe(RUBlocks.GREEN_BIOSHROOM.get(), RUBlocks.TALL_GREEN_BIOSHROOM.get(), "bioshrooms", 2);
        this.oneToOneConversionRecipe(Items.LIME_DYE, RUBlocks.GREEN_BIOSHROOM.get(), "lime_dye");
        this.oneToOneConversionRecipe(RUBlocks.PINK_BIOSHROOM.get(), RUBlocks.TALL_PINK_BIOSHROOM.get(), "bioshrooms", 2);
        this.oneToOneConversionRecipe(Items.PINK_DYE, RUBlocks.PINK_BIOSHROOM.get(), "pink_dye");
        this.oneToOneConversionRecipe(RUBlocks.YELLOW_BIOSHROOM.get(), RUBlocks.TALL_YELLOW_BIOSHROOM.get(), "bioshrooms", 2);
        this.oneToOneConversionRecipe(Items.YELLOW_DYE, RUBlocks.TALL_YELLOW_BIOSHROOM.get(), "yellow_dye");
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.TALL_BLUE_BIOSHROOM.get(), 1).define('#', RUBlocks.BLUE_BIOSHROOM.get()).pattern("#").pattern("#").group("bioshrooms").unlockedBy("has_blue_bioshroom", has(RUBlocks.BLUE_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.TALL_GREEN_BIOSHROOM.get(), 1).define('#', RUBlocks.GREEN_BIOSHROOM.get()).pattern("#").pattern("#").group("bioshrooms").unlockedBy("has_green_bioshroom", has(RUBlocks.GREEN_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.TALL_PINK_BIOSHROOM.get(), 1).define('#', RUBlocks.PINK_BIOSHROOM.get()).pattern("#").pattern("#").group("bioshrooms").unlockedBy("has_pink_bioshroom", has(RUBlocks.PINK_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.TALL_YELLOW_BIOSHROOM.get(), 1).define('#', RUBlocks.YELLOW_BIOSHROOM.get()).pattern("#").pattern("#").group("bioshrooms").unlockedBy("has_yellow_bioshroom", has(RUBlocks.YELLOW_BIOSHROOM.get())).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.ICE).define('#', RUBlocks.ICICLE.get()).pattern("##").pattern("##").group("ice").unlockedBy("has_icicle", has(RUBlocks.ICICLE.get())).save(this.output, recipe(getConversionRecipeName(Blocks.ICE, RUBlocks.ICICLE.get())));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RUBlocks.BARREL_CACTUS.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GREEN_DYE, 1.0F, 200).group("cactus").unlockedBy("has_barrel_cactus", has(RUBlocks.BARREL_CACTUS.get())).save(this.output, recipe(getConversionRecipeName(Items.GREEN_DYE, RUBlocks.BARREL_CACTUS.get())));
        this.oneToOneConversionRecipe(Items.ORANGE_DYE, RUBlocks.CAVE_HYSSOP.get(), "orange_dye");

        /*-----------------PLANT_BLOCKS-----------------*/
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.BLUE_BIOSHROOM_BLOCK.get(), 1).define('#', RUBlocks.BLUE_BIOSHROOM.get()).pattern("##").pattern("##").group("bioshroom_blocks").unlockedBy("has_blue_bioshroom", has(RUBlocks.BLUE_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.GLOWING_BLUE_BIOSHROOM_BLOCK.get(), 4).define('#', RUBlocks.BLUE_BIOSHROOM_BLOCK.get()).define('X', Blocks.GLOWSTONE).pattern(" # ").pattern("#X#").pattern(" # ").group("bioshroom_blocks").unlockedBy("has_blue_bioshroom_block", has(RUBlocks.BLUE_BIOSHROOM_BLOCK.get())).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.GREEN_BIOSHROOM_BLOCK.get(), 1).define('#', RUBlocks.GREEN_BIOSHROOM.get()).pattern("##").pattern("##").group("bioshroom_blocks").unlockedBy("has_green_bioshroom", has(RUBlocks.GREEN_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.GLOWING_GREEN_BIOSHROOM_BLOCK.get(), 4).define('#', RUBlocks.GREEN_BIOSHROOM_BLOCK.get()).define('X', Blocks.GLOWSTONE).pattern(" # ").pattern("#X#").pattern(" # ").group("bioshroom_blocks").unlockedBy("has_green_bioshroom_block", has(RUBlocks.GREEN_BIOSHROOM_BLOCK.get())).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.PINK_BIOSHROOM_BLOCK.get(), 1).define('#', RUBlocks.PINK_BIOSHROOM.get()).pattern("##").pattern("##").group("bioshroom_blocks").unlockedBy("has_pink_bioshroom", has(RUBlocks.PINK_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.GLOWING_PINK_BIOSHROOM_BLOCK.get(), 4).define('#', RUBlocks.PINK_BIOSHROOM_BLOCK.get()).define('X', Blocks.GLOWSTONE).pattern(" # ").pattern("#X#").pattern(" # ").group("bioshroom_blocks").unlockedBy("has_pink_bioshroom_block", has(RUBlocks.PINK_BIOSHROOM_BLOCK.get())).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.YELLOW_BIOSHROOM_BLOCK.get(), 1).define('#', RUBlocks.YELLOW_BIOSHROOM.get()).pattern("##").pattern("##").group("bioshroom_blocks").unlockedBy("has_yellow_bioshroom", has(RUBlocks.YELLOW_BIOSHROOM.get())).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.GLOWING_YELLOW_BIOSHROOM_BLOCK.get(), 4).define('#', RUBlocks.YELLOW_BIOSHROOM_BLOCK.get()).define('X', Blocks.GLOWSTONE).pattern(" # ").pattern("#X#").pattern(" # ").group("bioshroom_blocks").unlockedBy("has_yellow_bioshroom_block", has(RUBlocks.YELLOW_BIOSHROOM_BLOCK.get())).save(this.output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RUBlocks.SAGUARO_CACTUS.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GREEN_DYE, 1.0F, 200).group("cactus").unlockedBy("has_saguaro_cactus", has(RUBlocks.SAGUARO_CACTUS.get())).save(this.output, recipe(getConversionRecipeName(Items.GREEN_DYE, RUBlocks.SAGUARO_CACTUS.get())));

        /*-----------------BRANCHES-----------------*/
        branchFromLog(this.output, RUBlocks.ACACIA_NATURAL_SET.getBranch(), Blocks.ACACIA_LOG);
        branchFromLog(this.output, RUBlocks.BAOBAB_NATURAL_SET.getBranch(), RUBlocks.BAOBAB_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.BIRCH_NATURAL_SET.getBranch(), Blocks.BIRCH_LOG);
        branchFromLog(this.output, RUBlocks.BLACKWOOD_NATURAL_SET.getBranch(), RUBlocks.BLACKWOOD_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.MAGNOLIA_NATURAL_SET.getBranch(), RUBlocks.MAGNOLIA_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.CYPRESS_NATURAL_SET.getBranch(), RUBlocks.CYPRESS_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.CHERRY_NATURAL_SET.getBranch(), Blocks.CHERRY_LOG);
        branchFromLog(this.output, RUBlocks.DARK_OAK_NATURAL_SET.getBranch(), Blocks.DARK_OAK_LOG);
        branchFromLog(this.output, RUBlocks.DEAD_NATURAL_SET.getBranch(), RUBlocks.DEAD_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.EUCALYPTUS_NATURAL_SET.getBranch(), RUBlocks.EUCALYPTUS_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.JOSHUA_NATURAL_SET.getBranch(), RUBlocks.JOSHUA_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.KAPOK_NATURAL_SET.getBranch(), RUBlocks.KAPOK_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.JUNGLE_NATURAL_SET.getBranch(), Blocks.JUNGLE_LOG);
        branchFromLog(this.output, RUBlocks.LARCH_NATURAL_SET.getBranch(), RUBlocks.LARCH_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.MANGROVE_NATURAL_SET.getBranch(), Blocks.MANGROVE_LOG);
        branchFromLog(this.output, RUBlocks.MAPLE_NATURAL_SET.getBranch(), RUBlocks.MAPLE_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.WISTERIA_NATURAL_SET.getBranch(), RUBlocks.WISTERIA_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.OAK_NATURAL_SET.getBranch(), Blocks.OAK_LOG);
        branchFromLog(this.output, RUBlocks.PALM_NATURAL_SET.getBranch(), RUBlocks.PALM_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.PINE_NATURAL_SET.getBranch(), RUBlocks.PINE_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.REDWOOD_NATURAL_SET.getBranch(), RUBlocks.REDWOOD_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.SILVER_BIRCH_NATURAL_SET.getBranch(), RUBlocks.SILVER_BIRCH_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.SOCOTRA_NATURAL_SET.getBranch(), RUBlocks.SOCOTRA_WOOD_SET.getLog());
        branchFromLog(this.output, RUBlocks.SPRUCE_NATURAL_SET.getBranch(), Blocks.SPRUCE_LOG);
        branchFromLog(this.output, RUBlocks.WILLOW_NATURAL_SET.getBranch(), RUBlocks.WILLOW_WOOD_SET.getLog());
        
        
        this.shapeless(RecipeCategory.MISC, Items.STICK, 4).requires(RUItemTags.BRANCHES).group("sticks").unlockedBy("has_branches", this.has(RUItemTags.BRANCHES)).save(this.output, "stick_from_branches");
        
        /*-----------------DIRT_BLOCKS-----------------*/
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.PEAT_COARSE_DIRT.get(), 4).define('D', RUBlocks.PEAT_DIRT.get()).define('G', Blocks.GRAVEL).pattern("DG").pattern("GD").group("coarse_dirt").unlockedBy("has_gravel", has(Blocks.GRAVEL)).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.SILT_COARSE_DIRT.get(), 4).define('D', RUBlocks.SILT_DIRT.get()).define('G', Blocks.GRAVEL).pattern("DG").pattern("GD").group("coarse_dirt").unlockedBy("has_gravel", has(Blocks.GRAVEL)).save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.ASHEN_DIRT.get(), 4).define('D', Blocks.DIRT).define('G', RUItemTags.ASH).pattern("DG").pattern("GD").group("coarse_dirt").unlockedBy("has_ash", has(RUItemTags.ASH)).save(this.output, recipe(getConversionRecipeName(RUBlocks.ASHEN_DIRT.get(), Blocks.DIRT)));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.ASHEN_DIRT.get(), 4).define('D', RUBlocks.PEAT_DIRT.get()).define('G', RUItemTags.ASH).pattern("DG").pattern("GD").group("coarse_dirt").unlockedBy("has_ash", has(RUItemTags.ASH)).save(this.output, recipe(getConversionRecipeName(RUBlocks.ASHEN_DIRT.get(), RUBlocks.PEAT_DIRT.get())));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.ASHEN_DIRT.get(), 4).define('D', RUBlocks.SILT_DIRT.get()).define('G', RUItemTags.ASH).pattern("DG").pattern("GD").group("coarse_dirt").unlockedBy("has_ash", has(RUItemTags.ASH)).save(this.output, recipe(getConversionRecipeName(RUBlocks.ASHEN_DIRT.get(), RUBlocks.SILT_DIRT.get())));

        /*-----------------STONE_BLOCKS-----------------*/

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_SLAB.get(), Ingredient.of(RUBlocks.CHALK.get())).group("chalk").unlockedBy("has_chalk", has(RUBlocks.CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_SLAB.get(), RUBlocks.CHALK.get(), 2);

        stairBuilder(RUBlocks.CHALK_STAIRS.get(), Ingredient.of(RUBlocks.CHALK.get())).group("chalk").unlockedBy("has_chalk", has(RUBlocks.CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_STAIRS.get(), RUBlocks.CHALK.get());

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_GRASS_BLOCK.get(), 1).define('#', RUBlocks.CHALK.get()).define('X', RUItemTags.GRASS).pattern("X").pattern("#").group("stone_grass").unlockedBy("has_chalk", has(RUBlocks.CHALK.get())).save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICKS.get(), 4).define('#', RUBlocks.POLISHED_CHALK.get()).pattern("##").pattern("##").group("chalk").unlockedBy("has_polished_chalk", has(RUBlocks.POLISHED_CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICKS.get(), RUBlocks.CHALK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICKS.get(), RUBlocks.CHALK_GRASS_BLOCK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICKS.get(), RUBlocks.POLISHED_CHALK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICKS.get(), RUBlocks.CHALK_PILLAR.get());

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICK_SLAB.get(), Ingredient.of(RUBlocks.CHALK_BRICKS.get())).group("chalk").unlockedBy("has_chalk_bricks", has(RUBlocks.CHALK_BRICKS.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICK_SLAB.get(), RUBlocks.CHALK_BRICKS.get(), 2);

        stairBuilder(RUBlocks.CHALK_BRICK_STAIRS.get(), Ingredient.of(RUBlocks.CHALK_BRICKS.get())).group("chalk").unlockedBy("has_chalk_bricks", has(RUBlocks.CHALK_BRICKS.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_BRICK_STAIRS.get(), RUBlocks.CHALK_BRICKS.get());

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_PILLAR.get(), 2).define('#', RUBlocks.CHALK.get()).pattern("#").pattern("#").group("chalk").unlockedBy("has_chalk", has(RUBlocks.CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_PILLAR.get(), RUBlocks.CHALK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_PILLAR.get(), RUBlocks.CHALK_GRASS_BLOCK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_PILLAR.get(), RUBlocks.POLISHED_CHALK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.CHALK_PILLAR.get(), RUBlocks.CHALK_BRICKS.get());

        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK.get(), 4).define('#', RUBlocks.CHALK.get()).pattern("##").pattern("##").group("chalk").unlockedBy("has_chalk", has(RUBlocks.CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK.get(), RUBlocks.CHALK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK.get(), RUBlocks.CHALK_GRASS_BLOCK.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK.get(), RUBlocks.CHALK_BRICKS.get());
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK.get(), RUBlocks.CHALK_PILLAR.get());

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK_SLAB.get(), Ingredient.of(RUBlocks.POLISHED_CHALK.get())).group("chalk").unlockedBy("has_polished_chalk", has(RUBlocks.POLISHED_CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK_SLAB.get(), RUBlocks.POLISHED_CHALK.get(), 2);

        stairBuilder(RUBlocks.POLISHED_CHALK_STAIRS.get(), Ingredient.of(RUBlocks.POLISHED_CHALK.get())).group("chalk").unlockedBy("has_polished_chalk", has(RUBlocks.POLISHED_CHALK.get())).save(this.output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, RUBlocks.POLISHED_CHALK_STAIRS.get(), RUBlocks.POLISHED_CHALK.get());

        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.BUILDING_BLOCKS, RUBlocks.MOSSY_STONE.get(), 1).requires(Blocks.STONE).requires(Blocks.VINE).group("mossy").unlockedBy("has_stone", has(Blocks.STONE)).unlockedBy("has_vine", has(Blocks.VINE)).save(this.output);
        oreSmelting(MOSSY_STONE_SMELTABLES.get(), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, RUBlocks.MOSSY_STONE.get(), 0.1F, 200, "mossy");
        oreBlasting(MOSSY_STONE_SMELTABLES.get(), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, RUBlocks.MOSSY_STONE.get(), 0.1F, 100, "mossy");

        /*-----------------OCEAN_BLOCKS-----------------*/
        this.shaped(RecipeCategory.DECORATIONS, RUBlocks.HYACINTH_LAMP.get()).define('#', Items.PRISMARINE_SLAB).define('X', RUItemTags.HYACINTH_BLOOMS).pattern("X").pattern("#").group("hyacinth").unlockedBy("has_prismarine_slab", has(Items.PRISMARINE_SLAB)).unlockedBy("has_hyacinth", has(RUItemTags.HYACINTH_BLOOMS)).save(this.output);

        /*-----------------OTHER_BLOCKS-----------------*/
        this.shaped(RecipeCategory.BUILDING_BLOCKS, RUBlocks.ASH.get(), 1).define('#', Items.GUNPOWDER).pattern("##").pattern("##").group("ash").unlockedBy("has_gunpowder", has(Items.GUNPOWDER)).save(this.output);

        /*-----------------WOOD_TYPES-----------------*/
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_PLANKS, 4).requires(RUItemTags.BAMBOO_LOGS).group("planks").unlockedBy("has_logs", has(RUItemTags.BAMBOO_LOGS)).save(this.output, "regions_unexplored:bamboo_planks");
        this.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO, 1).define('#', RUItemTags.BAMBOO_LOGS).pattern("#").pattern("#").group("bamboo").unlockedBy("has_bamboo_log", has(RUItemTags.BAMBOO_LOGS)).save(this.output, "regions_unexplored:bamboo");
        //ALPHA_BLOCKS
        woodenFence(Items.OAK_FENCE, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenDoor(Items.OAK_DOOR, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenFenceGate(Items.OAK_FENCE_GATE, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenTrapdoor(Items.OAK_TRAPDOOR, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        pressurePlate(Items.OAK_PRESSURE_PLATE, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenButton(Items.OAK_BUTTON, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenSign(Items.OAK_SIGN, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        hangingSign(Items.OAK_HANGING_SIGN, RUBlocks.ALPHA_WOOD_SET.getPlanks());
        woodenBoat(Items.OAK_BOAT, RUBlocks.ALPHA_WOOD_SET.getPlanks());

        woodFromOtherItem(RUBlocks.BRIMWOOD_WOOD_SET.getWood(), RUBlocks.BRIMWOOD_WOOD_SET.getLogMagma());

        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (set.getWood() != null && set.getLog() != null) woodFromLogs(set.getWood(), set.getLog());
            if (set.getStrippedWood() != null && set.getStrippedLog() != null) woodFromLogs(set.getStrippedWood(), set.getStrippedLog());
            if (set.getPlanks() != null) planksFromLogs(set.getPlanks(), RUItemTags.key(
                set.getPlanks().builtInRegistryHolder().key().identifier().getPath().replace("_planks", "_logs")
            ), 4);
            woodenStairs(set.getStairs(), set.getPlanks());
            woodenSlab(set.getSlab(), set.getPlanks());
            woodenFence(set.getFence(), set.getPlanks());
            woodenDoor(set.getDoor(), set.getPlanks());
            woodenFenceGate(set.getFenceGate(), set.getPlanks());
            woodenTrapdoor(set.getTrapdoor(), set.getPlanks());
            pressurePlate(set.getPressurePlate(), set.getPlanks());
            woodenButton(set.getButton(), set.getPlanks());
            woodenSign(set.getSign(), set.getPlanks());
            hangingSign(set.getHangingSign(), set.getStrippedLog());
            woodenBoat(set.getBoat(), set.getPlanks());
            if (set.getChestBoat() != null && set.getBoat() != null) chestBoat(set.getChestBoat(), set.getBoat());
        }

        /*-----------------PAINTED PLANKS-----------------*/
        //for (Map.Entry<DyeColor, Block> entry : RUBlocks.PAINTED_PLANKS.getMap().entrySet()) {
        //    paintedPlanks(entry.getValue(), TagKey.create(Registries.ITEM, cId("dyes/" + entry.getKey().getName())));
        //}
        for (Map.Entry<DyeColor, StairBlock> entry : RUBlocks.PAINTED_STAIRS.getMap().entrySet()) {
            paintedStairs(entry.getValue(), RUBlocks.PAINTED_PLANKS.getMap().get(entry.getKey()));
        }
        for (Map.Entry<DyeColor, SlabBlock> entry : RUBlocks.PAINTED_SLABS.getMap().entrySet()) {
            paintedSlab(entry.getValue(), RUBlocks.PAINTED_PLANKS.getMap().get(entry.getKey()));
        }

        /*-----------------NETHER_BLOCKS-----------------*/

        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.BUILDING_BLOCKS, RUBlocks.OVERGROWN_BONE_BLOCK.get(), 1).requires(Blocks.BONE_BLOCK).requires(RUBlocks.GLISTERING_NYLIUM.get()).group("overgrown_bone_block").unlockedBy("has_glistering_nylium", has(RUBlocks.GLISTERING_NYLIUM.get())).unlockedBy("has_bone_block", has(Blocks.BONE_BLOCK)).save(this.output);
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.MISC, Items.BONE_MEAL, 9).requires(RUBlocks.OVERGROWN_BONE_BLOCK.get()).group("bone_meal").unlockedBy("has_overgrown_bone_block", has(RUBlocks.OVERGROWN_BONE_BLOCK.get())).save(this.output , recipe(getConversionRecipeName(Items.BONE_MEAL, RUBlocks.OVERGROWN_BONE_BLOCK.get())));
    }
    
    private static ResourceKey<Recipe<?>> recipe(String name) {
        return RegionsUnexplored.key(Registries.RECIPE, name);
    }

    private static Identifier cId(String name) {
        return Identifier.fromNamespaceAndPath("c", name);
    }
    
    public static Criterion<EnterBlockTrigger.TriggerInstance> insideOf(Block p_125980_) {
        return CriteriaTriggers.ENTER_BLOCK.createCriterion(new EnterBlockTrigger.TriggerInstance(Optional.empty(), Optional.of(p_125980_.builtInRegistryHolder()), Optional.empty()));
    }

    protected void branchFromLog(RecipeOutput output, ItemLike item, ItemLike item2) {
        this.shaped(RecipeCategory.DECORATIONS, item, 2).define('#', item2).define('X', Items.STICK).pattern("#X").group("branches").unlockedBy("has_log", has(item2)).save(output, recipe(getConversionRecipeName(item,item2)));
    }

    protected void woodFromOtherItem(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        this.shaped(RecipeCategory.BUILDING_BLOCKS, item, 3).define('#', item2).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(item2)).save(this.output, recipe(getConversionRecipeName(item,item2)));
    }

    protected void paintedSlab(ItemLike item, ItemLike item2) {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, item, Ingredient.of(item2)).group("painted_slab").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void paintedStairs(ItemLike item, ItemLike item2) {
        stairBuilder(item, Ingredient.of(item2)).group("painted_stairs").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void paintedPlanks(ItemLike result, TagKey<Item> dye) {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, result, 8).define('X', dye).define('#', ItemTags.PLANKS).pattern("###").pattern("#X#").pattern("###").group("painted_planks").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(this.output);
    }

    protected void snowbelle(ItemLike result, TagKey<Item> dye) {
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.BUILDING_BLOCKS, result).requires(dye).requires(RUItemTags.SNOWBELLE).group("snowbelle").unlockedBy("has_snowbelle", has(RUItemTags.SNOWBELLE)).save(this.output);
    }

    protected void planksFromOneLog(RecipeOutput p_259712_, ItemLike p_259052_, ItemLike p_259045_, int i) {
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.BUILDING_BLOCKS, p_259052_, i).requires(p_259045_).group("planks").unlockedBy("has_log", has(p_259045_)).save(this.output);
    }
    protected void woodenButton(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        buttonBuilder(item, Ingredient.of(item2)).group("wooden_button").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenSign(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        signBuilder(item, Ingredient.of(item2)).group("wooden_sign").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void hangingSign(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        this.shaped(RecipeCategory.DECORATIONS, item, 6).group("hanging_sign").define('#', item2).define('X', Items.IRON_CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenBoat(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        this.shaped(RecipeCategory.TRANSPORTATION, item).define('#', item2).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenDoor(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        doorBuilder(item, Ingredient.of(item2)).group("wooden_door").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenFenceGate(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        fenceGateBuilder(item, Ingredient.of(item2)).group("wooden_fence_gate").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenFence(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        fenceBuilder(item, Ingredient.of(item2)).group("wooden_fence").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenSlab(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, item, Ingredient.of(item2)).group("wooden_slab").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenStairs(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        stairBuilder(item, Ingredient.of(item2)).group("wooden_stairs").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void woodenTrapdoor(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        this.shaped(RecipeCategory.REDSTONE, item, 2).define('#', item2).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void pressurePlate(ItemLike item, ItemLike item2) {
        if (item == null || item2 == null) return;
        pressurePlateBuilder(RecipeCategory.REDSTONE, item, Ingredient.of(item2)).group("wooden_pressure_plate").unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getItemName(item)));
    }

    protected void stonecutterResultFromBase(RecipeCategory category, ItemLike item, ItemLike item2) {
        stonecutterResultFromBase(category, item, item2, 1);
    }

    protected void stonecutterResultFromBase(RecipeCategory category, ItemLike item, ItemLike item2, int i) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(item2), category, item, i).unlockedBy(getHasName(item2), has(item2)).save(this.output, recipe(getConversionRecipeName(item, item2) + "_stonecutting"));
    }

    protected void oreSmelting(List<ItemLike> itemLikes, RecipeCategory category, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(SmeltingRecipe::new, itemLikes, category, cookingCategory, result, experience, cookingTime, group, "_from_smelting");
    }

    protected void oreBlasting(List<ItemLike> itemLikes, RecipeCategory category, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(BlastingRecipe::new, itemLikes, category, cookingCategory, result, experience, cookingTime, group, "_from_blasting");
    }

    public <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> ingredients, RecipeCategory category, CookingBookCategory cookingCategory, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for(ItemLike ingredient : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), category, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(ingredient), has(ingredient)).save(this.output, recipe(getItemName(result) + suffix + "_" + getItemName(ingredient)));
        }
    }
    
    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }
        
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new RURecipeProvider(provider, output);
        }
        
        @Override
        public String getName() {
            return "RU Recipes";
        }
    }
}