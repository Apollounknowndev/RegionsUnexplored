package net.regions_unexplored.datagen.provider.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.data.RUEntityTypeIds;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RUEntityTypeTagProvider extends TagsProvider<EntityType<?>> {
    public RUEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.ENTITY_TYPE, lookupProvider, RegionsUnexplored.MOD_ID, existingFileHelper);
    }

    @Override
    @SuppressWarnings(value = "all")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE).add(RUEntityTypeIds.ASHEN);
        this.tag(EntityTypeTags.ZOMBIES).add(RUEntityTypeIds.ASHEN);
    }
}
