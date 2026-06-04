package net.regions_unexplored.module.version;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.msrandom.multiplatform.annotations.Actual;

public class VersionBlockHelperActual {
	@Actual
	public static BlockBehaviour.Properties postProcessed(BlockBehaviour.Properties properties) {
		return properties.postProcess((state, getter, pos) -> pos);
	}
}
