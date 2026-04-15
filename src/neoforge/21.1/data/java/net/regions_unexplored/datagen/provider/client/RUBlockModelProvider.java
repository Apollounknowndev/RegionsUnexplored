package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUBlocks;

public class RUBlockModelProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;
    public RUBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        flower(RUBlocks.SALMON_POPPY.get());
    }
    
    private void flower(Block block) {
        Identifier name = blockId(block);
        Identifier texture = blockTexture(name);
        
        simpleBlock(block, models().cross(name.getPath(), texture).renderType("cutout"));
        flatItem(name, texture);
    }
    
    private void flatItem(Identifier name, Identifier texture) {
        itemModels().singleTexture(name.getPath(), Identifier.withDefaultNamespace("item/generated"), "layer0", texture);
    }
    
    private static Identifier blockId(Block block) {
        return block.builtInRegistryHolder().key().identifier();
    }
    
    private Identifier blockTexture(Identifier name) {
        Identifier texture = name.withPrefix("block/");
        this.existingFileHelper.trackGenerated(texture, ModelProvider.TEXTURE);
        return texture;
    }
}
