package net.regions_unexplored.module.platform;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

//? if fabric
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
//? else {
/*import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.regions_unexplored.platform.neoforge.RegionsUnexploredNeo;
*///? }

public class EntityHelper {
	public static <T extends Mob> void registerPlacement(Supplier<EntityType<T>> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate) {
		//? if fabric
		SpawnPlacements.register(type.get(), placementType, heightmap, predicate);
		//? else {
		/*RegionsUnexploredNeo.SPAWN_PLACEMENTS.add(event -> {
			event.register(type.get(), placementType, heightmap, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		});
		*///? }
	}
	
	public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> type, Supplier<AttributeSupplier.Builder> builder) {
		//? if fabric
		FabricDefaultAttributeRegistry.register(type.get(), builder.get());
		//? else {
		/*RegionsUnexploredNeo.ENTITY_ATTRIBUTES.add(event -> {
			event.put(type.get(), builder.get().build());
		});
		*///? }
	}
}
