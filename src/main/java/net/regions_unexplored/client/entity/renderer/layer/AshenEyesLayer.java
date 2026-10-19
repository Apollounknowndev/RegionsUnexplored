package net.regions_unexplored.client.entity.renderer.layer;

import net.minecraft.client.model.monster.zombie.AbstractZombieModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.regions_unexplored.RegionsUnexplored;

public class AshenEyesLayer<M extends AbstractZombieModel<ZombieRenderState>> extends EyesLayer<ZombieRenderState, M> {
	private static final RenderType ASHEN_EYES = RenderTypes.eyes(RegionsUnexplored.id("textures/entity/ashen/ashen_eyes.png"));

	public AshenEyesLayer(RenderLayerParent<ZombieRenderState, M> parent) {
		super(parent);
	}
	
	@Override
	public RenderType renderType() {
		return ASHEN_EYES;
	}
}
