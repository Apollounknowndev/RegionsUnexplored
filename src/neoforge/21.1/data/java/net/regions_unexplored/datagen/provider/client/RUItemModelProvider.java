package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUItems;

public class RUItemModelProvider extends ItemModelProvider {
    public RUItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        String ring = name(RUItems.IRIDESCENT_RING.get());
        singleTexture(ring, template("ring"), "ring", texturize(ring));
        
        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (set.getBoat() != null) {
                itemGenerated(set.getBoat());
                itemGenerated(set.getChestBoat());
            }
        }
    }
    
    private void itemBlock(Block block) {
        simpleBlockItem(block);
    }
    
    private void itemBlock(Identifier id) {
        simpleBlockItem(id);
    }
    
    private void itemGenerated(ItemLike item) {
        basicItem(item.asItem());
    }
    
    // TEXTURES
    
    private Identifier nameId(Block block) {
        return block.builtInRegistryHolder().key().identifier();
    }
    
    private Identifier nameId(Item item) {
        return item.builtInRegistryHolder().key().identifier();
    }
    
    private String name(Item item) {
        return nameId(item).getPath();
    }
    
    private Identifier texturize(String texture) {
        return texturize(RegionsUnexplored.id(texture));
    }
    
    private Identifier texturize(Identifier id) {
        id = id.withPrefix("item/");
        this.existingFileHelper.trackGenerated(id, ModelProvider.TEXTURE);
        return id;
    }
    
    // MISC
    
    private Identifier template(String name) {
        Identifier id = RegionsUnexplored.id("item/template/" + name);
        getBuilder(id.toString());
        generatedModels.remove(id);
        return id;
    }
}
