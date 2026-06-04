package net.regions_unexplored.client.entity.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.client.entity.model.RUEntityModelLayers;
import net.regions_unexplored.client.entity.renderer.layer.AshenEyesLayer;

public class AshenRenderer extends ZombieRenderer {
	private static final Identifier BASE_LOCATION = RegionsUnexplored.id("textures/entity/ashen/ashen.png");
	
	public AshenRenderer(EntityRendererProvider.Context context) {
		super(context, RUEntityModelLayers.ASHEN_MAIN, RUEntityModelLayers.ASHEN_INNER_ARMOR, RUEntityModelLayers.ASHEN_OUTER_ARMOR);
		this.addLayer(new AshenEyesLayer<>(this));
	}
	
	@Override
	public Identifier getTextureLocation(Zombie zombie) {
		return BASE_LOCATION;
	}
	
	public static LayerDefinition createMainLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		return LayerDefinition.create(modelData, 64, 64);
	}
	
	public static LayerDefinition createEyesLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F);
		return LayerDefinition.create(modelData, 64, 64);
	}
}
