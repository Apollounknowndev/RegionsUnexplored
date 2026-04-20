package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;

public class RUItemModelProvider extends ItemModelProvider {
    public RUItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
    
    }
    
    private void itemBlock(Block block) {
        simpleBlockItem(block);
    }
    
    private void itemGenerated(Block block) {
        basicItem(block.asItem());
    }
}
