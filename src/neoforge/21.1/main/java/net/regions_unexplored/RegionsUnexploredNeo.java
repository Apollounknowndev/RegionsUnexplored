package net.regions_unexplored;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.regions_unexplored.client.RegionsUnexploredClient;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.registry.RUItems;

import java.util.HashMap;
import java.util.Map;

@Mod(value = RegionsUnexplored.MOD_ID)
public class RegionsUnexploredNeo {
    public static final Map<ResourceKey, DeferredRegister> REGISTER_CACHE = new HashMap<>();

    public RegionsUnexploredNeo(ModContainer container) {
        IEventBus bus = container.getEventBus();

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::setupBlockEntities);

        RegionsUnexplored.init(FMLEnvironment.dist.isClient());

        REGISTER_CACHE.values().forEach(deferredRegister -> deferredRegister.register(bus));

        var blockRegistry = DeferredRegister.create(Registries.BLOCK, RegionsUnexplored.MOD_ID);
        var itemRegistry = DeferredRegister.create(Registries.ITEM, RegionsUnexplored.MOD_ID);
        RUBlocks.applyAliases(blockRegistry::addAlias);
        RUItems.applyAliases(itemRegistry::addAlias);
    }

    private void setupBlockEntities(BlockEntityTypeAddBlocksEvent event) {
        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (set.getSign() != null) {
                event.modify(BlockEntityType.SIGN, set.getSign());
            }
            if (set.getWallSign() != null) {
                event.modify(BlockEntityType.SIGN, set.getWallSign());
            }

            if (set.getHangingSign() != null) {
                event.modify(BlockEntityType.SIGN, set.getHangingSign());
            }
            if (set.getWallHangingSign() != null) {
                event.modify(BlockEntityType.SIGN, set.getWallHangingSign());
            }
        }
    }

    //set up client side features
    public void clientSetup(final FMLClientSetupEvent event) {
        RegionsUnexploredClient.clientInit();
    }

    //set up non-client side features
    @SubscribeEvent
    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(RegionsUnexplored::afterRegistriesFreeze);
    }
}