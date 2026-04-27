package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUItems;

public class RUItemModelProvider extends ItemModelProvider {
    public RUItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        registerTemplate("ring");
        
        String ring = name(RUItems.IRIDESCENT_RING.get());
        this.singleTexture(ring, RegionsUnexplored.id("item/template/ring"), "ring", texturize(ring));
    }
    
    private void itemBlock(Block block) {
        simpleBlockItem(block);
    }
    
    private void itemGenerated(Block block) {
        basicItem(block.asItem());
    }
    
    // TEXTURES
    
    private Identifier nameId(Item item) {
        return item.builtInRegistryHolder().key().identifier();
    }
    
    private String name(Item block) {
        return nameId(block).getPath();
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
    
    private void registerTemplate(String name) {
        Identifier id = Identifier.parse(template(name));
        getBuilder(id.toString());
        generatedModels.remove(id);
    }
    
    private String template(String name) {
        return RegionsUnexplored.id("item/template/" + name).toString();
    }
}
