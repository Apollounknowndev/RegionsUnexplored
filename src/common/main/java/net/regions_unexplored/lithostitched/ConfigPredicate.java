package net.regions_unexplored.lithostitched;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.lithostitched.api.predicate.LoadPredicate;
import net.regions_unexplored.config.RUConfigHandler;

public record ConfigPredicate(String key) implements LoadPredicate {
    public static final MapCodec<ConfigPredicate> CODEC = Codec.STRING.fieldOf("key").xmap(ConfigPredicate::new, ConfigPredicate::key);

    @Override
    public boolean test() {
        return RUConfigHandler.COMMON.test(key);
    }

    @Override
    public MapCodec<? extends LoadPredicate> codec() {
        return CODEC;
    }
}
