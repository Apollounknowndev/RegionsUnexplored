package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("all")
@Mixin(Biome.class)
public class BiomeMixin {
	@ModifyReturnValue(
		method = "shouldSnow",
		at = @At("RETURN")
	)
	private boolean removeTundraSnow(boolean shouldSnow, LevelReader level, BlockPos pos) {
		return shouldSnow && ((Biome)(Object)this).hasPrecipitation();
	}
	
	@ModifyReturnValue(
		method = "getGrassColor",
		at = @At("RETURN")
	)
	private int removeTundraSnow(int color, double x, double z) {
		Biome $this = ((Biome)(Object)this);
		BiomeSpecialEffects effects = $this.getSpecialEffects();
		if (effects.getFoliageColorOverride().orElse(0) == 0x80c16c && effects.getGrassColorModifier().equals(BiomeSpecialEffects.GrassColorModifier.SWAMP)) {
			double groundValue = Biome.BIOME_INFO_NOISE.getValue(x * 0.01, z * 0.01, false);
			return groundValue < -0.7 ? 0x63763c : groundValue < 0.3 ? 0x788745 : 0xa2a852;
		}
		return color;
	}
}
