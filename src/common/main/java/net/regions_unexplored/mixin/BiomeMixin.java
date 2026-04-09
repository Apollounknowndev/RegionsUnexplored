package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Biome.class)
public class BiomeMixin {
	@ModifyReturnValue(
		method = "shouldSnow",
		at = @At("RETURN")
	)
	private boolean removeTundraSnow(boolean shouldSnow, LevelReader level, BlockPos pos) {
		return shouldSnow && ((Biome)(Object)this).hasPrecipitation();
	}
}
