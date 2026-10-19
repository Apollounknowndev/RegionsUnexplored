package net.regions_unexplored.module.platform;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

//? if fabric {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
//? } else {
/*import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.client.ClientHooks;
*///? }

public class RenderHelper {
    public static void setRenderType(Block block, RenderType type) {
        //? if fabric
        BlockRenderLayerMap.INSTANCE.putBlock(block, type);
        //? else
        //ItemBlockRenderTypes.setRenderLayer(block, type);
    }
    
    public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererFactory) {
        //? if fabric
        EntityRendererRegistry.register(entityType, entityRendererFactory);
        //? else
        //EntityRenderers.register(entityType, entityRendererFactory);
    }
    
    public static void registerLayerDefinition(ModelLayerLocation layerLocation, Supplier<LayerDefinition> supplier) {
        //? if fabric
        EntityModelLayerRegistry.registerModelLayer(layerLocation, supplier::get);
        //? else
        //ClientHooks.registerLayerDefinition(layerLocation, supplier);
    }
}
