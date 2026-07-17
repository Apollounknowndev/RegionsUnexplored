package net.regions_unexplored.client;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.client.particle.RUParticleProviders;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(value = Dist.CLIENT, modid = RegionsUnexplored.MOD_ID)
public class ParticleRegistration {
    public static final Map<ParticleType, Function<SpriteSet, ParticleProvider<?>>> spriteParticleRegistrationMap = new HashMap<>();

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        RUParticleProviders.init();
        spriteParticleRegistrationMap.forEach((type, provider) -> event.registerSpriteSet(type, provider::apply));
    }
}
