package net.regions_unexplored.module.platform;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.function.Supplier;

public class RenderHelper {
    @Expect
    public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory);

    @Expect
    public static void registerModelLayer(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier);
    
    @Expect
    public static void registerArmorModelLayer(ArmorModelSet<ModelLayerLocation> armorModelSet, Supplier<ArmorModelSet<LayerDefinition>> provider);
}