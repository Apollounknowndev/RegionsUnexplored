package net.regions_unexplored;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.regions_unexplored.config.RUConfigHandler;
import net.regions_unexplored.lithostitched.RULithostitched;
import net.regions_unexplored.registry.*;
import net.regions_unexplored.block.compat.BlockToolCompat;
import net.regions_unexplored.block.compat.FlammableBlocks;
import net.regions_unexplored.registry.RUParticleTypes;
import net.regions_unexplored.registry.RUEntityTypes;
import net.regions_unexplored.registry.data.RUBiomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionsUnexplored {
	public static final String MOD_ID = "regions_unexplored";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// We do this because terrablender might load before us or after us, so this catches both cases.
	public static void init() {
		RUConfigHandler.loadConfigs();

		RUBiomes.init();
		RUBlocks.init();
		RUBlockStateProviderTypes.init();
		RUCreativeModeTabs.init();
		RUEntityTypes.init();
		RUFeatureTypes.init();
		RUFoliagePlacerTypes.init();
		RUItems.init();
		RULoadPredicateTypes.init();
		RUParticleTypes.init();
		RUProcessorConditionTypes.init();
		RURootPlacerTypes.init();
		RURuleSources.init();
		RUSoundEvents.init();
		RUTreeDecoratorTypes.init();
		RUTrunkPlacerTypes.init();
		
		RULithostitched.init();
	}

	public static void afterRegistriesFreeze(){
		BlockToolCompat.setup();
		//CompostableBlocks.setup();
		FlammableBlocks.setup();
		
		RUBlocks.initPostRegistryFreeze();
		RUEntityTypes.initPostRegistryFreeze();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static String stringId(String path) {
		return id(path).toString();
	}

	public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> key, String name) {
		return ResourceKey.create(key, id(name));
	}
}