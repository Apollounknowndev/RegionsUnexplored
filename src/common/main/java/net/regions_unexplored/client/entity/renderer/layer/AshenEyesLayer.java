package net.regions_unexplored.client.entity.renderer.layer;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.regions_unexplored.RegionsUnexplored;

public class AshenEyesLayer<T extends Zombie, M extends ZombieModel<T>> extends EyesLayer<T, M> {
	private static final RenderType ASHEN_EYES = RenderType.eyes(RegionsUnexplored.id("textures/entity/ashen/ashen_eyes.png"));

	public AshenEyesLayer(RenderLayerParent<T, M> parent) {
		super(parent);
	}
	
	@Override
	public RenderType renderType() {
		return ASHEN_EYES;
	}
}
