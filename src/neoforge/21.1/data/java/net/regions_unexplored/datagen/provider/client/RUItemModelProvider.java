package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUBlocks;

public class RUItemModelProvider extends ItemModelProvider {
    public RUItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        simpleBlockItem(RUBlocks.SALMON_POPPY.get());
    }
}
