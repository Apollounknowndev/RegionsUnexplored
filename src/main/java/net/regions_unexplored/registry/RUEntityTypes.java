package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.entity.ashen.Ashen;
import net.regions_unexplored.module.platform.EntityHelper;
import net.regions_unexplored.module.platform.Registrar;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public interface RUEntityTypes {
    Supplier<EntityType<Ashen>> ASHEN = register("ashen", EntityType.Builder.of(Ashen::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
        .eyeHeight(1.74F)
        .passengerAttachments(2.075F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
    );
    
    Map<WoodSet, Supplier<EntityType<Boat>>> BOATS = new HashMap<>();
    Map<WoodSet, Supplier<EntityType<ChestBoat>>> CHEST_BOATS = new HashMap<>();

    static <T extends Entity> Supplier<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, RegionsUnexplored.id(name));
        return Registrar.register(BuiltInRegistries.ENTITY_TYPE, name, () -> builder.build(key));
    }

    static void init() {
        EntityHelper.registerPlacement(ASHEN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        EntityHelper.registerAttributes(ASHEN, Zombie::createAttributes);
        
        for (WoodSet set : RUBlocks.WOOD_SETS) {
            if (!set.hasBoats()) continue;
            String name = set.name;
            
            BOATS.put(set, register(
                name + "_boat",
                EntityType.Builder.of(boatFactory(set::getBoat), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
            ));
            
            CHEST_BOATS.put(set, register(
                name + "_chest_boat",
                EntityType.Builder.of(chestBoatFactory(set::getChestBoat), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
            ));
        }
    }
    
    private static EntityType.EntityFactory<Boat> boatFactory(final Supplier<Item> boatItem) {
        return (entityType, level) -> new Boat(entityType, level, boatItem);
    }
    
    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(final Supplier<Item> dropItem) {
        return (entityType, level) -> new ChestBoat(entityType, level, dropItem);
    }
    
    static void initPostRegistryFreeze() {
    }
}