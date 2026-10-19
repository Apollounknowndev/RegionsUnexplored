package net.regions_unexplored.client;

import net.regions_unexplored.client.entity.model.RUEntityModelLayers;
import net.regions_unexplored.client.entity.renderer.RUEntityRenderers;
import net.regions_unexplored.client.renderer.RuBlockRenderer;
import net.regions_unexplored.client.renderer.RuEntityRenderer;

public class RegionsUnexploredClient  {
    public static void clientInit() {
        RUEntityModelLayers.init();
        RUEntityRenderers.init();
        
        RuEntityRenderer.renderBoat();
        RuBlockRenderer.init();
    }
}
