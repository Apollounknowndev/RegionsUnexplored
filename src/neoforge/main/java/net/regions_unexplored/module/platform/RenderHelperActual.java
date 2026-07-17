package net.regions_unexplored.module.platform;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.neoforge.client.ClientHooks;

import java.util.function.Supplier;

public class RenderHelperActual {
    @Actual
    public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory) {
        EntityRenderers.register(entityType, entityRendererFactory);
    }
    
    @Actual
    public static void registerModelLayer(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        ClientHooks.registerLayerDefinition(layerLocation, supplier::get);
    }
    
    @Actual
    public static void registerArmorModelLayer(ArmorModelSet<ModelLayerLocation> armorModelSet, Supplier<ArmorModelSet<LayerDefinition>> provider) {
        //ClientHooks.registerLayerDefinition(armorModelSet, provider::get);
    }
}
