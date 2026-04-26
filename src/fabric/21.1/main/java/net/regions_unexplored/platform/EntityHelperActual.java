package net.regions_unexplored.platform;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.msrandom.multiplatform.annotations.Actual;
import net.msrandom.multiplatform.annotations.Expect;

public class EntityHelperActual {
	@Actual
	public static <T extends Mob> void registerPlacement(EntityType<T> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
		SpawnPlacements.register(type, placementType, heightmap, predicate);
	}
	
	@Actual
	public static void registerAttributes(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder) {
		FabricDefaultAttributeRegistry.register(type, builder);
	}
}
