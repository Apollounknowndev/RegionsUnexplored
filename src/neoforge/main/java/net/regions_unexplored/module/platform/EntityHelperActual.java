package net.regions_unexplored.module.platform;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.regions_unexplored.RegionsUnexploredNeo;

import java.util.function.Supplier;

public class EntityHelperActual {
	@Actual
	public static <T extends Mob> void registerPlacement(Supplier<EntityType<T>> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
		RegionsUnexploredNeo.SPAWN_PLACEMENTS.add(event -> {
			event.register(type.get(), placementType, heightmap, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		});
	}
	
	@Actual
	public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> type, Supplier<AttributeSupplier.Builder> builder) {
		RegionsUnexploredNeo.ENTITY_ATTRIBUTES.add(event -> {
			event.put(type.get(), builder.get().build());
		});
	}
}
