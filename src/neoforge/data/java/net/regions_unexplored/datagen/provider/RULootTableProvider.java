package net.regions_unexplored.datagen.provider;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.Util;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.WithConditions;
import net.regions_unexplored.datagen.provider.loot.RUBlockLootProvider;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RULootTableProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final PackOutput.PathProvider pathProvider;
    private final List<SubProviderEntry> subProviders;
    private final CompletableFuture<HolderLookup.Provider> registries;
    
    public RULootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createRegistryElementsPathProvider(Registries.LOOT_TABLE);
        this.subProviders = List.of(
            new SubProviderEntry(RUBlockLootProvider::new, LootContextParamSets.BLOCK)
        );
        this.registries = registries;
    }
    
    public CompletableFuture<?> run(CachedOutput cache) {
        return this.registries.thenCompose((registries) -> this.run(cache, registries));
    }
    
    private CompletableFuture<?> run(CachedOutput cache, HolderLookup.Provider registries) {
        WritableRegistry<LootTable> tables = new MappedRegistry<>(Registries.LOOT_TABLE, Lifecycle.experimental());
        Map<RandomSupport.Seed128bit, Identifier> randomSequenceSeeds = new Object2ObjectOpenHashMap<>();
        Map<LootTable, List<ICondition>> conditionsPerTable = new IdentityHashMap<>();
        this.getTables().forEach((subProvider) -> subProvider.provider().apply(registries).generate((id, lootTable) -> {
            Identifier sequenceId = sequenceIdForLootTable(id);
            Identifier previous = randomSequenceSeeds.put(RandomSequence.seedForKey(sequenceId), sequenceId);
            if (previous != null) {
                String var10000 = String.valueOf(previous);
                Util.logAndPauseIfInIde("Loot table random sequence seed collision on " + var10000 + " and " + id.identifier());
            }
            
            lootTable.setRandomSequence(sequenceId);
            LootTable table = lootTable.setParamSet(subProvider.paramSet()).build();
            tables.register(id, table, RegistrationInfo.BUILT_IN);
            List<ICondition> conditions = lootTable.buildConditions();
            if (!conditions.isEmpty()) {
                conditionsPerTable.put(table, conditions);
            }
            
        }));
        tables.freeze();
        ProblemReporter.Collector problems = new ProblemReporter.Collector();
        if (!problems.isEmpty()) {
            problems.forEach((id, problem) -> LOGGER.warn("Found validation problem in {}: {}", id, problem.description()));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            return CompletableFuture.allOf(tables.entrySet().stream().map((entry) -> {
                ResourceKey<LootTable> id = entry.getKey();
                LootTable table = entry.getValue();
                Path path = this.pathProvider.json(id.identifier());
                WithConditions<LootTable> conditional = new WithConditions<>(conditionsPerTable.getOrDefault(table, List.of()), table);
                
                RegistryOps<JsonElement> ops = registries.createSerializationContext(JsonOps.INSTANCE);
                if (LootTable.CONDITIONAL_DIRECT_CODEC.encodeStart(ops, Optional.of(conditional)).isError()) {
	                LOGGER.error("Can't parse loot table {}", id.identifier());
                    return CompletableFuture.completedFuture(null);
                } else {
                    return DataProvider.saveStable(cache, registries, LootTable.CONDITIONAL_DIRECT_CODEC, Optional.of(conditional), path);
                }
            }).toArray(CompletableFuture[]::new));
        }
    }
    
    public List<SubProviderEntry> getTables() {
        return this.subProviders;
    }
    
    private static Identifier sequenceIdForLootTable(ResourceKey<LootTable> id) {
        return id.identifier();
    }
    
    public final String getName() {
        return "Loot Tables";
    }
    
    public record SubProviderEntry(Function<HolderLookup.Provider, LootTableSubProvider> provider, ContextKeySet paramSet) {
    }
}
