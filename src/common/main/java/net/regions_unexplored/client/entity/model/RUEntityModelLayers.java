package net.regions_unexplored.client.entity.model;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.client.entity.renderer.AshenRenderer;
import net.regions_unexplored.module.platform.RenderHelper;

public interface RUEntityModelLayers {
	ModelLayerLocation ASHEN_MAIN = model("ashen", "main");
	ModelLayerLocation ASHEN_EYES = model("ashen", "eyes");
	ModelLayerLocation ASHEN_INNER_ARMOR = model("ashen", "inner");
	ModelLayerLocation ASHEN_OUTER_ARMOR = model("ashen", "outer_armor");
	
	static void init() {
		RenderHelper.registerLayerDefinition(ASHEN_MAIN, AshenRenderer::createMainLayer);
		RenderHelper.registerLayerDefinition(ASHEN_EYES, AshenRenderer::createEyesLayer);
		RenderHelper.registerLayerDefinition(ASHEN_INNER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5f)), 64, 32));
		RenderHelper.registerLayerDefinition(ASHEN_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(1f)), 64, 32));
	}
	
	private static ModelLayerLocation model(String model, String layer) {
		return new ModelLayerLocation(RegionsUnexplored.id(model), layer);
	}
}
