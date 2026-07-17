package net.regions_unexplored.datagen.provider.tag;

import dev.worldgen.lithostitched.api.tag.LithostitchedProcessorListTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.regions_unexplored.RegionsUnexplored;

import java.util.concurrent.CompletableFuture;

import static net.regions_unexplored.registry.data.RUProcessorLists.*;

public class RUProcessorListTagProvider extends KeyTagProvider<StructureProcessorList> {
    public RUProcessorListTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.PROCESSOR_LIST, lookupProvider, RegionsUnexplored.MOD_ID);
    }

    @Override
    @SuppressWarnings(value = "all")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(LithostitchedProcessorListTags.SHIPWRECK_PALETTES)
            .add(SHIPWRECK_DARK_OAK_AND_BAOBAB)
            .add(SHIPWRECK_DARK_OAK_AND_DEAD)
            .add(SHIPWRECK_DARK_OAK_AND_EUCALYPTUS)
            .add(SHIPWRECK_DARK_OAK_AND_JOSHUA)
            .add(SHIPWRECK_DARK_OAK_AND_KAPOK)
            .add(SHIPWRECK_DARK_OAK_AND_LARCH)
            .add(SHIPWRECK_DEAD_AND_DARK_OAK)
            .add(SHIPWRECK_DEAD_AND_LARCH)
            .add(SHIPWRECK_DEAD_AND_SPRUCE);
    }
}
