package net.regions_unexplored.module.platform;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import java.util.function.Supplier;

//? if fabric
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
//? else
//import net.neoforged.neoforge.client.ClientHooks;

public class RenderHelper {
    public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory) {
        EntityRenderers.register(entityType, entityRendererFactory);
    }
    
    public static void registerModelLayer(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        //? if fabric
        ModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
        //? else
        //ClientHooks.registerLayerDefinition(layerLocation, supplier);
    }
    
    public static void registerArmorModelLayer(ArmorModelSet<ModelLayerLocation> armorModelSet, Supplier<ArmorModelSet<LayerDefinition>> provider) {
        //? if fabric
        ModelLayerRegistry.registerArmorModelLayers(armorModelSet, provider::get);
        //? else {
        /*ClientHooks.registerLayerDefinition(armorModelSet.head(), () -> provider.get().get(EquipmentSlot.HEAD));
        ClientHooks.registerLayerDefinition(armorModelSet.chest(), () -> provider.get().get(EquipmentSlot.CHEST));
        ClientHooks.registerLayerDefinition(armorModelSet.legs(), () -> provider.get().get(EquipmentSlot.LEGS));
        ClientHooks.registerLayerDefinition(armorModelSet.feet(), () -> provider.get().get(EquipmentSlot.FEET));
        *///? }
    }
}