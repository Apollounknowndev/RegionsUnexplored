package net.regions_unexplored;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.regions_unexplored.config.RUConfigHandler;
import org.jetbrains.annotations.Nullable;

public record RUConfigCondition(String key) implements ResourceCondition {
	public static final MapCodec<RUConfigCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		Codec.STRING.fieldOf("key").forGetter(RUConfigCondition::key)
	).apply(instance, RUConfigCondition::new));
	public static final ResourceConditionType<RUConfigCondition> TYPE = ResourceConditionType.create(RegionsUnexplored.id("config"), CODEC);
	
	@Override
	public ResourceConditionType<?> getType() {
		return TYPE;
	}
	
	@Override
	public boolean test(RegistryOps.RegistryInfoLookup registries) {
		return RUConfigHandler.COMMON.test(this.key);
	}
}