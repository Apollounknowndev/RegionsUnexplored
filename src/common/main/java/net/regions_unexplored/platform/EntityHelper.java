package net.regions_unexplored.platform;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.function.Supplier;

public class EntityHelper {
	@Expect
	public static <T extends Mob> void registerPlacement(Supplier<EntityType<T>> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate);
	
	@Expect
	public static <T extends LivingEntity> void registerAttributes(Supplier<EntityType<T>> type, Supplier<AttributeSupplier.Builder> builder);
}
