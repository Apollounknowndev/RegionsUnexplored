package net.regions_unexplored.datagen.provider.client;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;

public class RUBlockModelProvider extends BlockStateProvider {
    public RUBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RegionsUnexplored.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //for (WoodSet set : RUBlocks.WOOD_SETS) {
        //    if (set.getLog() instanceof RotatedPillarBlock rotatedPillar) logBlock(rotatedPillar);
        //}
    }
}
