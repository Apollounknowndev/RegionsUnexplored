package net.regions_unexplored.module.platform;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.Function;

//? if fabric
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
//? else
//import net.regions_unexplored.platform.neoforge.client.ParticleRegistration;


public class ParticleRegistry {
    public static <T extends ParticleOptions> void register(ParticleType<T> type, Function<SpriteSet, ParticleProvider<T>> provider) {
        //? if fabric
        ParticleProviderRegistry.getInstance().register(type, provider::apply);
        //? else
        //ParticleRegistration.spriteParticleRegistrationMap.put(type, provider::apply);
    }
}
