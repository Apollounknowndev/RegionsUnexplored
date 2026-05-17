package net.regions_unexplored.datagen.provider.tag;

import dev.worldgen.lithostitched.api.tag.LithostitchedTemplatePoolTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.regions_unexplored.registry.data.RUTemplatePools.*;

public class RUTemplatePoolTagProvider extends TagsProvider<StructureTemplatePool> {
    public RUTemplatePoolTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.TEMPLATE_POOL, lookupProvider, RegionsUnexplored.MOD_ID, existingFileHelper);
    }

    @Override
    @SuppressWarnings(value = "all")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(LithostitchedTemplatePoolTags.TRIAL_SPAWNER_MELLE)
            .add(TRIAL_CHAMBERS_ASHEN);
    }
}
