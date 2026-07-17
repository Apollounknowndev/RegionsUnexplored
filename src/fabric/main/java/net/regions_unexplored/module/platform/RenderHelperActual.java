package net.regions_unexplored.module.platform;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.msrandom.multiplatform.annotations.Actual;

import java.util.function.Supplier;

public class RenderHelperActual {
    @Actual
    public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory) {
        EntityRendererRegistry.register(entityType, entityRendererFactory);
    }

    @Actual
    public static void registerModelLayer(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        ModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
    }
    
    @Actual
    public static void registerArmorModelLayer(ArmorModelSet<ModelLayerLocation> armorModelSet, Supplier<ArmorModelSet<LayerDefinition>> provider) {
        ModelLayerRegistry.registerArmorModelLayers(armorModelSet, provider::get);
    }
}
