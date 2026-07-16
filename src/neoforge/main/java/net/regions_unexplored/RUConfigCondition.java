package net.regions_unexplored;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.regions_unexplored.config.RUConfigHandler;
import org.jetbrains.annotations.NotNull;

public record RUConfigCondition(String key) implements ICondition {
	public static final MapCodec<RUConfigCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Codec.STRING.fieldOf("key").forGetter(RUConfigCondition::key)
	).apply(instance, RUConfigCondition::new));
	
	@Override
	public boolean test(@NotNull IContext context) {
		return RUConfigHandler.COMMON.test(this.key);
	}
	
	@Override
	public @NotNull MapCodec<? extends ICondition> codec() {
		return CODEC;
	}
}