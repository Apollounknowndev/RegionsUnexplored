package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.regions_unexplored.entity.ashen.Ashen;
import net.regions_unexplored.entity.custom.RuBoat;
import net.regions_unexplored.entity.custom.RuChestBoat;
import net.regions_unexplored.platform.EntityHelper;
import net.regions_unexplored.platform.Registrar;

import java.util.function.Supplier;

public interface RUEntityTypes {
    Supplier<EntityType<RuBoat>> BOAT = register("boat", () -> EntityType.Builder.<RuBoat>of(RuBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("boat"));
    Supplier<EntityType<RuChestBoat>> CHEST_BOAT = register("chest_boat", () -> EntityType.Builder.<RuChestBoat>of(RuChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build("chest_boat"));
    Supplier<EntityType<Ashen>> ASHEN = register("ashen", () -> EntityType.Builder.of(Ashen::new, MobCategory.MONSTER)
        .sized(0.6F, 1.95F)
        .eyeHeight(1.74F)
        .passengerAttachments(2.075F)
        .ridingOffset(-0.7F)
        .clientTrackingRange(8)
    .build("ashen"));

    static <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
        return Registrar.register(BuiltInRegistries.ENTITY_TYPE, name, type);
    }

    static void init() {

    }
    
    static void initPostRegistryFreeze() {
        EntityHelper.registerPlacement(ASHEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Zombie::checkMonsterSpawnRules);
        
        EntityHelper.registerAttributes(ASHEN.get(), Zombie.createAttributes());
    }
}