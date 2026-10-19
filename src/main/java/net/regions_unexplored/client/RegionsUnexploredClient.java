package net.regions_unexplored.client;

import net.regions_unexplored.client.entity.model.RUEntityModelLayers;
import net.regions_unexplored.client.entity.renderer.RUEntityRenderers;

public class RegionsUnexploredClient  {
    public static void clientInit() {
        RUEntityModelLayers.init();
        RUEntityRenderers.init();
    }
}
