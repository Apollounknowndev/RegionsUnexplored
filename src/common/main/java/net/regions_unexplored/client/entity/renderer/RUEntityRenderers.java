package net.regions_unexplored.client.entity.renderer;

import net.regions_unexplored.module.platform.RenderHelper;
import net.regions_unexplored.registry.RUEntityTypes;

public interface RUEntityRenderers {
	static void init() {
		RenderHelper.registerEntityRenderer(RUEntityTypes.ASHEN.get(), AshenRenderer::new);
	}
}
