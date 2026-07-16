package net.regions_unexplored.module.platform;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.msrandom.multiplatform.annotations.Actual;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.function.Supplier;

public class EntityHelperActual {
	@Actual
	public static <T extends Mob> void registerPlacement(Supplier<EntityType<T>> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
		SpawnPlacements.register(type.get(), placementType, heightmap, predicate);
	}
	
	@Actual
	public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> type, Supplier<AttributeSupplier.Builder> builder) {
		FabricDefaultAttributeRegistry.register(type.get(), builder.get());
	}
}
