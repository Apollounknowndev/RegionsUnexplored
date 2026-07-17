package net.regions_unexplored.datagen.provider.tag;

import dev.worldgen.lithostitched.api.tag.LithostitchedTemplatePoolTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.regions_unexplored.RegionsUnexplored;

import java.util.concurrent.CompletableFuture;

import static net.regions_unexplored.registry.data.RUTemplatePools.*;

public class RUTemplatePoolTagProvider extends KeyTagProvider<StructureTemplatePool> {
    public RUTemplatePoolTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.TEMPLATE_POOL, lookupProvider, RegionsUnexplored.MOD_ID);
    }

    @Override
    @SuppressWarnings(value = "all")
    public void addTags(HolderLookup.Provider provider) {
        this.tag(LithostitchedTemplatePoolTags.TRIAL_SPAWNER_MELEE)
            .add(TRIAL_CHAMBERS_ASHEN);
    }
}
