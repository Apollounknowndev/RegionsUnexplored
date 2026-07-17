package net.regions_unexplored.client.entity.renderer;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.client.entity.model.RUEntityModelLayers;
import net.regions_unexplored.module.platform.RenderHelper;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUEntityTypes;

import java.util.function.Supplier;

public interface RUEntityRenderers {
	static void init() {
		RenderHelper.registerEntityRenderer(RUEntityTypes.ASHEN.get(), AshenRenderer::new);
		
		
		for (WoodSet set : RUBlocks.WOOD_SETS) {
			if (!set.hasBoats()) continue;
			
			RenderHelper.registerEntityRenderer(RUEntityTypes.BOATS.get(set).get(), context -> new BoatRenderer(context, RUEntityModelLayers.BOATS.get(set)));
			RenderHelper.registerEntityRenderer(RUEntityTypes.CHEST_BOATS.get(set).get(), context -> new BoatRenderer(context, RUEntityModelLayers.CHEST_BOATS.get(set)));
		}
	}
}
