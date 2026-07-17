package net.regions_unexplored.client.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.client.entity.renderer.AshenRenderer;
import net.regions_unexplored.module.platform.RenderHelper;
import net.regions_unexplored.registry.RUBlocks;

import java.util.HashMap;
import java.util.Map;

public interface RUEntityModelLayers {
	ModelLayerLocation ASHEN_MAIN = model("ashen", "main");
	ModelLayerLocation ASHEN_EYES = model("ashen", "eyes");
	ModelLayerLocation ASHEN_INNER_ARMOR = model("ashen", "inner");
	ModelLayerLocation ASHEN_OUTER_ARMOR = model("ashen", "outer_armor");
	ArmorModelSet<ModelLayerLocation> ASHEN_ARMOR = new ArmorModelSet<>(model("ashen", "helmet"), model("ashen", "chestplate"), model("ashen", "leggings"), model("ashen", "boots"));
	Map<WoodSet, ModelLayerLocation> BOATS = new HashMap<>();
	Map<WoodSet, ModelLayerLocation> CHEST_BOATS = new HashMap<>();
	
	static void init() {
		ArmorModelSet<LayerDefinition> humanoidArmor = HumanoidModel.createArmorMeshSet(new CubeDeformation(0.5f), new CubeDeformation(1.0F)).map(mesh -> LayerDefinition.create(mesh, 64, 32));
		
		RenderHelper.registerModelLayer(ASHEN_MAIN, AshenRenderer::createMainLayer);
		RenderHelper.registerModelLayer(ASHEN_EYES, AshenRenderer::createEyesLayer);
		RenderHelper.registerArmorModelLayer(ASHEN_ARMOR, () -> humanoidArmor);
		
		for (WoodSet set : RUBlocks.WOOD_SETS) {
			if (!set.hasBoats()) continue;
			String name = set.name;
			
			ModelLayerLocation boat = model("boat/" + name, "main");
			BOATS.put(set, boat);
			RenderHelper.registerModelLayer(boat, BoatModel::createBoatModel);
			
			ModelLayerLocation chestBoat = model("chest_boat/" + name, "main");
			CHEST_BOATS.put(set, chestBoat);
			RenderHelper.registerModelLayer(chestBoat, BoatModel::createChestBoatModel);
		}
	}
	
	private static ModelLayerLocation model(String model, String layer) {
		return new ModelLayerLocation(RegionsUnexplored.id(model), layer);
	}
}
