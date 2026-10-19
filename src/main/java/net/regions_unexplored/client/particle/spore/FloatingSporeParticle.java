package net.regions_unexplored.client.particle.spore;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class FloatingSporeParticle extends SingleQuadParticle {
    FloatingSporeParticle(ClientLevel level, TextureAtlasSprite sprite, double x, double y, double z, double xa, double ya, double za) {
        super(level, x, y - (double)0.125F, z, xa, ya, za, sprite);
        this.setSize(0.01F, 0.01F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = (int)(16.0F / (this.random.nextFloat() * 0.8 + 0.2));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }
    
    @Override
    protected SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(ColorParticleOption options, ClientLevel level, double x, double y, double z, double xa, double ya, double za, RandomSource random) {
            var particle = new FloatingSporeParticle(level, this.sprites.get(random), x, y, z, 0.0F, -0.8F, 0.0F);
            particle.lifetime = Mth.randomBetweenInclusive(random, 500, 1000);
            particle.setColor(options.getRed(), options.getGreen(), options.getBlue());
            return particle;
        }
    }
}
