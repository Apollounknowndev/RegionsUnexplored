package net.regions_unexplored.datagen.provider.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUEntityTypes;

import java.util.concurrent.CompletableFuture;

public class RUEntityTypeTagProvider extends EntityTypeTagsProvider {
    public RUEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, RegionsUnexplored.MOD_ID);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE).add(RUEntityTypes.ASHEN.get());
        this.tag(EntityTypeTags.ZOMBIES).add(RUEntityTypes.ASHEN.get());
    }
}
