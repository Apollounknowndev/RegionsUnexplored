package net.regions_unexplored.datagen.provider.client;

import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUItems;

import java.util.function.Supplier;

public class RUItemModelProvider {
    private final BlockModelGenerators blockModels;
    private final ItemModelGenerators itemModels;
    private static final ItemTintSource LEAVES_TINT = ItemModelUtils.constantTint(0x48b518);
    private static final ItemTintSource GRASS_TINT = new GrassColorSource(0.5f, 1);
    
    public RUItemModelProvider(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.itemModels = itemModels;
    }
    
    protected void run() {
        this.itemModels.declareCustomModelItem(RUItems.IRIDESCENT_RING.get());
        
        var appleOakLeaves = RUBlocks.APPLE_OAK_NATURAL_SET.getLeaves().asItem();
        this.itemModels.itemModelOutput.accept(appleOakLeaves,
            ItemModelUtils.tintedModel(ModelLocationUtils.getModelLocation(appleOakLeaves), LEAVES_TINT)
        );
        
        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (set.getBoat() != null) {
                itemGenerated(set.getBoat());
                itemGenerated(set.getChestBoat());
            }
        }
        
        // TODO: Finish block model datagen equivalents
        itemBlock(RUBlocks.ALPHA_GRASS_BLOCK);
        itemBlock(RUBlocks.ARGILLITE_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.BAMBOO_LOG);
        itemBlock(RUBlocks.BRIMWOOD_WOOD_SET::getLogMagma);
        itemBlock(RUBlocks.CHALK_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.CHALK_PILLAR);
        itemBlock(RUBlocks.COBALT_OBSIDIAN);
        itemBlock(RUBlocks.DEEPSLATE_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.EUCALYPTUS_WOOD_SET::getLog);
        itemBlock(RUBlocks.EUCALYPTUS_WOOD_SET::getWood);
        itemBlock(RUBlocks.FLOWERING_NATURAL_SET::getLeaves, LEAVES_TINT);
        itemBlock(RUBlocks.PEAT_DIRT_PATH);
        itemBlock(RUBlocks.PEAT_FARMLAND);
        itemBlock(RUBlocks.PEAT_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.PEAT_PODZOL);
        itemBlock(RUBlocks.RAW_REDSTONE_BLOCK);
        itemBlock(RUBlocks.SAGUARO_CACTUS);
        itemBlock(RUBlocks.SILT_DIRT_PATH);
        itemBlock(RUBlocks.SILT_FARMLAND);
        itemBlock(RUBlocks.SILT_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.SILT_PODZOL);
        itemBlock(RUBlocks.SMALL_OAK_LOG);
        itemBlock(RUBlocks.STONE_GRASS_BLOCK, GRASS_TINT);
        itemBlock(RUBlocks.STRIPPED_BAMBOO_LOG);
        itemBlock(RUBlocks.STRIPPED_SMALL_OAK_LOG);
        itemBlock(RUBlocks.YELLOW_BIOSHROOM_BLOCK);
        
        itemGenerated(RUBlocks.BARLEY, "_top_1");
        itemGenerated(RUBlocks.BLUE_MAGNOLIA_FLOWERS);
        itemGenerated(RUBlocks.BRIMSPROUT, "_1");
        itemGenerated(RUBlocks.CATTAIL.get().asItem());
        itemGenerated(RUBlocks.CLOVER.get().asItem());
        itemGenerated(RUBlocks.COBALT_ROOTS, "_1");
        itemGenerated(RUBlocks.DUCKWEED.get().asItem());
        itemGenerated(RUItems.DUSKMELON_SLICE.get());
        itemGenerated(RUBlocks.DUSKTRAP, "_top_open");
        itemGenerated(RUBlocks.ELEPHANT_EAR, "_leaf");
        itemGenerated(RUBlocks.EUCALYPTUS_NATURAL_SET.getBranch().asItem());
        itemGenerated(RUBlocks.FLOWERING_LILY_PAD.get().asItem());
        itemGenerated(RUBlocks.GLISTER_BULB, "_head");
        itemGenerated(RUBlocks.GLISTERING_FERN, "_leaf");
        itemGenerated(RUBlocks.GLISTERING_IVY, "_plant_2");
        itemGenerated(RUItems.HANGING_EARLIGHT_FRUIT.get());
        itemGenerated(RUBlocks.HANGING_PRISMARITE.get().asItem());
        itemGenerated(RUBlocks.HYACINTH_FLOWERS);
        itemGenerated(RUBlocks.HYACINTH_LAMP.get().asItem());
        itemGenerated(RUBlocks.JOSHUA_NATURAL_SET.getBranch().asItem());
        itemGenerated(RUBlocks.JOSHUA_NATURAL_SET.getLeaves().asItem());
        itemGenerated(RUBlocks.KAPOK_VINES, "_1");
        itemGenerated(RUBlocks.MAPLE_LEAF_LITTER.get().asItem());
        itemGenerated(RUBlocks.MYCOTOXIC_DAISY, "_top");
        itemGenerated(RUBlocks.MYCOTOXIC_GRASS);
        itemGenerated(RUBlocks.MYCOTOXIC_MUSHROOMS.get().asItem());
        itemGenerated(RUBlocks.ORANGE_CONEFLOWER.get().asItem());
        itemGenerated(RUBlocks.ORANGE_MAPLE_LEAF_LITTER.get().asItem());
        itemGenerated(RUBlocks.PALM_NATURAL_SET.getBranch().asItem());
        itemGenerated(RUBlocks.PINK_MAGNOLIA_FLOWERS);
        itemGenerated(RUBlocks.PRISMOSS_SPROUT.get().asItem());
        itemGenerated(RUBlocks.PURPLE_CONEFLOWER.get().asItem());
        itemGenerated(RUBlocks.RED_MAPLE_LEAF_LITTER.get().asItem());
        itemGenerated(RUBlocks.REDSTONE_BUD);
        itemGenerated(RUBlocks.REDSTONE_BULB);
        itemGenerated(RUItems.SALMONBERRY.get());
        itemGenerated(RUBlocks.SILVER_BIRCH_LEAF_LITTER.get().asItem());
        itemGenerated(RUBlocks.TALL_HYACINTH_STOCK, "_tip");
        itemGenerated(RUBlocks.TASSEL.get().asItem());
        itemGenerated(RUBlocks.WHITE_MAGNOLIA_FLOWERS);
    }
    
    private void itemBlock(Supplier<Block> block) {
        this.blockModels.registerSimpleItemModel(block.get(), nameId(block.get()).withPrefix("block/"));
    }
    
    private void itemBlock(Supplier<Block> block, ItemTintSource tint) {
        this.blockModels.registerSimpleTintedItemModel(block.get(), nameId(block.get()).withPrefix("block/"), tint);
    }
    
    private <T extends Block> void itemGenerated(Supplier<T> block) {
        this.blockModels.registerSimpleFlatItemModel(block.get());
    }
    
    private <T extends Block> void itemGenerated(Supplier<T> block, String suffix) {
        this.blockModels.registerSimpleItemModel(
            block.get(),
            this.blockModels.createFlatItemModelWithBlockTexture(block.get().asItem(), block.get(), suffix)
        );
    }
    
    private void itemGenerated(Item item) {
        this.blockModels.registerSimpleFlatItemModel(item);
    }
    
    // TEXTURES
    
    private Identifier nameId(Block block) {
        return block.builtInRegistryHolder().key().identifier();
    }
}
