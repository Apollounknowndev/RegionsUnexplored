package net.regions_unexplored.client.particle.spore;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class FallingSporeParticle extends SingleQuadParticle {
    private int ticksOnGround;

    protected FallingSporeParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.007F;
    }
    
    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    
    @Override
    public int getLightCoords(float a) {
        return 240;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.removed) return;

        this.yd -= this.gravity;
        if (this.onGround) {
            this.ticksOnGround++;
            if (this.ticksOnGround == 1) {
                this.move(0, 0.02f, 0);
                this.onGround = true;
            } else if (this.ticksOnGround >= 5) {
                this.alpha = 1f - ((this.ticksOnGround - 5f) / 5f);
                if (this.ticksOnGround >= 10) {
                    this.remove();
                }
            }
        } else {
            this.ticksOnGround = 0;
            this.move(this.xd, this.yd, this.zd);
        }
        if (!this.removed) {
            this.xd *= 0.95F;
            this.yd *= 0.95F;
            this.zd *= 0.95F;
        }
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(ColorParticleOption option, ClientLevel level, double x, double y, double z, double xa, double ya, double za, RandomSource random) {
            var particle = new FallingSporeParticle(level, x, y, z, this.sprites.get(random));
            particle.setColor(option.getRed(), option.getGreen(), option.getBlue());
            return particle;
        }
    }
}
