package net.regions_unexplored.worldgen.rulesource;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.regions_unexplored.config.RUConfigHandler;

public record ConfigRuleSource(String key, RuleSource onEnabled, RuleSource onDisabled) implements RuleSource {
	public static final KeyDispatchDataCodec<ConfigRuleSource> CODEC = KeyDispatchDataCodec.of(
		RecordCodecBuilder.mapCodec(i -> i.group(
			Codec.STRING.fieldOf("key").forGetter(ConfigRuleSource::key),
			RuleSource.CODEC.fieldOf("on_enabled").forGetter(ConfigRuleSource::onEnabled),
			RuleSource.CODEC.fieldOf("on_disabled").forGetter(ConfigRuleSource::onDisabled)
		).apply(i, ConfigRuleSource::new))
	);
	
	@Override
	public KeyDispatchDataCodec<? extends RuleSource> codec() {
		return CODEC;
	}
	
	@Override
	public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
		RuleSource source = RUConfigHandler.COMMON.test(key) ? onEnabled : onDisabled;
		return source.apply(context);
	}
}
