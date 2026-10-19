package net.regions_unexplored.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class MycotoxicSporeParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    private float angularVelocity;
    private float angularAcceleration;

    protected MycotoxicSporeParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, xa, ya, za, sprites.get(level.getRandom()));
        this.sprites = sprites;
        this.setSize(0.2f, 0.2f);
        this.quadSize *= 1.25f;
        this.lifetime = Math.max(1, 90 + (this.random.nextInt(20) - 10));
        this.gravity = -0.075f;
        this.hasPhysics = false;
        this.xd = xa * 10;
        this.yd = ya * 10;
        this.zd = za * 10;
        this.angularVelocity = 0.000f;
        this.angularAcceleration = 0.000f;
        this.setSpriteFromAge(sprites);
    }
    
    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.oRoll = this.roll;
        this.roll += this.angularVelocity;
        this.angularVelocity += this.angularAcceleration;
        if (!this.removed) {
            this.setSprite(this.sprites.get((this.age / 10) % 8 + 1, 8));
        }
    }
    public static MycotoxicSporeParticleProvider provider(SpriteSet sprites) {
        return new MycotoxicSporeParticleProvider(sprites);
    }
    
    public static class MycotoxicSporeParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        
        public MycotoxicSporeParticleProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }
        
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xa, double ya, double za, RandomSource random) {
            return new MycotoxicSporeParticle(level, x, y, z, xa, ya, za, this.sprites);
        }
    }
}

