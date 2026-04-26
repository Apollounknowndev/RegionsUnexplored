package net.regions_unexplored.platform;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.msrandom.multiplatform.annotations.Expect;

public class EntityHelper {
	@Expect
	public static <T extends Mob> void registerPlacement(EntityType<T> type, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate);
	
	@Expect
	public static void registerAttributes(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder);
}
