package net.regions_unexplored.lithostitched;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.lithostitched.api.predicate.LoadPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.config.RuCommonConfig;

public record ConfigPredicate(String key) implements LoadPredicate {
    public static final MapCodec<ConfigPredicate> CODEC = Codec.STRING.fieldOf("key").xmap(ConfigPredicate::new, ConfigPredicate::key);

    @Override
    public boolean test() {
        return false;
    }

    @Override
    public MapCodec<? extends LoadPredicate> codec() {
        return CODEC;
    }
}
