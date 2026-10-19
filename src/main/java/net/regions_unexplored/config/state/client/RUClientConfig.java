package net.regions_unexplored.config.state.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.regions_unexplored.config.json5.CommentedMapCodec;

public class RUClientConfig {
	public static final RUClientConfig DEFAULT = new RUClientConfig();
	public static final Codec<RUClientConfig> CODEC = RecordCodecBuilder.create(i -> i.group(
		CommentedMapCodec.commented(ParticleRates.CODEC, "particle_rates", "Controls the spawn rate of various particles. Default value for all is 1.").forGetter(c -> c.particleRates),
		CommentedMapCodec.commented(EucalyptusColors.CODEC, "eucalyptus_colors", "Controls the colors of the rainbow Eucalyptus logs").forGetter(c -> c.eucalyptusColors)
	).apply(i, RUClientConfig::new));
	
	public ParticleRates particleRates = new ParticleRates();
	public EucalyptusColors eucalyptusColors = new EucalyptusColors();
	
	private RUClientConfig() {}
	
	public RUClientConfig(ParticleRates particleRates, EucalyptusColors eucalyptusColors) {
		this.particleRates = particleRates;
		this.eucalyptusColors = eucalyptusColors;
	}
	
	public static class ParticleRates {
		public static final Codec<ParticleRates> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.doubleRange(0, 5).fieldOf("leaves").orElse(1.0).forGetter(r -> r.leaves),
			Codec.doubleRange(0, 5).fieldOf("prismarite").orElse(1.0).forGetter(r -> r.prismarite)
		).apply(i, ParticleRates::new));
		
		public double leaves = 1;
		public double prismarite = 1;
		
		public ParticleRates() {}
		
		public ParticleRates(double leaves, double prismarite) {
			this.leaves = leaves;
			this.prismarite = prismarite;
		}
	}
	
	public static class EucalyptusColors {
		public static final Codec<EucalyptusColors> CODEC = RecordCodecBuilder.create(i -> i.group(
			Codec.doubleRange(0, 200).fieldOf("transition_size").orElse(25.0).forGetter(c -> c.transitionSize),
			Codec.doubleRange(0, 1).fieldOf("saturation").orElse(0.5).forGetter(c -> c.saturation),
			Codec.doubleRange(0, 1).fieldOf("brightness").orElse(0.8).forGetter(c -> c.brightness)
		).apply(i, EucalyptusColors::new));
		
		public double transitionSize = 25;
		public double saturation = 0.5;
		public double brightness = 0.8;
		
		public EucalyptusColors() {}
		
		public EucalyptusColors(double transitionSize, double saturation, double brightness) {
			this.transitionSize = transitionSize;
			this.saturation = saturation;
			this.brightness = brightness;
		}
	}
}
